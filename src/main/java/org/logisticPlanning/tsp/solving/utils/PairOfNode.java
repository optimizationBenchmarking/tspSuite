package org.logisticPlanning.tsp.solving.utils;

/** a pair for nodes */
public final class PairOfNode {

  /** the first node */
  private int m_a;

  /** the second node */
  private int m_b;

  /** create */
  public PairOfNode() {
    super();
    this.m_a = 0;
    this.m_b = 1;
  }

  /**
   * create and initialize
   *
   * @param a
   *          the first node
   * @param b
   *          the second node
   */
  public PairOfNode(final int a, final int b) {
    super();
    this.m_a = a;
    this.m_b = b;
  }

  /**
   * get the first node
   *
   * @return the first node
   */
  public int getA() {
    return this.m_a;
  }

  /**
   * set the first node
   *
   * @param a
   *          the first node
   */
  public void setA(final int a) {
    this.m_a = a;
  }

  /**
   * get the second node
   *
   * @return the second node
   */
  public int getB() {
    return this.m_b;
  }

  /**
   * set the second node
   *
   * @param b
   *          the second node
   */
  public void setB(final int b) {
    this.m_b = b;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof PairOfNode) {
      final PairOfNode temp = (PairOfNode) o;
      return (((this.m_a == temp.m_a) && (this.m_b == temp.m_b))
          || ((this.m_a == temp.m_b) && (this.m_b == temp.m_a)));
    }
    return false;

  }

  /** {@inheritDoc} */
  @Override
  public final PairOfNode clone() {
    return new PairOfNode(this.m_a, this.m_b);
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return ((this.m_a * this.m_b) + this.m_a + this.m_b);
  }
}
