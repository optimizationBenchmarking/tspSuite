/**
 * <p>
 * An approach which solves the Traveling Salesman Problem (TSP) by an
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA.DevUpdatingEA
 * EA} which uses a developmental
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA.DevUpdatingGPM
 * Genotype-Phenotype Mapping} (GPM) which updates the best known solution.
 * </p>
 * <p>
 * This algorithm is very similar to the method introduced in&nbsp;[<a
 * href="#cite_OWDC2013SADAFTSP" style="font-weight:bold">1</a>], but with
 * a uniform random way to pick up indices and thus, not a {@code O(n^2)}
 * complexity. A different (older) developmental idea applied to CARPs
 * instead of TSPs is given in&nbsp;[<a href="#cite_WDT2012ADSTDCARPUGP"
 * style="font-weight:bold">2</a>, <a href="#cite_W2012RFLP"
 * style="font-weight:bold">3</a>].
 * </p>
 * <h2>References</h2>
 * <ol>
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
 * <li><div><span id="cite_WDT2012ADSTDCARPUGP" /><a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>], <a
 * href="http://staff.ustc.edu.cn/~ketang/">Ke Tang</a> <span
 * style="color:gray">[&#21776;&#29634;</span>], and&nbsp;<a
 * href="http://www.marmakoide.org/">Alexandre Devert</a>: <span
 * style="font-weight:bold">&ldquo;A Developmental Solution to (Dynamic)
 * Capacitated Arc Routing Problems using Genetic
 * Programming,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * Genetic and Evolutionary Computation Conference (GECCO'12)</span>,
 * July&nbsp;7&ndash;11, 2012, Philadelphia, PA, USA: Doubletree by Hilton
 * Hotel Philadelphia Center City, pages 831&ndash;838, Terence Soule
 * and&nbsp;<a href="http://www.epistasis.org/jason.html">Jason H.
 * Moore</a>, editors, New York, NY, USA: Association for Computing
 * Machinery (ACM). ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781450311779"
 * >978-1-4503-1177-9</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1145/2330163.2330278"
 * >10.1145/2330163.2330278</a>; SCI/WOS:&nbsp;WOS:000309611100104;
 * EI:&nbsp;20123315330852. <div>link: [<a
 * href="http://www.it-weise.de/documents/files/WDT2012ADSTDCARPUGP.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_W2012RFLP" /><a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>]: <span
 * style="font-weight:bold">&ldquo;Representations for Logistic
 * Planning,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">The Third NICaiA Workshop
 * on Nature Inspired Computation and Its Applications (NICaiA'12)</span>,
 * April&nbsp;16&ndash;17, 2012, Birmingham, UK: University of Birmingham,
 * Computer Science Building, <a href="http://www.cs.bham.ac.uk/~xin/">Xin
 * Yao</a> <span style="color:gray">[&#23002;&#26032;</span>], editor.
 * further information: [<a href=
 * "http://www.cercia.ac.uk/projects/research/NICaiA/2012workshopsinfor.php"
 * >1</a>]. <div>link: [<a
 * href="http://www.it-weise.de/documents/files/W2012RFLP.pdf"
 * >1</a>]</div></div></li>
 * </ol>
 * 
 * @author <ul>
 *         <li>
 *         <em><a href="mailto:oyjmical@mail.ustc.edu.cn">Jin Ouyang</a></em>
 *         [&#x6B27;&#x9633;&#x664B;]</li>
 *         <li><em><a href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a> (role:
 *         adaption to benchmarking framework)</li>
 *         </ul>
 */
package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA;

