package org.logisticPlanning.utils.document.spec;

import java.awt.geom.Dimension2D;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.logisticPlanning.utils.graphics.DoubleDimension;

/**
 * The figure caption element.
 */
public class FigureBody extends Element {

  /** the name of this figure */
  private final URI m_relativeName;

  /** the dimension */
  private final DoubleDimension m_dim;

  /**
   * create the figure body element
   * 
   * @param relativeNameBase
   *          the uri denoting the basis for the relative name of this
   *          graphic. this name may be extended or modified by
   *          {@link #makeRelativeName(URI)}
   * @param owner
   *          the owning element
   * @param sizeInMM
   *          the size of the figure in millimeters
   * @throws URISyntaxException
   *           if the uri syntax is wrong
   * @throws IOException
   *           if io fails
   */
  protected FigureBody(final Element owner, final URI relativeNameBase,
      final Dimension2D sizeInMM) throws IOException, URISyntaxException {
    super(owner);
    if (relativeNameBase == null) {
      throw new IllegalArgumentException();
    }
    this.m_relativeName = this.makeRelativeName(relativeNameBase);
    this.m_dim = this.makeDimensions(sizeInMM);
  }

  /**
   * Get the relative name of this figure
   * 
   * @return the relative name of this figure
   */
  public final URI getRelativeName() {
    return this.m_relativeName;
  }

  /**
   * Complete a relative name
   * 
   * @param relativeNameBase
   *          the relative name base
   * @return the completed relative name
   * @throws URISyntaxException
   *           if the uri syntax is wrong
   */
  @SuppressWarnings("unused")
  protected URI makeRelativeName(final URI relativeNameBase)
      throws URISyntaxException {
    return relativeNameBase;
  }

  /**
   * Make the dimensions
   * 
   * @param dim
   *          the dimension
   * @return the fixed dimension
   */
  protected DoubleDimension makeDimensions(final Dimension2D dim) {
    return new DoubleDimension(dim);
  }

  /** {@inheritDoc} */
  @Override
  protected File resolveRelativeName(final URI relativeName)
      throws IOException, URISyntaxException {
    return super.resolveRelativeName(relativeName);
  }

  /**
   * Obtain the size of the figure's body in millimeters
   * 
   * @return the size of the figure's body in millimeters
   */
  public final Dimension2D getSizeInMM() {
    return this.m_dim;
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.figureBodyEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.checkDead(Element.STATE_NOTHING)) {
      this.doClose();
    }
  }

  /** {@inheritDoc} */
  @Override
  protected Graphic graphicCreate(final FigureBody owner)
      throws IOException {
    return super.graphicCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void graphicBegin(final Graphic h) throws IOException,
      IllegalStateException {
    super.graphicBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void graphicEnd(final Graphic h) throws IOException,
      IllegalStateException {
    super.graphicEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public Graphic graphic() throws IOException, IllegalStateException {
    return super.graphic();
  }
}
