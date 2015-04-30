package org.logisticPlanning.utils.text.transformations;

import java.text.Normalizer;

/**
 * the internal class we use to load the text normalization data
 */
public final class NormalCharTransformer extends LookupCharTransformer {

  /** the normalizing character transformer */
  public static final NormalCharTransformer INSTANCE = new NormalCharTransformer();

  /** instantiate */
  private NormalCharTransformer() {
    super(Normalizer.Form.NFKC, "normalCharTransformationMap.transform"); //$NON-NLS-1$
  }

}
