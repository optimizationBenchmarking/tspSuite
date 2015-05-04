package org.logisticPlanning.utils.math.units;

import org.logisticPlanning.utils.text.TextUtils;

/**
 * <p>
 * Units of length and corresponding conversion factors according
 * to&nbsp;[<a href="#cite_BCG2006TISOUSCFFGU"
 * style="font-weight:bold">1</a>, <a href="#cite_UCO2009LYUOM"
 * style="font-weight:bold">2</a>, <a href="#cite_GG2013CMCOTWS1"
 * style="font-weight:bold">3</a>, <a href="#cite_R2012ESDMR"
 * style="font-weight:bold">4</a>, <a href="#cite_J2010TKHCUICUCT"
 * style="font-weight:bold">5</a>].
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_BCG2006TISOUSCFFGU" />Kenneth Butcher, Linda
 * Crown, and&nbsp;Elizabeth J. Gentry: <span
 * style="font-weight:bold">&ldquo;The International System of Units (SI)
 * &#8211; Conversion Factors for General Use,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;1038, May&nbsp;2006. <div>link: [<a
 * href="http://www.nist.gov/pml/wmd/metric/upload/SP1038.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_UCO2009LYUOM" />Dmitry Sychov: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Light Year, Unit
 * of Measure,&rdquo;</span> (Website), September&nbsp;7, 2009. <div>link:
 * [<a href=
 * "http://www.unitconversion.org/length/light-year-conversion.html"
 * >1</a>]< /div></div></li>
 * <li><div><span id="cite_GG2013CMCOTWS1" />Sergey Gershtein and&nbsp;Anna
 * Gershtein: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Convert-me.com
 * &#8211; on the Web since 1996,&rdquo;</span> (Website), July&nbsp;9,
 * 2013. <div>link: [<a
 * href="http://www.convert-me.com/">1</a>]</div></div></li>
 * <li><div><span id="cite_R2012ESDMR" />Nola Taylor Redd: <span
 * style="font-weight:bold">&ldquo;Earth-Sun Distance Measurement
 * Redefined,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Space.com</span>
 * September&nbsp;24, 2012; published by Ogden, UT, USA: TechMediaNetwork.
 * <div>link: [<a href=
 * "http://www.space.com/17733-earth-sun-distance-astronomical-unit.html"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_J2010TKHCUICUCT" />James Peirce: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Three Kingdoms
 * History: Chinese Units &#8211; Imperial Chinese Unit Conversion
 * Tool,&rdquo;</span> (Website), 2010, Concord, CA, USA: Kongming's
 * Archives' Foundation. <div>link: [<a
 * href="http://kongming.net/novel/chinese_units/">1</a>]</div></div></li>
 * </ol>
 */
public enum ELength {

  /** angstrom */
  ANGSTROM("\u00e5ngstr\u00f6m", "\u00c5"), //$NON-NLS-1$//$NON-NLS-2$
  /** micrometer */
  NANOMETER("nanometer", "nm"), //$NON-NLS-1$//$NON-NLS-2$
  /** micrometer */
  MICROMETER("micrometer", "\u00b5m"), //$NON-NLS-1$//$NON-NLS-2$
  /** millimeters */
  MILLIMETER("milliemter", "mm"), //$NON-NLS-1$//$NON-NLS-2$
  /** centimeters */
  CENTIMETER("centimeter", "cm"), //$NON-NLS-1$//$NON-NLS-2$
  /** decimeters */
  DECIMETER("decimeter", "dm"), //$NON-NLS-1$//$NON-NLS-2$
  /** meters */
  METER("meter", "m"), //$NON-NLS-1$//$NON-NLS-2$
  /** kilo meters */
  KILOMETER("kilometer", "km"), //$NON-NLS-1$//$NON-NLS-2$

  /** point */
  POINT("point", "pt"), //$NON-NLS-1$//$NON-NLS-2$
  /** postscript-pica */
  POINT_POSTSCRIPT("point (ps)", null, "point (postscript)"), //$NON-NLS-1$//$NON-NLS-2$
  /** European/didot point */
  POINT_EUROPEAN_DIDOT("point (Didot)", null, "point (European/Didot)"), //$NON-NLS-1$//$NON-NLS-2$
  /** European/didot cicero */
  CICERO_EUROPEAN_DIDOT("cicero", null, "cicero (Europan/Didot)"), //$NON-NLS-1$//$NON-NLS-2$
  /** pica */
  PICA("pica"), //$NON-NLS-1$
  /** postscript-pica */
  PICA_POSTSCRIPT("pica (ps)", null, "pica (postscript)"), //$NON-NLS-1$//$NON-NLS-2$
  /** pixel */
  PIXEL("pixel", "px"), //$NON-NLS-1$//$NON-NLS-2$
  /** twip */
  TWIP("twip"), //$NON-NLS-1$

  /** micro inch */
  MICROINCH("microinch", "\u00b5in"), //$NON-NLS-1$//$NON-NLS-2$
  /** milliinch */
  MIL("mil"), //$NON-NLS-1$
  /** barleycorn */
  BARLEYCORN("barleycorn"), //$NON-NLS-1$
  /** inch */
  INCH("inch", "in"), //$NON-NLS-1$//$NON-NLS-2$
  /** nail */
  NAIL("nail"), //$NON-NLS-1$
  /** palm */
  PALM("palm"), //$NON-NLS-1$
  /** hand */
  HAND("hand"), //$NON-NLS-1$
  /** span */
  SPAN("span"), //$NON-NLS-1$
  /** line */
  LINE("line"), //$NON-NLS-1$
  /** foot */
  FOOT("foot", "ft"), //$NON-NLS-1$//$NON-NLS-2$
  /** cubit */
  CUBIT("cubit"), //$NON-NLS-1$
  /** yard */
  YARD("yard", "yd"), //$NON-NLS-1$//$NON-NLS-2$
  /** ell */
  ELL("ell"), //$NON-NLS-1$
  /** fathom */
  FATHOM("fathom"), //$NON-NLS-1$
  /** perch or rod */
  ROD("rod", "rd"), //$NON-NLS-1$//$NON-NLS-2$
  /** chain */
  CHAIN("chain"), //$NON-NLS-1$
  /** furlong */
  FURLONG("furlong"), //$NON-NLS-1$
  /** us survey foot */
  FOOT_US_SURVEY("survey foot", null, "US survey foot"), //$NON-NLS-1$//$NON-NLS-2$
  /** international mile */
  MILE_INTERNATIONAL("mile", null, "mile (international)"), //$NON-NLS-1$//$NON-NLS-2$
  /** league */
  LEAGUE("league"), //$NON-NLS-1$
  /** nautical mile */
  MILE_NAUTICAL("nautical mile"), //$NON-NLS-1$
  /** geographical mile */
  MILE_GEOGRAPHICAL("geographical mile", null, "German geographical mile"), //$NON-NLS-1$//$NON-NLS-2$

  /** the chinese li */
  LI_CHINESE("li", "\u5e02\u91cc"), //$NON-NLS-1$//$NON-NLS-2$
  /** the chinese yin */
  YIN_CHINESE("yin", "\u5f15"), //$NON-NLS-1$//$NON-NLS-2$
  /** the chinese zhang */
  ZHANG_CHINESE("zhang", "\u5e02\u4e08"), //$NON-NLS-1$//$NON-NLS-2$
  /** the chinese bu */
  BU_CHINESE("bu", "\u6b65"), //$NON-NLS-1$//$NON-NLS-2$
  /** the chinese chi */
  CHI_CHINESE("chi", "\u5e02\u5c3a"), //$NON-NLS-1$//$NON-NLS-2$
  /** the chinese cun */
  CUN_CHINESE("cun", "\u5e02\u5bf8"), //$NON-NLS-1$//$NON-NLS-2$
  /** the chinese fen */
  FEN_CHINESE("fen", "\u5e02\u5206"), //$NON-NLS-1$//$NON-NLS-2$
  /** the small chinese li */
  LI_CHINESE_SMALL("li(small)", "\u5e02\u5398", //$NON-NLS-1$//$NON-NLS-2$
      "small li"), //$NON-NLS-1$
  /** the chinese hao */
  HAO_CHINESE("hao", "\u6beb"), //$NON-NLS-1$//$NON-NLS-2$
  /** the chinese si */
  SI_CHINESE("si", "\u4e1d"), //$NON-NLS-1$//$NON-NLS-2$
  /** the chinese hu */
  HU_CHINESE("hu", "\u5ffd"), //$NON-NLS-1$//$NON-NLS-2$

  /** a light second */
  LIGHT_SECOND("light second"), //$NON-NLS-1$
  /** a light minute */
  LIGHT_MINUTE("light minute"), //$NON-NLS-1$
  /** an astronomical unit */
  ASTRONOMICAL_UNIT("astronomical unit", "AU"), //$NON-NLS-1$//$NON-NLS-2$
  /** a light year */
  LIGHT_YEAR("light year", "ly"), //$NON-NLS-1$//$NON-NLS-2$
  /** a parsec */
  PARSEC("parsec"), //$NON-NLS-1$
  /** red shift */
  RED_SHIFT("red shift", "z"), //$NON-NLS-1$//$NON-NLS-2$
  ;

  /** the nanometer */
  public static final ELength NM = NANOMETER;
  /** the micron */
  public static final ELength MICRON = MICROMETER;
  /** the micrometer */
  public static final ELength UM = MICROMETER;
  /** the millimeter */
  public static final ELength MM = MILLIMETER;
  /** the centimeter */
  public static final ELength CM = CENTIMETER;
  /** the deciimeter */
  public static final ELength DM = DECIMETER;
  /** the meter */
  public static final ELength M = METER;
  /** the kilometer */
  public static final ELength KM = KILOMETER;
  /** the inch */
  public static final ELength IN = INCH;
  /** the foot */
  public static final ELength FT = FOOT;
  /** the yard */
  public static final ELength YD = YARD;
  /** the points */
  public static final ELength PT = POINT;
  /** the astronomical unit */
  public static final ELength AU = ASTRONOMICAL_UNIT;
  /** the light year */
  public static final ELength LY = LIGHT_YEAR;
  /** the perch */
  public static final ELength PERCH = ROD;
  /** the rod */
  public static final ELength RD = ROD;
  /** the diopter */
  public static final ELength DIOPTER = METER;
  /** the red shift */
  public static final ELength Z = RED_SHIFT;
  /** the ems/pica */
  public static final ELength EMS = PICA;

  /** the conversion table */
  private static final double[][] CONVERT;

  static {
    final int length;
    _Compiler comp;

    length = (RED_SHIFT.ordinal() + 1);
    comp = new _Compiler(length);

    // 5 sets of independent measures (now without transformations between
    // each
    // other):

    // 1. metric
    comp._set(ANGSTROM, NANOMETER, 1l, 10l);
    comp._set(KILOMETER, METER, 1000l, 1l);
    comp._set(METER, DECIMETER, 10l, 1l);
    comp._set(DECIMETER, CENTIMETER, 10l, 1l);
    comp._set(CENTIMETER, MILLIMETER, 10l, 1l);
    comp._set(MILLIMETER, MICROMETER, 1000l, 1l);
    comp._set(MICROMETER, NANOMETER, 1000l, 1l);
    comp._set(INCH, MIL, 1000l, 1l);

    // 2. british/imperial
    comp._set(LEAGUE, FURLONG, 24l, 1l);
    comp._set(FATHOM, YARD, 2l, 1l);
    comp._set(FURLONG, CHAIN, 10l, 1l);
    comp._set(CHAIN, ELength.PERCH, 4l, 1l);
    comp._set(ELength.PERCH, CUBIT, 11l, 1l);
    comp._set(CUBIT, FOOT, 15l, 10l);
    comp._set(ELL, FOOT, 375l, 100l);
    comp._set(FOOT, INCH, 12l, 1l);
    comp._set(FOOT, LINE, 144l, 1l);
    comp._set(FOOT, BARLEYCORN, 36l, 1l);
    comp._set(SPAN, NAIL, 4l, 1l);
    comp._set(YARD, SPAN, 4l, 1l);
    comp._set(FOOT, PALM, 4l, 1l);
    comp._set(FOOT, HAND, 3l, 1l);
    comp._set(YARD, FOOT, 3l, 1l);
    comp._set(MIL, MICROINCH, 1000l, 1l);
    comp._set(MILE_INTERNATIONAL, FURLONG, 8l, 1l);

    // 3. point/printing
    comp._set(PICA, POINT, 12l, 1l);
    comp._set(PICA, PIXEL, 16l, 1l);
    comp._set(PICA, TWIP, 240l, 1l);
    comp._set(CICERO_EUROPEAN_DIDOT, POINT_EUROPEAN_DIDOT, 12l, 1l);
    comp._set(PICA_POSTSCRIPT, POINT_POSTSCRIPT, 12l, 1l);

    // 4. astronomical
    comp._set(LIGHT_YEAR, PARSEC, 306601394l, 1000000000l);
    comp._set(RED_SHIFT, PARSEC, 4222000000l, 1l);
    comp._set(RED_SHIFT, LIGHT_YEAR, 13770000000l, 1l);

    // 5. chinese
    comp._set(LI_CHINESE, YIN_CHINESE, 15l, 1l);
    comp._set(LI_CHINESE, ZHANG_CHINESE, 150l, 1l);
    comp._set(LI_CHINESE, BU_CHINESE, 300l, 1l);
    comp._set(LI_CHINESE, CHI_CHINESE, 1500l, 1l);
    comp._set(LI_CHINESE, CUN_CHINESE, 15000l, 1l);
    comp._set(LI_CHINESE, FEN_CHINESE, 150000l, 1l);
    comp._set(LI_CHINESE, LI_CHINESE_SMALL, 1500000l, 1l);
    comp._set(LI_CHINESE, HAO_CHINESE, 15000000l, 1l);
    comp._set(LI_CHINESE, SI_CHINESE, 150000000l, 1l);
    comp._set(HU_CHINESE, HAO_CHINESE, 1500000000l, 1l);

    // pre-compile these separate systems
    comp._precompile();

    comp._set(POINT, MILLIMETER, 35278l, 100000l);
    comp._precompile();

    comp._set(FOOT, METER, 3048l, 10000l);
    comp._set(FOOT_US_SURVEY, METER, 3048006l, 10000000l);
    comp._set(INCH, CENTIMETER, 254l, 100l);
    comp._set(YARD, METER, 9144l, 10000l);
    comp._precompile();

    comp._set(POINT_EUROPEAN_DIDOT, MILLIMETER, 376l, 1000l);
    comp._set(PICA, MILLIMETER, 4233l, 1000l);
    comp._set(INCH, PICA_POSTSCRIPT, 6l, 1l);
    comp._precompile();

    comp._set(MILE_INTERNATIONAL, KILOMETER, 1609344l, 1000000l);
    comp._set(MILE_NAUTICAL, KILOMETER, 1852l, 1000l);
    comp._set(MILE_GEOGRAPHICAL, KILOMETER, 742l, 100l);
    comp._set(FATHOM, METER, 1828804l, 1000000l);
    comp._precompile();

    comp._set(LIGHT_SECOND, ANGSTROM, 2997924580000000000l, 1l);
    comp._set(LIGHT_SECOND, NANOMETER, 299792458000000000l, 1l);
    comp._set(LIGHT_SECOND, MICROMETER, 299792458000000l, 1l);
    comp._set(LIGHT_SECOND, MILLIMETER, 299792458000l, 1l);
    comp._set(LIGHT_SECOND, CENTIMETER, 29979245800l, 1l);
    comp._set(LIGHT_SECOND, DECIMETER, 2997924580l, 1l);
    comp._set(LIGHT_SECOND, METER, 299792458l, 1l);
    comp._set(LIGHT_SECOND, KILOMETER, 299792458l, 1000l);

    comp._set(LIGHT_MINUTE, LIGHT_SECOND, 60l, 1l);

    comp._set(LIGHT_MINUTE, MICROMETER, 17987547480000000l, 1l);
    comp._set(LIGHT_MINUTE, MILLIMETER, 17987547480000l, 1l);
    comp._set(LIGHT_MINUTE, CENTIMETER, 1798754748000l, 1l);
    comp._set(LIGHT_MINUTE, DECIMETER, 179875474800l, 1l);
    comp._set(LIGHT_MINUTE, METER, 17987547480l, 1l);
    comp._set(LIGHT_MINUTE, KILOMETER, 17987547480l, 1000l);

    comp._set(LIGHT_YEAR, CENTIMETER, 946073047258080000l, 1l);
    comp._set(LIGHT_YEAR, DECIMETER, 94607304725808000l, 1l);
    comp._set(LIGHT_YEAR, METER, 9460730472580800l, 1l);
    comp._set(LIGHT_YEAR, KILOMETER, 94607304725808l, 10l);

    comp._set(ASTRONOMICAL_UNIT, MICROMETER, 149597870700000000l, 1l);
    comp._set(ASTRONOMICAL_UNIT, MILLIMETER, 149597870700000l, 1l);
    comp._set(ASTRONOMICAL_UNIT, CENTIMETER, 14959787070000l, 1l);
    comp._set(ASTRONOMICAL_UNIT, DECIMETER, 1495978707000l, 1l);
    comp._set(ASTRONOMICAL_UNIT, METER, 149597870700l, 1l);
    comp._set(ASTRONOMICAL_UNIT, KILOMETER, 149597870700l, 1000l);
    comp._precompile();

    comp._set(METER, YIN_CHINESE, 3l, 100l);
    comp._set(METER, ZHANG_CHINESE, 3l, 10l);
    comp._set(METER, BU_CHINESE, 6l, 10l);
    comp._set(METER, CHI_CHINESE, 3l, 1l);
    comp._set(METER, CUN_CHINESE, 30l, 1l);
    comp._set(METER, FEN_CHINESE, 300l, 1l);
    comp._set(METER, LI_CHINESE_SMALL, 3000l, 1l);
    comp._set(METER, HAO_CHINESE, 30000l, 1l);
    comp._set(METER, SI_CHINESE, 300000l, 1l);
    comp._set(METER, HU_CHINESE, 3000000l, 1l);

    CONVERT = comp._compile();
  }

  /** the name */
  private final String m_name;
  /** the long name */
  private final String m_longName;
  /** the shortcut */
  private final String m_shortcut;

  /**
   * Create
   *
   * @param name
   *          the name
   * @param shortcut
   *          the shortcut
   * @param longName
   *          the long name
   */
  private ELength(final String name, final String shortcut,
      final String longName) {
    this.m_name = TextUtils.prepare(name);
    this.m_longName = TextUtils.prepare(longName);
    this.m_shortcut = TextUtils.prepare(shortcut);
  }

  /**
   * Create
   *
   * @param name
   *          the name
   * @param shortcut
   *          the shortcut
   */
  private ELength(final String name, final String shortcut) {
    this(name, shortcut, null);
  }

  /**
   * Create the name
   *
   * @param name
   *          the name
   */
  private ELength(final String name) {
    this(name, null);
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return ((this.m_shortcut != null) ? this.m_shortcut
        : ((this.m_name != null) ? (this.m_name) : (this.m_longName)));
  }

  /**
   * Convert the given value from the length dimension {@code from} to the
   * length dimension {@code to}
   *
   * @param value
   *          the value
   * @param from
   *          the length dimension {@code value} is specified in
   * @param to
   *          the length dimension the output will be defined in
   * @return the result
   */
  public static final double convert(final double value,
      final ELength from, final ELength to) {
    if (from == to) {
      return value;
    }
    return (value * ELength.CONVERT[from.ordinal()][to.ordinal()]);
  }

  /**
   * Get the conversion factor from the length dimension {@code from} to
   * the length dimension {@code to}
   *
   * @param from
   *          the length dimension {@code value} is specified in
   * @param to
   *          the length dimension the output will be defined in
   * @return the conversion factor
   */
  public static final double getConversionFactor(final ELength from,
      final ELength to) {
    if (from == to) {
      return 1d;
    }
    return ELength.CONVERT[from.ordinal()][to.ordinal()];
  }
}
