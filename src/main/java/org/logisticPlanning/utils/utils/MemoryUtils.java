package org.logisticPlanning.utils.utils;

/**
 * some additional utilities
 */
public final class MemoryUtils {

  /**
   * Invoke the garbage collector in a very crude manner. This method is
   * more hard-core than {@link System#gc()} as the goal is to free a
   * maximum amount of memory, regardless how long it takes. This makes
   * sense when doing some memory-intense stuff such as evaluating
   * experimental results. It makes not much sense in performance-critical
   * code.
   */
  public static final void gc() {
    final Runtime r;
    long best, cur, i;

    r = Runtime.getRuntime();
    best = cur = Long.MIN_VALUE;
    i = 5;
    for (;;) {
      r.gc();
      Thread.yield();
      r.runFinalization();
      Thread.yield();
      r.gc();
      cur = r.freeMemory();
      if (cur > best) {
        best = cur;
        i = 5;
        continue;
      }
      if ((--i) <= 0) {
        return;
      }
    }
  }
}
