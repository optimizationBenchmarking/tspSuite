package org.logisticPlanning.tsp.evaluation.modules.spec;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.evaluation.data.DataSet;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.document.spec.Element;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.text.TextUtils;

/**
 * An evaluator module. Each module will add a piece of information to the
 * final evaluation report in an own, separate section. Modules are
 * configurable but should not store any data-related state in them.
 * Instead, they should use the
 * {@link org.logisticPlanning.tsp.evaluation.data.Property property API}
 * of the {@link org.logisticPlanning.tsp.evaluation.data data package} to
 * place information into the data sets directly. This allows the
 * {@link org.logisticPlanning.tsp.evaluation.Evaluator evaluator} and its
 * modules to be configured once an then applied multiple times in
 * parallel.
 * 
 * 
 @author <em><a href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a>
 */
public class Module extends Configurable implements Comparable<Module> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the graphics folder: {@value} */
  public static final String GRAPHICS_FOLDER = "graphics"; //$NON-NLS-1$
  /** the legend file name: {@value} */
  public static final String LEGEND = "legend"; //$NON-NLS-1$
  /** the figure size parameter: {@value} */
  protected static final String FIGURE_SIZE_PARAM = "FigureSize"; //$NON-NLS-1$
  /** the figure size parameter descriptor prefix: {@value} */
  protected static final String FIGURE_SIZE_PARAM_DESC_PREFIX = "the size of the figures printed by "; //$NON-NLS-1$

  /** activate all modules */
  public static final String PARAM_ALL_MODULES = "allModules"; //$NON-NLS-1$

  /** the module is inactive */
  private static final int ACTIVE_INACTIVE = 0;
  /** the module is active */
  private static final int ACTIVE_ACTIVE = (Module.ACTIVE_INACTIVE + 1);
  /** the module is forced to be active */
  private static final int ACTIVE_FORCE_ACTIVE = (Module.ACTIVE_ACTIVE + 1);
  /** the module is forced to be inactive */
  private static final int ACTIVE_FORCE_INACTIVE = (Module.ACTIVE_FORCE_ACTIVE + 1);

  /** the other modules that depend on this one */
  private ArrayList<Module> m_dependants;

  /** the child modules */
  private ArrayList<Module> m_children;

  /** the root module */
  final RootModule m_root;

  /** the owning module */
  private final Module m_owner;

  /** are we active? */
  private int m_active;

  /** the list of active children */
  private ArraySetView<Module> m_activeChildren;

  /** has the module been configured? */
  private boolean m_configured;

  /**
   * create
   * 
   * @param name
   *          the evaluator name
   * @param owner
   *          the owning module
   * @param active
   *          is this module initially active or not?
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  Module(final String name, final Module owner, final boolean active) {
    super(name);
    if (owner != null) {
      owner._addChild(this);
      this.m_root = owner.m_root;
    } else {
      this.m_root = ((RootModule) this);
    }
    this.m_owner = owner;
    this.m_active = (active ? Module.ACTIVE_ACTIVE
        : Module.ACTIVE_INACTIVE);
    this.m_activeChildren = ((ArraySetView) (ArraySetView.EMPTY_SET_VIEW));

    this.createChildModules();
  }

  /**
   * Get the owning module
   * 
   * @return the owning module
   */
  protected final Module getOwner() {
    return this.m_owner;
  }

  /**
   * Get the collection of active child modules
   * 
   * @return the collection of active child modules
   */
  protected final ArraySetView<Module> getActiveChildren() {
    return this.m_activeChildren;
  }

  /** Create the child modules of this module. */
  void createChildModules() {
    //
  }

  /**
   * Get the root module
   * 
   * @return the root module
   */
  protected final RootModule getRoot() {
    return this.m_root;
  }

  /**
   * Add a dependency on another module
   * 
   * @param mod
   *          the other module
   */
  public void addDependency(final Module mod) {
    if (mod != null) {
      if (this.containsDependency(mod)) {
        throw new IllegalStateException(//
            " Module '" + this.name() + //$NON-NLS-1$
                "' cannot depend on module '" + mod.name() + //$NON-NLS-1$
                "', because '" + mod.name() + //$NON-NLS-1$
                "' already depends on '" + this.name() + //$NON-NLS-1$
                "'."); //$NON-NLS-1$
      }

      if (mod.m_dependants == null) {
        mod.m_dependants = new ArrayList<>();
      } else {// avoid useless double dependencies
        if (mod.containsDependency(this)) {
          return;
        }
      }
      mod.m_dependants.add(this);
    }
  }

  /**
   * is module {@code mod} depending on this module?
   * 
   * @param mod
   *          the module
   * @return {@code true} if {@code mod} depends on this module,
   *         {@code false} otherwise
   */
  private final boolean containsDependency(final Module mod) {
    if (this.m_dependants == null) {
      return false;
    }
    for (final Module m : this.m_dependants) {
      if (m == mod) {
        return true;
      }
      if (m.containsDependency(mod)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Find an instance of the given class in the hierarchical tree of
   * modules
   * 
   * @param clazz
   *          the class
   * @return the instance, or {@code null} if none was found
   */
  @SuppressWarnings("unchecked")
  final <T extends Module> T _findInstance(final Class<? extends T> clazz) {
    final ArrayList<Module> children;
    T t;
    Module m;
    int i;

    children = this.m_children;
    if (children != null) {
      for (i = children.size(); (--i) >= 0;) {
        m = children.get(i);
        if (clazz.isInstance(m)) {
          return ((T) m);
        }
        t = m._findInstance(clazz);
        if (t != null) {
          return t;
        }
      }
    }

    return null;
  }

  /**
   * Add a child module
   * 
   * @param child
   *          the new child module
   */
  void _addChild(final Module child) {
    if (this.m_children == null) {
      this.m_children = new ArrayList<>();
    }
    this.m_children.add(child);

    if (this.m_dependants == null) {
      this.m_dependants = new ArrayList<>();
      this.m_dependants.add(child);
    }
  }

  /**
   * the errror if a child is not permitted
   * 
   * @param child
   *          the child
   */
  final void _childNotPermittedError(final Module child) {
    throw new IllegalArgumentException(//
        "Instance of class '" + child.getClass() + //$NON-NLS-1$
            "' not permitted as child of a module of type '" + //$NON-NLS-1$
            this.getClass() + "'."); //$NON-NLS-1$
  }

  /**
   * Get the logger
   * 
   * @return the logger
   */
  protected final Logger getLogger() {
    return this.m_root.m_evaluator.getLogger();
  }

  /**
   * Set whether this module is active or not. Must be done before calling
   * {@link #configure(Configuration)}!
   * 
   * @param active
   *          {@code true} if this evaluator should be active,
   *          {@code false} otherwise
   */
  protected final void setActive(final boolean active) {
    this.m_active = (active ? Module.ACTIVE_ACTIVE
        : Module.ACTIVE_INACTIVE);
  }

  /**
   * is this evaluator active?
   * 
   * @return {@code true} if this evaluator is active, {@code false}
   *         otherwise
   */
  public final boolean isActive() {
    return ((this.m_active == Module.ACTIVE_ACTIVE) || (this.m_active == Module.ACTIVE_FORCE_ACTIVE));
  }

  /**
   * Apply this module to a data set.
   * 
   * @param body
   *          the body where the section of this module can be placed into
   *          via
   *          {@link org.logisticPlanning.utils.document.spec.Section#section(org.logisticPlanning.utils.document.spec.Element, org.logisticPlanning.utils.document.spec.Label)}
   * @param data
   *          the data
   * @throws IOException
   *           if io fails
   */
  void _run(final Element body, final DataSet<?> data) throws IOException {
    this._runChildren(body, data);
  }

  /**
   * Apply all children to a given data set.
   * 
   * @param body
   *          the body
   * @param data
   *          the data
   * @throws IOException
   *           if io fails
   */
  @SuppressWarnings("unused")
  final void _runChildren(final Element body, final DataSet<?> data)
      throws IOException {
    if (this.m_activeChildren != null) {
      for (final Module mod : this.m_activeChildren) {
        mod._call_run(body, data);
      }
    }
  }

  /**
   * perform the evaluation
   * 
   * @param body
   *          the body where the section of this module can be placed into
   *          via
   *          {@link org.logisticPlanning.utils.document.spec.Section#section(org.logisticPlanning.utils.document.spec.Element, org.logisticPlanning.utils.document.spec.Label)}
   * @param data
   *          the experiment set
   */
  final void _call_run(final Element body, final DataSet<?> data) {
    final Logger log;
    final Level level;

    this.__checkConfigured();

    log = this.getLogger();

    if ((data == null) || (data.size() <= 0)) {
      if ((log != null) && (log.isLoggable(Level.WARNING))) {
        log.warning(//
        "Null or empty data set passed to 'run' method of evaluation module - module quits without doing anything.");//$NON-NLS-1$
      }
      return;
    }

    if (!(this.isActive())) {
      if ((log != null) && (log.isLoggable(Level.WARNING))) {
        log.warning("Running the INACTIVE module '" + this.name() + //$NON-NLS-1$
            "' on data set '" //$NON-NLS-1$
            + data.name() + '\'');
      }
    }

    level = (this.hasContent(data) ? Level.INFO : Level.FINE);
    if ((log != null) && (log.isLoggable(level))) {
      log.info("Begin running module '" + this.name() + //$NON-NLS-1$
          "' on data set '" //$NON-NLS-1$
          + data.name() + '\'');
    }

    try {
      try {
        this._run(body, data);
      } catch (final Throwable t) {
        if (log != null) {
          log.log(Level.SEVERE,
              "An error occured during running the evaluator modules.", t);//$NON-NLS-1$
        }
      }
    } finally {
      if ((log != null) && (log.isLoggable(level))) {
        log.info("Finished running module '" + this.name() + //$NON-NLS-1$
            "' on data set '" //$NON-NLS-1$
            + data.name() + '\'');
      }
    }
  }

  /**
   * Initialize this module on a data set.
   * 
   * @param header
   *          the header of the document
   * @param data
   *          the data element
   * @throws IOException
   *           if io fails
   */
  void _initialize(final Header header, final DataSet<?> data)
      throws IOException {//
    this._initializeChildren(header, data);
  }

  /**
   * initialize all the elements in the given array
   * 
   * @param header
   *          the header
   * @param data
   *          the data
   * @throws IOException
   *           if io fails
   */
  @SuppressWarnings("unused")
  final void _initializeChildren(final Header header, final DataSet<?> data)
      throws IOException {

    if (this.m_activeChildren != null) {
      for (final Module mod : this.m_activeChildren) {
        mod._call_initialize(header, data);
      }
    }
  }

  /**
   * perform the initialization procedure
   * 
   * @param header
   *          the header of the document
   * @param data
   *          the experiment set
   */
  final void _call_initialize(final Header header, final DataSet<?> data) {
    final Logger log;
    final Level level;

    this.__checkConfigured();

    log = this.getLogger();

    if ((data == null) || (data.size() <= 0)) {
      if ((log != null) && (log.isLoggable(Level.WARNING))) {
        log.warning(//
        "Null or empty data set passed to 'initialize' method of evaluation module - module quits without doing anything.");//$NON-NLS-1$
      }
      return;
    }

    if (!(this.isActive())) {
      if ((log != null) && (log.isLoggable(Level.WARNING))) {
        log.warning("Initializing the INACTIVE module '" + this.name() + //$NON-NLS-1$
            "' on data set '" //$NON-NLS-1$
            + data.name() + '\'');
      }
    }

    level = (this.hasContent(data) ? Level.INFO : Level.FINE);
    if ((log != null) && (log.isLoggable(level))) {
      log.info("Begin initialization of module '" + this.name() + //$NON-NLS-1$
          "' on data set '" //$NON-NLS-1$
          + data.name() + '\'');
    }

    try {
      try {
        this._initialize(header, data);
      } catch (final Throwable t) {
        if (log != null) {
          log.log(
              Level.SEVERE,
              "An error occurred during initialization of the evaluator modules.", t);//$NON-NLS-1$
        }
      }
    } finally {
      if ((log != null) && (log.isLoggable(level))) {
        log.info("Finished initialization of module '" + this.name() + //$NON-NLS-1$
            "' on data set '" //$NON-NLS-1$
            + data.name() + '\'');
      }
    }
  }

  /**
   * compare modules based on their classes
   * 
   * @param a
   *          the first module
   * @param b
   *          the second module
   * @return the result
   */
  private static final int __classCheck(final Module a, final Module b) {
    boolean x, y;

    x = (a instanceof RootModule);
    y = (b instanceof RootModule);
    if (x && y) {
      return 0;
    }
    if (x) {
      return (-1);
    }
    if (y) {
      return 1;
    }

    x = (a instanceof _Descriptions);
    y = (b instanceof _Descriptions);
    if (x && y) {
      return 0;
    }
    if (x) {
      return (-1);
    }
    if (y) {
      return 1;
    }

    x = (a instanceof _Single);
    y = (b instanceof _Single);
    if (x && y) {
      return 0;
    }
    if (x) {
      return (-1);
    }
    if (y) {
      return 1;
    }

    x = (a instanceof _Comparisons);
    y = (b instanceof _Comparisons);
    if (x && y) {
      return 0;
    }
    if (x) {
      return (-1);
    }
    if (y) {
      return 1;
    }

    x = (a instanceof DescriptionModule);
    y = (b instanceof DescriptionModule);
    if (x && y) {
      return 0;
    }
    if (x) {
      return (-1);
    }
    if (y) {
      return 1;
    }

    x = (a instanceof SingleModule);
    y = (b instanceof SingleModule);
    if (x && y) {
      return 0;
    }
    if (x) {
      return (-1);
    }
    if (y) {
      return 1;
    }

    x = (a instanceof ComparisonModule);
    y = (b instanceof ComparisonModule);
    if (x && y) {
      return 0;
    }
    if (x) {
      return (-1);
    }
    if (y) {
      return 1;
    }

    return 0;
  }

  /**
   * compare two modules
   * 
   * @param a
   *          the first module
   * @param b
   *          the second module
   * @param set
   *          the set
   * @param alsoConsiderInactive
   *          should we also consider inactive modules?
   * @return the return value
   */
  private static final int __compare(final Module a, final Module b,
      final HashSet<Module> set, final boolean alsoConsiderInactive) {
    final HashSet<Module> visited;
    final ArrayList<Module> depA, depB;
    final boolean aa, ba;
    int res;

    if (a == b) {
      return 0;
    }
    if (a == null) {
      return (1);
    }
    if (b == null) {
      return (-1);
    }
    aa = (a.isActive() || alsoConsiderInactive);
    ba = (b.isActive() || alsoConsiderInactive);

    if (aa || ba) {
      if (!aa) {
        return 1;
      }
      if (!ba) {
        return (-1);
      }
    } else {
      return 0;
    }

    res = Module.__classCheck(a, b);
    if (res != 0) {
      return res;
    }

    depA = a.m_dependants;
    depB = b.m_dependants;

    if (depA == depB) {// can only be if both are null
      return 0;
    }

    visited = ((set == null) ? (new HashSet<Module>()) : set);
    visited.add(a);
    visited.add(b);

    if (depA != null) {
      res = Module.__trace(depA, b, visited, alsoConsiderInactive);
      if ((res < (-1)) || (res > 1)) {
        return 2;
      }
    }
    if (depB != null) {
      switch (-Module.__trace(depB, a, visited, alsoConsiderInactive)) {
        case -1: {
          if (res > 0) {
            return 2;
          }
          return (-1);
        }
        case 0: {
          return res;
        }
        case 1: {
          if (res < 0) {
            return 2;
          }
          return 1;
        }
        default: {
          return 2;
        }
      }
    }

    return res;
  }

  /**
   * trace a list
   * 
   * @param lst
   *          the list
   * @param b
   *          the other module
   * @param visited
   *          the set of visited modules
   * @param alsoConsiderInactive
   *          should we also consider inactive modules?
   * @return the return value
   */
  private static final int __trace(final ArrayList<Module> lst,
      final Module b, final HashSet<Module> visited,
      final boolean alsoConsiderInactive) {
    int res, i;
    Module mod;

    res = 0;
    loopy: for (i = lst.size(); (--i) >= 0;) {
      mod = lst.get(i);

      if (mod == b) {// b depends on a
        if (res > 0) {
          return 2;
        }
        return (-1);
      }

      if (visited.contains(mod)) {
        continue loopy;
      }

      switch (Module.__compare(mod, b, visited, alsoConsiderInactive)) {
        case -1: {
          if (res > 0) {
            return 2;
          }
          res = (-1);
          continue loopy;
        }
        case 0: {
          continue loopy;
        }
        case 1: {
          if (res < 0) {
            return 2;
          }
          res = 1;
          continue loopy;
        }
        default: {
          return 2;
        }
      }
    }

    return res;
  }

  /** {@inheritDoc} */
  @Override
  public int compareTo(final Module other) {
    int comp;

    comp = Module.__compare(this, other, null, false);
    if ((comp < (-1)) || (comp > 1)) {
      return 0;
    }

    comp = Module.__compare(this, other, null, true);
    if ((comp < (-1)) || (comp > 1)) {
      return 0;
    }

    return comp;
  }

  /** ensure that the module was configured properly */
  private final void __checkConfigured() {
    if (!(this.m_configured)) {
      throw new IllegalStateException("Module '" + this.name() + //$NON-NLS-1$
          "' has not been configured!"); //$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public void configure(final Configuration config) {
    this.m_activeChildren = ((ArraySetView) (ArraySetView.EMPTY_SET_VIEW));
    this.m_configured = true;
    super.configure(config);

    if (this.m_children != null) {
      for (final Module mod : this.m_children) {
        mod.configure(config);
      }
    }

    if (config.getBoolean(Module.PARAM_ALL_MODULES, false)) {
      this.m_active = Module.ACTIVE_FORCE_ACTIVE;
      return;
    }

    if (!(config.getBoolean(this.name(), true))) {
      this.m_active = Module.ACTIVE_FORCE_INACTIVE;
      return;
    }
    if (config.getBoolean(this.name(), false)) {
      this.m_active = Module.ACTIVE_FORCE_ACTIVE;
      return;
    }
  }

  /**
   * this method must be called at the end of the configure routine in the
   * root module
   */
  final void _afterRootConfigure() {
    while (this.__propagateActive()) {
      /** find all active modules */
    }
    this.__gatherActiveChildren();
  }

  /**
   * Propagate activation back and forth
   * 
   * @return {@code true} if something changed about the activation status
   */
  private final boolean __propagateActive() {
    boolean ret;

    ret = false;
    if (this.m_children != null) {
      for (final Module m : this.m_children) {
        if (m.__propagateActive()) {
          ret = true;
        }
      }
    }

    if (this.m_active == Module.ACTIVE_ACTIVE) {
      return ret;
    }

    if (this.m_active == Module.ACTIVE_FORCE_INACTIVE) {
      return false;
    }

    if (this.m_children != null) {
      for (final Module m : this.m_children) {
        if (m.isActive()) {
          this.m_active = Module.ACTIVE_ACTIVE;
          ret = true;
        }
      }
    }
    if (this.m_dependants != null) {
      for (final Module m : this.m_dependants) {
        if (m.isActive()) {
          this.m_active = Module.ACTIVE_ACTIVE;
          ret = true;
        }
      }
    }

    return ret;
  }

  /** gather the active children */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  private final void __gatherActiveChildren() {
    final ArrayList<Module> active;
    final Module[] ch;

    if (this.m_children != null) {
      active = new ArrayList<>();
      for (final Module mod : this.m_children) {
        if (mod.isActive()) {
          active.add(mod);
          mod.__gatherActiveChildren();
        }
      }
      ch = active.toArray(new Module[active.size()]);
      Arrays.sort(ch);
      this.m_activeChildren = ArraySetView.makeArraySetView(ch, false);
      return;
    }
    this.m_activeChildren = ((ArraySetView) (ArraySetView.EMPTY_SET_VIEW));
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);
    Configurable.printKey(this.name(), ps);
    ps.println(this.isActive());

    if (this.m_children != null) {
      for (final Module m : this.m_children) {
        m.printConfiguration(ps);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    final String s;

    super.printParameters(ps);

    s = this.name();
    Configurable.printKey(s, ps);
    ps.print("true to activate, false to de-activate the "); //$NON-NLS-1$
    ps.print(s);
    ps.println(" module"); //$NON-NLS-1$

    if (this.m_children != null) {
      for (final Module m : this.m_children) {
        m.printParameters(ps);
      }
    }
  }

  /**
   * log a warning since the module can only process data of a given type
   * 
   * @param clazz
   *          the class of data that can be processed
   * @param data
   *          the data actually passed in
   * @param what
   *          what is quit
   */
  final void _logModuleCanOnlyProcessWarning(final Class<?> clazz,
      final Object data, final String what) {
    final Logger log;
    log = this.getLogger();
    if ((log != null) && (log.isLoggable(Level.WARNING))) {
      log.warning(this.getClass()
          + " module can only process instances of '" + //$NON-NLS-1$
          clazz + "' but an instance of '" + data.getClass() + //$NON-NLS-1$
          "' has been passed in. Module quits '" + what + //$NON-NLS-1$
          "' without doing anything.");//$NON-NLS-1$
    }
  }

  /**
   * make a uri that represents the given configuration
   * 
   * @param folder
   *          a default sub-folder, or {@code null} if none provided
   * @param experiment
   *          experiment the experiment, or {@code null} if none provided
   * @param instance
   *          the instance, or {@code null} if none provided
   * @param fileName
   *          a fixed file name
   * @return the uri
   * @throws IOException
   *           if io fails
   */
  protected final URI makeURI(final String folder,
      final Experiment experiment, final Instance instance,
      final String fileName) throws IOException {
    final String m, e, rs, f, n, u;
    final StringBuilder uri, fn;

    f = TextUtils.prepare(folder);
    m = TextUtils.prepare(this.name());
    n = TextUtils.prepare(fileName);
    e = ((experiment != null) ? TextUtils.prepare(experiment.shortName())
        : (Module.LEGEND.equalsIgnoreCase(n) ? null : "_all_")); //$NON-NLS-1$
    rs = ((instance != null) ? instance.name() : null);

    uri = new StringBuilder();
    fn = new StringBuilder();

    if (f != null) {
      uri.append(f);
    }

    if (e != null) {
      if (uri.length() > 0) {
        uri.append('/');
      }
      uri.append(e);
      fn.append(e);
    }

    if (m != null) {
      if ((e != null) || (rs != null)) {
        if (uri.length() > 0) {
          uri.append('/');
        }
        uri.append(m);
      }
      if (fn.length() > 0) {
        fn.append('_');
      }
      fn.append(m);
    }

    if (rs != null) {
      if (fn.length() > 0) {
        fn.append('_');
      }
      fn.append(rs);
    }
    if (n != null) {
      if (n.length() > 0) {
        fn.append('_');
      }
      fn.append(n);
    }

    if (fn.length() > 0) {
      if (uri.length() > 0) {
        uri.append('/');
      }
      uri.append(fn);
    }

    u = TextUtils.prepare(uri.toString());
    try {
      return new URI(u);
    } catch (final URISyntaxException use) {
      throw new IOException("Invalid URI format encountered ('" + //$NON-NLS-1$
          f + "', '" + m + "', '" + e + //$NON-NLS-1$ //$NON-NLS-2$
          "', '" + rs + "', '" + //$NON-NLS-1$ //$NON-NLS-2$
          f + "').", //$NON-NLS-1$
          use);
    }
  }

  /**
   * make a uri that represents the given configuration
   * 
   * @param folder
   *          a default sub-folder, or {@code null} if none provided
   * @param fileName
   *          a fixed file name
   * @return the uri
   * @throws IOException
   *           if io fails
   */
  protected final URI makeURI(final String folder, final String fileName)
      throws IOException {
    return this.makeURI(folder, null, null, fileName);
  }

  /**
   * make a uri that represents the given configuration
   * 
   * @param folder
   *          a default sub-folder, or {@code null} if none provided
   * @param experiment
   *          experiment the experiment, or {@code null} if none provided
   * @param instance
   *          the instance, or {@code null} if none provided
   * @return the uri
   * @throws IOException
   *           if io fails
   */
  protected final URI makeURI(final String folder,
      final Experiment experiment, final Instance instance)
      throws IOException {
    return this.makeURI(folder, experiment, instance, null);
  }

  /**
   * Does this module have contents?
   * 
   * @param data
   *          the data set
   * @return {@code true} if so, {@code false} otherwise
   */
  boolean hasContent(final DataSet<?> data) {
    return false;
  }
}
