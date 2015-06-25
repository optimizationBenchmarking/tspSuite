package org.logisticPlanning.tsp.evaluation.data;

import java.util.Arrays;
import java.util.HashMap;

import org.logisticPlanning.utils.NamedObject;
import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.text.TextUtils;
import org.logisticPlanning.utils.text.transformations.NormalCharTransformer;

/**
 * <p>
 * A set of things that also supports a cache of property values.
 * </p>
 * <p>
 * Datasets are the base class of the data model used for the experimental
 * evaluation procedure. They are lists as well as sets of data elements. A
 * {@link org.logisticPlanning.tsp.evaluation.data.Run run} is a set of
 * {@link org.logisticPlanning.tsp.benchmarking.objective.DataPoint data
 * points}, {@link org.logisticPlanning.tsp.evaluation.data.RunSet run set}
 * is a set of {@link org.logisticPlanning.tsp.evaluation.data.Run runs},
 * and so on.
 * </p>
 * <p>
 * While holding the results of experiments as static data, DataSets also
 * hold dynamic data: During the evaluation procedure, any form of data may
 * be stored in them in form of
 * {@link org.logisticPlanning.tsp.evaluation.data.Property property
 * values}. There are two ideas behind this concept: On one hand, all
 * {@link org.logisticPlanning.tsp.evaluation.Evaluator evaluator}
 * {@link org.logisticPlanning.tsp.evaluation.modules.spec.Module modules}
 * should be stateless (apart from their configuration), so that they can
 * process multiple experiments in parallel or sequentially. Second, some
 * computations are very processor-intense. Say the selection and
 * transformation of thousands of points for painting a diagram or running
 * of hundreds of statistical tests. Such data may be computed once on
 * demand and stored in the internal cache so that it can be reused when
 * needed again.
 * </p>
 * <p>
 * A data set is a form of soft cache. It can store values
 * {@link org.logisticPlanning.tsp.evaluation.data.EPropertyType#PERMANENTLY_STORED
 * permanently} or
 * {@link org.logisticPlanning.tsp.evaluation.data.EPropertyType#TEMPORARILY_STORED
 * temporarily}. In the former case, a property value will always remain in
 * the cache. In the latter case, the garbage collector may purge the value
 * in low-memory situations. This is accomplished by using
 * {@link java.lang.ref.SoftReference soft references} (in the case of
 * {@link org.logisticPlanning.tsp.evaluation.data.EPropertyType#TEMPORARILY_STORED
 * temporary storage} and &quot;normal&quot; object variables
 * (&quot;hard&quot; references) for
 * {@link org.logisticPlanning.tsp.evaluation.data.EPropertyType#PERMANENTLY_STORED
 * permanently} stored objects in a transparent fashion. If a
 * {@link org.logisticPlanning.tsp.evaluation.data.EPropertyType#TEMPORARILY_STORED
 * temporarily stored} property is purged by the garbage collector, it will
 * simply be re-computed when accessed the next time. This allows us to
 * safe runtime when sufficient memory is available and to still function
 * when we don't have much memory.
 * </p>
 * <p>
 * The property value cache of a data set is thread safe. However, it is
 * not strictly synchronized. During the computation of a property, it is
 * not locked and can still accessed in parallel. This may lead to the
 * computation of the same property value in parallel by multiple threads.
 * This case is unlikely and also has no impact: the computation result
 * finished second will simply be discarded (as both results would be
 * identical by definition). The reason for not locking the cache is that
 * sometimes it may take very long to compute a property value. In
 * multi-thread scenarios, we then still can access other property values
 * and/or compute them in the meantime. Only the exact read/write
 * operations to the internal map are synchronized - and these take little
 * time. No additional synchronization is required.
 * </p>
 *
 * @param <DT>
 *          the type
 */
public class DataSet<DT> extends ArraySetView<DT> implements
    Comparable<Object> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** an internal map for properties */
  private transient HashMap<Property<?, ?>, Object> m_map;

  /**
   * the name of this object
   *
   * @serial the name identifying this data set
   */
  private final String m_name;

  /**
   * the normalized name
   *
   * @serial the name of this data set, without any special or numeric
   *         characters
   */
  private final String m_normalName;

  /**
   * instantiate
   *
   * @param name
   *          the name of the set
   * @param data
   *          the data of the set
   */
  DataSet(final String name, final DT[] data) {
    super(data);

    final String a, b;

    a = TextUtils.prepare(name);
    if (a == null) {
      throw new IllegalArgumentException(//
          "Name of set must not be empty."); //$NON-NLS-1$
    }
    b = NormalCharTransformer.INSTANCE.transform(a);
    if (b == null) {
      throw new IllegalArgumentException(//
          "Name must have non-trivial normalized representation."); //$NON-NLS-1$
    }

    this.m_name = a;
    this.m_normalName = b;
  }

  /**
   * Setup the data set: This method will throw an
   * {@link java.lang.IllegalArgumentException} if it detects an error.
   *
   * @param data
   *          the data set
   * @param owner
   *          the owner
   * @param <T>
   *          the set type
   * @param <OT>
   *          the owner type
   * @param <X>
   *          the extension type
   */
  static final <T, OT, X extends _OwnedSet<T, OT>> void _setup(
      final X[] data, final OT owner) {
    String n;
    _OwnedSet<T, OT> s;
    int i, j;

    Arrays.sort(data);

    for (i = data.length; (--i) >= 0;) {
      s = data[i];
      s.m_owner = owner;
      s.m_index = i;
      n = s.name();
      if (n == null) {
        throw new IllegalArgumentException(//
            "Name must not be null."); //$NON-NLS-1$
      }
      for (j = i; (--j) >= 0;) {
        if (n.equalsIgnoreCase(data[j].name())) {
          throw new IllegalArgumentException(//
              "No two names must be alike, but found '" + n + //$NON-NLS-1$
                  "' and '" + data[j].name() + '\'');//$NON-NLS-1$
        }
      }
    }
  }

  /**
   * get the name of this object
   *
   * @return the name of this object
   */
  public final String name() {
    return this.m_name;
  }

  /**
   * get the normalized name to be used in file names and such alike
   *
   * @return the normalized name to be used in file names and such alike
   */
  public final String getNormalizedName() {
    return this.m_normalName;
  }

  /**
   * Find the data element fitting to the given key
   *
   * @param key
   *          the key element
   * @return the data element, if one fits
   */
  public final DT find(final Object key) {
    final int i;

    i = Arrays.binarySearch(this.m_data, key);
    if (i >= 0) {
      return this.m_data[i];
    }
    return null;
  }

  /**
   * Compare to a given object
   *
   * @param o
   *          the object
   * @return the comparison result
   */
  int _compareTo(final Object o) {
    int r;
    if (o instanceof String) {
      r = this.m_name.compareTo((String) o);
      if (r != 0) {
        return r;
      }
    }
    if (o instanceof DataSet) {
      r = this.m_name.compareTo(((DataSet<?>) o).m_name);
      if (r != 0) {
        return r;
      }
    }
    if (o instanceof NamedObject) {
      r = this.m_name.compareTo(((NamedObject) o).name());
      if (r != 0) {
        return r;
      }
    }
    return 0;
  }

  /** {@inheritDoc} */
  @Override
  public final int compareTo(final Object o) {
    if (o == this) {
      return 0;
    }
    if (o == null) {
      return (-1);
    }
    return this._compareTo(o);
  }

  /**
   * get the property stored under the given key
   *
   * @param property
   *          the property
   * @param doc
   *          the document object into which the report should be written
   * @return the property
   * @param <XDT>
   *          the data set type
   * @param <T>
   *          the property type
   */
  @SuppressWarnings("unchecked")
  final <XDT extends DataSet<?>, T> T _getProperty(
      final Property<XDT, T> property, final Document doc) {
    final Object sync;
    final boolean store;
    final EPropertyType type;
    T computed, ret;
    Object old;

    sync = this.m_data;
    store = ((type = property.m_type).ordinal() <= //
    EPropertyType.TEMPORARILY_STORED.ordinal());

    if (store) {
      // if the property can be stored, we first need to check if it has
      // already been computed and stored. We do this in a synchronized
      // way.
      synchronized (sync) {
        if (this.m_map != null) {
          old = this.m_map.get(property);
          if (old != null) {
            ret = type.unpack(old);
            if (ret != null) {
              return ret;
            }
            // although ret is a softref that points nowhere, we
            // don't need to
            // delete it, since it will be overwritten soon
          }
        }
      }
    }

    // ok, the property either never is stored or needs to be computed
    // every
    // time
    computed = property.compute(((XDT) this), doc);
    if (computed == null) {
      throw new IllegalStateException(//
          "Computed property must not be null."); //$NON-NLS-1$
    }

    if (store) {
      // Synchronized again: check if property has been stored in the mean
      // time, if not, set it and return it
      synchronized (sync) {
        if (this.m_map != null) {
          old = this.m_map.get(property);
          if (old != null) {
            ret = type.unpack(old);
            if (ret != null) {
              // while we were computing, someone else put a value
              // for the
              // property into the map....and this value was
              // already purged by
              // the gc
              return ret;
            }
            // although ret is a softref that points nowhere, we
            // don't need to
            // delete it, since it will be overwritten soon
          }
        } else {
          this.m_map = new HashMap<>();
        }
        this.m_map.put(property, type.pack(computed));
      }
    }

    return computed;
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return (((this.name() + '[') + this.m_data.length) + ']');
  }
}
