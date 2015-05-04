package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The base class for lists element.
 */
public abstract class AbstractList extends Element {

  /** we are in an item */
  private static final int STATE_IN_ITEM = (Element.STATE_NOTHING + 1);

  /** we are after an item */
  private static final int STATE_AFTER_ITEM = (AbstractList.STATE_IN_ITEM + 1);

  /** the index of the current item */
  private int m_index;

  /** the list depth */
  private int m_depth;

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  AbstractList(final AbstractTextBlock owner) throws IOException {
    super(owner);

    findDepth: {
      for (Element e = owner; e != null; e = e.m_owner) {
        if (e instanceof AbstractList) {
          this.m_depth = (((AbstractList) e).m_depth + 1);
          break findDepth;
        }
      }
      this.m_depth = 1;
    }
  }

  /**
   * get the next index
   *
   * @return the next index
   */
  final int nextIndex() {
    return (++this.m_index);
  }

  /**
   * Get the nesting level of this list, i.e., the number of lists it is
   * contained in plus 1
   *
   * @return the nesting level
   */
  public final int getDepth() {
    return this.m_depth;
  }

  /**
   * Create a list item
   *
   * @return the list item
   * @throws IOException
   *           if io fails
   * @throws IllegalStateException
   *           if creating an item is not permitted now
   */
  public abstract AbstractItem item() throws IOException,
      IllegalStateException;

  /** {@inheritDoc} */
  @Override
  public AbstractTextBlock getOwner() {
    return ((AbstractTextBlock) (this.m_owner));
  }

  /**
   * begin an item
   *
   * @param item
   *          the item
   */
  final void beginItem(final AbstractItem item) {
    if ((this.m_state != Element.STATE_NOTHING)
        && (this.m_state != AbstractList.STATE_AFTER_ITEM)) {
      throw new IllegalStateException(//
          "Cannot create list item here."); //$NON-NLS-1$
    }
    this.m_state = AbstractList.STATE_IN_ITEM;
  }

  /**
   * end an item
   *
   * @param item
   *          the item
   */
  final void endItem(final AbstractItem item) {
    if (this.m_state != AbstractList.STATE_IN_ITEM) {
      throw new IllegalStateException(//
          "List item can only end after it has begun."); //$NON-NLS-1$
    }
    this.m_state = AbstractList.STATE_AFTER_ITEM;
  }

  /**
   * check if we should be closing
   *
   * @return {@code true} if some closing action needs to be carried out,
   *         {@code false} otherwise
   */
  final boolean checkClose() {
    if (this.checkDead(AbstractList.STATE_AFTER_ITEM)) {
      if (this.m_index <= 0) {
        throw new IllegalStateException(//
            "List must have at least one item."); //$NON-NLS-1$
      }
      return true;
    }
    return false;
  }
}
