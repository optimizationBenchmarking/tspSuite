package org.logisticPlanning.utils.utils.comparison;

/**
 * A set of comparisons that return {@code true} if they match and
 * {@code false} if they don't. These comparisons also serve as result
 * constants of semantically more complex comparisons, such as those
 * provided by
 * {@link org.logisticPlanning.utils.utils.comparison.PreciseComparator#preciseCompare(Object, Object)
 * PreciseComparator}.
 */
public enum EComparison {

  /** less */
  LESS(-1) {

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final byte a, final byte b) {
      return (a < b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final short a, final short b) {
      return (a < b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final int a, final int b) {
      return (a < b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final long a, final long b) {
      return (a < b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final float a, final float b) {
      return (a < b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final double a, final double b) {
      return (a < b);
    }

    /** {@inheritDoc} */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public final boolean compare(final Object a, final Object b) {
      if (a == b) {
        return false;
      }
      if (a == null) {
        return false;
      }
      if (b == null) {
        return true;
      }
      if (a.equals(b)) {
        return false;
      }
      try {
        return (((Comparable) a).compareTo(b) < 0);
      } catch (final ClassCastException t) {
        return false;
      }
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final boolean a, final boolean b) {
      return ((!a) && b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final char a, final char b) {
      return (Character.compare(a, b) < 0);
    }

  },

  /** less or same */
  LESS_OR_SAME(-1) {

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final byte a, final byte b) {
      return (a <= b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final short a, final short b) {
      return (a <= b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final int a, final int b) {
      return (a <= b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final long a, final long b) {
      return (a <= b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final float a, final float b) {
      return (a <= b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final double a, final double b) {
      return (a <= b);
    }

    /** {@inheritDoc} */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public final boolean compare(final Object a, final Object b) {
      if (a == b) {
        return true;
      }
      if (a == null) {
        return false;
      }
      if (b == null) {
        return true;
      }
      if (a.equals(b)) {
        return false;
      }
      try {
        return (((Comparable) a).compareTo(b) < 0);
      } catch (final ClassCastException t) {
        return false;
      }
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final boolean a, final boolean b) {
      return ((!a) || b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final char a, final char b) {
      return (Character.compare(a, b) <= 0);
    }

  },

  /** less or equal */
  LESS_OR_EQUAL(-1) {

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final byte a, final byte b) {
      return (a <= b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final short a, final short b) {
      return (a <= b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final int a, final int b) {
      return (a <= b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final long a, final long b) {
      return (a <= b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final float a, final float b) {
      return (ComparisonUtils.compare(a, b) <= 0);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final double a, final double b) {
      return (ComparisonUtils.compare(a, b) <= 0);
    }

    /** {@inheritDoc} */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public final boolean compare(final Object a, final Object b) {
      if (a == b) {
        return true;
      }
      if (a == null) {
        return false;
      }
      if (b == null) {
        return true;
      }
      if (a.equals(b)) {
        return true;
      }
      try {
        return (((Comparable) a).compareTo(b) <= 0);
      } catch (final ClassCastException t) {
        return false;
      }
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final boolean a, final boolean b) {
      return ((!a) || b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final char a, final char b) {
      return (Character.compare(a, b) <= 0);
    }

  },

  /** are the values identical? */
  SAME(0) {

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final byte a, final byte b) {
      return (a == b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final short a, final short b) {
      return (a == b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final int a, final int b) {
      return (a == b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final long a, final long b) {
      return (a == b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final float a, final float b) {
      return (a == b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final double a, final double b) {
      return (a == b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final Object a, final Object b) {
      return (a == b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final boolean a, final boolean b) {
      return (a == b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final char a, final char b) {
      return (Character.compare(a, b) == 0);
    }
  },

  /** less */
  EQUAL(0) {

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final byte a, final byte b) {
      return (a == b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final short a, final short b) {
      return (a == b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final int a, final int b) {
      return (a == b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final long a, final long b) {
      return (a == b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final float a, final float b) {
      return (ComparisonUtils.compare(a, b) == 0);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final double a, final double b) {
      return (ComparisonUtils.compare(a, b) == 0);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final Object a, final Object b) {
      if (a == b) {
        return true;
      }
      if (a == null) {
        return false;
      }
      if (b == null) {
        return false;
      }
      return (a.equals(b));
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final boolean a, final boolean b) {
      return (a == b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final char a, final char b) {
      return (Character.compare(a, b) == 0);
    }
  },

  /** greater or equal */
  GREATER_OR_EQUAL(1) {

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final byte a, final byte b) {
      return (a >= b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final short a, final short b) {
      return (a >= b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final int a, final int b) {
      return (a >= b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final long a, final long b) {
      return (a >= b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final float a, final float b) {
      return (ComparisonUtils.compare(a, b) >= 0);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final double a, final double b) {
      return (ComparisonUtils.compare(a, b) >= 0);
    }

    /** {@inheritDoc} */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public final boolean compare(final Object a, final Object b) {
      if (a == b) {
        return true;
      }
      if (a == null) {
        return true;
      }
      if (b == null) {
        return false;
      }
      if (a.equals(b)) {
        return true;
      }
      try {
        return (((Comparable) a).compareTo(b) >= 0);
      } catch (final ClassCastException t) {
        return false;
      }
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final boolean a, final boolean b) {
      return (a || (!b));
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final char a, final char b) {
      return (Character.compare(a, b) >= 0);
    }

  },

  /** greater or same */
  GREATER_OR_SAME(1) {

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final byte a, final byte b) {
      return (a >= b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final short a, final short b) {
      return (a >= b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final int a, final int b) {
      return (a >= b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final long a, final long b) {
      return (a >= b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final float a, final float b) {
      return (a >= b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final double a, final double b) {
      return (a >= b);
    }

    /** {@inheritDoc} */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public final boolean compare(final Object a, final Object b) {
      if (a == b) {
        return true;
      }
      if (a == null) {
        return false;
      }
      if (b == null) {
        return true;
      }
      if (a.equals(b)) {
        return false;
      }
      try {
        return (((Comparable) a).compareTo(b) > 0);
      } catch (final ClassCastException t) {
        return false;
      }
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final boolean a, final boolean b) {
      return (a || (!b));
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final char a, final char b) {
      return (Character.compare(a, b) >= 0);
    }
  },

  /** greater */
  GREATER(1) {

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final byte a, final byte b) {
      return (a > b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final short a, final short b) {
      return (a > b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final int a, final int b) {
      return (a > b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final long a, final long b) {
      return (a > b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final float a, final float b) {
      return (a > b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final double a, final double b) {
      return (a > b);
    }

    /** {@inheritDoc} */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public final boolean compare(final Object a, final Object b) {
      if (a == b) {
        return false;
      }
      if (a == null) {
        return false;
      }
      if (b == null) {
        return true;
      }
      if (a.equals(b)) {
        return false;
      }
      try {
        return (((Comparable) a).compareTo(b) > 0);
      } catch (final ClassCastException t) {
        return false;
      }
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final boolean a, final boolean b) {
      return (a && (!b));
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final char a, final char b) {
      return (Character.compare(a, b) > 0);
    }
  },

  /** not same */
  NOT_SAME(0) {

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final byte a, final byte b) {
      return (a != b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final short a, final short b) {
      return (a != b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final int a, final int b) {
      return (a != b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final long a, final long b) {
      return (a != b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final float a, final float b) {
      return (a != b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final double a, final double b) {
      return (a != b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final Object a, final Object b) {
      return (a != b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final boolean a, final boolean b) {
      return (a != b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final char a, final char b) {
      return (Character.compare(a, b) != 0);
    }
  },

  /** not equal */
  NOT_EQUAL(0) {

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final byte a, final byte b) {
      return (a != b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final short a, final short b) {
      return (a != b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final int a, final int b) {
      return (a != b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final long a, final long b) {
      return (a != b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final float a, final float b) {
      return (ComparisonUtils.compare(a, b) != 0);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final double a, final double b) {
      return (ComparisonUtils.compare(a, b) != 0);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final Object a, final Object b) {
      if (a == b) {
        return false;
      }
      if (a == null) {
        return true;
      }
      if (b == null) {
        return true;
      }
      return (!(a.equals(b)));
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final boolean a, final boolean b) {
      return (a != b);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean compare(final char a, final char b) {
      return (Character.compare(a, b) != 0);
    }

  };

  /** a comparison constant */
  final int m_cmp;

  /**
   * create
   * 
   * @param cmp
   *          the comparison result to be returned by
   *          {@link org.logisticPlanning.utils.utils.comparison.PreciseComparator#preciseCompare(Object, Object)}
   */
  private EComparison(final int cmp) {
    this.m_cmp = cmp;
  }

  /**
   * compare {@code a} and {@code b}.
   * 
   * @param a
   *          the first number
   * @param b
   *          the second number
   * @return {@code true} if the comparison is met, {@code false} otherwise
   */
  public abstract boolean compare(final byte a, final byte b);

  /**
   * compare {@code a} and {@code b}.
   * 
   * @param a
   *          the first number
   * @param b
   *          the second number
   * @return {@code true} if the comparison is met, {@code false} otherwise
   */
  public abstract boolean compare(final short a, final short b);

  /**
   * compare {@code a} and {@code b}.
   * 
   * @param a
   *          the first number
   * @param b
   *          the second number
   * @return {@code true} if the comparison is met, {@code false} otherwise
   */
  public abstract boolean compare(final int a, final int b);

  /**
   * compare {@code a} and {@code b}.
   * 
   * @param a
   *          the first number
   * @param b
   *          the second number
   * @return {@code true} if the comparison is met, {@code false} otherwise
   */
  public abstract boolean compare(final long a, final long b);

  /**
   * compare {@code a} and {@code b}.
   * 
   * @param a
   *          the first number
   * @param b
   *          the second number
   * @return {@code true} if the comparison is met, {@code false} otherwise
   */
  public abstract boolean compare(final float a, final float b);

  /**
   * compare {@code a} and {@code b}.
   * 
   * @param a
   *          the first number
   * @param b
   *          the second number
   * @return {@code true} if the comparison is met, {@code false} otherwise
   */
  public abstract boolean compare(final double a, final double b);

  /**
   * compare {@code a} and {@code b}.
   * 
   * @param a
   *          the first object
   * @param b
   *          the second object
   * @return {@code true} if the comparison is met, {@code false} otherwise
   */
  public abstract boolean compare(final Object a, final Object b);

  /**
   * compare {@code a} and {@code b}.
   * 
   * @param a
   *          the first boolean
   * @param b
   *          the second boolean
   * @return {@code true} if the comparison is met, {@code false} otherwise
   */
  public abstract boolean compare(final boolean a, final boolean b);

  /**
   * compare {@code a} and {@code b}.
   * 
   * @param a
   *          the first character
   * @param b
   *          the second character
   * @return {@code true} if the comparison is met, {@code false} otherwise
   */
  public abstract boolean compare(final char a, final char b);

  /**
   * Get the equivalent result of a {@link java.util.Comparator}, i.e.,
   * either {@code -1}, {@code 0}, or {@code 1}.
   * 
   * @return the equivalent result of a {@link java.util.Comparator}.
   */
  public final int getComparisonResult() {
    return this.m_cmp;
  }
}
