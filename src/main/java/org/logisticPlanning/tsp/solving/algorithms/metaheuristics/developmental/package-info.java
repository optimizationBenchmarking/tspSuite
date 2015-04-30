/**
 * <p>
 * Developmental metaheuristic approaches to the Traveling Salesman Problem
 * (TSP).
 * <h2 id="developmentalMetaheuristicsList">
 * {@linkplain org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental
 * Developmental Metaheuristics}</h2>
 * <p>
 * Metaheuristics that use <em>indirect representations</em>&nbsp;[<a
 * href="#cite_DWT2011ASOSRFEOOGS" style="font-weight:bold">1</a>, <a
 * href="#cite_BK1999TWTGDACOEFAEDP" style="font-weight:bold">2</a>]: Here,
 * the genotypes (internal data structures processed by the search
 * operations) are significantly different from the phenotypes (the data
 * structures of the candidate solutions, in our case, tours) and there is
 * a non-trivial, iterative {@link org.logisticPlanning.tsp.solving.gpm
 * process} (called {@link org.logisticPlanning.tsp.solving.gpm
 * genotype-phenotype mapping}) translating from genotypes to phenotypes,
 * usually employing feedback from an environment. We implemented the
 * following approaches:
 * </p>
 * <ol>
 * <li>The
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA.DevUpdatingEA
 * Developmental Updating EA} defined in&nbsp;[<a
 * href="#cite_OWDC2013SADAFTSP" style="font-weight:bold">3</a>] uses
 * {@link org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function
 * mathematical formulas} (
 * {@link org.logisticPlanning.tsp.solving.searchSpaces.trees.Node trees})
 * as genotypes that tell the
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA.DevUpdatingGPM
 * GPM} which change to a given solution (tour) should be applied.</li>
 * </ol>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_DWT2011ASOSRFEOOGS" /><a
 * href="http://www.marmakoide.org/">Alexandre Devert</a>, <a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>], and&nbsp;<a
 * href="http://staff.ustc.edu.cn/~ketang/">Ke Tang</a> <span
 * style="color:gray">[&#21776;&#29634;</span>]: <span
 * style="font-weight:bold">&ldquo;A Study on Scalable Representations for
 * Evolutionary Optimization of Ground Structures,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Evolutionary
 * Computation</span> 20(3):453&ndash;472, Fall&nbsp;2012; published by
 * Cambridge, MA, USA: MIT Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1162/EVCO_a_00054">10.1162/EVCO_a_00054</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/44169764">44169764</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10636560">1063-6560</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15309304">1530-9304</a>;
 * PubMed:&nbsp;<a
 * href="https://www.ncbi.nlm.nih.gov/pubmed/22004002">22004002</a>;
 * SCI/WOS:&nbsp;WOS:000306767200005. <div>links: [<a href=
 * "http://www.marmakoide.org/download/publications/devweita-ecj-preprint.pdf"
 * >1</a>] and&nbsp;[<a
 * href="http://www.it-weise.de/documents/files/DWT2011ASOSRFEOOGS.pdf"
 * >2</a>]</div></div></li>
 * <li><div><span id="cite_BK1999TWTGDACOEFAEDP" /><a
 * href="http://peterjbentley.com/">Peter John Bentley</a> and&nbsp;<a
 * href="http://iianalytics.com/iia-faculty/sanjeev-kumar-2/">Sanjeev P.
 * Kumar</a>: <span style="font-weight:bold">&ldquo;The Ways to Grow
 * Designs: A Comparison of Embryogenies for an Evolutionary Design
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * Genetic and Evolutionary Computation Conference (GECCO'99)</span>,
 * July&nbsp;13&ndash;17, 1999, Orlando, FL, USA, pages 35&ndash;43, <a
 * href="http://www.cs.mun.ca/~banzhaf/">Wolfgang Banzhaf</a>, Jason M.
 * Daida, <a href="http://www.cs.vu.nl/~gusz/">&#193;goston E. Eiben</a>
 * aka. Gusz/Guszti, Max H. Garzon, Vasant Honavar, Mark J. Jakiela,
 * and&nbsp;Robert Elliott Smith, editors, San Francisco, CA, USA: Morgan
 * Kaufmann Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558606114">1-55860-611-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558606111">978-1-55860
 * -611-1</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/59333111">59333111</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=-1vTAAAACAAJ">-1vTAAAACAAJ</a>.
 * <div>link: [<a
 * href="http://www.cs.ucl.ac.uk/staff/ucacpjb/BEKUC1.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.51.1345">
 * 10.1 .1.51.1345</a></div></div></li>
 * <li><div><span id="cite_OWDC2013SADAFTSP" /><a
 * href="mailto:oyjmical@mail.ustc.edu.cn">Jin Ouyang</a> <span
 * style="color:gray">[&#27431;&#38451;&#26187;</span>], <a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>], <a
 * href="http://www.marmakoide.org/">Alexandre Devert</a>, and&nbsp;<a
 * href="http://www.swinburne.edu.my/iSECURES/index.php?do=rchiong">Raymond
 * Chiong</a>: <span style="font-weight:bold">&ldquo;SDGP: A Developmental
 * Approach for Traveling Salesman Problems,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 2013
 * IEEE Symposium on Computational Intelligence in Production and Logistics
 * Systems (CIPLS'13)</span>, April&nbsp;15&ndash;19, 2013, Singapore:
 * Grand Copthorne Waterfront Hotel, pages 78&ndash;85, Los Alamitos, CA,
 * USA: IEEE Computer Society Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781467359054"
 * >978-1-4673-5905-4</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CIPLS.2013.6595203"
 * >10.1109/CIPLS.2013.6595203</a>; INSPEC Accession Number:&nbsp;13752116;
 * EI:&nbsp;20134116837899. <div>link: [<a
 * href="http://www.it-weise.de/documents/files/OWDC2013SADAFTSP.pdf"
 * >1</a>]</div></div></li>
 * </ol>
 */
package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental;

