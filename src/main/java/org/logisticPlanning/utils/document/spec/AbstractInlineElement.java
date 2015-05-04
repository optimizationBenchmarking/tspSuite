package org.logisticPlanning.utils.document.spec;

import java.io.IOException;
import java.text.NumberFormat;

/**
 * The basic inline element.
 */
public abstract class AbstractInlineElement extends Element {

  /**
   * create the inline element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  AbstractInlineElement(final Element owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  public void writeBoolean(final boolean v) throws IOException {
    super.writeBoolean(v);
  }

  /** {@inheritDoc} */
  @Override
  public void writeInt(final int v) throws IOException {
    super.writeInt(v);
  }

  /** {@inheritDoc} */
  @Override
  public void writeInt(final int v, final NumberFormat format)
      throws IOException {
    super.writeInt(v, format);
  }

  /** {@inheritDoc} */
  @Override
  public void writeLong(final long v, final NumberFormat format)
      throws IOException {
    super.writeLong(v, format);
  }

  /** {@inheritDoc} */
  @Override
  public void writeLong(final long v) throws IOException {
    super.writeLong(v);
  }

  /** {@inheritDoc} */
  @Override
  public void writeDouble(final double v) throws IOException {
    super.writeDouble(v);
  }

  /** {@inheritDoc} */
  @Override
  public void writeDouble(final double v, final NumberFormat format)
      throws IOException {
    super.writeDouble(v, format);
  }

  /** {@inheritDoc} */
  @Override
  protected MacroInvocation macroInvocationCreate(
      final AbstractInlineElement owner, final MacroDescriptor desc)
          throws IOException {
    return super.macroInvocationCreate(owner, desc);
  }

  /** {@inheritDoc} */
  @Override
  protected void macroInvocationBegin(final MacroInvocation h)
      throws IOException, IllegalStateException {
    super.macroInvocationBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void macroInvocationEnd(final MacroInvocation h)
      throws IOException, IllegalStateException {
    super.macroInvocationEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public MacroInvocation macroInvocation(final MacroDescriptor desc)
      throws IOException, IllegalStateException {
    return super.macroInvocation(desc);
  }

  /**
   * Invoke a parameter-less macro
   *
   * @param desc
   *          the macro descriptor
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if the state does not permit invoking the macro
   */
  public void macroInvoke(final MacroDescriptor desc) throws IOException,
  IllegalStateException {
    try (MacroInvocation mop = this.macroInvocation(desc)) {
      //
    }
  }
}
