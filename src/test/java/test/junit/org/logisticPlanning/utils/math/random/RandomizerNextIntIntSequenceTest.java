package test.junit.org.logisticPlanning.utils.math.random;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Assert;
import org.junit.Test;
import org.logisticPlanning.utils.io.FileUtils;
import org.logisticPlanning.utils.math.random.Randomizer;

import test.junit.TestBase;

/**
 * This test for the
 * {@link org.logisticPlanning.utils.math.random.Randomizer} class checks
 * whether the implementation of the
 * {@link org.logisticPlanning.utils.math.random.Randomizer#nextInt(int)}
 * method has changed (which is not allowed). We use files which contain a
 * random seed, a "limit" integer, and a sequence of random numbers that
 * should result from it and match the behavior of the randomizer against
 * it.
 */
public class RandomizerNextIntIntSequenceTest extends TestBase {

  /** the number of sequences: {@value} */
  private static final int SEQUENCE_COUNT = 30;

  /** the length of the sequences: {@value} */
  private static final int SEQUENCE_LENGTH = (2048);

  /** the file prefix: {@value} */
  private static final String FILE_PREFIX = "nextIntIntSequence_"; //$NON-NLS-1$

  /** the file suffix: {@value} */
  private static final String FILE_SUFFIX = ".data"; //$NON-NLS-1$

  /** create */
  public RandomizerNextIntIntSequenceTest() {
    super();
  }

  /**
   * test the sequences
   *
   * @throws Throwable
   *           if something goes wrong
   */
  @Test(timeout = 3600000)
  public void testNextIntIntSequences() throws Throwable {
    int sequence, element;
    long seed;
    int max, x;
    Randomizer r;

    r = new Randomizer();
    for (sequence = 1; sequence <= RandomizerNextIntIntSequenceTest.SEQUENCE_COUNT; sequence++) {
      try (final InputStream is = RandomizerNextIntIntSequenceTest.class
          .getResourceAsStream(RandomizerNextIntIntSequenceTest.FILE_PREFIX
              + sequence + RandomizerNextIntIntSequenceTest.FILE_SUFFIX)) {
        try (final InputStreamReader isr = new InputStreamReader(is)) {
          try (final BufferedReader br = new BufferedReader(isr)) {

            seed = Long.parseLong(br.readLine());
            r.setSeed(seed);
            max = Integer.parseInt(br.readLine());
            Assert.assertTrue(max > 0);

            for (element = 1; element <= RandomizerNextIntIntSequenceTest.SEQUENCE_LENGTH; element++) {
              x = r.nextInt(max);
              Assert.assertTrue(x < max);
              Assert.assertTrue(x >= 0);
              Assert.assertEquals(Integer.parseInt(br.readLine()), x);
            }
          }
        }
      }
    }
  }

  /**
   * the program to create sequence
   *
   * @param args
   *          the command line
   */
  public static final void main(final String[] args) {
    final File outDir;
    int sequence, element, max;
    long seed;
    Randomizer r1, r2;

    if ((args != null) && (args.length > 0)) {
      outDir = FileUtils.canonicalize(args[0]);
    } else {
      outDir = FileUtils.canonicalize("E:/random"); //$NON-NLS-1$
    }

    r1 = new Randomizer();
    outDir.mkdirs();
    for (sequence = 1; sequence <= RandomizerNextIntIntSequenceTest.SEQUENCE_COUNT; sequence++) {
      try (final FileWriter fw = new FileWriter(
          new File(
              outDir,//
              (RandomizerNextIntIntSequenceTest.FILE_PREFIX + sequence + RandomizerNextIntIntSequenceTest.FILE_SUFFIX)))) {
        try (final BufferedWriter bw = new BufferedWriter(fw)) {

          seed = r1.nextLong();
          switch (sequence) {

            case 1: {
              max = 1;
              break;
            }
            case 2: {
              max = 2;
              break;
            }
            case 3: {
              max = 3;
              break;
            }
            case 4: {
              max = 5;
              break;
            }
            case 5: {
              max = 7;
              break;
            }
            case 6: {
              max = 8;
              break;
            }

            default: {
              do {
                max = r1.nextInt();
              } while (max < 1);
            }
          }

          r2 = new Randomizer();
          r2.setSeed(seed);
          bw.write(Long.toString(seed));
          bw.newLine();
          bw.write(Integer.toString(max));
          for (element = 1; element <= RandomizerNextIntIntSequenceTest.SEQUENCE_LENGTH; element++) {
            bw.newLine();
            bw.write(Integer.toString(r2.nextInt(max)));
          }

        }
      } catch (final Throwable t) {
        t.printStackTrace();
      }
    }
  }
}
