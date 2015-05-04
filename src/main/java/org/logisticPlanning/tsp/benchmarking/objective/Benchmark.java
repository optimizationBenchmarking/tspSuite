package org.logisticPlanning.tsp.benchmarking.objective;

import java.io.File;
import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer;
import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.io.FileUtils;

/**
 * <p>
 * This class is the base class for a benchmarking setup. Instances of this
 * class can be used in a multi-threading environment, whereas
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction}
 * cannot. However, you can create an arbitrary number of
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * objective function instances} for a benchmark and use them in parallel.
 * </p>
 * <p>
 * The difference between the class
 * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance
 * Instance} and this class is that
 * {@link org.logisticPlanning.tsp.benchmarking.objective.Benchmark
 * Benchmark} additionally stores the configuration of the execution
 * environment for a TSP algorithm, such as the {@link #m_maxDEs maximum
 * DEs}, {@link #m_maxFEs maximum FEs}, and {@link #m_maxTime maximum
 * runtime}
 */
public final class Benchmark extends Configurable {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the first multiplier ({@value} ) that is multiplied to the tour length
   * of the optimal tour and added to that tour length in order to obtain a
   * log point threshold
   */
  public static final double MULTIPLIER_1 = 1e-3d;

  /**
   * the second multiplier ({@value} ) that is multiplied to the tour
   * length of the optimal tour and added to that tour length in order to
   * obtain a log point threshold
   */
  public static final double MULTIPLIER_2 = 1e-6d;

  /**
   * This is the inversely ordered list of the log points:
   * <ol>
   * <li>powers of 2</li>
   * <li>powers of 3</li>
   * <li>powers of 5</li>
   * <li>powers of 7</li>
   * <li>powers of 10</li>
   * <li>powers of 11</li>
   * <li>the multiples of 5 from 5 to 200</li>
   * <li>for each power of 100, a division into 10 uni-spaced intervals</li>
   * <li>border conditions {@link java.lang.Long#MIN_VALUE}, {@code 0},
   * {@link java.lang.Long#MAX_VALUE} that are unlikely to occur.</li>
   * </ol>
   * See the internal (unused) class
   * {@link org.logisticPlanning.tsp.benchmarking.objective._MakeLogPoints}
   * for a documentation on how this list is generated.
   */
  private static final long[] LOG_POINTS = new long[] { 1l, 2l, 3l, 4l,
    5l, 6l, 7l, 8l, 9l, 10l, 11l, 15l, 16l, 20l, 25l, 27l, 30l, 32l,
    35l, 40l, 45l, 49l, 50l, 55l, 60l, 64l, 65l, 70l, 75l, 80l, 81l,
    85l, 90l, 95l, 100l, 105l, 110l, 115l, 120l, 121l, 125l, 128l, 130l,
    135l, 140l, 145l, 150l, 155l, 160l, 165l, 170l, 175l, 180l, 185l,
    190l, 195l, 200l, 243l, 250l, 256l, 300l, 343l, 400l, 500l, 512l,
    600l, 625l, 700l, 729l, 750l, 800l, 900l, 1000l, 1024l, 1250l,
    1331l, 1500l, 1750l, 2000l, 2048l, 2187l, 2250l, 2401l, 2500l,
    2750l, 3000l, 3125l, 3250l, 3500l, 3750l, 4000l, 4096l, 4250l,
    4500l, 4750l, 5000l, 6000l, 6561l, 7000l, 7500l, 8000l, 8192l,
    9000l, 10000l, 12500l, 14641l, 15000l, 15625l, 16384l, 16807l,
    17500l, 19683l, 20000l, 22500l, 25000l, 27500l, 30000l, 32500l,
    32768l, 35000l, 37500l, 40000l, 42500l, 45000l, 47500l, 50000l,
    59049l, 60000l, 65536l, 70000l, 75000l, 78125l, 80000l, 90000l,
    100000l, 117649l, 125000l, 131072l, 150000l, 161051l, 175000l,
    177147l, 200000l, 225000l, 250000l, 262144l, 275000l, 300000l,
    325000l, 350000l, 375000l, 390625l, 400000l, 425000l, 450000l,
    475000l, 500000l, 524288l, 531441l, 600000l, 700000l, 800000l,
    823543l, 900000l, 1000000l, 1048576l, 1594323l, 1771561l, 1953125l,
    2000000l, 2097152l, 3000000l, 4000000l, 4194304l, 4782969l,
    5000000l, 5764801l, 6000000l, 7000000l, 8000000l, 8388608l,
    9000000l, 9765625l, 10000000l, 14348907l, 16777216l, 19487171l,
    20000000l, 30000000l, 33554432l, 40000000l, 40353607l, 43046721l,
    48828125l, 50000000l, 60000000l, 67108864l, 70000000l, 80000000l,
    90000000l, 100000000l, 129140163l, 134217728l, 200000000l,
    214358881l, 244140625l, 268435456l, 282475249l, 300000000l,
    387420489l, 400000000l, 500000000l, 536870912l, 600000000l,
    700000000l, 800000000l, 900000000l, 1000000000l, 1073741824l,
    1162261467l, 1220703125l, 1977326743l, 2000000000l, 2147483648l,
    2357947691l, 3000000000l, 3486784401l, 4000000000l, 4294967296l,
    5000000000l, 6000000000l, 6103515625l, 7000000000l, 8000000000l,
    8589934592l, 9000000000l, 10000000000l, 10460353203l, 13841287201l,
    17179869184l, 20000000000l, 25937424601l, 30000000000l,
    30517578125l, 31381059609l, 34359738368l, 40000000000l,
    50000000000l, 60000000000l, 68719476736l, 70000000000l,
    80000000000l, 90000000000l, 94143178827l, 96889010407l,
    100000000000l, 137438953472l, 152587890625l, 200000000000l,
    274877906944l, 282429536481l, 285311670611l, 300000000000l,
    400000000000l, 500000000000l, 549755813888l, 600000000000l,
    678223072849l, 700000000000l, 762939453125l, 800000000000l,
    847288609443l, 900000000000l, 1000000000000l, 1099511627776l,
    2000000000000l, 2199023255552l, 2541865828329l, 3000000000000l,
    3138428376721l, 3814697265625l, 4000000000000l, 4398046511104l,
    4747561509943l, 5000000000000l, 6000000000000l, 7000000000000l,
    7625597484987l, 8000000000000l, 8796093022208l, 9000000000000l,
    10000000000000l, 17592186044416l, 19073486328125l, 20000000000000l,
    22876792454961l, 30000000000000l, 33232930569601l, 34522712143931l,
    35184372088832l, 40000000000000l, 50000000000000l, 60000000000000l,
    68630377364883l, 70000000000000l, 70368744177664l, 80000000000000l,
    90000000000000l, 95367431640625l, 100000000000000l,
    140737488355328l, 200000000000000l, 205891132094649l,
    232630513987207l, 281474976710656l, 300000000000000l,
    379749833583241l, 400000000000000l, 476837158203125l,
    500000000000000l, 562949953421312l, 600000000000000l,
    617673396283947l, 700000000000000l, 800000000000000l,
    900000000000000l, 1000000000000000l, 1125899906842624l,
    1628413597910449l, 1853020188851841l, 2000000000000000l,
    2251799813685248l, 2384185791015625l, 3000000000000000l,
    4000000000000000l, 4177248169415651l, 4503599627370496l,
    5000000000000000l, 5559060566555523l, 6000000000000000l,
    7000000000000000l, 8000000000000000l, 9000000000000000l,
    9007199254740992l, 10000000000000000l, 11398895185373143l,
    11920928955078125l, 16677181699666569l, 18014398509481984l,
    20000000000000000l, 30000000000000000l, 36028797018963968l,
    40000000000000000l, 45949729863572161l, 50000000000000000l,
    50031545098999707l, 59604644775390625l, 60000000000000000l,
    70000000000000000l, 72057594037927936l, 79792266297612001l,
    80000000000000000l, 90000000000000000l, 100000000000000000l,
    144115188075855872l, 150094635296999121l, 200000000000000000l,
    288230376151711744l, 298023223876953125l, 300000000000000000l,
    359414837200037393l, 400000000000000000l, 450283905890997363l,
    500000000000000000l, 505447028499293771l, 558545864083284007l,
    576460752303423488l, 600000000000000000l, 700000000000000000l,
    800000000000000000l, 900000000000000000l, 1000000000000000000l,
    1152921504606846976l, 1350851717672992089l, 1490116119384765625l,
    1797074186000186965l, 2305843009213693952l, 3105570700629903195l,
    3273344365508751233l, 3909821048582988049l, 4052555153018976267l,
    4611686018427387904l, 5559917313492231481l, 5818858227285891443l,
    7113790643470898241l, 7450580596923828125l, 8033366502585570893l,
    8667208279016151025l, 8922003266371364727l, 8985370930000934825l, };

  /** the name for folders containing results for symmetric cases */
  public static final String SYMMETRIC = "symmetric"; //$NON-NLS-1$

  /** the name for folders containing results for asymmetric cases */
  public static final String ASYMMETRIC = "asymmetric"; //$NON-NLS-1$

  /**
   * The maximum number of nodes in a problem instance for which a
   * coordinate-list based distance representation will automatically be
   * converted to a distance matrix: {@value}
   */
  public static final int DEFAULT_MATRIX_LIMIT_DIM = 4096;

  /** the default number of runs: {@value} */
  public static final int DEFAULT_MAX_RUNS = 30;

  /** the default maximum time per run: 1h */
  public static final long DEFAULT_MAX_TIME_PER_RUN = (3600000l);

  /** the destination directors param: {@value} */
  public static final String PARAM_DEST_DIR = "outputDir"; //$NON-NLS-1$

  /**
   * the maximum DEs parameter ({@value} , defaults to {@code n}
   * <sup>4</sup>*100)
   */
  public static final String PARAM_MAX_DES = "maxDEs"; //$NON-NLS-1$

  /**
   * the maximum FEs parameter ({@value} , defaults to {@code n}
   * <sup>3</sup>*100)
   */
  public static final String PARAM_MAX_FES = "maxFEs"; //$NON-NLS-1$

  /** the maximum runs ({@value} , default to {@link #DEFAULT_MAX_RUNS}) */
  public static final String PARAM_MAX_RUNS = "maxRuns"; //$NON-NLS-1$

  /** the distance computer type */
  private static final String DISTANCE_COMPUTER_TYPE = "distanceComputerType"; //$NON-NLS-1$

  /**
   * the maximum runtime parameter ({@value} , defaults to
   * {@link #DEFAULT_MAX_TIME_PER_RUN})
   */
  public static final String PARAM_MAX_TIME = "maxTime"; //$NON-NLS-1$

  /**
   * the limit dimension ({@value} , defaults to
   * {@link #DEFAULT_MATRIX_LIMIT_DIM})
   */
  public static final String PARAM_LIMIT_DIM = "maxAutoDistanceMatrixConversionDim"; //$NON-NLS-1$

  /**
   * the root directory for all results
   *
   * @serial a file pointing to the root direction for the log files
   */
  private File m_dir;

  /**
   * the actual directory to hold this log file
   *
   * @serial a file with the directory for the current log file
   */
  private File m_realDir;

  /**
   * the benchmark problem instance
   *
   * @serial the benchmark instance associated with the benchmark
   */
  final Instance m_instance;

  /**
   * Are we already running (true), i.e., finished setup, or not (false)?
   *
   * @serial a boolean value indicating whether setup has been finished
   */
  private boolean m_running;

  /**
   * the maximum function evaluations (FEs) per run
   *
   * @serial a positive long value with the maximum allowed FEs
   */
  long m_maxFEs;

  /**
   * the maximum distance evaluations (DEs) per run
   *
   * @serial a positive long value with the maximum allowed DEs
   */
  long m_maxDEs;

  /**
   * the maximum runtime in ms per run
   *
   * @serial a positive long value with the maximum allowed runtime in ms
   */
  long m_maxTime;

  /**
   * the matrix limit dimension. If more than {@link #m_limitDim} cities
   * are in a benchmark instance, distances will be computed based on
   * coordinate lists if possible to save memory. If fewer cities are in
   * the instance, the distance lists will automatically be used.
   *
   * @serial an int with the largest number of cities for which distance
   *         matrices will be used
   */
  int m_limitDim;

  /**
   * the maximum number of runs per problem instance
   *
   * @serial an int with the maximum number of runs per instance
   */
  int m_maxRuns;

  /** the values to log */
  transient long[] m_valuesToLog;

  /** the FEs and DEs to log */
  transient long[] m_FEsDEsToLog;

  /** the internal, shared distance computer */
  transient DistanceComputer m_dist;

  /**
   * Create a setup
   *
   * @param instance
   *          the problem instance
   */
  public Benchmark(final Instance instance) {
    this(null, instance);
  }

  /**
   * Create a setup
   *
   * @param instance
   *          the problem instance
   * @param baseDir
   *          the base dir
   */
  public Benchmark(final File baseDir, final Instance instance) {
    super(instance.name());
    final long n;
    long z;

    this.m_dir = FileUtils.canonicalize(//
        (baseDir != null) ? baseDir : new File("./results"));//$NON-NLS-1$

    this.m_instance = instance;

    n = instance.n();
    z = (n * (n * (n * 30l)));
    this.m_maxFEs = ((z > 0l) ? z : Long.MAX_VALUE);
    z *= n;
    this.m_maxDEs = ((z > 0l) ? z : Long.MAX_VALUE);
    this.m_maxTime = Benchmark.DEFAULT_MAX_TIME_PER_RUN;
    this.m_limitDim = Benchmark.DEFAULT_MATRIX_LIMIT_DIM;
    this.m_maxRuns = Benchmark.DEFAULT_MAX_RUNS;
  }

  /** {@inheritDoc} */
  @Override
  public synchronized void configure(final Configuration config) {
    this.__onlyIfNotRunning();
    super.configure(config);

    this.m_maxDEs = config.getLong(Benchmark.PARAM_MAX_DES, 1l,
        Long.MAX_VALUE, this.m_maxDEs);

    this.m_maxFEs = config.getLong(Benchmark.PARAM_MAX_FES, 1l,
        Long.MAX_VALUE, this.m_maxFEs);

    this.m_maxTime = config.getLong(Benchmark.PARAM_MAX_TIME, 1l,
        Long.MAX_VALUE, this.m_maxTime);

    this.m_limitDim = config.getInt(Benchmark.PARAM_LIMIT_DIM, 0,
        Integer.MAX_VALUE,//
        this.m_limitDim);

    this.m_maxRuns = config.getInt(Benchmark.PARAM_MAX_RUNS, 0, 100000,//
        this.m_maxRuns);

    this.m_dir = config.getFile(Benchmark.PARAM_DEST_DIR, this.m_dir);
    this.m_realDir = null;
  }

  /** {@inheritDoc} */
  @Override
  public final void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    this.m_instance.printConfiguration(ps);

    Configurable.printKey(Benchmark.PARAM_MAX_DES, ps);
    ps.println(this.m_maxDEs);

    Configurable.printKey(Benchmark.PARAM_MAX_FES, ps);
    ps.println(this.m_maxFEs);

    Configurable.printKey(Benchmark.PARAM_MAX_TIME, ps);
    ps.println(this.m_maxTime);

    Configurable.printKey(Benchmark.PARAM_LIMIT_DIM, ps);
    ps.println(this.m_limitDim);

    if (this.m_dist != null) {
      Configurable.printKey(Benchmark.DISTANCE_COMPUTER_TYPE, ps);
      Configurable.printlnClass(this.m_dist.getClass(), ps);
    }

    Configurable.printKey(Benchmark.PARAM_DEST_DIR, ps);
    ps.println(this.m_dir.getAbsolutePath());

    Configurable.printKey(Benchmark.PARAM_MAX_RUNS, ps);
    ps.println(this.m_maxRuns);
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    Configurable.printKey(Benchmark.PARAM_MAX_DES, ps);
    ps.println(//
        "The maximum number of DEs (distance evaluations) granted per run."); //$NON-NLS-1$

    Configurable.printKey(Benchmark.PARAM_MAX_FES, ps);
    ps.println(//
        "The maximum number of FEs (objective function evaluations) granted per run."); //$NON-NLS-1$

    Configurable.printKey(Benchmark.PARAM_MAX_TIME, ps);
    ps.println(//
        "The maximum run time granted to a run in milliseconds."); //$NON-NLS-1$

    Configurable.printKey(Benchmark.PARAM_LIMIT_DIM, ps);
    ps.println(//
        "The maximum problem dimension at which a coordinate list is still translated to a distance matrix."); //$NON-NLS-1$

    Configurable.printKey(Benchmark.PARAM_DEST_DIR, ps);
    ps.println(//
        "The destination directory to store the log files."); //$NON-NLS-1$

    Configurable.printKey(Benchmark.PARAM_MAX_RUNS, ps);
    ps.println(//
        "The maximum number of runs to perform."); //$NON-NLS-1$
  }

  /**
   * Get the problem instance to be benchmarked
   *
   * @return the problem instance to be benchmarked
   */
  public final Instance getInstance() {
    return this.m_instance;
  }

  /**
   * get a final file to use
   *
   * @param prefix
   *          a prefix or {@code null}
   * @return the temporary file
   */
  synchronized final File _getFile(final String prefix) {
    int i;
    File f;
    String base_name;

    // this.init();

    try {
      base_name = (this.name() + "_");//$NON-NLS-1$
      if (prefix != null) {
        base_name = (prefix + "_" + base_name);//$NON-NLS-1$
      }
      for (i = 1;; i++) {
        f = new File(this.__getRealDir(), base_name + i + ".txt");//$NON-NLS-1$
        if (f.createNewFile()) {
          return f;
        }
      }

    } catch (final Throwable t) {
      throw new RuntimeException(t);
    }
  }

  /**
   * The number of runs that still need to be performed
   *
   * @return the number of runs that still need to be performed
   */
  public synchronized final int remainingRunCount() {
    final File[] fs;

    fs = this.__getRealDir().listFiles();
    return Math.max(0, (this.m_maxRuns - ((fs != null) ? fs.length : 0)));
  }

  /** only if not running! */
  private final void __onlyIfNotRunning() {
    if (this.m_running) {
      throw new IllegalStateException(//
          "Benchmark parameters cannot be changed during a run."); //$NON-NLS-1$
    }
  }

  /**
   * Set the maximum allowed function evaluations (FEs).
   *
   * @param fes
   *          the maximum FEs
   */
  public synchronized final void setMaxFEs(final long fes) {
    this.__onlyIfNotRunning();
    this.m_maxFEs = ((fes <= 0l) ? Long.MAX_VALUE : fes);
  }

  /**
   * Set the maximum allowed distance evaluations (EEs).
   *
   * @param des
   *          the maximum EEs
   */
  public synchronized final void setMaxDEs(final long des) {
    this.__onlyIfNotRunning();
    this.m_maxDEs = ((des <= 0l) ? Long.MAX_VALUE : des);
  }

  /**
   * Set the maximum allowed time per run in milliseconds. The actually
   * allocated runtime will probably be bigger than this, as we only check
   * the system time when a log point is reached.
   *
   * @param ms
   *          the maximum time per run in milliseconds
   */
  public synchronized final void setMaxRuntime(final long ms) {
    this.__onlyIfNotRunning();
    this.m_maxTime = ((ms <= 0l) ? Long.MAX_VALUE : ms);
  }

  /**
   * get the actual directory
   *
   * @return the actual directory
   */
  private synchronized final File __getRealDir() {
    File f;

    f = this.m_realDir;
    if (f != null) {
      return f;
    }

    f = this.m_dir;

    if (this.m_instance.symmetric()) {
      f = FileUtils.canonicalize(new File(f, Benchmark.SYMMETRIC));
    } else {
      f = FileUtils.canonicalize(new File(f, Benchmark.ASYMMETRIC));
    }
    this.m_realDir = f = FileUtils.canonicalize(new File(f,//
        this.name()));
    return f;
  }

  /**
   * initialize
   *
   * @param makeDirs
   *          should we make the directories?
   */
  synchronized final void _init(final boolean makeDirs) {
    if (this.m_running) {
      return;
    }

    try {
      try {
        if (makeDirs) {
          this.__getRealDir().mkdirs();
        }

        this.m_dist = this.m_instance.load(this.m_limitDim);

        this.m_valuesToLog = Benchmark.getLogObjectiveValues(//
            this.m_instance.optimum());

        this.m_FEsDEsToLog = Benchmark.getLogFEsAndDEs(
            this.m_instance.n(), this.m_maxDEs, this.m_maxFEs);

      } catch (final Throwable txx) {
        throw new RuntimeException(txx);
      }
    } finally {
      this.m_running = true;
    }
  }

  /**
   * Create an objective function to be used for performing runs under this
   * setup. One
   * {@link org.logisticPlanning.tsp.benchmarking.objective.Benchmark} can
   * create arbitrarily many objective functions that can be used in
   * different threads.
   * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction}
   * s are not thread-safe: one function must be used only in one single
   * thread. However, you can create arbitrarily many such functions, one
   * for each thread you want to use, for instance.
   *
   * @return the new objective function.
   */
  public final ObjectiveFunction createObjective() {
    this._init(true);
    return new ObjectiveFunction(this);
  }

  /**
   * <p>
   * Get all the FEs and DEs values that will trigger logging. Generally,
   * we return the values listed in {@link Benchmark#LOG_POINTS} as well as
   * the multiples of these values and {@code n}, {@code n^2}, and
   * {@code n^3}. This allows us to collect performance measurements both
   * for absolute step indices as well as for multiples of the number of
   * nodes (the problem size), the squared problem size, and the problem
   * size by the 3.
   * </p>
   * <p>
   * The values are listed from the largest to the smallest.
   * </p>
   *
   * @param n
   *          the number of nodes
   * @param maxDEs
   *          the maximum DEs
   * @param maxFEs
   *          the maximum FEs
   * @return the array of objective values that would trigger logging
   */
  public static final long[] getLogFEsAndDEs(final int n,
      final long maxDEs, final long maxFEs) {
    final long[] array, temp, src;
    final int len;
    int idxOut, idxPlain, idxN, idxNN, idxNNN, nl, idxFeDe, FeDeLen;
    final long mulN, mulNN, mulNNN, max;
    long last, min, plain, N, NN, NNN, FeDe;
    boolean it;

    temp = new long[((Benchmark.LOG_POINTS.length + 5) * 5) + 3];

    // check the limit for FEs or the limit for DEs is bigger
    if (maxFEs > maxDEs) {
      max = maxFEs;
      min = maxDEs;
    } else {
      max = maxDEs;
      min = maxFEs;
    }

    // we use the front portion of the temp array to hold the FeDe limits,
    // if
    // they are defined
    FeDeLen = 0;
    FeDe = Long.MAX_VALUE;
    if ((min > 0l) && (min < Long.MAX_VALUE)) {
      temp[FeDeLen++] = min;
      FeDe = min;
    }
    if ((max > 0l) && (max < Long.MAX_VALUE)) {
      temp[FeDeLen++] = max;
      if (FeDeLen == 1) {
        FeDe = max;
      }
    }

    idxPlain = idxN = idxNN = idxNNN = idxFeDe = 0;
    src = Benchmark.LOG_POINTS;
    len = src.length;

    plain = src[0];

    mulN = n;
    N = (plain * mulN);

    mulNN = (mulN * n);
    NN = (plain * mulNN);

    mulNNN = (mulNN * n);
    NNN = (plain * mulNNN);

    idxOut = temp.length;
    last = -1l;

    // in this loop, we include the log points, as well as their multiples
    // with
    // n, n², n³, and the FE and DE limits
    outer: for (;;) {
      min = Math
          .min(Math.min(plain, N), Math.min(Math.min(NN, NNN), FeDe));

      if (min != last) {
        temp[--idxOut] = min;
        last = min;
        if (min >= max) {
          break outer;
        }
      }

      it = true;

      // add the log point directly, if it is less or equal than the
      // minimum
      addPlain: {
        if (min >= plain) {
          it = false;
          if ((++idxPlain) < len) {
            plain = src[idxPlain];
            if (plain <= max) {
              break addPlain;
            }
          }
          plain = Long.MAX_VALUE;
          idxPlain = len;
        }
      }

      // add the log point multiplied with n, if it is less or equal than
      // the
      // minimum
      addN: {
        if (min >= N) {
          it = false;
          if ((++idxN) < len) {
            N = (mulN * src[idxN]);
            if ((N >= min) && (N <= max)) {
              break addN;
            }
          }

          N = Long.MAX_VALUE;
          idxN = len;
        }
      }

      // add the log point multiplied with n², if it is less or equal than
      // the
      // minimum
      addNN: {
        if (min >= NN) {
          it = false;
          if ((++idxNN) < len) {
            NN = (mulNN * src[idxNN]);
            if ((NN >= min) && (NN <= max)) {
              break addNN;
            }
          }

          NN = Long.MAX_VALUE;
          idxNN = len;
        }
      }

      // add the log point multiplied with n³, if it is less or equal than
      // the
      // minimum
      addNNN: {
        if (min >= NNN) {
          it = false;
          if ((++idxNNN) < len) {
            NNN = (mulNNN * src[idxNNN]);
            if ((NNN >= min) && (NNN <= max)) {
              break addNNN;
            }
          }

          NNN = Long.MAX_VALUE;
          idxNNN = len;
        }
      }

      // add the FE or DE limit
      addFeDe: {
        if (min >= FeDe) {
          it = false;
          if ((++idxFeDe) < FeDeLen) {
            FeDe = temp[idxFeDe];
            break addFeDe;
          }
          FeDe = Long.MAX_VALUE;
          idxFeDe = len;
        }
      }

      if (it) {
        break outer;
      }
    }

    // build the output array
    nl = (temp.length - idxOut);
    array = new long[nl + 3];
    array[0] = Long.MAX_VALUE;
    System.arraycopy(temp, idxOut, array, 1, nl);
    array[nl + 1] = 0l;
    array[nl + 2] = Long.MIN_VALUE;

    return array;
  }

  /**
   * <p>
   * Get all the objective values that will trigger logging. These values
   * are the optimum plus specific offsets, and certain multiples of the
   * optimum.
   * </p>
   * <p>
   * The values are sorted from the smallest to the largest.
   * </p>
   *
   * @param optimum
   *          the known optimum or lower bound
   * @return the array of objective values that would trigger logging
   */
  public static final long[] getLogObjectiveValues(final long optimum) {
    final long[] array, temp, src;
    final int len;
    int idxOut, idxPlain, idx_EM3_Floor, idx_EM3_Ceil, idx_EM6_Floor, idx_EM6_Ceil;
    long last, min, plain, EM3_Ceil, EM3_Floor, EM6_Ceil, EM6_Floor;
    long cutOff;
    boolean it;

    temp = new long[((Benchmark.LOG_POINTS.length + 6) * 6) + 1];

    idxPlain = idx_EM3_Floor = idx_EM3_Ceil = idx_EM6_Floor = idx_EM6_Ceil = 0;
    src = Benchmark.LOG_POINTS;
    len = src.length;

    last = src[0];
    plain = (last + optimum);
    EM3_Floor = (Math.round(Math
        .floor((Benchmark.MULTIPLIER_1 * last * optimum))) + optimum);
    EM3_Ceil = (Math.round(Math
        .ceil((Benchmark.MULTIPLIER_1 * last * optimum))) + optimum);
    EM6_Floor = (Math.round(Math
        .floor((Benchmark.MULTIPLIER_2 * last * optimum))) + optimum);
    EM6_Ceil = (Math.round(Math
        .ceil((Benchmark.MULTIPLIER_2 * last * optimum))) + optimum);

    cutOff = last = optimum;
    last <<= 2l;
    if (last > optimum) {
      cutOff = last;
      last *= 25l;
      if (last > optimum) {
        cutOff = last;
      } else {
        cutOff = Long.MAX_VALUE;
      }
    } else {
      last = (cutOff << 1l);
      if (last > optimum) {
        cutOff = last;
      } else {
        cutOff = Long.MAX_VALUE;
      }
    }

    idxOut = 1;
    last = optimum;
    temp[0] = last;
    for (;;) {
      min = Math.min(Math.min(plain, EM3_Floor),
          Math.min(Math.min(EM3_Ceil, EM6_Floor), EM6_Ceil));

      if (min != last) {
        temp[idxOut++] = min;
        last = min;
      }

      it = true;
      if (min >= plain) {
        if ((++idxPlain) < len) {
          plain = (src[idxPlain] + optimum);
          if (plain < min) {
            plain = Long.MAX_VALUE;
            idxPlain = len;
          } else {
            it = false;
          }
        } else {
          plain = Long.MAX_VALUE;
        }
      }

      if (min >= EM3_Floor) {
        if ((++idx_EM3_Floor) < len) {
          EM3_Floor = ((Math.round(Math.floor(src[idx_EM3_Floor]
              * Benchmark.MULTIPLIER_1 * optimum))) + optimum);
          if (EM3_Floor < min) {
            EM3_Floor = Long.MAX_VALUE;
            idx_EM3_Floor = len;
          } else {
            it = false;
          }
        } else {
          EM3_Floor = Long.MAX_VALUE;
        }
      }

      if (min >= EM3_Ceil) {
        if ((++idx_EM3_Ceil) < len) {
          EM3_Ceil = ((Math.round(Math.ceil(src[idx_EM3_Ceil]
              * Benchmark.MULTIPLIER_1 * optimum))) + optimum);
          if (EM3_Ceil < min) {
            EM3_Ceil = Long.MAX_VALUE;
            idx_EM3_Ceil = len;
          } else {
            it = false;
          }
        } else {
          EM3_Ceil = Long.MAX_VALUE;
        }
      }

      if (min >= EM6_Floor) {
        if ((++idx_EM6_Floor) < len) {
          EM6_Floor = ((Math.round(Math.floor(src[idx_EM6_Floor]
              * Benchmark.MULTIPLIER_2 * optimum))) + optimum);
          if (EM6_Floor < min) {
            EM6_Floor = Long.MAX_VALUE;
            idx_EM6_Floor = len;
          } else {
            it = false;
          }
        } else {
          EM6_Floor = Long.MAX_VALUE;
        }
      }

      if (min >= EM6_Ceil) {
        if ((++idx_EM6_Ceil) < len) {
          EM6_Ceil = ((Math.round(Math.ceil(src[idx_EM6_Ceil]
              * Benchmark.MULTIPLIER_2 * optimum))) + optimum);
          if (EM6_Ceil < min) {
            EM6_Ceil = Long.MAX_VALUE;
            idx_EM6_Ceil = len;
          } else {
            it = false;
          }
        } else {
          EM6_Ceil = Long.MAX_VALUE;
        }
      }

      if (it) {
        break;
      }
    }

    array = new long[idxOut + 3];
    array[0] = Long.MIN_VALUE;
    array[1] = 0l;
    System.arraycopy(temp, 0, array, 2, idxOut);
    array[idxOut + 2] = Long.MAX_VALUE;

    return array;
  }

}
