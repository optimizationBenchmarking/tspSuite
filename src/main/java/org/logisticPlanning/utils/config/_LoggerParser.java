package org.logisticPlanning.utils.config;

import java.util.List;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.logisticPlanning.utils.text.TextUtils;
import org.logisticPlanning.utils.utils.EmptyUtils;

/**
 * A parser takes an object and translates it to a
 * {@link java.util.logging.Logger}. The logger description may contain up
 * to three elements:
 * <ol>
 * <li>The logger name. You may use a pre-defined logger that has been
 * created elsewhere in your application or specify a new name or use
 * well-known names such as &quot;global&quot;. The logger name will be
 * resolved using
 * {@link java.util.logging.Logger#getLogger(java.lang.String)}.</li>
 * <li>The {@link java.util.logging.Level logging level}, either a constant
 * that identifies a level (as specified in {@link java.util.logging.Level}
 * ) or a numerical value, i.e., anything the can be parsed by
 * {@link java.util.logging.Level#parse(String)}.</li>
 * <li>If a logging level is given: A Boolean value indicating whether the
 * logger loading should also override all logging levels of the handlers
 * registered with the logger. {@code true} is assumed if no value for this
 * is specified. If the log level is to be enforced with having this
 * parameter as {@code true}, loading the logger will also work its way up
 * to the parents until some useful handler is found.</li>
 * </ol>
 */
final class _LoggerParser extends Parser<Logger> {

  /** the logger parser instance */
  static final _LoggerParser INSTANCE = new _LoggerParser();

  /** create */
  private _LoggerParser() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final Logger parse(final Object value) {
    final List<String> conf;
    final int size;
    final Logger ret;
    final Level level;
    Handler[] handlers;
    Logger use;
    Filter filter;
    LogRecord record;
    boolean notFound;

    if (value instanceof Logger) {
      return ((Logger) value);
    }

    try {
      conf = ListParser.STRING_LIST_PARSER.parse(value);
    } catch (final Throwable t) {
      throw new _ClassError(value.getClass(), Logger.class, t);
    }

    size = conf.size();
    if (size <= 0) {
      throw new IllegalArgumentException(//
          "Logger parameter must contain at least the name of the logger."); //$NON-NLS-1$
    }

    ret = Logger.getLogger(conf.get(0));

    // is there a logging level?
    if (size > 1) {

      // yes, let's try to parse it
      level = Level.parse(conf.get(1));
      if (!(ret.isLoggable(level))) {
        ret.setLevel(level);
      }

      // if a logging level specified, we will also set the log levels for
      // all
      // handlers registered with the loggers, unless a third parameter is
      // given which is set to "false"
      setHandlerLevels: {
        if (size > 2) {
          if (!(TextUtils.parseBoolean(conf.get(2)))) {
            break setHandlerLevels;
          }
        }

        // let us work our way through the parent hierarchy until we
        // find a
        // useful handler
        use = ret;
        record = null;
        notFound = true;
        findLogger: for (;;) {

          if (!(use.isLoggable(level))) {
            use.setLevel(level);
          }

          handlers = use.getHandlers();
          if ((handlers != null) && (handlers.length > 0)) {
            for (final Handler handler : handlers) {

              if (record == null) {
                record = new LogRecord(level, EmptyUtils.EMPTY_STRING);
              }

              if (!(handler.isLoggable(record))) {
                handler.setLevel(level);
              }

              filter = handler.getFilter();
              if (filter != null) {
                if (!(filter.isLoggable(record))) {
                  handler.setFilter(null);
                }
              }

              if (handler.isLoggable(record)) {
                notFound = false;
              }
            }
          }
          if (notFound) {
            if (use.getUseParentHandlers()) {
              use = use.getParent();
              if (use != null) {
                continue findLogger;
              }
            }
          }
          break findLogger;
        }
      }
    }

    return ret;
  }

  /** {@inheritDoc} */
  @Override
  public final Class<Logger> objectClass() {
    return Logger.class;
  }
}
