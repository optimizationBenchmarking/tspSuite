package org.logisticPlanning.utils.document.impl.xhtml;

import java.awt.geom.Dimension2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import org.logisticPlanning.utils.document.spec.AbstractTableCell;
import org.logisticPlanning.utils.document.spec.Authors;
import org.logisticPlanning.utils.document.spec.BibReference;
import org.logisticPlanning.utils.document.spec.Body;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.document.spec.DocumentDimensions;
import org.logisticPlanning.utils.document.spec.ECitationMode;
import org.logisticPlanning.utils.document.spec.ESequenceType;
import org.logisticPlanning.utils.document.spec.Element;
import org.logisticPlanning.utils.document.spec.Emphasize;
import org.logisticPlanning.utils.document.spec.Enumeration;
import org.logisticPlanning.utils.document.spec.EnumerationItem;
import org.logisticPlanning.utils.document.spec.Equation;
import org.logisticPlanning.utils.document.spec.Figure;
import org.logisticPlanning.utils.document.spec.FigureBody;
import org.logisticPlanning.utils.document.spec.FigureCaption;
import org.logisticPlanning.utils.document.spec.FigureSeries;
import org.logisticPlanning.utils.document.spec.FigureSeriesCaption;
import org.logisticPlanning.utils.document.spec.FigureSeriesPage;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.Itemization;
import org.logisticPlanning.utils.document.spec.ItemizationItem;
import org.logisticPlanning.utils.document.spec.Label;
import org.logisticPlanning.utils.document.spec.Macro;
import org.logisticPlanning.utils.document.spec.MacroDescriptor;
import org.logisticPlanning.utils.document.spec.MacroInvocation;
import org.logisticPlanning.utils.document.spec.MacroParameter;
import org.logisticPlanning.utils.document.spec.MathName;
import org.logisticPlanning.utils.document.spec.MathOp;
import org.logisticPlanning.utils.document.spec.MathOpParam;
import org.logisticPlanning.utils.document.spec.MathSubscript;
import org.logisticPlanning.utils.document.spec.MathSuperscript;
import org.logisticPlanning.utils.document.spec.NormalText;
import org.logisticPlanning.utils.document.spec.Section;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;
import org.logisticPlanning.utils.document.spec.Sequence;
import org.logisticPlanning.utils.document.spec.SingleLabel;
import org.logisticPlanning.utils.document.spec.SubFigure;
import org.logisticPlanning.utils.document.spec.SubFigureCaption;
import org.logisticPlanning.utils.document.spec.Subscript;
import org.logisticPlanning.utils.document.spec.Summary;
import org.logisticPlanning.utils.document.spec.Superscript;
import org.logisticPlanning.utils.document.spec.Table;
import org.logisticPlanning.utils.document.spec.TableBodyCell;
import org.logisticPlanning.utils.document.spec.TableBodyRow;
import org.logisticPlanning.utils.document.spec.TableCaption;
import org.logisticPlanning.utils.document.spec.TableCellDef;
import org.logisticPlanning.utils.document.spec.TableFooter;
import org.logisticPlanning.utils.document.spec.TableFooterCell;
import org.logisticPlanning.utils.document.spec.TableFooterRow;
import org.logisticPlanning.utils.document.spec.TableHeader;
import org.logisticPlanning.utils.document.spec.TableHeaderCell;
import org.logisticPlanning.utils.document.spec.TableHeaderRow;
import org.logisticPlanning.utils.document.spec.TablePage;
import org.logisticPlanning.utils.document.spec.Title;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;
import org.logisticPlanning.utils.document.spec.bib.BibBook;
import org.logisticPlanning.utils.document.spec.bib.BibRecord;
import org.logisticPlanning.utils.io.EEncoding;
import org.logisticPlanning.utils.io.charIO.CharArrayCharInput;
import org.logisticPlanning.utils.io.charIO.CharCharInput;
import org.logisticPlanning.utils.io.charIO.CharInput;
import org.logisticPlanning.utils.io.charIO.StringCharInput;
import org.logisticPlanning.utils.io.charIO.WriterCharOutput;
import org.logisticPlanning.utils.text.TextUtils;
import org.logisticPlanning.utils.text.transformations.XMLCharTransformer;
import org.logisticPlanning.utils.utils.EmptyUtils;

/**
 * The <a href="http://en.wikipedia.org/wiki/Xhtml">XHTML</a>&nbsp;[<a
 * href="#cite_W3C2010XHTML" style="font-weight:bold">1</a>] document
 * implementation.<h2>References</h2>
 * <ol>
 * <li><div><span id="cite_W3C2010XHTML" /><a
 * href="http://www.altheim.com/murray/">Murray Altheim</a> and&nbsp;Shane
 * McCarron: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;XHTML&#8482; 1.1
 * &#8212; Module-based XHTML &#8212; Second Edition,&rdquo;</span>
 * November&nbsp;23, 2010, W3C Recommendation, MIT/CSAIL (USA), ERCIM
 * (France), Keio University (Japan): World Wide Web Consortium (W3C).
 * <div>link: [<a
 * href="http://www.w3.org/TR/2010/REC-xhtml11-20101123">1</a>]</div></div>
 * </li>
 * </ol>
 */
public class XHTMLDocument extends Document {

  /** the line feed buffer */
  private final static char[] LINE_FEED;
  /** the newline length */
  private static final int NEWLINE_LENGTH;

  static {
    final String nl;
    final int nll;

    nl = TextUtils.LINE_SEPARATOR;
    NEWLINE_LENGTH = nll = nl.length();

    LINE_FEED = new char[nll + 70];
    nl.getChars(0, nll, XHTMLDocument.LINE_FEED, 0);
    Arrays.fill(XHTMLDocument.LINE_FEED, nll,
        XHTMLDocument.LINE_FEED.length, ' ');

    MacroDescriptor.validateMaxParams(XHTMLDocument.class, 9);
  }

  /** dot */
  private static final String DOT = "."; //$NON-NLS-1$

  /** the xml header start: charset must follow */
  private static final char[] XML_HEADER_BEGIN = { '<', '?', 'x', 'm',
      'l', ' ', 'v', 'e', 'r', 's', 'i', 'o', 'n', '=', '"', '1', '.',
      '0', '"', ' ', 'e', 'n', 'c', 'o', 'd', 'i', 'n', 'g', '=', '"' };
  /** the xml header end: newline must follow */
  private static final char[] XML_HEADER_END = { '"', '?', '>' };

  /** the doc type */
  private static final char[] DOC_TYPE = { '<', '!', 'D', 'O', 'C', 'T',
      'Y', 'P', 'E', ' ', 'h', 't', 'm', 'l', ' ', 'P', 'U', 'B', 'L',
      'I', 'C', ' ', '"', '-', '/', '/', 'W', '3', 'C', '/', '/', 'D',
      'T', 'D', ' ', 'X', 'H', 'T', 'M', 'L', ' ', '1', '.', '0', ' ',
      'S', 't', 'r', 'i', 'c', 't', '/', '/', 'E', 'N', '"', ' ', '"',
      'h', 't', 't', 'p', ':', '/', '/', 'w', 'w', 'w', '.', 'w', '3',
      '.', 'o', 'r', 'g', '/', 'T', 'R', '/', 'x', 'h', 't', 'm', 'l',
      '1', '/', 'D', 'T', 'D', '/', 'x', 'h', 't', 'm', 'l', '1', '-',
      's', 't', 'r', 'i', 'c', 't', '.', 'd', 't', 'd', '"', '>' };

  /** the doc type, charset must follow */
  private static final char[] HTML_BEGIN = { '<', 'h', 't', 'm', 'l', ' ',
      'x', 'm', 'l', 'n', 's', '=', '"', 'h', 't', 't', 'p', ':', '/',
      '/', 'w', 'w', 'w', '.', 'w', '3', '.', 'o', 'r', 'g', '/', '1',
      '9', '9', '9', '/', 'x', 'h', 't', 'm', 'l', '"', ' ', 'l', 'a',
      'n', 'g', '=', '"', 'e', 'n', '"', ' ', 'x', 'm', 'l', ':', 'l',
      'a', 'n', 'g', '=', '"', 'e', 'n', '"', '>' };
  /** the head begin */
  private static final char[] HEAD_BEGIN = { '<', 'h', 'e', 'a', 'd', '>' };

  /** the head begin */
  private static final char[] META_CHARSET_BEGIN = { '<', 'm', 'e', 't',
      'a', ' ', 'h', 't', 't', 'p', '-', 'e', 'q', 'u', 'i', 'v', '=',
      '"', 'c', 'o', 'n', 't', 'e', 'n', 't', '-', 't', 'y', 'p', 'e',
      '"', ' ', 'c', 'o', 'n', 't', 'e', 'n', 't', '=', '"', 'a', 'p',
      'p', 'l', 'i', 'c', 'a', 't', 'i', 'o', 'n', '/', 'x', 'h', 't',
      'm', 'l', '+', 'x', 'm', 'l', ';', ' ', 'c', 'h', 'a', 'r', 's',
      'e', 't', '=' };

  /** the attributed tag end */
  static final char[] ATTRIB_TAG_BEGIN_END = { '"', '>' };

  /** the meta style type */
  private static final char[] META_STYLE_TYPE = { '<', 'm', 'e', 't', 'a',
      ' ', 'h', 't', 't', 'p', '-', 'e', 'q', 'u', 'i', 'v', '=', '"',
      'c', 'o', 'n', 't', 'e', 'n', 't', '-', 's', 't', 'y', 'l', 'e',
      '-', 't', 'y', 'p', 'e', '"', ' ', 'c', 'o', 'n', 't', 'e', 'n',
      't', '=', '"', 't', 'e', 'x', 't', '/', 'c', 's', 's', '"', ' ',
      '/', '>' };

  /** the default css link */
  private static final char[] DEFAULT_CSS_LINK = { '<', 'l', 'i', 'n',
      'k', ' ', 'r', 'e', 'l', '=', '"', 's', 't', 'y', 'l', 'e', 's',
      'h', 'e', 'e', 't', '"', ' ', 'm', 'e', 'd', 'i', 'a', '=', '"',
      'a', 'l', 'l', '"', ' ', 'h', 'r', 'e', 'f', '=', '"' };
  /** the print css link */
  private static final char[] PRINT_CSS_LINK = { '<', 'l', 'i', 'n', 'k',
      ' ', 'r', 'e', 'l', '=', '"', 's', 't', 'y', 'l', 'e', 's', 'h',
      'e', 'e', 't', '"', ' ', 'm', 'e', 'd', 'i', 'a', '=', '"', 'p',
      'r', 'i', 'n', 't', '"', ' ', 'h', 'r', 'e', 'f', '=', '"', };

  /** the header end */
  private static final char[] HEADER_END = { '<', '/', 'h', 'e', 'a', 'd',
      '>' };
  /** the body begin */
  private static final char[] BODY_BEGIN = { '<', 'b', 'o', 'd', 'y', '>' };
  /** the body end */
  private static final char[] BODY_END = { '<', '/', 'b', 'o', 'd', 'y',
      '>' };
  /** the html end */
  private static final char[] HTML_END = { '<', '/', 'h', 't', 'm', 'l',
      '>' };

  /** the infinity char input */
  private static final CharInput INFINITY = new CharCharInput('\u221e');
  /** the nan char input */
  private static final CharInput NAN = new CharCharInput('\u2205');

  /** the label end */
  private static final char[] EMPTY_ATTRIB_TAG_END = { '"', ' ', '/', '>' };

  /** non-breaking space */
  static final char[] NBSP = { '&', 'n', 'b', 's', 'p', ';' };
  /** the br */
  private static final char[] BR = { '<', 'b', 'r', '/', '>', '&', 'n',
      'b', 's', 'p', ';', '&', 'n', 'b', 's', 'p', ';', '&', 'n', 'b',
      's', 'p', ';', '&', 'n', 'b', 's', 'p', ';' };
  /** the title start */
  private static final char[] TITLE_BEGIN = { '<', 't', 'i', 't', 'l',
      'e', '>' };
  /** the title end */
  private static final char[] TITLE_END = { '<', '/', 't', 'i', 't', 'l',
      'e', '>' };
  /** the label start */
  private static final char[] LABEL_BEGIN = { '<', 'a', ' ', 'i', 'd',
      '=', '"', };
  /** the label end */
  private static final char[] LABEL_END = {// avoid empty tags
  '"', '>', '<', '/', 'a', '>' };

  /** the sequence starts */
  private static final char[][] SEQUENCE_BEGIN = { EmptyUtils.EMPTY_CHARS,// COMMA
      EmptyUtils.EMPTY_CHARS,// AND
      EmptyUtils.EMPTY_CHARS,// OR
      { 'e', 'i', 't', 'h', 'e', 'r', ' ' },// xor
      { 'n', 'e', 'i', 't', 'h', 'e', 'r', ' ' },// nor
      EmptyUtils.EMPTY_CHARS,// to
  };

  /** the single-element sequence begin */
  private static final char[][] SEQUENCE_SINGLE = {
      EmptyUtils.EMPTY_CHARS,// COMMA
      EmptyUtils.EMPTY_CHARS,// AND
      EmptyUtils.EMPTY_CHARS,// OR
      EmptyUtils.EMPTY_CHARS,// OR
      { 'n', 'o', 't', ' ' },// nor
      EmptyUtils.EMPTY_CHARS,// to
  };

  /** the sequence starts */
  static final char[] SEQUENCE_SMALL_SEP = { ',', ' ' };
  /** the sequence starts */
  private static final char[][] SEQUENCE_BIG_SEP = {
      EmptyUtils.EMPTY_CHARS,// COMMA
      { 'a', 'n', 'd' },// AND
      { 'o', 'r' },// OR
      { 'o', 'r' },// xor
      { 'n', 'o', 'r' },// nor
      { 't', 'o' },// to
  };

  /** the label above char */
  private static final char[] REF_ABOVE = //
  XMLCharTransformer.INSTANCE.transform("\u25b2").toCharArray(); //$NON-NLS-1$
  /** the label above char */
  private static final char[] REF_BELOW = //
  XMLCharTransformer.INSTANCE.transform("\u25bc").toCharArray(); //$NON-NLS-1$ 

  /** the 1st part of a reference */
  private static final char[] REF_1 = { '<', 'a', ' ', 'h', 'r', 'e', 'f',
      '=', '"', '#' };

  /** the 3rd part of a reference */
  static final char[] REF_3 = { '<', '/', 'a', '>' };

  /** the date */
  private static final char[] META_DATE = { '<', 'm', 'e', 't', 'a', ' ',
      'n', 'a', 'm', 'e', '=', '"', 'd', 'a', 't', 'e', '"', ' ', 'c',
      'o', 'n', 't', 'e', 'n', 't', '=', '"' };
  /** the author */
  private static final char[] META_AUTHOR = { '<', 'm', 'e', 't', 'a',
      ' ', 'n', 'a', 'm', 'e', '=', '"', 'a', 'u', 't', 'h', 'o', 'r',
      '"', ' ', 'c', 'o', 'n', 't', 'e', 'n', 't', '=', '"' };

  /** the head line start */
  private static final char[][] HEADLINE_BEGIN = {
      { '<', 'h', '1', '>' },
      { '<', 'h', '2', '>' },
      { '<', 'h', '3', '>' },
      { '<', 'h', '4', '>' },
      { '<', 'h', '5', '>' },
      { '<', 'h', '6', '>' },
      { '<', 's', 'p', 'a', 'n', ' ', 'c', 'l', 'a', 's', 's', '=', '"',
          'h', '7', '"', '>' } };

  /** the span end */
  static final char[] SPAN_END = { '<', '/', 's', 'p', 'a', 'n', '>', };

  /** the head line end */
  private static final char[][] HEADLINE_END = {
      { '<', '/', 'h', '1', '>' }, { '<', '/', 'h', '2', '>' },
      { '<', '/', 'h', '3', '>' }, { '<', '/', 'h', '4', '>' },
      { '<', '/', 'h', '5', '>' }, { '<', '/', 'h', '6', '>' },
      XHTMLDocument.SPAN_END };

  /** the abstract start */
  private static final char[] ABSTRACT_DIV_BEGIN = { '<', 'd', 'i', 'v',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'a', 'b', 's', 't', 'r',
      'a', 'c', 't', '"', '>', '<', 's', 'p', 'a', 'n', ' ', 'c', 'l',
      'a', 's', 's', '=', '"', 'a', 'b', 's', 't', 'r', 'a', 'c', 't',
      'T', 'i', 't', 'l', 'e', '"', '>', 'A', 'b', 's', 't', 'r', 'a',
      'c', 't', '.', '<', '/', 's', 'p', 'a', 'n', '>', '&', 'n', 'b',
      's', 'p', ';' };

  /** the div end */
  private static final char[] DIV_END = { '<', '/', 'd', 'i', 'v', '>' };

  /** the span for numbers */
  private static final char[] SPAN_NUMBER = { '<', 's', 'p', 'a', 'n',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'n', 'u', 'm', 'b', 'e',
      'r', '"', '>' };

  /** the span end */
  private static final char[] SPAN_END_NBSP = { '<', '/', 's', 'p', 'a',
      'n', '>', '&', 'n', 'b', 's', 'p', ';' };

  /** the authors start */
  private static final char[] AUTHORS_DIV_BEGIN = { '<', 'd', 'i', 'v',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'a', 'u', 't', 'h', 'o',
      'r', 's', '"', '>', 'b', 'y', '&', 'n', 'b', 's', 'p', ';' };
  /** the date start */
  private static final char[] DATE_DIV_BEGIN = { '<', 'd', 'i', 'v', ' ',
      'c', 'l', 'a', 's', 's', '=', '"', 'd', 'a', 't', 'e', '"', '>',
      'o', 'n', '&', 'n', 'b', 's', 'p', ';' };
  /** the start of the section div */
  private static final char[] SECTION_DIV_BEGIN = { '<', 'd', 'i', 'v',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 's', 'e', 'c', 't', 'i',
      'o', 'n', '"', '>' };
  /** the start of the section head div */
  private static final char[] SECTION_HEAD_DIV_BEGIN = { '<', 'd', 'i',
      'v', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 's', 'e', 'c', 't',
      'i', 'o', 'n', 'H', 'e', 'a', 'd', '"', '>' };
  /** the start of the section body div */
  private static final char[] SECTION_BODY_DIV_BEGIN = { '<', 'd', 'i',
      'v', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 's', 'e', 'c', 't',
      'i', 'o', 'n', 'B', 'o', 'd', 'y', '"', '>' };

  /** the start of the float */
  private static final char[] FIGURE_DIV_BEGIN = { '<', 'd', 'i', 'v',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'f', 'i', 'g', 'u', 'r',
      'e', '"', '>', };

  /** the start of the float */
  private static final char[] FIGURE_TABLE_BEGIN = { '<', 't', 'a', 'b',
      'l', 'e', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'f', 'i', 'g',
      'u', 'r', 'e', '"', '>' };

  /** the start of the float tr caption */
  private static final char[] FIGURE_TR_CAPTION_BEGIN = { '<', 't', 'r',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'f', 'i', 'g', 'u', 'r',
      'e', 'C', 'a', 'p', 't', 'i', 'o', 'n', '"', '>' };
  /** the start of the float tr body */
  private static final char[] FIGURE_TR_BODY_BEGIN = { '<', 't', 'r', ' ',
      'c', 'l', 'a', 's', 's', '=', '"', 'f', 'i', 'g', 'u', 'r', 'e',
      'B', 'o', 'd', 'y', '"', '>' };
  /** the start of the float td */
  private static final char[] FIGURE_TD_CAPTION_SPAN_BEGIN = { '<', 't',
      'd', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'f', 'i', 'g', 'u',
      'r', 'e', 'C', 'a', 'p', 't', 'i', 'o', 'n', '"', '>', '<', 's',
      'p', 'a', 'n', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'c', 'a',
      'p', 't', 'i', 'o', 'n', 'T', 'i', 't', 'l', 'e', '"', '>', 'F',
      'i', 'g', '.', '&', 'n', 'b', 's', 'p', ';' };
  /** the start of the float body td */
  private static final char[] FIGURE_TD_BODY_BEGIN = { '<', 't', 'd', ' ',
      'c', 'l', 'a', 's', 's', '=', '"', 'f', 'i', 'g', 'u', 'r', 'e',
      'B', 'o', 'd', 'y', '"', '>' };

  /** the start of the float */
  private static final char[] SUBFIGURE_TABLE_BEGIN = { '<', 't', 'a',
      'b', 'l', 'e', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 's', 'u',
      'b', 'f', 'i', 'g', 'u', 'r', 'e', '"', '>' };

  /** the start of the float tr caption */
  private static final char[] SUBFIGURE_TR_CAPTION_BEGIN = { '<', 't',
      'r', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 's', 'u', 'b', 'f',
      'i', 'g', 'u', 'r', 'e', 'C', 'a', 'p', 't', 'i', 'o', 'n', '"', '>' };
  /** the start of the float tr body */
  private static final char[] SUBFIGURE_TR_BODY_BEGIN = { '<', 't', 'r',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 's', 'u', 'b', 'f', 'i',
      'g', 'u', 'r', 'e', 'B', 'o', 'd', 'y', '"', '>' };
  /** the start of the float td */
  private static final char[] SUBFIGURE_TD_CAPTION_SPAN_BEGIN = { '<',
      't', 'd', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 's', 'u', 'b',
      'f', 'i', 'g', 'u', 'r', 'e', 'C', 'a', 'p', 't', 'i', 'o', 'n',
      '"', '>', '<', 's', 'p', 'a', 'n', ' ', 'c', 'l', 'a', 's', 's',
      '=', '"', 'c', 'a', 'p', 't', 'i', 'o', 'n', 'T', 'i', 't', 'l',
      'e', '"', '>', 'F', 'i', 'g', '.', '&', 'n', 'b', 's', 'p', ';' };
  /** the start of the float td */
  private static final char[] SUBFIGURE_TD_CAPTION_EMPTY = { '<', 't',
      'd', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 's', 'u', 'b', 'f',
      'i', 'g', 'u', 'r', 'e', 'C', 'a', 'p', 't', 'i', 'o', 'n', '"',
      '/', '>' };
  /** the start of the float body td */
  private static final char[] SUBFIGURE_TD_BODY_BEGIN = { '<', 't', 'd',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 's', 'u', 'b', 'f', 'i',
      'g', 'u', 'r', 'e', 'B', 'o', 'd', 'y', '"', '>' };
  /** the start of the float body td */
  private static final char[] SUBFIGURE_TD_BODY_EMPTY = { '<', 't', 'd',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 's', 'u', 'b', 'f', 'i',
      'g', 'u', 'r', 'e', 'B', 'o', 'd', 'y', '"', '/', '>' };

  /** the start figure image: uri follows */
  private static final char[] FIGURE_IMG_URI = { '<', 'i', 'm', 'g', ' ',
      'c', 'l', 'a', 's', 's', '=', '"', 'f', 'i', 'g', 'u', 'r', 'e',
      '"', ' ', 's', 'r', 'c', '=', '"' };
  /** the start figure image width: width follows */
  private static final char[] FIGURE_IMG_WIDTH = { '"', ' ', 's', 't',
      'y', 'l', 'e', '=', '"', 'w', 'i', 'd', 't', 'h', ':', };
  /** the start figure image height: height will follow */
  private static final char[] FIGURE_IMG_HEIGHT = { 'm', 'm', ';', 'h',
      'e', 'i', 'g', 'h', 't', ':', };
  /** the start figure image: alt will follow */
  private static final char[] FIGURE_IMG_ALT = { 'm', 'm', '"', ' ', 'a',
      'l', 't', '=', '"' };

  /** the end of the table */
  static final char[] TABLE_END = { '<', '/', 't', 'a', 'b', 'l', 'e', '>' };
  /** the end of a tr */
  static final char[] TR_END = { '<', '/', 't', 'r', '>' };
  /** the end of a td */
  static final char[] TD_END = { '<', '/', 't', 'd', '>' };

  /** the start of ol */
  private static final char[] OL_BEGIN = { '<', 'o', 'l', '>' };
  /** the end of ol */
  private static final char[] OL_END = { '<', '/', 'o', 'l', '>' };
  /** the start of ul */
  private static final char[] UL_BEGIN = { '<', 'u', 'l', '>' };
  /** the end of ul */
  private static final char[] UL_END = { '<', '/', 'u', 'l', '>' };
  /** the start of il */
  private static final char[] IL_BEGIN = { '<', 'l', 'i', '>' };
  /** the end of il */
  private static final char[] IL_END = { '<', '/', 'l', 'i', '>' };

  /** the start of the table */
  private static final char[] TABLE_DIV_BEGIN = { '<', 'd', 'i', 'v', ' ',
      'c', 'l', 'a', 's', 's', '=', '"', 't', 'a', 'b', 'l', 'e', '"',
      '>', };

  /** the start of the table */
  private static final char[] TABLE_TABLE_BEGIN = { '<', 't', 'a', 'b',
      'l', 'e', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 't', 'a', 'b',
      'l', 'e', '"', '>' };

  /** the start of the float tr caption */
  private static final char[] TABLE_TR_CAPTION_BEGIN = { '<', 't', 'r',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 't', 'a', 'b', 'l', 'e',
      'C', 'a', 'p', 't', 'i', 'o', 'n', '"', '>' };
  /** the start of the float tr body */
  private static final char[] TABLE_TR_BODY_BEGIN = { '<', 't', 'r', ' ',
      'c', 'l', 'a', 's', 's', '=', '"', 't', 'a', 'b', 'l', 'e', 'B',
      'o', 'd', 'y', '"', '>' };
  /** the start of the float td */
  private static final char[] TABLE_TD_CAPTION_SPAN_BEGIN = { '<', 't',
      'd', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 't', 'a', 'b', 'l',
      'e', 'C', 'a', 'p', 't', 'i', 'o', 'n', '"', '>', '<', 's', 'p',
      'a', 'n', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'c', 'a', 'p',
      't', 'i', 'o', 'n', 'T', 'i', 't', 'l', 'e', '"', '>', 'T', 'b',
      'l', '.', '&', 'n', 'b', 's', 'p', ';' };
  /** the start of the float body td */
  private static final char[] TABLE_TD_BODY_BEGIN = { '<', 't', 'd', ' ',
      'c', 'l', 'a', 's', 's', '=', '"', 't', 'a', 'b', 'l', 'e', 'B',
      'o', 'd', 'y', '"', '>' };

  /** the start of table */
  private static final char[] TAB_TABLE_BEGIN = { '<', 't', 'a', 'b', 'l',
      'e', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 't', 'a', 'b', '"', '>' };
  /** the start of the table head */
  private static final char[] TAB_HEAD_BEGIN = { '<', 't', 'h', 'e', 'a',
      'd', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 't', 'a', 'b', '"', '>' };
  /** the end of the table head */
  private static final char[] TAB_HEAD_END = { '<', '/', 't', 'h', 'e',
      'a', 'd', '>' };
  /** the start of table body */
  private static final char[] TAB_BODY_BEGIN = { '<', 't', 'b', 'o', 'd',
      'y', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 't', 'a', 'b', '"', '>' };
  /** the end of the table body */
  private static final char[] TAB_BODY_END = { '<', '/', 't', 'b', 'o',
      'd', 'y', '>' };

  /** the start of table foot */
  private static final char[] TAB_FOOT_BEGIN = { '<', 't', 'f', 'o', 'o',
      't', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 't', 'a', 'b', '"', '>' };
  /** the end of the table foot */
  private static final char[] TAB_FOOT_END = { '<', '/', 't', 'f', 'o',
      'o', 't', '>' };

  /** the start of a normal tr */
  private static final char[] TAB_TR_H_BEGIN = { '<', 't', 'r', ' ', 'c',
      'l', 'a', 's', 's', '=', '"', 't', 'a', 'b', 'H', '"', '>' };

  /** the start of a normal tr */
  private static final char[] TAB_TR_F_BEGIN = { '<', 't', 'r', ' ', 'c',
      'l', 'a', 's', 's', '=', '"', 't', 'a', 'b', 'F', '"', '>' };

  /** the start of an even tr */
  private static final char[] TAB_TR_BE_BEGIN = { '<', 't', 'r', ' ', 'c',
      'l', 'a', 's', 's', '=', '"', 't', 'a', 'b', 'B', 'E', '"', '>' };
  /** the start of an odd tr */
  private static final char[] TAB_TR_BO_BEGIN = { '<', 't', 'r', ' ', 'c',
      'l', 'a', 's', 's', '=', '"', 't', 'a', 'b', 'B', 'O', '"', '>' };
  /** the start of an even tr */
  private static final char[] TAB_TR_BEHR_BEGIN = { '<', 't', 'r', ' ',
      'c', 'l', 'a', 's', 's', '=', '"', 't', 'a', 'b', 'B', 'E', 'H',
      'R', '"', '>' };
  /** the start of an odd tr */
  private static final char[] TAB_TR_BOHR_BEGIN = { '<', 't', 'r', ' ',
      'c', 'l', 'a', 's', 's', '=', '"', 't', 'a', 'b', 'B', 'O', 'H',
      'R', '"', '>' };
  /** the start of the TH */
  private static final char[] TAB_TH_BEGIN = { '<', 't', 'h' };
  /** the end of the TH */
  private static final char[] TAB_TH_END = { '<', '/', 't', 'h', '>' };
  /** the start of the td */
  private static final char[] TAB_TD_BEGIN = { '<', 't', 'd' };
  /** the class c */
  private static final char[] TAB_CLASS_C = { ' ', 'c', 'l', 'a', 's',
      's', '=', '"', 't', 'a', 'b', 'C', '"', '>' };
  /** the class l */
  private static final char[] TAB_CLASS_L = { ' ', 'c', 'l', 'a', 's',
      's', '=', '"', 't', 'a', 'b', 'L', '"', '>' };
  /** the class r */
  private static final char[] TAB_CLASS_R = { ' ', 'c', 'l', 'a', 's',
      's', '=', '"', 't', 'a', 'b', 'R', '"', '>' };

  /** the greater char */
  private static final char[] GREATER = { '>' };

  /** the sub begin */
  private static final char[] SUB_BEGIN = { '<', 's', 'u', 'b', '>' };
  /** the sub end */
  private static final char[] SUB_END = { '<', '/', 's', 'u', 'b', '>' };
  /** the sub math begin */
  private static final char[] SUB_MATH_BEGIN = { '<', 's', 'u', 'b', ' ',
      'c', 'l', 'a', 's', 's', '=', '"', 'm', 'a', 't', 'h', '"', '>' };
  /** the sup begin */
  private static final char[] SUP_BEGIN = { '<', 's', 'u', 'p', '>' };
  /** the sup end */
  private static final char[] SUP_END = { '<', '/', 's', 'u', 'p', '>' };
  /** the sup math begin */
  private static final char[] SUP_MATH_BEGIN = { '<', 's', 'u', 'p', ' ',
      'c', 'l', 'a', 's', 's', '=', '"', 'm', 'a', 't', 'h', '"', '>' };
  /** the emphasize begin */
  private static final char[] EM_BEGIN = { '<', 'e', 'm', '>' };
  /** the emphasize end */
  private static final char[] EM_END = { '<', '/', 'e', 'm', '>' };

  /** the equation div begin */
  private static final char[] EQU_DIV = { '<', 'd', 'i', 'v', ' ', 'c',
      'l', 'a', 's', 's', '=', '"', 'e', 'q', 'u', 'a', 't', 'i', 'o',
      'n', '"', '>' };
  /** the equation table begin */
  private static final char[] EQU_TAB = { '<', 't', 'a', 'b', 'l', 'e',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'e', 'q', 'u', 'a', 't',
      'i', 'o', 'n', '"', '>' };
  /** the equation tr begin */
  private static final char[] EQU_TR = { '<', 't', 'r', ' ', 'c', 'l',
      'a', 's', 's', '=', '"', 'e', 'q', 'u', 'a', 't', 'i', 'o', 'n',
      '"', '>' };
  /** the equation td begin */
  private static final char[] EQU_BODY_TD = { '<', 't', 'd', ' ', 'c',
      'l', 'a', 's', 's', '=', '"', 'e', 'q', 'u', 'a', 't', 'i', 'o',
      'n', 'B', 'o', 'd', 'y', '"', '>' };
  /** the equation td begin */
  private static final char[] EQU_NR_TD = { '<', 't', 'd', ' ', 'c', 'l',
      'a', 's', 's', '=', '"', 'e', 'q', 'u', 'a', 't', 'i', 'o', 'n',
      'N', 'u', 'm', 'b', 'e', 'r', '"', '>' };

  /** the math span begin */
  private static final char[] MATH_DIV_BEGIN = { '<', 'd', 'i', 'v', ' ',
      'c', 'l', 'a', 's', 's', '=', '"', 'm', 'a', 't', 'h', '"', '>' };

  /** the normal span begin */
  private static final char[] NORMAL_SPAN_BEGIN = { '<', 's', 'p', 'a',
      'n', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'n', 'o', 'r', 'm',
      'a', 'l', '"', '>' };

  /** the math name span begin */
  private static final char[][] NAME_SPAN_BEGIN = {
      { '<', 's', 'p', 'a', 'n', ' ', 'c', 'l', 'a', 's', 's', '=', '"',
          's', 'c', 'a', 'l', 'a', 'r', '"', '>' },
      { '<', 's', 'p', 'a', 'n', ' ', 'c', 'l', 'a', 's', 's', '=', '"',
          'v', 'e', 'c', 't', 'o', 'r', '"', '>' },
      { '<', 's', 'p', 'a', 'n', ' ', 'c', 'l', 'a', 's', 's', '=', '"',
          'm', 'a', 't', 'r', 'i', 'x', '"', '>' },
      { '<', 's', 'p', 'a', 'n', ' ', 'c', 'l', 'a', 's', 's', '=', '"',
          's', 'e', 't', '"', '>' },
      { '<', 's', 'p', 'a', 'n', ' ', 'c', 'l', 'a', 's', 's', '=', '"',
          's', 'p', 'a', 'c', 'e', '"', '>' },
      { '<', 's', 'p', 'a', 'n', ' ', 'c', 'l', 'a', 's', 's', '=', '"',
          't', 'u', 'p', 'l', 'e', '"', '>' }, };

  /** the reference table */
  private static final char[] REF_TAB = { '<', 't', 'a', 'b', 'l', 'e',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'r', 'e', 'f', '"', '>' };
  /** the reference table tr */
  private static final char[] REF_TR = { '<', 't', 'r', ' ', 'c', 'l',
      'a', 's', 's', '=', '"', 'r', 'e', 'f', '"', '>' };
  /** the reference table idx td */
  private static final char[] REF_ID_TD = { '<', 't', 'd', ' ', 'c', 'l',
      'a', 's', 's', '=', '"', 'r', 'e', 'f', 'I', 'd', '"', '>' };
  /** the reference table text td */
  private static final char[] REF_TXT_TD = { '<', 't', 'd', ' ', 'c', 'l',
      'a', 's', 's', '=', '"', 'r', 'e', 'f', 'T', 'x', 't', '"', '>' };

  /** the reference id */
  private static final char[] A_REF_ID = { '<', 'a', ' ', 'c', 'l', 'a',
      's', 's', '=', '"', 'r', 'e', 'f', 'I', 'd', '"', ' ', 'i', 'd',
      '=', '"' };

  /** the reference href id */
  private static final char[] A_REF = { '<', 'a', ' ', 'c', 'l', 'a', 's',
      's', '=', '"', 'r', 'e', 'f', '"', ' ', 'h', 'r', 'e', 'f', '=',
      '"', '#' };
  /** the reference span id */
  private static final char[] SPAN_REF = { '<', 's', 'p', 'a', 'n', ' ',
      'c', 'l', 'a', 's', 's', '=', '"', 'r', 'e', 'f', '"', '>' };

  /** the output stream */
  private final OutputStream m_os;

  /** the output stream writer */
  private final OutputStreamWriter m_osw;

  /** the file output writer */
  private final BufferedWriter m_writer;

  /** the character output chain to use */
  private _Output m_chain;

  /** the temporary output stream */
  WriterCharOutput m_out;

  /** are we human readable? */
  private final boolean m_isHumanReadable;

  /** the macros */
  private final HashMap<MacroDescriptor, char[]> m_macros;

  /** the title collected when writing the header */
  private char[] m_title;

  /** the authors */
  private char[] m_authors;

  /** the abstract */
  private char[] m_abstract;

  /** the float caption */
  private char[] m_floatCaption;

  /** the table head */
  private char[] m_tableHead;
  /** the table foot */
  private char[] m_tableFoot;

  /** the sub-figure captions */
  private char[][] m_subfigCaptions;

  /** the labels */
  private SingleLabel[] m_subfigLabels;

  /** the subfigure counter */
  private int m_subfigCount;

  /** does the table have a body? */
  private boolean m_tblHasBody;

  /** a horizontal row should be printed */
  private boolean m_tblHR;

  /**
   * instantiate
   * 
   * @param context
   *          the owning context
   * @param file
   *          the output file
   * @throws IOException
   *           if io fails
   */
  protected XHTMLDocument(final XHTMLContext context, final File file)
      throws IOException {
    super(context);

    this.m_os = new FileOutputStream(file);
    this.m_osw = new OutputStreamWriter(this.m_os,//
        EEncoding.ISO_8859_1.getJavaName());
    this.m_writer = new BufferedWriter(this.m_osw);

    this.m_chain = new _Output(null, this.m_writer);
    this.m_out = this.m_chain.m_out;
    this.m_isHumanReadable = context.getDriver().isHumanReadable();
    this.m_macros = new HashMap<>();
  }

  /** push a char writer */
  private final void __pushCharArrayWriter() {
    final _Output old;
    old = this.m_chain;
    this.m_chain = new _Output(old);
    this.m_out = this.m_chain.m_out;
  }

  /**
   * pop a char array writer
   * 
   * @return the contents written to the writer, {@code null} if empty
   */
  private final char[] __popCharArrayWriter() {
    final char[] ch;

    ch = this.m_chain._getChars();
    this.m_chain = this.m_chain.m_next;
    this.m_out = this.m_chain.m_out;
    return ch;
  }

  /**
   * write a conditional line feed
   * 
   * @throws IOException
   *           if i/o fails
   */
  private final void __feed() throws IOException {
    final int len;

    if (this.m_isHumanReadable) {
      len = (XHTMLDocument.NEWLINE_LENGTH + (this.m_chain.m_tagDepth << 1));
      this.m_out.write(XHTMLDocument.LINE_FEED, 0,
          Math.min(XHTMLDocument.LINE_FEED.length, len));
    }
  }

  /**
   * change the depth
   * 
   * @param add
   *          the adder
   */
  private final void __changeDepth(final int add) {
    if ((this.m_chain.m_tagDepth += add) < 0) {
      throw new IllegalStateException();
    }

  }

  /** {@inheritDoc} */
  @Override
  public XHTMLContext getOwner() {
    return ((XHTMLContext) (super.getOwner()));
  }

  /** {@inheritDoc} */
  @Override
  protected void writeChar(final int data) throws IOException {
    XMLCharTransformer.INSTANCE.transform(//
        new CharCharInput(data), this.m_out, 0, 1);
  }

  /** {@inheritDoc} */
  @Override
  protected void write(final String data) throws IOException {
    XMLCharTransformer.INSTANCE.transform(//
        new StringCharInput(data), this.m_out, 0, data.length());
  }

  /** {@inheritDoc} */
  @Override
  protected void writeHyphenated(final String data) throws IOException {
    XMLCharTransformer.INSTANCE.transformHyphenated(//
        new StringCharInput(data), this.m_out, 0, data.length());
  }

  /** {@inheritDoc} */
  @Override
  protected void writeInfinity(final boolean positive) throws IOException {
    this.m_out.write(XHTMLDocument.SPAN_NUMBER, 0,
        XHTMLDocument.SPAN_NUMBER.length);
    this.m_out.write(positive ? '+' : '-');
    XMLCharTransformer.INSTANCE.transform(XHTMLDocument.INFINITY,
        this.m_out, 0, 1);
    this.m_out.write(XHTMLDocument.SPAN_END, 0,
        XHTMLDocument.SPAN_END.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeNaN() throws IOException {
    this.m_out.write(XHTMLDocument.SPAN_NUMBER, 0,
        XHTMLDocument.SPAN_NUMBER.length);
    XMLCharTransformer.INSTANCE.transform(XHTMLDocument.NAN, this.m_out,
        0, 1);
    this.m_out.write(XHTMLDocument.SPAN_END, 0,
        XHTMLDocument.SPAN_END.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeNumber(final String number) throws IOException {
    this.m_out.write(XHTMLDocument.SPAN_NUMBER, 0,
        XHTMLDocument.SPAN_NUMBER.length);
    this.write(number);
    this.m_out.write(XHTMLDocument.SPAN_END, 0,
        XHTMLDocument.SPAN_END.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeFormattedNumber(final String number)
      throws IOException {
    this.m_out.write(XHTMLDocument.SPAN_NUMBER, 0,
        XHTMLDocument.SPAN_NUMBER.length);
    this.write(number);
    this.m_out.write(XHTMLDocument.SPAN_END, 0,
        XHTMLDocument.SPAN_END.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeNoneBreakingSpace() throws IOException {
    this.m_out.write(XHTMLDocument.NBSP, 0, XHTMLDocument.NBSP.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeLinebreak() throws IOException {
    this.__feed();
    this.m_out.write(XHTMLDocument.BR, 0, XHTMLDocument.BR.length);
    this.__feed();
  }

  /** {@inheritDoc} */
  @Override
  protected void titleBegin(final Title h) throws IOException,
      IllegalStateException {
    super.titleBegin(h);
    this.__pushCharArrayWriter();
  }

  /** {@inheritDoc} */
  @Override
  protected void titleEnd(final Title h) throws IOException,
      IllegalStateException {

    try {
      this.m_title = this.__popCharArrayWriter();

      if (this.m_title != null) {
        this.__feed();
        this.m_out.write(XHTMLDocument.TITLE_BEGIN, 0,
            XHTMLDocument.TITLE_BEGIN.length);
        this.m_out.write(this.m_title, 0, this.m_title.length);
        this.m_out.write(XHTMLDocument.TITLE_END, 0,
            XHTMLDocument.TITLE_END.length);
      }

    } finally {
      super.titleEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void doWriteSingleLabel(final SingleLabel label)
      throws IOException {
    final String s;

    this.m_out.write(XHTMLDocument.LABEL_BEGIN, 0,
        XHTMLDocument.LABEL_BEGIN.length);
    s = label.getKey();
    this.m_out.write(s, 0, s.length());
    this.m_out.write(XHTMLDocument.LABEL_END, 0,
        XHTMLDocument.LABEL_END.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSequence(final Sequence sequence,
      final ESequenceType type,
      final boolean connectLastElementWithNonBreakableSpace)
      throws IOException {
    final int idx;
    final int len, stop;
    char[] chars;
    int i;

    idx = type.ordinal();
    len = sequence.length();
    if (len < 1) {
      throw new IllegalArgumentException(//
          "Sequence must have at least one element!"); //$NON-NLS-1$
    }

    if (len == 1) {
      chars = XHTMLDocument.SEQUENCE_SINGLE[idx];
      if (chars.length > 0) {
        this.m_out.write(chars, 0, chars.length);
      }
      sequence.write(0);
      return;
    }

    chars = XHTMLDocument.SEQUENCE_BEGIN[idx];
    stop = ((type == ESequenceType.FROM_TO) ? 1 : (len - 1));

    for (i = 0; i < stop; i++) {
      if (chars.length > 0) {
        this.m_out.write(chars, 0, chars.length);
      }
      sequence.write(i);
      chars = XHTMLDocument.SEQUENCE_SMALL_SEP;
    }

    if ((stop > 1) || (type == ESequenceType.COMMA)) {
      if (chars.length > 0) {
        this.m_out.write(chars, 0, chars.length);
      }
    } else {
      this.m_out.write(' ');
    }

    chars = XHTMLDocument.SEQUENCE_BIG_SEP[idx];
    if (chars.length > 0) {
      this.m_out.write(chars, 0, chars.length);
    }

    if (connectLastElementWithNonBreakableSpace) {
      this.m_out.write(XHTMLDocument.NBSP, 0, XHTMLDocument.NBSP.length);
    } else {
      this.m_out.write(' ');
    }
    sequence.write(len - 1);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeReference(final ESequenceType type,
      final SingleLabel[] labels) throws IOException {
    this.writeSequence(new _RefSequence(labels, this,
        (type == ESequenceType.FROM_TO)), type, true);
  }

  /**
   * write a single reference
   * 
   * @param label
   *          the reference
   * @throws IOException
   *           if io fails
   */
  protected void writeSingleRef(final SingleLabel label)
      throws IOException {
    final String s, info;
    final boolean w;
    final char[] ch;

    this.m_out.write(XHTMLDocument.REF_1, 0, XHTMLDocument.REF_1.length);
    s = label.getKey();
    this.m_out.write(s, 0, s.length());
    this.m_out.write(XHTMLDocument.ATTRIB_TAG_BEGIN_END, 0,
        XHTMLDocument.ATTRIB_TAG_BEGIN_END.length);

    writeLabel: {
      w = label.hasBeenWritten();
      if (w) {
        info = label.getInfo();
        if (info != null) {
          this.write(info);
          break writeLabel;
        }
      }
      ch = (w ? XHTMLDocument.REF_ABOVE : XHTMLDocument.REF_BELOW);
      this.m_out.write(ch, 0, ch.length);
    }
    this.m_out.write(XHTMLDocument.REF_3, 0, XHTMLDocument.REF_3.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    try {
      try {
        try {
          this.m_writer.close();
        } finally {
          this.m_osw.close();
        }
      } finally {
        this.m_os.close();
      }
    } finally {
      super.doClose();
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void headerBegin(final Header h) throws IOException,
      IllegalStateException {
    final String encStr, date;
    EEncoding enc;

    super.headerBegin(h);

    this.m_out.write(XHTMLDocument.XML_HEADER_BEGIN, 0,
        XHTMLDocument.XML_HEADER_BEGIN.length);

    enc = this.getOwner().getDriver().getPreferedTextEncoding();
    if ((enc == null) || (enc == EEncoding.TEXT)
        || (enc == EEncoding.BINARY)) {
      enc = EEncoding.DEFAULT_TEXT_ENCODING;
    }
    encStr = enc.getStandardName();
    this.m_out.write(encStr, 0, encStr.length());
    this.m_out.write(XHTMLDocument.XML_HEADER_END, 0,
        XHTMLDocument.XML_HEADER_END.length);
    this.m_out.write(XHTMLDocument.LINE_FEED, 0,
        XHTMLDocument.NEWLINE_LENGTH);

    this.m_out.write(XHTMLDocument.DOC_TYPE, 0,
        XHTMLDocument.DOC_TYPE.length);
    this.m_out.write(XHTMLDocument.LINE_FEED, 0,
        XHTMLDocument.NEWLINE_LENGTH);

    this.m_out.write(XHTMLDocument.HTML_BEGIN, 0,
        XHTMLDocument.HTML_BEGIN.length);
    this.__changeDepth(1);
    this.__feed();
    this.m_out.write(XHTMLDocument.HEAD_BEGIN, 0,
        XHTMLDocument.HEAD_BEGIN.length);
    this.__changeDepth(1);
    this.__feed();
    this.m_out.write(XHTMLDocument.META_CHARSET_BEGIN, 0,
        XHTMLDocument.META_CHARSET_BEGIN.length);
    this.m_out.write(encStr, 0, encStr.length());
    this.m_out.write(XHTMLDocument.EMPTY_ATTRIB_TAG_END, 0,
        XHTMLDocument.EMPTY_ATTRIB_TAG_END.length);
    this.__feed();
    this.m_out.write(XHTMLDocument.META_STYLE_TYPE, 0,
        XHTMLDocument.META_STYLE_TYPE.length);
    this.__feed();
    this.m_out.write(XHTMLDocument.DEFAULT_CSS_LINK, 0,
        XHTMLDocument.DEFAULT_CSS_LINK.length);
    this.m_out.write(XHTMLContext.CSS_DEFAULT, 0,
        XHTMLContext.CSS_DEFAULT.length());
    this.m_out.write(XHTMLDocument.EMPTY_ATTRIB_TAG_END, 0,
        XHTMLDocument.EMPTY_ATTRIB_TAG_END.length);
    this.__feed();
    this.m_out.write(XHTMLDocument.PRINT_CSS_LINK, 0,
        XHTMLDocument.PRINT_CSS_LINK.length);
    this.m_out.write(XHTMLContext.CSS_PRINT, 0,
        XHTMLContext.CSS_PRINT.length());
    this.m_out.write(XHTMLDocument.EMPTY_ATTRIB_TAG_END, 0,
        XHTMLDocument.EMPTY_ATTRIB_TAG_END.length);

    date = TextUtils.prepare(this.getDateForHeader());
    if (date != null) {
      this.__feed();
      this.m_out.write(XHTMLDocument.META_DATE, 0,
          XHTMLDocument.META_DATE.length);
      XMLCharTransformer.INSTANCE.transform(//
          new StringCharInput(date), this.m_out, 0, date.length());
      this.m_out.write(XHTMLDocument.EMPTY_ATTRIB_TAG_END, 0,
          XHTMLDocument.EMPTY_ATTRIB_TAG_END.length);
    }

  }

  /** {@inheritDoc} */
  @Override
  protected void headerEnd(final Header h) throws IOException,
      IllegalStateException {
    try {
      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.HEADER_END, 0,
          XHTMLDocument.HEADER_END.length);
    } finally {
      super.headerEnd(h);
    }
  }

  /**
   * Get the date string for printing in the document header, or
   * {@code null} if no date should be printed
   * 
   * @return the date string
   */
  protected String getDateForHeader() {
    return (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")).format(new Date()); //$NON-NLS-1$
  }

  /**
   * Get the date string for printing in the document body, or {@code null}
   * if no date should be printed
   * 
   * @return the date string
   */
  protected String getDateForBody() {
    return (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void bodyBegin(final Body h) throws IOException,
      IllegalStateException {
    final String date;

    super.bodyBegin(h);

    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.BODY_BEGIN, 0,
        XHTMLDocument.BODY_BEGIN.length);

    if (this.m_title != null) {
      try {
        this.__feed();
        this.__changeDepth(1);

        this.m_out.write(XHTMLDocument.SECTION_DIV_BEGIN, 0,
            XHTMLDocument.SECTION_DIV_BEGIN.length);
        this.__feed();
        this.__changeDepth(1);
        try {
          this.m_out.write(XHTMLDocument.SECTION_HEAD_DIV_BEGIN, 0,
              XHTMLDocument.SECTION_HEAD_DIV_BEGIN.length);
          this.__feed();
          this.__changeDepth(1);
          try {
            this.m_out.write(XHTMLDocument.HEADLINE_BEGIN[0], 0,
                XHTMLDocument.HEADLINE_BEGIN[0].length);
            this.m_out.write(this.m_title, 0, this.m_title.length);
            this.m_out.write(XHTMLDocument.HEADLINE_END[0], 0,
                XHTMLDocument.HEADLINE_END[0].length);
          } finally {
            this.__changeDepth(-1);
          }
          this.__feed();
          this.m_out.write(XHTMLDocument.DIV_END, 0,
              XHTMLDocument.DIV_END.length);
        } finally {
          this.__changeDepth(-1);
        }
        this.__feed();
        this.__changeDepth(1);
        this.m_out.write(XHTMLDocument.SECTION_BODY_DIV_BEGIN, 0,
            XHTMLDocument.SECTION_BODY_DIV_BEGIN.length);
      } finally {// preserve the non-nullness for use at the end
        this.m_title = EmptyUtils.EMPTY_CHARS;
      }
    }

    if (this.m_abstract != null) {
      try {
        this.__feed();
        this.__changeDepth(1);
        try {
          this.m_out.write(XHTMLDocument.ABSTRACT_DIV_BEGIN, 0,
              XHTMLDocument.ABSTRACT_DIV_BEGIN.length);
          this.m_out.write(this.m_abstract, 0, this.m_abstract.length);
          this.m_out.write(XHTMLDocument.DIV_END, 0,
              XHTMLDocument.DIV_END.length);
        } finally {
          this.__changeDepth(-1);
        }
      } finally {
        this.m_abstract = null;
      }
    }

    if (this.m_authors != null) {
      try {
        this.__feed();
        this.__changeDepth(1);
        try {
          this.m_out.write(XHTMLDocument.AUTHORS_DIV_BEGIN, 0,
              XHTMLDocument.AUTHORS_DIV_BEGIN.length);
          this.m_out.write(this.m_authors, 0, this.m_authors.length);
          this.m_out.write(XHTMLDocument.DIV_END, 0,
              XHTMLDocument.DIV_END.length);
        } finally {
          this.__changeDepth(-1);
        }
      } finally {
        this.m_authors = null;
      }
    }

    date = TextUtils.prepare(this.getDateForBody());
    if (date != null) {
      this.__feed();
      this.__changeDepth(1);
      try {
        this.m_out.write(XHTMLDocument.DATE_DIV_BEGIN, 0,
            XHTMLDocument.DATE_DIV_BEGIN.length);
        this.m_out.write(date, 0, date.length());
        this.m_out.write(XHTMLDocument.DIV_END, 0,
            XHTMLDocument.DIV_END.length);
      } finally {
        this.__changeDepth(-1);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void bodyEnd(final Body h) throws IOException,
      IllegalStateException {
    try {
      this.makeBibliography();

      if (this.m_title != null) {
        this.__changeDepth(-1);
        this.__feed();
        this.m_out.write(XHTMLDocument.DIV_END, 0,
            XHTMLDocument.DIV_END.length);
        this.__changeDepth(-1);
        this.__feed();
        this.m_out.write(XHTMLDocument.DIV_END, 0,
            XHTMLDocument.DIV_END.length);
      }

      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.BODY_END, 0,
          XHTMLDocument.BODY_END.length);
      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.HTML_END, 0,
          XHTMLDocument.HTML_END.length);
    } finally {
      super.bodyEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void authorsBegin(final Authors h) throws IOException,
      IllegalStateException {
    super.authorsBegin(h);

    this.__pushCharArrayWriter();
    this.writeSequence(new _AuthorsSequence(h.getAuthors(), true, this),
        ESequenceType.AND, false);

    this.m_authors = this.__popCharArrayWriter();

    if (this.m_authors != null) {
      this.__feed();
      this.m_out.write(XHTMLDocument.META_AUTHOR, 0,
          XHTMLDocument.META_AUTHOR.length);
      this.m_out.write(this.m_authors, 0, this.m_authors.length);
      this.m_out.write(XHTMLDocument.EMPTY_ATTRIB_TAG_END, 0,
          XHTMLDocument.EMPTY_ATTRIB_TAG_END.length);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void summaryBegin(final Summary h) throws IOException,
      IllegalStateException {
    super.summaryBegin(h);
    this.__pushCharArrayWriter();
  }

  /** {@inheritDoc} */
  @Override
  protected void summaryEnd(final Summary h) throws IOException,
      IllegalStateException {
    try {
      this.m_abstract = this.__popCharArrayWriter();
    } finally {
      super.summaryEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void sectionBegin(final Section h) throws IOException,
      IllegalStateException {
    super.sectionBegin(h);
    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.SECTION_DIV_BEGIN, 0,
        XHTMLDocument.SECTION_DIV_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void sectionEnd(final Section h) throws IOException,
      IllegalStateException {
    try {
      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.DIV_END, 0,
          XHTMLDocument.DIV_END.length);
    } finally {
      super.sectionEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void sectionTitleBegin(final SectionTitle h)
      throws IOException, IllegalStateException {
    final SingleLabel l;
    String s;
    StringBuilder sb;
    int i;

    super.sectionTitleBegin(h);

    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.SECTION_HEAD_DIV_BEGIN, 0,
        XHTMLDocument.SECTION_HEAD_DIV_BEGIN.length);

    this.__feed();

    i = Math.min(h.getOwner().getDepth(),
        (XHTMLDocument.HEADLINE_BEGIN.length - 1));
    this.m_out.write(XHTMLDocument.HEADLINE_BEGIN[i], 0,
        XHTMLDocument.HEADLINE_BEGIN[i].length);

    sb = new StringBuilder();
    for (final int j : h.getOwner().getAllIndexes()) {
      s = (j + XHTMLDocument.DOT);
      sb.append(s);
      this.m_out.write(s, 0, s.length());
    }
    this.m_out.write(XHTMLDocument.NBSP, 0, XHTMLDocument.NBSP.length);

    l = h.getOwner().getLabel();
    if (l != null) {
      sb.setLength(sb.length() - 1);
      this.writeSingleLabel(l, sb.toString());
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void sectionTitleEnd(final SectionTitle h) throws IOException,
      IllegalStateException {
    final int i;

    try {
      i = Math.min(h.getOwner().getDepth(),
          (XHTMLDocument.HEADLINE_END.length - 1));
      this.m_out.write(XHTMLDocument.HEADLINE_END[i], 0,
          XHTMLDocument.HEADLINE_END[i].length);

      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.DIV_END, 0,
          XHTMLDocument.DIV_END.length);
    } finally {
      super.sectionTitleEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void sectionBodyBegin(final SectionBody h) throws IOException,
      IllegalStateException {
    super.sectionBodyBegin(h);

    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.SECTION_BODY_DIV_BEGIN, 0,
        XHTMLDocument.SECTION_BODY_DIV_BEGIN.length);
    this.__feed();
  }

  /** {@inheritDoc} */
  @Override
  protected void sectionBodyEnd(final SectionBody h) throws IOException,
      IllegalStateException {
    try {
      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.DIV_END, 0,
          XHTMLDocument.DIV_END.length);
    } finally {
      super.sectionBodyEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void figureBegin(final Figure h) throws IOException,
      IllegalStateException {
    super.figureBegin(h);

    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.FIGURE_DIV_BEGIN, 0,
        XHTMLDocument.FIGURE_DIV_BEGIN.length);
    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.FIGURE_TABLE_BEGIN, 0,
        XHTMLDocument.FIGURE_TABLE_BEGIN.length);
    this.m_floatCaption = null;
  }

  /** {@inheritDoc} */
  @Override
  protected void figureEnd(final Figure h) throws IOException,
      IllegalStateException {
    String t, s;
    SingleLabel l;

    try {
      this.__feed();
      this.__changeDepth(1);
      this.m_out.write(XHTMLDocument.FIGURE_TR_CAPTION_BEGIN, 0,
          XHTMLDocument.FIGURE_TR_CAPTION_BEGIN.length);

      this.__feed();
      this.__changeDepth(1);
      this.m_out.write(XHTMLDocument.FIGURE_TD_CAPTION_SPAN_BEGIN, 0,
          XHTMLDocument.FIGURE_TD_CAPTION_SPAN_BEGIN.length);
      s = ((t = String.valueOf(h.getFigureIndex())) + XHTMLDocument.DOT);
      this.m_out.write(s, 0, s.length());
      this.m_out.write(XHTMLDocument.SPAN_END_NBSP, 0,
          XHTMLDocument.SPAN_END_NBSP.length);
      l = h.getLabel();
      if (l != null) {
        this.writeSingleLabel(l, t);
      }

      this.m_out.write(this.m_floatCaption, 0, this.m_floatCaption.length);
      this.m_floatCaption = null;

      this.__changeDepth(-1);
      this.m_out.write(XHTMLDocument.TD_END, 0,
          XHTMLDocument.TD_END.length);

      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.TR_END, 0,
          XHTMLDocument.TR_END.length);

      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.TABLE_END, 0,
          XHTMLDocument.TABLE_END.length);
      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.DIV_END, 0,
          XHTMLDocument.DIV_END.length);
    } finally {
      super.figureEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void figureCaptionBegin(final FigureCaption h)
      throws IOException, IllegalStateException {
    super.figureCaptionBegin(h);
    this.__pushCharArrayWriter();
  }

  /** {@inheritDoc} */
  @Override
  protected void figureCaptionEnd(final FigureCaption h)
      throws IOException, IllegalStateException {
    try {
      this.m_floatCaption = this.__popCharArrayWriter();
    } finally {
      super.figureCaptionEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected FigureBody figureBodyCreate(final Element owner,
      final URI relativeNameBase, final Dimension2D sizeInMM)
      throws IOException, URISyntaxException {
    return new _RasterFigureBody(owner, relativeNameBase, sizeInMM);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureBodyBegin(final FigureBody h) throws IOException,
      IllegalStateException {

    super.figureBodyBegin(h);

    if (h.getOwner() instanceof Figure) {
      this.__feed();
      this.__changeDepth(1);
      this.m_out.write(XHTMLDocument.FIGURE_TR_BODY_BEGIN, 0,
          XHTMLDocument.FIGURE_TR_BODY_BEGIN.length);
      this.__feed();
      this.__changeDepth(1);
      this.m_out.write(XHTMLDocument.FIGURE_TD_BODY_BEGIN, 0,
          XHTMLDocument.FIGURE_TD_BODY_BEGIN.length);
    }
  }

  /** {@inheritDoc} */
  @SuppressWarnings("resource")
  @Override
  protected void figureBodyEnd(final FigureBody h) throws IOException,
      IllegalStateException {
    String s;
    char[] ch;
    final Element o;
    boolean figo;

    try {
      this.__feed();
      this.m_out.write(XHTMLDocument.FIGURE_IMG_URI, 0,
          XHTMLDocument.FIGURE_IMG_URI.length);
      s = h.getRelativeName().toString();
      this.m_out.write(s, 0, s.length());

      this.m_out.write(XHTMLDocument.FIGURE_IMG_WIDTH, 0,
          XHTMLDocument.FIGURE_IMG_WIDTH.length);
      s = XHTMLDocument.__makeString(h.getSizeInMM().getWidth());
      this.m_out.write(s, 0, s.length());

      this.m_out.write(XHTMLDocument.FIGURE_IMG_HEIGHT, 0,
          XHTMLDocument.FIGURE_IMG_HEIGHT.length);
      s = XHTMLDocument.__makeString(h.getSizeInMM().getHeight());
      this.m_out.write(s, 0, s.length());

      this.m_out.write(XHTMLDocument.FIGURE_IMG_ALT, 0,
          XHTMLDocument.FIGURE_IMG_ALT.length);

      ch = null;
      o = h.getOwner();
      figo = false;
      if (o instanceof Figure) {
        figo = true;
        ch = this.m_floatCaption;
      } else {
        if (o instanceof SubFigure) {
          ch = this.m_subfigCaptions[((SubFigure) o).getCol() - 1];
        }
      }

      if (ch != null) {
        XMLCharTransformer.INSTANCE.transform(new CharArrayCharInput(ch),
            this.m_out, 0, ch.length);
      }

      this.m_out.write(XHTMLDocument.EMPTY_ATTRIB_TAG_END, 0,
          XHTMLDocument.EMPTY_ATTRIB_TAG_END.length);

      if (figo) {
        this.__changeDepth(-1);
        this.__feed();
        this.m_out.write(XHTMLDocument.TD_END, 0,
            XHTMLDocument.TD_END.length);
        this.__changeDepth(-1);
        this.__feed();
        this.m_out.write(XHTMLDocument.TR_END, 0,
            XHTMLDocument.TR_END.length);
      }
    } finally {
      super.figureBodyEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected FigureSeries figureSeriesCreate(final SectionBody owner,
      final Label label, final int cols, final int rows,
      final boolean colspan) throws IOException {
    return super.figureSeriesCreate(owner, label, cols, Integer.MAX_VALUE,
        colspan);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesBegin(final FigureSeries h)
      throws IOException, IllegalStateException {
    final int i;

    super.figureSeriesBegin(h);

    i = h.getColumnsPerRow();
    this.m_subfigCaptions = new char[i][];
    this.m_subfigLabels = new SingleLabel[i];
    this.m_subfigCount = 0;
    this.m_floatCaption = null;
  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesEnd(final FigureSeries h) throws IOException,
      IllegalStateException {
    this.m_subfigCaptions = null;
    this.m_subfigLabels = null;
    this.m_floatCaption = null;
    this.m_subfigCount = 0;

    super.figureSeriesEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesPageBegin(final FigureSeriesPage h)
      throws IOException, IllegalStateException {
    super.figureSeriesPageBegin(h);

    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.FIGURE_DIV_BEGIN, 0,
        XHTMLDocument.FIGURE_DIV_BEGIN.length);
    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.FIGURE_TABLE_BEGIN, 0,
        XHTMLDocument.FIGURE_TABLE_BEGIN.length);

    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.FIGURE_TR_BODY_BEGIN, 0,
        XHTMLDocument.FIGURE_TR_BODY_BEGIN.length);
    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.FIGURE_TD_BODY_BEGIN, 0,
        XHTMLDocument.FIGURE_TD_BODY_BEGIN.length);

    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.SUBFIGURE_TABLE_BEGIN, 0,
        XHTMLDocument.SUBFIGURE_TABLE_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesPageEnd(final FigureSeriesPage h)
      throws IOException, IllegalStateException {
    final SingleLabel l;
    String s, t;

    try {
      this.__endFigureRow(h);

      // close sub figure table
      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.TABLE_END, 0,
          XHTMLDocument.TABLE_END.length);

      // close figure body
      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.TD_END, 0,
          XHTMLDocument.TD_END.length);
      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.TR_END, 0,
          XHTMLDocument.TR_END.length);

      this.__feed();
      this.__changeDepth(1);
      this.m_out.write(XHTMLDocument.FIGURE_TR_CAPTION_BEGIN, 0,
          XHTMLDocument.FIGURE_TR_CAPTION_BEGIN.length);

      this.__feed();
      this.__changeDepth(1);
      this.m_out.write(XHTMLDocument.FIGURE_TD_CAPTION_SPAN_BEGIN, 0,
          XHTMLDocument.FIGURE_TD_CAPTION_SPAN_BEGIN.length);
      s = ((t = String.valueOf(h.getFigureIndex())) + XHTMLDocument.DOT);
      this.m_out.write(s, 0, s.length());
      this.m_out.write(XHTMLDocument.SPAN_END_NBSP, 0,
          XHTMLDocument.SPAN_END_NBSP.length);

      l = h.getLabel();
      if (l != null) {
        this.writeSingleLabel(l, t);
      }
      this.m_out.write(this.m_floatCaption, 0, this.m_floatCaption.length);
      this.__changeDepth(-1);
      this.m_out.write(XHTMLDocument.TD_END, 0,
          XHTMLDocument.TD_END.length);

      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.TR_END, 0,
          XHTMLDocument.TR_END.length);

      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.TABLE_END, 0,
          XHTMLDocument.TABLE_END.length);
      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.DIV_END, 0,
          XHTMLDocument.DIV_END.length);
    } finally {
      super.figureSeriesPageEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesCaptionBegin(final FigureSeriesCaption h)
      throws IOException, IllegalStateException {
    super.figureSeriesCaptionBegin(h);
    this.__pushCharArrayWriter();
  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesCaptionEnd(final FigureSeriesCaption h)
      throws IOException, IllegalStateException {
    try {
      this.m_floatCaption = this.__popCharArrayWriter();
    } finally {
      super.figureSeriesCaptionEnd(h);
    }
  }

  /**
   * end the figure row
   * 
   * @param h
   *          the page
   * @throws IOException
   *           if i/o fails
   */
  private final void __endFigureRow(final FigureSeriesPage h)
      throws IOException {
    int i;
    char[] ch;
    final String t;
    String s, v;

    for (final char x[] : this.m_subfigCaptions) {
      if (x == null) {
        this.__feed();
        this.m_out.write(XHTMLDocument.SUBFIGURE_TD_BODY_EMPTY, 0,
            XHTMLDocument.SUBFIGURE_TD_BODY_EMPTY.length);
      }
    }

    this.__changeDepth(-1);
    this.__feed();
    this.m_out.write(XHTMLDocument.TR_END, 0, XHTMLDocument.TR_END.length);

    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.SUBFIGURE_TR_CAPTION_BEGIN, 0,
        XHTMLDocument.SUBFIGURE_TR_CAPTION_BEGIN.length);

    t = h.getFigureIndex() + XHTMLDocument.DOT;

    for (i = 0; i < this.m_subfigCaptions.length; i++) {
      this.__feed();

      ch = this.m_subfigCaptions[i];
      if (ch != null) {
        this.m_out.write(XHTMLDocument.SUBFIGURE_TD_CAPTION_SPAN_BEGIN, 0,
            XHTMLDocument.SUBFIGURE_TD_CAPTION_SPAN_BEGIN.length);

        s = ((v = (t + (++this.m_subfigCount))) + XHTMLDocument.DOT);
        this.m_out.write(s, 0, s.length());
        this.m_out.write(XHTMLDocument.SPAN_END_NBSP, 0,
            XHTMLDocument.SPAN_END_NBSP.length);

        if (this.m_subfigLabels[i] != null) {
          this.writeSingleLabel(this.m_subfigLabels[i], v);
          this.m_subfigLabels[i] = null;
        }
        this.m_out.write(ch, 0, ch.length);
        this.m_out.write(XHTMLDocument.TD_END, 0,
            XHTMLDocument.TD_END.length);
      } else {

        this.m_out.write(XHTMLDocument.SUBFIGURE_TD_CAPTION_EMPTY, 0,
            XHTMLDocument.SUBFIGURE_TD_CAPTION_EMPTY.length);
      }
      this.m_subfigCaptions[i] = null;
    }

    this.__changeDepth(-1);
    this.__feed();
    this.m_out.write(XHTMLDocument.TR_END, 0, XHTMLDocument.TR_END.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void subFigureBegin(final SubFigure h) throws IOException,
      IllegalStateException {
    super.subFigureBegin(h);

    if (h.getCol() <= 1) {
      if (h.getRow() > 1) {
        this.__endFigureRow(h.getOwner());
      }

      this.__feed();
      this.__changeDepth(1);
      this.m_out.write(XHTMLDocument.SUBFIGURE_TR_BODY_BEGIN, 0,
          XHTMLDocument.SUBFIGURE_TR_BODY_BEGIN.length);
    }

    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.SUBFIGURE_TD_BODY_BEGIN, 0,
        XHTMLDocument.SUBFIGURE_TD_BODY_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void subFigureEnd(final SubFigure h) throws IOException,
      IllegalStateException {
    try {
      this.__feed();
      this.__changeDepth(-1);
      this.m_out.write(XHTMLDocument.TD_END, 0,
          XHTMLDocument.TD_END.length);

      this.m_subfigLabels[h.getCol() - 1] = h.getLabel();
    } finally {
      super.subFigureEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void subFigureCaptionBegin(final SubFigureCaption h)
      throws IOException, IllegalStateException {
    super.subFigureCaptionBegin(h);
    this.__pushCharArrayWriter();
  }

  /** {@inheritDoc} */
  @Override
  protected void subFigureCaptionEnd(final SubFigureCaption h)
      throws IOException, IllegalStateException {
    try {
      this.m_subfigCaptions[h.getOwner().getCol() - 1] = //
      this.__popCharArrayWriter();
    } finally {
      super.subFigureCaptionEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void itemizationBegin(final Itemization h) throws IOException,
      IllegalStateException {
    super.itemizationBegin(h);
    this.m_out.write(XHTMLDocument.UL_BEGIN, 0,
        XHTMLDocument.UL_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void itemizationEnd(final Itemization h) throws IOException,
      IllegalStateException {
    try {
      this.m_out.write(XHTMLDocument.UL_END, 0,
          XHTMLDocument.UL_END.length);
    } finally {
      super.itemizationEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void itemizationItemBegin(final ItemizationItem h)
      throws IOException, IllegalStateException {
    super.itemizationItemBegin(h);
    this.m_out.write(XHTMLDocument.IL_BEGIN, 0,
        XHTMLDocument.IL_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void itemizationItemEnd(final ItemizationItem h)
      throws IOException, IllegalStateException {
    try {
      this.m_out.write(XHTMLDocument.IL_END, 0,
          XHTMLDocument.IL_END.length);
    } finally {
      super.itemizationItemEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void enumerationBegin(final Enumeration h) throws IOException,
      IllegalStateException {
    super.enumerationBegin(h);
    this.m_out.write(XHTMLDocument.OL_BEGIN, 0,
        XHTMLDocument.OL_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void enumerationEnd(final Enumeration h) throws IOException,
      IllegalStateException {
    try {
      this.m_out.write(XHTMLDocument.OL_END, 0,
          XHTMLDocument.OL_END.length);
    } finally {
      super.enumerationEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void enumerationItemBegin(final EnumerationItem h)
      throws IOException, IllegalStateException {
    super.enumerationItemBegin(h);
    this.m_out.write(XHTMLDocument.IL_BEGIN, 0,
        XHTMLDocument.IL_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void enumerationItemEnd(final EnumerationItem h)
      throws IOException, IllegalStateException {
    try {
      this.m_out.write(XHTMLDocument.IL_END, 0,
          XHTMLDocument.IL_END.length);
    } finally {
      super.enumerationItemEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected Table tableCreate(final SectionBody owner, final Label label,
      final int rowsPerPage, final boolean colspan,
      final TableCellDef[] def) throws IOException {
    return super
        .tableCreate(owner, label, Integer.MAX_VALUE, colspan, def);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBegin(final Table h) throws IOException,
      IllegalStateException {
    super.tableBegin(h);
    this.m_floatCaption = null;
    this.m_tableHead = null;
    this.m_tableFoot = null;
    this.m_tblHR = false;
  }

  /** {@inheritDoc} */
  @Override
  protected void tableEnd(final Table h) throws IOException,
      IllegalStateException {
    this.m_floatCaption = null;
    this.m_tableHead = null;
    this.m_tableFoot = null;
    this.m_tblHR = false;
    super.tableEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tablePageBegin(final TablePage h) throws IOException,
      IllegalStateException {
    final String s, t;
    final SingleLabel l;

    super.tablePageBegin(h);

    this.m_tblHasBody = false;

    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.TABLE_DIV_BEGIN, 0,
        XHTMLDocument.TABLE_DIV_BEGIN.length);

    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.TABLE_TABLE_BEGIN, 0,
        XHTMLDocument.TABLE_TABLE_BEGIN.length);

    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.TABLE_TR_CAPTION_BEGIN, 0,
        XHTMLDocument.TABLE_TR_CAPTION_BEGIN.length);

    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.TABLE_TD_CAPTION_SPAN_BEGIN, 0,
        XHTMLDocument.TABLE_TD_CAPTION_SPAN_BEGIN.length);

    s = ((t = String.valueOf(h.getTableIndex())) + XHTMLDocument.DOT);
    this.m_out.write(s, 0, s.length());
    this.m_out.write(XHTMLDocument.SPAN_END_NBSP, 0,
        XHTMLDocument.SPAN_END_NBSP.length);

    l = h.getLabel();
    if (l != null) {
      this.writeSingleLabel(l, t);
    }

    if (this.m_floatCaption != null) {
      this.m_out.write(this.m_floatCaption, 0, this.m_floatCaption.length);
    }

    this.__changeDepth(-1);
    this.__feed();
    this.m_out.write(XHTMLDocument.TD_END, 0, XHTMLDocument.TD_END.length);

    this.__changeDepth(-1);
    this.__feed();
    this.m_out.write(XHTMLDocument.TR_END, 0, XHTMLDocument.TR_END.length);

    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.TABLE_TR_BODY_BEGIN, 0,
        XHTMLDocument.TABLE_TR_BODY_BEGIN.length);

    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.TABLE_TD_BODY_BEGIN, 0,
        XHTMLDocument.TABLE_TD_BODY_BEGIN.length);

    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.TAB_TABLE_BEGIN, 0,
        XHTMLDocument.TAB_TABLE_BEGIN.length);

    if (this.m_tableHead != null) {
      this.m_out.write(this.m_tableHead, 0, this.m_tableHead.length);
    } else {
      this.m_out.write(XHTMLDocument.TAB_HEAD_BEGIN, 0,
          XHTMLDocument.TAB_HEAD_BEGIN.length);
      this.m_out.write(XHTMLDocument.TAB_HEAD_END, 0,
          XHTMLDocument.TAB_HEAD_END.length);
    }
    if (this.m_tableFoot != null) {
      this.m_out.write(this.m_tableFoot, 0, this.m_tableFoot.length);
    } else {
      this.m_out.write(XHTMLDocument.TAB_FOOT_BEGIN, 0,
          XHTMLDocument.TAB_FOOT_BEGIN.length);
      this.m_out.write(XHTMLDocument.TAB_FOOT_END, 0,
          XHTMLDocument.TAB_FOOT_END.length);
    }

    this.m_tblHR = false;
  }

  /** {@inheritDoc} */
  @Override
  protected void tablePageEnd(final TablePage h) throws IOException,
      IllegalStateException {
    try {

      if (this.m_tblHasBody) {
        this.__changeDepth(-1);
        this.__feed();
        this.m_out.write(XHTMLDocument.TAB_BODY_END, 0,
            XHTMLDocument.TAB_BODY_END.length);
        this.m_tblHasBody = false;
      }

      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.TABLE_END, 0,
          XHTMLDocument.TABLE_END.length);

      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.TD_END, 0,
          XHTMLDocument.TD_END.length);

      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.TR_END, 0,
          XHTMLDocument.TR_END.length);

      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.TABLE_END, 0,
          XHTMLDocument.TABLE_END.length);

      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.DIV_END, 0,
          XHTMLDocument.DIV_END.length);

    } finally {
      super.tablePageEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void tableCaptionBegin(final TableCaption h)
      throws IOException, IllegalStateException {
    super.tableCaptionBegin(h);
    this.__pushCharArrayWriter();
  }

  /** {@inheritDoc} */
  @Override
  protected void tableCaptionEnd(final TableCaption h) throws IOException,
      IllegalStateException {
    try {
      this.m_floatCaption = this.__popCharArrayWriter();
    } finally {
      super.tableCaptionEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderBegin(final TableHeader h) throws IOException,
      IllegalStateException {
    super.tableHeaderBegin(h);

    this.__pushCharArrayWriter();
    this.m_chain.m_tagDepth = (this.m_chain.m_next.m_tagDepth + 5);
    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.TAB_HEAD_BEGIN, 0,
        XHTMLDocument.TAB_HEAD_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderEnd(final TableHeader h) throws IOException,
      IllegalStateException {
    try {
      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.TAB_HEAD_END, 0,
          XHTMLDocument.TAB_HEAD_END.length);
      this.m_tableHead = this.__popCharArrayWriter();
    } finally {
      super.tableHeaderEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterBegin(final TableFooter h) throws IOException,
      IllegalStateException {

    super.tableFooterBegin(h);

    this.__pushCharArrayWriter();
    this.m_chain.m_tagDepth = (this.m_chain.m_next.m_tagDepth + 5);
    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.TAB_FOOT_BEGIN, 0,
        XHTMLDocument.TAB_FOOT_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterEnd(final TableFooter h) throws IOException,
      IllegalStateException {
    try {
      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.TAB_FOOT_END, 0,
          XHTMLDocument.TAB_FOOT_END.length);
      this.m_tableFoot = this.__popCharArrayWriter();
    } finally {
      super.tableFooterEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderRowBegin(final TableHeaderRow h)
      throws IOException, IllegalStateException {

    super.tableHeaderRowBegin(h);

    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.TAB_TR_H_BEGIN, 0,
        XHTMLDocument.TAB_TR_H_BEGIN.length);
    this.__feed();
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderRowEnd(final TableHeaderRow h)
      throws IOException, IllegalStateException {

    try {
      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.TR_END, 0,
          XHTMLDocument.TR_END.length);
    } finally {
      super.tableHeaderRowEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterRowBegin(final TableFooterRow h)
      throws IOException, IllegalStateException {

    super.tableFooterRowBegin(h);

    this.__feed();
    this.__changeDepth(1);
    this.m_out.write(XHTMLDocument.TAB_TR_F_BEGIN, 0,
        XHTMLDocument.TAB_TR_F_BEGIN.length);
    this.__feed();
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterRowEnd(final TableFooterRow h)
      throws IOException, IllegalStateException {

    try {
      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.TR_END, 0,
          XHTMLDocument.TR_END.length);
    } finally {
      super.tableFooterRowEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyRowBegin(final TableBodyRow h)
      throws IOException, IllegalStateException {
    final char[] ch;

    super.tableBodyRowBegin(h);

    if (!(this.m_tblHasBody)) {
      this.__feed();
      this.__changeDepth(1);
      this.m_out.write(XHTMLDocument.TAB_BODY_BEGIN, 0,
          XHTMLDocument.TAB_BODY_BEGIN.length);
      this.m_tblHasBody = true;
    }

    this.__feed();
    this.__changeDepth(1);

    ch = (((h.getRowIndex() & 1) == 0)//
    ? (this.m_tblHR ? XHTMLDocument.TAB_TR_BEHR_BEGIN
        : XHTMLDocument.TAB_TR_BE_BEGIN)//
        : (this.m_tblHR ? XHTMLDocument.TAB_TR_BOHR_BEGIN
            : XHTMLDocument.TAB_TR_BO_BEGIN));
    this.m_out.write(ch, 0, ch.length);
    this.m_tblHR = false;
    this.__feed();
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHorizontalLine() throws IOException,
      IllegalStateException {
    super.tableHorizontalLine();
    this.m_tblHR = true;
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyRowEnd(final TableBodyRow h) throws IOException,
      IllegalStateException {

    try {
      this.m_tblHR = false;
      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.TR_END, 0,
          XHTMLDocument.TR_END.length);
    } finally {
      super.tableBodyRowEnd(h);
    }
  }

  /**
   * get the cell mode
   * 
   * @param cell
   *          the cell
   * @throws IOException
   *           if i/o fails
   */
  @SuppressWarnings("incomplete-switch")
  private final void __cellMode(final AbstractTableCell cell)
      throws IOException {
    int i;
    char[] ch;
    String s;

    ch = XHTMLDocument.GREATER;
    looper: for (i = cell.getDefinitionLength(); (--i) >= 0;) {
      switch (cell.getDefinition(i).getType()) {
        case TableCellDef.ALIGN_LEFT: {
          ch = XHTMLDocument.TAB_CLASS_L;
          break looper;
        }
        case TableCellDef.ALIGN_RIGHT: {
          ch = XHTMLDocument.TAB_CLASS_R;
          break looper;
        }
        case TableCellDef.ALIGN_CENTER: {
          ch = XHTMLDocument.TAB_CLASS_C;
          break looper;
        }
      }
    }

    i = cell.getColumnSpan();
    if (i > 1) {
      s = (" colspan=\"" + i + '"'); //$NON-NLS-1$
      this.m_out.write(s, 0, s.length());
    }

    i = cell.getRowSpan();
    if (i > 1) {
      s = (" rowspan=\"" + i + '"'); //$NON-NLS-1$
      this.m_out.write(s, 0, s.length());
    }

    this.m_out.write(ch, 0, ch.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderCellBegin(final TableHeaderCell h)
      throws IOException, IllegalStateException {

    super.tableHeaderCellBegin(h);

    this.m_out.write(XHTMLDocument.TAB_TH_BEGIN, 0,
        XHTMLDocument.TAB_TH_BEGIN.length);
    this.__cellMode(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderCellEnd(final TableHeaderCell h)
      throws IOException, IllegalStateException {
    try {
      this.m_out.write(XHTMLDocument.TAB_TH_END, 0,
          XHTMLDocument.TAB_TH_END.length);
    } finally {
      super.tableHeaderCellEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterCellBegin(final TableFooterCell h)
      throws IOException, IllegalStateException {
    super.tableFooterCellBegin(h);
    this.m_out.write(XHTMLDocument.TAB_TD_BEGIN, 0,
        XHTMLDocument.TAB_TD_BEGIN.length);
    this.__cellMode(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterCellEnd(final TableFooterCell h)
      throws IOException, IllegalStateException {
    try {
      this.m_out.write(XHTMLDocument.TD_END, 0,
          XHTMLDocument.TD_END.length);
    } finally {
      super.tableFooterCellEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyCellBegin(final TableBodyCell h)
      throws IOException, IllegalStateException {
    super.tableBodyCellBegin(h);
    this.m_out.write(XHTMLDocument.TAB_TD_BEGIN, 0,
        XHTMLDocument.TAB_TD_BEGIN.length);
    this.__cellMode(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyCellEnd(final TableBodyCell h)
      throws IOException, IllegalStateException {
    try {

      this.m_out.write(XHTMLDocument.TD_END, 0,
          XHTMLDocument.TD_END.length);
    } finally {
      super.tableBodyCellEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void subscriptBegin(final Subscript h) throws IOException,
      IllegalStateException {
    super.subscriptBegin(h);
    this.m_out.write(XHTMLDocument.SUB_BEGIN, 0,
        XHTMLDocument.SUB_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void subscriptEnd(final Subscript h) throws IOException,
      IllegalStateException {
    try {
      this.m_out.write(XHTMLDocument.SUB_END, 0,
          XHTMLDocument.SUB_END.length);
    } finally {
      super.subscriptEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void superscriptBegin(final Superscript h) throws IOException,
      IllegalStateException {
    super.superscriptBegin(h);
    this.m_out.write(XHTMLDocument.SUP_BEGIN, 0,
        XHTMLDocument.SUP_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void superscriptEnd(final Superscript h) throws IOException,
      IllegalStateException {
    try {
      this.m_out.write(XHTMLDocument.SUP_END, 0,
          XHTMLDocument.SUP_END.length);
    } finally {
      super.superscriptEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void emphasizeBegin(final Emphasize h) throws IOException,
      IllegalStateException {
    super.emphasizeBegin(h);
    this.m_out.write(XHTMLDocument.EM_BEGIN, 0,
        XHTMLDocument.EM_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void emphasizeEnd(final Emphasize h) throws IOException,
      IllegalStateException {
    try {
      this.m_out.write(XHTMLDocument.EM_END, 0,
          XHTMLDocument.EM_END.length);
    } finally {
      super.emphasizeEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void equationBegin(final Equation h) throws IOException,
      IllegalStateException {
    super.equationBegin(h);

    this.__feed();
    this.m_chain.m_tagDepth++;
    this.m_out.write(XHTMLDocument.EQU_DIV, 0,
        XHTMLDocument.EQU_DIV.length);

    this.__feed();
    this.m_chain.m_tagDepth++;
    this.m_out.write(XHTMLDocument.EQU_TAB, 0,
        XHTMLDocument.EQU_TAB.length);

    this.__feed();
    this.m_chain.m_tagDepth++;
    this.m_out.write(XHTMLDocument.EQU_TR, 0, XHTMLDocument.EQU_TR.length);

    this.__feed();
    this.m_chain.m_tagDepth++;
    this.m_out.write(XHTMLDocument.EQU_BODY_TD, 0,
        XHTMLDocument.EQU_BODY_TD.length);

    this.__feed();
    this.m_out.write(XHTMLDocument.MATH_DIV_BEGIN, 0,
        XHTMLDocument.MATH_DIV_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void equationEnd(final Equation h) throws IOException,
      IllegalStateException {
    String s;
    SingleLabel sl;

    try {
      this.m_out.write(XHTMLDocument.DIV_END, 0,
          XHTMLDocument.DIV_END.length);

      this.m_chain.m_tagDepth--;
      this.__feed();
      this.m_out.write(XHTMLDocument.TD_END, 0,
          XHTMLDocument.TD_END.length);

      this.m_out.write(XHTMLDocument.EQU_NR_TD, 0,
          XHTMLDocument.EQU_NR_TD.length);
      s = String.valueOf(h.getEquationIndex());
      sl = h.getLabel();
      if (sl != null) {
        this.writeSingleLabel(sl, s);
      }
      s = ("(Eq.&nbsp;" + s + ')'); //$NON-NLS-1$
      this.m_out.write(s, 0, s.length());
      this.m_out.write(XHTMLDocument.TD_END, 0,
          XHTMLDocument.TD_END.length);

      this.m_chain.m_tagDepth--;
      this.__feed();
      this.m_out.write(XHTMLDocument.TR_END, 0,
          XHTMLDocument.TR_END.length);

      this.m_chain.m_tagDepth--;
      this.__feed();
      this.m_out.write(XHTMLDocument.TABLE_END, 0,
          XHTMLDocument.TABLE_END.length);

      this.m_chain.m_tagDepth--;
      this.__feed();
      this.m_out.write(XHTMLDocument.DIV_END, 0,
          XHTMLDocument.DIV_END.length);

      this.__feed();
    } finally {
      super.equationEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void inlineMathBegin(final InlineMath h) throws IOException,
      IllegalStateException {
    super.inlineMathBegin(h);
    this.m_out.write(XHTMLDocument.MATH_DIV_BEGIN, 0,
        XHTMLDocument.MATH_DIV_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void inlineMathEnd(final InlineMath h) throws IOException,
      IllegalStateException {
    try {
      this.m_out.write(XHTMLDocument.DIV_END, 0,
          XHTMLDocument.DIV_END.length);
    } finally {
      super.inlineMathEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void normalTextBegin(final NormalText h) throws IOException,
      IllegalStateException {
    super.normalTextBegin(h);
    this.m_out.write(XHTMLDocument.NORMAL_SPAN_BEGIN, 0,
        XHTMLDocument.NORMAL_SPAN_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void normalTextEnd(final NormalText h) throws IOException,
      IllegalStateException {
    try {
      this.m_out.write(XHTMLDocument.SPAN_END, 0,
          XHTMLDocument.SPAN_END.length);
    } finally {
      super.normalTextEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void mathSubscriptBegin(final MathSubscript h)
      throws IOException, IllegalStateException {
    super.mathSubscriptBegin(h);
    this.m_out.write(XHTMLDocument.SUB_MATH_BEGIN, 0,
        XHTMLDocument.SUB_MATH_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void mathSubscriptEnd(final MathSubscript h)
      throws IOException, IllegalStateException {
    try {
      this.m_out.write(XHTMLDocument.SUB_END, 0,
          XHTMLDocument.SUB_END.length);
    } finally {
      super.mathSubscriptEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void mathSuperscriptBegin(final MathSuperscript h)
      throws IOException, IllegalStateException {
    super.mathSuperscriptBegin(h);
    this.m_out.write(XHTMLDocument.SUP_MATH_BEGIN, 0,
        XHTMLDocument.SUP_MATH_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void mathSuperscriptEnd(final MathSuperscript h)
      throws IOException, IllegalStateException {
    try {
      this.m_out.write(XHTMLDocument.SUP_END, 0,
          XHTMLDocument.SUP_END.length);
    } finally {
      super.mathSuperscriptEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void mathNameBegin(final MathName h) throws IOException,
      IllegalStateException {
    final char[] ch;

    super.mathNameBegin(h);

    ch = XHTMLDocument.NAME_SPAN_BEGIN[h.getType().ordinal()];
    this.m_out.write(ch, 0, ch.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void mathNameEnd(final MathName h) throws IOException,
      IllegalStateException {

    try {
      this.m_out.write(XHTMLDocument.SPAN_END, 0,
          XHTMLDocument.SPAN_END.length);
    } finally {
      super.mathNameEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void mathOpEnd(final MathOp h) throws IOException,
      IllegalStateException {
    final char[][] ch;
    final _OpDef def;

    try {
      ch = this.m_chain.take();

      def = _OpDef.MAP.get(h.getOperator());
      if (def != null) {
        def.render(this.m_out, ch);
      }
    } finally {
      super.mathOpEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void mathOpParamBegin(final MathOpParam h) throws IOException,
      IllegalStateException {

    super.mathOpParamBegin(h);
    this.__pushCharArrayWriter();
  }

  /** {@inheritDoc} */
  @Override
  protected void mathOpParamEnd(final MathOpParam h) throws IOException,
      IllegalStateException {
    final char[] ch;
    try {
      ch = this.__popCharArrayWriter();
      this.m_chain.addData(ch);
    } finally {
      super.mathOpParamEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void macroBegin(final Macro h) throws IOException,
      IllegalStateException {
    super.macroBegin(h);
    this.__pushCharArrayWriter();
  }

  /** {@inheritDoc} */
  @Override
  protected void macroEnd(final Macro h) throws IOException,
      IllegalStateException {
    try {
      this.m_macros.put(h.getDescriptor(), this.__popCharArrayWriter());
    } finally {
      super.macroEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void macroParameter(final int id) throws IOException {
    super.macroParameter(id);
    this.m_out.write(id - 1);
  }

  /** {@inheritDoc} */
  @Override
  protected void macroParameterBegin(final MacroParameter h)
      throws IOException, IllegalStateException {
    super.macroParameterBegin(h);
    this.__pushCharArrayWriter();
  }

  /** {@inheritDoc} */
  @Override
  protected void macroParameterEnd(final MacroParameter h)
      throws IOException, IllegalStateException {
    final char[] ch;
    try {
      ch = this.__popCharArrayWriter();
      this.m_chain.addData(ch);
    } finally {
      super.macroParameterEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void macroInvocationEnd(final MacroInvocation h)
      throws IOException, IllegalStateException {
    final char[] macro;
    final char[][] params;
    char[] ch;
    int j, i;

    try {
      macro = this.m_macros.get(h.getDescriptor());
      params = this.m_chain.take();

      if (macro != null) {

        j = 0;
        loopy: for (;;) {
          inner: for (i = j; i < macro.length; i++) {
            if (macro[i] < MacroDescriptor.MAX_PARAMETERS) {
              break inner;
            }
          }

          this.m_out.write(macro, j, i);

          if (i >= macro.length) {
            break loopy;
          }

          ch = params[macro[i]];
          if (ch != null) {
            this.m_out.write(ch, 0, ch.length);
          }

          j = (i + 1);
        }
      }
    } finally {
      super.macroInvocationEnd(h);
    }
  }

  /**
   * Make the bibliography style, file, and references
   * 
   * @throws IOException
   *           if io fails
   */
  protected void makeBibliography() throws IOException {
    final BibReference[] brs;
    String s;

    brs = this.getReferences();
    if ((brs != null) && (brs.length > 0)) {

      this.__feed();
      this.__changeDepth(1);
      this.m_out.write(XHTMLDocument.SECTION_DIV_BEGIN, 0,
          XHTMLDocument.SECTION_DIV_BEGIN.length);

      this.__feed();
      this.__changeDepth(1);
      this.m_out.write(XHTMLDocument.SECTION_HEAD_DIV_BEGIN, 0,
          XHTMLDocument.SECTION_HEAD_DIV_BEGIN.length);

      this.__feed();
      this.m_out.write(XHTMLDocument.HEADLINE_BEGIN[1], 0,
          XHTMLDocument.HEADLINE_BEGIN[1].length);
      s = "References"; //$NON-NLS-1$
      this.m_out.write(s, 0, s.length());
      this.m_out.write(XHTMLDocument.HEADLINE_END[1], 0,
          XHTMLDocument.HEADLINE_END[1].length);

      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.DIV_END, 0,
          XHTMLDocument.DIV_END.length);

      this.__feed();
      this.__changeDepth(1);
      this.m_out.write(XHTMLDocument.SECTION_BODY_DIV_BEGIN, 0,
          XHTMLDocument.SECTION_BODY_DIV_BEGIN.length);

      this.__feed();
      this.__changeDepth(1);
      this.m_out.write(XHTMLDocument.REF_TAB, 0,
          XHTMLDocument.REF_TAB.length);

      for (final BibReference ref : brs) {
        this.__feed();
        this.__changeDepth(1);
        this.m_out.write(XHTMLDocument.REF_TR, 0,
            XHTMLDocument.REF_TR.length);

        this.__feed();
        this.m_out.write(XHTMLDocument.REF_ID_TD, 0,
            XHTMLDocument.REF_ID_TD.length);
        this.m_out.write(XHTMLDocument.A_REF_ID, 0,
            XHTMLDocument.A_REF_ID.length);
        s = ref.getKey();
        this.m_out.write(s, 0, s.length());
        this.m_out.write(XHTMLDocument.LABEL_END, 0, 2);
        s = (ref.getIndex() + XHTMLDocument.DOT);
        this.m_out.write(s, 0, s.length());
        this.m_out.write(XHTMLDocument.LABEL_END, 2,
            XHTMLDocument.LABEL_END.length);
        this.m_out.write(XHTMLDocument.TD_END, 0,
            XHTMLDocument.TD_END.length);

        this.__feed();
        this.m_out.write(XHTMLDocument.REF_TXT_TD, 0,
            XHTMLDocument.REF_TXT_TD.length);
        _Bibliography._writeRecord(ref.getRecord(), this);
        this.m_out.write(XHTMLDocument.TD_END, 0,
            XHTMLDocument.TD_END.length);

        this.__changeDepth(-1);
        this.__feed();
        this.m_out.write(XHTMLDocument.TR_END, 0,
            XHTMLDocument.TR_END.length);
      }

      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.TABLE_END, 0,
          XHTMLDocument.TABLE_END.length);

      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.DIV_END, 0,
          XHTMLDocument.DIV_END.length);

      this.__changeDepth(-1);
      this.__feed();
      this.m_out.write(XHTMLDocument.DIV_END, 0,
          XHTMLDocument.DIV_END.length);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void cite(final ECitationMode mode,
      final BibReference... references) throws IOException {
    final boolean auth;

    if ((references != null) && (references.length > 0)) {

      if (!(mode.isAtStart())) {
        this.m_out.write(XHTMLDocument.NBSP, 0, XHTMLDocument.NBSP.length);
      }

      auth = mode.printAuthors();
      this.m_out.write(XHTMLDocument.SPAN_REF, 0,
          XHTMLDocument.SPAN_REF.length);
      if (!auth) {
        this.m_out.write('[');
      }

      this.writeSequence(new _CiteSequence(references, this, auth),
          (auth ? ESequenceType.AND : ESequenceType.COMMA), false);

      if (!auth) {
        this.m_out.write(']');
      }
      this.m_out.write(XHTMLDocument.SPAN_END, 0,
          XHTMLDocument.SPAN_END.length);

    }
  }

  /**
   * print a single reference
   * 
   * @param printAuthors
   *          the author names
   * @param ref
   *          the reference
   * @throws IOException
   *           the i/o exception
   */
  final void _printRef(final boolean printAuthors, final BibReference ref)
      throws IOException {
    BibRecord rec;
    BibAuthors auth;
    String s;

    this.m_out.write(XHTMLDocument.A_REF, 0, XHTMLDocument.A_REF.length);
    s = ref.getKey();
    this.m_out.write(s, 0, s.length());
    this.m_out.write(XHTMLDocument.LABEL_END, 0, 2);

    print: {
      if (printAuthors) {
        rec = ref.getRecord();
        auth = rec.getAuthors();

        if ((auth == null) || (auth.size() <= 0)) {
          if (rec instanceof BibBook) {
            auth = ((BibBook) rec).getEditors();
          }
        }

        if ((auth != null) && (auth.size() > 0)) {
          this.writeSequence(new _AuthorsSequence(auth, false, this),
              ESequenceType.AND, false);
          break print;
        }
      }

      s = String.valueOf(ref.getIndex());
      this.m_out.write(s, 0, s.length());
    }

    this.m_out.write(XHTMLDocument.LABEL_END, 2,
        XHTMLDocument.LABEL_END.length);
  }

  /** {@inheritDoc} */
  @Override
  public DocumentDimensions getDimensions() {
    return XHTMLDocumentDimensions.DEFAULT;
  }

  /**
   * make a string
   * 
   * @param d
   *          the double value
   * @return the string
   */
  private static final String __makeString(final double d) {
    long l;

    l = Math.round(d);
    if (l == d) {
      return String.valueOf(l);
    }
    return String.valueOf(d);
  }
}
