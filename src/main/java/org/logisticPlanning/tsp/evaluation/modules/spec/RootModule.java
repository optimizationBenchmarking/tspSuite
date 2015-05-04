package org.logisticPlanning.tsp.evaluation.modules.spec;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.logisticPlanning.tsp.evaluation.Evaluator;
import org.logisticPlanning.tsp.evaluation.EvaluatorVersion;
import org.logisticPlanning.tsp.evaluation.data.DataSet;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSetup;
import org.logisticPlanning.tsp.evaluation.modules.impl.ExperimentSequence;
import org.logisticPlanning.utils.config.ClassParser;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.config.ListParser;
import org.logisticPlanning.utils.document.spec.Body;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.document.spec.ESequenceType;
import org.logisticPlanning.utils.document.spec.Element;
import org.logisticPlanning.utils.document.spec.Emphasize;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.MacroDefinition;
import org.logisticPlanning.utils.document.spec.MacroDescriptor;
import org.logisticPlanning.utils.document.spec.Summary;
import org.logisticPlanning.utils.document.spec.Title;
import org.logisticPlanning.utils.document.spec.bib.BibAuthor;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;

/**
 * The roow module
 */
public final class RootModule extends Module {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * this parameter allows you to dynamically load additional evaluation
   * modules: {@value}
   */
  public static final String PARAM_MORE_MODULES = "moreModules"; //$NON-NLS-1$

  /** the evaluator */
  final Evaluator m_evaluator;

  /** the long macros */
  private final _LongMacros m_long;

  /** the short macros */
  private final _ShortMacros m_short;

  /** the descriptions */
  private final _Descriptions m_desc;
  /** the single modules */
  private final _Single m_single;
  /** the descriptions */
  private final _Comparisons m_comp;

  /**
   * create
   *
   * @param evaluator
   *          the evaluator
   */
  public RootModule(final Evaluator evaluator) {
    super("root", null, true); //$NON-NLS-1$
    this.m_evaluator = evaluator;
    this.m_long = new _LongMacros();
    this.m_short = new _ShortMacros();

    this.m_desc = new _Descriptions(this);
    this.m_single = new _Single(this);
    this.m_comp = new _Comparisons(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void configure(final Configuration config) {
    List<Class<Module>> more;

    more = config.get(RootModule.PARAM_MORE_MODULES,//
        new ListParser<>(new ClassParser<>(Module.class)), null);

    if (more != null) {
      for (final Class<Module> mod : more) {
        this.addModule(mod);
      }
    }

    super.configure(config);

    this._afterRootConfigure();
  }

  /** {@inheritDoc} */
  @Override
  final void _addChild(final Module child) {
    if (child instanceof _Descriptions) {
      if (this.m_desc != null) {
        throw new IllegalStateException(//
            "Only one _Descriptions module allowed."); //$NON-NLS-1$
      }
      super._addChild(child);
    } else {
      if (child instanceof _Comparisons) {
        if (this.m_comp != null) {
          throw new IllegalStateException(//
              "Only one _Comparisons module allowed."); //$NON-NLS-1$
        }
        super._addChild(child);
      } else {
        if (child instanceof _Single) {
          if (this.m_single != null) {
            throw new IllegalStateException(//
                "Only one _Single module allowed."); //$NON-NLS-1$
          }
          super._addChild(child);
        } else {
          this._childNotPermittedError(child);
        }
      }
    }
  }

  /**
   * add an instance of the given class to the module list
   *
   * @param clazz
   *          the class
   */
  @SuppressWarnings("unchecked")
  public final void addModule(final Class<? extends Module> clazz) {
    Module m, owner;
    Constructor<Module>[] c;
    Class<?>[] params;

    m = this.findInstance(clazz);
    if (m != null) {
      m.setActive(true);
      return;
    }

    if (DescriptionModule.class.isAssignableFrom(clazz)) {
      owner = this.m_desc;
    } else {
      if (ComparisonModule.class.isAssignableFrom(clazz)) {
        owner = this.m_comp;
      } else {
        if (SingleModule.class.isAssignableFrom(clazz)) {
          owner = this.m_single;
        } else {
          throw new IllegalArgumentException(//
              "Invalid module class '" + clazz + '\''); //$NON-NLS-1$
        }
      }
    }

    c = ((Constructor<Module>[]) (clazz.getConstructors()));
    if (c != null) {
      try {
        for (final Constructor<Module> cc : c) {
          params = cc.getParameterTypes();
          if ((params == null) || (params.length <= 0)) {
            continue;
          }

          if (params.length == 1) {
            if (params[0].isInstance(owner)) {
              cc.newInstance(owner);
              return;
            }
          }

          if (params.length == 2) {
            if (params[0].isInstance(owner)
                && ((params[1].isAssignableFrom(boolean.class) || //
                params[1].isAssignableFrom(Boolean.class)))) {
              cc.newInstance(owner, Boolean.TRUE);
              return;
            }
            if (params[1].isInstance(owner)
                && ((params[0].isAssignableFrom(boolean.class) || //
                params[0].isAssignableFrom(Boolean.class)))) {
              cc.newInstance(Boolean.TRUE, owner);
              return;
            }
          }

        }
      } catch (final InstantiationException e1) {
        throw new IllegalArgumentException(e1);
      } catch (final IllegalAccessException e2) {
        throw new IllegalArgumentException(e2);
      } catch (final InvocationTargetException e3) {
        throw new IllegalArgumentException(e3);
      }

    }
    throw new IllegalArgumentException("Class '" + clazz + //$NON-NLS-1$
        "' has no matching public constructor.");//$NON-NLS-1$
  }

  /**
   * Apply all modules to the experiment set.
   *
   * @param body
   *          the document body to write the output to
   * @param data
   *          the data
   */
  public final void run(final Body body, final ExperimentSet data) {
    this._call_run(body, data);
  }

  /**
   * Initialize all modules to the experiment set.
   *
   * @param header
   *          the document header to write the output to
   * @param data
   *          the data
   */
  public final void initialize(final Header header,
      final ExperimentSet data) {
    this._call_initialize(header, data);
  }

  /** {@inheritDoc} */
  @Override
  final void _initialize(final Header header, final DataSet<?> data)
      throws IOException {
    this.__initialize(header, ((ExperimentSet) data));
  }

  /**
   * the authors of all the experiments
   *
   * @param data
   *          the experiment data
   * @return the authors
   */
  private static final BibAuthors __allAuthors(final ExperimentSet data) {
    final int s;
    final BibAuthor[] ax;
    ExperimentSetup es;
    BibAuthors auth;
    HashSet<BibAuthor> auths;

    if (data != null) {
      auths = null;
      for (final Experiment ex : data) {
        if (ex != null) {
          es = ex.getSetup();
          if (es != null) {
            auth = es.getAuthors();

            if ((auth != null) && (auth.size() > 0)) {
              if (auths == null) {
                auths = new HashSet<>();
              }

              for (final BibAuthor au : auth) {
                auths.add(au);
              }
            }
          }
        }
      }

      if ((auths != null) && ((s = auths.size()) > 0)) {
        ax = auths.toArray(new BibAuthor[s]);
        Arrays.sort(ax);
        return new BibAuthors(ax);
      }
    }

    return BibAuthors.EMPTY;
  }

  /**
   * Do the initialization
   *
   * @param header
   *          the header
   * @param data
   *          the data
   * @throws IOException
   *           if io fails
   */
  @SuppressWarnings("resource")
  private final void __initialize(final Header header,
      final ExperimentSet data) throws IOException {
    final Document doc;
    final int size;
    final BibAuthors all;
    _Single single;
    _Comparisons compa;
    _Descriptions desc;

    size = data.size();
    if (size <= 0) {
      return;
    }

    doc = header.getDocument();

    // first, we define the names of the algorithms as macros
    for (final MacroDefinition md : (this.m_short.get(data, doc)).values()) {
      md.define(header);
    }
    for (final MacroDefinition md : this.m_long.get(data, doc).values()) {
      md.define(header);
    }

    // now we initialize the child modules
    this._initializeChildren(header, data);

    // we check which kind of information we will produce: singe
    // evaluations
    // or
    // comparisons? And/or will we include the experimental descriptions?
    single = this.findInstance(_Single.class);
    if ((single != null) && (!(single.isActive()))) {
      single = null;
    }
    compa = this.findInstance(_Comparisons.class);
    if ((compa != null) && (!(compa.isActive()))) {
      compa = null;
    }
    desc = this._findInstance(_Descriptions.class);
    if ((desc != null) && (!(desc.isActive()))) {
      desc = null;
    }

    // The title reflects the active modules
    try (Title title = header.title()) {
      if ((compa != null) && (size > 1)) {
        title.write("Comparison Report of "); //$NON-NLS-1$
        title.writeIntInText(size, true);
        title.write(" Algorithms"); //$NON-NLS-1$
      } else {
        if (single != null) {
          title.write("Report on "); //$NON-NLS-1$
          title.writeSequence(//
              new ExperimentSequence(this, data, false, true, title),//
              ESequenceType.AND, false);
        } else {
          if (desc != null) {
            title.write("Experiment Procedure Description");//$NON-NLS-1$
          } else {
            title.write("Report without Contents");//$NON-NLS-1$
          }
        }
      }
    }

    // By default, we use the researchers that conducted the experiments as
    // authors
    all = RootModule.__allAuthors(data);
    header.authors(((all != null) && (all.size() > 0)) ? all
        : new BibAuthors(//
            new BibAuthor("Anomnyma", "Unknownowskaya")//$NON-NLS-1$//$NON-NLS-2$
        ));

    // Produce a short abstract: What information will be in the document.
    // The
    // abstract also contains the version/timestamp of our system, so we
    // can
    // always find out with which version of the software it was created
    try (Summary sum = header.summary()) {
      sum.write("This document provides a report on the ");//$NON-NLS-1$
      sum.writeSequence(
          new ExperimentSequence(this, data, true, true, sum),
          ESequenceType.AND, false);
      sum.writeChar('.');

      if (desc != null) {
        sum.write(//
        " We first provide background information and outline the experimental procedure as well as the evaluation methodology.");//$NON-NLS-1$
      }

      if (single != null) {
        sum.write((desc == null) ? " We first" : " Then we");//$NON-NLS-1$//$NON-NLS-2$

        sum.write(//
        " analyze the performance of the ");//$NON-NLS-1$
        if (size > 1) {
          sum.write(//
          " single algorithms separately");//$NON-NLS-1$
        } else {
          sum.macroInvoke(this.getShortName(data.get(0)));
        }
        if (single.getActiveChildren().size() > 1) {
          sum.write(//
          " from several separate angles");//$NON-NLS-1$
        }
        sum.writeChar('.');
      }

      if (compa != null) {
        sum.write(//
        ((single != null) && (desc != null)) ? " Finally" : //$NON-NLS-1$
            (((single != null) || (desc != null)) ? " Then" : //$NON-NLS-1$
                " In this report"));//$NON-NLS-1$
        sum.write(//
        ", we then compare the algorithms with each other");//$NON-NLS-1$
        if (compa.getActiveChildren().size() > 1) {
          sum.write(//
          " with different statistical approaches");//$NON-NLS-1$
        }
        sum.writeChar('.');
      }

      sum.writeChar(' ');
      sum.write(" The evaluation procedure was carried with the evaluator version ");//$NON-NLS-1$
      try (Emphasize em = sum.emphasize()) {
        em.write(EvaluatorVersion.VERSION.getVersion());
      }
      sum.write(" built on ");//$NON-NLS-1$
      try (Emphasize em = sum.emphasize()) {
        em.write(EvaluatorVersion.VERSION.getBuildDate());
      }
      sum.write(" for ");//$NON-NLS-1$
      sum.write(EvaluatorVersion.VERSION.getTargetPlatform());
      sum.writeChar('.');
    }
  }

  /** {@inheritDoc} */
  @Override
  final void _run(final Element body, final DataSet<?> data)
      throws IOException {
    this._runChildren(body, data);
  }

  /**
   * the macro of the long name of a given experiment
   *
   * @param experiment
   *          the experiment
   * @return the long name for that experiment
   */
  public final MacroDescriptor getLongName(final Experiment experiment) {
    return this.m_long.get(experiment.getOwner(), null).get(//
        experiment.name());
  }

  /**
   * the macro of the short name of a given experiment
   *
   * @param experiment
   *          the experiment
   * @return the long name for that experiment
   */
  public final MacroDescriptor getShortName(final Experiment experiment) {
    return this.m_short.get(experiment.getOwner(), null).get(//
        experiment.name());
  }

  /**
   * Find an instance of the given class in the hierarchical tree of
   * modules
   *
   * @param clazz
   *          the class
   * @return the instance, or {@code null} if none was found
   * @param <T>
   *          the type of the module
   */
  @SuppressWarnings("unchecked")
  public final <T extends Module> T findInstance(
      final Class<? extends T> clazz) {
    if (clazz.isInstance(this)) {
      return ((T) this);
    }
    return this._findInstance(clazz);
  }
}
