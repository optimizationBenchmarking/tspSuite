package org.logisticPlanning.tsp.solving.algorithms.localSearch.fundamentalStemAndCycle;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchAlgorithm;
import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateCanonical;
import org.logisticPlanning.tsp.solving.utils.candidates.CandidateSet;



/**
 * The fundamental stem and cycle algorithm working internally on a stem-and-cycle data
 * structure.
 */
public class FundamentalStemAndCycle extends TSPLocalSearchAlgorithm<int[]> {

	/** the serial version uid */
	private static final long serialVersionUID = 1L;
	// Liu Weichen's varies
	/** The number of all the nodes */
	private int m_n;
	/** the node neighbors, store left and right node of each node from 1 to n */
	private int[] m_neighbors;
	/** The better tour */
	private int[] m_betterTour;
	/** The root node */
	private int m_rootNode;
	/** The first node of the stem connecting the root node */
	private int m_beginStemNode;
	/** The last node of the stem */
	private int m_endStemNode;
	/** The j node, selecting form the available nodes */
	private int m_jNode;
	/** Store four values that present whether change the structure in the four methods */
	private boolean[] m_flags;
	/** The level to change the structure*/
	private int m_level;
	/** Store the deleted edges*/
	private boolean[][] m_tubaEdge;
	/** The list of root node can be selected*/
	private int[] m_rootList;
	/** The length of the root list*/
	private int m_rootListLength;
	/** The root list for sec in fsec*/
	private int[] m_rootListFSec;
	/** The length of the rootListESec*/
	private int m_rootListFSecLength;
	/** The neighbor list for PSec in the FSec*/
	private CandidateSet m_nearestNeighborhood;
	//private boolean m_doOnce = true;
	/** The information of tour */
	private ObjectiveFunction m_f;
	private Individual<int[]> m_dst;
//long temp = Long.MAX_VALUE;
	/** create */
	public FundamentalStemAndCycle() {
		super("Subpath Ejection Algorithm using a fundamental Stem-and-Cycle Datastructure");
	}

	/**
	 * The main routine
	 * 
	 * @param srcdst
	 *            an individual record with a tour in srcdst.solution
	 *            represented as permutation of the numbers (1, 2, 3, ..., n)
	 *            and the tour length is stored in srcdst.tourLength. this
	 *            variable is also the output: at the end of this method, you
	 *            need to store the best tour you found again in srcdst.solution
	 *            (as permutation) and the tour length store in
	 *            srcdest.tourlength
	 * @param f
	 *            if the objective function which can tell you the distance
	 *            between two cities and the total tour length of a permutation
	 *            or adjacency list representation
	 */
	/** {@inheritDoc} */
	@Override
	public void beginRun(final ObjectiveFunction f) {
		super.beginRun(f);
		this.m_f = f;
		this.m_n = f.n();
		//Store left and right node of each node from 1 to n
		this.m_neighbors = new int[this.m_n << 1];
		this.m_betterTour = new int[this.m_n];
		// if you need to allocate/create a big object, say, an integer array or
		// a list or something, you can allocate it here and store it in a
		// member variable. you can then use this variable in the localSearch
		// method
		this.m_nearestNeighborhood = CandidateSet.allocate(this.m_f, 10, this.m_nearestNeighborhood);
	}
	/** {@inheritDoc} */
	@Override
	public void endRun(final ObjectiveFunction f) {
		// if you have allocated an object in beginRun, here you need to dispose
		// it: if you store something in a member variable, here you need to set
		// this variable to null
		super.endRun(f);
		this.m_neighbors = null; //The left and right node of each node
		this.m_betterTour = null; // The better tour
		/** The root node */
		this.m_rootNode = 0;
		/** The first node of the stem connecting the root node */
		this.m_beginStemNode = 0;
		/** The last node of the stem */
		this.m_endStemNode = 0;
		/** The j node, selecting form the available nodes */
		this.m_jNode = 0;
		/**Store the variance for four method, 0 presents no change occur, 1 present change*/
		this.m_flags = null;
		/** Store the deleted edges*/
		this.m_tubaEdge = null;
		/** The list of root node can be selected*/
		this.m_rootList = null;
		/** The root list for sec in fsec*/
		this.m_rootListFSec = null;
		/** The neighbor list for PSec in the FSec*/
		//this.m_nearestNeighbor = null;
		/** The information of tour */
		this.m_f = null;
		this.m_dst = null;
	}
	public void localSearch(final Individual<int[]> srcdst,
			final ObjectiveFunction f) {
		// The initialization of the tour
		this.m_dst = srcdst;
		this.__resetBestTour();
		// Initialization of the m_betterTour[]
		this.m_betterTour = new int[this.m_n];
		//Initialization of the root list
		this.m_rootList = new int[this.m_n];
		//The root list for FSec
		this.m_rootListFSec = new int[this.m_n];
		//level in sec is 6, select 10 nearest neighbors
		//this.m_nearestNeighbor = new int[this.m_n][10];
//		this.__initRootList();
//		this.m_rootNode = this.__getRandomRoot();
//		this.m_beginStemNode = this.m_rootNode;
//		this.m_endStemNode = this.m_rootNode;
		this.m_flags = new boolean[4];
		this.m_level = this.m_n<<1;
		//The end of the int[][m_level] store the length of the int[]
		this.m_tubaEdge = new boolean[this.m_n][this.m_n];
		//System.out.println("**********Start*************");
		//this.__initTubaList();
//		this.__initRootList();
//		this.__secRootNoChanged();
//		if(this.m_doOnce) {
//			this.m_doOnce = false;
//			this.__greedAlgorithm();
//			this.__changeToTour();
//			this.__resetBestTour();
//		}
		
		this.__pSecRootNoChanged();
		
		//this.__fSecRootNoChanged();
		
		//this.__printTubaEdge();
		//this.__printTubaList();
		//System.exit(1);
	}
	/**
	 * get random root node from root list
	 * @return the available root node
	 */
	private final int __getRandomRoot() {
		int root = 0;
		if(this.m_rootListLength != 0) {
			int index = this.m_f.getRandom().nextInt(this.m_rootListLength);
			root = this.m_rootList[index];
			this.m_rootList[index] = this.m_rootList[--this.m_rootListLength];
			//System.out.println("root list length" + this.m_rootListLength);
		} else {
			System.out.println("root list is empoty!!");
			int[] i = new int[2];
			i[2] = 8;
			System.exit(1);
		}
		return root;
	}

	/**
	 * this.m_dst stores the best tour, reset the best tour to current tour
	 */
	private final void __resetBestTour() {
		int [] sol = this.m_dst.solution;
		//Set the neighbors of the nodes including left and right nodes
		this.__setLeftNeighbor(sol[0], sol[this.m_n-1]);
		this.__setRightNeighbor(sol[0], sol[1]);
		this.__setRightNeighbor(sol[this.m_n - 1], sol[0]);
		this.__setLeftNeighbor(sol[this.m_n - 1], sol[this.m_n - 2]);
		for (int i = 1; i < this.m_n - 1; i++) {
			this.__setLeftNeighbor(sol[i], sol[i-1]);
			this.__setRightNeighbor(sol[i], sol[i + 1]);
		}
	}
	/**
	 * reset the best tour to current tour
	 */
	private final void __resetTour(int[] tour) {
		int [] sol = tour;
		//Set the neighbors of the nodes including left and right nodes
		this.__setLeftNeighbor(sol[0], sol[this.m_n-1]);
		this.__setRightNeighbor(sol[0], sol[1]);
		this.__setRightNeighbor(sol[this.m_n - 1], sol[0]);
		this.__setLeftNeighbor(sol[this.m_n - 1], sol[this.m_n - 2]);
		for (int i = 1; i < this.m_n - 1; i++) {
			this.__setLeftNeighbor(sol[i], sol[i-1]);
			this.__setRightNeighbor(sol[i], sol[i + 1]);
		}
	}
	/**
	 * initialization of the root list
	 */
	private final void __initRootList() {
		this.m_rootListLength = this.m_n;
		PermutationCreateCanonical.makeCanonical(this.m_rootList);
	}
	/**
	 * Imply two rules that root node does not change, do the process of pFsc 
	 * @return no use
	 */
	private final void __pSecRootNoChanged() {
		long lastTourLength = Integer.MAX_VALUE;
		this.__initRootList();
		while(this.m_rootListLength != 0 && (!(this.m_f.shouldTerminate()))) {
			this.__secRootNoChanged();
			this.__resetBestTour();
			this.m_beginStemNode = this.m_rootNode;
			this.m_endStemNode = this.m_rootNode;
			long currentTour = this.__getBetterTour();
			if(currentTour < lastTourLength) {
				lastTourLength = currentTour;
				this.__initRootList();
			}
			//System.out.println("root list length " + this.m_rootListLength);
		}
	}
	/**
	 * The process of the full sec
	 */
	private final void __fSecRootNoChanged() {
		this.__pSecRootNoChanged();
		this.__initRootList();
		while(this.m_rootListLength > 0 && (!(this.m_f.shouldTerminate()))) {
			this.__secFirstFsec();
			this.__pSecForFSec();
			this.__resetBestTour();
		}
		this.__resetBestTour();
//		this.m_beginStemNode = this.m_rootNode;
//		this.m_endStemNode = this.m_rootNode;
//		if(this.__getBetterTour() < temp) {
//			temp = this.__getBetterTour();
//			System.out.println(this.m_n + " " + this.__getBetterTour());
//		}
	}
	/**
	 * process of the psec for fsec
	 */
	private final void __pSecForFSec() {
		this.m_beginStemNode = this.m_rootNode;
		this.m_endStemNode = this.m_rootNode;
		//Before the psec, the best tour length
		long lastTourLength = this.__getBetterTour();
		while(this.m_rootListFSecLength > 1) {
			long currentTourLength = this.__secRootNoChangedFSec();
			if(currentTourLength < lastTourLength) {
				lastTourLength = currentTourLength;
				this.__initRootList();
			}
		}
	}
	/**
	 * the process of sec for the FSec
	 */
	private final void __secFirstFsec() {
		//Set for the FSec
		this.m_level = 6;
		long bestTourLength = Integer.MAX_VALUE;
		int bestLevelLength = 0;
		int[] bestSolution = this.m_betterTour;
		//Level is 6, add root node
		int[] deletedEdge = new int[14];
		int deletedEdgeLength = 0;
		this.__initTubaList();
		int countLevel = 0;
		this.m_rootNode = this.__getRandomRoot();
		//Add the root node to the deleted edges
		deletedEdge[deletedEdgeLength++] = this.m_rootNode;
		
		this.m_beginStemNode = this.m_rootNode;
		this.m_endStemNode = this.m_rootNode;
		while(countLevel < this.m_level) {
			countLevel++;
			int[] jOnCycle = this.__jOnCycleRootNoChanged();
			int[] jOnStem = this.__jOnStemNoRootChanged();
			int trialBefore = this.__trialSolution(this.m_rootNode, 
					this.__getLeftNeighbor(this.m_rootNode), 
					this.__getRightNeighbor(this.m_rootNode), 
					this.m_endStemNode);
			if(jOnCycle == null && jOnStem == null) {
				break;
			}
			if(jOnCycle != null && (jOnStem == null || jOnCycle[0] >= jOnStem[0])) {
				this.__addEdge(jOnCycle[2], jOnCycle[3]);
				//System.out.println("j on cycle Delete edge " + jOnCycle[2] + " " + jOnCycle[3]);
				this.m_jNode = jOnCycle[2];
				int formerNode = 0;
				int laterNode = 0;
				if(jOnCycle[1] == 1) {
					formerNode = jOnCycle[3];
					laterNode = this.__getNeighbor(this.m_jNode, formerNode);
				} else if(jOnCycle[1] == 2) {
					laterNode = jOnCycle[3];
					formerNode = this.__getNeighbor(this.m_jNode, laterNode);
				}
				int subRoot = this.__getLeftNeighbor(this.m_rootNode);
				int subRootAno = this.__getRightNeighbor(this.m_rootNode);
				
				if(jOnCycle[1] == 1) {
					if(this.m_beginStemNode != this.m_rootNode) {
						//change the structure
						this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
						this.__setNeighborSame(this.m_jNode, formerNode, this.m_endStemNode);
						this.__setNeighborSame(formerNode, this.m_jNode, -1);
						this.__setNeighborSame(this.m_rootNode, subRoot, this.m_beginStemNode);
						//change the root node
						this.m_beginStemNode = subRoot;
						this.m_endStemNode = formerNode;
					} else {
						//change the structure
						this.__setNeighborSame(this.m_rootNode, subRoot, this.m_jNode);
						this.__setNeighborSame(this.m_jNode, formerNode, this.m_rootNode);
						this.__setNeighborSame(formerNode, this.m_jNode, -1);
						//change the root node
						this.m_beginStemNode = subRoot;
						this.m_endStemNode = formerNode;
					} 
				} else if(jOnCycle[1] == 2) {
					if(this.m_beginStemNode != this.m_rootNode) {
						//change the structure
						this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
						this.__setNeighborSame(this.m_jNode, laterNode, this.m_endStemNode);
						this.__setNeighborSame(laterNode, this.m_jNode, -1);
						this.__setNeighborSame(this.m_rootNode, subRootAno, this.m_beginStemNode);
						//change the root node
						this.m_beginStemNode = subRootAno;
						this.m_endStemNode = laterNode;
					} else {
						//change the structure
						this.__setNeighborSame(this.m_rootNode, subRootAno, this.m_jNode);
						this.__setNeighborSame(this.m_jNode, laterNode, this.m_rootNode);
						this.__setNeighborSame(laterNode, this.m_jNode, -1);
						//change the root node
						this.m_beginStemNode = subRootAno;
						this.m_endStemNode = laterNode;
					}
				}
				//When get the best tour, put the edge to deletedEdge[]
				deletedEdge[deletedEdgeLength++] = jOnCycle[2];
				deletedEdge[deletedEdgeLength++] = jOnCycle[3];

				int trialAfter = this.__trialSolution(this.m_rootNode, 
						this.__getLeftNeighbor(this.m_rootNode), 
						this.__getRightNeighbor(this.m_rootNode), 
						this.m_endStemNode);
				if((jOnCycle[0] + trialAfter - trialBefore) > 0) {
					long currentTourlength = this.__getBetterTour();
					if(currentTourlength < bestTourLength) {
						bestTourLength = currentTourlength;
						bestLevelLength = deletedEdgeLength;
						bestSolution = this.m_betterTour;
					}
				}
			} else if(jOnStem != null && (jOnCycle == null || jOnStem[0] >= jOnCycle[0])) {
				this.__addEdge(jOnStem[1], jOnStem[2]);
				//System.out.println("j on stem Delete edge " + jOnStem[1] + " " + jOnStem[2]);
				this.m_jNode = jOnStem[1];
				int laterNode = jOnStem[2];
				int formerNode = this.__getNeighbor(this.m_jNode, laterNode);
				// change the relation
				this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
				this.__setNeighborDifferent(this.m_jNode, formerNode, this.m_endStemNode);
				this.__setNeighborSame(laterNode, this.m_jNode, -1);
				// change the root node, begin stem node
				this.m_endStemNode = laterNode;
				//When get the best tour, put the edge to deletedEdge[]
				deletedEdge[deletedEdgeLength++] = jOnStem[1];
				deletedEdge[deletedEdgeLength++] = jOnStem[2];

				int trialAfter = this.__trialSolution(this.m_rootNode, 
						this.__getLeftNeighbor(this.m_rootNode), 
						this.__getRightNeighbor(this.m_rootNode), 
						this.m_endStemNode);
				if((jOnStem[0] + trialAfter - trialBefore) > 0) {
					long currentTourlength = this.__getBetterTour();
					if(currentTourlength < bestTourLength) {
						bestTourLength = currentTourlength;
						bestLevelLength = deletedEdgeLength;
						bestSolution = this.m_betterTour;
					}
				}
			}
		}
		//Remove the repeated node
		for(int i = deletedEdgeLength; i > 0; i--) {
			for(int j = i -1; j > 0; j--) {
				if(deletedEdge[j - 1] == deletedEdge[i - 1]) {
					deletedEdge[j - 1] = deletedEdge[--deletedEdgeLength];
					break;
				}
			}
		}
		//Get the 10 nearest neighborhood list
		this.m_rootListFSecLength = 0;
		boolean[] rootListFSec = new boolean[this.m_n];
		for(int i = 0; i < bestLevelLength; i++) {
			for(int j = 0; j < 10; j++) {
				int dex = this.m_nearestNeighborhood.getCandidate(deletedEdge[i], j + 1) - 1;
				rootListFSec[dex] = true;
			}
		}
		for(int i = 0; i < this.m_n; i++) {
			if(rootListFSec[i]) {
				this.m_rootListFSec[this.m_rootListFSecLength++] = i + 1;
			}
		}
		//Reset the best solution in the loop
		this.__resetTour(bestSolution);
	}
	/**
	 * process of sec,in the psec of the fsec
	 * @return 
	 */
	private final long __secRootNoChangedFSec() {
		
		this.__initTubaList();
		this.m_level = this.m_n<<1;
		int countLevel = 0;
		this.m_rootNode = this.m_rootListFSec[--this.m_rootListFSecLength];
		this.m_beginStemNode = this.m_rootNode;
		this.m_endStemNode = this.m_rootNode;
		int[] bestSolution = this.m_betterTour;
		long bestTourLength = this.__getBetterTour();
		while(countLevel < this.m_level) {
			countLevel++;
			int trialBefore = this.__trialSolution(this.m_rootNode, 
					this.__getLeftNeighbor(this.m_rootNode), 
					this.__getRightNeighbor(this.m_rootNode), 
					this.m_endStemNode);
			int[] jOnCycle = this.__jOnCycleRootNoChanged();
			int[] jOnStem = this.__jOnStemNoRootChanged();
			
			if(jOnCycle == null && jOnStem == null) {
				break;
			}
			if(jOnCycle != null && (jOnStem == null || jOnCycle[0] >= jOnStem[0])) {
				this.__addEdge(jOnCycle[2], jOnCycle[3]);
				//System.out.println("j on cycle Delete edge " + jOnCycle[2] + " " + jOnCycle[3]);
				this.m_jNode = jOnCycle[2];
				int formerNode = 0;
				int laterNode = 0;
				if(jOnCycle[1] == 1) {
					formerNode = jOnCycle[3];
					laterNode = this.__getNeighbor(this.m_jNode, formerNode);
				} else if(jOnCycle[1] == 2) {
					laterNode = jOnCycle[3];
					formerNode = this.__getNeighbor(this.m_jNode, laterNode);
				}
				int subRoot = this.__getLeftNeighbor(this.m_rootNode);
				int subRootAno = this.__getRightNeighbor(this.m_rootNode);
				
				if(jOnCycle[1] == 1) {
					if(this.m_beginStemNode != this.m_rootNode) {
						//change the structure
						this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
						this.__setNeighborSame(this.m_jNode, formerNode, this.m_endStemNode);
						this.__setNeighborSame(formerNode, this.m_jNode, -1);
						this.__setNeighborSame(this.m_rootNode, subRoot, this.m_beginStemNode);
						//change the root node
						this.m_beginStemNode = subRoot;
						this.m_endStemNode = formerNode;
					} else {
						//change the structure
						this.__setNeighborSame(this.m_rootNode, subRoot, this.m_jNode);
						this.__setNeighborSame(this.m_jNode, formerNode, this.m_rootNode);
						this.__setNeighborSame(formerNode, this.m_jNode, -1);
						//change the root node
						this.m_beginStemNode = subRoot;
						this.m_endStemNode = formerNode;
					} 
				} else if(jOnCycle[1] == 2) {
					if(this.m_beginStemNode != this.m_rootNode) {
						//change the structure
						this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
						this.__setNeighborSame(this.m_jNode, laterNode, this.m_endStemNode);
						this.__setNeighborSame(laterNode, this.m_jNode, -1);
						this.__setNeighborSame(this.m_rootNode, subRootAno, this.m_beginStemNode);
						//change the root node
						this.m_beginStemNode = subRootAno;
						this.m_endStemNode = laterNode;
					} else {
						//change the structure
						this.__setNeighborSame(this.m_rootNode, subRootAno, this.m_jNode);
						this.__setNeighborSame(this.m_jNode, laterNode, this.m_rootNode);
						this.__setNeighborSame(laterNode, this.m_jNode, -1);
						//change the root node
						this.m_beginStemNode = subRootAno;
						this.m_endStemNode = laterNode;
					}
				}
				int trialAfter = this.__trialSolution(this.m_rootNode, 
						this.__getLeftNeighbor(this.m_rootNode), 
						this.__getRightNeighbor(this.m_rootNode), 
						this.m_endStemNode);
				if((jOnCycle[0] + trialAfter - trialBefore) > 0) {
					long currentTourlength = this.__getBetterTour();
					if(currentTourlength < bestTourLength) {
						bestSolution = this.m_betterTour;
					}
				}
			} else if(jOnStem != null && (jOnCycle == null || jOnStem[0] >= jOnCycle[0])) {
				this.__addEdge(jOnStem[1], jOnStem[2]);
				//System.out.println("j on stem Delete edge " + jOnStem[1] + " " + jOnStem[2]);
				this.m_jNode = jOnStem[1];
				int laterNode = jOnStem[2];
				int formerNode = this.__getNeighbor(this.m_jNode, laterNode);
				// change the relation
				this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
				this.__setNeighborDifferent(this.m_jNode, formerNode, this.m_endStemNode);
				this.__setNeighborSame(laterNode, this.m_jNode, -1);
				// change the root node, begin stem node
				this.m_endStemNode = laterNode;
				int trialAfter = this.__trialSolution(this.m_rootNode, 
						this.__getLeftNeighbor(this.m_rootNode), 
						this.__getRightNeighbor(this.m_rootNode), 
						this.m_endStemNode);
				if((jOnStem[0] + trialAfter - trialBefore) > 0) {
					long currentTourlength = this.__getBetterTour();
					if(currentTourlength < bestTourLength) {
						bestSolution = this.m_betterTour;
					}
				}
			}
		}
		//Set the best solution in the loop
		this.__resetTour(bestSolution);
		return bestTourLength;
	}
	/**
	 * process of sec, root is not changed
	 * @return 
	 */
	private final void __secRootNoChanged() {
		
		this.__initTubaList();
		//this.__initRootList();
		this.m_level = this.m_n<<1;
		int countLevel = 0;
		this.m_rootNode = this.__getRandomRoot();
		this.m_beginStemNode = this.m_rootNode;
		this.m_endStemNode = this.m_rootNode;
		while(countLevel < this.m_level) {
			countLevel++;
			int trialBefore = this.__trialSolution(this.m_rootNode, 
					this.__getLeftNeighbor(this.m_rootNode), 
					this.__getRightNeighbor(this.m_rootNode), 
					this.m_endStemNode);
			int[] jOnCycle = this.__jOnCycleRootNoChanged();
			int[] jOnStem = this.__jOnStemNoRootChanged();
			
			if(jOnCycle == null && jOnStem == null) {
				break;
			}
			if(jOnCycle != null && (jOnStem == null || jOnCycle[0] >= jOnStem[0])) {
				this.__addEdge(jOnCycle[2], jOnCycle[3]);
				//System.out.println("j on cycle Delete edge " + jOnCycle[2] + " " + jOnCycle[3]);
				this.m_jNode = jOnCycle[2];
				int formerNode = 0;
				int laterNode = 0;
				if(jOnCycle[1] == 1) {
					formerNode = jOnCycle[3];
					laterNode = this.__getNeighbor(this.m_jNode, formerNode);
				} else if(jOnCycle[1] == 2) {
					laterNode = jOnCycle[3];
					formerNode = this.__getNeighbor(this.m_jNode, laterNode);
				}
				int subRoot = this.__getLeftNeighbor(this.m_rootNode);
				int subRootAno = this.__getRightNeighbor(this.m_rootNode);
				
				if(jOnCycle[1] == 1) {
					if(this.m_beginStemNode != this.m_rootNode) {
						//change the structure
						this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
						this.__setNeighborSame(this.m_jNode, formerNode, this.m_endStemNode);
						this.__setNeighborSame(formerNode, this.m_jNode, -1);
						this.__setNeighborSame(this.m_rootNode, subRoot, this.m_beginStemNode);
						//change the root node
						this.m_beginStemNode = subRoot;
						this.m_endStemNode = formerNode;
					} else {
						//change the structure
						this.__setNeighborSame(this.m_rootNode, subRoot, this.m_jNode);
						this.__setNeighborSame(this.m_jNode, formerNode, this.m_rootNode);
						this.__setNeighborSame(formerNode, this.m_jNode, -1);
						//change the root node
						this.m_beginStemNode = subRoot;
						this.m_endStemNode = formerNode;
					} 
				} else if(jOnCycle[1] == 2) {
					if(this.m_beginStemNode != this.m_rootNode) {
						//change the structure
						this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
						this.__setNeighborSame(this.m_jNode, laterNode, this.m_endStemNode);
						this.__setNeighborSame(laterNode, this.m_jNode, -1);
						this.__setNeighborSame(this.m_rootNode, subRootAno, this.m_beginStemNode);
						//change the root node
						this.m_beginStemNode = subRootAno;
						this.m_endStemNode = laterNode;
					} else {
						//change the structure
						this.__setNeighborSame(this.m_rootNode, subRootAno, this.m_jNode);
						this.__setNeighborSame(this.m_jNode, laterNode, this.m_rootNode);
						this.__setNeighborSame(laterNode, this.m_jNode, -1);
						//change the root node
						this.m_beginStemNode = subRootAno;
						this.m_endStemNode = laterNode;
					}
				}
				int trialAfter = this.__trialSolution(this.m_rootNode, 
						this.__getLeftNeighbor(this.m_rootNode), 
						this.__getRightNeighbor(this.m_rootNode), 
						this.m_endStemNode);
				if((jOnCycle[0] + trialAfter - trialBefore) > 0) {
					this.__getBetterTour();
				}
			} else if(jOnStem != null && (jOnCycle == null || jOnStem[0] >= jOnCycle[0])) {
				this.__addEdge(jOnStem[1], jOnStem[2]);
				//System.out.println("j on stem Delete edge " + jOnStem[1] + " " + jOnStem[2]);
				this.m_jNode = jOnStem[1];
				int laterNode = jOnStem[2];
				int formerNode = this.__getNeighbor(this.m_jNode, laterNode);
				// change the relation
				this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
				this.__setNeighborDifferent(this.m_jNode, formerNode, this.m_endStemNode);
				this.__setNeighborSame(laterNode, this.m_jNode, -1);
				// change the root node, begin stem node
				this.m_endStemNode = laterNode;
				int trialAfter = this.__trialSolution(this.m_rootNode, 
						this.__getLeftNeighbor(this.m_rootNode), 
						this.__getRightNeighbor(this.m_rootNode), 
						this.m_endStemNode);
				if((jOnStem[0] + trialAfter - trialBefore) > 0) {
					this.__getBetterTour();
				}
			}
		}
	}

	/**
	 * j node is on the cycle, root node is not changed
	 * @return
	 * jOnCycle[0]: best EK
	 * jOnCycle[1]: best type, 1 former node, 2 later node
	 * jOnCycle[2]: j node also p node
	 * jOnCycle[3]: q node, former node or later node
	 */
	private final int[] __jOnCycleRootNoChanged() {
		int bestEK = Integer.MIN_VALUE;
		//0 is initialization, 1 presents former node is better, 2 later node
		int bestType = 0;
		int bestP = 0;
		int bestQ = 0;
		int subRoot = this.__getLeftNeighbor(this.m_rootNode);
		int subRootAno = this.__getRightNeighbor(this.m_rootNode);
		this.m_jNode = this.__getNeighbor(subRoot, this.m_rootNode);
		int formerNode = subRoot;
		int laterNode = this.__getNeighbor(this.m_jNode, formerNode);
		int[] distance = new int[2];
		
		if(this.m_jNode == subRootAno || subRoot == subRootAno) {
			return null;
		}
		while(this.m_jNode != subRootAno) {
			if(!this.__inTubaList(this.m_jNode, formerNode)) {
				distance[0] = this.m_f.distance(this.m_jNode, formerNode)
						- this.m_f.distance(this.m_jNode, this.m_endStemNode);
				if(distance[0] > bestEK) {
					bestEK = distance[0];
					bestType = 1;
					bestP = this.m_jNode;
					bestQ = formerNode;
				}
			}
			if(!this.__inTubaList(this.m_jNode, laterNode)) {
				distance[1] = this.m_f.distance(this.m_jNode, laterNode)
						- this.m_f.distance(this.m_jNode, this.m_endStemNode);
				if(distance[1] > bestEK) {
					bestEK = distance[1];
					bestType = 2;
					bestP = this.m_jNode;
					bestQ = laterNode;
				}
			}
			//move j node to next node
			formerNode = this.m_jNode;
			this.m_jNode = laterNode;
			laterNode = this.__getNeighbor(this.m_jNode, formerNode);
		}
		if(bestType == 0) {
			return null;
		}
		int[] jOnCycle = new int[4];
		jOnCycle[0] = bestEK;
		jOnCycle[1] = bestType;
		jOnCycle[2] = bestP;
		jOnCycle[3] = bestQ;
		return jOnCycle;
	}

	/**
	 * j is on the stem, no root node is changed
	 * @return
	 * jOnStem[0]: bestEK
	 * jOnStem[1]: best p node, is also j node
	 * jOnStem[2]: best q node, later node
	 */
	private final int[] __jOnStemNoRootChanged() {
		if(this.m_beginStemNode == this.m_endStemNode) {
			return null;
		}
		int bestEK = Integer.MIN_VALUE;
		int bestP = 0;
		int bestQ = 0;
		//initialization
		this.m_jNode = this.__getNeighbor(this.m_beginStemNode, this.m_rootNode);
		int formerNode = this.m_beginStemNode;
		int laterNode = this.__getNeighbor(this.m_jNode, formerNode);
		int stopNode = this.__getNeighbor(this.m_endStemNode, -1);
		if(this.m_jNode == stopNode || this.m_jNode == this.m_endStemNode) {
			return null;
		}
		while(this.m_jNode != stopNode) {
			if(!this.__inTubaList(this.m_jNode, laterNode)) {
				int distance = this.m_f.distance(this.m_jNode, laterNode) 
						- this.m_f.distance(this.m_jNode, this.m_endStemNode);
				if(distance > bestEK) {
					bestEK = distance;
					bestP = this.m_jNode;
					bestQ = laterNode;
				}
			}
			//move j node to next node
			formerNode = this.m_jNode;
			this.m_jNode = laterNode;
			laterNode = this.__getNeighbor(this.m_jNode, formerNode);
		}
		if(bestP == 0 || bestQ == 0) {
			return null;
		} 
		int[] jOnStem = new int[3];
		jOnStem[0] = bestEK;
		jOnStem[1] = bestP;
		jOnStem[2] = bestQ;
		return jOnStem;
	}
	/**
	 * test one edge in the tuba edge or not
	 * @param i, one node of the edge
	 * @param j, another node of the edge
	 * @return: true, in the tuba, false, not in the tuba
	 */
	private final boolean __inTubaList(int i, int j) {
		//private final boolean __inTubaEdge(int i, int j) {
		if(i > j) {// i is always smaller than j
			int temp = i;
			i = j;
			j = temp;
		}
		//System.out.println(i + " "+ j + " " + this.m_tubaEdge[i - 1][j - 1]);
		return this.m_tubaEdge[i - 1][j - 1];
	}
	/**
	 * set the edge to be true, present the edge is deleted
	 * @param i, one node of the edge
	 * @param j, another node of the edge
	 */
	private final void __addEdge(int i, int j) {
//		private final void __addDeletedEdge(int i, int j) {
		if(i > j) {// i is always smaller than j
			int temp = i;
			i = j;
			j = temp;
		}
		this.m_tubaEdge[i -1][j -1] = true;
	}
	/**
	 * set all the edges to be false
	 */
	private final void __initTubaList() {
//		private final void __initTubaEdge() {
		this.m_tubaEdge = null;
		this.m_tubaEdge = new boolean[this.m_n][this.m_n];
	}
	/**
	 * print all the deleted edges
	 */
	private final void __printTubaEdge() {
		System.out.println("******Tuba List******");
		for(int i = 0; i < this.m_n; i++) {
			System.out.print( (i + 1) + " row **");
			for(int j = 0; j < this.m_n; j++) {
				System.out.print(this.m_tubaEdge[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Greed algorithm, once change the structure, then make an improvement
	 */
	private final void __greedAlgorithm() {
		this.__initRootList();
		//this.m_rootNode = this.m_f.getRandom().nextInt(this.m_n) + 1;//Need add 1, easy to fault
		this.m_rootNode = this.__getRandomRoot();
		this.m_beginStemNode = this.m_rootNode;
		this.m_endStemNode = this.m_rootNode;
		//Greed algorithm
		while(this.m_rootListLength > 0 && (!(this.m_f.shouldTerminate()))) {
			if(this.__doRules()) {
				this.__initRootList();
			} else {
				this.__changeToTour();
				this.m_rootNode = this.__getRandomRoot();
				this.m_beginStemNode = this.m_rootNode;
				this.m_endStemNode = this.m_rootNode;
			}
		}
	}
	/**
	 * Do the four rules
	 * @return
	 * true: changed at least one time
	 * false: no change
	 */
	private final boolean __doRules() {
		
		if(!this.__tAndJOnCycle() && !this.__qAndJOnCycleDeleteRootEdge()) {
			return false;
		}
		boolean flag = true;
		while(flag) {
			for(int i = 0; i < 4; i++) {
				this.m_flags[i] = false;
			}
			this.__tAndJOnCycle();
			if(this.m_rootNode != this.m_beginStemNode) {
				this.__qAsSubRootAndJOnStem();//Need stem chain
				this.__tAndJOnStem();//Need stem chain
			}
			this.__qAndJOnCycleDeleteRootEdge();
			flag = false;
			for(int i = 0; i < 4; i++) {
				if(this.m_flags[i]) {
					flag = true;
				}
			}
		}
		this.__getBetterTour();
		return true;
	}
	/**
	 * Change the stem and cycle structure to a tour
	 */
	private final void __changeToTour() {
		if(this.m_rootNode != this.m_beginStemNode) {
			int one = 0;//One of the sub root
			int another = 0;//Another sub root
			int better = 0;//Store the better of the node between "one" and "another"
			one = this.__getLeftNeighbor(this.m_rootNode);
			another = this.__getRightNeighbor(this.m_rootNode);
			int distanceOne = this.m_f.distance(this.m_endStemNode, one)
					- this.m_f.distance(this.m_rootNode, one);
			int distanceAnother = this.m_f.distance(this.m_endStemNode, another)
					- this.m_f.distance(this.m_rootNode, another);
			if(distanceOne < distanceAnother) {
				better = one;
			} else {
				better = another;
			}
			//change the relation
			this.__setNeighborSame(better, this.m_rootNode, this.m_endStemNode);
			this.__setNeighborSame(this.m_endStemNode, -1, better);
			this.__setNeighborSame(this.m_rootNode, better, this.m_beginStemNode);
			this.m_beginStemNode = this.m_rootNode;
			this.m_endStemNode = this.m_rootNode;
		}
	}
	/**
	 * Print one cycle by j node
	 * @param oneNode: one of the cycle nodes
	 */
	private final void __printOneCycleForDebug(int oneNode) {
		int jNode = oneNode;
System.out.println("%%%%%%%%%%%%%%%%%%%%%");
		int formerNode = this.__getRandomNeighbor(oneNode);
		int i = 0;
		do {
            System.out.println("Cycle "+i+"= " + jNode);
            i++;
			int temp = jNode;
			jNode = this.__getNeighbor(jNode, formerNode);
			formerNode = temp;
		} while (jNode != oneNode);
		System.out.println(i);
System.out.println("%%%%%%%%%%%%%%%%%%%%%");
	}
	/**
	 * q is one sub root, j is on stem chain, by comparing sub root with another sub root,
	 * decide to whether we change, if not, j node moves to next node 
	 */
	private final boolean __qAsSubRootAndJOnStem() {
		// Initialization, create a stem
		//this.__tempGetStem();// Get a stem chain
		// Store the sub root that we come from
		this.m_jNode = this.m_beginStemNode;
		int formerNode = this.m_rootNode;// The node before the j node
		while (this.m_jNode != this.m_endStemNode) {
			// betterNode if is -1, we do not need to change
			int betterNode = -1;
			int subRoot = this.__getLeftNeighbor(this.m_rootNode);
			int subRootAnother = this.__getRightNeighbor(this.m_rootNode);
			int trialBeforechange = this.__trialSolution(this.m_rootNode, subRoot, subRootAnother, this.m_endStemNode);
			int oneForTrial = this.__trialSolution(this.m_jNode, subRoot, formerNode, this.m_endStemNode);
			int twoForTrial = this.__trialSolution(this.m_jNode, subRootAnother, formerNode, this.m_endStemNode);
			int one = this.m_f.distance(this.m_rootNode, subRoot) 
					- this.m_f.distance(this.m_jNode, subRoot) 
					+ oneForTrial - trialBeforechange;
			int two = this.m_f.distance(this.m_rootNode, subRootAnother) 
					- this.m_f.distance(this.m_jNode, subRootAnother) 
					+ twoForTrial - trialBeforechange;
			
			if(one > 0 && one >= two) {
				betterNode = subRoot;
			} else if(two > 0 && two >= one) {
				betterNode = subRootAnother;
			}
			// If the shortest edge is longer than the add edge, don't need to
			// change
			if (betterNode != -1) {
				//this.m_flags = true, present change occur
				this.m_flags[3] = true;
				// better node is sub root or another sub root
				// Change the relation
				int laterNode = this.__getNeighbor(this.m_jNode, formerNode);
				this.__setNeighborSame(betterNode, this.m_rootNode, this.m_jNode);
				this.__setNeighborSame(this.m_rootNode, betterNode, this.m_beginStemNode);
				this.__setNeighborDifferent(this.m_jNode, formerNode, betterNode);
				// change the root node, begin stem node
				this.m_rootNode = this.m_jNode;
				this.m_beginStemNode = laterNode;
				
				// when change is occur, update j node, sub root and another sub
				// root
				// Change the root node, begin stem node and end stem node
				this.m_jNode = this.m_beginStemNode;
				formerNode = this.m_rootNode;
				// Get the better tour
				//this.__getBetterTour();
			} else {
				// Update j node and former node
				int tempNode = this.m_jNode;
				this.m_jNode = this.__getNeighbor(this.m_jNode, formerNode);
				formerNode = tempNode;
				// System.out.println("No change, next node, j node" +
				// this.m_jNode);
			}
		}
		return this.m_flags[3];
	}
	/**
	 * The trial solution
	 * @param root
	 * @param subRoot
	 * @param subRootAnother
	 * @param endStem
	 * @return the improvement of the trial solution, from stem and cycle to tour
	 */
	private final int __trialSolution(int root, int subRoot, int subRootAnother, int endStem) {
		int distanceSub = this.m_f.distance(root, subRoot) 
				- this.m_f.distance(endStem, subRoot);
		int distanceSubAnother = this.m_f.distance(root, subRootAnother) 
				- this.m_f.distance(endStem, subRootAnother);
		if(distanceSub > distanceSubAnother) {
			return distanceSub;
		} else {
			return distanceSubAnother;
		}
	}
	/**
	 * Get a stem chain for method __tAndjOnStem()
	 */
	private final void __getStem() {
		int one = 1;
		int two = this.__getRandomNeighbor(one);
		int three = this.__getNeighbor(two, one);
		int four = this.__getNeighbor(three, two);
		int five = this.__getNeighbor(four, three);
		int six = this.__getNeighbor(five, four);
		this.m_rootNode = one;
		this.m_beginStemNode = this.__getNeighbor(one, two);
		this.m_endStemNode = six;
		
		this.__setNeighborSame(five, six, one);
		this.__setNeighborSame(six, five, -1);
		this.__setNeighborDifferent(one, two, five);
	}
	/**
	 * t is the end stem node, j is on stem, compare one edge of j with two edges of sub roots,
	 * delete one of three edges
	 */
	private final boolean __tAndJOnStem() {
		//Initialization, create a stem
		//this.__tempGetStem();//Get a stem chain
		// Store the sub root that we come from
		this.m_jNode = this.m_beginStemNode;
		int formerNode = this.m_rootNode;// The node before the j node
		int laterNode = this.__getNeighbor(this.m_jNode, formerNode);
		int subRoot = this.__getLeftNeighbor(this.m_rootNode);
		int subRootAnother = this.__getRightNeighbor(this.m_rootNode);
		
		while (this.m_jNode != this.m_endStemNode) {
			//idAndEdge[0] is id of the node, idAndEdge[1] is the shortest edge
			int betterNode = -1;
			int trialBeforechange = this.__trialSolution(this.m_rootNode, subRoot, subRootAnother, this.m_endStemNode);
			int disTrialStem = this.__trialSolution(this.m_rootNode, subRoot, subRootAnother, 
					laterNode);
			int disTrialSub = this.__trialSolution(this.m_jNode, this.m_endStemNode, 
					laterNode, subRoot);
			int disTrialSubAnother = this.__trialSolution(this.m_jNode, this.m_endStemNode, 
					laterNode, subRootAnother);
			
			int distanceStem = this.m_f.distance(this.m_jNode, this.__getNeighbor(this.m_jNode, formerNode))
					- this.m_f.distance(this.m_jNode, this.m_endStemNode) 
					+ disTrialStem - trialBeforechange;
			int distanceSub = this.m_f.distance(this.m_rootNode, subRoot)
					- this.m_f.distance(this.m_jNode, this.m_endStemNode) 
					+ disTrialSub - trialBeforechange;	
			int distanceSubAnother = this.m_f.distance(this.m_rootNode, subRootAnother)
					- this.m_f.distance(this.m_jNode, this.m_endStemNode) 
					+ disTrialSubAnother - trialBeforechange;
			if(distanceStem <= 0 && distanceSub <= 0 && distanceSubAnother <= 0) {
				betterNode = -1;
			} else if(distanceStem >= distanceSub && distanceStem >= distanceSubAnother) {
				betterNode = this.m_jNode;
			} else if(distanceSub >= distanceStem && distanceSub >= distanceSubAnother) {
				betterNode = subRoot;
			} else if(distanceSubAnother >= distanceStem && distanceSubAnother >= distanceSub) {
				betterNode = subRootAnother;
			}
			// If the shortest edge is longer than the add edge, don't need to change
			if (betterNode != -1) {
				//this.__printInformationForDebug();
				//this.m_flags = true, present change occur
				this.m_flags[2] = true;
				// better node is j node
				if (betterNode == this.m_jNode) {
					// change the relation
					this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
					this.__setNeighborDifferent(this.m_jNode, formerNode, this.m_endStemNode);
					this.__setNeighborSame(laterNode, this.m_jNode, -1);
					// change the root node, begin stem node
					this.m_endStemNode = laterNode;
				} else {
					//Change the relation
					this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
					this.__setNeighborSame(this.m_jNode, formerNode, this.m_endStemNode);
					this.__setNeighborSame(this.m_rootNode, betterNode,this.m_beginStemNode);
					this.__setNeighborSame(betterNode, this.m_rootNode, -1);
					//change the root node, begin stem node
					this.m_rootNode = this.m_jNode;
					this.m_beginStemNode = formerNode;
					this.m_endStemNode = betterNode;
				}
				// when change is occur, update j node, sub root and another sub
				// root
				// Change the root node, begin stem node and end stem node
				this.m_jNode = this.m_beginStemNode;
				formerNode = this.m_rootNode;
				laterNode = this.__getNeighbor(this.m_jNode, formerNode);
				subRoot = this.__getLeftNeighbor(this.m_rootNode);
				subRootAnother = this.__getRightNeighbor(this.m_rootNode);
				// Get the better tour
				//this.__getBetterTour();
			} else {
				// Update j node and former node
				int tempNode = this.m_jNode;
				this.m_jNode = this.__getNeighbor(this.m_jNode, formerNode);
				formerNode = tempNode;
				laterNode = this.__getNeighbor(this.m_jNode, formerNode);
			}
		}
		return this.m_flags[2];
	}
	/**
	 * q node is sub root,sub root another or begin stem node
	 * j node is on cycle, delete the edge adjacent root node
	 */
	private final boolean __qAndJOnCycleDeleteRootEdge() {
		int subRoot = this.__getLeftNeighbor(this.m_rootNode);//The node before the j node
		int subRootAnother = this.__getRightNeighbor(this.m_rootNode);//Another sub root
		this.m_jNode = this.__getNeighbor(subRoot, this.m_rootNode);//Store the sub root that we come from
		int formerNode = subRoot;//The node before the j node
		
		while(this.m_jNode != subRootAnother) {
			int betterNode = -1;
			int disOneForTrial = 0;
			int disTwoForTrial = 0;
			int disThreeForTrial = 0;
			int trialBeforechange = this.__trialSolution(this.m_rootNode, subRoot, subRootAnother, this.m_endStemNode);
			if(this.m_rootNode != this.m_beginStemNode) {
				disOneForTrial = this.__trialSolution(this.m_jNode, formerNode, this.__getNeighbor(this.m_jNode, formerNode), this.m_endStemNode);
			} else {
				disOneForTrial = Integer.MIN_VALUE/2;
			}
			disTwoForTrial = this.__trialSolution(this.m_jNode, formerNode, subRoot, this.m_endStemNode);
			disThreeForTrial = this.__trialSolution(this.m_jNode, this.__getNeighbor(this.m_jNode, formerNode), subRootAnother, this.m_endStemNode);
			int disOne = this.m_f.distance(this.m_rootNode, this.m_beginStemNode)
					- this.m_f.distance(this.m_beginStemNode, this.m_jNode) 
					+ disOneForTrial - trialBeforechange;
			int disTwo = this.m_f.distance(this.m_rootNode, subRoot)
					- this.m_f.distance(subRoot, this.m_jNode) 
					+ disTwoForTrial - trialBeforechange;
			int disThree = this.m_f.distance(this.m_rootNode,subRootAnother)
					- this.m_f.distance(subRootAnother, this.m_jNode) 
					+ disThreeForTrial - trialBeforechange;
			if((disOne <= 0) && (disTwo <= 0)	&& (disThree <= 0)) {
				betterNode = -1;
			} else if((disOne >= disTwo)&&(disOne >= disThree)) {
				betterNode = this.m_beginStemNode;
			} else if((disTwo >= disOne)&&(disTwo >= disThree)) {
				betterNode = subRoot;
			} else if((disThree >= disOne)&&(disThree >= disTwo)) {
				betterNode = subRootAnother;
			}
			//If betterNode == -1, do not need to change the structure
			if(betterNode != -1) {
				//this.m_flags = true, present change occur
				this.m_flags[1] = true;
				//better node is begin stem node
				if(betterNode == this.m_beginStemNode) {
					//change the relation
					this.__setNeighborSame(this.m_beginStemNode, this.m_rootNode, this.m_jNode);
					//change the root node, begin stem node
					this.m_rootNode = this.m_jNode;
				} else if(betterNode == subRoot) {
					//change the relation
					int tempBeginstemNode = this.__getNeighbor(this.m_jNode, formerNode);
					if(this.m_rootNode != this.m_beginStemNode) {
						this.__setNeighborSame(this.m_rootNode, subRoot, this.m_beginStemNode);
					} else {
						this.__setNeighborSame(this.m_rootNode, subRoot, -1);
					}
					this.__setNeighborSame(subRoot, this.m_rootNode, this.m_jNode);
					this.__setNeighborDifferent(this.m_jNode, formerNode, subRoot);
					//change the root node and begin stem node
					this.m_rootNode = this.m_jNode;
					this.m_beginStemNode = tempBeginstemNode;
				} else if(betterNode == subRootAnother) {
					//System.out.println("betterNode == subRootAnother");
					//change the relation
					if(this.m_rootNode != this.m_beginStemNode) {
						this.__setNeighborSame(this.m_rootNode, subRootAnother, this.m_beginStemNode);
					} else {
						this.__setNeighborSame(this.m_rootNode, subRootAnother, -1);
					}
					this.__setNeighborSame(subRootAnother, this.m_rootNode, this.m_jNode);
					this.__setNeighborSame(this.m_jNode, formerNode, subRootAnother);
					//change the root node and begin stem node
					this.m_rootNode = this.m_jNode;
					this.m_beginStemNode = formerNode;
				} else {
					System.out.println("Error in change the relation");
				}
				
				//when change is occur, update j node, sub root and another sub root
				//Change the root node, begin stem node and end stem node
				subRoot = this.__getLeftNeighbor(this.m_rootNode);//The node before the j node
				subRootAnother = this.__getRightNeighbor(this.m_rootNode);//Another sub root
				this.m_jNode = this.__getNeighbor(subRoot, this.m_rootNode);//Store the sub root that we come from
				formerNode = subRoot;//The node before the j node
				//Get the better tour
				//this.__getBetterTour();
			} else {
				//Update j node and former node
				int tempNode = this.m_jNode;
				this.m_jNode = this.__getNeighbor(this.m_jNode, formerNode);
				formerNode = tempNode;
			}
		}
		return this.m_flags[1];
	}
	/**
	 * t is the end stem node, j is on the cycle, compare the edges that are adjacent j node
	 * and root node, get the best node from five nodes( begin stem, sub root, sub root another
	 * former node, later node) 
	 */
	private final boolean __tAndJOnCycle() {
		int subRoot = this.__getLeftNeighbor(this.m_rootNode);
		int subRootAnother = this.__getRightNeighbor(this.m_rootNode);
		this.m_jNode = this.__getNeighbor(subRoot, this.m_rootNode);
		int formerNode = subRoot;
		int[] typeAndId = new int[2];
		while (this.m_jNode != subRootAnother) {
			typeAndId = this.__getBetterNodeFor__tAndJOnCycle(subRoot, formerNode);
			if (typeAndId[0] != -1) {
				//this.m_flags = true, present change occur
				this.m_flags[0] = true;
				// change the structure
				if(typeAndId[0] == 1) {//begin stem node is best node
					//change the structure
					this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
					this.__setNeighborSame(this.m_beginStemNode, this.m_rootNode, -1);
					//change the root node
					this.m_rootNode = this.m_jNode;
					int temp = this.m_beginStemNode;
					this.m_beginStemNode = this.m_endStemNode;
					this.m_endStemNode = temp;
					
					subRoot = this.__getLeftNeighbor(this.m_rootNode);
					subRootAnother = this.__getRightNeighbor(this.m_rootNode);
					this.m_jNode = this.__getNeighbor(subRoot, this.m_rootNode);
					formerNode = subRoot;
				} else if(typeAndId[0] == 2) {//sub root node is best node
					if(this.m_beginStemNode != this.m_rootNode) {
						//change the structure
						this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
						if(this.m_jNode != typeAndId[1]) {
							this.__setNeighborSame(typeAndId[1], this.m_rootNode, -1);
						}
						this.__setNeighborSame(this.m_jNode, formerNode, this.m_endStemNode);
						this.__setNeighborSame(this.m_rootNode, typeAndId[1], this.m_beginStemNode);
						//change the root node
						this.m_rootNode = this.m_jNode;
						this.m_beginStemNode = formerNode;
						if(this.m_jNode != typeAndId[1]) {
							this.m_endStemNode = typeAndId[1];
						} else {
							this.m_beginStemNode = this.m_rootNode;
							this.m_endStemNode = this.m_beginStemNode;
						}
						subRoot = this.__getLeftNeighbor(this.m_rootNode);
						subRootAnother = this.__getRightNeighbor(this.m_rootNode);
						this.m_jNode = this.__getNeighbor(subRoot, this.m_rootNode);
						formerNode = subRoot;
					} else {
						//change the structure
						this.__setNeighborSame(this.m_rootNode, typeAndId[1], this.m_jNode);
						this.__setNeighborSame(this.m_jNode, formerNode, this.m_rootNode);
						this.__setNeighborSame(typeAndId[1], this.m_rootNode, -1);
						//change the root node
						this.m_rootNode = this.m_jNode;
						this.m_beginStemNode = formerNode;
						this.m_endStemNode = typeAndId[1];
						subRoot = this.__getLeftNeighbor(this.m_rootNode);
						subRootAnother = this.__getRightNeighbor(this.m_rootNode);
						this.m_jNode = this.__getNeighbor(subRoot, this.m_rootNode);
						formerNode = subRoot;
					}
				} else if(typeAndId[0] == 3) {//sub root another node is best node
					if(this.m_beginStemNode != this.m_rootNode) {
						//change the structure
						int tempLaterNode = this.__getNeighbor(this.m_jNode, formerNode);
						this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
						if(this.m_jNode != typeAndId[1]) {
							this.__setNeighborSame(typeAndId[1], this.m_rootNode, -1);
						}
						this.__setNeighborDifferent(this.m_jNode, formerNode, this.m_endStemNode);
						this.__setNeighborSame(this.m_rootNode, typeAndId[1], this.m_beginStemNode);
						//change the root node
						if(tempLaterNode != this.m_rootNode) {
							this.m_beginStemNode = tempLaterNode;
						} else {
							this.m_beginStemNode = this.m_jNode;
						}
						this.m_rootNode = this.m_jNode;
						this.m_endStemNode = typeAndId[1];
						subRoot = this.__getLeftNeighbor(this.m_rootNode);
						subRootAnother = this.__getRightNeighbor(this.m_rootNode);
						this.m_jNode = this.__getNeighbor(subRoot, this.m_rootNode);
						formerNode = subRoot;
					} else {
						//change the structure
						int tempLaterNode = this.__getNeighbor(this.m_jNode, formerNode);
						this.__setNeighborSame(this.m_rootNode, typeAndId[1], this.m_jNode);
						this.__setNeighborDifferent(this.m_jNode, formerNode, this.m_rootNode);
						this.__setNeighborSame(typeAndId[1], this.m_rootNode, -1);
						//change the root node
						this.m_rootNode = this.m_jNode;
						this.m_beginStemNode = tempLaterNode;
						this.m_endStemNode = typeAndId[1];
						subRoot = this.__getLeftNeighbor(this.m_rootNode);
						subRootAnother = this.__getRightNeighbor(this.m_rootNode);
						this.m_jNode = this.__getNeighbor(subRoot, this.m_rootNode);
						formerNode = subRoot;
					}
				} else if(typeAndId[0] == 4) {//former node is best node
					if(this.m_beginStemNode != this.m_rootNode) {
						if(this.m_jNode != subRoot) {
							//change the structure
							this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
							this.__setNeighborSame(this.m_jNode, formerNode, this.m_endStemNode);
							this.__setNeighborSame(formerNode, this.m_jNode, -1);
							this.__setNeighborSame(this.m_rootNode, subRoot, this.m_beginStemNode);
							//change the root node
							this.m_beginStemNode = subRoot;
							this.m_endStemNode = formerNode;
							subRoot = this.__getLeftNeighbor(this.m_rootNode);
							subRootAnother = this.__getRightNeighbor(this.m_rootNode);
							this.m_jNode = this.__getNeighbor(subRoot, this.m_rootNode);
							formerNode = subRoot;
						} else {
							System.exit(1);
							//change the structure
							this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
							this.__setNeighborSame(this.m_jNode, formerNode, this.m_endStemNode);
							this.__setNeighborSame(this.m_rootNode, subRoot, this.m_beginStemNode);
							//change the root node
							this.m_beginStemNode = this.m_rootNode;
							this.m_endStemNode = formerNode;
							subRoot = this.__getLeftNeighbor(this.m_rootNode);
							subRootAnother = this.__getRightNeighbor(this.m_rootNode);
							this.m_jNode = this.__getNeighbor(subRoot, this.m_rootNode);
							formerNode = subRoot;
						}
					} else {
						//change the structure
						this.__setNeighborSame(this.m_rootNode, subRoot, this.m_jNode);
						this.__setNeighborSame(this.m_jNode, formerNode, this.m_rootNode);
						this.__setNeighborSame(formerNode, this.m_jNode, -1);
						//change the root node
						this.m_beginStemNode = subRoot;
						this.m_endStemNode = formerNode;
						subRoot = this.__getLeftNeighbor(this.m_rootNode);
						subRootAnother = this.__getRightNeighbor(this.m_rootNode);
						this.m_jNode = this.__getNeighbor(subRoot, this.m_rootNode);
						formerNode = subRoot;
					} 
				} else if(typeAndId[0] == 5) {
					int laterNode = this.__getNeighbor(this.m_jNode, formerNode);
					if(this.m_beginStemNode != this.m_rootNode) {
						if(this.m_jNode != subRootAnother) {
							//change the structure
							this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
							this.__setNeighborSame(this.m_jNode, laterNode, this.m_endStemNode);
							this.__setNeighborSame(laterNode, this.m_jNode, -1);
							this.__setNeighborSame(this.m_rootNode, subRootAnother, this.m_beginStemNode);
							//change the root node
							this.m_beginStemNode = subRootAnother;
							this.m_endStemNode = laterNode;
							subRoot = this.__getLeftNeighbor(this.m_rootNode);
							subRootAnother = this.__getRightNeighbor(this.m_rootNode);
							this.m_jNode = this.__getNeighbor(subRoot, this.m_rootNode);
							formerNode = subRoot;
						} else {
							System.exit(1);
							//change the structure
							this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
							this.__setNeighborSame(this.m_jNode, laterNode, this.m_endStemNode);
							this.__setNeighborSame(this.m_rootNode, subRootAnother, this.m_beginStemNode);
							//change the root node
							this.m_beginStemNode = this.m_rootNode;
							this.m_endStemNode = laterNode;
							subRoot = this.__getLeftNeighbor(this.m_rootNode);
							subRootAnother = this.__getRightNeighbor(this.m_rootNode);
							this.m_jNode = this.__getNeighbor(subRoot, this.m_rootNode);
							formerNode = subRoot;
						}
					} else {
						//change the structure
						this.__setNeighborSame(this.m_rootNode, subRootAnother, this.m_jNode);
						this.__setNeighborSame(this.m_jNode, laterNode, this.m_rootNode);
						this.__setNeighborSame(laterNode, this.m_jNode, -1);
						//change the root node
						this.m_beginStemNode = subRootAnother;
						this.m_endStemNode = laterNode;
						subRoot = this.__getLeftNeighbor(this.m_rootNode);
						subRootAnother = this.__getRightNeighbor(this.m_rootNode);
						this.m_jNode = this.__getNeighbor(subRoot, this.m_rootNode);
						formerNode = subRoot;
					}
				} 
				// Get the better tour
				//this.__getBetterTour();
			} else {
				// Update j node and former node
				int tempNode = this.m_jNode;
				this.m_jNode = this.__getNeighbor(this.m_jNode, formerNode);
				formerNode = tempNode;
			}
			//this.__getBetterTour();
		}
		return this.m_flags[0];
	}
	/**
	 * Get the longest edge of the five edges,<beginStem, root><subroot, root>
	 * <subrootanother, root><former, j node><later, j node>, if the longest is
	 * longer than the edge between j node and end stem, return the type and node id
	 * @param formerNode
	 * @return typeAndId[0]:
	 * -1: do not need to change
	 * 0: better node is begin stem node
	 * 1: sub root
	 * 2: sub root another
	 * 3: former node
	 * 4: later node
	 * typeAndId[1]: the id of the better node
	 */
	private final int[] __getBetterNodeFor__tAndJOnCycle(int subRoot, int formerNode) {
		int[] typeAndId = new int[2];
		typeAndId[0] = -1;
		typeAndId[1] = Integer.MIN_VALUE;
		int subRootAnother = this.__getNeighbor(this.m_rootNode, subRoot);
		int laterNode = this.__getNeighbor(this.m_jNode, formerNode);
		
		int[] distance = new int[5];
		int trialBeforechange = this.__trialSolution(this.m_rootNode, subRoot, subRootAnother, this.m_endStemNode);
		int beginStemTrial = 0;
		if(this.m_beginStemNode != this.m_rootNode) {
			beginStemTrial = this.__trialSolution(this.m_jNode, formerNode, laterNode, this.m_beginStemNode);
		} else {
			beginStemTrial = Integer.MIN_VALUE/2;
		}
		//System.out.println("distance[0] " + distance[0]);
		int subTrial = 0;
		int subAnotherTrial = 0;
		subTrial = this.__trialSolution(this.m_jNode, this.m_endStemNode, laterNode, subRoot);
		subAnotherTrial = this.__trialSolution(this.m_jNode, this.m_endStemNode, formerNode, subRootAnother);
		int formerTrial = 0;
		int laterTrial = 0;
		if(subRoot != subRootAnother) {
			formerTrial = this.__trialSolution(this.m_rootNode, this.m_beginStemNode, subRootAnother, formerNode);
			laterTrial = this.__trialSolution(this.m_rootNode, this.m_beginStemNode, subRoot, laterNode);
		} else {
			formerTrial = this.__trialSolution(this.m_rootNode, this.m_beginStemNode, subRootAnother, this.m_jNode);
			laterTrial = this.__trialSolution(this.m_rootNode, this.m_beginStemNode, subRoot, this.m_jNode);
		}
		distance[0] = this.m_f.distance(this.m_rootNode, this.m_beginStemNode)
				- this.m_f.distance(this.m_endStemNode, this.m_jNode) 
				+ beginStemTrial - trialBeforechange;
		distance[1] = this.m_f.distance(this.m_rootNode, subRoot)
				- this.m_f.distance(this.m_endStemNode, this.m_jNode) 
				+ subTrial - trialBeforechange;
		distance[2] = this.m_f.distance(this.m_rootNode, subRootAnother)
				- this.m_f.distance(this.m_endStemNode, this.m_jNode) 
				+ subAnotherTrial - trialBeforechange;
		distance[3] = this.m_f.distance(this.m_jNode, formerNode)
				- this.m_f.distance(this.m_endStemNode, this.m_jNode) 
				+ formerTrial - trialBeforechange;
		distance[4] = this.m_f.distance(this.m_jNode, laterNode)
				- this.m_f.distance(this.m_endStemNode, this.m_jNode) 
				+ laterTrial - trialBeforechange;
		for(int i = 0; i < 5; i++) {
			if((distance[i] > typeAndId[1]) && distance[i] > 0) {
				typeAndId[0] = i + 1;
				typeAndId[1] = distance[i];
			}
		}
		if(typeAndId[0] == 1) {
			typeAndId[1] = this.m_beginStemNode;
		} else if(typeAndId[0] == 2) {
			typeAndId[1] = subRoot;
		} else if(typeAndId[0] == 3) {
			typeAndId[1] = subRootAnother;
		} else if(typeAndId[0] == 4) {
			typeAndId[1] = formerNode;
		} else if(typeAndId[0] == 5) {
			typeAndId[1] = laterNode;
		} else {
			typeAndId[0] = -1;
		}
		return typeAndId;
	}

	/**
	 * Print information about the algorithm
	 */
	private final void __printInformationForDebug() {
		System.out.println("****** Stem nodes*****");
		this.__printStemForDebug();
		System.out.println("****** Root cycle*****");
		this.__printOneCycleForDebug(this.m_rootNode);
		System.out.println("Root " + this.m_rootNode);
		System.out.println("Begin stem " + this.m_beginStemNode);
		System.out.println("End stem " + this.m_endStemNode);
	}
	/**
	 * Print stem nodes
	 */
	private final void __printStemForDebug() {
		int formerNode = this.m_rootNode;
		int cursor = this.m_beginStemNode;
		while((cursor != -1) && (cursor != formerNode)) {
			System.out.println("Stem = " + cursor);
			int temp = cursor;
			cursor = this.__getNeighbor(cursor, formerNode);
			formerNode = temp;
		}
	}
	/**
	 * Get the neighbor of nodeID, when another neighbor is "oneNeighbor"
	 * @param nodeID: the ID of nodes from 1 to n
	 * @param oneNeighbor: one of the two neighbors
	 * @return another neighbor when "oneNeighbor" is given
	 */
	private final int __getNeighbor(final int nodeID, final int oneNeighbor) {
		int one = this.__getLeftNeighbor(nodeID);
		int two = this.__getRightNeighbor(nodeID);
		if(oneNeighbor == one) {
			return two;
		} else if(oneNeighbor == two) {
			return one;
		} else {
			System.out.println("Error in get neighbor method");
			System.out.println("nodeID " + nodeID);
			System.out.println("oneNeighbor " + oneNeighbor);
			System.out.println("one " + one);
			System.out.println("two " + two);
			int[] e = new int[2];
			e[3] = 0;
			System.exit(-1);
			return 0;
		}
	}
	/**
	 * Get the random neighbor of the two neighbors
	 * @param nodeID
	 * @return one of the neighbor of the node
	 */
	private final int __getRandomNeighbor(final int nodeID) {
		if(this.m_f.getRandom().nextBoolean()) {
			return this.__getLeftNeighbor(nodeID);
		} else {
			return this.__getRightNeighbor(nodeID);
		}
	}
 	/**
	 * Set the neighbor when know one, and set the one
	 * @param nodeID: The number of nodes
	 * @param oldNeighbor: The neighbor is given
	 * @param newNeighbor: The new neighbor's ID
	 */
	private final void __setNeighborSame(final int nodeID, final int oldNeighbor, final int newNeighbor) {
		if(this.__getLeftNeighbor(nodeID) == oldNeighbor) {
			this.__setLeftNeighbor(nodeID, newNeighbor);
		} else if(this.__getRightNeighbor(nodeID) == oldNeighbor) {
			this.__setRightNeighbor(nodeID, newNeighbor);
		} else {
			System.out.println("Error in setNeighborSame");
			System.out.println("nodeID" + nodeID);
			System.out.println("LeftNeighbor" + this.__getLeftNeighbor(nodeID));
			System.out.println("RightNeighbor" + this.__getRightNeighbor(nodeID));
			System.out.println("oldNeighbor" + oldNeighbor);
			System.out.println("newNeighbor" + newNeighbor);
			//this.__printInformationForDebug();
			System.exit(-1);
		}
	}
 	/**
	 * Set the neighbor when know one, and set the one
	 * @param nodeID: The number of nodes
	 * @param oldNeighbor: The neighbor is given
	 * @param newNeighbor: The new neighbor's ID
	 */
	private final void __setNeighborDifferent(final int nodeID, final int oldNeighbor, final int newNeighbor) {
		if(this.__getLeftNeighbor(nodeID) == oldNeighbor) {
			this.__setRightNeighbor(nodeID, newNeighbor);
		} else if(this.__getRightNeighbor(nodeID) == oldNeighbor) {
			this.__setLeftNeighbor(nodeID, newNeighbor);
		} else {
			System.out.println("Error in setNeighborDifferent");
			System.out.println("nodeID" + nodeID);
			System.out.println("LeftNeighbor" + this.__getLeftNeighbor(nodeID));
			System.out.println("RightNeighbor" + this.__getRightNeighbor(nodeID));
			System.out.println("oldNeighbor" + oldNeighbor);
			System.out.println("newNeighbor" + newNeighbor);
			//this.__printInformationForDebug();
			System.exit(-1);
		}
	}
	/**
	 * Get the left neighbor node by node Id
	 * @param nodeId: node Id from 1 to n
	 * @return the left neighbor's Id
	 */
	private final int __getLeftNeighbor(final int nodeId) {
		return this.m_neighbors[(nodeId - 1) << 1];
	}
	/**
	 * Get the right neighbor node by node Id
	 * @param nodeId: node Id from 1 to n
	 * @return the right neighbor's Id
	 */
	private final int __getRightNeighbor(final int nodeId) {
		return this.m_neighbors[1 + ((nodeId - 1) << 1)];
	}
	/**
	 * Set the left neighbor node by node Id
	 * @param nodeId: node Id from 1 to n
	 * @param neighbor: The node Id of the left neighbor
	 */
	private final void __setLeftNeighbor(final int nodeId, final int neighbor) {
		this.m_neighbors[(nodeId - 1) << 1] = neighbor;
	}
	/**
	 * Set the right neighbor node by node Id
	 * @param nodeId: node Id from 1 to n
	 * @param neighbor: The node Id of the right neighbor
	 */
	private final void __setRightNeighbor(final int nodeId, final int neighbor) {
		this.m_neighbors[1 + ((nodeId - 1) << 1)] = neighbor;
	}
	/**
	 * Connect end stem node with sub root or sub root another,
	 * get the better sub root by comparing, get the better tour 
	 */
	private final long __getBetterTour() {
		int one = 0;//One of the sub root
		int another = 0;//Another sub root
		int better = 0;//Store the better of the node between "one" and "another"
		one = this.__getLeftNeighbor(this.m_rootNode);
		another = this.__getRightNeighbor(this.m_rootNode);
		int distanceOne = this.m_f.distance(this.m_endStemNode, one)
				- this.m_f.distance(this.m_rootNode, one);
		int distanceAnother = this.m_f.distance(this.m_endStemNode, another)
				- this.m_f.distance(this.m_rootNode, another);
		if(distanceOne < distanceAnother) {
			better = one;
		} else {
			better = another;
		}
//System.out.println("root  " + this.m_rootNode);
//System.out.println("begin  " + this.m_beginStemNode);
//System.out.println("end  " + this.m_endStemNode );
//System.out.println("one  " + one );
//System.out.println("another " + another);
//System.out.println("better " + better);
		int cursor = better;
		int former = this.m_rootNode;
		int count =  0;
		int tempNode = 0;
		//Store the cycle nodes from the better sub node to another sub root 
		while(cursor != this.m_rootNode) {
//System.out.println("Cycle " + cursor + "  count " + count);
			this.m_betterTour[count++] = cursor;
			tempNode = cursor;
			cursor = this.__getNeighbor(cursor, former);
			former = tempNode;
		}
		//Store the root node
//System.out.println("Root " + m_rootNode + "  count " + count);
		this.m_betterTour[count++] = this.m_rootNode;
		//Store the stem from begin stem to end stem node
		cursor = this.m_beginStemNode;
		former = this.m_rootNode;
		while(cursor != -1) {
			if(this.m_beginStemNode != this.m_rootNode) {
//System.out.println("Stem " + cursor + "  count " + count);
				this.m_betterTour[count++] = cursor;
			} else {
				cursor = -1;
			}
			//If begin stem node is the same with end stem node
			if(this.m_beginStemNode != this.m_endStemNode) {
				tempNode = cursor;
				cursor = this.__getNeighbor(cursor, former);
				former = tempNode;
			} else {
				cursor = -1;
			}
		}
		long tourLength = this.m_f.evaluate(this.m_betterTour);
		if (tourLength < this.m_dst.tourLength) {
			this.m_dst.tourLength = tourLength;
			System.arraycopy(this.m_betterTour, 0, this.m_dst.solution, 0, this.m_n);
		}
		//System.out.println("Tour Length" + tourLength);
		return tourLength;
	}
	/**
	 * get the better solution for the current solution
	 */
	private final int[] __getBetterSolution() {
		int one = 0;//One of the sub root
		int another = 0;//Another sub root
		int better = 0;//Store the better of the node between "one" and "another"
		one = this.__getLeftNeighbor(this.m_rootNode);
		another = this.__getRightNeighbor(this.m_rootNode);
		int distanceOne = this.m_f.distance(this.m_endStemNode, one)
				- this.m_f.distance(this.m_rootNode, one);
		int distanceAnother = this.m_f.distance(this.m_endStemNode, another)
				- this.m_f.distance(this.m_rootNode, another);
		if(distanceOne < distanceAnother) {
			better = one;
		} else {
			better = another;
		}
		int cursor = better;
		int former = this.m_rootNode;
		int count =  0;
		int tempNode = 0;
		//Store the cycle nodes from the better sub node to another sub root 
		while(cursor != this.m_rootNode) {
			this.m_betterTour[count++] = cursor;
			tempNode = cursor;
			cursor = this.__getNeighbor(cursor, former);
			former = tempNode;
		}
		//Store the root node
		this.m_betterTour[count++] = this.m_rootNode;
		//Store the stem from begin stem to end stem node
		cursor = this.m_beginStemNode;
		former = this.m_rootNode;
		while(cursor != -1) {
			if(this.m_beginStemNode != this.m_rootNode) {
				this.m_betterTour[count++] = cursor;
			} else {
				cursor = -1;
			}
			//If begin stem node is the same with end stem node
			if(this.m_beginStemNode != this.m_endStemNode) {
				tempNode = cursor;
				cursor = this.__getNeighbor(cursor, former);
				former = tempNode;
			} else {
				cursor = -1;
			}
		}
		return this.m_betterTour;
	}
	/**
	 * Bootstrap function
	 * 
	 * @param args
	 */
	public static void main(final String args[]) {
		TSPAlgorithmRunner.benchmark(Instance.SYMMETRIC_INSTANCES,
				FundamentalStemAndCycle.class, args);
	}
}
