package org.logisticPlanning.utils.graphics.chart.spec;

import java.util.Iterator;

import org.logisticPlanning.utils.math.data.iteration.StairsKeepLeftIterator2D;
import org.logisticPlanning.utils.math.data.iteration.StairsPreviewRightIterator2D;
import org.logisticPlanning.utils.math.data.point.Point2D;

/**
 * the data mode
 */
public enum ELineMode {

  /** directly connect the points in a line / the data */
  DIRECT {
    /** {@inheritDoc} */
    @Override
    public final Iterator<Point2D> iterate(final Iterator<Point2D> source) {
      return source;
    }
  },

  /** stairs, keeping the right-most value */
  STAIRS_KEEP_LEFT {
    /** {@inheritDoc} */
    @Override
    public final Iterator<Point2D> iterate(final Iterator<Point2D> source) {
      return new StairsKeepLeftIterator2D(source);
    }
  },

  /** stairs, keeping the left-most value */
  STAIRS_PREVIEW_RIGHT {
    /** {@inheritDoc} */
    @Override
    public final Iterator<Point2D> iterate(final Iterator<Point2D> source) {
      return new StairsPreviewRightIterator2D(source);
    }
  };

  /**
   * Create an iterator wrapping around the source iterator and inducing
   * the prescribed line behavior
   *
   * @param source
   *          the source iterator
   * @return the iterator with the required line behavior
   */
  public abstract Iterator<Point2D> iterate(final Iterator<Point2D> source);
}
