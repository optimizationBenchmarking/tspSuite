package org.logisticPlanning.tsp.evaluation.data;

/**
 * a set of things.
 * 
 * @param <T>
 *          the type
 * @param <OT>
 *          the owner type
 */
class _OwnedSet<T, OT> extends DataSet<T> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the owner */
  OT m_owner;

  /** the index */
  int m_index;

  /**
   * instantiate
   * 
   * @param name
   *          the name of the set
   * @param data
   *          the data of the set
   */
  _OwnedSet(final String name, final T[] data) {
    super(name, data);
    this.m_index = (-1);
  }

  /**
   * Get the index of this data element in the owner
   * 
   * @return the index of this data element in the owner
   */
  public final int getIndex() {
    return this.m_index;
  }

  /**
   * Get the owning object
   * 
   * @return the owning object
   */
  public final OT getOwner() {
    return this.m_owner;
  }

  /** {@inheritDoc} */
  @Override
  int _compareTo(final Object o) {
    int i;
    if ((this.m_index >= 0) && (o instanceof _OwnedSet<?, ?>)) {
      i = ((_OwnedSet<?, ?>) o).m_index;
      if (i >= 0) {
        i = Integer.compare(this.m_index, i);
        if (i != 0) {
          return i;
        }
      }
    }
    return super._compareTo(o);
  }
}
