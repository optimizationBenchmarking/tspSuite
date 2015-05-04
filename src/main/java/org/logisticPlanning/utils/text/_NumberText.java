package org.logisticPlanning.utils.text;

/**
 * An internal class for transforming numbers to text, inspired by the
 * solutions provided in&nbsp;[<a href="#cite_JRJWMAA2010"
 * style="font-weight:bold">1</a>]. The solution implemented here is sort
 * of equivalent to <a
 * href="http://stackoverflow.com/users/320700/yanick-rochon">Yanick
 * Rochon</a>'s in&nbsp;[<a href="#cite_JRJWMAA2010"
 * style="font-weight:bold">1</a>], but it is shorter and needs less
 * classes and memory. The names for the large numbers are from&nbsp;[<a
 * href="#cite_W2010NOLND" style="font-weight:bold">2</a>, <a
 * href="#cite_W2013GWTLNE" style="font-weight:bold">3</a>, <a
 * href="#cite_G2012ZNOLN" style="font-weight:bold">4</a>]. This class
 * allows us to represent all possible values of {@code double} and
 * {@code long} as textual strings. <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_JRJWMAA2010" />Jason, Yanick Rochon, Jigar
 * Joshi, David Wallace, Santhosh Reddy Mandadi, Ankit Arjaria,
 * and&nbsp;Riyar Ajay: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Question on
 * Stackoverflow.com: How to Convert Number to Words in Java,&rdquo;</span>
 * (Website), October&nbsp;12, 2010, New York, NY, USA: Stack Exchange
 * Inc., stackoverflow. <div>link: [<a href=
 * "http://stackoverflow.com/questions/3911966/how-to-convert-number-to-words-in-java"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_W2010NOLND" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Names of Large
 * Numbers &#8210; Definition,&rdquo;</span> (Website), 2010&ndash;2013, El
 * Segundo, CA, USA: Internet Brands Inc., WordIQ.com. <div>link: [<a
 * href="http://www.wordiq.com/definition/Names_of_large_numbers"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_W2013GWTLNE" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Googology Wiki:
 * The Large Number Encyclopedia,&rdquo;</span> (Website), 2013, Toronto,
 * ON, Canada: Wikia.com, Contact Privacy Inc. Customer 0130027609.
 * <div>link: [<a href="http://googology.wikia.com/">1</a>]</div></div></li>
 * <li><div><span id="cite_G2012ZNOLN" /><a
 * href="http://www.grobauer.at/">Gerhard Grobauer</a>: <span
 * style="font-weight:bold">&ldquo;Zahlnamen &#8210; Names of Large
 * Numbers,&rdquo;</span> October&nbsp;12, 2012; published by M&#246;dling,
 * Austria: Gerhard Grobauer &#8210; Verhaltenstraining. <div>link: [<a
 * href="http://www.grobauer.at/gg_chcounter/getfile.php?id=4"
 * >1</a>]</div></div></li>
 * </ol>
 */
final class _NumberText {

  /** minus */
  private static final char[] MINUS = new char[] { 'm', 'i', 'n', 'u', 's' };

  /** and */
  private static final char[] AND = new char[] { ' ', 'a', 'n', 'd' };

  /** the small numbers */
  static final String[] SMALL_NUMBERS = { "zero", //$NON-NLS-1$
    "one",//$NON-NLS-1$
    "two",//$NON-NLS-1$
    "three",//$NON-NLS-1$
    "four",//$NON-NLS-1$
    "five",//$NON-NLS-1$
    "six",//$NON-NLS-1$
    "seven",//$NON-NLS-1$
    "eight",//$NON-NLS-1$
    "nine",//$NON-NLS-1$
    "ten",//$NON-NLS-1$
    "eleven",//$NON-NLS-1$
    "twelve"//$NON-NLS-1$
  };

  /** zero */
  private static final char[] C_0 = _NumberText.SMALL_NUMBERS[0]
      .toCharArray();

  /** ten */
  private static final char[] C_10 = _NumberText.SMALL_NUMBERS[10]
      .toCharArray();

  /** 100 */
  private static final char[] C_100 = new char[] { 'h', 'u', 'n', 'd',
    'r', 'e', 'd' };

  /** all known scales */
  private static final char[][] SCALES = {
    _NumberText.C_0,//
    { 't', 'h', 'o', 'u', 's', 'a', 'n', 'd' },//
    { 'm', 'i', 'l', 'l', 'i', 'o', 'n' },//
    { 'b', 'i', 'l', 'l', 'i', 'o', 'n' },//
    { 't', 'r', 'i', 'l', 'l', 'i', 'o', 'n' },//
    { 'q', 'u', 'a', 'd', 'r', 'i', 'l', 'l', 'i', 'o', 'n' },//
    { 'q', 'u', 'i', 'n', 't', 'i', 'l', 'l', 'i', 'o', 'n' },//
    { 's', 'e', 'x', 't', 'i', 'l', 'l', 'i', 'o', 'n' },//
    { 's', 'e', 'p', 't', 'i', 'l', 'l', 'i', 'o', 'n' },//
    { 'o', 'c', 't', 'i', 'l', 'l', 'i', 'o', 'n' },//
    { 'n', 'o', 'n', 'i', 'l', 'l', 'i', 'o', 'n' },//
    { 'd', 'e', 'c', 'i', 'l', 'l', 'i', 'o', 'n' },//
    { 'u', 'n', 'd', 'e', 'c', 'i', 'l', 'l', 'i', 'o', 'n' },//
    { 'd', 'u', 'o', 'd', 'e', 'c', 'i', 'l', 'l', 'i', 'o', 'n' },//
    { 't', 'r', 'e', 'd', 'e', 'c', 'i', 'l', 'l', 'i', 'o', 'n' },//
    { 'q', 'u', 'a', 't', 't', 'u', 'o', 'r', 'd', 'e', 'c', 'i', 'l',
      'l', 'i', 'o', 'n' },//
      { 'q', 'u', 'i', 'n', 'd', 'e', 'c', 'i', 'l', 'l', 'i', 'o', 'n' },//
      { 's', 'e', 'x', 'd', 'e', 'c', 'i', 'l', 'l', 'i', 'o', 'n' },//
      { 's', 'e', 'p', 't', 'e', 'n', 'd', 'e', 'c', 'i', 'l', 'l', 'i',
        'o', 'n' },//
        { 'o', 'c', 't', 'o', 'd', 'e', 'c', 'i', 'l', 'l', 'i', 'o', 'n' },//
        { 'n', 'o', 'v', 'e', 'm', 'd', 'e', 'c', 'i', 'l', 'l', 'i', 'o',
        'n' },//
        { 'v', 'i', 'g', 'i', 'n', 't', 'i', 'l', 'l', 'i', 'o', 'n' }, //
        { 'u', 'n', 'v', 'i', 'g', 'i', 'n', 't', 'i', 'l', 'l', 'i', 'o',
          'n', }, //
          { 'd', 'u', 'o', 'v', 'i', 'g', 'i', 'n', 't', 'i', 'l', 'l', 'i',
            'o', 'n', }, //
            { 't', 'r', 'e', 'v', 'i', 'g', 'i', 'n', 't', 'i', 'l', 'l', 'i',
              'o', 'n', }, //
              { 'q', 'u', 'a', 't', 't', 'u', 'o', 'r', 'v', 'i', 'g', 'i', 'n',
                't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                { 'q', 'u', 'i', 'n', 'v', 'i', 'g', 'i', 'n', 't', 'i', 'l', 'l',
                  'i', 'o', 'n', }, //
                  { 's', 'e', 'x', 'v', 'i', 'g', 'i', 'n', 't', 'i', 'l', 'l', 'i',
                    'o', 'n', }, //
                    { 's', 'e', 'p', 't', 'e', 'n', 'v', 'i', 'g', 'i', 'n', 't', 'i',
                      'l', 'l', 'i', 'o', 'n', }, //
                      { 'o', 'c', 't', 'o', 'v', 'i', 'g', 'i', 'n', 't', 'i', 'l', 'l',
                        'i', 'o', 'n', }, //
                        { 'n', 'o', 'v', 'e', 'm', 'v', 'i', 'g', 'i', 'n', 't', 'i', 'l',
                          'l', 'i', 'o', 'n', }, //
                          { 't', 'r', 'i', 'g', 'i', 'n', 't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                          { 'u', 'n', 't', 'r', 'i', 'g', 'i', 'n', 't', 'i', 'l', 'l', 'i',
                            'o', 'n', }, //
                            { 'd', 'u', 'o', 't', 'r', 'i', 'g', 'i', 'n', 't', 'i', 'l', 'l',
                              'i', 'o', 'n', }, //
                              { 't', 'r', 'e', 't', 'r', 'i', 'g', 'i', 'n', 't', 'i', 'l', 'l',
                                'i', 'o', 'n', }, //
                                { 'q', 'u', 'a', 't', 't', 'u', 'o', 'r', 't', 'r', 'i', 'g', 'i',
                                  'n', 't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                  { 'q', 'u', 'i', 'n', 't', 'r', 'i', 'g', 'i', 'n', 't', 'i', 'l',
                                    'l', 'i', 'o', 'n', }, //
                                    { 's', 'e', 'x', 't', 'r', 'i', 'g', 'i', 'n', 't', 'i', 'l', 'l',
                                      'i', 'o', 'n', }, //
                                      { 's', 'e', 'p', 't', 'e', 'n', 't', 'r', 'i', 'g', 'i', 'n', 't',
                                        'i', 'l', 'l', 'i', 'o', 'n', }, //
                                        { 'o', 'c', 't', 'o', 't', 'r', 'i', 'g', 'i', 'n', 't', 'i', 'l',
                                          'l', 'i', 'o', 'n', }, //
                                          { 'n', 'o', 'v', 'e', 'm', 't', 'r', 'i', 'g', 'i', 'n', 't', 'i',
                                            'l', 'l', 'i', 'o', 'n', }, //
                                            { 'q', 'u', 'a', 'd', 'r', 'a', 'g', 'i', 'n', 't', 'i', 'l', 'l',
                                              'i', 'o', 'n', }, //
                                              { 'u', 'n', 'q', 'u', 'a', 'd', 'r', 'a', 'g', 'i', 'n', 't', 'i',
                                                'l', 'l', 'i', 'o', 'n', }, //
                                                { 'd', 'u', 'o', 'q', 'u', 'a', 'd', 'r', 'a', 'g', 'i', 'n', 't',
                                                  'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                  { 't', 'r', 'e', 'q', 'u', 'a', 'd', 'r', 'a', 'g', 'i', 'n', 't',
                                                    'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                    { 'q', 'u', 'a', 't', 't', 'u', 'o', 'r', 'q', 'u', 'a', 'd', 'r',
                                                      'a', 'g', 'i', 'n', 't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                      { 'q', 'u', 'i', 'n', 'q', 'u', 'a', 'd', 'r', 'a', 'g', 'i', 'n',
                                                        't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                        { 's', 'e', 'x', 'q', 'u', 'a', 'd', 'r', 'a', 'g', 'i', 'n', 't',
                                                          'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                          { 's', 'e', 'p', 't', 'e', 'n', 'q', 'u', 'a', 'd', 'r', 'a', 'g',
                                                            'i', 'n', 't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                            { 'o', 'c', 't', 'o', 'q', 'u', 'a', 'd', 'r', 'a', 'g', 'i', 'n',
                                                              't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                              { 'n', 'o', 'v', 'e', 'm', 'q', 'u', 'a', 'd', 'r', 'a', 'g', 'i',
                                                                'n', 't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                { 'q', 'u', 'i', 'n', 'q', 'u', 'a', 'g', 'i', 'n', 't', 'i', 'l',
                                                                  'l', 'i', 'o', 'n', }, //
                                                                  { 'u', 'n', 'q', 'u', 'i', 'n', 'q', 'u', 'a', 'g', 'i', 'n', 't',
                                                                    'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                    { 'd', 'u', 'o', 'q', 'u', 'i', 'n', 'q', 'u', 'a', 'g', 'i', 'n',
                                                                      't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                      { 't', 'r', 'e', 'q', 'u', 'i', 'n', 'q', 'u', 'a', 'g', 'i', 'n',
                                                                        't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                        { 'q', 'u', 'a', 't', 't', 'u', 'o', 'r', 'q', 'u', 'i', 'n', 'q',
                                                                          'u', 'a', 'g', 'i', 'n', 't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                          { 'q', 'u', 'i', 'n', 'q', 'u', 'i', 'n', 'q', 'u', 'a', 'g', 'i',
                                                                            'n', 't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                            { 's', 'e', 'x', 'q', 'u', 'i', 'n', 'q', 'u', 'a', 'g', 'i', 'n',
                                                                              't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                              { 's', 'e', 'p', 't', 'e', 'n', 'q', 'u', 'i', 'n', 'q', 'u', 'a',
                                                                                'g', 'i', 'n', 't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                                { 'o', 'c', 't', 'o', 'q', 'u', 'i', 'n', 'q', 'u', 'a', 'g', 'i',
                                                                                  'n', 't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                                  { 'n', 'o', 'v', 'e', 'm', 'q', 'u', 'i', 'n', 'q', 'u', 'a', 'g',
                                                                                    'i', 'n', 't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                                    { 's', 'e', 'x', 'a', 'g', 'i', 'n', 't', 'i', 'l', 'l', 'i', 'o',
                                                                                      'n', }, //
                                                                                      { 'u', 'n', 's', 'e', 'x', 'a', 'g', 'i', 'n', 't', 'i', 'l', 'l',
                                                                                        'i', 'o', 'n', }, //
                                                                                        { 'd', 'u', 'o', 's', 'e', 'x', 'a', 'g', 'i', 'n', 't', 'i', 'l',
                                                                                          'l', 'i', 'o', 'n', }, //
                                                                                          { 't', 'r', 'e', 's', 'e', 'x', 'a', 'g', 'i', 'n', 't', 'i', 'l',
                                                                                            'l', 'i', 'o', 'n', }, //
                                                                                            { 'q', 'u', 'a', 't', 't', 'u', 'o', 'r', 's', 'e', 'x', 'a', 'g',
                                                                                              'i', 'n', 't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                                              { 'q', 'u', 'i', 'n', 's', 'e', 'x', 'a', 'g', 'i', 'n', 't', 'i',
                                                                                                'l', 'l', 'i', 'o', 'n', }, //
                                                                                                { 's', 'e', 'x', 's', 'e', 'x', 'a', 'g', 'i', 'n', 't', 'i', 'l',
                                                                                                  'l', 'i', 'o', 'n', }, //
                                                                                                  { 's', 'e', 'p', 't', 'e', 'n', 's', 'e', 'x', 'a', 'g', 'i', 'n',
                                                                                                    't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                                                    { 'o', 'c', 't', 'a', 's', 'e', 'x', 'a', 'g', 'i', 'n', 't', 'i',
                                                                                                      'l', 'l', 'i', 'o', 'n', }, //
                                                                                                      { 'n', 'o', 'v', 'e', 'm', 's', 'e', 'x', 'a', 'g', 'i', 'n', 't',
                                                                                                        'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                                                        { 's', 'e', 'p', 't', 'u', 'a', 'g', 'i', 'n', 't', 'i', 'l', 'l',
                                                                                                          'i', 'o', 'n', }, //
                                                                                                          { 'u', 'n', 's', 'e', 'p', 't', 'u', 'a', 'g', 'i', 'n', 't', 'i',
                                                                                                            'l', 'l', 'i', 'o', 'n', }, //
                                                                                                            { 'd', 'u', 'o', 's', 'e', 'p', 't', 'u', 'a', 'g', 'i', 'n', 't',
                                                                                                              'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                                                              { 't', 'r', 'e', 's', 'e', 'p', 't', 'u', 'a', 'g', 'i', 'n', 't',
                                                                                                                'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                                                                { 'q', 'u', 'a', 't', 't', 'u', 'o', 'r', 's', 'e', 'p', 't', 'u',
                                                                                                                  'a', 'g', 'i', 'n', 't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                                                                  { 'q', 'u', 'i', 'n', 's', 'e', 'p', 't', 'u', 'a', 'g', 'i', 'n',
                                                                                                                    't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                                                                    { 's', 'e', 'x', 's', 'e', 'p', 't', 'u', 'a', 'g', 'i', 'n', 't',
                                                                                                                      'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                                                                      { 's', 'e', 'p', 't', 'e', 'n', 's', 'e', 'p', 't', 'u', 'a', 'g',
                                                                                                                        'i', 'n', 't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                                                                        { 'o', 'c', 't', 'a', 's', 'e', 'p', 't', 'u', 'a', 'g', 'i', 'n',
                                                                                                                          't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                                                                          { 'n', 'o', 'v', 'e', 'm', 's', 'e', 'p', 't', 'u', 'a', 'g', 'i',
                                                                                                                            'n', 't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                                                                            { 'o', 'c', 't', 'o', 'g', 'i', 'n', 't', 'i', 'l', 'l', 'i', 'o',
                                                                                                                              'n', }, //
                                                                                                                              { 'u', 'n', 'o', 'c', 't', 'o', 'g', 'i', 'n', 't', 'i', 'l', 'l',
                                                                                                                                'i', 'o', 'n', }, //
                                                                                                                                { 'd', 'u', 'o', 'o', 'c', 't', 'o', 'g', 'i', 'n', 't', 'i', 'l',
                                                                                                                                  'l', 'i', 'o', 'n', }, //
                                                                                                                                  { 't', 'r', 'e', 'o', 'c', 't', 'o', 'g', 'i', 'n', 't', 'i', 'l',
                                                                                                                                    'l', 'i', 'o', 'n', }, //
                                                                                                                                    { 'q', 'u', 'a', 't', 't', 'u', 'o', 'r', 'o', 'c', 't', 'o', 'g',
                                                                                                                                      'i', 'n', 't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                                                                                      { 'q', 'u', 'i', 'n', 'o', 'c', 't', 'o', 'g', 'i', 'n', 't', 'i',
                                                                                                                                        'l', 'l', 'i', 'o', 'n', }, //
                                                                                                                                        { 's', 'e', 'x', 'o', 'c', 't', 'o', 'g', 'i', 'n', 't', 'i', 'l',
                                                                                                                                          'l', 'i', 'o', 'n', }, //
                                                                                                                                          { 's', 'e', 'p', 't', 'e', 'n', 'o', 'c', 't', 'o', 'g', 'i', 'n',
                                                                                                                                            't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                                                                                            { 'o', 'c', 't', 'a', 'o', 'c', 't', 'o', 'g', 'i', 'n', 't', 'i',
                                                                                                                                              'l', 'l', 'i', 'o', 'n', }, //
                                                                                                                                              { 'n', 'o', 'v', 'e', 'm', 'o', 'c', 't', 'o', 'g', 'i', 'n', 't',
                                                                                                                                                'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                                                                                                { 'n', 'o', 'n', 'a', 'g', 'i', 'n', 't', 'i', 'l', 'l', 'i', 'o',
                                                                                                                                                  'n', }, //
                                                                                                                                                  { 'u', 'n', 'n', 'o', 'n', 'a', 'g', 'i', 'n', 't', 'i', 'l', 'l',
                                                                                                                                                    'i', 'o', 'n', }, //
                                                                                                                                                    { 'd', 'u', 'o', 'n', 'o', 'n', 'a', 'g', 'i', 'n', 't', 'i', 'l',
                                                                                                                                                      'l', 'i', 'o', 'n', }, //
                                                                                                                                                      { 't', 'r', 'e', 'n', 'o', 'n', 'a', 'g', 'i', 'n', 't', 'i', 'l',
                                                                                                                                                        'l', 'i', 'o', 'n', }, //
                                                                                                                                                        { 'q', 'u', 'a', 't', 't', 'u', 'o', 'r', 'n', 'o', 'n', 'a', 'g',
                                                                                                                                                          'i', 'n', 't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                                                                                                          { 'q', 'u', 'i', 'n', 'n', 'o', 'n', 'a', 'g', 'i', 'n', 't', 'i',
                                                                                                                                                            'l', 'l', 'i', 'o', 'n', }, //
                                                                                                                                                            { 's', 'e', 'x', 'n', 'o', 'n', 'a', 'g', 'i', 'n', 't', 'i', 'l',
                                                                                                                                                              'l', 'i', 'o', 'n', }, //
                                                                                                                                                              { 's', 'e', 'p', 't', 'e', 'n', 'n', 'o', 'n', 'a', 'g', 'i', 'n',
                                                                                                                                                                't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                                                                                                                { 'o', 'c', 't', 'a', 'n', 'o', 'n', 'a', 'g', 'i', 'n', 't', 'i',
                                                                                                                                                                  'l', 'l', 'i', 'o', 'n', }, //
                                                                                                                                                                  { 'n', 'o', 'v', 'e', 'm', 'n', 'o', 'n', 'a', 'g', 'i', 'n', 't',
                                                                                                                                                                    'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                                                                                                                    { 'c', 'e', 'n', 't', 'i', 'l', 'l', 'i', 'o', 'n', }, //
                                                                                                                                                                    { 'c', 'e', 'n', 't', 'u', 'n', 't', 'i', 'l', 'l', 'i', 'o', 'n' },//
                                                                                                                                                                    { 'c', 'e', 'n', 't', 'd', 'u', 'o', 't', 'i', 'l', 'l', 'i', 'o',
                                                                                                                                                                    'n' },//
                                                                                                                                                                    { 'c', 'e', 'n', 't', 't', 'r', 'e', 't', 'i', 'l', 'l', 'i', 'o',
                                                                                                                                                                    'n' },//
                                                                                                                                                                    { 'c', 'e', 'n', 't', 'q', 'u', 'a', 't', 't', 'u', 'o', 'r', 't',
                                                                                                                                                                      'i', 'l', 'l', 'i', 'o', 'n' },//
                                                                                                                                                                      { 'c', 'e', 'n', 't', 'q', 'u', 'i', 'n', 't', 'i', 'l', 'l', 'i',
                                                                                                                                                                        'o', 'n' },//
                                                                                                                                                                        { 'c', 'e', 'n', 't', 's', 'e', 'x', 't', 'i', 'l', 'l', 'i', 'o',
                                                                                                                                                                        'n' },//
                                                                                                                                                                        { 'c', 'e', 'n', 't', 's', 'e', 'p', 't', 'e', 'n', 't', 'i', 'l',
                                                                                                                                                                          'l', 'i', 'o', 'n' },//
                                                                                                                                                                          { 'c', 'e', 'n', 't', 'o', 'k', 't', 'o', 't', 'i', 'l', 'l', 'i',
                                                                                                                                                                            'o', 'n', },//
                                                                                                                                                                            { 'c', 'e', 'n', 't', 'n', 'o', 'v', 'e', 'm', 't', 'i', 'l', 'l',
                                                                                                                                                                              'i', 'o', 'n' },//
                                                                                                                                                                              { 'c', 'e', 'n', 't', 'd', 'e', 'z', 'i', 'l', 'l', 'i', 'o', 'n' },//
                                                                                                                                                                              { 'c', 'e', 'n', 't', 'u', 'n', 'd', 'e', 'z', 'i', 'l', 'l', 'i',
                                                                                                                                                                                'o', 'n' },//

  };

  /** the values from 1 to 19 */
  private static final char[][] TOKENS_1 = {//
    _NumberText.C_0, //
    _NumberText.SMALL_NUMBERS[1].toCharArray(),//
    _NumberText.SMALL_NUMBERS[2].toCharArray(),//
    _NumberText.SMALL_NUMBERS[3].toCharArray(),//
    _NumberText.SMALL_NUMBERS[4].toCharArray(),//
    _NumberText.SMALL_NUMBERS[5].toCharArray(),//
    _NumberText.SMALL_NUMBERS[6].toCharArray(),//
    _NumberText.SMALL_NUMBERS[7].toCharArray(),//
    _NumberText.SMALL_NUMBERS[8].toCharArray(),//
    _NumberText.SMALL_NUMBERS[9].toCharArray(),//
    _NumberText.C_10,//
    _NumberText.SMALL_NUMBERS[11].toCharArray(),//
    _NumberText.SMALL_NUMBERS[12].toCharArray(),//
    { 't', 'h', 'i', 'r', 't', 'e', 'e', 'n' },//
    { 'f', 'o', 'u', 'r', 't', 'e', 'e', 'n' },//
    { 'f', 'i', 'f', 't', 'e', 'e', 'n' },//
    { 's', 'i', 'x', 't', 'e', 'e', 'n' },//
    { 's', 'e', 'v', 'e', 'n', 't', 'e', 'e', 'n' },//
    { 'e', 'i', 'g', 'h', 't', 'e', 'e', 'n' },//
    { 'n', 'i', 'n', 'e', 't', 'e', 'e', 'n' } };

  /** the values from 20 to 90 */
  private static final char[][] TOKENS_10 = {//
    _NumberText.C_0,//
    _NumberText.C_10, { 't', 'w', 'e', 'n', 't', 'y' },//
    { 't', 'h', 'i', 'r', 't', 'y' },//
    { 'f', 'o', 'u', 'r', 't', 'y' },//
    { 'f', 'i', 'f', 't', 'y' },//
    { 's', 'i', 'x', 't', 'y' },//
    { 's', 'e', 'v', 'e', 'n', 't', 'y' },//
    { 'e', 'i', 'g', 'h', 't', 'y' },//
    { 'n', 'i', 'n', 'e', 't', 'y' } };

  /** the array to use for the minimum long value */
  private static final int[] MIN_LONG_THOUSANDS = { 808, 775, 854, 36,
    372, 223, 9 };

  /**
   * append the thousand chars
   *
   * @param thousand
   *          the thousand to append
   * @param dest
   *          the destination
   * @param startLength
   *          the starting length of the string builder
   */
  private static final void __appendThousand(final int thousand,
      final StringBuilder dest, final int startLength) {
    int i;

    if (thousand > 0) {
      i = (thousand / 100);
      if (i > 0) {
        if (dest.length() > startLength) {
          dest.append(' ');
        }
        dest.append(_NumberText.TOKENS_1[i]);
        dest.append(' ');
        dest.append(_NumberText.C_100);
        i = (thousand % 100);
      } else {
        i = thousand;
      }

      if (i > 0) {
        if (i < _NumberText.TOKENS_1.length) {
          if (dest.length() > startLength) {
            dest.append(' ');
          }
          dest.append(_NumberText.TOKENS_1[i]);
        } else {

          if (dest.length() > startLength) {
            dest.append(' ');
          }
          dest.append(_NumberText.TOKENS_10[i / 10]);
          i %= 10;
          if (i > 0) {
            dest.append('-');
            dest.append(_NumberText.TOKENS_1[i]);
          }
        }
      }
    }
  }

  /**
   * Append a long to a string builder
   *
   * @param number
   *          the number
   * @param dest
   *          the destination string builder
   * @param scaleOffset
   *          the scale offset
   * @param startLength
   *          the start length of the string builder
   */
  static final void _appendLongAsText(final long number,
      final StringBuilder dest, final int startLength,
      final int scaleOffset) {
    final int[] thousands;
    int start, i, scale, carry, j;
    long n;

    findNumbers: {
      if (number < 0l) {
        dest.append(_NumberText.MINUS);
        if (number <= Long.MIN_VALUE) {
          thousands = _NumberText.MIN_LONG_THOUSANDS;
          start = thousands.length;
          break findNumbers;
        }
        n = (-number);
      } else {
        if (number == 0l) {
          dest.append(_NumberText.C_0);
          return;
        }
        n = number;
      }

      start = 0;
      thousands = new int[8];// maximum number thousands
      while (n > 0l) {
        thousands[start++] = ((int) (n % 1000l));
        n /= 1000l;
      }
    }

    // in case we have a scale offset, we need to adjust the thousands
    // array
    // we cannot do this before, because this could result in an overflow
    if (scaleOffset > 0) {
      scale = (scaleOffset % 3);
      if (scale > 0) {
        scale = ((scale == 1) ? 10 : 100);
        carry = 0;
        for (i = 0; i < start; i++) {
          j = ((thousands[i] * scale) + carry);
          thousands[i] = (j % 1000);
          carry = (j / 1000);
        }
        if (carry > 0) {
          thousands[start++] = carry;
        }
      }
    }

    // now write the different thousands
    scale = ((scaleOffset / 3) + start);
    for (; (--start) >= 0;) {
      i = thousands[start];
      --scale;
      if (i > 0) {
        _NumberText.__appendThousand(i, dest, startLength);
        if (scale > 0) {
          dest.append(' ');
          dest.append(_NumberText.SCALES[scale]);
        }
      }
    }
  }

  /**
   * Append a long to a string builder
   *
   * @param number
   *          the number
   * @param dest
   *          the destination string builder
   * @param startLength
   *          the start length of the string builder
   */
  @SuppressWarnings("incomplete-switch")
  static final void _appendDoubleAsText(final double number,
      final StringBuilder dest, final int startLength) {
    long num;
    double value, d;
    int power;
    String t;

    if (number < 0d) {
      if (number <= Double.NEGATIVE_INFINITY) {
        dest.append(_TextConst.N_INFINITY);
        return;
      }
      if (number == Long.MIN_VALUE) {
        _NumberText
        ._appendLongAsText(Long.MIN_VALUE, dest, startLength, 0);
        return;
      }
      dest.append(_NumberText.MINUS);
      value = (-number);
    } else {
      if (number == 0d) {
        dest.append(_NumberText.C_0);
        return;
      }
      if (number >= Double.POSITIVE_INFINITY) {
        dest.append(_TextConst.P_INFINITY);
        return;
      }
      if (number != number) {
        dest.append(_TextConst.NAN);
        return;
      }
      value = number;
    }

    if ((value >= Long.MIN_VALUE) && (value <= Long.MAX_VALUE)) {
      // we can directly access the value as a long, but need to take care
      // of
      // the things after the comma

      num = ((long) value);
      if (num > 0l) {// stuff before the comma
        _NumberText._appendLongAsText(num, dest, startLength, 0);
      }

      if (num == value) {
        return;
      }

      // We try to obtain the power by first transforming the fraction
      // into a
      // string. If this works, we can circumvent strange precision errors
      // such
      // as that 3.141592d - 3d = 0.1415920...016 or something, instead of
      // 0.141592. If that does not work, we try to get the fraction part
      // by
      // multiplying with powers of 10.
      t = String.valueOf(value);
      if (t.indexOf('E') >= 0) {
        // ok, no easy fallback
        t = null;

        value -= num;

        // ok, the scale of the double is larger that what fits into a
        // long, so
        // we
        // need to adjust it
        power = ((int) (-Math.log10(value)));
        for (;;) {
          d = (value * Math.pow(10d, power));
          num = ((long) d);
          if (num == d) {
            break;
          }
          power++;
        }

      } else {

        // good, we can use the string!
        power = t.indexOf('.');
        if (power < 0) {
          return;
        }
        t = t.substring(power + 1);
        num = Long.parseLong(t);
        power = t.length();
        t = null;
      }

      if ((power / 3) >= _NumberText.SCALES.length) {
        // This should never happen: The power is too large for our
        // list, fall
        // back to normal number representation
        dest.setLength(startLength);
        dest.append(number);
        return;
      }

      if (dest.length() > startLength) {
        dest.append(_NumberText.AND);
      }

      _NumberText._appendLongAsText(num, dest, startLength, 0);
      dest.append(' ');

      switch (power) {
        case 1: {
          dest.append(_NumberText.C_10);
          break;
        }
        case 2: {
          dest.append(_NumberText.C_100);
          break;
        }

        default: {
          if (power <= 0) {
            throw new IllegalStateException();
          }

          switch (power % 3) {
            case 1: {
              dest.append(_NumberText.C_10);
              dest.append('-');
              break;
            }
            case 2: {
              dest.append(_NumberText.C_100);
              dest.append('-');
              break;
            }
          }
          dest.append(_NumberText.SCALES[power / 3]);
        }
      }

      dest.append('t');
      dest.append('h');
      if (num > 1l) {
        dest.append('s');
      }

      return;
    }

    // ok, the scale of the double is larger that what fits into a long, so
    // we
    // need to adjust it
    power = ((int) (Math.log10(value)));
    for (;;) {
      d = (value * Math.pow(10d, -power));
      num = ((long) d);
      if (num == d) {
        break;
      }
      power--;
    }

    try {
      _NumberText._appendLongAsText(num, dest, startLength, power);
    } catch (final ArrayIndexOutOfBoundsException aioobe) {
      // This should never happen: the power is too large for our list,
      // fall
      // back to normal number representation
      dest.setLength(startLength);
      dest.append(number);
    }
  }

}
