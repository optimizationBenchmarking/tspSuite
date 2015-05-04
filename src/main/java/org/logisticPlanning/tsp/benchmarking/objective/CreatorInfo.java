package org.logisticPlanning.tsp.benchmarking.objective;

import java.io.PrintStream;

import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.text.TextUtils;

/**
 * Instances of this class represent an information record to represent the
 * producer of a log file, i.e., the person who runs the experiment. The
 * purpose of this class is to allow the experimenter to store some static
 * information in the log files. This will provide anyone who accesses the
 * experimental data with the opportunity to contact the experimenter or to
 * find out which research group has developed the algorithm.
 */
public class CreatorInfo extends Configurable {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * : The name the researcher who conducted the experiment. &ndash; see
   * {@link #getResearcherName()}, {@link #setResearcherName(String)}, and
   * {@link #m_researcherName}
   */
  public static final String PARAM_RESEARCHER_NAME = "researcherName"; //$NON-NLS-1$

  /**
   * : The website of the researcher who conducted the experiment. &ndash;
   * see {@link #getResearcherWebsite()},
   * {@link #setResearcherWebsite(String)}, and
   * {@link #m_researcherWebsite}
   */
  public static final String PARAM_RESEARCHER_WEBSITE = "researcherWebsite"; //$NON-NLS-1$

  /**
   * : The email address of the researcher who conducted the experiment.
   * &ndash; see {@link #getResearcherEmail()},
   * {@link #setResearcherEmail(String)}, and {@link #m_researcherEmail}
   */
  public static final String PARAM_RESEARCHER_EMAIL = "researcherEmail"; //$NON-NLS-1$

  /**
   * : Additional information about the researcher who conducted the
   * experiment. &ndash; see {@link #getResearcherInfo()},
   * {@link #setResearcherInfo(String)}, and {@link #m_researcherInfo}
   */
  public static final String PARAM_RESEARCHER_INFO = "researcherInfo"; //$NON-NLS-1$

  /**
   * : The name of the research group that conducted the experiment run.
   * &ndash; see {@link #getGroupName()}, {@link #setGroupName(String)},
   * and {@link #m_groupName}
   */
  public static final String PARAM_GROUP_NAME = "groupName"; //$NON-NLS-1$

  /**
   * : The name of the institution hosting the research group that
   * conducted the experiment run. &ndash; see
   * {@link #getGroupInstitution()}, {@link #setGroupInstitution(String)},
   * and {@link #m_groupInstitution}
   */
  public static final String PARAM_GROUP_INSTITUTION = "groupInstitution"; //$NON-NLS-1$

  /**
   * : The address of the research group that conducted the experiment run.
   * &ndash; see {@link #getGroupAddress()},
   * {@link #setGroupAddress(String)}, and {@link #m_groupAddress}
   */
  public static final String PARAM_GROUP_ADDRESS = "groupAddress"; //$NON-NLS-1$

  /**
   * : The website of the research group that conducted the experiment run.
   * &ndash; see {@link #getGroupWebsite()},
   * {@link #setGroupWebsite(String)}, and {@link #m_groupWebsite}
   */
  public static final String PARAM_GROUP_WEBSITE = "groupWebsite"; //$NON-NLS-1$

  /**
   * : Additional information about the research group that conducted the
   * experiment run. &ndash; see {@link #getGroupInfo()},
   * {@link #setGroupInfo(String)}, and {@link #m_groupInfo}
   */
  public static final String PARAM_GROUP_INFO = "groupInfo"; //$NON-NLS-1$

  /**
   * : The name of the project during which the log file was created. This
   * may, e.g., be a personal project or a funded project. &ndash; see
   * {@link #getProjectName()}, {@link #setProjectName(String)}, and
   * {@link #m_projectName}
   */
  public static final String PARAM_PROJECT_NAME = "projectName"; //$NON-NLS-1$

  /**
   * : The website of the project during which the log file was created.
   * This may, e.g., be a personal project or a funded project. &ndash; see
   * {@link #getProjectWebsite()}, {@link #setProjectWebsite(String)}, and
   * {@link #m_projectWebsite}
   */
  public static final String PARAM_PROJECT_WEBSITE = "projectWebsite"; //$NON-NLS-1$

  /**
   * : Additional information regarding the project during which the log
   * file was created. This may, e.g., be a personal project or a funded
   * project. &ndash; see {@link #getProjectInfo()},
   * {@link #setProjectInfo(String)}, and {@link #m_projectInfo}
   */
  public static final String PARAM_PROJECT_INFO = "projectInfo"; //$NON-NLS-1$

  /**
   * : Information regarding the funding of the project during which the
   * log file was created. &ndash; see {@link #getFundingInfo()},
   * {@link #setFundingInfo(String)}, and {@link #m_fundingInfo}
   */
  public static final String PARAM_FUNDING_INFO = "fundingInfo"; //$NON-NLS-1$

  /**
   * : If the algorithm used in this experiment has been published
   * somewhere, then this field holds the information about that
   * publication. &ndash; see {@link #getPublicationInfo()},
   * {@link #setPublicationInfo(String)}, and {@link #m_publicationInfo}
   */
  public static final String PARAM_PUBLICATION_INFO = "publicationInfo"; //$NON-NLS-1$

  /**
   * : Copyright information. &ndash; see {@link #getCopyrightInfo()},
   * {@link #setCopyrightInfo(String)}, and {@link #m_copyrightInfo}
   */
  public static final String PARAM_COPYRIGHT_INFO = "copyrightInfo"; //$NON-NLS-1$

  /**
   * : Information about the license under which the software is provided
   * that was used to solve the benchmark cases. &ndash; see
   * {@link #getLicenseInfo()}, {@link #setLicenseInfo(String)}, and
   * {@link #m_licenseInfo}
   */
  public static final String PARAM_LICENSE_INFO = "licenseInfo"; //$NON-NLS-1$

  /**
   * : Any additional information text. &ndash; see
   * {@link #getAdditionalInfo()}, {@link #setAdditionalInfo(String)}, and
   * {@link #m_additionalInfo}
   */
  public static final String PARAM_ADDITIONAL_INFO = "additionalInfo"; //$NON-NLS-1$

  /**
   * The name the researcher who conducted the experiment. See
   * {@link #PARAM_RESEARCHER_NAME}, {@link #getResearcherName()}, and
   * {@link #setResearcherName(String)}
   *
   * @serial serializable field
   */
  private String m_researcherName;

  /**
   * The website of the researcher who conducted the experiment. See
   * {@link #PARAM_RESEARCHER_WEBSITE}, {@link #getResearcherWebsite()},
   * and {@link #setResearcherWebsite(String)}
   *
   * @serial serializable field
   */
  private String m_researcherWebsite;

  /**
   * The email address of the researcher who conducted the experiment. See
   * {@link #PARAM_RESEARCHER_EMAIL}, {@link #getResearcherEmail()}, and
   * {@link #setResearcherEmail(String)}
   *
   * @serial serializable field
   */
  private String m_researcherEmail;

  /**
   * Additional information about the researcher who conducted the
   * experiment. See {@link #PARAM_RESEARCHER_INFO},
   * {@link #getResearcherInfo()}, and {@link #setResearcherInfo(String)}
   *
   * @serial serializable field
   */
  private String m_researcherInfo;

  /**
   * The name of the research group that conducted the experiment run. See
   * {@link #PARAM_GROUP_NAME}, {@link #getGroupName()}, and
   * {@link #setGroupName(String)}
   *
   * @serial serializable field
   */
  private String m_groupName;

  /**
   * The name of the institution hosting the research group that conducted
   * the experiment run. See {@link #PARAM_GROUP_INSTITUTION},
   * {@link #getGroupInstitution()}, and
   * {@link #setGroupInstitution(String)}
   *
   * @serial serializable field
   */
  private String m_groupInstitution;

  /**
   * The address of the research group that conducted the experiment run.
   * See {@link #PARAM_GROUP_ADDRESS}, {@link #getGroupAddress()}, and
   * {@link #setGroupAddress(String)}
   *
   * @serial serializable field
   */
  private String m_groupAddress;

  /**
   * The website of the research group that conducted the experiment run.
   * See {@link #PARAM_GROUP_WEBSITE}, {@link #getGroupWebsite()}, and
   * {@link #setGroupWebsite(String)}
   *
   * @serial serializable field
   */
  private String m_groupWebsite;

  /**
   * Additional information about the research group that conducted the
   * experiment run. See {@link #PARAM_GROUP_INFO}, {@link #getGroupInfo()}
   * , and {@link #setGroupInfo(String)}
   *
   * @serial serializable field
   */
  private String m_groupInfo;

  /**
   * The name of the project during which the log file was created. This
   * may, e.g., be a personal project or a funded project. See
   * {@link #PARAM_PROJECT_NAME}, {@link #getProjectName()}, and
   * {@link #setProjectName(String)}
   *
   * @serial serializable field
   */
  private String m_projectName;

  /**
   * The website of the project during which the log file was created. This
   * may, e.g., be a personal project or a funded project. See
   * {@link #PARAM_PROJECT_WEBSITE}, {@link #getProjectWebsite()}, and
   * {@link #setProjectWebsite(String)}
   *
   * @serial serializable field
   */
  private String m_projectWebsite;

  /**
   * Additional information regarding the project during which the log file
   * was created. This may, e.g., be a personal project or a funded
   * project. See {@link #PARAM_PROJECT_INFO}, {@link #getProjectInfo()},
   * and {@link #setProjectInfo(String)}
   *
   * @serial serializable field
   */
  private String m_projectInfo;

  /**
   * Information regarding the funding of the project during which the log
   * file was created. See {@link #PARAM_FUNDING_INFO},
   * {@link #getFundingInfo()}, and {@link #setFundingInfo(String)}
   *
   * @serial serializable field
   */
  private String m_fundingInfo;

  /**
   * If the algorithm used in this experiment has been published somewhere,
   * then this field holds the information about that publication. See
   * {@link #PARAM_PUBLICATION_INFO}, {@link #getPublicationInfo()}, and
   * {@link #setPublicationInfo(String)}
   *
   * @serial serializable field
   */
  private String m_publicationInfo;

  /**
   * Copyright information. See {@link #PARAM_COPYRIGHT_INFO},
   * {@link #getCopyrightInfo()}, and {@link #setCopyrightInfo(String)}
   *
   * @serial serializable field
   */
  private String m_copyrightInfo;

  /**
   * Information about the license under which the software is provided
   * that was used to solve the benchmark cases. See
   * {@link #PARAM_LICENSE_INFO}, {@link #getLicenseInfo()}, and
   * {@link #setLicenseInfo(String)}
   *
   * @serial serializable field
   */
  private String m_licenseInfo;

  /**
   * Any additional information text. See {@link #PARAM_ADDITIONAL_INFO},
   * {@link #getAdditionalInfo()}, and {@link #setAdditionalInfo(String)}
   *
   * @serial serializable field
   */
  private String m_additionalInfo;

  /** the producer info */
  public CreatorInfo() {
    super("producerInfo"); //$NON-NLS-1$
  }

  // getters and setters for m_researcherName

  /**
   * Get The name the researcher who conducted the experiment. See
   * {@link #PARAM_RESEARCHER_NAME}, {@link #setResearcherName(String)},
   * and {@link #m_researcherName}.
   *
   * @return The name the researcher who conducted the experiment.
   */
  public final String getResearcherName() {
    return this.m_researcherName;
  }

  /**
   * Set The name the researcher who conducted the experiment. See
   * {@link #PARAM_RESEARCHER_NAME}, {@link #getResearcherName()}, and
   * {@link #m_researcherName}.
   *
   * @param researcherName
   *          The name the researcher who conducted the experiment.
   */
  public void setResearcherName(final String researcherName) {
    this.m_researcherName = TextUtils.prepare(researcherName);
  }

  // getters and setters for m_researcherWebsite

  /**
   * Get The website of the researcher who conducted the experiment. See
   * {@link #PARAM_RESEARCHER_WEBSITE},
   * {@link #setResearcherWebsite(String)}, and
   * {@link #m_researcherWebsite}.
   *
   * @return The website of the researcher who conducted the experiment.
   */
  public final String getResearcherWebsite() {
    return this.m_researcherWebsite;
  }

  /**
   * Set The website of the researcher who conducted the experiment. See
   * {@link #PARAM_RESEARCHER_WEBSITE}, {@link #getResearcherWebsite()},
   * and {@link #m_researcherWebsite}.
   *
   * @param researcherWebsite
   *          The website of the researcher who conducted the experiment.
   */
  public void setResearcherWebsite(final String researcherWebsite) {
    this.m_researcherWebsite = TextUtils.prepare(researcherWebsite);
  }

  // getters and setters for m_researcherEmail

  /**
   * Get The email address of the researcher who conducted the experiment.
   * See {@link #PARAM_RESEARCHER_EMAIL},
   * {@link #setResearcherEmail(String)}, and {@link #m_researcherEmail}.
   *
   * @return The email address of the researcher who conducted the
   *         experiment.
   */
  public final String getResearcherEmail() {
    return this.m_researcherEmail;
  }

  /**
   * Set The email address of the researcher who conducted the experiment.
   * See {@link #PARAM_RESEARCHER_EMAIL}, {@link #getResearcherEmail()},
   * and {@link #m_researcherEmail}.
   *
   * @param researcherEmail
   *          The email address of the researcher who conducted the
   *          experiment.
   */
  public void setResearcherEmail(final String researcherEmail) {
    this.m_researcherEmail = TextUtils.prepare(researcherEmail);
  }

  // getters and setters for m_researcherInfo

  /**
   * Get Additional information about the researcher who conducted the
   * experiment. See {@link #PARAM_RESEARCHER_INFO},
   * {@link #setResearcherInfo(String)}, and {@link #m_researcherInfo}.
   *
   * @return Additional information about the researcher who conducted the
   *         experiment.
   */
  public final String getResearcherInfo() {
    return this.m_researcherInfo;
  }

  /**
   * Set Additional information about the researcher who conducted the
   * experiment. See {@link #PARAM_RESEARCHER_INFO},
   * {@link #getResearcherInfo()}, and {@link #m_researcherInfo}.
   *
   * @param researcherInfo
   *          Additional information about the researcher who conducted the
   *          experiment.
   */
  public void setResearcherInfo(final String researcherInfo) {
    this.m_researcherInfo = TextUtils.prepare(researcherInfo);
  }

  // getters and setters for m_groupName

  /**
   * Get The name of the research group that conducted the experiment run.
   * See {@link #PARAM_GROUP_NAME}, {@link #setGroupName(String)}, and
   * {@link #m_groupName}.
   *
   * @return The name of the research group that conducted the experiment
   *         run.
   */
  public final String getGroupName() {
    return this.m_groupName;
  }

  /**
   * Set The name of the research group that conducted the experiment run.
   * See {@link #PARAM_GROUP_NAME}, {@link #getGroupName()}, and
   * {@link #m_groupName}.
   *
   * @param groupName
   *          The name of the research group that conducted the experiment
   *          run.
   */
  public void setGroupName(final String groupName) {
    this.m_groupName = TextUtils.prepare(groupName);
  }

  // getters and setters for m_groupInstitution

  /**
   * Get The name of the institution hosting the research group that
   * conducted the experiment run. See {@link #PARAM_GROUP_INSTITUTION},
   * {@link #setGroupInstitution(String)}, and {@link #m_groupInstitution}.
   *
   * @return The name of the institution hosting the research group that
   *         conducted the experiment run.
   */
  public final String getGroupInstitution() {
    return this.m_groupInstitution;
  }

  /**
   * Set The name of the institution hosting the research group that
   * conducted the experiment run. See {@link #PARAM_GROUP_INSTITUTION},
   * {@link #getGroupInstitution()}, and {@link #m_groupInstitution}.
   *
   * @param groupInstitution
   *          The name of the institution hosting the research group that
   *          conducted the experiment run.
   */
  public void setGroupInstitution(final String groupInstitution) {
    this.m_groupInstitution = TextUtils.prepare(groupInstitution);
  }

  // getters and setters for m_groupAddress

  /**
   * Get The address of the research group that conducted the experiment
   * run. See {@link #PARAM_GROUP_ADDRESS},
   * {@link #setGroupAddress(String)}, and {@link #m_groupAddress}.
   *
   * @return The address of the research group that conducted the
   *         experiment run.
   */
  public final String getGroupAddress() {
    return this.m_groupAddress;
  }

  /**
   * Set The address of the research group that conducted the experiment
   * run. See {@link #PARAM_GROUP_ADDRESS}, {@link #getGroupAddress()}, and
   * {@link #m_groupAddress}.
   *
   * @param groupAddress
   *          The address of the research group that conducted the
   *          experiment run.
   */
  public void setGroupAddress(final String groupAddress) {
    this.m_groupAddress = TextUtils.prepare(groupAddress);
  }

  // getters and setters for m_groupWebsite

  /**
   * Get The website of the research group that conducted the experiment
   * run. See {@link #PARAM_GROUP_WEBSITE},
   * {@link #setGroupWebsite(String)}, and {@link #m_groupWebsite}.
   *
   * @return The website of the research group that conducted the
   *         experiment run.
   */
  public final String getGroupWebsite() {
    return this.m_groupWebsite;
  }

  /**
   * Set The website of the research group that conducted the experiment
   * run. See {@link #PARAM_GROUP_WEBSITE}, {@link #getGroupWebsite()}, and
   * {@link #m_groupWebsite}.
   *
   * @param groupWebsite
   *          The website of the research group that conducted the
   *          experiment run.
   */
  public void setGroupWebsite(final String groupWebsite) {
    this.m_groupWebsite = TextUtils.prepare(groupWebsite);
  }

  // getters and setters for m_groupInfo

  /**
   * Get Additional information about the research group that conducted the
   * experiment run. See {@link #PARAM_GROUP_INFO},
   * {@link #setGroupInfo(String)}, and {@link #m_groupInfo}.
   *
   * @return Additional information about the research group that conducted
   *         the experiment run.
   */
  public final String getGroupInfo() {
    return this.m_groupInfo;
  }

  /**
   * Set Additional information about the research group that conducted the
   * experiment run. See {@link #PARAM_GROUP_INFO}, {@link #getGroupInfo()}
   * , and {@link #m_groupInfo}.
   *
   * @param groupInfo
   *          Additional information about the research group that
   *          conducted the experiment run.
   */
  public void setGroupInfo(final String groupInfo) {
    this.m_groupInfo = TextUtils.prepare(groupInfo);
  }

  // getters and setters for m_projectName

  /**
   * Get The name of the project during which the log file was created.
   * This may, e.g., be a personal project or a funded project. See
   * {@link #PARAM_PROJECT_NAME}, {@link #setProjectName(String)}, and
   * {@link #m_projectName}.
   *
   * @return The name of the project during which the log file was created.
   *         This may, e.g., be a personal project or a funded project.
   */
  public final String getProjectName() {
    return this.m_projectName;
  }

  /**
   * Set The name of the project during which the log file was created.
   * This may, e.g., be a personal project or a funded project. See
   * {@link #PARAM_PROJECT_NAME}, {@link #getProjectName()}, and
   * {@link #m_projectName}.
   *
   * @param projectName
   *          The name of the project during which the log file was
   *          created. This may, e.g., be a personal project or a funded
   *          project.
   */
  public void setProjectName(final String projectName) {
    this.m_projectName = TextUtils.prepare(projectName);
  }

  // getters and setters for m_projectWebsite

  /**
   * Get The website of the project during which the log file was created.
   * This may, e.g., be a personal project or a funded project. See
   * {@link #PARAM_PROJECT_WEBSITE}, {@link #setProjectWebsite(String)},
   * and {@link #m_projectWebsite}.
   *
   * @return The website of the project during which the log file was
   *         created. This may, e.g., be a personal project or a funded
   *         project.
   */
  public final String getProjectWebsite() {
    return this.m_projectWebsite;
  }

  /**
   * Set The website of the project during which the log file was created.
   * This may, e.g., be a personal project or a funded project. See
   * {@link #PARAM_PROJECT_WEBSITE}, {@link #getProjectWebsite()}, and
   * {@link #m_projectWebsite}.
   *
   * @param projectWebsite
   *          The website of the project during which the log file was
   *          created. This may, e.g., be a personal project or a funded
   *          project.
   */
  public void setProjectWebsite(final String projectWebsite) {
    this.m_projectWebsite = TextUtils.prepare(projectWebsite);
  }

  // getters and setters for m_projectInfo

  /**
   * Get Additional information regarding the project during which the log
   * file was created. This may, e.g., be a personal project or a funded
   * project. See {@link #PARAM_PROJECT_INFO},
   * {@link #setProjectInfo(String)}, and {@link #m_projectInfo}.
   *
   * @return Additional information regarding the project during which the
   *         log file was created. This may, e.g., be a personal project or
   *         a funded project.
   */
  public final String getProjectInfo() {
    return this.m_projectInfo;
  }

  /**
   * Set Additional information regarding the project during which the log
   * file was created. This may, e.g., be a personal project or a funded
   * project. See {@link #PARAM_PROJECT_INFO}, {@link #getProjectInfo()},
   * and {@link #m_projectInfo}.
   *
   * @param projectInfo
   *          Additional information regarding the project during which the
   *          log file was created. This may, e.g., be a personal project
   *          or a funded project.
   */
  public void setProjectInfo(final String projectInfo) {
    this.m_projectInfo = TextUtils.prepare(projectInfo);
  }

  // getters and setters for m_fundingInfo

  /**
   * Get Information regarding the funding of the project during which the
   * log file was created. See {@link #PARAM_FUNDING_INFO},
   * {@link #setFundingInfo(String)}, and {@link #m_fundingInfo}.
   *
   * @return Information regarding the funding of the project during which
   *         the log file was created.
   */
  public final String getFundingInfo() {
    return this.m_fundingInfo;
  }

  /**
   * Set Information regarding the funding of the project during which the
   * log file was created. See {@link #PARAM_FUNDING_INFO},
   * {@link #getFundingInfo()}, and {@link #m_fundingInfo}.
   *
   * @param fundingInfo
   *          Information regarding the funding of the project during which
   *          the log file was created.
   */
  public void setFundingInfo(final String fundingInfo) {
    this.m_fundingInfo = TextUtils.prepare(fundingInfo);
  }

  // getters and setters for m_publicationInfo

  /**
   * Get If the algorithm used in this experiment has been published
   * somewhere, then this field holds the information about that
   * publication. See {@link #PARAM_PUBLICATION_INFO},
   * {@link #setPublicationInfo(String)}, and {@link #m_publicationInfo}.
   *
   * @return If the algorithm used in this experiment has been published
   *         somewhere, then this field holds the information about that
   *         publication.
   */
  public final String getPublicationInfo() {
    return this.m_publicationInfo;
  }

  /**
   * Set If the algorithm used in this experiment has been published
   * somewhere, then this field holds the information about that
   * publication. See {@link #PARAM_PUBLICATION_INFO},
   * {@link #getPublicationInfo()}, and {@link #m_publicationInfo}.
   *
   * @param publicationInfo
   *          If the algorithm used in this experiment has been published
   *          somewhere, then this field holds the information about that
   *          publication.
   */
  public void setPublicationInfo(final String publicationInfo) {
    this.m_publicationInfo = TextUtils.prepare(publicationInfo);
  }

  // getters and setters for m_copyrightInfo

  /**
   * Get Copyright information. See {@link #PARAM_COPYRIGHT_INFO},
   * {@link #setCopyrightInfo(String)}, and {@link #m_copyrightInfo}.
   *
   * @return Copyright information.
   */
  public final String getCopyrightInfo() {
    return this.m_copyrightInfo;
  }

  /**
   * Set Copyright information. See {@link #PARAM_COPYRIGHT_INFO},
   * {@link #getCopyrightInfo()}, and {@link #m_copyrightInfo}.
   *
   * @param copyrightInfo
   *          Copyright information.
   */
  public void setCopyrightInfo(final String copyrightInfo) {
    this.m_copyrightInfo = TextUtils.prepare(copyrightInfo);
  }

  // getters and setters for m_licenseInfo

  /**
   * Get Information about the license under which the software is provided
   * that was used to solve the benchmark cases. See
   * {@link #PARAM_LICENSE_INFO}, {@link #setLicenseInfo(String)}, and
   * {@link #m_licenseInfo}.
   *
   * @return Information about the license under which the software is
   *         provided that was used to solve the benchmark cases.
   */
  public final String getLicenseInfo() {
    return this.m_licenseInfo;
  }

  /**
   * Set Information about the license under which the software is provided
   * that was used to solve the benchmark cases. See
   * {@link #PARAM_LICENSE_INFO}, {@link #getLicenseInfo()}, and
   * {@link #m_licenseInfo}.
   *
   * @param licenseInfo
   *          Information about the license under which the software is
   *          provided that was used to solve the benchmark cases.
   */
  public void setLicenseInfo(final String licenseInfo) {
    this.m_licenseInfo = TextUtils.prepare(licenseInfo);
  }

  // getters and setters for m_additionalInfo

  /**
   * Get Any additional information text. See
   * {@link #PARAM_ADDITIONAL_INFO}, {@link #setAdditionalInfo(String)},
   * and {@link #m_additionalInfo}.
   *
   * @return Any additional information text.
   */
  public final String getAdditionalInfo() {
    return this.m_additionalInfo;
  }

  /**
   * Set Any additional information text. See
   * {@link #PARAM_ADDITIONAL_INFO}, {@link #getAdditionalInfo()}, and
   * {@link #m_additionalInfo}.
   *
   * @param additionalInfo
   *          Any additional information text.
   */
  public void setAdditionalInfo(final String additionalInfo) {
    this.m_additionalInfo = TextUtils.prepare(additionalInfo);
  }

  // the inherited methods for configuration and value printing

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    String v;

    super.configure(config);

    v = config.getString(CreatorInfo.PARAM_RESEARCHER_NAME,
        Configuration.IMPOSSIBLE_VALUE_STRING);
    if (v != Configuration.IMPOSSIBLE_VALUE_STRING) {
      this.setResearcherName(Configuration.unpack(v));
    }
    v = config.getString(CreatorInfo.PARAM_RESEARCHER_WEBSITE,
        Configuration.IMPOSSIBLE_VALUE_STRING);
    if (v != Configuration.IMPOSSIBLE_VALUE_STRING) {
      this.setResearcherWebsite(Configuration.unpack(v));
    }
    v = config.getString(CreatorInfo.PARAM_RESEARCHER_EMAIL,
        Configuration.IMPOSSIBLE_VALUE_STRING);
    if (v != Configuration.IMPOSSIBLE_VALUE_STRING) {
      this.setResearcherEmail(Configuration.unpack(v));
    }
    v = config.getString(CreatorInfo.PARAM_RESEARCHER_INFO,
        Configuration.IMPOSSIBLE_VALUE_STRING);
    if (v != Configuration.IMPOSSIBLE_VALUE_STRING) {
      this.setResearcherInfo(Configuration.unpack(v));
    }
    v = config.getString(CreatorInfo.PARAM_GROUP_NAME,
        Configuration.IMPOSSIBLE_VALUE_STRING);
    if (v != Configuration.IMPOSSIBLE_VALUE_STRING) {
      this.setGroupName(Configuration.unpack(v));
    }
    v = config.getString(CreatorInfo.PARAM_GROUP_INSTITUTION,
        Configuration.IMPOSSIBLE_VALUE_STRING);
    if (v != Configuration.IMPOSSIBLE_VALUE_STRING) {
      this.setGroupInstitution(Configuration.unpack(v));
    }
    v = config.getString(CreatorInfo.PARAM_GROUP_ADDRESS,
        Configuration.IMPOSSIBLE_VALUE_STRING);
    if (v != Configuration.IMPOSSIBLE_VALUE_STRING) {
      this.setGroupAddress(Configuration.unpack(v));
    }
    v = config.getString(CreatorInfo.PARAM_GROUP_WEBSITE,
        Configuration.IMPOSSIBLE_VALUE_STRING);
    if (v != Configuration.IMPOSSIBLE_VALUE_STRING) {
      this.setGroupWebsite(Configuration.unpack(v));
    }
    v = config.getString(CreatorInfo.PARAM_GROUP_INFO,
        Configuration.IMPOSSIBLE_VALUE_STRING);
    if (v != Configuration.IMPOSSIBLE_VALUE_STRING) {
      this.setGroupInfo(Configuration.unpack(v));
    }
    v = config.getString(CreatorInfo.PARAM_PROJECT_NAME,
        Configuration.IMPOSSIBLE_VALUE_STRING);
    if (v != Configuration.IMPOSSIBLE_VALUE_STRING) {
      this.setProjectName(Configuration.unpack(v));
    }
    v = config.getString(CreatorInfo.PARAM_PROJECT_WEBSITE,
        Configuration.IMPOSSIBLE_VALUE_STRING);
    if (v != Configuration.IMPOSSIBLE_VALUE_STRING) {
      this.setProjectWebsite(Configuration.unpack(v));
    }
    v = config.getString(CreatorInfo.PARAM_PROJECT_INFO,
        Configuration.IMPOSSIBLE_VALUE_STRING);
    if (v != Configuration.IMPOSSIBLE_VALUE_STRING) {
      this.setProjectInfo(Configuration.unpack(v));
    }
    v = config.getString(CreatorInfo.PARAM_FUNDING_INFO,
        Configuration.IMPOSSIBLE_VALUE_STRING);
    if (v != Configuration.IMPOSSIBLE_VALUE_STRING) {
      this.setFundingInfo(Configuration.unpack(v));
    }
    v = config.getString(CreatorInfo.PARAM_PUBLICATION_INFO,
        Configuration.IMPOSSIBLE_VALUE_STRING);
    if (v != Configuration.IMPOSSIBLE_VALUE_STRING) {
      this.setPublicationInfo(Configuration.unpack(v));
    }
    v = config.getString(CreatorInfo.PARAM_COPYRIGHT_INFO,
        Configuration.IMPOSSIBLE_VALUE_STRING);
    if (v != Configuration.IMPOSSIBLE_VALUE_STRING) {
      this.setCopyrightInfo(Configuration.unpack(v));
    }
    v = config.getString(CreatorInfo.PARAM_LICENSE_INFO,
        Configuration.IMPOSSIBLE_VALUE_STRING);
    if (v != Configuration.IMPOSSIBLE_VALUE_STRING) {
      this.setLicenseInfo(Configuration.unpack(v));
    }
    v = config.getString(CreatorInfo.PARAM_ADDITIONAL_INFO,
        Configuration.IMPOSSIBLE_VALUE_STRING);
    if (v != Configuration.IMPOSSIBLE_VALUE_STRING) {
      this.setAdditionalInfo(Configuration.unpack(v));
    }
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    if (this.m_researcherName != null) {
      Configurable.printKey(CreatorInfo.PARAM_RESEARCHER_NAME, ps);
      ps.println(this.m_researcherName);
    }
    if (this.m_researcherWebsite != null) {
      Configurable.printKey(CreatorInfo.PARAM_RESEARCHER_WEBSITE, ps);
      ps.println(this.m_researcherWebsite);
    }
    if (this.m_researcherEmail != null) {
      Configurable.printKey(CreatorInfo.PARAM_RESEARCHER_EMAIL, ps);
      ps.println(this.m_researcherEmail);
    }
    if (this.m_researcherInfo != null) {
      Configurable.printKey(CreatorInfo.PARAM_RESEARCHER_INFO, ps);
      ps.println(this.m_researcherInfo);
    }
    if (this.m_groupName != null) {
      Configurable.printKey(CreatorInfo.PARAM_GROUP_NAME, ps);
      ps.println(this.m_groupName);
    }
    if (this.m_groupInstitution != null) {
      Configurable.printKey(CreatorInfo.PARAM_GROUP_INSTITUTION, ps);
      ps.println(this.m_groupInstitution);
    }
    if (this.m_groupAddress != null) {
      Configurable.printKey(CreatorInfo.PARAM_GROUP_ADDRESS, ps);
      ps.println(this.m_groupAddress);
    }
    if (this.m_groupWebsite != null) {
      Configurable.printKey(CreatorInfo.PARAM_GROUP_WEBSITE, ps);
      ps.println(this.m_groupWebsite);
    }
    if (this.m_groupInfo != null) {
      Configurable.printKey(CreatorInfo.PARAM_GROUP_INFO, ps);
      ps.println(this.m_groupInfo);
    }
    if (this.m_projectName != null) {
      Configurable.printKey(CreatorInfo.PARAM_PROJECT_NAME, ps);
      ps.println(this.m_projectName);
    }
    if (this.m_projectWebsite != null) {
      Configurable.printKey(CreatorInfo.PARAM_PROJECT_WEBSITE, ps);
      ps.println(this.m_projectWebsite);
    }
    if (this.m_projectInfo != null) {
      Configurable.printKey(CreatorInfo.PARAM_PROJECT_INFO, ps);
      ps.println(this.m_projectInfo);
    }
    if (this.m_fundingInfo != null) {
      Configurable.printKey(CreatorInfo.PARAM_FUNDING_INFO, ps);
      ps.println(this.m_fundingInfo);
    }
    if (this.m_publicationInfo != null) {
      Configurable.printKey(CreatorInfo.PARAM_PUBLICATION_INFO, ps);
      ps.println(this.m_publicationInfo);
    }
    if (this.m_copyrightInfo != null) {
      Configurable.printKey(CreatorInfo.PARAM_COPYRIGHT_INFO, ps);
      ps.println(this.m_copyrightInfo);
    }
    if (this.m_licenseInfo != null) {
      Configurable.printKey(CreatorInfo.PARAM_LICENSE_INFO, ps);
      ps.println(this.m_licenseInfo);
    }
    if (this.m_additionalInfo != null) {
      Configurable.printKey(CreatorInfo.PARAM_ADDITIONAL_INFO, ps);
      ps.println(this.m_additionalInfo);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    Configurable.printKey(CreatorInfo.PARAM_RESEARCHER_NAME, ps);
    ps.println("The name the researcher who conducted the experiment."); //$NON-NLS-1$
    Configurable.printKey(CreatorInfo.PARAM_RESEARCHER_WEBSITE, ps);
    ps.println("The website of the researcher who conducted the experiment."); //$NON-NLS-1$
    Configurable.printKey(CreatorInfo.PARAM_RESEARCHER_EMAIL, ps);
    ps.println("The email address  of the researcher who conducted the experiment."); //$NON-NLS-1$
    Configurable.printKey(CreatorInfo.PARAM_RESEARCHER_INFO, ps);
    ps.println("Additional information about the researcher who conducted the experiment."); //$NON-NLS-1$
    Configurable.printKey(CreatorInfo.PARAM_GROUP_NAME, ps);
    ps.println("The name of the research group that conducted the experiment run."); //$NON-NLS-1$
    Configurable.printKey(CreatorInfo.PARAM_GROUP_INSTITUTION, ps);
    ps.println("The name of the institution hosting the research group that conducted the experiment run."); //$NON-NLS-1$
    Configurable.printKey(CreatorInfo.PARAM_GROUP_ADDRESS, ps);
    ps.println("The address of the research group that conducted the experiment run."); //$NON-NLS-1$
    Configurable.printKey(CreatorInfo.PARAM_GROUP_WEBSITE, ps);
    ps.println("The website of the research group that conducted the experiment run."); //$NON-NLS-1$
    Configurable.printKey(CreatorInfo.PARAM_GROUP_INFO, ps);
    ps.println("Additional information about the research group that conducted the experiment run."); //$NON-NLS-1$
    Configurable.printKey(CreatorInfo.PARAM_PROJECT_NAME, ps);
    ps.println("The name of the project during which the log file was created. This may, e.g., be a personal project or a funded project."); //$NON-NLS-1$
    Configurable.printKey(CreatorInfo.PARAM_PROJECT_WEBSITE, ps);
    ps.println("The website of the project during which the log file was created. This may, e.g., be a personal project or a funded project."); //$NON-NLS-1$
    Configurable.printKey(CreatorInfo.PARAM_PROJECT_INFO, ps);
    ps.println("Additional information regarding the project during which the log file was created. This may, e.g., be a personal project or a funded project."); //$NON-NLS-1$
    Configurable.printKey(CreatorInfo.PARAM_FUNDING_INFO, ps);
    ps.println("Information regarding the funding of the project during which the log file was created."); //$NON-NLS-1$
    Configurable.printKey(CreatorInfo.PARAM_PUBLICATION_INFO, ps);
    ps.println("If the algorithm used in this experiment has been published somewhere, then this field holds the information about that publication."); //$NON-NLS-1$
    Configurable.printKey(CreatorInfo.PARAM_COPYRIGHT_INFO, ps);
    ps.println("Copyright information."); //$NON-NLS-1$
    Configurable.printKey(CreatorInfo.PARAM_LICENSE_INFO, ps);
    ps.println("Information about the license under which the software is provided that was used to solve the benchmark cases."); //$NON-NLS-1$
    Configurable.printKey(CreatorInfo.PARAM_ADDITIONAL_INFO, ps);
    ps.println("Any additional information text."); //$NON-NLS-1$
  }

  /**
   * Check if any internal information has been set
   *
   * @return {@code true} if and only if some internal information has been
   *         set
   */
  public boolean isEmpty() {
    return ((this.m_researcherName == null)
        && (this.m_researcherWebsite == null)
        && (this.m_researcherEmail == null)
        && (this.m_researcherInfo == null) && (this.m_groupName == null)
        && (this.m_groupInstitution == null)
        && (this.m_groupAddress == null) && (this.m_groupWebsite == null)
        && (this.m_groupInfo == null) && (this.m_projectName == null)
        && (this.m_projectWebsite == null) && (this.m_projectInfo == null)
        && (this.m_fundingInfo == null)
        && (this.m_publicationInfo == null)
        && (this.m_copyrightInfo == null) && (this.m_licenseInfo == null) && (this.m_additionalInfo == null));
  }

}
