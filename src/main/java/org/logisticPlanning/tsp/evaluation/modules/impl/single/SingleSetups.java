package org.logisticPlanning.tsp.evaluation.modules.impl.single;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSetup;
import org.logisticPlanning.tsp.evaluation.data.SetupItem;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.tsp.evaluation.modules.spec.SingleModule;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.document.spec.AbstractTextBlock;
import org.logisticPlanning.utils.document.spec.Emphasize;
import org.logisticPlanning.utils.document.spec.Enumeration;
import org.logisticPlanning.utils.document.spec.EnumerationItem;
import org.logisticPlanning.utils.document.spec.Label;
import org.logisticPlanning.utils.document.spec.MacroDescriptor;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;
import org.logisticPlanning.utils.document.spec.Table;
import org.logisticPlanning.utils.document.spec.TableBody;
import org.logisticPlanning.utils.document.spec.TableBodyCell;
import org.logisticPlanning.utils.document.spec.TableBodyRow;
import org.logisticPlanning.utils.document.spec.TableCaption;
import org.logisticPlanning.utils.document.spec.TableCellDef;
import org.logisticPlanning.utils.document.spec.TableHeader;
import org.logisticPlanning.utils.document.spec.TableHeaderCell;
import org.logisticPlanning.utils.document.spec.TableHeaderRow;
import org.logisticPlanning.utils.text.TextUtils;

/**
 * This evaluator will print the setup values of a given algorithm.
 */
public class SingleSetups extends SingleModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the single setups: {@value} */
  private static final String NAME = "singleSetups";//$NON-NLS-1$

  /** the print the algorithm setups: {@value} */
  public static final String PARAM_PRINT_ALGO_SETUP = SingleSetups.NAME
      + "PrintAlgoSetups";//$NON-NLS-1$
  /** the print the initializer setups: {@value} */
  public static final String PARAM_PRINT_INIT_SETUP = SingleSetups.NAME
      + "PrintInitSetups";//$NON-NLS-1$
  /** the print the system setups: {@value} */
  public static final String PARAM_PRINT_SYSTEM_SETUP = SingleSetups.NAME
      + "PrintSystemSetups";//$NON-NLS-1$

  /** the table cell definition */
  private static final TableCellDef[] TABLE_DEF = {
      TableCellDef.VERTICAL_LINE,
      TableCellDef.make(TableCellDef.ALIGN_LEFT, 0.4),
      TableCellDef.VERTICAL_LINE,
      TableCellDef.make(TableCellDef.ALIGN_LEFT, 0.5),
      TableCellDef.VERTICAL_LINE };

  /** do we print the algorithm parameters? */
  private boolean m_printAlgoParams;
  /** do we print the machine parameters? */
  private boolean m_printMachineParams;
  /** do we print the initializer parameters? */
  private boolean m_printInitParams;

  /**
   * create!
   *
   * @param owner
   *          the macro's owner
   * @param isActive
   *          should this module be active?
   */
  public SingleSetups(final Module owner, final boolean isActive) {
    super(SingleSetups.NAME, owner, isActive);
    this.m_printAlgoParams = true;
    this.m_printMachineParams = false;
    this.m_printInitParams = true;
  }

  /** {@inheritDoc} */
  @Override
  protected boolean hasContent(final Experiment data) {
    final ArrayList<SetupItem> list;
    final ExperimentSetup es;

    es = data.getSetup();
    if (es == null) {
      return false;
    }

    if (this.m_printAlgoParams || this.m_printInitParams
        || this.m_printMachineParams) {
      list = new ArrayList<>();

      if (this.m_printAlgoParams) {
        list.addAll(es.getAlgoSetup());
      }
      if (this.m_printInitParams) {
        list.addAll(es.getInitSetup());
      }
      if (this.m_printMachineParams) {
        list.addAll(es.getSysSetup());
      }

      SingleSetups.__cleanse(list);
      return (!(list.isEmpty()));
    }
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    this.m_printAlgoParams = config.getBoolean(
        SingleSetups.PARAM_PRINT_ALGO_SETUP, this.m_printAlgoParams);
    this.m_printInitParams = config.getBoolean(
        SingleSetups.PARAM_PRINT_INIT_SETUP, this.m_printInitParams);
    this.m_printMachineParams = config.getBoolean(
        SingleSetups.PARAM_PRINT_SYSTEM_SETUP, this.m_printMachineParams);

    this.setActive(this.isActive()
        && (this.m_printAlgoParams || this.m_printInitParams || this.m_printMachineParams));

    super.configure(config);
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);
    Configurable.printKey(SingleSetups.PARAM_PRINT_ALGO_SETUP, ps);
    ps.println(this.m_printAlgoParams);
    Configurable.printKey(SingleSetups.PARAM_PRINT_INIT_SETUP, ps);
    ps.println(this.m_printInitParams);
    Configurable.printKey(SingleSetups.PARAM_PRINT_SYSTEM_SETUP, ps);
    ps.println(this.m_printMachineParams);
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);
    Configurable.printKey(SingleSetups.PARAM_PRINT_ALGO_SETUP, ps);
    ps.println("print the algorithm parameters?"); //$NON-NLS-1$
    Configurable.printKey(SingleSetups.PARAM_PRINT_INIT_SETUP, ps);
    ps.println("print the initializer parameters?"); //$NON-NLS-1$
    Configurable.printKey(SingleSetups.PARAM_PRINT_SYSTEM_SETUP, ps);
    ps.println("print the system/machine parameters?"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionTitle(final SectionTitle title,
      final Experiment data) throws IOException {
    title.write("Setup of "); //$NON-NLS-1$
    title.macroInvoke(this.getRoot().getShortName(data));
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionBody(final SectionBody body,
      final Experiment data) throws IOException {
    final MacroDescriptor sname, lname;
    final ExperimentSetup es;
    final String initName;
    final Logger log;
    Label algoSetupLabel, initSetupLabel, sysSetupLabel;
    ArrayList<SetupItem> list;
    String pfx, pfx1, pfx2;

    es = data.getSetup();
    if (es == null) {
      pfx = ("No setup data found for run set " + data.name() + '!');//$NON-NLS-1$
      body.write(pfx);
      log = this.getLogger();
      if ((log != null) && (log.isLoggable(Level.WARNING))) {
        log.warning(pfx);
      }
      return;
    }

    body.write("In this section, we list the setup of the "); //$NON-NLS-1$
    sname = this.getRoot().getShortName(data);
    body.macroInvoke(sname);
    body.write(" (");//$NON-NLS-1$
    lname = this.getRoot().getLongName(data);
    body.macroInvoke(lname);
    body.write(")."); //$NON-NLS-1$

    algoSetupLabel = initSetupLabel = sysSetupLabel = null;
    list = new ArrayList<>();

    if (this.m_printAlgoParams) {
      if (list.addAll(es.getAlgoSetup())) {
        SingleSetups.__cleanse(list);
        if (list.size() > 0) {
          pfx = "The setup parameters of the ";//$NON-NLS-1$
          try (Table table = body.table(Label.AUTO_LABEL,//
              (pfx + sname.name() + " (" + lname.name() + ")."), //$NON-NLS-1$ //$NON-NLS-2$
              false, SingleSetups.TABLE_DEF)) {

            try (TableCaption capn = table.tableCaption()) {

              capn.write(pfx);
              capn.macroInvoke(sname);
              capn.write(" ("); //$NON-NLS-1$
              capn.macroInvoke(lname);
              capn.write(")."); //$NON-NLS-1$
            }
            SingleSetups.__makeTable(table, list.iterator());
            algoSetupLabel = table.getLabel();
          }
        }
      }
    }

    initName = es.getInitName();
    if (this.m_printInitParams) {
      list.clear();
      if (list.addAll(es.getInitSetup())) {
        SingleSetups.__cleanse(list);
        if (list.size() > 0) {
          pfx1 = "The setup parameters of the deterministic initialization procedure ";//$NON-NLS-1$
          pfx2 = " applied before algorithm ";//$NON-NLS-1$
          try (Table table = body
              .table(
                  Label.AUTO_LABEL,//
                  (pfx1 + initName + pfx2 + sname.name()
                      + " (" + lname.name() + ")."), //$NON-NLS-1$ //$NON-NLS-2$
                  false, SingleSetups.TABLE_DEF)) {

            try (TableCaption capn = table.tableCaption()) {

              capn.write(pfx1);
              try (Emphasize emph = capn.emphasize()) {
                emph.write(initName);
              }
              capn.write(pfx2);
              capn.macroInvoke(sname);
              capn.write(" ("); //$NON-NLS-1$
              capn.macroInvoke(lname);
              capn.write(")."); //$NON-NLS-1$
            }
            SingleSetups.__makeTable(table, list.iterator());
            initSetupLabel = table.getLabel();
          }
        }
      }
    }

    if (this.m_printMachineParams) {
      list.clear();
      if (list.addAll(es.getSysSetup())) {
        SingleSetups.__cleanse(list);
        if (list.size() > 0) {
          pfx = "The configuration of the system where the ";//$NON-NLS-1$
          try (Table table = body.table(Label.AUTO_LABEL,//
              (pfx + sname.name() + " (" + lname.name() + ") was run."), //$NON-NLS-1$ //$NON-NLS-2$
              false, SingleSetups.TABLE_DEF)) {

            try (TableCaption capn = table.tableCaption()) {

              capn.write(pfx);
              capn.macroInvoke(sname);
              capn.write(" ("); //$NON-NLS-1$
              capn.macroInvoke(lname);
              capn.write(" was run)."); //$NON-NLS-1$
            }
            SingleSetups.__makeTable(table, list.iterator());
            sysSetupLabel = table.getLabel();
          }
        }
      }
    }

    if (algoSetupLabel != null) {
      body.write(" The configuration of the "); //$NON-NLS-1$
      body.macroInvoke(sname);
      if (initSetupLabel != null) {
        body.write(" itself "); //$NON-NLS-1$
      }
      body.write(" can be found in "); //$NON-NLS-1$
      body.reference(algoSetupLabel);
      if (initSetupLabel != null) {
        body.write(" and the "); //$NON-NLS-1$
      }
    } else {
      if (initSetupLabel != null) {
        body.write(" The "); //$NON-NLS-1$
      }
    }

    if (initSetupLabel != null) {
      body.write("setup of the deterministic initialization procedure ");//$NON-NLS-1$
      try (Emphasize emph = body.emphasize()) {
        emph.write(es.getInitName());
      }
      body.write(" executed before it is given in "); //$NON-NLS-1$
      body.reference(initSetupLabel);
    }

    if ((algoSetupLabel != null) || (initSetupLabel != null)) {
      body.writeChar('.');
    }

    if ((initSetupLabel == null) && (algoSetupLabel != null)
        && (initName != null)) {
      body.write(" The ");//$NON-NLS-1$
      body.macroInvoke(sname);
      body.write(" was initialized with the ");//$NON-NLS-1$
      body.write(initName);
      body.write(//
      ", but this initialization algorithm does not have any specific own setup parameters.");//$NON-NLS-1$
    }

    if (sysSetupLabel != null) {
      body.writeChar(' ');
      body.write(//
      "Information about the system which was used for the experiments is given in "); //$NON-NLS-1$
      body.reference(sysSetupLabel);
      body.writeChar('.');
    }

    super.writeSectionBody(body, data);
  }

  /**
   * Make a table for the given setup items
   *
   * @param table
   *          the table
   * @param iterator
   *          the iterator
   * @throws IOException
   *           if io fails
   */
  private static final void __makeTable(final Table table,
      final Iterator<SetupItem> iterator) throws IOException {

    SetupItem it;
    String s;

    try (TableHeader header = table.tableHeader()) {
      try (TableHeaderRow thr = header.row()) {
        try (TableHeaderCell thc = thr.cell()) {
          thc.write("Parameter");//$NON-NLS-1$
        }
        try (TableHeaderCell thc = thr.cell()) {
          thc.write("Value");//$NON-NLS-1$
        }
      }
    }

    try (TableBody tbody = table.tableBody()) {

      while (iterator.hasNext()) {
        it = iterator.next();
        if (it == null) {
          continue;
        }
        s = it.getKey();
        if (s == null) {
          continue;
        }

        if (s.startsWith(ObjectiveFunction.ENV_VAR_PREFIX)) {
          s = TextUtils.prepare(s.substring(//
              ObjectiveFunction.ENV_VAR_PREFIX.length()));
        } else {
          if (s.startsWith(ObjectiveFunction.SYS_PROP_PREFIX)) {
            s = TextUtils.prepare(s.substring(//
                ObjectiveFunction.SYS_PROP_PREFIX.length()));
          }
        }

        try (TableBodyRow tbr = tbody.row()) {
          try (TableBodyCell tbc = tbr.cell()) {
            tbc.writeHyphenated(s);
          }
          try (TableBodyCell tbc = tbr.cell()) {
            SingleSetups.printValue(it.getValue(), tbc);
          }
        }
      }
    }
  }

  /**
   * Print a configuration value
   *
   * @param value
   *          the value
   * @param out
   *          the writer
   * @throws IOException
   *           if io fails
   */
  private static final void printValue(final String value,
      final AbstractTextBlock out) throws IOException {
    long l;
    double d;
    final String s;
    String v;
    final int len;
    int start, i, depth;

    s = TextUtils.prepare(value);
    if (s == null) {
      out.write((String) null);
      return;
    }

    testLong: {
      try {
        l = TextUtils.parseLong(s);
      } catch (final Throwable tt) {
        break testLong;
      }

      out.writeLong(l);
      return;
    }

    testDouble: {
      try {
        d = TextUtils.parseDouble(s);
      } catch (final Throwable t) {
        break testDouble;
      }
      out.writeDouble(d);
      return;
    }

    len = s.length() - 1;
    if ((s.charAt(0) == '[') && (s.charAt(len) == ']')) {
      if (s.indexOf(',') > 0) {

        try (Enumeration enu = out.enumeration()) {
          depth = 0;
          start = 1;
          outer: for (i = 1; i < len; i++) {
            switch (s.charAt(i)) {
              case ',': {
                if (depth <= 0) {
                  v = TextUtils.prepare(s.substring(start, i));
                  if (v != null) {
                    try (EnumerationItem ei = enu.item()) {
                      SingleSetups.printValue(v, ei);
                    }
                  }
                  start = (i + 1);
                }
                break;
              }
              case '[': {
                depth++;
                break;
              }
              case ']': {
                depth--;
                break;
              }
              default: {
                continue outer;
              }
            }
          }

          v = TextUtils.prepare(s.substring(start, len));
          if (v != null) {
            try (EnumerationItem ei = enu.item()) {
              SingleSetups.printValue(v, ei);
            }
          }

        }

        return;
      }

      v = TextUtils.prepare(s.substring(1, (len - 1)));
    } else {
      v = s;
    }

    out.writeHyphenated(v);
  }

  /**
   * cleanse the list
   *
   * @param list
   *          the list to cleanse
   */
  private static final void __cleanse(final ArrayList<SetupItem> list) {
    String key;
    int i;

    outer: for (i = list.size(); (--i) >= 0;) {
      key = list.get(i).getKey();
      checker: {
        if (key == null) {
          break checker;
        }
        if (key.equalsIgnoreCase(ObjectiveFunction.INITIALIZATION_DES)) {
          break checker;
        }
        if (key.equalsIgnoreCase(ObjectiveFunction.INITIALIZATION_FES)) {
          break checker;
        }
        if (key
            .equalsIgnoreCase(ObjectiveFunction.INITIALIZATION_RAND_SEED)) {
          break checker;
        }
        if (key.equalsIgnoreCase(ObjectiveFunction.INITIALIZATION_RUNTIME)) {
          break checker;
        }
        if (key
            .equalsIgnoreCase(ObjectiveFunction.INITIALIZATION_START_TIME)) {
          break checker;
        }
        if (key
            .equalsIgnoreCase(ObjectiveFunction.INITIALIZATION_END_TIME)) {
          break checker;
        }
        if (key.equalsIgnoreCase(ObjectiveFunction.INITIALIZATION_F)) {
          break checker;
        }
        if (key.equalsIgnoreCase(ObjectiveFunction.FREE_MEMORY)) {
          break checker;
        }
        if (key.equalsIgnoreCase(ObjectiveFunction.ALGORITHM_NAME)) {
          break checker;
        }
        if (key.equalsIgnoreCase(ObjectiveFunction.INITIALIZER_NAME)) {
          break checker;
        }
        if (key
            .equalsIgnoreCase(ObjectiveFunction.RUNTIME_NORMALIZATION_FACTOR)) {
          break checker;
        }
        if (key.equalsIgnoreCase(ObjectiveFunction.ALGORITHM_CLASS)) {
          break checker;
        }
        if (key.equalsIgnoreCase(ObjectiveFunction.INITIALIZER_CLASS)) {
          break checker;
        }
        continue outer;
      }
      list.remove(i);
    }
  }
}
