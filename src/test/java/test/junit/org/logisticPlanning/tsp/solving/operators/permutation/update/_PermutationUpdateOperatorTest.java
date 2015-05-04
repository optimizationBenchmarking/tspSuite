package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.update;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer;
import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateUniform;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator;
import org.logisticPlanning.utils.math.random.Randomizer;

import test.junit.org.logisticPlanning.tsp.benchmarking.dist.DistanceComputerTest;
import test.junit.org.logisticPlanning.tsp.solving.operators.permutation.PermutationOperatorTest;

/** Test a permutation update operator. */
@Ignore
public class _PermutationUpdateOperatorTest extends
PermutationOperatorTest {

  /** a wrong point distance */
  private static final String WRONG_POINT_DISTANCE = "A point distance was wrong."; //$NON-NLS-1$

  /** a wrong delta */
  private static final String WRONG_DELTA_UPDATE = "The delta value computed before the update does not fit to the evaluated solution after the update."; //$NON-NLS-1$

  /** a reversion */
  private static final String WRONG_REVERT = "Reversion of the operation after its application did not lead to the original permutation."; //$NON-NLS-1$

  /** the internal randomizer */
  private final Randomizer m_r;

  /** Instantiate the permutation operator test. */
  _PermutationUpdateOperatorTest() {
    super();
    this.m_r = new Randomizer();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationUpdateOperator createOperator() {
    return null;
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationUpdateOperator getOperator() {
    return ((PermutationUpdateOperator) (super.getOperator()));
  }

  /**
   * <p>
   * Test whether updating works correctly on a given instance (see
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance}) with
   * random indices. This test tests:
   * </p>
   * <ul>
   * <li>whether loading the distance computer works well
   * <li>
   * <li>whether the {@code update(int[], int, int} leads to the change in
   * tour length predicted by
   * {@code delta(int[], DistanceComputer, int, int)} , and</li>
   * <li>whether reverting an update via
   * {@code revertUpdate(int[], int, int)} again leads to the same basic
   * situation</li>
   *
   * @param inst
   *          the instance to test
   * @throws IOException
   *           if io fails
   */
  private final void testUpdateRand(final Instance inst)
      throws IOException {
    final DistanceComputer dist;
    final int[] perm1, perm2;
    final long eval;
    final int n;
    final PermutationUpdateOperator op;
    final Randomizer rrr;
    long updated;
    int delta, a, b;

    dist = inst.load(4096);
    Assert.assertNotNull(dist);
    n = dist.n();
    Assert.assertEquals(n, inst.n());
    op = this.getOperator();

    rrr = new Randomizer();
    perm1 = PermutationCreateUniform.create(n, rrr);
    eval = dist.evaluate(perm1);
    Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
        (eval > 0l));
    perm2 = perm1.clone();

    for (int i = DistanceComputerTest.getRequiredTests(n); (--i) >= 0;) {
      synchronized (this.m_r) {
        a = this.m_r.nextInt(n);
        do {
          b = this.m_r.nextInt(n);
        } while (a == b);
      }

      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }
  }

  /**
   * <p>
   * Test whether updating works correctly on a given instance (see
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance}) with
   * special indices. This test tests:
   * </p>
   * <ul>
   * <li>whether loading the distance computer works well
   * <li>
   * <li>whether the {@code update(int[], int, int} leads to the change in
   * tour length predicted by
   * {@code delta(int[], DistanceComputer, int, int)} , and</li>
   * <li>whether reverting an update via
   * {@code revertUpdate(int[], int, int)} again leads to the same basic
   * situation</li>
   *
   * @param inst
   *          the instance to test
   * @throws IOException
   *           if io fails
   */
  private final void testUpdateSpecial(final Instance inst)
      throws IOException {
    final DistanceComputer dist;
    final int[] perm1, perm2;
    final long eval;
    final int n;
    final PermutationUpdateOperator op;
    final Randomizer rrr;
    long updated;
    int delta, a, b;

    dist = inst.load(4096);
    Assert.assertNotNull(dist);
    n = dist.n();
    Assert.assertEquals(n, inst.n());
    op = this.getOperator();

    rrr = new Randomizer();
    perm1 = PermutationCreateUniform.create(n, rrr);
    eval = dist.evaluate(perm1);
    Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
        (eval > 0l));
    perm2 = perm1.clone();

    // test the special indices 0 and (1%n)
    a = (0);
    b = ((1 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices 0 and (2%n)
    a = (0);
    b = ((2 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices 0 and (3%n)
    a = (0);
    b = ((3 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices 0 and (4%n)
    a = (0);
    b = ((4 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices 0 and (((n-1)+n)%n)
    a = (0);
    b = ((((n - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices 0 and (((n-2)+n)%n)
    a = (0);
    b = ((((n - 2) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices 0 and (((n-3)+n)%n)
    a = (0);
    b = ((((n - 3) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices 0 and (((n-4)+n)%n)
    a = (0);
    b = ((((n - 4) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices 0 and (((n-5)+n)%n)
    a = (0);
    b = ((((n - 5) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices 0 and (n>>1)
    a = (0);
    b = ((n >> 1));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices 0 and (((n>>1)+1)%n)
    a = (0);
    b = ((((n >> 1) + 1) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices 0 and ((((n>>1)-1)+n)%n)
    a = (0);
    b = (((((n >> 1) - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (1%n) and 0
    a = ((1 % n));
    b = (0);
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (1%n) and (2%n)
    a = ((1 % n));
    b = ((2 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (1%n) and (3%n)
    a = ((1 % n));
    b = ((3 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (1%n) and (4%n)
    a = ((1 % n));
    b = ((4 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (1%n) and (((n-1)+n)%n)
    a = ((1 % n));
    b = ((((n - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (1%n) and (((n-2)+n)%n)
    a = ((1 % n));
    b = ((((n - 2) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (1%n) and (((n-3)+n)%n)
    a = ((1 % n));
    b = ((((n - 3) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (1%n) and (((n-4)+n)%n)
    a = ((1 % n));
    b = ((((n - 4) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (1%n) and (((n-5)+n)%n)
    a = ((1 % n));
    b = ((((n - 5) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (1%n) and (n>>1)
    a = ((1 % n));
    b = ((n >> 1));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (1%n) and (((n>>1)+1)%n)
    a = ((1 % n));
    b = ((((n >> 1) + 1) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (1%n) and ((((n>>1)-1)+n)%n)
    a = ((1 % n));
    b = (((((n >> 1) - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (2%n) and 0
    a = ((2 % n));
    b = (0);
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (2%n) and (1%n)
    a = ((2 % n));
    b = ((1 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (2%n) and (3%n)
    a = ((2 % n));
    b = ((3 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (2%n) and (4%n)
    a = ((2 % n));
    b = ((4 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (2%n) and (((n-1)+n)%n)
    a = ((2 % n));
    b = ((((n - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (2%n) and (((n-2)+n)%n)
    a = ((2 % n));
    b = ((((n - 2) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (2%n) and (((n-3)+n)%n)
    a = ((2 % n));
    b = ((((n - 3) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (2%n) and (((n-4)+n)%n)
    a = ((2 % n));
    b = ((((n - 4) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (2%n) and (((n-5)+n)%n)
    a = ((2 % n));
    b = ((((n - 5) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (2%n) and (n>>1)
    a = ((2 % n));
    b = ((n >> 1));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (2%n) and (((n>>1)+1)%n)
    a = ((2 % n));
    b = ((((n >> 1) + 1) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (2%n) and ((((n>>1)-1)+n)%n)
    a = ((2 % n));
    b = (((((n >> 1) - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (3%n) and 0
    a = ((3 % n));
    b = (0);
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (3%n) and (1%n)
    a = ((3 % n));
    b = ((1 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (3%n) and (2%n)
    a = ((3 % n));
    b = ((2 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (3%n) and (4%n)
    a = ((3 % n));
    b = ((4 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (3%n) and (((n-1)+n)%n)
    a = ((3 % n));
    b = ((((n - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (3%n) and (((n-2)+n)%n)
    a = ((3 % n));
    b = ((((n - 2) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (3%n) and (((n-3)+n)%n)
    a = ((3 % n));
    b = ((((n - 3) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (3%n) and (((n-4)+n)%n)
    a = ((3 % n));
    b = ((((n - 4) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (3%n) and (((n-5)+n)%n)
    a = ((3 % n));
    b = ((((n - 5) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (3%n) and (n>>1)
    a = ((3 % n));
    b = ((n >> 1));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (3%n) and (((n>>1)+1)%n)
    a = ((3 % n));
    b = ((((n >> 1) + 1) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (3%n) and ((((n>>1)-1)+n)%n)
    a = ((3 % n));
    b = (((((n >> 1) - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (4%n) and 0
    a = ((4 % n));
    b = (0);
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (4%n) and (1%n)
    a = ((4 % n));
    b = ((1 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (4%n) and (2%n)
    a = ((4 % n));
    b = ((2 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (4%n) and (3%n)
    a = ((4 % n));
    b = ((3 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (4%n) and (((n-1)+n)%n)
    a = ((4 % n));
    b = ((((n - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (4%n) and (((n-2)+n)%n)
    a = ((4 % n));
    b = ((((n - 2) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (4%n) and (((n-3)+n)%n)
    a = ((4 % n));
    b = ((((n - 3) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (4%n) and (((n-4)+n)%n)
    a = ((4 % n));
    b = ((((n - 4) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (4%n) and (((n-5)+n)%n)
    a = ((4 % n));
    b = ((((n - 5) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (4%n) and (n>>1)
    a = ((4 % n));
    b = ((n >> 1));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (4%n) and (((n>>1)+1)%n)
    a = ((4 % n));
    b = ((((n >> 1) + 1) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (4%n) and ((((n>>1)-1)+n)%n)
    a = ((4 % n));
    b = (((((n >> 1) - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-1)+n)%n) and 0
    a = ((((n - 1) + n) % n));
    b = (0);
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-1)+n)%n) and (1%n)
    a = ((((n - 1) + n) % n));
    b = ((1 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-1)+n)%n) and (2%n)
    a = ((((n - 1) + n) % n));
    b = ((2 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-1)+n)%n) and (3%n)
    a = ((((n - 1) + n) % n));
    b = ((3 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-1)+n)%n) and (4%n)
    a = ((((n - 1) + n) % n));
    b = ((4 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-1)+n)%n) and (((n-2)+n)%n)
    a = ((((n - 1) + n) % n));
    b = ((((n - 2) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-1)+n)%n) and (((n-3)+n)%n)
    a = ((((n - 1) + n) % n));
    b = ((((n - 3) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-1)+n)%n) and (((n-4)+n)%n)
    a = ((((n - 1) + n) % n));
    b = ((((n - 4) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-1)+n)%n) and (((n-5)+n)%n)
    a = ((((n - 1) + n) % n));
    b = ((((n - 5) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-1)+n)%n) and (n>>1)
    a = ((((n - 1) + n) % n));
    b = ((n >> 1));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-1)+n)%n) and (((n>>1)+1)%n)
    a = ((((n - 1) + n) % n));
    b = ((((n >> 1) + 1) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-1)+n)%n) and ((((n>>1)-1)+n)%n)
    a = ((((n - 1) + n) % n));
    b = (((((n >> 1) - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-2)+n)%n) and 0
    a = ((((n - 2) + n) % n));
    b = (0);
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-2)+n)%n) and (1%n)
    a = ((((n - 2) + n) % n));
    b = ((1 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-2)+n)%n) and (2%n)
    a = ((((n - 2) + n) % n));
    b = ((2 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-2)+n)%n) and (3%n)
    a = ((((n - 2) + n) % n));
    b = ((3 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-2)+n)%n) and (4%n)
    a = ((((n - 2) + n) % n));
    b = ((4 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-2)+n)%n) and (((n-1)+n)%n)
    a = ((((n - 2) + n) % n));
    b = ((((n - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-2)+n)%n) and (((n-3)+n)%n)
    a = ((((n - 2) + n) % n));
    b = ((((n - 3) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-2)+n)%n) and (((n-4)+n)%n)
    a = ((((n - 2) + n) % n));
    b = ((((n - 4) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-2)+n)%n) and (((n-5)+n)%n)
    a = ((((n - 2) + n) % n));
    b = ((((n - 5) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-2)+n)%n) and (n>>1)
    a = ((((n - 2) + n) % n));
    b = ((n >> 1));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-2)+n)%n) and (((n>>1)+1)%n)
    a = ((((n - 2) + n) % n));
    b = ((((n >> 1) + 1) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-2)+n)%n) and ((((n>>1)-1)+n)%n)
    a = ((((n - 2) + n) % n));
    b = (((((n >> 1) - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-3)+n)%n) and 0
    a = ((((n - 3) + n) % n));
    b = (0);
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-3)+n)%n) and (1%n)
    a = ((((n - 3) + n) % n));
    b = ((1 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-3)+n)%n) and (2%n)
    a = ((((n - 3) + n) % n));
    b = ((2 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-3)+n)%n) and (3%n)
    a = ((((n - 3) + n) % n));
    b = ((3 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-3)+n)%n) and (4%n)
    a = ((((n - 3) + n) % n));
    b = ((4 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-3)+n)%n) and (((n-1)+n)%n)
    a = ((((n - 3) + n) % n));
    b = ((((n - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-3)+n)%n) and (((n-2)+n)%n)
    a = ((((n - 3) + n) % n));
    b = ((((n - 2) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-3)+n)%n) and (((n-4)+n)%n)
    a = ((((n - 3) + n) % n));
    b = ((((n - 4) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-3)+n)%n) and (((n-5)+n)%n)
    a = ((((n - 3) + n) % n));
    b = ((((n - 5) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-3)+n)%n) and (n>>1)
    a = ((((n - 3) + n) % n));
    b = ((n >> 1));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-3)+n)%n) and (((n>>1)+1)%n)
    a = ((((n - 3) + n) % n));
    b = ((((n >> 1) + 1) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-3)+n)%n) and ((((n>>1)-1)+n)%n)
    a = ((((n - 3) + n) % n));
    b = (((((n >> 1) - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-4)+n)%n) and 0
    a = ((((n - 4) + n) % n));
    b = (0);
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-4)+n)%n) and (1%n)
    a = ((((n - 4) + n) % n));
    b = ((1 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-4)+n)%n) and (2%n)
    a = ((((n - 4) + n) % n));
    b = ((2 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-4)+n)%n) and (3%n)
    a = ((((n - 4) + n) % n));
    b = ((3 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-4)+n)%n) and (4%n)
    a = ((((n - 4) + n) % n));
    b = ((4 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-4)+n)%n) and (((n-1)+n)%n)
    a = ((((n - 4) + n) % n));
    b = ((((n - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-4)+n)%n) and (((n-2)+n)%n)
    a = ((((n - 4) + n) % n));
    b = ((((n - 2) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-4)+n)%n) and (((n-3)+n)%n)
    a = ((((n - 4) + n) % n));
    b = ((((n - 3) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-4)+n)%n) and (((n-5)+n)%n)
    a = ((((n - 4) + n) % n));
    b = ((((n - 5) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-4)+n)%n) and (n>>1)
    a = ((((n - 4) + n) % n));
    b = ((n >> 1));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-4)+n)%n) and (((n>>1)+1)%n)
    a = ((((n - 4) + n) % n));
    b = ((((n >> 1) + 1) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-4)+n)%n) and ((((n>>1)-1)+n)%n)
    a = ((((n - 4) + n) % n));
    b = (((((n >> 1) - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-5)+n)%n) and 0
    a = ((((n - 5) + n) % n));
    b = (0);
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-5)+n)%n) and (1%n)
    a = ((((n - 5) + n) % n));
    b = ((1 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-5)+n)%n) and (2%n)
    a = ((((n - 5) + n) % n));
    b = ((2 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-5)+n)%n) and (3%n)
    a = ((((n - 5) + n) % n));
    b = ((3 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-5)+n)%n) and (4%n)
    a = ((((n - 5) + n) % n));
    b = ((4 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-5)+n)%n) and (((n-1)+n)%n)
    a = ((((n - 5) + n) % n));
    b = ((((n - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-5)+n)%n) and (((n-2)+n)%n)
    a = ((((n - 5) + n) % n));
    b = ((((n - 2) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-5)+n)%n) and (((n-3)+n)%n)
    a = ((((n - 5) + n) % n));
    b = ((((n - 3) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-5)+n)%n) and (((n-4)+n)%n)
    a = ((((n - 5) + n) % n));
    b = ((((n - 4) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-5)+n)%n) and (n>>1)
    a = ((((n - 5) + n) % n));
    b = ((n >> 1));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-5)+n)%n) and (((n>>1)+1)%n)
    a = ((((n - 5) + n) % n));
    b = ((((n >> 1) + 1) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n-5)+n)%n) and ((((n>>1)-1)+n)%n)
    a = ((((n - 5) + n) % n));
    b = (((((n >> 1) - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (n>>1) and 0
    a = ((n >> 1));
    b = (0);
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (n>>1) and (1%n)
    a = ((n >> 1));
    b = ((1 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (n>>1) and (2%n)
    a = ((n >> 1));
    b = ((2 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (n>>1) and (3%n)
    a = ((n >> 1));
    b = ((3 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (n>>1) and (4%n)
    a = ((n >> 1));
    b = ((4 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (n>>1) and (((n-1)+n)%n)
    a = ((n >> 1));
    b = ((((n - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (n>>1) and (((n-2)+n)%n)
    a = ((n >> 1));
    b = ((((n - 2) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (n>>1) and (((n-3)+n)%n)
    a = ((n >> 1));
    b = ((((n - 3) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (n>>1) and (((n-4)+n)%n)
    a = ((n >> 1));
    b = ((((n - 4) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (n>>1) and (((n-5)+n)%n)
    a = ((n >> 1));
    b = ((((n - 5) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (n>>1) and (((n>>1)+1)%n)
    a = ((n >> 1));
    b = ((((n >> 1) + 1) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (n>>1) and ((((n>>1)-1)+n)%n)
    a = ((n >> 1));
    b = (((((n >> 1) - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n>>1)+1)%n) and 0
    a = ((((n >> 1) + 1) % n));
    b = (0);
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n>>1)+1)%n) and (1%n)
    a = ((((n >> 1) + 1) % n));
    b = ((1 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n>>1)+1)%n) and (2%n)
    a = ((((n >> 1) + 1) % n));
    b = ((2 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n>>1)+1)%n) and (3%n)
    a = ((((n >> 1) + 1) % n));
    b = ((3 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n>>1)+1)%n) and (4%n)
    a = ((((n >> 1) + 1) % n));
    b = ((4 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n>>1)+1)%n) and (((n-1)+n)%n)
    a = ((((n >> 1) + 1) % n));
    b = ((((n - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n>>1)+1)%n) and (((n-2)+n)%n)
    a = ((((n >> 1) + 1) % n));
    b = ((((n - 2) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n>>1)+1)%n) and (((n-3)+n)%n)
    a = ((((n >> 1) + 1) % n));
    b = ((((n - 3) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n>>1)+1)%n) and (((n-4)+n)%n)
    a = ((((n >> 1) + 1) % n));
    b = ((((n - 4) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n>>1)+1)%n) and (((n-5)+n)%n)
    a = ((((n >> 1) + 1) % n));
    b = ((((n - 5) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n>>1)+1)%n) and (n>>1)
    a = ((((n >> 1) + 1) % n));
    b = ((n >> 1));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices (((n>>1)+1)%n) and ((((n>>1)-1)+n)%n)
    a = ((((n >> 1) + 1) % n));
    b = (((((n >> 1) - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices ((((n>>1)-1)+n)%n) and 0
    a = (((((n >> 1) - 1) + n) % n));
    b = (0);
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices ((((n>>1)-1)+n)%n) and (1%n)
    a = (((((n >> 1) - 1) + n) % n));
    b = ((1 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices ((((n>>1)-1)+n)%n) and (2%n)
    a = (((((n >> 1) - 1) + n) % n));
    b = ((2 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices ((((n>>1)-1)+n)%n) and (3%n)
    a = (((((n >> 1) - 1) + n) % n));
    b = ((3 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices ((((n>>1)-1)+n)%n) and (4%n)
    a = (((((n >> 1) - 1) + n) % n));
    b = ((4 % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices ((((n>>1)-1)+n)%n) and (((n-1)+n)%n)
    a = (((((n >> 1) - 1) + n) % n));
    b = ((((n - 1) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices ((((n>>1)-1)+n)%n) and (((n-2)+n)%n)
    a = (((((n >> 1) - 1) + n) % n));
    b = ((((n - 2) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices ((((n>>1)-1)+n)%n) and (((n-3)+n)%n)
    a = (((((n >> 1) - 1) + n) % n));
    b = ((((n - 3) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices ((((n>>1)-1)+n)%n) and (((n-4)+n)%n)
    a = (((((n >> 1) - 1) + n) % n));
    b = ((((n - 4) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices ((((n>>1)-1)+n)%n) and (((n-5)+n)%n)
    a = (((((n >> 1) - 1) + n) % n));
    b = ((((n - 5) + n) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices ((((n>>1)-1)+n)%n) and (n>>1)
    a = (((((n >> 1) - 1) + n) % n));
    b = ((n >> 1));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }

    // test the special indices ((((n>>1)-1)+n)%n) and (((n>>1)+1)%n)
    a = (((((n >> 1) - 1) + n) % n));
    b = ((((n >> 1) + 1) % n));
    if (a != b) {
      delta = op.delta(perm1, dist, a, b);
      if (delta != PermutationUpdateOperator.NO_EFFECT) {
        updated = (eval + delta);

        op.update(perm1, a, b);
        Assert.assertEquals(
            _PermutationUpdateOperatorTest.WRONG_DELTA_UPDATE,
            dist.evaluate(perm1), updated);
        Assert.assertTrue(
            _PermutationUpdateOperatorTest.WRONG_POINT_DISTANCE,
            updated >= inst.optimum());

        op.revertUpdate(perm1, a, b);
        Assert.assertTrue(_PermutationUpdateOperatorTest.WRONG_REVERT,
            Arrays.equals(perm1, perm2));
      }
    }
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#A280}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_A280() throws IOException {
    this.testUpdateRand(Instance.A280);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#A280}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_A280() throws IOException {
    this.testUpdateSpecial(Instance.A280);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ALI535}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_ALI535() throws IOException {
    this.testUpdateRand(Instance.ALI535);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ALI535}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_ALI535() throws IOException {
    this.testUpdateSpecial(Instance.ALI535);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ATT48}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_ATT48() throws IOException {
    this.testUpdateRand(Instance.ATT48);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ATT48}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_ATT48() throws IOException {
    this.testUpdateSpecial(Instance.ATT48);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ATT532}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_ATT532() throws IOException {
    this.testUpdateRand(Instance.ATT532);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ATT532}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_ATT532() throws IOException {
    this.testUpdateSpecial(Instance.ATT532);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BAYG29}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_BAYG29() throws IOException {
    this.testUpdateRand(Instance.BAYG29);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BAYG29}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_BAYG29() throws IOException {
    this.testUpdateSpecial(Instance.BAYG29);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BAYS29}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_BAYS29() throws IOException {
    this.testUpdateRand(Instance.BAYS29);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BAYS29}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_BAYS29() throws IOException {
    this.testUpdateSpecial(Instance.BAYS29);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BERLIN52}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_BERLIN52() throws IOException {
    this.testUpdateRand(Instance.BERLIN52);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BERLIN52}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_BERLIN52() throws IOException {
    this.testUpdateSpecial(Instance.BERLIN52);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BIER127}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_BIER127() throws IOException {
    this.testUpdateRand(Instance.BIER127);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BIER127}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_BIER127() throws IOException {
    this.testUpdateSpecial(Instance.BIER127);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BRAZIL58}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_BRAZIL58() throws IOException {
    this.testUpdateRand(Instance.BRAZIL58);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BRAZIL58}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_BRAZIL58() throws IOException {
    this.testUpdateSpecial(Instance.BRAZIL58);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BRD14051}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_BRD14051() throws IOException {
    this.testUpdateRand(Instance.BRD14051);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BRD14051}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_BRD14051() throws IOException {
    this.testUpdateSpecial(Instance.BRD14051);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BRG180}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_BRG180() throws IOException {
    this.testUpdateRand(Instance.BRG180);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BRG180}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_BRG180() throws IOException {
    this.testUpdateSpecial(Instance.BRG180);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BURMA14}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_BURMA14() throws IOException {
    this.testUpdateRand(Instance.BURMA14);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BURMA14}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_BURMA14() throws IOException {
    this.testUpdateSpecial(Instance.BURMA14);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#CH130}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_CH130() throws IOException {
    this.testUpdateRand(Instance.CH130);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#CH130}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_CH130() throws IOException {
    this.testUpdateSpecial(Instance.CH130);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#CH150}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_CH150() throws IOException {
    this.testUpdateRand(Instance.CH150);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#CH150}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_CH150() throws IOException {
    this.testUpdateSpecial(Instance.CH150);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#D198}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_D198() throws IOException {
    this.testUpdateRand(Instance.D198);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#D198}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_D198() throws IOException {
    this.testUpdateSpecial(Instance.D198);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#D493}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_D493() throws IOException {
    this.testUpdateRand(Instance.D493);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#D493}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_D493() throws IOException {
    this.testUpdateSpecial(Instance.D493);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#D657}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_D657() throws IOException {
    this.testUpdateRand(Instance.D657);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#D657}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_D657() throws IOException {
    this.testUpdateSpecial(Instance.D657);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#D1291}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_D1291() throws IOException {
    this.testUpdateRand(Instance.D1291);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#D1291}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_D1291() throws IOException {
    this.testUpdateSpecial(Instance.D1291);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#D1655}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_D1655() throws IOException {
    this.testUpdateRand(Instance.D1655);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#D1655}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_D1655() throws IOException {
    this.testUpdateSpecial(Instance.D1655);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#D2103}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_D2103() throws IOException {
    this.testUpdateRand(Instance.D2103);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#D2103}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_D2103() throws IOException {
    this.testUpdateSpecial(Instance.D2103);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#D15112}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_D15112() throws IOException {
    this.testUpdateRand(Instance.D15112);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#D15112}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_D15112() throws IOException {
    this.testUpdateSpecial(Instance.D15112);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#D18512}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_D18512() throws IOException {
    this.testUpdateRand(Instance.D18512);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#D18512}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_D18512() throws IOException {
    this.testUpdateSpecial(Instance.D18512);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#DANTZIG42}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_DANTZIG42() throws IOException {
    this.testUpdateRand(Instance.DANTZIG42);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#DANTZIG42}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_DANTZIG42() throws IOException {
    this.testUpdateSpecial(Instance.DANTZIG42);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#DSJ1000}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_DSJ1000() throws IOException {
    this.testUpdateRand(Instance.DSJ1000);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#DSJ1000}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_DSJ1000() throws IOException {
    this.testUpdateSpecial(Instance.DSJ1000);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#EIL51}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_EIL51() throws IOException {
    this.testUpdateRand(Instance.EIL51);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#EIL51}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_EIL51() throws IOException {
    this.testUpdateSpecial(Instance.EIL51);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#EIL76}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_EIL76() throws IOException {
    this.testUpdateRand(Instance.EIL76);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#EIL76}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_EIL76() throws IOException {
    this.testUpdateSpecial(Instance.EIL76);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#EIL101}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_EIL101() throws IOException {
    this.testUpdateRand(Instance.EIL101);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#EIL101}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_EIL101() throws IOException {
    this.testUpdateSpecial(Instance.EIL101);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FL417}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_FL417() throws IOException {
    this.testUpdateRand(Instance.FL417);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FL417}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_FL417() throws IOException {
    this.testUpdateSpecial(Instance.FL417);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FL1400}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_FL1400() throws IOException {
    this.testUpdateRand(Instance.FL1400);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FL1400}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_FL1400() throws IOException {
    this.testUpdateSpecial(Instance.FL1400);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FL1577}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_FL1577() throws IOException {
    this.testUpdateRand(Instance.FL1577);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FL1577}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_FL1577() throws IOException {
    this.testUpdateSpecial(Instance.FL1577);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FL3795}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_FL3795() throws IOException {
    this.testUpdateRand(Instance.FL3795);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FL3795}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_FL3795() throws IOException {
    this.testUpdateSpecial(Instance.FL3795);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FNL4461}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_FNL4461() throws IOException {
    this.testUpdateRand(Instance.FNL4461);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FNL4461}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_FNL4461() throws IOException {
    this.testUpdateSpecial(Instance.FNL4461);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FRI26}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_FRI26() throws IOException {
    this.testUpdateRand(Instance.FRI26);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FRI26}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_FRI26() throws IOException {
    this.testUpdateSpecial(Instance.FRI26);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GIL262}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_GIL262() throws IOException {
    this.testUpdateRand(Instance.GIL262);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GIL262}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_GIL262() throws IOException {
    this.testUpdateSpecial(Instance.GIL262);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR17}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_GR17() throws IOException {
    this.testUpdateRand(Instance.GR17);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR17}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_GR17() throws IOException {
    this.testUpdateSpecial(Instance.GR17);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR21}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_GR21() throws IOException {
    this.testUpdateRand(Instance.GR21);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR21}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_GR21() throws IOException {
    this.testUpdateSpecial(Instance.GR21);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR24}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_GR24() throws IOException {
    this.testUpdateRand(Instance.GR24);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR24}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_GR24() throws IOException {
    this.testUpdateSpecial(Instance.GR24);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR48}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_GR48() throws IOException {
    this.testUpdateRand(Instance.GR48);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR48}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_GR48() throws IOException {
    this.testUpdateSpecial(Instance.GR48);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR96}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_GR96() throws IOException {
    this.testUpdateRand(Instance.GR96);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR96}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_GR96() throws IOException {
    this.testUpdateSpecial(Instance.GR96);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR120}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_GR120() throws IOException {
    this.testUpdateRand(Instance.GR120);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR120}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_GR120() throws IOException {
    this.testUpdateSpecial(Instance.GR120);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR137}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_GR137() throws IOException {
    this.testUpdateRand(Instance.GR137);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR137}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_GR137() throws IOException {
    this.testUpdateSpecial(Instance.GR137);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR202}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_GR202() throws IOException {
    this.testUpdateRand(Instance.GR202);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR202}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_GR202() throws IOException {
    this.testUpdateSpecial(Instance.GR202);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR229}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_GR229() throws IOException {
    this.testUpdateRand(Instance.GR229);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR229}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_GR229() throws IOException {
    this.testUpdateSpecial(Instance.GR229);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR431}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_GR431() throws IOException {
    this.testUpdateRand(Instance.GR431);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR431}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_GR431() throws IOException {
    this.testUpdateSpecial(Instance.GR431);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR666}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_GR666() throws IOException {
    this.testUpdateRand(Instance.GR666);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR666}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_GR666() throws IOException {
    this.testUpdateSpecial(Instance.GR666);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#HK48}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_HK48() throws IOException {
    this.testUpdateRand(Instance.HK48);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#HK48}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_HK48() throws IOException {
    this.testUpdateSpecial(Instance.HK48);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROA100}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_KROA100() throws IOException {
    this.testUpdateRand(Instance.KROA100);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROA100}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_KROA100() throws IOException {
    this.testUpdateSpecial(Instance.KROA100);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROB100}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_KROB100() throws IOException {
    this.testUpdateRand(Instance.KROB100);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROB100}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_KROB100() throws IOException {
    this.testUpdateSpecial(Instance.KROB100);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROC100}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_KROC100() throws IOException {
    this.testUpdateRand(Instance.KROC100);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROC100}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_KROC100() throws IOException {
    this.testUpdateSpecial(Instance.KROC100);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROD100}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_KROD100() throws IOException {
    this.testUpdateRand(Instance.KROD100);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROD100}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_KROD100() throws IOException {
    this.testUpdateSpecial(Instance.KROD100);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROE100}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_KROE100() throws IOException {
    this.testUpdateRand(Instance.KROE100);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROE100}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_KROE100() throws IOException {
    this.testUpdateSpecial(Instance.KROE100);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROA150}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_KROA150() throws IOException {
    this.testUpdateRand(Instance.KROA150);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROA150}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_KROA150() throws IOException {
    this.testUpdateSpecial(Instance.KROA150);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROB150}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_KROB150() throws IOException {
    this.testUpdateRand(Instance.KROB150);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROB150}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_KROB150() throws IOException {
    this.testUpdateSpecial(Instance.KROB150);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROA200}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_KROA200() throws IOException {
    this.testUpdateRand(Instance.KROA200);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROA200}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_KROA200() throws IOException {
    this.testUpdateSpecial(Instance.KROA200);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROB200}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_KROB200() throws IOException {
    this.testUpdateRand(Instance.KROB200);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#KROB200}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_KROB200() throws IOException {
    this.testUpdateSpecial(Instance.KROB200);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#LIN105}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_LIN105() throws IOException {
    this.testUpdateRand(Instance.LIN105);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#LIN105}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_LIN105() throws IOException {
    this.testUpdateSpecial(Instance.LIN105);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#LIN318}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_LIN318() throws IOException {
    this.testUpdateRand(Instance.LIN318);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#LIN318}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_LIN318() throws IOException {
    this.testUpdateSpecial(Instance.LIN318);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#NRW1379}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_NRW1379() throws IOException {
    this.testUpdateRand(Instance.NRW1379);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#NRW1379}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_NRW1379() throws IOException {
    this.testUpdateSpecial(Instance.NRW1379);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#P654}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_P654() throws IOException {
    this.testUpdateRand(Instance.P654);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#P654}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_P654() throws IOException {
    this.testUpdateSpecial(Instance.P654);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PA561}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_PA561() throws IOException {
    this.testUpdateRand(Instance.PA561);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PA561}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_PA561() throws IOException {
    this.testUpdateSpecial(Instance.PA561);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PCB442}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_PCB442() throws IOException {
    this.testUpdateRand(Instance.PCB442);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PCB442}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_PCB442() throws IOException {
    this.testUpdateSpecial(Instance.PCB442);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PCB1173}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_PCB1173() throws IOException {
    this.testUpdateRand(Instance.PCB1173);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PCB1173}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_PCB1173() throws IOException {
    this.testUpdateSpecial(Instance.PCB1173);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PCB3038}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_PCB3038() throws IOException {
    this.testUpdateRand(Instance.PCB3038);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PCB3038}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_PCB3038() throws IOException {
    this.testUpdateSpecial(Instance.PCB3038);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PLA7397}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_PLA7397() throws IOException {
    this.testUpdateRand(Instance.PLA7397);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PLA7397}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_PLA7397() throws IOException {
    this.testUpdateSpecial(Instance.PLA7397);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PLA33810}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_PLA33810() throws IOException {
    this.testUpdateRand(Instance.PLA33810);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PLA33810}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_PLA33810() throws IOException {
    this.testUpdateSpecial(Instance.PLA33810);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PLA85900}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_PLA85900() throws IOException {
    this.testUpdateRand(Instance.PLA85900);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PLA85900}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_PLA85900() throws IOException {
    this.testUpdateSpecial(Instance.PLA85900);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR76}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_PR76() throws IOException {
    this.testUpdateRand(Instance.PR76);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR76}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_PR76() throws IOException {
    this.testUpdateSpecial(Instance.PR76);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR107}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_PR107() throws IOException {
    this.testUpdateRand(Instance.PR107);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR107}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_PR107() throws IOException {
    this.testUpdateSpecial(Instance.PR107);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR124}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_PR124() throws IOException {
    this.testUpdateRand(Instance.PR124);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR124}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_PR124() throws IOException {
    this.testUpdateSpecial(Instance.PR124);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR136}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_PR136() throws IOException {
    this.testUpdateRand(Instance.PR136);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR136}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_PR136() throws IOException {
    this.testUpdateSpecial(Instance.PR136);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR144}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_PR144() throws IOException {
    this.testUpdateRand(Instance.PR144);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR144}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_PR144() throws IOException {
    this.testUpdateSpecial(Instance.PR144);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR152}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_PR152() throws IOException {
    this.testUpdateRand(Instance.PR152);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR152}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_PR152() throws IOException {
    this.testUpdateSpecial(Instance.PR152);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR226}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_PR226() throws IOException {
    this.testUpdateRand(Instance.PR226);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR226}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_PR226() throws IOException {
    this.testUpdateSpecial(Instance.PR226);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR264}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_PR264() throws IOException {
    this.testUpdateRand(Instance.PR264);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR264}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_PR264() throws IOException {
    this.testUpdateSpecial(Instance.PR264);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR299}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_PR299() throws IOException {
    this.testUpdateRand(Instance.PR299);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR299}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_PR299() throws IOException {
    this.testUpdateSpecial(Instance.PR299);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR439}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_PR439() throws IOException {
    this.testUpdateRand(Instance.PR439);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR439}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_PR439() throws IOException {
    this.testUpdateSpecial(Instance.PR439);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR1002}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_PR1002() throws IOException {
    this.testUpdateRand(Instance.PR1002);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR1002}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_PR1002() throws IOException {
    this.testUpdateSpecial(Instance.PR1002);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR2392}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_PR2392() throws IOException {
    this.testUpdateRand(Instance.PR2392);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#PR2392}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_PR2392() throws IOException {
    this.testUpdateSpecial(Instance.PR2392);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RAT99}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_RAT99() throws IOException {
    this.testUpdateRand(Instance.RAT99);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RAT99}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_RAT99() throws IOException {
    this.testUpdateSpecial(Instance.RAT99);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RAT195}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_RAT195() throws IOException {
    this.testUpdateRand(Instance.RAT195);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RAT195}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_RAT195() throws IOException {
    this.testUpdateSpecial(Instance.RAT195);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RAT575}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_RAT575() throws IOException {
    this.testUpdateRand(Instance.RAT575);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RAT575}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_RAT575() throws IOException {
    this.testUpdateSpecial(Instance.RAT575);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RAT783}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_RAT783() throws IOException {
    this.testUpdateRand(Instance.RAT783);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RAT783}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_RAT783() throws IOException {
    this.testUpdateSpecial(Instance.RAT783);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RD100}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_RD100() throws IOException {
    this.testUpdateRand(Instance.RD100);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RD100}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_RD100() throws IOException {
    this.testUpdateSpecial(Instance.RD100);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RD400}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_RD400() throws IOException {
    this.testUpdateRand(Instance.RD400);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RD400}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_RD400() throws IOException {
    this.testUpdateSpecial(Instance.RD400);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RL1304}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_RL1304() throws IOException {
    this.testUpdateRand(Instance.RL1304);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RL1304}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_RL1304() throws IOException {
    this.testUpdateSpecial(Instance.RL1304);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RL1323}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_RL1323() throws IOException {
    this.testUpdateRand(Instance.RL1323);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RL1323}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_RL1323() throws IOException {
    this.testUpdateSpecial(Instance.RL1323);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RL1889}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_RL1889() throws IOException {
    this.testUpdateRand(Instance.RL1889);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RL1889}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_RL1889() throws IOException {
    this.testUpdateSpecial(Instance.RL1889);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RL5915}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_RL5915() throws IOException {
    this.testUpdateRand(Instance.RL5915);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RL5915}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_RL5915() throws IOException {
    this.testUpdateSpecial(Instance.RL5915);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RL5934}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_RL5934() throws IOException {
    this.testUpdateRand(Instance.RL5934);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RL5934}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_RL5934() throws IOException {
    this.testUpdateSpecial(Instance.RL5934);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RL11849}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_RL11849() throws IOException {
    this.testUpdateRand(Instance.RL11849);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#RL11849}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_RL11849() throws IOException {
    this.testUpdateSpecial(Instance.RL11849);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SI175}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_SI175() throws IOException {
    this.testUpdateRand(Instance.SI175);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SI175}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_SI175() throws IOException {
    this.testUpdateSpecial(Instance.SI175);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SI535}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_SI535() throws IOException {
    this.testUpdateRand(Instance.SI535);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SI535}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_SI535() throws IOException {
    this.testUpdateSpecial(Instance.SI535);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SI1032}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_SI1032() throws IOException {
    this.testUpdateRand(Instance.SI1032);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SI1032}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_SI1032() throws IOException {
    this.testUpdateSpecial(Instance.SI1032);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ST70}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_ST70() throws IOException {
    this.testUpdateRand(Instance.ST70);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ST70}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_ST70() throws IOException {
    this.testUpdateSpecial(Instance.ST70);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SWISS42}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_SWISS42() throws IOException {
    this.testUpdateRand(Instance.SWISS42);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#SWISS42}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_SWISS42() throws IOException {
    this.testUpdateSpecial(Instance.SWISS42);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#TS225}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_TS225() throws IOException {
    this.testUpdateRand(Instance.TS225);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#TS225}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_TS225() throws IOException {
    this.testUpdateSpecial(Instance.TS225);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#TSP225}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_TSP225() throws IOException {
    this.testUpdateRand(Instance.TSP225);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#TSP225}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_TSP225() throws IOException {
    this.testUpdateSpecial(Instance.TSP225);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#U159}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_U159() throws IOException {
    this.testUpdateRand(Instance.U159);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#U159}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_U159() throws IOException {
    this.testUpdateSpecial(Instance.U159);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#U574}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_U574() throws IOException {
    this.testUpdateRand(Instance.U574);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#U574}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_U574() throws IOException {
    this.testUpdateSpecial(Instance.U574);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#U724}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_U724() throws IOException {
    this.testUpdateRand(Instance.U724);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#U724}.
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_U724() throws IOException {
    this.testUpdateSpecial(Instance.U724);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#U1060}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_U1060() throws IOException {
    this.testUpdateRand(Instance.U1060);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#U1060}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_U1060() throws IOException {
    this.testUpdateSpecial(Instance.U1060);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#U1432}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_U1432() throws IOException {
    this.testUpdateRand(Instance.U1432);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#U1432}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_U1432() throws IOException {
    this.testUpdateSpecial(Instance.U1432);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#U1817}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_U1817() throws IOException {
    this.testUpdateRand(Instance.U1817);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#U1817}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_U1817() throws IOException {
    this.testUpdateSpecial(Instance.U1817);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#U2152}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_U2152() throws IOException {
    this.testUpdateRand(Instance.U2152);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#U2152}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_U2152() throws IOException {
    this.testUpdateSpecial(Instance.U2152);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#U2319}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_U2319() throws IOException {
    this.testUpdateRand(Instance.U2319);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#U2319}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_U2319() throws IOException {
    this.testUpdateSpecial(Instance.U2319);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ULYSSES16}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_ULYSSES16() throws IOException {
    this.testUpdateRand(Instance.ULYSSES16);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ULYSSES16}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_ULYSSES16() throws IOException {
    this.testUpdateSpecial(Instance.ULYSSES16);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ULYSSES22}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_ULYSSES22() throws IOException {
    this.testUpdateRand(Instance.ULYSSES22);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#ULYSSES22}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_ULYSSES22() throws IOException {
    this.testUpdateSpecial(Instance.ULYSSES22);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#USA13509}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_USA13509() throws IOException {
    this.testUpdateRand(Instance.USA13509);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#USA13509}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_USA13509() throws IOException {
    this.testUpdateSpecial(Instance.USA13509);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#VM1084}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_VM1084() throws IOException {
    this.testUpdateRand(Instance.VM1084);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#VM1084}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_VM1084() throws IOException {
    this.testUpdateSpecial(Instance.VM1084);
  }

  /**
   * Test whether updating with random indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#VM1748}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateRand_VM1748() throws IOException {
    this.testUpdateRand(Instance.VM1748);
  }

  /**
   * Test whether updating with special indices works correctly on the
   * instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#VM1748}
   * .
   *
   * @throws IOException
   *           if io fails
   */
  @Test(timeout = 3600000)
  public final void testUpdateSpecial_VM1748() throws IOException {
    this.testUpdateSpecial(Instance.VM1748);
  }

}
