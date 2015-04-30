package test.junit.org.logisticPlanning.utils.text;

import org.logisticPlanning.utils.text.transformations.LookupCharTransformer;

/**
 * The character transformation class for testing purposes.
 */
public final class _TestCharTransformer extends LookupCharTransformer {

  /** the character transformer */
  public static final _TestCharTransformer INSTANCE = new _TestCharTransformer();

  /** instantiate */
  private _TestCharTransformer() {
    super(null, "_TestCharTransformer.transform"); //$NON-NLS-1$
  }

}
