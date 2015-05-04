package org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tsp;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.spec.DescriptionModule;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.document.spec.ECitationMode;
import org.logisticPlanning.utils.document.spec.EMathName;
import org.logisticPlanning.utils.document.spec.Emphasize;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.InQuotes;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.MathName;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;
import org.logisticPlanning.utils.document.spec.bib.BibArticle;
import org.logisticPlanning.utils.document.spec.bib.BibAuthor;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;
import org.logisticPlanning.utils.document.spec.bib.BibDate;
import org.logisticPlanning.utils.document.spec.bib.BibInProceedings;
import org.logisticPlanning.utils.document.spec.bib.BibProceedings;
import org.logisticPlanning.utils.document.spec.bib.EBibMonth;
import org.logisticPlanning.utils.text.EQuotes;

/**
 * A description of the representations.<h2>References</h2>
 * <ol>
 * <li><div><span id="cite_LKMUB1999GAFTTSPARORAO" /><a href=
 * "http://cig.fi.upm.es/index.php?option=com_content&amp;view=article&amp;id=78&amp;Itemid=111"
 * >Pedro Larra&#241;aga</a>, <a href=
 * "http://www.tilburguniversity.edu/nl/webwijs/show/&amp;uid=c.m.h.kuijpers?uid=c.m.h.kuijpers"
 * >Cindy M. H. Kuijpers</a>, Roberto H. Murga, <a
 * href="http://www.sc.ehu.es/ccwbayes/members/inaki.htm">I&#241;aki
 * Inza</a>, and&nbsp;Sejla Dizdarevic: <span
 * style="font-weight:bold">&ldquo;Genetic Algorithms for the Travelling
 * Salesman Problem: A Review of Representations and
 * Operators,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of Artificial
 * Intelligence Research (JAIR)</span> 13(2):129&ndash;170,
 * April&nbsp;1999; published by El Segundo, CA, USA: AI Access Foundation,
 * Inc. and&nbsp;Menlo Park, CA, USA: AAAI Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1023/A:1006529012972"
 * >10.1023/A:1006529012972</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/110769757">11076-9757</a>.
 * <div>link: [<a href=
 * "http://www.dca.fee.unicamp.br/~gomide/courses/EA072/artigos/Genetic_Algorithm_TSPR_eview_Larranaga_1999.pdf"
 * >1</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.35.8882"
 * >10.1.1.35.8882</a></div></div></li>
 * <li><div><span id="cite_P1996GAFTTSP" /><a
 * href="http://www.iro.umontreal.ca/~potvin/">Jean-Yves Potvin</a>: <span
 * style="font-weight:bold">&ldquo;Genetic Algorithms for the Traveling
 * Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Annals of Operations
 * Research</span> 63(3):337&ndash;370, June&nbsp;1, 1996; published by
 * Dordrecht, Netherlands: Springer Netherlands and&nbsp;Amsterdam, The
 * Netherlands: J. C. Baltzer AG, Science Publishers. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/BF02125403">10.1007/BF02125403</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/02545330">0254-5330</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15729338">1572-9338</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href
 * ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.93.2179"
 * >10.1.1.93 .2179</a></div></div></li>
 * <li><div><span id="cite_GGRVG1985GAFTT" /><a
 * href="http://www.biostat.pitt.edu/directory/bios/grefenstette.asp">John
 * J. Grefenstette</a>, Rajeev Gopal, <a href=
 * "http://academics.hamilton.edu/computer_science/brosmait/index.html"
 * >Brian J. Rosmaita</a>, and&nbsp;<a
 * href="http://www.cs.indiana.edu/~vgucht/">Dirk Van Gucht</a>: <span
 * style="font-weight:bold">&ldquo;Genetic Algorithms for the
 * TSP,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 1st
 * International Conference on Genetic Algorithms and their Applications
 * (ICGA'85)</span>, June&nbsp;24&ndash;26, 1985, Pittsburgh, PA, USA:
 * Carnegy Mellon University (CMU), pages 160&ndash;168, <a
 * href="http://www.biostat.pitt.edu/directory/bios/grefenstette.asp">John
 * J. Grefenstette</a>, editor, Hillsdale, NJ, USA: Lawrence Erlbaum
 * Associates. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0805804269">0-8058-0426-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780805804263">978-0-8058
 * -0426-3</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/19702892">19702892</a></div></li>
 * <li><div><span id="cite_FJMGO1995DSFTS" /><a
 * href="http://en.wikipedia.org/wiki/Michael_Fredman">Michael L.
 * Fredman</a>, <a
 * href="https://en.wikipedia.org/wiki/David_S._Johnson">David Stifler
 * Johnson</a>, <a
 * href="https://www.amherst.edu/people/facstaff/lamcgeoch">Lyle A.
 * McGeoch</a>, and&nbsp;Gerard Ostheimer: <span
 * style="font-weight:bold">&ldquo;Data Structures for Traveling
 * Salesmen,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of
 * Algorithms</span> 18(3):432&ndash;479, May&nbsp;1995; published by
 * Amsterdam, The Netherlands: Elsevier Science Publishers B.V..
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1006/jagm.1995.1018">10.1006/jagm
 * .1995.1018</a>. <div>link: [<a
 * href="ftp://dimacs.rutgers.edu/pub/dsj/temp/data.ps">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.71.170">10.1
 * .1.71 .170</a></div></div></li>
 * </ol>
 */
public final class DescRepresentations extends DescriptionModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the article&nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
   * style="font-weight:bold">1</a>]
   */
  private static final BibArticle LKMUB1999GAFTTSPARORAO;

  /**
   * the article&nbsp;[<a href="#cite_P1996GAFTTSP"
   * style="font-weight:bold">2</a>]
   */
  private static final BibArticle P1996GAFTTSP;

  /**
   * the in-proceedings&nbsp;[<a href="#cite_GGRVG1985GAFTT"
   * style="font-weight:bold">3</a>]
   */
  static final BibInProceedings GGRVG1985GAFTT;

  /**
   * the article&nbsp;[<a href="#cite_FJMGO1995DSFTS"
   * style="font-weight:bold">4</a>]
   */
  private static final BibArticle FJMGO1995DSFTS;

  /**
   * the in-proceedings&nbsp;[<a href="#cite_ORG2005TSLARDLL"
   * style="font-weight:bold">5</a>]
   */
  static final BibInProceedings ORG2005TSLARDLL;

  static {
    try {
      LKMUB1999GAFTTSPARORAO = new BibArticle(
          new BibAuthors(//
              new BibAuthor("Pedro", "Larra\u00f1aga"),//$NON-NLS-1$//$NON-NLS-2$
              new BibAuthor("Cindy M. H.", "Kuijpers"),//$NON-NLS-1$//$NON-NLS-2$
              new BibAuthor("Roberto H.", "Murga"),//$NON-NLS-1$//$NON-NLS-2$
              new BibAuthor("I\u00f1aki", "Inza"),//$NON-NLS-1$//$NON-NLS-2$
              new BibAuthor("Sejla", "Dizdarevic")//$NON-NLS-1$//$NON-NLS-2$"
              ),//
              "Genetic Algorithms for the Travelling Salesman Problem: A Review of Representations and Operators",//$NON-NLS-1$
              new BibDate(1991, EBibMonth.APRIL),//
              "Journal of Artificial Intelligence Research",//$NON-NLS-1$
              "13", "2",//$NON-NLS-1$//$NON-NLS-2$
              "129", "170",//$NON-NLS-1$//$NON-NLS-2$
              new URI(
                  "http://www.dca.fee.unicamp.br/~gomide/courses/EA072/artigos/Genetic_Algorithm_TSPR_eview_Larranaga_1999.pdf"),//$NON-NLS-1$
          "10.1023/A:1006529012972");//$NON-NLS-1$
    } catch (final URISyntaxException usi) {
      throw new RuntimeException(usi);
    }

    try {
      P1996GAFTTSP = new BibArticle(
          new BibAuthors(//
              new BibAuthor("Jean-Yves", "Potvin")//$NON-NLS-1$//$NON-NLS-2$
              ),//
              "Genetic Algorithms for the Traveling Salesman Problem",//$NON-NLS-1$
              new BibDate(1996, EBibMonth.JUNE, 1),//
              "Annals of Operations Research",//$NON-NLS-1$
              "63", "3",//$NON-NLS-1$//$NON-NLS-2$
              "337", "370",//$NON-NLS-1$//$NON-NLS-2$
              new URI(
                  "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.6.4205"),//$NON-NLS-1$
          "10.1007/BF02125403");//$NON-NLS-1$
    } catch (final URISyntaxException usi) {
      throw new RuntimeException(usi);
    }

    GGRVG1985GAFTT = new BibInProceedings(new BibAuthors(new BibAuthor(
        "John J.", "Grefenstette"),//$NON-NLS-1$//$NON-NLS-2$
        new BibAuthor("Rajeev", "Gopal"),//$NON-NLS-1$//$NON-NLS-2$
        new BibAuthor("Brian J.", "Rosmaita"),//$NON-NLS-1$//$NON-NLS-2$
        new BibAuthor("Dirk", "Van Gucht")//$NON-NLS-1$//$NON-NLS-2$
        ),//
        "Genetic Algorithms for the TSP",//$NON-NLS-1$
        //
        new BibProceedings(
            "Proceedings of the 1st International Conference on Genetic Algorithms and their Applications (ICGA'85)",//$NON-NLS-1$
            new BibDate(1985, EBibMonth.JUNE, 24), //
            new BibDate(1985, EBibMonth.JUNE, 26), //
            new BibAuthors(//
                new BibAuthor("John J.", "Grefenstette")//$NON-NLS-1$//$NON-NLS-2$
                ),//
                "Carnegy Mellon University (CMU), Pittsburgh, PA, USA",//$NON-NLS-1$
                "Lawrence Erlbaum Associates", "Hillsdale, NJ, USA",//$NON-NLS-1$//$NON-NLS-2$
                null, null, null, null), //
                "160", "168", null,//$NON-NLS-1$//$NON-NLS-2$
                null, null);
    try {
      FJMGO1995DSFTS = new BibArticle(
          new BibAuthors(//
              new BibAuthor("Michael L.", "Fredman"),//$NON-NLS-1$//$NON-NLS-2$
              DescTimeMeasures.DSJ,//
              DescTimeMeasures.LAMG,//
              new BibAuthor("Gerard", "Ostheimer")//$NON-NLS-1$//$NON-NLS-2$
              ),//
              "Data Structures for Traveling Salesmen",//$NON-NLS-1$
              new BibDate(1995, EBibMonth.MAY),//
              "Journal of Algorithms",//$NON-NLS-1$
              "18", "3",//$NON-NLS-1$//$NON-NLS-2$
              "432", "479",//$NON-NLS-1$//$NON-NLS-2$
              new URI(
                  "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.71.170"),//$NON-NLS-1$
          "10.1006/jagm.1995.1018");//$NON-NLS-1$
    } catch (final URISyntaxException usi) {
      throw new RuntimeException(usi);
    }

    ORG2005TSLARDLL = new BibInProceedings(new BibAuthors(new BibAuthor(
        "Colin", "Osterman"),//$NON-NLS-1$//$NON-NLS-2$
        new BibAuthor("C\u00e9sar", "Rego"),//$NON-NLS-1$//$NON-NLS-2$
        new BibAuthor("Dorabela", "Gamboa")//$NON-NLS-1$//$NON-NLS-2$
        ),//
        "The Satellite List: A Reversible Doubly-Linked List",//$NON-NLS-1$
        //
        new BibProceedings(
            "Proceedings of the 7th International Conference on Adaptive and Natural Computing Algorithms (ICANNGA'2005)",//$NON-NLS-1$
            new BibDate(2005, EBibMonth.MARCH, 21), //
            new BibDate(2005, EBibMonth.MARCH, 23), //
            new BibAuthors(//
                new BibAuthor("Bernardete", "Ribeiro"),//$NON-NLS-1$//$NON-NLS-2$
                new BibAuthor("Rudolf F.", "Albrecht"),//$NON-NLS-1$//$NON-NLS-2$
                new BibAuthor("Andrej", "Dobnikar"),//$NON-NLS-1$//$NON-NLS-2$
                new BibAuthor("David W.", "Pearson"),//$NON-NLS-1$//$NON-NLS-2$
                new BibAuthor("Nigel C.", "Steele")//$NON-NLS-1$//$NON-NLS-2$
                ),//
                "University of Coimbra, Coimbra, Portugal",//$NON-NLS-1$
                "Springer Verlag GmbH", "Vienna, Austria",//$NON-NLS-1$//$NON-NLS-2$
                null, null, null, "10.1007/b138998"), //$NON-NLS-1$
                "542", "545", null,//$NON-NLS-1$//$NON-NLS-2$
                null, "10.1007/3-211-27389-1_131");//$NON-NLS-1$
  }

  /**
   * create!
   *
   * @param owner
   *          the owner
   */
  DescRepresentations(final Module owner) {
    super("Representations", owner, false); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void initialize(final Header header, final ExperimentSet data)
      throws IOException {//
    super.initialize(header, data);
    Macros.SCALE.define(header);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write("Candidate Solution Representations"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionBody(final SectionBody body,
      final ExperimentSet data) throws IOException {

    body.write(//
        "Solutions to the TSP can be stored in different data structures"); //$NON-NLS-1$

    body.cite(ECitationMode.BY_ID_IN_SENTENCE,
        DescRepresentations.FJMGO1995DSFTS,
        DescRepresentations.LKMUB1999GAFTTSPARORAO,
        DescTimeMeasures.JMG2004EAOHFTS);

    body.write(". The data structure (or "); //$NON-NLS-1$

    try (InQuotes iq = body.inQuotes(EQuotes.DEFAULT)) {
      iq.write("representation");//$NON-NLS-1$
    }
    body.write(//
        ") has a strong impact on the algorithm's performance."); //$NON-NLS-1$
    body.writeLinebreak();

    body.write(//
        "One possible data structure to represent candidate solutions are permutations of the numbers "); //$NON-NLS-1$
    body.writeInt(1);
    body.write(" to ");//$NON-NLS-1$
    body.macroInvoke(Macros.SCALE);
    body.write(//
        ", where each number identifies a node and the positions of the numbers define the sequence in which the nodes are visited. This representation is called "); //$NON-NLS-1$
    try (Emphasize em = body.emphasize()) {
      em.write("path representation");//$NON-NLS-1$
    }
    body.cite(ECitationMode.BY_ID_IN_SENTENCE,
        DescRepresentations.LKMUB1999GAFTTSPARORAO,
        DescRepresentations.P1996GAFTTSP);
    body.write(". In the "); //$NON-NLS-1$
    try (Emphasize em = body.emphasize()) {
      em.write("adjacency representation");//$NON-NLS-1$
    }
    body.cite(ECitationMode.BY_ID_IN_SENTENCE,
        DescRepresentations.GGRVG1985GAFTT,
        DescRepresentations.LKMUB1999GAFTTSPARORAO,
        DescRepresentations.P1996GAFTTSP);
    body.write(//
        " the candidate solutions are, too, represented as permutations. However, here city ");//$NON-NLS-1$
    try (InlineMath im = body.inlineMath()) {
      try (MathName mn = im.mathName(EMathName.SCALAR)) {
        mn.writeChar('j');
      }
    }
    body.write(" is listed in position ");//$NON-NLS-1$
    try (InlineMath im = body.inlineMath()) {
      try (MathName mn = im.mathName(EMathName.SCALAR)) {
        mn.writeChar('i');
      }
    }
    body.write(" if, and only if, the tour directly leads from city ");//$NON-NLS-1$
    try (InlineMath im = body.inlineMath()) {
      try (MathName mn = im.mathName(EMathName.SCALAR)) {
        mn.writeChar('i');
      }
    }
    body.write(" to city ");//$NON-NLS-1$
    try (InlineMath im = body.inlineMath()) {
      try (MathName mn = im.mathName(EMathName.SCALAR)) {
        mn.writeChar('j');
      }
    }

    body.write(//
        "The adjacency representation is thus something like an array implementation of a singly-linked list, as the value stored for a node points to the next node to visit.");//$NON-NLS-1$

    body.writeLinebreak();

    body.write(//
        "Although there exist significantly more efficient (and complicated) representations");//$NON-NLS-1$
    body.cite(ECitationMode.BY_ID_IN_SENTENCE,
        DescRepresentations.FJMGO1995DSFTS,
        DescTimeMeasures.JMG2004EAOHFTS,
        DescRepresentations.ORG2005TSLARDLL);
    body.write(//
        ", these two are maybe the most common and easy-to-understand ones for anyone starting to experiment with TSPs.");//$NON-NLS-1$

    super.writeSectionBody(body, data);
  }
}
