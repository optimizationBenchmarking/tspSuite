package org.logisticPlanning.utils.config;

import java.io.File;

import org.logisticPlanning.utils.io.FileUtils;

/**
 * A parser takes an object and translates it to a file
 */
public final class FileParser extends Parser<File> {

  /** the string parser instance */
  public static final FileParser INSTANCE = new FileParser();

  /** create */
  private FileParser() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final File parse(final Object value) {
    if (value instanceof File) {
      return FileUtils.canonicalize((File) value);
    }

    if (value instanceof String) {
      return FileUtils.canonicalize((String) value);
    }

    throw new _ClassError(value.getClass(), File.class);
  }

  /** {@inheritDoc} */
  @Override
  public final Class<File> objectClass() {
    return File.class;
  }
}
