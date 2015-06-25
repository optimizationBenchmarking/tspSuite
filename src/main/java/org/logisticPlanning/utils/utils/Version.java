package org.logisticPlanning.utils.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.logisticPlanning.utils.text.TextUtils;

/**
 * This class encapsulates a project version
 */
public class Version {

  /** the build time stamp */
  private final long m_buildTimeStamp;

  /** the target platform */
  private final String m_targetPlatform;

  /** the major version */
  private final int m_major;

  /** the minor version */
  private final int m_minor;

  /** the revision */
  private final int m_revision;

  /** the version string */
  private String m_version;

  /** the date string */
  private String m_buildDate;

  /**
   * Create
   *
   * @param major
   *          the major version
   * @param minor
   *          the minor version
   * @param revision
   *          the revision
   * @param buildTimeStamp
   *          the build time stamp
   * @param targetPlatform
   *          the target platform
   */
  public Version(final int major, final int minor, final int revision,
      final long buildTimeStamp, final String targetPlatform) {
    super();

    this.m_major = major;
    this.m_minor = minor;
    this.m_revision = revision;

    if ((this.m_major < 0) || (this.m_minor < 0) || (this.m_revision < 0)) {
      throw new IllegalArgumentException(this.getVersion());
    }

    this.m_buildTimeStamp = buildTimeStamp;
    if ((this.m_buildTimeStamp <= 1357016400000l) || //
        (this.m_buildTimeStamp > (System.currentTimeMillis() + 31536000000l))) {
      throw new IllegalArgumentException("Impossible build time stamp: " + //$NON-NLS-1$
          this.m_buildTimeStamp);
    }

    this.m_targetPlatform = TextUtils.prepare(targetPlatform);
    if (this.m_targetPlatform == null) {
      throw new IllegalArgumentException(
          "Target platform cannot be empty.");//$NON-NLS-1$
    }
  }

  /**
   * Get the major version number
   *
   * @return the major version number
   */
  public final int getMajor() {
    return this.m_major;
  }

  /**
   * Get the minor version number
   *
   * @return the minor version number
   */
  public final int getMinor() {
    return this.m_minor;
  }

  /**
   * Get the revision number
   *
   * @return the revision number
   */
  public final int getRevision() {
    return this.m_revision;
  }

  /**
   * Get the version string
   *
   * @return the version string
   */
  public final String getVersion() {
    if (this.m_version == null) {
      this.m_version = ((((String.valueOf(this.m_major) + '.') + this.m_minor) + '.') + this.m_revision);
    }
    return this.m_version;
  }

  /**
   * Get the build time stamp
   *
   * @return the build time stamp
   */
  public final long getBuildTimeStamp() {
    return this.m_buildTimeStamp;
  }

  /**
   * Get the build date
   *
   * @return the build date
   */
  public final String getBuildDate() {
    if (this.m_buildDate == null) {
      this.m_buildDate = new SimpleDateFormat("yyyy-MM-dd").format(//$NON-NLS-1$
          new Date(this.m_buildTimeStamp));
    }
    return this.m_buildDate;
  }

  /**
   * Get the target platform
   *
   * @return the target platform
   */
  public final String getTargetPlatform() {
    return this.m_targetPlatform;
  }
}
