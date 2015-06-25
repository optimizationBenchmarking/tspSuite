package org.logisticPlanning.utils.document.impl.xhtml;

import java.io.IOException;
import java.net.URI;

import org.logisticPlanning.utils.document.spec.ESequenceType;
import org.logisticPlanning.utils.document.spec.bib.BibArticle;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;
import org.logisticPlanning.utils.document.spec.bib.BibBook;
import org.logisticPlanning.utils.document.spec.bib.BibDate;
import org.logisticPlanning.utils.document.spec.bib.BibInCollection;
import org.logisticPlanning.utils.document.spec.bib.BibInProceedings;
import org.logisticPlanning.utils.document.spec.bib.BibProceedings;
import org.logisticPlanning.utils.document.spec.bib.BibRecord;
import org.logisticPlanning.utils.document.spec.bib.BibTechReport;
import org.logisticPlanning.utils.document.spec.bib.BibThesis;
import org.logisticPlanning.utils.document.spec.bib.BibWebsite;
import org.logisticPlanning.utils.document.spec.bib.EBibMonth;
import org.logisticPlanning.utils.document.spec.bib.EBibQuarter;
import org.logisticPlanning.utils.document.spec.bib.EThesisType;
import org.logisticPlanning.utils.text.EQuotes;
import org.logisticPlanning.utils.text.transformations.XMLCharTransformer;

/**
 * The writer for bibliography.
 */
final class _Bibliography {

  /** begin the title */
  private static final char[] BEGIN_TITLE = //
  XMLCharTransformer.INSTANCE.transform(//
      String.valueOf(EQuotes.DEFAULT.getBegin())).toCharArray();
  /** end the title */
  private static final char[] END_TITLE = //
  XMLCharTransformer.INSTANCE.transform(//
      "," + EQuotes.DEFAULT.getEnd()).toCharArray(); //$NON-NLS-1$

  /** begin the title */
  private static final char[] BEFORE_TITLE = { ':', ' ' };

  /** the dash */
  private static final char[] DASH = XMLCharTransformer.INSTANCE
      .transform("\u2012").toCharArray(); //$NON-NLS-1$

  /** the editors */
  private static final char[] EDITORS = { ',', ' ', 'e', 'd', 'i', 't',
      'o', 'r', 's' };

  /** the in */
  private static final char[] IN = { ' ', 'i', 'n', '&', 'n', 'b', 's',
      'p', ';', };

  /** the article */
  private static final char[] SPAN_ARTICLE = { '<', 's', 'p', 'a', 'n',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'a', 'r', 't', 'i', 'c',
      'l', 'e', '"', '>' };
  /** the in collection */
  private static final char[] SPAN_BOOK = { '<', 's', 'p', 'a', 'n', ' ',
      'c', 'l', 'a', 's', 's', '=', '"', 'b', 'o', 'o', 'k', '"', '>' };
  /** the in collection */
  private static final char[] SPAN_IN_COLLECTION = { '<', 's', 'p', 'a',
      'n', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'i', 'n', 'C', 'o',
      'l', 'l', 'e', 'c', 't', 'i', 'o', 'n', '"', '>' };
  /** the in proceedings */
  private static final char[] SPAN_IN_PROCEEDINGS = { '<', 's', 'p', 'a',
      'n', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'i', 'n', 'P', 'r',
      'o', 'c', 'e', 'e', 'd', 'i', 'n', 'g', 's', '"', '>' };
  /** the proceedings */
  private static final char[] SPAN_PROCEEDINGS = { '<', 's', 'p', 'a',
      'n', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'p', 'r', 'o', 'c',
      'e', 'e', 'd', 'i', 'n', 'g', 's', '"', '>' };
  /** the tech report */
  private static final char[] SPAN_TECH_REPORT = { '<', 's', 'p', 'a',
      'n', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 't', 'e', 'c', 'h',
      'R', 'e', 'p', 'o', 'r', 't', '"', '>' };
  /** the thesis */
  private static final char[] SPAN_THESIS = { '<', 's', 'p', 'a', 'n',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 't', 'h', 'e', 's', 'i',
      's', '"', '>' };
  /** the website */
  private static final char[] SPAN_WEBSITE = { '<', 's', 'p', 'a', 'n',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'w', 'e', 'b', 's', 'i',
      't', 'e', '"', '>' };
  /** the authors */
  private static final char[] SPAN_AUTHORS = { '<', 's', 'p', 'a', 'n',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'a', 'u', 't', 'h', 'o',
      'r', 's', '"', '>' };
  /** the author */
  private static final char[] SPAN_AUTHOR = { '<', 's', 'p', 'a', 'n',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'a', 'u', 't', 'h', 'o',
      'r', '"', '>' };
  /** the editors */
  private static final char[] SPAN_EDITORS = { '<', 's', 'p', 'a', 'n',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'e', 'd', 'i', 't', 'o',
      'r', 's', '"', '>' };
  /** the editor */
  private static final char[] SPAN_EDITOR = { '<', 's', 'p', 'a', 'n',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'e', 'd', 'i', 't', 'o',
      'r', '"', '>' };
  /** the title */
  private static final char[] SPAN_TITLE = { '<', 's', 'p', 'a', 'n', ' ',
      'c', 'l', 'a', 's', 's', '=', '"', 't', 'i', 't', 'l', 'e', '"', '>' };
  /** the book title */
  private static final char[] SPAN_BOOK_TITLE = { '<', 's', 'p', 'a', 'n',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'b', 'o', 'o', 'k', 'T',
      'i', 't', 'l', 'e', '"', '>' };
  /** the date */
  private static final char[] SPAN_DATE = { '<', 's', 'p', 'a', 'n', ' ',
      'c', 'l', 'a', 's', 's', '=', '"', 'd', 'a', 't', 'e', '"', '>' };
  /** the date */
  private static final char[] SPAN_JOURNAL = { '<', 's', 'p', 'a', 'n',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'j', 'o', 'u', 'r', 'n',
      'a', 'l', '"', '>' };
  /** the pages */
  private static final char[] SPAN_PAGES = { '<', 's', 'p', 'a', 'n', ' ',
      'c', 'l', 'a', 's', 's', '=', '"', 'p', 'a', 'g', 'e', 's', '"',
      '>', };
  /** the page */
  private static final char[] PAGE = { 'p', 'a', 'g', 'e', '&', 'n', 'b',
      's', 'p', ';' };
  /** the page */
  private static final char[] PAGES = { 'p', 'a', 'g', 'e', 's', '&', 'n',
      'b', 's', 'p', ';' };

  /** the chapter */
  private static final char[] SPAN_CHAPTER = { '<', 's', 'p', 'a', 'n',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'c', 'h', 'a', 'p', 't',
      'e', 'r', '"', '>', 'c', 'h', 'a', 'p', 't', 'e', 'r', '&', 'n',
      'b', 's', 'p', ';' };
  /** the journal volume */
  private static final char[] SPAN_JOURNAL_VOLUME = { '<', 's', 'p', 'a',
      'n', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'j', 'v', 'o', 'l',
      'u', 'm', 'e', '"', '>' };
  /** the issue */
  private static final char[] SPAN_ISSUE = { '<', 's', 'p', 'a', 'n', ' ',
      'c', 'l', 'a', 's', 's', '=', '"', 'i', 's', 's', 'u', 'e', '"', '>' };
  /** the edition */
  private static final char[] SPAN_EDITION = { '<', 's', 'p', 'a', 'n',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'e', 'd', 'i', 't', 'i',
      'o', 'n', '"', '>' };
  /** the series */
  private static final char[] SPAN_SERIES = { '<', 's', 'p', 'a', 'n',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 's', 'e', 'r', 'i', 'e',
      's', '"', '>' };
  /** the series */
  private static final char[] SPAN_RSERIES = { '<', 's', 'p', 'a', 'n',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'r', 's', 'e', 'r', 'i',
      'e', 's', '"', '>' };
  /** the series volume */
  private static final char[] SPAN_SERIES_VOLUME = { '<', 's', 'p', 'a',
      'n', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 's', 'v', 'o', 'l',
      'u', 'm', 'e', '"', '>', 'v', 'o', 'l', 'u', 'm', 'e', '&', 'n',
      'b', 's', 'p', ';' };
  /** the report number */
  private static final char[] SPAN_REPORT_NUMBER = { '<', 's', 'p', 'a',
      'n', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'r', 'n', 'u', 'm',
      'b', 'e', 'r', '"', '>', 'n', 'u', 'm', 'b', 'e', 'r', '&', 'n',
      'b', 's', 'p', ';' };
  /** the publisher */
  private static final char[] SPAN_PUBLISHER = { '<', 's', 'p', 'a', 'n',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'p', 'u', 'b', 'l', 'i',
      's', 'h', 'e', 'r', '"', '>' };
  /** the publisher address */
  private static final char[] SPAN_PUBLISHER_ADDRESS = { '<', 's', 'p',
      'a', 'n', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'p', 'u', 'b',
      'l', 'i', 's', 'h', 'e', 'r', 'A', 'd', 'd', 'r', 'e', 's', 's',
      '"', '>' };
  /** the location */
  private static final char[] SPAN_LOCATION = { '<', 's', 'p', 'a', 'n',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'l', 'o', 'c', 'a', 't',
      'i', 'o', 'n', '"', '>' };
  /** the doi */
  private static final char[] SPAN_DOI = { '<', 's', 'p', 'a', 'n', ' ',
      'c', 'l', 'a', 's', 's', '=', '"', 'd', 'o', 'i', '"', '>', 'd',
      'o', 'i', ':', '<', 'a', ' ', 'h', 'r', 'e', 'f', '=', '"', 'h',
      't', 't', 'p', ':', '/', '/', 'd', 'x', '.', 'd', 'o', 'i', '.',
      'o', 'r', 'g', '/' };
  /** the link */
  private static final char[] SPAN_LINK = { '<', 's', 'p', 'a', 'n', ' ',
      'c', 'l', 'a', 's', 's', '=', '"', 'l', 'i', 'n', 'k', '"', '>',
      '[', '<', 'a', ' ', 'h', 'r', 'e', 'f', '=', '"' };
  /** the thesis type */
  private static final char[] SPAN_THESIS_TYPE = { '<', 's', 'p', 'a',
      'n', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 't', 't', 'y', 'p',
      'e', '"', '>' };

  /** the institute span */
  private static final char[] SPAN_INSTITUTE = { '<', 's', 'p', 'a', 'n',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'i', 'n', 's', 't', 'i',
      't', 'u', 't', 'e', '"', '>' };

  /** the institute address */
  private static final char[] SPAN_INSTITUTE_ADDRESS = { '<', 's', 'p',
      'a', 'n', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 'i', 'n', 's',
      't', 'i', 't', 'u', 't', 'e', 'A', 'd', 'd', 'r', 'e', 's', 's',
      '"', '>' };
  /** the school */
  private static final char[] SPAN_SCHOOL = { '<', 's', 'p', 'a', 'n',
      ' ', 'c', 'l', 'a', 's', 's', '=', '"', 's', 'c', 'h', 'o', 'o',
      'l', '"', '>' };
  /** the school address */
  private static final char[] SPAN_SCHOOL_ADDRESS = { '<', 's', 'p', 'a',
      'n', ' ', 'c', 'l', 'a', 's', 's', '=', '"', 's', 'c', 'h', 'o',
      'o', 'l', 'A', 'd', 'd', 'r', 'e', 's', 's', '"', '>' };

  /**
   * Write a record
   *
   * @param r
   *          the record
   * @param out
   *          the document to write to
   * @throws IOException
   *           if io fails
   */
  static final void _writeRecord(final BibRecord r, final XHTMLDocument out)
      throws IOException {

    if (r instanceof BibArticle) {
      _Bibliography.__writeArticle(((BibArticle) r), out);
      return;
    }
    if (r instanceof BibProceedings) {
      _Bibliography.__writeProceedings(((BibProceedings) r), out);
      return;
    }
    if (r instanceof BibInProceedings) {
      _Bibliography.__writeInProceedings(((BibInProceedings) r), out);
      return;
    }
    if (r instanceof BibInCollection) {
      _Bibliography.__writeInCollection(((BibInCollection) r), out);
      return;
    }
    if (r instanceof BibTechReport) {
      _Bibliography.__writeTechReport(((BibTechReport) r), out);
      return;
    }
    if (r instanceof BibThesis) {
      _Bibliography.__writeThesis(((BibThesis) r), out);
      return;
    }
    if (r instanceof BibBook) {
      _Bibliography.__writeBook(((BibBook) r), out);
      return;
    }
    if (r instanceof BibWebsite) {
      _Bibliography.__writeWebsite(((BibWebsite) r), out);
      return;
    }

    throw new IllegalArgumentException(r.toString());
  }

  /**
   * write a full date
   *
   * @param year
   *          the year
   * @param quarter
   *          the quarter
   * @param month
   *          the month
   * @param day
   *          the eay
   * @param wco
   *          the output
   * @throws IOException
   *           if io fails
   */
  private static final void __fullDate(final int year,
      final EBibQuarter quarter, final EBibMonth month, final int day,
      final XHTMLDocument wco) throws IOException {
    String s;

    if (month != null) {
      s = month.getLongName();
      wco.write(s);
      if (day > 0) {
        wco.writeNoneBreakingSpace();
        s = String.valueOf(day);
        wco.write(s);
      }
      if (year > 0) {
        wco.m_out.write(XHTMLDocument.SEQUENCE_SMALL_SEP, 0,
            XHTMLDocument.SEQUENCE_SMALL_SEP.length);
      }
    } else {
      if (quarter != null) {
        s = quarter.getLongName();
        wco.write(s);
        if (year > 0) {
          wco.writeNoneBreakingSpace();
        }
      }
    }

    if (year > 0) {
      s = String.valueOf(year);
      wco.write(s);
    }
  }

  /**
   * write date
   *
   * @param start
   *          the start
   * @param end
   *          the end
   * @param wco
   *          the output
   * @throws IOException
   *           if io fails
   */
  private static final void __date(final BibDate start, final BibDate end,
      final XHTMLDocument wco) throws IOException {
    final EBibMonth month1, month2;
    final EBibQuarter quarter1, quarter2;
    final int day1, day2, year1, year2;
    BibDate a, b;
    String s;

    a = start;
    if (a == null) {
      a = end;
      if (a == null) {
        return;
      }
      b = null;
    } else {
      if (a.equals(end)) {
        b = null;
      } else {
        b = end;
      }
    }

    wco.m_out.write(_Bibliography.SPAN_DATE, 0,
        _Bibliography.SPAN_DATE.length);

    day1 = a.getDay();
    month1 = a.getMonth();
    quarter1 = a.getQuarter();
    year1 = a.getYear();

    if (b == null) {
      _Bibliography.__fullDate(year1, quarter1, month1, day1, wco);
    } else {

      day2 = b.getDay();
      month2 = b.getMonth();
      quarter2 = b.getQuarter();
      year2 = b.getYear();

      if (year1 == year2) {

        if (month1 != null) {

          if (month1 == month2) {
            s = month1.getLongName();
            wco.write(s);
            if ((day1 > 0) && (day2 > 0)) {
              wco.writeNoneBreakingSpace();
              wco.write(String.valueOf(day1));
              wco.m_out.write(_Bibliography.DASH, 0,
                  _Bibliography.DASH.length);
              wco.write(String.valueOf(day2));
              wco.m_out.write(XHTMLDocument.SEQUENCE_SMALL_SEP, 0,
                  XHTMLDocument.SEQUENCE_SMALL_SEP.length);
            }
          } else {
            _Bibliography.__fullDate(-1, null, month1, day1, wco);
            if (month2 != null) {
              wco.m_out.write(_Bibliography.DASH, 0,
                  _Bibliography.DASH.length);
              _Bibliography.__fullDate(-1, null, month2, day2, wco);
            }
            if (quarter2 != null) {
              wco.m_out.write(_Bibliography.DASH, 0,
                  _Bibliography.DASH.length);
              wco.write(quarter2.getLongName());
            }
            wco.m_out.write(XHTMLDocument.SEQUENCE_SMALL_SEP, 0,
                XHTMLDocument.SEQUENCE_SMALL_SEP.length);
          }
        } else {

          if (quarter1 != null) {
            wco.write(quarter1.getLongName());

            if (quarter2 == quarter1) {
              wco.writeNoneBreakingSpace();
            } else {
              if (quarter2 != null) {
                wco.m_out.write(_Bibliography.DASH, 0,
                    _Bibliography.DASH.length);
                wco.write(quarter2.getLongName());
                wco.m_out.write(XHTMLDocument.SEQUENCE_SMALL_SEP, 0,
                    XHTMLDocument.SEQUENCE_SMALL_SEP.length);
              } else {
                if (month2 != null) {
                  wco.m_out.write(_Bibliography.DASH, 0,
                      _Bibliography.DASH.length);
                  _Bibliography
                      .__fullDate(-1, quarter2, month2, day2, wco);
                  wco.m_out.write(XHTMLDocument.SEQUENCE_SMALL_SEP, 0,
                      XHTMLDocument.SEQUENCE_SMALL_SEP.length);
                }
              }
            }

          } else {
            _Bibliography.__fullDate(-1, quarter2, month2, day2, wco);
            if ((quarter2 != null) && (month2 != null)) {
              wco.m_out.write(' ');
            }
          }

        }

        wco.write(String.valueOf(year1));
      } else {
        _Bibliography.__fullDate(year1, quarter1, month1, day1, wco);
        wco.m_out.write(_Bibliography.DASH, 0, _Bibliography.DASH.length);
        _Bibliography.__fullDate(year2, quarter2, month2, day2, wco);
      }

    }

    wco.m_out.write(XHTMLDocument.SPAN_END, 0,
        XHTMLDocument.SPAN_END.length);
  }

  /**
   * Write the beginning of a record
   *
   * @param ref
   *          the article
   * @param wco
   *          the writer char output
   * @throws IOException
   *           if io fails
   */
  private static final void __beginRecord(final BibRecord ref,
      final XHTMLDocument wco) throws IOException {
    final BibAuthors aut;

    aut = ref.getAuthors();
    if ((aut != null) && (aut.size() > 0)) {
      wco.m_out.write(_Bibliography.SPAN_AUTHORS, 0,
          _Bibliography.SPAN_AUTHORS.length);
      wco.writeSequence(new _BibAuthorsSequence(ref.getAuthors(),
          _Bibliography.SPAN_AUTHOR, wco), ESequenceType.AND, false);
      wco.m_out.write(XHTMLDocument.SPAN_END, 0,
          XHTMLDocument.SPAN_END.length);
      wco.m_out.write(_Bibliography.BEFORE_TITLE, 0,
          _Bibliography.BEFORE_TITLE.length);
    }

    wco.m_out.write(_Bibliography.SPAN_TITLE, 0,
        _Bibliography.SPAN_TITLE.length);
    wco.m_out.write(_Bibliography.BEGIN_TITLE, 0,
        _Bibliography.BEGIN_TITLE.length);
    wco.write(ref.getTitle());
    wco.m_out.write(_Bibliography.END_TITLE, 0,
        _Bibliography.END_TITLE.length);
    wco.m_out.write(XHTMLDocument.SPAN_END, 0,
        XHTMLDocument.SPAN_END.length);

    wco.m_out.write(' ');
  }

  /**
   * Write the beginning of a record
   *
   * @param ref
   *          the article
   * @param wco
   *          the writer char output
   * @param needsComma
   *          must a comma be put first?
   * @throws IOException
   *           if io fails
   */
  private static final void __endRecord(final boolean needsComma,
      final BibRecord ref, final XHTMLDocument wco) throws IOException {
    boolean nc;
    String s;
    URI uri;

    s = ref.getDoi();
    nc = needsComma;
    if ((s != null) && (s.length() > 0)) {
      if (nc) {
        wco.m_out.write(XHTMLDocument.SEQUENCE_SMALL_SEP, 0, //
            XHTMLDocument.SEQUENCE_SMALL_SEP.length);
      } else {
        nc = true;
      }
      wco.m_out.write(_Bibliography.SPAN_DOI, 0,
          _Bibliography.SPAN_DOI.length);
      wco.write(s);
      wco.m_out.write(XHTMLDocument.ATTRIB_TAG_BEGIN_END, 0,
          XHTMLDocument.ATTRIB_TAG_BEGIN_END.length);
      wco.write(s);
      wco.m_out.write(XHTMLDocument.REF_3, 0, XHTMLDocument.REF_3.length);
      wco.m_out.write(XHTMLDocument.SPAN_END, 0,
          XHTMLDocument.SPAN_END.length);
    }

    uri = ref.getURI();
    if (uri != null) {
      s = uri.toString();
      if ((s != null) && (s.length() > 0)) {
        if (nc) {
          wco.m_out.write(XHTMLDocument.SEQUENCE_SMALL_SEP, 0, //
              XHTMLDocument.SEQUENCE_SMALL_SEP.length);
        } else {
          nc = true;
        }
        wco.m_out.write(_Bibliography.SPAN_LINK, 0,
            _Bibliography.SPAN_LINK.length);
        wco.write(s);
        wco.m_out.write(XHTMLDocument.ATTRIB_TAG_BEGIN_END, 0,
            XHTMLDocument.ATTRIB_TAG_BEGIN_END.length);
        wco.write("link"); //$NON-NLS-1$
        wco.m_out
            .write(XHTMLDocument.REF_3, 0, XHTMLDocument.REF_3.length);
        wco.writeChar(']');
        wco.m_out.write(XHTMLDocument.SPAN_END, 0,
            XHTMLDocument.SPAN_END.length);
      }
    }
  }

  /**
   * Write an article
   *
   * @param ref
   *          the article
   * @param wco
   *          the writer char output
   * @throws IOException
   *           if io fails
   */
  private static final void __writeArticle(final BibArticle ref,
      final XHTMLDocument wco) throws IOException {
    String v, i, sp, ep;

    wco.m_out.write(_Bibliography.SPAN_ARTICLE, 0,
        _Bibliography.SPAN_ARTICLE.length);

    _Bibliography.__beginRecord(ref, wco);

    wco.m_out.write(_Bibliography.SPAN_JOURNAL, 0,
        _Bibliography.SPAN_JOURNAL.length);
    wco.write(ref.getJournal());
    wco.m_out.write(XHTMLDocument.SPAN_END, 0,
        XHTMLDocument.SPAN_END.length);

    wco.m_out.write(' ');
    v = ref.getVolume();
    if ((v != null) && (v.length() > 0)) {
      wco.m_out.write(_Bibliography.SPAN_JOURNAL_VOLUME, 0,
          _Bibliography.SPAN_JOURNAL_VOLUME.length);
      wco.write(v);
      wco.m_out.write(XHTMLDocument.SPAN_END, 0, //
          XHTMLDocument.SPAN_END.length);
    } else {
      v = null;
    }

    i = ref.getNumber();
    if ((i != null) && (i.length() > 0)) {
      if (v != null) {
        wco.m_out.write('(');
      }
      wco.m_out.write(_Bibliography.SPAN_ISSUE, 0,
          _Bibliography.SPAN_ISSUE.length);
      wco.write(i);
      wco.m_out.write(XHTMLDocument.SPAN_END, 0,//
          XHTMLDocument.SPAN_END.length);
      if (v != null) {
        wco.m_out.write(')');
      }
    } else {
      i = null;
    }

    sp = ref.getStartPage();
    ep = ref.getEndPage();
    if ((sp != null) && (sp.length() > 0)) {
      if ((v != null) || (i != null)) {
        wco.m_out.write(':');
      }
      wco.m_out.write(_Bibliography.SPAN_PAGES, 0,
          _Bibliography.SPAN_PAGES.length);
      wco.write(sp);
      if ((ep != null) && (ep.length() > 0)) {
        wco.m_out.write(_Bibliography.DASH, 0, _Bibliography.DASH.length);
        wco.write(ep);
      }
      wco.m_out.write(XHTMLDocument.SPAN_END, 0, //
          XHTMLDocument.SPAN_END.length);
    }

    wco.m_out.write(XHTMLDocument.SEQUENCE_SMALL_SEP, 0, //
        XHTMLDocument.SEQUENCE_SMALL_SEP.length);
    _Bibliography.__date(ref.getDate(), null, wco);

    _Bibliography.__endRecord(true, ref, wco);
    wco.m_out.write(XHTMLDocument.SPAN_END, 0,
        XHTMLDocument.SPAN_END.length);
  }

  /**
   * write the book data
   *
   * @param printTitle
   *          should we print the book title?
   * @param ref
   *          the record
   * @param wco
   *          the dest
   * @param putComma
   *          put a comma?
   * @return will subsequent output need a comma?
   * @throws IOException
   *           if i/o fails
   */
  private static final boolean __writeBookData(final boolean printTitle,
      final boolean putComma, final BibBook ref, final XHTMLDocument wco)
      throws IOException {
    BibAuthors eds;
    char[] ch;
    int i;
    boolean needsComma;
    String s, t;
    final BibProceedings proc;

    needsComma = putComma;
    if (printTitle) {
      wco.m_out.write(_Bibliography.IN, (needsComma ? 0 : 1),
          _Bibliography.IN.length);
      wco.m_out.write(_Bibliography.SPAN_BOOK_TITLE, 0,
          _Bibliography.SPAN_BOOK_TITLE.length);
      wco.write(ref.getTitle());
      wco.m_out.write(XHTMLDocument.SPAN_END, //
          0, XHTMLDocument.SPAN_END.length);
      needsComma = true;
    }

    s = ref.getEdition();
    if ((s != null) && (s.length() > 0)) {
      wco.m_out.write(_Bibliography.SPAN_EDITION, 0,
          _Bibliography.SPAN_EDITION.length);
      wco.write(s);
      wco.writeNoneBreakingSpace();
      s = ("edition"); //$NON-NLS-1$
      wco.m_out.write(s, 0, s.length());
      wco.m_out.write(XHTMLDocument.SPAN_END, //
          0, XHTMLDocument.SPAN_END.length);
      needsComma = true;
    }

    eds = ref.getEditors();
    if ((eds != null) && ((i = eds.size()) > 0)) {
      if (needsComma) {
        wco.m_out.write(XHTMLDocument.SEQUENCE_SMALL_SEP, 0, //
            XHTMLDocument.SEQUENCE_SMALL_SEP.length);
      }
      wco.m_out.write(_Bibliography.SPAN_EDITORS, 0,
          _Bibliography.SPAN_EDITORS.length);
      wco.writeSequence(new _BibAuthorsSequence(eds,
          _Bibliography.SPAN_EDITOR, wco), ESequenceType.AND, false);

      ch = _Bibliography.EDITORS;
      wco.m_out.write(ch, 0, ch.length - ((i > 1) ? 0 : 1));
      wco.m_out.write(XHTMLDocument.SPAN_END, //
          0, XHTMLDocument.SPAN_END.length);
      needsComma = true;
    }

    if (needsComma) {
      wco.m_out.write(XHTMLDocument.SEQUENCE_SMALL_SEP, 0, //
          XHTMLDocument.SEQUENCE_SMALL_SEP.length);
    }
    if (ref instanceof BibProceedings) {
      proc = ((BibProceedings) ref);
      _Bibliography.__date(proc.getStartDate(), proc.getEndDate(), wco);

      s = proc.getLocation();
      if ((s != null) && (s.length() > 0)) {
        wco.m_out.write(XHTMLDocument.SEQUENCE_SMALL_SEP, 0, //
            XHTMLDocument.SEQUENCE_SMALL_SEP.length);
        wco.m_out.write(_Bibliography.SPAN_LOCATION, 0,
            _Bibliography.SPAN_LOCATION.length);
        wco.write(s);
        wco.m_out.write(XHTMLDocument.SPAN_END, //
            0, XHTMLDocument.SPAN_END.length);
      }
    } else {
      _Bibliography.__date(ref.getDate(), null, wco);
    }
    needsComma = true;

    s = ref.getSeries();
    if (s != null) {
      if (needsComma) {
        wco.m_out.write(XHTMLDocument.SEQUENCE_SMALL_SEP, 0, //
            XHTMLDocument.SEQUENCE_SMALL_SEP.length);
      }

      t = ref.getVolume();
      if (t != null) {
        wco.m_out.write(_Bibliography.SPAN_SERIES_VOLUME, 0,
            _Bibliography.SPAN_SERIES_VOLUME.length);
        wco.write(t);
        wco.m_out.write(XHTMLDocument.SPAN_END, //
            0, XHTMLDocument.SPAN_END.length);
        t = " of&nbsp;"; //$NON-NLS-1$
        wco.m_out.write(t, 0, t.length());
      } else {
        wco.m_out.write(_Bibliography.IN, 0, _Bibliography.IN.length);
      }

      wco.m_out.write(_Bibliography.SPAN_SERIES, 0,
          _Bibliography.SPAN_SERIES.length);
      wco.write(s);
      wco.m_out.write(XHTMLDocument.SPAN_END, //
          0, XHTMLDocument.SPAN_END.length);
      needsComma = true;
    }

    s = ref.getPublisher();
    if (s != null) {
      if (needsComma) {
        wco.m_out.write(XHTMLDocument.SEQUENCE_SMALL_SEP, 0, //
            XHTMLDocument.SEQUENCE_SMALL_SEP.length);
      }
      t = ref.getPublisherAddress();
      if (t != null) {
        wco.m_out.write(_Bibliography.SPAN_PUBLISHER_ADDRESS, 0,
            _Bibliography.SPAN_PUBLISHER_ADDRESS.length);
        wco.write(t);
        wco.m_out.write(XHTMLDocument.SPAN_END, //
            0, XHTMLDocument.SPAN_END.length);
        wco.m_out.write(_Bibliography.BEFORE_TITLE, 0,
            _Bibliography.BEFORE_TITLE.length);
      }
      wco.m_out.write(_Bibliography.SPAN_PUBLISHER, 0,
          _Bibliography.SPAN_PUBLISHER.length);
      wco.write(s);
      wco.m_out.write(XHTMLDocument.SPAN_END, //
          0, XHTMLDocument.SPAN_END.length);
      needsComma = true;
    }

    return needsComma;
  }

  /**
   * Write a book
   *
   * @param ref
   *          the article
   * @param wco
   *          the writer char output
   * @throws IOException
   *           if io fails
   */
  private static final void __writeBook(final BibBook ref,
      final XHTMLDocument wco) throws IOException {
    boolean d;

    wco.m_out.write(_Bibliography.SPAN_BOOK, 0,
        _Bibliography.SPAN_BOOK.length);

    _Bibliography.__beginRecord(ref, wco);
    d = _Bibliography.__writeBookData(false, false, ref, wco);
    _Bibliography.__endRecord(d, ref, wco);

    wco.m_out.write(XHTMLDocument.SPAN_END, 0,
        XHTMLDocument.SPAN_END.length);
  }

  /**
   * Write a book
   *
   * @param ref
   *          the article
   * @param wco
   *          the writer char output
   * @throws IOException
   *           if io fails
   */
  private static final void __writeWebsite(final BibWebsite ref,
      final XHTMLDocument wco) throws IOException {
    boolean needsComma;
    String s, t;

    wco.m_out.write(_Bibliography.SPAN_WEBSITE, 0,//
        _Bibliography.SPAN_WEBSITE.length);

    _Bibliography.__beginRecord(ref, wco);
    _Bibliography.__date(ref.getDate(), null, wco);

    needsComma = true;
    s = ref.getPublisher();
    if (s != null) {
      wco.m_out.write(XHTMLDocument.SEQUENCE_SMALL_SEP, 0, //
          XHTMLDocument.SEQUENCE_SMALL_SEP.length);

      t = ref.getPublisherAddress();
      if (t != null) {
        wco.m_out.write(_Bibliography.SPAN_PUBLISHER_ADDRESS, 0,
            _Bibliography.SPAN_PUBLISHER_ADDRESS.length);
        wco.write(t);
        wco.m_out.write(XHTMLDocument.SPAN_END, //
            0, XHTMLDocument.SPAN_END.length);
        wco.m_out.write(_Bibliography.BEFORE_TITLE, 0,
            _Bibliography.BEFORE_TITLE.length);
      }
      wco.m_out.write(_Bibliography.SPAN_PUBLISHER, 0,
          _Bibliography.SPAN_PUBLISHER.length);
      wco.write(s);
      wco.m_out.write(XHTMLDocument.SPAN_END, //
          0, XHTMLDocument.SPAN_END.length);
      needsComma = true;
    }

    _Bibliography.__endRecord(needsComma, ref, wco);

    wco.m_out.write(XHTMLDocument.SPAN_END, 0,
        XHTMLDocument.SPAN_END.length);
  }

  /**
   * Write a proceedings
   *
   * @param ref
   *          the article
   * @param wco
   *          the writer char output
   * @throws IOException
   *           if io fails
   */
  private static final void __writeProceedings(final BibProceedings ref,
      final XHTMLDocument wco) throws IOException {
    boolean d;

    wco.m_out.write(_Bibliography.SPAN_PROCEEDINGS, 0,//
        _Bibliography.SPAN_PROCEEDINGS.length);

    _Bibliography.__beginRecord(ref, wco);
    d = _Bibliography.__writeBookData(false, false, ref, wco);

    _Bibliography.__endRecord(d, ref, wco);

    wco.m_out.write(XHTMLDocument.SPAN_END, 0,
        XHTMLDocument.SPAN_END.length);
  }

  /**
   * Write an in proceedings
   *
   * @param ref
   *          the article
   * @param wco
   *          the writer char output
   * @throws IOException
   *           if io fails
   */
  private static final void __writeInProceedings(
      final BibInProceedings ref, final XHTMLDocument wco)
      throws IOException {
    boolean d;
    String sp, ep;

    wco.m_out.write(_Bibliography.SPAN_IN_PROCEEDINGS, 0,//
        _Bibliography.SPAN_IN_PROCEEDINGS.length);

    _Bibliography.__beginRecord(ref, wco);

    d = _Bibliography.__writeBookData(true, false, ref.getBook(), wco);

    sp = ref.getStartPage();
    ep = ref.getEndPage();
    if (sp != null) {
      if (d) {
        wco.m_out.write(XHTMLDocument.SEQUENCE_SMALL_SEP, 0, //
            XHTMLDocument.SEQUENCE_SMALL_SEP.length);
      } else {
        d = true;
      }
      wco.m_out.write(_Bibliography.SPAN_PAGES, 0,
          _Bibliography.SPAN_PAGES.length);
      if ((ep != null) && (ep.length() > 0)) {
        wco.m_out
            .write(_Bibliography.PAGES, 0, _Bibliography.PAGES.length);
      } else {
        wco.m_out.write(_Bibliography.PAGE, 0, _Bibliography.PAGE.length);
        ep = null;
      }

      wco.write(sp);
      if (ep != null) {
        wco.m_out.write(_Bibliography.DASH, 0, _Bibliography.DASH.length);
        wco.write(ep);
      }
      wco.m_out.write(XHTMLDocument.SPAN_END, 0, //
          XHTMLDocument.SPAN_END.length);
    }

    sp = ref.getChapter();
    if ((sp != null) && (sp.length() > 0)) {
      if (d) {
        wco.m_out.write(XHTMLDocument.SEQUENCE_SMALL_SEP, 0, //
            XHTMLDocument.SEQUENCE_SMALL_SEP.length);
      } else {
        d = true;
      }
      wco.m_out.write(_Bibliography.SPAN_CHAPTER, 0,
          _Bibliography.SPAN_CHAPTER.length);
      wco.write(sp);
      wco.m_out.write(XHTMLDocument.SPAN_END, 0, //
          XHTMLDocument.SPAN_END.length);
    }

    _Bibliography.__endRecord(d, ref, wco);

    wco.m_out.write(XHTMLDocument.SPAN_END, 0,
        XHTMLDocument.SPAN_END.length);
  }

  /**
   * Write an in collection
   *
   * @param ref
   *          the article
   * @param wco
   *          the writer char output
   * @throws IOException
   *           if io fails
   */
  private static final void __writeInCollection(final BibInCollection ref,
      final XHTMLDocument wco) throws IOException {
    boolean d;
    String sp, ep;

    wco.m_out.write(_Bibliography.SPAN_IN_COLLECTION, 0,//
        _Bibliography.SPAN_IN_COLLECTION.length);

    _Bibliography.__beginRecord(ref, wco);

    d = _Bibliography.__writeBookData(true, false, ref.getBook(), wco);

    sp = ref.getStartPage();
    ep = ref.getEndPage();
    if (sp != null) {
      if (d) {
        wco.m_out.write(XHTMLDocument.SEQUENCE_SMALL_SEP, 0, //
            XHTMLDocument.SEQUENCE_SMALL_SEP.length);
      } else {
        d = true;
      }
      wco.m_out.write(_Bibliography.SPAN_PAGES, 0,
          _Bibliography.SPAN_PAGES.length);
      if ((ep != null) && (ep.length() > 0)) {
        wco.m_out
            .write(_Bibliography.PAGES, 0, _Bibliography.PAGES.length);
      } else {
        wco.m_out.write(_Bibliography.PAGE, 0, _Bibliography.PAGE.length);
        ep = null;
      }

      wco.write(sp);
      if (ep != null) {
        wco.m_out.write(_Bibliography.DASH, 0, _Bibliography.DASH.length);
        wco.write(ep);
      }
      wco.m_out.write(XHTMLDocument.SPAN_END, 0, //
          XHTMLDocument.SPAN_END.length);
    }

    sp = ref.getChapter();
    if ((sp != null) && (sp.length() > 0)) {
      if (d) {
        wco.m_out.write(XHTMLDocument.SEQUENCE_SMALL_SEP, 0, //
            XHTMLDocument.SEQUENCE_SMALL_SEP.length);
      } else {
        d = true;
      }
      wco.m_out.write(_Bibliography.SPAN_CHAPTER, 0,
          _Bibliography.SPAN_CHAPTER.length);
      wco.write(sp);
      wco.m_out.write(XHTMLDocument.SPAN_END, 0, //
          XHTMLDocument.SPAN_END.length);
    }

    _Bibliography.__endRecord(d, ref, wco);

    wco.m_out.write(XHTMLDocument.SPAN_END, 0,
        XHTMLDocument.SPAN_END.length);
  }

  /**
   * Write a tech report
   *
   * @param ref
   *          the article
   * @param wco
   *          the writer char output
   * @throws IOException
   *           if io fails
   */
  private static final void __writeTechReport(final BibTechReport ref,
      final XHTMLDocument wco) throws IOException {
    boolean needsComma;
    String s, t;

    wco.m_out.write(_Bibliography.SPAN_TECH_REPORT, 0,//
        _Bibliography.SPAN_TECH_REPORT.length);

    _Bibliography.__beginRecord(ref, wco);

    _Bibliography.__date(ref.getDate(), null, wco);

    needsComma = true;

    s = ref.getSeries();
    t = ref.getNumber();
    if (t != null) {
      if (needsComma) {
        wco.m_out.write(XHTMLDocument.SEQUENCE_SMALL_SEP, 0, //
            XHTMLDocument.SEQUENCE_SMALL_SEP.length);
      }

      wco.m_out.write(_Bibliography.SPAN_REPORT_NUMBER, 0,
          _Bibliography.SPAN_REPORT_NUMBER.length);
      wco.write(t);
      wco.m_out.write(XHTMLDocument.SPAN_END, //
          0, XHTMLDocument.SPAN_END.length);

      if (s != null) {
        wco.m_out.write(_Bibliography.IN, 0, _Bibliography.IN.length);
      }
      needsComma = true;
    }

    if (s != null) {
      if (needsComma) {
        if (t == null) {
          wco.m_out.write(XHTMLDocument.SEQUENCE_SMALL_SEP, 0, //
              XHTMLDocument.SEQUENCE_SMALL_SEP.length);
        }
      }

      wco.m_out.write(_Bibliography.SPAN_RSERIES, 0,
          _Bibliography.SPAN_RSERIES.length);
      wco.write(t);
      wco.m_out.write(XHTMLDocument.SPAN_END, //
          0, XHTMLDocument.SPAN_END.length);
      needsComma = true;
    }

    s = ref.getInstitute();
    if (s != null) {
      if (needsComma) {
        wco.m_out.write(XHTMLDocument.SEQUENCE_SMALL_SEP, 0, //
            XHTMLDocument.SEQUENCE_SMALL_SEP.length);
      }
      t = ref.getInstituteAddress();
      if (t != null) {
        wco.m_out.write(_Bibliography.SPAN_INSTITUTE_ADDRESS, 0,
            _Bibliography.SPAN_INSTITUTE_ADDRESS.length);
        wco.write(t);
        wco.m_out.write(XHTMLDocument.SPAN_END, //
            0, XHTMLDocument.SPAN_END.length);
        wco.m_out.write(_Bibliography.BEFORE_TITLE, 0,
            _Bibliography.BEFORE_TITLE.length);
      }
      wco.m_out.write(_Bibliography.SPAN_INSTITUTE, 0,
          _Bibliography.SPAN_INSTITUTE.length);
      wco.write(s);
      wco.m_out.write(XHTMLDocument.SPAN_END, //
          0, XHTMLDocument.SPAN_END.length);
      needsComma = true;
    }

    _Bibliography.__endRecord(needsComma, ref, wco);

    wco.m_out.write(XHTMLDocument.SPAN_END, 0,
        XHTMLDocument.SPAN_END.length);
  }

  /**
   * Write a tech report
   *
   * @param ref
   *          the article
   * @param wco
   *          the writer char output
   * @throws IOException
   *           if io fails
   */
  private static final void __writeThesis(final BibThesis ref,
      final XHTMLDocument wco) throws IOException {
    boolean needsComma;
    EThesisType ht;
    String s, t;

    wco.m_out.write(_Bibliography.SPAN_THESIS, 0,//
        _Bibliography.SPAN_THESIS.length);

    _Bibliography.__beginRecord(ref, wco);
    needsComma = false;

    ht = ref.getType();
    if (ht != null) {
      wco.m_out.write(_Bibliography.SPAN_THESIS_TYPE, 0,
          _Bibliography.SPAN_THESIS_TYPE.length);
      wco.write(ht.getName());
      wco.m_out.write(XHTMLDocument.SPAN_END, 0, //
          XHTMLDocument.SPAN_END.length);
      needsComma = true;
    }

    s = ref.getSchool();
    if (s != null) {
      if (needsComma) {
        wco.m_out.write(XHTMLDocument.SEQUENCE_SMALL_SEP, 0, //
            XHTMLDocument.SEQUENCE_SMALL_SEP.length);
      }
      t = ref.getSchoolAddress();
      if (t != null) {
        wco.m_out.write(_Bibliography.SPAN_SCHOOL_ADDRESS, 0,
            _Bibliography.SPAN_SCHOOL_ADDRESS.length);
        wco.write(t);
        wco.m_out.write(XHTMLDocument.SPAN_END, //
            0, XHTMLDocument.SPAN_END.length);
        wco.m_out.write(_Bibliography.BEFORE_TITLE, 0,
            _Bibliography.BEFORE_TITLE.length);
      }
      wco.m_out.write(_Bibliography.SPAN_SCHOOL, 0,
          _Bibliography.SPAN_SCHOOL.length);
      wco.write(s);
      wco.m_out.write(XHTMLDocument.SPAN_END, //
          0, XHTMLDocument.SPAN_END.length);
      needsComma = true;
    }

    needsComma = _Bibliography.__writeBookData(false, true, ref, wco);

    _Bibliography.__endRecord(needsComma, ref, wco);

    wco.m_out.write(XHTMLDocument.SPAN_END, 0,
        XHTMLDocument.SPAN_END.length);
  }

}
