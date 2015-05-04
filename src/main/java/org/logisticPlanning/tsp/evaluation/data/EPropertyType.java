package org.logisticPlanning.tsp.evaluation.data;

import java.lang.ref.SoftReference;

/**
 * The storage type of properties. Properties can be
 * {@link #PERMANENTLY_STORED permanently} stored,
 * {@link #TEMPORARILY_STORED temporarily} stored as long as there is
 * enough memory, or {@link #NEVER_STORED not stored} in the internal
 * caches at all and thus, always recomputed when their value is needed.
 */
public enum EPropertyType {

  /**
   * This type is for properties which must be permanently stored in the
   * data set. This makes sense, for example, for labels that point to
   * locations in the output document and need to be accessed later.
   */
  PERMANENTLY_STORED,

  /**
   * Properties of this type my be stored in the data sets but may also be
   * purged in low-memory situations. Once purged, they will simply be
   * re-computed. This is realized by internally referencing them with
   * {@link java.lang.ref.SoftReference soft references} which will be
   * garbage collected when the memory situation warrants it.
   */
  TEMPORARILY_STORED {

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("unchecked")
    final <T> T unpack(final Object o) {
      return ((o != null) ? (((SoftReference<T>) (o)).get()) : null);
    }

    /** {@inheritDoc} */
    @Override
    final Object pack(final Object o) {
      return new SoftReference<>(o);
    }

  },

  /**
   * Properties of this type will never be stored. We would use this
   * property type for properties that either consume a lot of memory or
   * are known to be rarely accessed twice. Storing them is either not
   * feasible or makes no sense. Properties of this type will always be
   * re-computed when accessed.
   */
  NEVER_STORED;

  /**
   * Unpack an object: the method is internally used to unwrap objects by
   * the cache in a
   * {@link org.logisticPlanning.tsp.evaluation.data.DataSet data set}.
   *
   * @param o
   *          the packed object
   * @return the unpacked object
   * @param <T>
   *          the goal type
   */
  @SuppressWarnings("unchecked")
  <T> T unpack(final Object o) {
    return ((T) o);
  }

  /**
   * pack an object: the method is internally used to wrap objects by the
   * cache in a {@link org.logisticPlanning.tsp.evaluation.data.DataSet
   * data set}.
   *
   * @param o
   *          the unpacked object
   * @return the packed object
   */
  Object pack(final Object o) {
    return o;
  }
}
