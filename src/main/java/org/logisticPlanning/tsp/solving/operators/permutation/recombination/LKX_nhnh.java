package org.logisticPlanning.tsp.solving.operators.permutation.recombination;

import java.util.Arrays;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.operators.BinaryOperator;
import org.logisticPlanning.utils.math.random.Randomizer;

/** a crossover operator somehow similar to LK */
public final class LKX_nhnh extends BinaryOperator<int[]> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** */
  public LKX_nhnh() {
    super("PermutationLKCrossover"); //$NON-NLS-1$
    // TODO Auto-generated constructor stub
  }

  @Override
  public void recombine(final Individual<int[]> dest,
      final ObjectiveFunction f, final Individual<int[]> parent1,
      final Individual<int[]> parent2) {
    final int[] parentPerm1, parentPerm2;
    long can1Length, can2Length, bestLength;
    int node1, node2;
    int addCount;
    int addCount1;
    final Randomizer r;
    int[] candidate;
    int count1;
    int index1;

    parentPerm1 = parent1.solution;
    parentPerm2 = parent2.solution;

    final int n = f.n();

    candidate = new int[n];
    for (int i = 0; i < n; i++) {
      candidate[i] = i;
    }

    final int[] can1 = new int[n];
    final int[] can2 = new int[n];
    int[] bestSol;

    addCount1 = addCount = 0;
    r = f.getRandom();

    dest.clearEvaluation();
    bestSol = dest.solution;
    if ((bestSol == null) || (bestSol.length != n)) {
      dest.solution = bestSol = new int[n];
    }
    dest.producer = this;
    dest.tourLength = 0l;

    Arrays.fill(can1, 0);
    Arrays.fill(can2, 0);
    System.arraycopy(parentPerm1, 0, bestSol, 0, parentPerm1.length);
    dest.tourLength = bestLength = parent1.tourLength;

    // System.out.println(Arrays.toString(parentPerm1)+","+f.evaluate(parentPerm1));
    // System.out.println(Arrays.toString(parentPerm2)+","+f.evaluate(parentPerm2));
    // System.out.println("__________________________");

    for (count1 = 0; count1 < n; count1++) {
      index1 = r.nextInt(n - count1);
      node1 = candidate[index1];
      candidate[index1] = candidate[n - count1 - 1];
      candidate[n - count1 - 1] = node1;
      if ((node1 + 1) >= n) {
        node2 = 0;
      } else {
        node2 = node1 + 1;
      }
      // System.out.println("begin____________________");
      // System.out.println("add edge is:"+parentPerm2[node1]+" ,
      // "+parentPerm2[node2]);

      this.getTheCanSol(n, node1, node2, bestSol, parentPerm2, can1, can2);
      // System.out.println("can1: "+Arrays.toString(can1));
      // System.out.println("can2: "+Arrays.toString(can2));
      can1Length = f.evaluate(can1);
      can2Length = f.evaluate(can2);
      if (can1Length < can2Length) {
        if (can1Length < bestLength) {
          System.arraycopy(can1, 0, bestSol, 0, can1.length);
          dest.tourLength = bestLength = can1Length;
          addCount1++;
        }
      } else {
        if (can2Length < bestLength) {
          System.arraycopy(can2, 0, bestSol, 0, can2.length);
          dest.tourLength = bestLength = can2Length;
          addCount1++;
        }
      }
      // System.out.println("bestSol:
      // "+Arrays.toString(bestSol)+","+f.evaluate(bestSol));
      // System.out.println("addCount1:"+addCount1);
      // System.out.println("end_______________________");

    }

    if (addCount1 == 0) {
      for (count1 = 0; count1 < (n / 2); count1++) {
        index1 = r.nextInt(n - count1);
        node1 = candidate[index1];
        candidate[index1] = candidate[n - count1 - 1];
        candidate[n - count1 - 1] = node1;
        if ((node1 + 1) >= n) {
          node2 = 0;
        } else {
          node2 = node1 + 1;
        }

        this.getTheCanSol(n, node1, node2, bestSol, parentPerm2, can1,
            can2);
        can1Length = f.evaluate(can1);
        can2Length = f.evaluate(can2);
        // System.out.println(can1Length);
        // System.out.println(can2Length);
        if (can1Length < can2Length) {
          System.arraycopy(can1, 0, bestSol, 0, can1.length);
          dest.tourLength = bestLength = can1Length;

        } else
          if (can1Length > can2Length) {
            System.arraycopy(can2, 0, bestSol, 0, can2.length);
            dest.tourLength = bestLength = can2Length;

          } else
            if (can1Length == can2Length) {
              System.arraycopy(can1, 0, bestSol, 0, can1.length);
              dest.tourLength = bestLength = can1Length;
            }
        addCount1++;
        // System.out.println("addCount1:"+addCount1);
        // System.out.println((addCount1 <= (n/2))+" "+(can1Length !=
        // can2Length));
      }
    }
    addCount1 = 0;

    // System.out.println("----------");
    for (count1 = 0; count1 < n; count1++) {
      index1 = r.nextInt(n - count1);
      node1 = candidate[index1];
      candidate[index1] = candidate[n - count1 - 1];
      candidate[n - count1 - 1] = node1;
      if ((node1 + 1) >= n) {
        node2 = 0;
      } else {
        node2 = node1 + 1;
      }
      // System.out.println(node1);
      // System.out.println("begin____________________");
      // System.out.println("add edge is:"+parentPerm2[node1]+" ,
      // "+parentPerm2[node2]);

      this.getTheCanSol(n, node1, node2, bestSol, parentPerm1, can1, can2);
      // System.out.println("can1: "+Arrays.toString(can1));
      // System.out.println("can2: "+Arrays.toString(can2));
      can1Length = f.evaluate(can1);
      can2Length = f.evaluate(can2);
      if (can1Length < can2Length) {
        if (can1Length < bestLength) {
          System.arraycopy(can1, 0, bestSol, 0, can1.length);
          dest.tourLength = bestLength = can1Length;
          addCount++;
        }
      } else {
        if (can2Length < bestLength) {
          System.arraycopy(can2, 0, bestSol, 0, can2.length);
          dest.tourLength = bestLength = can2Length;
          addCount++;
        }
      }
      // System.out.println("bestSol:
      // "+Arrays.toString(bestSol)+","+f.evaluate(bestSol));
      // System.out.println("addCount:"+addCount);
      // System.out.println("end_______________________");

    }

    if (addCount == 0) {
      // System.out.println("-------------");
      for (count1 = 0; count1 < (n / 2); count1++) {
        index1 = r.nextInt(n - count1);
        node1 = candidate[index1];
        candidate[index1] = candidate[n - count1 - 1];
        candidate[n - count1 - 1] = node1;
        if ((node1 + 1) >= n) {
          node2 = 0;
        } else {
          node2 = node1 + 1;
        }
        // System.out.println(node1);
        this.getTheCanSol(n, node1, node2, bestSol, parentPerm1, can1,
            can2);
        can1Length = f.evaluate(can1);
        can2Length = f.evaluate(can2);
        if (can1Length < can2Length) {
          System.arraycopy(can1, 0, bestSol, 0, can1.length);
          dest.tourLength = bestLength = can1Length;

        } else
          if (can1Length > can2Length) {
            System.arraycopy(can2, 0, bestSol, 0, can2.length);
            dest.tourLength = bestLength = can2Length;

          } else
            if (can1Length == can2Length) {
              System.arraycopy(can1, 0, bestSol, 0, can1.length);
              dest.tourLength = bestLength = can1Length;
            }
        addCount++;
        // System.out.println("addCount:"+addCount);
        // System.out.println((addCount <= (n/2))+" "+(can1Length !=
        // can2Length));
      }

    }
    addCount = 0;
    // System.out.println("finalbestSol:
    // "+Arrays.toString(bestSol)+bestLength);
    f.registerFE(bestSol, bestLength);

    // super.recombine(dest, f, parent1, parent2);
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    // TODO Auto-generated method stub
    super.beginRun(f);
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    // TODO Auto-generated method stub
    super.endRun(f);
  }

  /** {@inheritDoc} */
  @Override
  public LKX_nhnh clone() {
    // TODO Auto-generated method stub
    LKX_nhnh pec;
    pec = ((LKX_nhnh) (super.clone()));
    return pec;
  }

  /**
   * @param value
   * @param sol
   * @return
   */
  public int getTheIndex(final int value, final int[] sol) {

    for (int i = 0; i < sol.length; i++) {
      if (sol[i] == value) {
        return i;
      }
    }
    return -1;
  }

  /**
   * insert a edge from p2 into p1 to get two new solutions
   *
   * @param n
   * @param node1
   * @param node2
   * @param p1
   * @param p2
   * @param can1
   * @param can2
   */
  public void getTheCanSol(final int n, final int node1, final int node2,
      final int[] p1, final int[] p2, final int[] can1, final int[] can2) {

    can2[0] = can1[0] = p2[node1];
    can2[1] = can1[1] = p2[node2];

    int indexCan1, indexCan2;
    int countFirstHalf, countSecondHalf;
    int indexInP1;
    final int pinInP1 = this.getTheIndex(p2[node2], p1);

    indexCan2 = indexCan1 = 2;
    indexInP1 = pinInP1;
    countSecondHalf = countFirstHalf = 0;

    if ((indexInP1 + 1) > (n - 1)) {
      indexInP1 = -1;
    }
    while (p1[indexInP1 + 1] != p2[node1]) {
      // System.out.println("start fill firt half of can1 ");
      can1[indexCan1] = p1[indexInP1 + 1];
      // System.out.println(Arrays.toString(can1));
      countFirstHalf++;
      indexCan1++;
      indexInP1++;
      // System.out.println("indexCan1: "+indexCan1);
      // System.out.println("indexInP1: "+indexInP1);
      if ((indexInP1 + 1) > (n - 1)) {
        indexInP1 = -1;
      }
      // System.out.println("end fill firt half of can1 ");
    }

    indexInP1 = pinInP1;
    if ((indexInP1 - 1) < 0) {
      indexInP1 = n;
    }
    while (p1[indexInP1 - 1] != p2[node1]) {
      // System.out.println("start fill firt half of can2 ");
      can2[indexCan2] = p1[indexInP1 - 1];
      countSecondHalf++;
      indexCan2++;
      indexInP1--;
      if ((indexInP1 - 1) < 0) {
        indexInP1 = n;
      }

      // System.out.println("end fill firt half of can1 ");
    }

    // can1->can2
    System.arraycopy(can1, 2, can2, indexCan2, countFirstHalf);
    // can2->can1
    System.arraycopy(can2, 2, can1, indexCan1, countSecondHalf);

  }

}
