package org.logisticPlanning.tsp.solving.operators.permutation.recombination;

import java.util.Arrays;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.operators.BinaryOperator;

/**
 * The whole algorithm is based on a very important observation. When we
 * choose a specified edge in one parent, actually we are choosing a group
 * of edges in this parent, we call such groupId is zero based.
 */
public class BABOperator_2P_AP extends BinaryOperator<int[]> {
  /** missing doc */
  static class _Node {
    /**
     * notice the stored value are groupId if the edge set is from parent1,
     * we just store groupId if the edge set is from parent2, we store
     * groupId + p1.length
     */
    int[] includedArcSet;

    /** lower bound cost of this node */
    long lowbound_cost;

  }

  /** missing doc */
  private static final long serialVersionUID = 1L;

  /**
   * missing doc
   *
   * @param name
   *          missing doc
   */
  public BABOperator_2P_AP(final String name) {
    super(name);
    // TODO Auto-generated constructor stub
  }

  /** missing doc */
  public BABOperator_2P_AP() {
    this("BABOperator_2P_AP"); //$NON-NLS-1$
  }

  /** missing doc */
  private _Node[] m_nodeBuffer;
  /** missing doc */
  int m_nodeBufferSize;
  /** missing doc */
  private int[] m_p1;
  /** missing doc */
  private int[] m_p2;

  /**
   * this is the array indicating an assignment by considering each row and
   * column
   */
  private int[] m_assignment;

  /**
   * indicating which set of arcs we are going to choose in a given
   * groupId, 1 in p1 and 2 in p2; an assignment by groupId, a group
   * contains a certain number of rows and the columns corresponding to
   * those rows are specified in this m_groupAssignment. with value 1
   * indicating the arcs from parent one of the respect rows are included
   * in the assignment.
   */
  private int[] m_groupAssignment;

  /** This is used to record the answer the current TSP solution */
  private int[] m_asr;

  /** reverse map used to find row of a specified column of parent1 */
  private int[] m_p1_rm;

  /** reverse map used to find row of a specified column of parent2 */
  private int[] m_holderOfGroupId;

  /** auxiliary arrays */
  private long[] m_longAuxiliaryArray;
  /** missing doc */
  private int[] m_iaa1;
  /** missing doc */
  private int[] m_iaa2;

  /**
   * a map used to find whether a specified group is included in the
   * included arcs of a given node
   */
  private int[] m_reverseIncludedArcSet;

  // for an assignment, we find all the subtours contained in the
  // assignment
  // private int[] subtours;

  /** a map from row to groupId */
  private int[] m_rowToGroup;

  /** the same edge of two parents */
  private int[] m_sameAssignment;

  /**
   * group array and weight array should have the same length; a map from
   * groupId to index
   */
  private int[] m_groupArray;
  /** missing doc */
  private long[] m_weightArray;

  // private int MaxNodeGenerated;
  /**
   * missing doc
   *
   * @param node
   *          missing doc
   * @param result
   *          missing doc
   * @param sameAssignment
   *          missing doc
   * @return missing doc
   */
  public int[] constructAnAssignment(final _Node node, final int[] result,
      final int[] sameAssignment) {
    Arrays.fill(this.m_assignment, -1);
    for (int i = 0; i < this.m_groupAssignment.length; i++) {
      // s is a row in group i
      int s = this.m_groupArray[i];
      int[] p;
      if (this.m_groupAssignment[i] == 1) {
        p = this.m_p1;
      } else {
        p = this.m_p2;
      }

      do {
        result[s] = p[s];
        s = this.findNextRow(s);
      } while (s != this.m_groupArray[i]);
    }

    for (final int i : sameAssignment) {
      result[i] = this.m_p1[i];
    }

    return result;
  }

  /**
   * missing doc
   *
   * @param node
   *          missing doc
   * @param f
   *          missing doc
   * @return missing doc
   */
  public long solveAPInGroupPresentation(final _Node node,
      final ObjectiveFunction f) {
    Arrays.fill(this.m_groupAssignment, -1);
    long sum = 0;
    for (final int i : node.includedArcSet) {
      if (i < this.m_p1.length) {
        this.m_groupAssignment[i % this.m_p1.length] = 1;
        sum = sum + this.getWeight(1, i % this.m_p1.length);
      } else {
        this.m_groupAssignment[i % this.m_p1.length] = 2;
        sum = sum + this.getWeight(2, i % this.m_p1.length);
      }
    }

    for (int i = 0; i < this.m_groupAssignment.length; i++) {
      if (this.m_groupAssignment[i] < 0) {
        this.m_groupAssignment[i] = this.getWeight(1, i) < this
            .getWeight(2, i) ? 1 : 2;
        if (this.m_groupAssignment[i] == 1) {
          sum = sum + this.getWeight(1, i);
        } else {
          sum = sum + this.getWeight(2, i);
        }
      }
    }
    for (final int i : this.m_sameAssignment) {
      sum = sum + f.distance(i + 1, this.m_p1[i] + 1);
    }
    return sum;
  }

  /**
   * missing doc
   *
   * @param parent
   *          missing doc
   * @param groupId
   *          missing doc
   * @return missing doc
   */
  public final long getWeight(final int parent, final int groupId) {
    if (parent == 1) {
      return this.m_weightArray[groupId % this.m_p1.length];
    }
    return this.m_weightArray[this.m_weightArray.length - 1
        - (groupId % this.m_p1.length)];
  }

  /**
   * missing doc
   *
   * @param apSolution
   *          missing doc
   * @return missing doc
   */
  public boolean isTspTour(final int[] apSolution) {
    boolean result = false;
    int index = 0;
    int total = 0;
    do {
      total++;
      index = apSolution[index];

    } while (index != 0);

    if (total == apSolution.length) {
      result = true;
    }

    return result;
  }

  /**
   * missing doc
   *
   * @param f
   *          missing doc
   */
  public void preProcess(final ObjectiveFunction f) {
    // for all the row that has been explored.
    Arrays.fill(this.m_assignment, -1);
    Arrays.fill(this.m_asr, -1);
    Arrays.fill(this.m_rowToGroup, -1);
    final int[] markerArray = this.m_assignment;
    final long[] weightArray = this.m_longAuxiliaryArray;
    // used to install groupID to a specified row
    int index = 0;
    final int[] groupArray = this.m_asr;
    // int groupId = 0;
    int sameAssignmentCount = 0;
    for (int i = 0; i < this.m_p1.length; i++) {
      if (markerArray[i] > 0) {
        continue;
      }
      if (this.m_p1[i] == this.m_p2[i]) {
        this.m_rowToGroup[i] = -1;
        markerArray[i] = 2;
        sameAssignmentCount++;
        continue;
      }
      int idx = i;
      long sum1 = 0;
      long sum2 = 0;
      do {
        markerArray[idx] = 1;
        sum1 = f.distance(idx + 1, this.m_p1[idx] + 1) + sum1;
        sum2 = f.distance(idx + 1, this.m_p2[idx] + 1) + sum2;
        this.m_rowToGroup[idx] = index;
        idx = this.findNextRow(idx);
      } while (idx != i);
      groupArray[index] = i;
      weightArray[index] = sum1;
      weightArray[this.m_p1.length - 1 - index] = sum2;
      index++;
    }

    // System.out.println(index);

    this.m_groupArray = Arrays.copyOf(groupArray, index);
    this.m_weightArray = new long[index * 2];
    this.m_groupAssignment = new int[index];
    this.m_reverseIncludedArcSet = new int[index];
    this.m_sameAssignment = new int[sameAssignmentCount];
    Arrays.fill(this.m_iaa1, -1);
    Arrays.fill(this.m_iaa2, -1);
    int ith = 0;
    for (int i = 0; i < this.m_p1.length; i++) {
      if (markerArray[i] == 2) {
        this.m_sameAssignment[ith++] = i;
      }
    }
    System.arraycopy(weightArray, 0, this.m_weightArray, 0, index);
    System.arraycopy(weightArray, weightArray.length - index,
        this.m_weightArray, index, index);
  }

  /**
   * find next row in a group, given any row in such a group.
   *
   * @param row
   *          missing doc
   * @return missing doc
   */
  private int findNextRow(final int row) {
    return this.m_p1_rm[this.m_p2[row]];
  }

  /** missing doc */
  private long m_bestF;

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);
    final int m = f.n();
    // this.f = f;
    this.m_nodeBufferSize = 0;
    this.m_p1 = new int[m];
    this.m_p2 = new int[m];
    this.m_asr = new int[m];
    this.m_assignment = new int[m];
    this.m_longAuxiliaryArray = new long[m];
    this.m_rowToGroup = new int[m];
    this.m_p1_rm = new int[m];
    this.m_holderOfGroupId = new int[m];
    this.m_iaa1 = new int[m];
    this.m_iaa2 = new int[m];
    this.m_nodeBuffer = new _Node[10];
  }

  @Override
  public BABOperator_2P_AP clone() {
    BABOperator_2P_AP o;

    o = (BABOperator_2P_AP) (super.clone());

    o.m_p1 = null;
    o.m_p2 = null;
    o.m_asr = null;
    o.m_rowToGroup = null;
    o.m_assignment = null;
    o.m_groupArray = null;
    o.m_sameAssignment = null;
    o.m_longAuxiliaryArray = null;
    o.m_iaa1 = null;
    o.m_iaa2 = null;
    o.m_nodeBuffer = null;
    o.m_nodeBufferSize = 0;
    o.m_groupArray = null;
    o.m_groupArray = null;
    o.m_groupAssignment = null;
    o.m_reverseIncludedArcSet = null;
    o.m_sameAssignment = null;

    return o;
  }

  @Override
  public void endRun(final ObjectiveFunction f) {
    // this.f = null;
    this.m_p1 = null;
    this.m_p2 = null;
    this.m_asr = null;
    this.m_rowToGroup = null;
    this.m_assignment = null;
    this.m_groupArray = null;
    this.m_sameAssignment = null;
    this.m_longAuxiliaryArray = null;
    this.m_iaa1 = null;
    this.m_iaa2 = null;
    this.m_nodeBuffer = null;
    this.m_nodeBufferSize = 0;
    super.endRun(f);
  }

  /**
   * @param assignment
   * @param result
   * @param node
   * @return
   */
  public int getSubtoursOfTheAssignment(final int[] assignment,
      final int[] result, final _Node node) {
    Arrays.fill(this.m_reverseIncludedArcSet, -1);
    for (final int i : node.includedArcSet) {
      this.m_reverseIncludedArcSet[i % this.m_p1.length] = 1;
    }
    int starter = -1;
    // int[] subtourLengthRecorder = iaa1;
    // Arrays.fill(subtourLengthRecorder, -1);
    // Arrays.fill(result, -1);
    int subtourSize = 0;
    final int[] markerArray = this.m_iaa2;
    Arrays.fill(markerArray, -1);
    int smallestSubtourLength = Integer.MAX_VALUE;
    final int length = assignment.length;
    for (int i = 0; i < length; i++) {
      if (markerArray[i] > 0) {
        continue;
      }
      int innerIndex = i;
      // count the number of the arcs in this sub-tour which are not
      // contained in the arcs of included arcs of the node we are
      // considering.
      int arcLength = 0;
      // count the number of the arcs of this sub-tour
      // int thisSubtourLength = 0;
      do {
        markerArray[innerIndex] = 1;

        // check if arc is in the included arc
        if ((this.m_rowToGroup[innerIndex] != -1)
            && (this.m_reverseIncludedArcSet[this.m_rowToGroup[innerIndex]] < 0)) {
          arcLength++;
        }
        // thisSubtourLength++;
        innerIndex = assignment[innerIndex];
      } while (innerIndex != i);

      // update arcLength is a smaller tour is found.
      if (arcLength < smallestSubtourLength) {
        smallestSubtourLength = arcLength;
        starter = i;
      }

      // record the length of the sub-tour starting from i
      // subtourLengthRecorder[i] = thisSubtourLength;

      // int k = subtourSize - 1;

      // while (k >= 0
      // && subtourLengthRecorder[result[k]] < thisSubtourLength) {
      // result[k + 1] = result[k];
      // k--;
      // }
      // result[k + 1] = i;
      subtourSize++;

    }

    // if we only have one subtour, then this assignment is a TSP solution
    // and we return dimension of this TSP problem.
    if (subtourSize == 1) {
      starter = assignment.length;
    }

    return starter;

  }

  /**
   * @param assignment
   * @param starterOfTheSubtour
   * @param holderOfGroupId
   * @param reverseIncludedArcSet
   */
  public void reOrganizeSmallestSubtour(final int[] assignment,
      final int starterOfTheSubtour, final int[] holderOfGroupId,
      final int[] reverseIncludedArcSet) {
    Arrays.fill(holderOfGroupId, -1);
    int i = starterOfTheSubtour;
    do {
      if ((this.m_rowToGroup[i] != -1)
          && (reverseIncludedArcSet[this.m_rowToGroup[i]] < 0)) {
        int j = 0;
        while ((holderOfGroupId[j] != -1)
            && (holderOfGroupId[j] != this.m_rowToGroup[i])) {
          j++;
        }
        holderOfGroupId[j] = this.m_rowToGroup[i];
      }
      i = assignment[i];
    } while (i != starterOfTheSubtour);
  }

  /**
   * @param node
   * @return
   */
  public boolean detectSubtourInIncludedArcs(final _Node node) {
    Arrays.fill(this.m_iaa1, -1);
    Arrays.fill(this.m_iaa2, -1);
    for (final int i : node.includedArcSet) {
      final int[] p = i < this.m_p1.length ? this.m_p1 : this.m_p2;
      int index = this.m_groupArray[i % this.m_p1.length];
      do {
        this.m_iaa1[index] = p[index];
        index = this.findNextRow(index);
      } while (index != this.m_groupArray[i % this.m_p1.length]);

    }

    for (final int i : this.m_sameAssignment) {
      this.m_iaa1[i] = this.m_p1[i];
    }

    for (int i = 0; i < this.m_iaa1.length; i++) {
      if ((this.m_iaa1[i] == -1) || (this.m_iaa2[2] > -1)) {
        continue;
      }
      int starter = i;
      do {

        this.m_iaa2[starter] = 1;
        starter = this.m_iaa1[starter];
        if (starter == i) {
          return true;
        }
      } while (this.m_iaa1[starter] != -1);
    }
    return false;
  }

  /**
   * @param f
   * @param parent1
   * @param parent2
   */
  public void produce(final ObjectiveFunction f,
      final Individual<int[]> parent1, final Individual<int[]> parent2) {

    // MaxNodeGenerated = 0;
    int[] solution;
    if (parent1.tourLength > parent2.tourLength) {
      solution = parent2.solution;
    } else {
      solution = parent1.solution;
    }

    this.__pathToAdj(solution, this.m_asr, null);
    this.m_bestF = parent1.tourLength > parent2.tourLength
        ? parent2.tourLength : parent1.tourLength;
    _Node initialNode = new _Node();
    initialNode.includedArcSet = new int[0];
    initialNode.lowbound_cost = 0;
    for (int i = 0; i < this.m_groupAssignment.length; i++) {
      final long smallW = this.getWeight(1, i) < this.getWeight(2, i)
          ? this.getWeight(1, i) : this.getWeight(2, i);
      initialNode.lowbound_cost = initialNode.lowbound_cost + smallW;
    }

    for (final int i : this.m_sameAssignment) {
      initialNode.lowbound_cost = initialNode.lowbound_cost
          + f.distance(i + 1, this.m_p1[i] + 1);
    }

    this.m_nodeBuffer[this.m_nodeBufferSize++] = initialNode;
    initialNode = null;

    search: while (this.m_nodeBufferSize > 0) {
      // if(this.m_nodeBufferSize > MaxNodeGenerated){
      // MaxNodeGenerated = this.m_nodeBufferSize;
      // }

      _Node workingNode = null;
      for (int i = 0; i < this.m_nodeBufferSize; i++) {
        final _Node z = this.m_nodeBuffer[i];
        if (z.lowbound_cost >= this.m_bestF) {
          this.m_nodeBuffer[i] = null;
        } else {
          if (workingNode != null) {
            if (z.lowbound_cost < workingNode.lowbound_cost) {
              this.m_nodeBuffer[i] = workingNode;
              workingNode = z;
            }
            continue search;
          }
          workingNode = z;
        }
        this.m_nodeBuffer[i] = this.m_nodeBuffer[--this.m_nodeBufferSize];
        this.m_nodeBuffer[this.m_nodeBufferSize] = null;
      }

      if (workingNode == null) {
        return;
      }

      // construct an assignment from workingNode
      {
        final long cost = this.solveAPInGroupPresentation(workingNode, f);
        this.constructAnAssignment(workingNode, this.m_assignment,
            this.m_sameAssignment);
        if (this.isTspTour(this.m_assignment)) {
          if (cost < this.m_bestF) {
            this.m_bestF = cost;
            System.arraycopy(this.m_assignment, 0, this.m_asr, 0,
                this.m_asr.length);
          }
          continue search;
        }

      }

      // from this assignment, we generate more node denoting other
      // assignments space
      {
        final int starter = this.getSubtoursOfTheAssignment(
            this.m_assignment, null, workingNode);
        // int[] holderOfGroupId = this.holderOfGroupId;
        this.reOrganizeSmallestSubtour(this.m_assignment, starter,
            this.m_holderOfGroupId, this.m_reverseIncludedArcSet);
        for (int i = 0; this.m_holderOfGroupId[i] != -1; i++) {
          final _Node node = new _Node();

          final int[] inc = new int[workingNode.includedArcSet.length + i
              + 1];
          System.arraycopy(workingNode.includedArcSet, 0, inc, 0,
              workingNode.includedArcSet.length);

          // included arc sets
          for (int j = 0; j < i; j++) {
            final int groupId = this.m_holderOfGroupId[j];
            final int whichOne = this.m_groupAssignment[groupId];
            inc[workingNode.includedArcSet.length + j] = whichOne == 1
                ? groupId : groupId + this.m_p1.length;
          }

          // the excluded arcs set
          final int groupId = this.m_holderOfGroupId[i];
          final int whichOne = this.m_groupAssignment[groupId];
          final int otherOne = (whichOne == 1 ? 2 : 1);
          inc[workingNode.includedArcSet.length + i] = (whichOne == 1
              ? groupId + this.m_p1.length : groupId);
          final long diffInCost = this.getWeight(otherOne, groupId)
              - this.getWeight(whichOne, groupId);
          // assert diffInCost >= 0;
          node.includedArcSet = inc;
          node.lowbound_cost = workingNode.lowbound_cost + diffInCost;
          if (this.detectSubtourInIncludedArcs(node)
              || (node.lowbound_cost >= this.m_bestF)) {
            continue;
          }
          this.__addNodeToBuffer(node);
          // m_nodeBuffer[this.m_nodeBufferSize++] = node;

        }

      }

    }

  }

  @Override
  public final void recombine(final Individual<int[]> dest,
      final ObjectiveFunction f, final Individual<int[]> parent1,
      final Individual<int[]> parent2) {

    // this.f = f;
    this.__pathToAdj(parent1.solution, this.m_p1, this.m_p1_rm);
    this.__pathToAdj(parent2.solution, this.m_p2, null);
    this.preProcess(f);
    this.produce(f, parent1, parent2);
    // assert(m_asr[0] != 0);
    dest.solution = this.__adjToPath(this.m_asr).clone();
    // System.out.println("Max Node Generated in size " + p1.length +" is
    // -------------->" + MaxNodeGenerated);
    // System.out.println("not found");
    // if(bestF < parent1.tourLength && bestF < parent2.tourLength){
    // System.out.println("Found!" + "parent1 -->" + parent1.tourLength +
    // ";parent2 -- >" + parent2.tourLength + ";new ---->" + bestF);
    // for(int i : dest.solution){
    // System.out.print(i + "-->");
    // }
    //
    // System.out.println(verifyTspTour(dest.solution));
    // System.out.println("Max Node Generated in size " + p1.length +" is
    // -------------->" + MaxNodeGenerated);
    // }
    dest.tourLength = this.m_bestF;
    dest.producer = this;

  }

  /**
   * This method convert the path representation of a solution to the
   * adjacent representation. The path here should be of the length n and
   * adj of length 2 * n
   *
   * @param path
   *          the array with path representation
   * @param adj
   *          the array with adjacent representation
   * @param reverse
   */
  public void __pathToAdj(final int[] path, final int[] adj,
      final int[] reverse) {
    for (int i = 0; i < path.length; i++) {
      adj[path[i] - 1] = path[(i + 1) % path.length] - 1;
      if (reverse != null) {
        reverse[path[(i + 1) % path.length] - 1] = path[i] - 1;
      }
    }
  }

  /**
   * @param node
   */
  private final void __addNodeToBuffer(final _Node node) {
    if (this.m_nodeBufferSize >= this.m_nodeBuffer.length) {
      this.m_nodeBuffer = BABOperator_2P_AP
          .__extendArray(this.m_nodeBuffer);
    }
    this.m_nodeBuffer[this.m_nodeBufferSize++] = node;
  }

  /**
   * @param oldA
   * @return
   */
  private static final _Node[] __extendArray(final _Node[] oldA) {
    final _Node[] newA;
    final int k;

    k = oldA.length << 1;
    newA = new _Node[k < oldA.length ? Integer.MAX_VALUE : k];
    System.arraycopy(oldA, 0, newA, 0, oldA.length);
    return newA;
  }

  /**
   * @param in
   * @return
   */
  private final int[] __adjToPath(final int[] in) {
    final int[] ret = new int[in.length];
    int j = in[0];
    for (int i = 0; i < in.length; i++) {
      ret[i] = j + 1;
      j = in[j];
    }
    return ret;
  }

}