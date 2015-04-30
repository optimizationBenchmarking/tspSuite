package org.logisticPlanning.tsp.evaluation.data;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.logisticPlanning.tsp.benchmarking.objective.CreatorInfo;
import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.document.spec.bib.BibAuthor;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;
import org.logisticPlanning.utils.text.TextUtils;

/**
 * <p>
 * The setup of an experiment, i.e., the values stored in the log files
 * regarding the algorithm's parameters etc. This includes
 * </p>
 * <ol>
 * <li>the {@link #getAlgoSetup() setup} of the algorithm,</li>
 * <li>the {@link #getInitSetup() setup} of the deterministic
 * initialization method (if any),</li>
 * <li>the {@link #getSysSetup() system configuration} of the computer
 * where the experiments were ran, and</li>
 * <li>the {@link #getCreator() creator information}, i.e., the data of the
 * person who ran the experiments.</li>
 * </ol>
 */
public final class ExperimentSetup {

  /** the algorithm name */
  private final String m_algoName;

  /** the initialization algorithm name */
  private final String m_initName;

  /** the setup name */
  private final String m_setupName;

  /** the authors */
  private final BibAuthors m_authors;

  /** the algo setup */
  private final ArraySetView<SetupItem> m_algo;

  /** the init setup */
  private final ArraySetView<SetupItem> m_init;

  /** the system setup */
  private final ArraySetView<SetupItem> m_system;

  /** the creator information */
  private final ArraySetView<SetupItem> m_creator;

  /**
   * create an experiment setup
   * 
   * @param algoName
   *          the algorithm
   * @param initName
   *          the initializer
   * @param algo
   *          the algorithm setup
   * @param init
   *          the initialization setup
   * @param sys
   *          the system setup
   * @param creator
   *          the creator information
   */
  public ExperimentSetup(final String algoName, final String initName,
      final Collection<SetupItem> algo, final Collection<SetupItem> init,
      final Collection<SetupItem> sys, final Collection<SetupItem> creator) {
    super();

    int i;

    this.m_algoName = TextUtils.prepare(algoName);
    this.m_initName = TextUtils.prepare(initName);

    this.m_setupName = ((this.m_initName != null) ? (this.m_algoName
        + " initialized with the " + this.m_initName) : this.m_algoName); //$NON-NLS-1$

    this.m_algo = ArraySetView.makeArraySetView(
        (((algo != null) && ((i = algo.size()) > 0)) ? algo
            .toArray(new SetupItem[i]) : null), true);

    this.m_system = ArraySetView.makeArraySetView(
        (((sys != null) && ((i = sys.size()) > 0)) ? sys
            .toArray(new SetupItem[i]) : null), true);

    this.m_init = ArraySetView.makeArraySetView(//
        (((init != null) && ((i = init.size()) > 0)) ? init
            .toArray(new SetupItem[i]) : null), true);

    this.m_creator = ArraySetView.makeArraySetView(//
        (((creator != null) && ((i = creator.size()) > 0)) ? creator
            .toArray(new SetupItem[i]) : null), true);

    this.m_authors = this.__findAuthors();
  }

  /**
   * Get the authors of the algorithm and experiment, if known.
   * 
   * @return the authors of the algorithm and experiment, if known (
   *         {@link org.logisticPlanning.utils.document.spec.bib.BibAuthors#EMPTY}
   *         if no author information could be detected)
   */
  public final BibAuthors getAuthors() {
    return this.m_authors;
  }

  /**
   * get the algorithm name
   * 
   * @return the algorithm name
   */
  public final String getAlgorithmName() {
    return this.m_algoName;
  }

  /**
   * get the initializer name
   * 
   * @return the initializer name
   */
  public final String getInitName() {
    return this.m_initName;
  }

  /**
   * get the setup name
   * 
   * @return the setup name
   */
  public final String getSetupName() {
    return this.m_setupName;
  }

  /**
   * get the setup of the algorithm
   * 
   * @return the setup of the algorithm
   */
  public final ArraySetView<SetupItem> getAlgoSetup() {
    return this.m_algo;
  }

  /**
   * get the setup of the initialization algorithm
   * 
   * @return the setup of the initialization algorithm
   */
  public final ArraySetView<SetupItem> getInitSetup() {
    return this.m_init;
  }

  /**
   * get the creator information
   * 
   * @return the creator information
   */
  public final ArraySetView<SetupItem> getCreator() {
    return this.m_creator;
  }

  /**
   * get the setup of the system where the algorithms where running
   * 
   * @return the setup of the system where the algorithms where running
   */
  public final ArraySetView<SetupItem> getSysSetup() {
    return this.m_system;
  }

  /**
   * find the authors
   * 
   * @return the authors
   */
  private final BibAuthors __findAuthors() {
    final BibAuthor[] lst;
    int s;
    HashSet<BibAuthor> dest;

    dest = null;

    if (this.m_creator != null) {
      for (final SetupItem si : this.m_creator) {
        if (CreatorInfo.PARAM_RESEARCHER_NAME
            .equalsIgnoreCase(si.getKey())) {
          dest = ExperimentSetup.__authFromString(si.getValue(), dest);
        }
      }
    }

    if ((dest == null) || ((s = dest.size()) <= 0)) {
      return BibAuthors.EMPTY;
    }

    lst = dest.toArray(new BibAuthor[s]);
    Arrays.sort(lst);
    return new BibAuthors(lst);
  }

  /**
   * Get an author name from a given string
   * 
   * @param s
   *          the string
   * @param auth
   *          the author list to append to
   * @return the authors
   */
  private static final HashSet<BibAuthor> __authFromString(final String s,
      final HashSet<BibAuthor> auth) {
    HashSet<BibAuthor> al;
    BibAuthor a;
    int i, j;

    if (s == null) {
      return auth;
    }

    j = 0;
    al = auth;
    for (;;) {
      i = s.indexOf(',', j);
      if (i > 0) {
        a = ExperimentSetup.__singleAuth(s.substring(j, i));
      } else {
        a = ExperimentSetup.__singleAuth(s.substring(j));
      }
      if (a != null) {
        if (al == null) {
          al = new HashSet<>();
        }
        al.add(a);
      }
      if (i < 0) {
        return al;
      }
      j = (i + 1);
    }
  }

  /**
   * a single bibliography author
   * 
   * @param s
   *          the string
   * @return the author
   */
  private static final BibAuthor __singleAuth(final String s) {
    final String fn, ln;
    int i;

    i = s.lastIndexOf(' ');
    if (i > 0) {
      fn = TextUtils.prepare(s.substring(0, i));
      if (fn != null) {
        ln = TextUtils.prepare(s.substring(i + 1));
        if (ln != null) {
          return new BibAuthor(fn, ln);
        }
      }
    }

    return null;
  }

  // /**
  // * add the items to the destination
  // *
  // * @param items
  // * the items
  // * @param dest
  // * the destination
  // * @return {@code true} if at least one item was added, {@code false}
  // * otherwise
  // */
  // private static final boolean __add(final SetupItem[] items,
  // final Collection<? super SetupItem> dest) {
  // boolean ret;
  //
  // ret = false;
  // if (items != null) {
  // for (final SetupItem s : items) {
  // if (s.getKey() != null) {
  // if (dest.add(s)) {
  // ret = true;
  // }
  // }
  // }
  // }
  // return ret;
  // }

}
