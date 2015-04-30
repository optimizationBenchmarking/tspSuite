package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA;

import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * 
 The template methods are used to compute how many elements of a new
 * &quot;sample&quot; should actually be sampled from the histogram model.
 * The remaining elements will come from the template.
 * 
 * @author <ul>
 *         <li>
 *         <em><a href="http://www2.hannan-u.ac.jp/~tsutsui/index-e.html">Shigeyoshi Tsutsui</a></em>
 *         [&#x7B52;&#x4E95; &#x8302;&#x7FA9;], Email:&nbsp;<a
 *         href="mailto:tsutsui@hannan-u.ac.jp">tsutsui@hannan-u.ac.jp</a></li>
 *         <li><em><a href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a> (role:
 *         adaption to benchmarking framework)</li>
 *         </ul>
 */
public enum ETemplateMethod {
  /** the cut method */
  CUT {
    /** {@inheritDoc} */
    @Override
    final int calculateSampleLength(final int n, final double gamma,
        final Randomizer r) {
      final double p;

      p = r.nextDouble();

      if (gamma < 0.5d) {
        return (int) ((n * (1.0d - Math.pow(1.0d - p, gamma
            / (1.0d - gamma)))) + 0.5d);
      }
      if (gamma == 0.5d) {
        return (int) ((n * p) + 0.5d);
      }
      return (n - (int) ((n * (1.0d - //
      Math.pow(1.0d - p, (1.0d - gamma) / gamma))) + 0.5d));
    }
  },

  /** the fixed method */
  FIXED {
    /** {@inheritDoc} */
    @Override
    final int calculateSampleLength(final int n, final double gamma,
        final Randomizer r) {
      return (int) (n * gamma);
    }
  },

  /** the binomial method */
  BINOMIAL {
    /** {@inheritDoc} */
    @Override
    final int calculateSampleLength(final int n, final double gamma,
        final Randomizer r) {
      int i, L;
      L = 0;
      for (i = 0; i < n; i++) {
        if (r.nextDouble() <= gamma) {
          L++;
        }
      }
      return L;
    }
  };

  /**
   * Calculate the sample length
   * 
   * @param n
   *          the n
   * @param gamma
   *          the gamma
   * @param r
   *          the randomizer
   * @return the sample length
   */
  abstract int calculateSampleLength(final int n, final double gamma,
      final Randomizer r);
}
