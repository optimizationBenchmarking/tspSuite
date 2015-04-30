/**
 * <p>
 * In this package, we provide exact algorithms for solving TSP problems,
 * i.e., algorithms that will always return the right result &mdash; if
 * they are given enough runtime to complete, that is.
 * </p>
 * <h2 id="exactAlgorithmsList">
 * {@linkplain org.logisticPlanning.tsp.solving.algorithms.exact
 * Implemented Exact Methods}</h2>
 * <p>
 * Exact optimization methods guarantee to find the globally optimal
 * solution, if given enough time. The following members of this algorithm
 * family have been implemented:
 * </p>
 * <ol>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.exhaustiveEnumeration.ExhaustivePermutationIteration
 * Exhaustive Enumeration} tries to enumerate all possible permutations by
 * using <a href=
 * "http://en.wikipedia.org/wiki/Steinhaus-Johnson-Trotter_algorithm#Even.27s_speedup"
 * >Even's Speedup</a>&nbsp;[<a href="#cite_E1973AC"
 * style="font-weight:bold">1</a>] to a <a href=
 * "http://en.wikipedia.org/wiki/Steinhaus-Johnson-Trotter_algorithm"
 * >Steinhaus-Johnson-Trotter Algorithm</a>&nbsp;[<a
 * href="#cite_J1963GOPBAT" style="font-weight:bold">2</a>, <a
 * href="#cite_S1977PGM" style="font-weight:bold">3</a>].</li>
 * <li>In package
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963
 * branchAndBoundLittle1963}, we implement the
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963.BABLittle1963
 * Branch and Bound} algorithm for traveling salesman as proposed by Little
 * et al.&nbsp;[<a href="#cite_LMSK1963AAFTTSP"
 * style="font-weight:bold">4</a>].</li>
 * <li>We also provide an adaptation of a
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundHeldCarp.BAB_HK
 * Branch-and-Bound algorithm} using Held-Karp boundaries that we found in
 * the internet&nbsp;[<a href="#cite_CI2011OTA"
 * style="font-weight:bold">5</a>]&hellip;</li>
 * </ol>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_E1973AC" /><a
 * href="http://en.wikipedia.org/wiki/Shimon_Even">Shimon Even</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Algorithmic
 * Combinatorics,&rdquo;</span> 1973, New York, NY, USA: Macmillan
 * Publishers Co.. OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/589026">589026</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=AcE-AAAAIAAJ">AcE-AAAAIAAJ</a>;
 * ASIN:&nbsp;<a href="http://www.amazon.com/dp/B00393TCP4">B00393TCP4</a>
 * and&nbsp;<a
 * href="http://www.amazon.com/dp/B000NZSJ8M">B000NZSJ8M</a></div></li>
 * <li><div><span id="cite_J1963GOPBAT" /><a
 * href="http://en.wikipedia.org/wiki/Selmer_M._Johnson">Selmer Martin
 * Johnson</a>: <span style="font-weight:bold">&ldquo;Generation of
 * Permutations by Adjacent Transposition,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Mathematics of
 * Computation</span> 17(83):282&ndash;285, July&nbsp;1963; published by
 * Providence, RI, USA: American Mathematical Society (AMS). doi:&nbsp;<a
 * href
 * ="http://dx.doi.org/10.1090/S0025-5718-1963-0159764-2">10.1090/S0025-
 * 5718-1963-0159764-2</a>; JSTOR stable:&nbsp;<a
 * href="http://www.jstor.org/stable/2003846">2003846</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00255718">0025-5718</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/10886842">1088-6842</a></div></li>
 * <li><div><span id="cite_S1977PGM" /><a
 * href="http://www.cs.princeton.edu/~rs/">Robert Sedgewick</a>: <span
 * style="font-weight:bold">&ldquo;Permutation Generation
 * Methods,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">ACM Computing Surveys
 * (CSUR)</span> 9(2):137&ndash;164, June&nbsp;1977; published by New York,
 * NY, USA: ACM Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1145/356689.356692"
 * >10.1145/356689.356692</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03600300">0360-0300</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=CMSVAN"
 * >CMSVAN</a></div></li>
 * <li><div><span id="cite_LMSK1963AAFTTSP" />John D. C. Little, <a
 * href="http://www-personal.umich.edu/~murty/">Katta G. Murty</a>, Dura W.
 * Sweeny, and&nbsp;Caroline Karel: <span
 * style="font-weight:bold">&ldquo;An Algorithm for the Traveling Salesman
 * Problem,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;07-63, March&nbsp;1, 1963; published by Cambridge, MA, USA:
 * Massachusetts Institute of Technology (MIT), Sloan School of Management.
 * <div>links: [<a href=
 * "http://dspace.mit.edu/bitstream/handle/1721.1/46828/algorithmfortrav00litt.pdf"
 * >1</a>], [<a href="http://hdl.handle.net/1721.1/46828">2</a>],
 * and&nbsp;[<a
 * href="https://github.com/karepker/little-tsp/blob/master/source.pdf"
 * >3</a>]</div></div></li>
 * <li><div><span id="cite_CI2011OTA" /><a
 * href="http://stackoverflow.com/users/908076/comestibles">comestibles</a>
 * and&nbsp;<a
 * href="http://stackoverflow.com/users/270287/ivlad">IVlad</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Optimized TSP
 * Algorithms,&rdquo;</span> (Website), August&nbsp;23, 2011, New York, NY,
 * USA: Stack Exchange Inc., stackoverflow. <div>link: [<a href=
 * "http://stackoverflow.com/questions/7159259/optimized-tsp-algorithms"
 * >1</a>]</div></div></li>
 * </ol>
 */
package org.logisticPlanning.tsp.solving.algorithms.exact;

