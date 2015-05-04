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
 * {@link org.logisticPlanning.utils.math.random.Randomizer#nextFloat()}
 * method has changed (which is not allowed). We use files which contain a
 * random seed and a sequence of random numbers that should result from it
 * and match the behavior of the randomizer against it.
 */
public class RandomizerNextFloatSequenceTest extends TestBase {

  /** the number of sequences: {@value} */
  private static final int SEQUENCE_COUNT = 30;

  /** the length of the sequences: {@value} */
  private static final int SEQUENCE_LENGTH = (2048);

  /** the file prefix: {@value} */
  private static final String FILE_PREFIX = "nextFloatSequence_"; //$NON-NLS-1$

  /** the file suffix: {@value} */
  private static final String FILE_SUFFIX = ".data"; //$NON-NLS-1$

  /** create */
  public RandomizerNextFloatSequenceTest() {
    super();
  }

  /**
   * test the sequences
   *
   * @throws Throwable
   *           if something goes wrong
   */
  @Test(timeout = 3600000)
  public void testFloatSequences() throws Throwable {
    int sequence, element;
    long seed;
    Randomizer r;

    r = new Randomizer();
    for (sequence = 1; sequence <= RandomizerNextFloatSequenceTest.SEQUENCE_COUNT; sequence++) {
      try (final InputStream is = RandomizerNextFloatSequenceTest.class
          .getResourceAsStream(RandomizerNextFloatSequenceTest.FILE_PREFIX
              + sequence + RandomizerNextFloatSequenceTest.FILE_SUFFIX)) {
        try (final InputStreamReader isr = new InputStreamReader(is)) {
          try (final BufferedReader br = new BufferedReader(isr)) {

            seed = Long.parseLong(br.readLine());
            r.setSeed(seed);

            for (element = 1; element <= RandomizerNextFloatSequenceTest.SEQUENCE_LENGTH; element++) {
              Assert.assertEquals(
                  //
                  Integer.parseInt(br.readLine()),
                  Float.floatToIntBits(r.nextFloat()));
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
    int sequence, element;
    long seed;
    Randomizer r1, r2;

    if ((args != null) && (args.length > 0)) {
      outDir = FileUtils.canonicalize(args[0]);
    } else {
      outDir = FileUtils.canonicalize("E:/random"); //$NON-NLS-1$
    }

    r1 = new Randomizer();
    outDir.mkdirs();
    for (sequence = 1; sequence <= RandomizerNextFloatSequenceTest.SEQUENCE_COUNT; sequence++) {
      try (final FileWriter fw = new FileWriter(
          new File(
              outDir,//
              (RandomizerNextFloatSequenceTest.FILE_PREFIX + sequence + RandomizerNextFloatSequenceTest.FILE_SUFFIX)))) {
        try (final BufferedWriter bw = new BufferedWriter(fw)) {

          seed = r1.nextInt();
          r2 = new Randomizer();
          r2.setSeed(seed);
          bw.write(Long.toString(seed));
          for (element = 1; element <= RandomizerNextFloatSequenceTest.SEQUENCE_LENGTH; element++) {
            bw.newLine();
            bw.write(Integer.toString(Float.floatToIntBits(r2.nextFloat())));
          }

        }
      } catch (final Throwable t) {
        t.printStackTrace();
      }
    }
  }
}
