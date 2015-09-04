package snippets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/** the job description generator for our cluster */
public class Job implements Comparable<Job> {

  /** the java job file */
  private static final String JAVA_JOB = "java_job.bat"; //$NON-NLS-1$

  /** the program to execute */
  private final File m_jar;

  /** the configuration */
  private final File m_config;

  /** the batch file */
  private final File[] m_batch;

  /**
   * create the job
   *
   * @param j
   *          the jar file
   * @param c
   *          the config file
   * @param b
   *          the batch file
   */
  private Job(final File j, final File c, final File[] b) {
    super();
    this.m_jar = j;
    this.m_config = c;
    this.m_batch = b;
  }

  /**
   * Make the job text
   *
   * @param bw
   *          the buffered writer to write to
   * @param jar
   *          the jar
   * @param cfg
   *          the config
   */
  private static final void makeJob(final FileWriter bw, final File jar,
      final File cfg) {
    final File par;
    try {

      bw.write('\n');
      bw.write('~');
      bw.write('/');
      par = jar.getParentFile();
      bw.write(par.getParentFile().getName());
      bw.write(' ');
      bw.write(Job.JAVA_JOB);
      bw.write(' ');
      bw.write(par.getName());
      bw.write(' ');
      bw.write(jar.getName());
      bw.write(' ');
      bw.write(cfg.getName());
      bw.write('\n');
      bw.flush();
    } catch (final Throwable t) {
      t.printStackTrace();
    }
  }

  /**
   * Make the job text
   *
   * @param bw
   *          the buffered writer to write to
   * @param submit
   *          the file to submit
   */
  private static final void makeSubmit(final FileWriter bw,
      final File submit) {
    final File par, par2;

    if (submit == null) {
      return;
    }

    try {
      bw.write('\n');

      bw.write("bsub -q normal -n 12 -o ~/"); //$NON-NLS-1$
      par = submit.getParentFile();
      par2 = par.getParentFile();
      bw.write(par2.getName());
      bw.write('/');
      bw.write(par.getName());
      bw.write("/%J_log.txt -e ~/");//$NON-NLS-1$
      bw.write(par2.getName());
      bw.write('/');
      bw.write(par.getName());
      bw.write("/%J_errors.txt -x ~/");//$NON-NLS-1$
      bw.write(par2.getName());
      bw.write('/');
      bw.write(par.getName());
      bw.write('/');
      bw.write(submit.getName());

      bw.write('\n');
      bw.flush();

    } catch (final Throwable t) {
      t.printStackTrace();
    }
  }

  /**
   * make the batch files
   *
   * @param i
   *          the index of the batch file to make
   * @param next
   *          the next file to add
   */
  private final void makeBatchFile(final int i, final File next) {
    try {
      try (FileWriter bw = new FileWriter(this.m_batch[i])) {
        bw.write("#!/bin/sh");//$NON-NLS-1$
        bw.write('\n');

        if (next != null) {
          Job.makeSubmit(bw, next);
        }

        Job.makeJob(bw, this.m_jar, this.m_config);
      }
    } catch (final Throwable ttt) {
      ttt.printStackTrace();
    }
  }

  /**
   * Make the batch files
   *
   * @param dir
   *          the directory
   * @param stepWidths
   *          the step widths
   * @param jobs
   *          the jobs
   */
  private static final void makeBatch(final File dir,
      final int[] stepWidths, final Job[] jobs) {
    final int s;

    if (jobs == null) {
      return;
    }
    s = jobs.length;
    if (s <= 0) {
      return;
    }
    try {
      try (final FileWriter bw = new FileWriter(
          new File(dir, "submit.bat"))) { //$NON-NLS-1$
        bw.write("#!/bin/sh");//$NON-NLS-1$
        bw.write('\n');

        final int d = Math.min(s, stepWidths[0]);
        for (int i = 0; i < d; i++) {
          Job.makeSubmit(bw, jobs[i].m_batch[0]);
        }
      }
    } catch (final Throwable t) {
      t.printStackTrace();
    }

    for (int i = 0; i < stepWidths.length; i++) {
      System.out.println();
      System.out.println();
      System.out.println("Step width: " + stepWidths[i]);//$NON-NLS-1$
      for (int j = 0; j < s; j++) {
        final Job jj = jobs[j];
        File next;
        final int ni = (j + stepWidths[i]);
        if (ni < s) {
          next = jobs[j + stepWidths[i]].m_batch[i];
        } else {
          if (i < (stepWidths.length - 1)) {
            if (i < (stepWidths.length - 2)) {
              next = jobs[ni % s].m_batch[i + 1];
            } else {
              next = jobs[0].m_batch[i + 1];
            }
          } else {
            next = null;
          }
        }

        jj.makeBatchFile(i, next);
        System.out.println(j + ". " + //$NON-NLS-1$
            jj.m_jar.getParentFile().getName()
            + " --> " + //$NON-NLS-1$
            ((next != null) ? (next.getParentFile().getName() + '/' + next
                .getName()) : "[end]")); //$NON-NLS-1$
      }
    }
  }

  /**
   * Make the javajob file
   *
   * @param dir
   *          the directory
   */
  private static final void makeJavaJob(final File dir) {
    try (final FileWriter fw = new FileWriter(new File(dir, Job.JAVA_JOB))) {
      try (final BufferedWriter bw = new BufferedWriter(fw)) {
        bw.write("#!/bin/sh"); //$NON-NLS-1$
        bw.newLine();
        bw.write("JOB_WORKING_DIR=~/"); //$NON-NLS-1$
        bw.write(dir.getName());
        bw.write("/$1/"); //$NON-NLS-1$
        bw.newLine();
        bw.write("JOB_EXECUTABLE=$2"); //$NON-NLS-1$
        bw.newLine();
        bw.write("JOB_CONFIG=$3"); //$NON-NLS-1$
        bw.newLine();
        bw.write("cd ${JOB_WORKING_DIR}"); //$NON-NLS-1$
        bw.newLine();
        bw.write("find ${JOB_WORKING_DIR} -type f -empty -delete"); //$NON-NLS-1$
        bw.newLine();
        bw.write("~/java/bin/java -XX:+AggressiveOpts -XX:+UseFastAccessorMethods -server -Xnoclassgc -XX:+UseCompressedOops -XX:+AlwaysPreTouch -XX:CompileThreshold=512 -XX:+OptimizeStringConcat -jar ${JOB_WORKING_DIR}${JOB_EXECUTABLE} configFile=${JOB_CONFIG} >> ${JOB_WORKING_DIR}output.txt 2>&1"); //$NON-NLS-1$
      }
    } catch (final Throwable error) {
      error.printStackTrace();
    }
  }

  /**
   * find all jobs in a given directory and append them to a collection
   *
   * @param dir
   *          the directory
   * @param append
   *          the collection to append
   * @param batchCount
   *          the number of batch files per job
   */
  private static final void findJobs(final File dir,
      final Collection<Job> append, final int batchCount) {
    File v, q, q2, f3, jar, cfg;
    File[] zzz, zzz2, q3;
    String namelc;

    if (dir == null) {
      return;
    }
    try {
      v = dir.getCanonicalFile();
    } catch (final Throwable t1) {
      try {
        v = dir.getAbsoluteFile();
      } catch (final Throwable t2) {
        v = dir;
      }
    }

    if (v == null) {
      v = dir;
    }
    zzz = dir.listFiles();
    if (zzz == null) {
      return;
    }

    Arrays.sort(zzz);

    for (final File f : zzz) {

      if (f == null) {
        continue;
      }

      try {
        q = f.getCanonicalFile();
      } catch (final Throwable t1) {
        try {
          q = f.getAbsoluteFile();
        } catch (final Throwable t2) {
          q = f;
        }
      }
      if (q == null) {
        q = f;
      }

      if (q.exists() && q.isDirectory()) {

        jar = null;
        cfg = null;

        zzz2 = q.listFiles();
        if (zzz2 == null) {
          continue;
        }
        Arrays.sort(zzz2);

        inner: for (final File f2 : zzz2) {

          if (f2 == null) {
            continue;
          }

          try {
            q2 = f2.getCanonicalFile();
          } catch (final Throwable t1) {
            try {
              q2 = f2.getAbsoluteFile();
            } catch (final Throwable t2) {
              q2 = f2;
            }
          }
          if (q2 == null) {
            q2 = f2;
          }

          if (!(q2.isFile())) {
            continue inner;
          }

          namelc = q2.getName().toLowerCase();

          if (namelc.endsWith(".jar")) {//$NON-NLS-1$
            jar = q2;
          } else {
            if (namelc.endsWith(".txt")) {//$NON-NLS-1$
              cfg = q2;
            }
          }
        }

        if ((jar != null) && (cfg != null)) {

          q3 = new File[batchCount];
          for (int i = q3.length; (--i) >= 0;) {
            q3[i] = f3 = new File(q, q.getName() + "_" + (i + 1) + //$NON-NLS-1$
                ".bat");//$NON-NLS-1$

            try {
              q2 = f3.getCanonicalFile();
            } catch (final Throwable t1) {
              try {
                q2 = f3.getAbsoluteFile();
              } catch (final Throwable t2) {
                q2 = f3;
              }
            }
            if (q2 != null) {
              q3[i] = q2;
            }
          }

          append.add(new Job(jar, cfg, q3));
        }
      }
    }

  }

  /**
   * make the jobs
   *
   * @param dir
   *          the dir
   * @param stepWidths
   *          the step widths
   */
  public static final void makeJobs(final File dir, final int[] stepWidths) {
    final ArrayList<Job> l;
    final Job[] jobs;

    l = new ArrayList<>();
    Job.findJobs(dir, l, stepWidths.length);
    jobs = l.toArray(new Job[l.size()]);
    Arrays.sort(jobs);
    Job.makeBatch(dir, stepWidths, jobs);
    Job.makeJavaJob(dir);
  }

  /** {@inheritDoc} */
  @Override
  public final int compareTo(final Job o) {
    int r;

    if (o == this) {
      return 0;
    }
    if (o == null) {
      return (-1);
    }

    r = this.m_jar.getPath().compareTo(o.m_jar.getPath());
    if (r != 0) {
      return r;
    }

    return this.m_config.getPath().compareTo(o.m_config.getPath());
  }

  /**
   * Run the program
   *
   * @param args
   *          x
   * @throws Throwable
   *           xx
   */
  public static void main(final String[] args) throws Throwable {
    Job.makeJobs(new File("."), new int[] { //$NON-NLS-1$
        7, 7, 6, 5, 4, 3, 2, 1 });
  }

}
