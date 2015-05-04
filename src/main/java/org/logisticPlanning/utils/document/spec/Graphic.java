package org.logisticPlanning.utils.document.spec;

import java.awt.Graphics2D;
import java.io.Closeable;
import java.io.IOException;

/**
 * A graphics 2d object designed as output context for graphics. You can
 * use the initial {@link java.awt.Graphics2D#getClipBounds() clip bounds}
 * of this graphic determine its size.
 */
public abstract class Graphic extends Graphics2D implements Closeable {

  /** the owning element */
  private final FigureBody m_owner;

  /**
   * instantiate
   *
   * @param owner
   *          the owner
   */
  protected Graphic(final FigureBody owner) {
    super();
    if (owner == null) {
      throw new IllegalArgumentException();
    }
    this.m_owner = owner;
  }

  /**
   * Get the owning figure body object
   *
   * @return the owning figure body object
   */
  protected FigureBody getOwner() {
    return this.m_owner;
  }

  /** {@inheritDoc} */
  @Override
  public void close() throws IOException {
    try {
      this.dispose();
    } finally {
      this.m_owner.graphicEnd(this);
    }
  }

}
