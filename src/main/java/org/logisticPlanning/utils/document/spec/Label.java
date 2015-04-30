package org.logisticPlanning.utils.document.spec;

/**
 * the base class for references
 */
public class Label {

  /** this label means that labels will automatically be allocated */
  public static final Label AUTO_LABEL = new Label();

  /** create the reference */
  Label() {
    super();
  }
}
