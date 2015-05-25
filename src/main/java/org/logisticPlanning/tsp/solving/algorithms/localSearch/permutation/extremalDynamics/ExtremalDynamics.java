package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.extremalDynamics;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchAlgorithm;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;

/**
 * The Extremal Dynamics algorithm.
 */
public class ExtremalDynamics extends TSPLocalSearchAlgorithm<int[]> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the parameter of the power law distribution*/
  public static final String PARAM_ALPHA = "alpha";//$NON-NLS-1$
  /** the parameter of the power law distribution */
  public static final String PARAM_Beta = "Beta"; //$NON-NLS-1$
  
  /** the parameter of the alpha */
  public static final String PARAM_A = "A";
  /** the parameter of the alpha */
  public static final String PARAM_B = "B";

  /**
   * An example for a possible parameter
   */

  /** the parameter of the power law distribution */
  private double m_alpha;
  /** the parameter of the power law distribution */
  private double m_beta;
  /** the parameter of the alpha */
  private int m_A;
  /** the parameter of the alpha */
  private int m_B;
  
  /** the instance variable of Random */
  private Random m_r;
  /** the index of nearstCity */
  private int[] m_nearestCity;
  /** the index of next city connected to current city */
  private int[] m_nextCity;
  
  /** the probability of power law distribution  */
  private double[] m_Nprobability;
  /** the probability of power law distribution  */
  private double[] m_N1probability;
  /** the sum of the probability */
  double sum1;
  /** the sum of the probability */
  double sum2;
  
  /** the potential Energy of each city */
  private int[] m_cityPotentialEnergy;
  /** the copy of potential Energy of each city */
  private int[] m_cityPotentialEnergy_copy;
  
  /** the index of each city in srcdst */
  private int[] m_cityIndex;

  /** the changed Energy of the city */
  private int[] m_cityChangedEnergy;
  /** the copy of changed Energy of the city */
  private int[] m_cityChangedEnergy_copy;
  
  /** instantiate */
  public ExtremalDynamics() {
    super("Extremal Dynamics");//$NON-NLS-1$ 
    this.m_alpha = 1;
    this.m_beta = 1;// some initial value
  }

 
  /**
   * 
   * @param F the probability density function
   * @param sum the sum of all the probability
   * @return a random value according to the power law
   *         distribution
   */
  private final int powerLawDistribution(double[] F, double sum) {
    double r;
    int k;

    r = this.m_r.nextDouble();
    r = r * sum;
    k = Arrays.binarySearch(F, r);
    if (k < 0) {
      k = -k;
    } else {
      k = k + 1;
    }
    return k;
  }

  /**
   * @param nums
   *          the array
   * @param k
   *          the kth value
   * @param start
   *          the start index you want in the array
   * @param end
   *          the end index you want in the array
   * @param tag
   *          if tag is true, return the kth min value, is tag is false,
   *          return the kth max value.
   * @return the kth max/min value in the array start from @start end to @end
   */
  private static final int kthValue(final int[] nums, final int k, final int start,
      final int end, final boolean tag) {


    final int sentry = nums[end];
    int i = start - 1;
    int j;
    int temp;
    for (j = start; j < end; j++) {
      if (tag) {
        if (nums[j] <= sentry) {
          i++;
          temp = nums[j];
          nums[j] = nums[i];
          nums[i] = temp;
        }
      } else {
        if (nums[j] >= sentry) {
          i++;
          temp = nums[j];
          nums[j] = nums[i];
          nums[i] = temp;
        }
      }
    }
    temp = nums[j];
    nums[j] = nums[i + 1];
    nums[i + 1] = temp;

    if (((i + 2) - start) == k) {
      return nums[i + 1];
    } else
      if (((i + 2) - start) > k) {
        return kthValue(nums, k, start, i, tag);
      } else {
        return kthValue(nums, k - ((i + 2) - start), i + 2, end, tag);
      }
  }

  /**
   * @param f
   *          ObjectiveFunction that store the city information
   * @param c
   *          city index
   * @return return the index of the nearest city of city {@code c}
   */
  private static final int nearestCityIndex(final ObjectiveFunction f, final int c) {
    final int n = f.n();
    int dstCity = 0;
    int i;

    int mindistance = Integer.MAX_VALUE;
    for (i = 1; i <= n; i++) {
      if (i != c) {
        if (f.distance(c, i) < mindistance) {
          dstCity = i;
          mindistance = f.distance(c, i);
        }
      }
    }
    return dstCity;
  }

  /**
   * return potential Energy of each city, the index is 1-base
   * @param srcdst
   * 			the object that store the current solution
   * @param f	
   * 			the ObjectiveFunction that store the city information
   * @param energy
   * 			the array store each city potential energy
   * @param start
   * 			the start place in the path we need to change the potential energy
   * @param end
   * 			the end place in the path that we need to change the potential energy
   */
  private final void potentialEnergy(final Individual<int[]> srcdst,
      final ObjectiveFunction f, int[] energy, int start, int end) {
    final int n = f.n();
    int i;
    int curCity;
    int nextCity;
    int nearestCity;

    if (start == -1){
    	curCity = srcdst.solution[f.n()-1];
    	nextCity = srcdst.solution[0];
    	nearestCity = this.m_nearestCity[curCity];
    	energy[curCity] = f.distance(curCity, nextCity)
    	          - f.distance(curCity, nearestCity);
    	start++;
    }
    for (i = start; i <= end; i++) {
      curCity = srcdst.solution[i];
      nextCity = srcdst.solution[(i + 1) % n];
      nearestCity = this.m_nearestCity[curCity];
      energy[curCity] = f.distance(curCity, nextCity)
          - f.distance(curCity, nearestCity);
    }
  }

  /**
   * 
   * @param srcdst
   * 			current solution object
   * @param f	
   * 			ObjectiveFunction store the city information
   * @param connectedCity
   * 			the array stores the index of connected city
   * @param start
   * 			the start place in the path we need to change connected city
   * @param end
   * 			the end place in the path we need to change connected city
   */
  private final void nextCity(final Individual<int[]> srcdst,
      final ObjectiveFunction f, int[] connectedCity, int start, int end) {

    int i;
    if (start == -1){
    	connectedCity[srcdst.solution[f.n()-1]] = srcdst.solution[0];
    	start++;
    }
    for (i = start; i <= end; i++) {
      connectedCity[srcdst.solution[i]] = srcdst.solution[(i + 1) % f.n()];
    }
  }

  /**
   * @param srcdst
   *          current solution object
   * @param f
   *          ObjectiveFunction of city information
   * @param firstCity
   *          the chosen city to use 2-opt method
   * @param deltaEnergy
   * 			the array that store the changed energy according to the chosen city
   */
 private final void changedEnergy(final Individual<int[]> srcdst,
      final ObjectiveFunction f, final int firstCity, int[] deltaEnergy) {
    int i;

    /*int t_disab,t_discd,t_disac,t_disbd;
    int t_length;*/
    
    for (i = 1; i <= f.n(); i++) {
      if (i != firstCity) {
        if ((i != this.m_nextCity[firstCity])
            && (this.m_nextCity[i] != firstCity)
            && (this.m_nextCity[i] != this.m_nextCity[firstCity])) {
        	
        	/*t_disab = f.distance(firstCity, this.m_nextCity[firstCity]);
        	t_discd = f.distance(i, this.m_nextCity[i]);
        	t_disac = f.distance(firstCity,i);
        	t_disbd = f.distance(this.m_nextCity[firstCity],this.m_nextCity[i]);
        	
        	t_length = t_disac+t_disbd-t_disab-t_discd;*/
        	
          deltaEnergy[i] = (f.distance(firstCity, i) + f.distance(
              this.m_nextCity[firstCity], this.m_nextCity[i]))
              - f.distance(firstCity, this.m_nextCity[firstCity])
              - f.distance(i, this.m_nextCity[i]);
        } else {
          deltaEnergy[i] = 0;
        }
      } else {
        deltaEnergy[i] = Integer.MIN_VALUE;
      }
    }
  }

  /**
   * @param srcdst
   *          current solution object
   * @param f
   *          ObjectiveFunction of city information
   * @param index
   * 			the array store the index of the city in srcdst
   * @param start
   * 			the start place in the path we need to changed the city index
   * @param end
   * 			the end place in the path we need to changed the city index
   */
 private final void cityInDstIndex(final Individual<int[]> srcdst,
      final ObjectiveFunction f, int[] index, int start, int end) {
    int i;
    
    if(start == -1){
    	index[srcdst.solution[f.n()-1]] = f.n()-1;
    	start++;
    }
    for (i = start; i <= end; i++) {
      index[srcdst.solution[i]] = i;
    }
  }

  /** {@inheritDoc} */
  @Override
  public void localSearch(final Individual<int[]> srcdst,
      final ObjectiveFunction f) {

    // 1. srcdst contains an existing solution which is the input to your
    // algorithm. It could be randomly generated or come from a heuristic.
    // srcdst.solution = a permutation in form of an int[] of n cities to
    // visit
    // srcdst.tourLength = the tour length of the tour
    // srcdst.tourLength = f.evaluate(srcdst.solution);
    // 2. Your algorithm modifies srcdst.solution and srcdst.tourLength
    // during the local search

    // 3. the ObjectiveFunction f can provide you with all information you
    // need during the search:
    // f.distance(i, j) returns the distance between city i and city j
    // f.evaluate(nodes) computes the length of a tour
    // f.n() gives you the number of cities

    // 4. in every loop, your algorithm must check f.shouldTerminate(). If
    // it becomes true, you need to return / exit this method


    int firstChosenCity = 0, secondChosenCity = 0;
    int cityB;
    int index1 = 1, index2 = f.n()-1;
    int index1_copy = index1, index2_copy = index2;
    int temp;
    int kthHigh, uthLow;
    int kthHighValue, uthLowValue;
    int i;
    
  //  long t_length;
 

    final int[] bestSolution = new int[f.n()];
    long bestLength;

    // the 2-opt method need at least 4 cities.
    if (f.n() < 4) {
      return;
    }

    System.arraycopy(srcdst.solution, 0, bestSolution, 0, f.n());
    srcdst.tourLength = f.evaluate(srcdst.solution);
    bestLength = srcdst.tourLength;
    
    for (;;) {
  
      this.nextCity(srcdst, f, this.m_nextCity, (index1_copy-1), index2_copy);
      	//this.nextCity(srcdst, f, this.m_nextCity, 0, f.n()-1);
      this.cityInDstIndex(srcdst, f, this.m_cityIndex, (index1_copy-1), index2_copy); 
      	//this.cityInDstIndex(srcdst, f, this.m_cityIndex, 0, f.n()-1);
      this.potentialEnergy(srcdst, f, this.m_cityPotentialEnergy, (index1_copy-1), index2_copy);
      	//this.potentialEnergy(srcdst, f, this.m_cityPotentialEnergy, 0, f.n()-1);
    
    	
    	
       
        

      // choose the first city
      kthHigh = this.powerLawDistribution(this.m_Nprobability, this.sum1);
      System.arraycopy(this.m_cityPotentialEnergy,0,this.m_cityPotentialEnergy_copy,0,f.n()+1);
      kthHighValue = kthValue(this.m_cityPotentialEnergy_copy, kthHigh, 1, f.n(),
          false);
      for (i = 1; i <= f.n(); i++) {
        if (this.m_cityPotentialEnergy[i] == kthHighValue) {
          firstChosenCity = i;
          break;
        }
      }

      // choose the 2-opt method exchange city
      this.changedEnergy(srcdst, f, firstChosenCity, this.m_cityChangedEnergy);
      uthLow = this.powerLawDistribution(this.m_N1probability, this.sum2);
      System.arraycopy(this.m_cityChangedEnergy, 0, this.m_cityChangedEnergy_copy, 0, f.n()+1);
      uthLowValue = kthValue(this.m_cityChangedEnergy_copy, uthLow + 1, 1, f.n(),
          true);
      for (i = 1; i <= f.n(); i++) {
        if (this.m_cityChangedEnergy[i] == uthLowValue) {
          secondChosenCity = i;
          break;
        }
      }

      /*
       * srcdst.solution[cityIndex[firstChosenCity]] = secondChosenCity;
       * srcdst.solution[cityIndex[secondChosenCity]] = firstChosenCity;
       * srcdst.tourLength = f.evaluate(srcdst.solution);
       */

      if ((secondChosenCity != this.m_nextCity[firstChosenCity])
          && (this.m_nextCity[secondChosenCity] != firstChosenCity)
          && (this.m_nextCity[secondChosenCity] != this.m_nextCity[firstChosenCity])) {
        cityB = this.m_nextCity[firstChosenCity];
        // cityC = cityNext[secondChosenCity];
        index1 = this.m_cityIndex[cityB];
        index2 = this.m_cityIndex[secondChosenCity];
        
        if(index1 > index2){
        	cityB = this.m_nextCity[secondChosenCity];
        	index1 = this.m_cityIndex[cityB];
        	index2 = this.m_cityIndex[firstChosenCity];
        }
        
        index1_copy = index1;
        index2_copy = index2;
        // 2-opt method to change the connected city
        	while (index1 < index2) {
        		temp = srcdst.solution[index1];
        		srcdst.solution[index1] = srcdst.solution[index2];
        		srcdst.solution[index2] = temp;
        		index1++;
        		index2--;
        	}
        
        	
        srcdst.tourLength = srcdst.tourLength + this.m_cityChangedEnergy[secondChosenCity];
        	//t_length = srcdst.tourLength + this.m_cityChangedEnergy[secondChosenCity];
        	//srcdst.tourLength = f.evaluate(srcdst.solution);
        	f.registerFE(srcdst.solution, srcdst.tourLength);
      }

      if (bestLength > srcdst.tourLength) {
    	System.arraycopy(srcdst.solution, 0, bestSolution, 0, f.n());
        bestLength = srcdst.tourLength;
        
      }

      if (f.shouldTerminate()) {
        if (bestLength != srcdst.tourLength) {

          System.arraycopy(bestSolution, 0, srcdst.solution, 0, f.n());
          srcdst.tourLength = bestLength;
          f.registerFE(srcdst.solution, srcdst.tourLength);
          return;
        }
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final ExtremalDynamics clone() {
    ExtremalDynamics m;

    m = ((ExtremalDynamics) (super.clone()));
    // TODO: reset all member variables

    return m;
  }

  /** {@inheritDoc} */
  @Override
  public final void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    // TODO: print the values of all algorithm parameters
    Configurable.printKey(ExtremalDynamics.PARAM_ALPHA, ps);
    ps.println(this.m_alpha);
    Configurable.printKey(ExtremalDynamics.PARAM_Beta, ps);
    ps.println(this.m_beta);
    Configurable.printKey(ExtremalDynamics.PARAM_A, ps);
    ps.println(this.m_A);
    Configurable.printKey(ExtremalDynamics.PARAM_B, ps);
    ps.println(this.m_B);
  }

  /** {@inheritDoc} */
  @Override
  public final void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    Configurable.printKey(ExtremalDynamics.PARAM_ALPHA, ps);
    ps.println(//
    "the alpha of the power distribution"); //$NON-NLS-1$
    Configurable.printKey(ExtremalDynamics.PARAM_Beta, ps);
    ps.println(//
    "the beta of the power distribution"); //$NON-NLS-1$
    Configurable.printKey(ExtremalDynamics.PARAM_A, ps);
    ps.println(//
    "the A of the alpha");
    Configurable.printKey(ExtremalDynamics.PARAM_B, ps);
    ps.println(//
    "the B of the alpha");
  }

  /** {@inheritDoc} */
  @Override
  public final void configure(final Configuration config) {
    super.configure(config);

    // TODO: load the values of the parameters
    this.m_alpha = config.getDouble(ExtremalDynamics.PARAM_ALPHA, 0, Integer.MAX_VALUE,
        this.m_alpha);
    this.m_beta = config.getDouble(ExtremalDynamics.PARAM_Beta, 0, Integer.MAX_VALUE,
        this.m_beta);
    this.m_A = config.getInt(ExtremalDynamics.PARAM_A, 0, 10, this.m_A);
    this.m_B = config.getInt(ExtremalDynamics.PARAM_A, 0, 10, this.m_B);
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);

    this.m_alpha = this.m_A + this.m_B/Math.abs(Math.log(f.n()));
    
    this.m_r = f.getRandom();
    this.m_nearestCity = new int[f.n() + 1];
    for (int i = 1; i <= f.n(); i++) {
      this.m_nearestCity[i] = nearestCityIndex(f, i);
    }
    
    this.m_Nprobability = new double[f.n()];
    for (int i = 0; i < f.n(); i++) {
        // sum +=
        // (-alpha+1)/(Math.pow((double)n,(-alpha+1))-1)*Math.pow((double)i,-alpha);
        this.sum1 += Math.pow((i + 1), -m_alpha);
        m_Nprobability[i] = this.sum1;
    }
    
    this.m_N1probability = new double[f.n()-1];
    for (int i = 0; i < f.n()-1; i++) {
        // sum +=
        // (-alpha+1)/(Math.pow((double)n,(-alpha+1))-1)*Math.pow((double)i,-alpha);
        this.sum2 += Math.pow((i + 1), -m_beta);
        m_N1probability[i] = this.sum2;
    }
    
    this.m_cityPotentialEnergy = new int[f.n()+1];
    this.m_cityPotentialEnergy_copy = new int[f.n()+1];
    
    this.m_nextCity = new int[f.n() + 1];
    this.m_cityChangedEnergy = new int[f.n() + 1];
    this.m_cityChangedEnergy_copy = new int[f.n()+1];
    
    this.m_cityIndex = new int[f.n() + 1];
    
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    try {
      // TODO: dispose / null all member variables
      this.m_r = null;
      this.m_nearestCity = null;
      this.m_N1probability = null;
      this.m_Nprobability = null;
      this.m_cityPotentialEnergy = null;
      this.m_cityPotentialEnergy_copy = null;
      this.m_nextCity = null;
      this.m_cityChangedEnergy = null;
      this.m_cityChangedEnergy_copy = null;
      this.m_cityIndex = null;
    } finally {
      super.endRun(f);
    }
  }

  /**
   * Perform the Extremal dynamics
   * 
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(//
        Instance.SYMMETRIC_INSTANCES, ExtremalDynamics.class,//
        args);
  }
}
