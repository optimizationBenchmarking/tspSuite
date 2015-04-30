package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The enumeration item element.
 */
public abstract class AbstractItem extends AbstractTextBlock {

  /** the index */
  private final int m_index;

  /**
   * create the document element
   * 
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  AbstractItem(final AbstractList owner) throws IOException {
    super(owner);
    this.m_index = owner.nextIndex();
  }

  /**
   * Get the index of this enumeration item
   * 
   * @return the index of this enumeration item
   */
  public final int getIndex() {
    return this.m_index;
  }

  /** {@inheritDoc} */
  @Override
  public void writeLinebreak() throws IOException {
    super.writeLinebreak();
  }

  /**
   * Get the hierarchy of indexes
   * 
   * @return the hierarchy of indexes
   */
  @SuppressWarnings("resource")
  public final int[] getAllIndexes() {
    final int[] idx;
    int i;
    Element e;

    i = (((AbstractList) (this.m_owner)).getDepth());
    idx = new int[i];
    for (e = this; e != null; e = e.m_owner) {
      if (e instanceof AbstractItem) {
        idx[--i] = ((AbstractItem) e).m_index;
        if (i <= 0) {
          return idx;
        }
      }
    }

    return idx;
  }

  /** {@inheritDoc} */
  @Override
  public AbstractList getOwner() {
    return ((AbstractList) (this.m_owner));
  }
}
