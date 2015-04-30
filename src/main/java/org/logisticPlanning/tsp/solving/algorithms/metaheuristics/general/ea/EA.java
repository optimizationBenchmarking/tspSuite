package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithm;
import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.fitness.FitnessIsObjectiveValue;
import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.selection.TruncationSelection;
import org.logisticPlanning.tsp.solving.gpm.GPM;
import org.logisticPlanning.tsp.solving.gpm.IdentityMapping;
import org.logisticPlanning.tsp.solving.operators.BinaryOperator;
import org.logisticPlanning.tsp.solving.operators.BinaryOperatorFollowedByUnary;
import org.logisticPlanning.tsp.solving.operators.NullaryOperator;
import org.logisticPlanning.tsp.solving.operators.UnaryOperator;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <p>
 * An Evolutionary Algorithm (EA)&nbsp;[<a href="#cite_WGOEB"
 * style="font-weight:bold">1</a>, <a href="#cite_CWM2011VOEAFRWA"
 * style="font-weight:bold">2</a>, <a href="#cite_DJ2006ECAUA"
 * style="font-weight:bold">3</a>, <a href="#cite_BFM1997EA"
 * style="font-weight:bold">4</a>, <a href="#cite_SMY2002EO"
 * style="font-weight:bold">5</a>, <a href="#cite_MSY2003CIIC"
 * style="font-weight:bold">6</a>, <a href="#cite_HLY2006EH"
 * style="font-weight:bold">7</a>, <a href="#cite_C1996GA"
 * style="font-weight:bold">8</a>, <a href="#cite_BFM2000EC1BAAO"
 * style="font-weight:bold">9</a>, <a href="#cite_G1989GA"
 * style="font-weight:bold">10</a>, <a href="#cite_DLJD2000EC"
 * style="font-weight:bold">11</a>, <a href="#cite_CCLVV2002EAFSMOP"
 * style="font-weight:bold">12</a>, <a href="#cite_BFM2000EC2BAAO"
 * style="font-weight:bold">13</a>, <a href="#cite_EM1999EC"
 * style="font-weight:bold">14</a>, <a href="#cite_ES2003EAI"
 * style="font-weight:bold">15</a>, <a href="#cite_C2009NIAFO"
 * style="font-weight:bold">16</a>, <a href="#cite_YDBR2008ECIP"
 * style="font-weight:bold">17</a>, <a href="#cite_AJG2005EMOTAAA"
 * style="font-weight:bold">18</a>, <a href="#cite_KCD2008MPSFN"
 * style="font-weight:bold">19</a>, <a href="#cite_COS2000TOHAAT"
 * style="font-weight:bold">20</a>, <a href="#cite_GT2002AIECTAA"
 * style="font-weight:bold">21</a>, <a href="#cite_PT2009BIAFTVRP"
 * style="font-weight:bold">22</a>, <a href="#cite_YSB2008SIEC"
 * style="font-weight:bold">23</a>, <a href="#cite_PV2000CIITN"
 * style="font-weight:bold">24</a>, <a href="#cite_GAI2007HEA"
 * style="font-weight:bold">25</a>, <a href="#cite_B1999EDC"
 * style="font-weight:bold">26</a>, <a href="#cite_C2002ECIEF"
 * style="font-weight:bold">27</a>, <a href="#cite_LLM2007PSIEA"
 * style="font-weight:bold">28</a>, <a href="#cite_YOJ2007ECIDUE"
 * style="font-weight:bold">29</a>, <a href="#cite_CLO2008GAECFIPAA"
 * style="font-weight:bold">30</a>, <a href="#cite_F1965ECTFR"
 * style="font-weight:bold">31</a>, <a href="#cite_KES2001SICA"
 * style="font-weight:bold">32</a>, <a href="#cite_NADMM2006EC"
 * style="font-weight:bold">33</a>, <a href="#cite_M2004EADEADE"
 * style="font-weight:bold">34</a>, <a href="#cite_BA2008MOOICITAP"
 * style="font-weight:bold">35</a>, <a href="#cite_GJ2005ECIDM"
 * style="font-weight:bold">36</a>, <a href="#cite_K2008SAHFEC"
 * style="font-weight:bold">37</a>, <a href="#cite_R2002RFGAEA"
 * style="font-weight:bold">38</a>, <a href="#cite_TB1996EA"
 * style="font-weight:bold">39</a>, <a href="#cite_F2002DMAKDWEA"
 * style="font-weight:bold">40</a>, <a href="#cite_C2000EAIMD"
 * style="font-weight:bold">41</a>, <a href="#cite_D2001EAMO"
 * style="font-weight:bold">42</a>, <a href="#cite_W2002EA"
 * style="font-weight:bold">43</a>, <a href="#cite_M1998GA"
 * style="font-weight:bold">44</a>, <a href="#cite_M1996GADSEP"
 * style="font-weight:bold">45</a>, <a href="#cite_C1995PHOGAA"
 * style="font-weight:bold">46</a>, <a href="#cite_B1987GAASA"
 * style="font-weight:bold">47</a>, <a href="#cite_D1991HOGA"
 * style="font-weight:bold">48</a>, <a href="#cite_C1995PHOGANF"
 * style="font-weight:bold">49</a>, <a href="#cite_C2006ETSOLLGATP"
 * style="font-weight:bold">50</a>, <a href="#cite_CP2000EAAPGA"
 * style="font-weight:bold">51</a>, <a href="#cite_RSM1999EOBGA"
 * style="font-weight:bold">52</a>, <a href="#cite_CHH2001GFSETALOFKB"
 * style="font-weight:bold">53</a>, <a href="#cite_G2006GA"
 * style="font-weight:bold">54</a>, <a href="#cite_BK2005FOLCS"
 * style="font-weight:bold">55</a>, <a href="#cite_C1998PHOGACCS"
 * style="font-weight:bold">56</a>, <a href="#cite_JCS2003HC"
 * style="font-weight:bold">57</a>, <a href="#cite_W1983EOTEOGAICO"
 * style="font-weight:bold">58</a>, <a href="#cite_AD2008CGA"
 * style="font-weight:bold">59</a>, <a href="#cite_H1992AINAASAIAWATBCAIA"
 * style="font-weight:bold">60</a>, <a href="#cite_K2003GAAGPCAS"
 * style="font-weight:bold">61</a>, <a href="#cite_QPPW1997GAAESIEACS"
 * style="font-weight:bold">62</a>, <a href="#cite_H1975GA"
 * style="font-weight:bold">63</a>] that may either run in a
 * (&#956;+&#955;) or (&#956;,&#955;) fashion. An Evolutionary Algorithm is
 * a population-based metaheuristic method. It starts by
 * {@link #createFirstGeneration(Individual[], ObjectiveFunction) creating}
 * {@link #m_lambda &#955;} initial candidate solutions which are usually
 * random with the {@link #m_nullary nullary} search operator.<sup><a
 * href="#gpm">1</a></sup> From the &#955;&gt;&#956; individuals, the best
 * &#956; ones are selected based on their {@link #m_fap fitness}<sup><a
 * href="#fitness">2</a></sup>. These &#956; individuals become the parents
 * of the next &quot;generation&quot;: We use the reproduction operations
 * {@link #m_unary mutation} and {@link #m_binary crossover} to create
 * &quot;offspring&quot; of these parents.
 * </p>
 * <p>
 * {@link org.logisticPlanning.tsp.solving.operators.UnaryOperator
 * Mutation} is a unary search operation: It takes one parent solution and
 * creates one offspring solution from it. The offspring is a slightly
 * modified copy of the parent. If small changes to a solution lead to
 * small changes in its quality&nbsp;[<a href="#cite_R1973ES"
 * style="font-weight:bold">64</a>, <a href="#cite_R1994ES"
 * style="font-weight:bold">65</a>, <a href="#cite_RB1995GPCAUS"
 * style="font-weight:bold">66</a>], then we have a certain chance that the
 * offspring may be better than its parent. (If there is no so-called
 * &quot;strong causality&quot;, then we may as well create random
 * solutions).
 * </p>
 * <p>
 * {@link org.logisticPlanning.tsp.solving.operators.BinaryOperator
 * Crossover}, on the other hand, is a binary search operator: It takes two
 * parent individuals and creates one offspring solution. The basic
 * assumption is that each solution consists of several distinguishable
 * elements, its &quot;genes&quot;, that together are responsible for its
 * (good and bad) characteristics. In a TSP solution in permutation
 * representation, these genes may be the edges that are used in the
 * solution or sub-sequences of nodes/cities. It is known that genes with
 * good influence may be copied more often from one generation to
 * another&nbsp;[<a href="#cite_H1975GA" style="font-weight:bold">63</a>,
 * <a href="#cite_DJ1975GA" style="font-weight:bold">67</a>, <a
 * href="#cite_H1992GA" style="font-weight:bold">68</a>]. The idea behind a
 * binary (crossover) operator is that the two inputs, parent individuals,
 * may have different good genes. If the operator can combine these genes,
 * over the course of evolution, they may aggregate and we would get better
 * and better individuals. This so-called Building Block Hypothesis is
 * still not proven, though&nbsp;[<a href="#cite_G1989GA"
 * style="font-weight:bold">10</a>, <a href="#cite_H1975GA"
 * style="font-weight:bold">63</a>, <a href="#cite_MFH1992RRGAFLGAP"
 * style="font-weight:bold">69</a>], and there also exist alternative
 * hypotheses&nbsp;[<a href="#cite_BS2002ES"
 * style="font-weight:bold">70</a>, <a href="#cite_B1997AAEFTMIWGAO"
 * style="font-weight:bold">71</a>]. In any case, the crossover operation
 * aims at combining different good individuals to obtain even better ones.
 * </p>
 * <p>
 * Anyway, a new set of &#955; individuals are derived from the &#956;
 * parents. Now the process starts all over, i.e., selection takes place
 * and offspring are created from the &#956; survivors. However, there is
 * one small question left open: What happens to the &#956; original
 * parents? In a (&#956;+&#955;)-EA, they are still alive and will compete
 * with the offspring generation for surviving again and becoming parents
 * again. In a (&#956;,&#955;)-EA, however, they die after the offspring
 * have been created. The parameter {@link #m_steady} of our EA
 * implementation decides which of these two things may happen (it is
 * {@code true} for (&#956;+&#955;)-EAs and {@code false} for
 * (&#956;,&#955;) -EAs).
 * </p>
 * <ol>
 * <li id="gpm">Actually, in an EA, way may distinguish genotypes and
 * phenotypes. A phenotype is a candidate solution. In case of a TSP, that
 * is a sequence of nodes to be visited. A genotype can basically be
 * anything which represents instructions how the phenotype should be
 * built. These instructions are translated to phenotypes via the
 * {@link org.logisticPlanning.tsp.solving.gpm.GPM Genotype-Phenotype
 * Mapping} ( {@link #m_gpm GPM}). This is a bit like you being a phenotype
 * and your DNA being your genotype. Often, we will use the direct,
 * permutation-based representation for solutions of the TSP for the
 * search. Then, our search operators work on the same data structures,
 * i.e., {@code int[]} and the GPM becomes the
 * {@link org.logisticPlanning.tsp.solving.gpm.IdentityMapping identity
 * mapping}, i.e., is unnecessary. However, sometimes we may wish to have
 * genotypes that are different data structures. Such a data structure
 * could describe how an existing solution should be changed to get a new
 * one, for instance. Matter of fact, we implement such a GPM in our
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA.DevUpdatingEA
 * developmental updating EA} idea&nbsp;[<a href="#cite_OWDC2013SADAFTSP"
 * style="font-weight:bold">72</a>].</li>
 * <li id="fitness">Traditionally, in EAs,the fitness is the same as the
 * objective function value. The
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * objective function} which returns the total round-trip length of a tour
 * represented as node permutation. This value is subject to minimization.
 * However, we also may consider other metrics for optimization: For
 * instance, we could try to punish very similar tours. In an EA, we hope
 * to have a diverse population&nbsp;[<a href="#cite_PBJ2007OTIOPPOGD"
 * style="font-weight:bold">73</a>, <a href="#cite_OGC1991TSNATPOD"
 * style="font-weight:bold">74</a>, <a href="#cite_R1995GA"
 * style="font-weight:bold">75</a>, <a href="#cite_R1996GA"
 * style="font-weight:bold">76</a>, <a href="#cite_BBM1993AOOGAP1F"
 * style="font-weight:bold">77</a>, <a href="#cite_BT2002EA"
 * style="font-weight:bold">78</a>, <a href="#cite_BT2002EAMIDEA"
 * style="font-weight:bold">79</a>, <a href="#cite_LTDZ2001EA"
 * style="font-weight:bold">80</a>, <a href="#cite_M2004MOEA"
 * style="font-weight:bold">81</a>, <a href="#cite_SSSZ2004EAIM"
 * style="font-weight:bold">82</a>, <a href="#cite_SD2006O"
 * style="font-weight:bold">83</a>, <a href="#cite_DJWP2001RBAPDUMOM"
 * style="font-weight:bold">84</a>, <a href="#cite_G2004AAODIGP"
 * style="font-weight:bold">85</a>, <a href="#cite_GEBK2004PDACGIGP"
 * style="font-weight:bold">86</a>, <a href="#cite_BGKK2003IIDIGPBAAOTEOF"
 * style="font-weight:bold">87</a>, <a href="#cite_BGK2004DIGPAAOMACF"
 * style="font-weight:bold">88</a>, <a href="#cite_BB2002LGPECD"
 * style="font-weight:bold">89</a>]. Then, we may introduce a
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.FitnessAssignmentProcess
 * Fitness Assignment Process} that adds some value to the tour length
 * depending on how similar a given tour is to other tours in the
 * population, for example. If we do not want to do this and use the
 * objective value directly as fitness value, we apply the
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.fitness.FitnessIsObjectiveValue
 * FitnessIsObjectiveValue} process.</li>
 * </ol>
 * <p>
 * This EA implementation has several configurable parameters, such as
 * </p>
 * <ol>
 * <li>the number {@link #m_lambda &#955;} of offspring produced per
 * generation,</li>
 * <li>the number {@link #m_mu &#956;} of parents that survive selection,</li>
 * <li>a {@link #m_steady parameter} deciding whether the parent generation
 * will compete with the offspring generation (&#956;+&#955;) or whether
 * the parents will be discarded (&#956;,&#955;),</li>
 * <li>the {@link #m_selection selection algorithm},</li>
 * <li>a {@link #m_fap fitness assignment process},</li>
 * <li>a {@link #m_gpm genotype-phenotype mapping} (GPM),</li>
 * <li>the {@link #m_cr crossover rate} (the mutation rate is
 * {@code 1-(crossover rate)}), as well as</li>
 * <li>the {@link #m_nullary creation},</li>
 * <li>{@link #m_unary mutation}, and</li>
 * <li>{@link #m_binary} crossover operation.</li>
 * </ol>
 * <p>
 * Additionally, this EA implementation can easily be specialized to a
 * Memetic Algorithm (MA)&nbsp;[<a href="#cite_M1989MA"
 * style="font-weight:bold">90</a>, <a href="#cite_M2002MA"
 * style="font-weight:bold">91</a>, <a href="#cite_MC2003AGITMA"
 * style="font-weight:bold">92</a>, <a href="#cite_ES2003HWOTMA"
 * style="font-weight:bold">93</a>, <a href="#cite_HKS2005RAIMA"
 * style="font-weight:bold">94</a>, <a href="#cite_DM2004MA"
 * style="font-weight:bold">95</a>, <a href="#cite_RS1994FMA"
 * style="font-weight:bold">96</a>], which we do, e.g., in the classes
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSSavingsMA
 * HeuristicInitVNSSavingsMA} (by using a special {@link #m_unary unary
 * search operation} and
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ma.PermutationMA
 * PermutationMA} (by overriding the method
 * {@link #complete(Individual, ObjectiveFunction, GPM)}). The
 * {@link #m_gpm} can be used to implement developmental approaches such as
 * the one we provide in
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA.DevUpdatingEA
 * DevUpdatingEA}&nbsp;[<a href="#cite_OWDC2013SADAFTSP"
 * style="font-weight:bold">72</a>]. By overriding the method
 * {@link #createFirstGeneration(Individual[], ObjectiveFunction)}, the
 * first generation of the EA can be generated in a specialized way, maybe
 * by using
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.TSPHeuristic
 * heuristic methods} to derive the initial individuals, which is what we
 * do in
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSSavingsMA
 * HeuristicInitVNSSavingsMA}, for instance.
 * </p>
 * <p>
 * Starting at version 0.9.8 of TSP Suite, we provide run-depending
 * initialization and finalization support for algorithms and algorithm
 * modules. The new class
 * {@link org.logisticPlanning.tsp.solving.TSPModule} is introduced as
 * base-class for all algorithm modules (such as
 * {@link org.logisticPlanning.tsp.solving.operators.Operator search
 * operators} or {@link org.logisticPlanning.tsp.solving.gpm.GPM
 * genotype-phenotype mappings}) and the
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm TSP algorithms}
 * themselves. This new class provides two methods,
 * {@link org.logisticPlanning.tsp.solving.TSPModule#beginRun(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * and
 * {@link org.logisticPlanning.tsp.solving.TSPModule#endRun(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * which must be called before and after a run, respectively. Each
 * algorithm module must then invoke them recursively for all of its
 * sub-components. The new method
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm#call(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * now acts as a wrapper for
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm#solve(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * and invokes them. This allows for a more targeted allocation and
 * de-allocation of data structures for each run.
 * </p>
 * <p>
 * This new mechanism is used to initialize and de-initialize the
 * {@link #getNullaryOperator() nullary}, {@link #getUnaryOperator() unary}
 * , and {@link #getBinaryOperator() binary} search operator as well as the
 * {@link #getGPM() gpm}, {@link #getFitnessAssignmentProcess() fitness
 * assignment process}, and {@link #getSelectionAlgorithm() selection
 * algorithm} .
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_WGOEB" /><a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>]: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Global
 * Optimization Algorithms &#8210; Theory and Application,&rdquo;</span>
 * 2009, Germany: it-weise.de (self-published). <div>link: [<a
 * href="http://www.it-weise.de/projects/book.pdf">1</a>]</div></div></li>
 * <li><div><span id="cite_CWM2011VOEAFRWA" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Variants of
 * Evolutionary Algorithms for Real-World Applications,&rdquo;</span>
 * September&nbsp;30, 2011&ndash;2012, <a
 * href="http://www.swinburne.edu.my/iSECURES/index.php?do=rchiong">Raymond
 * Chiong</a>, <a href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>], and&nbsp;<a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>,
 * editors, Berlin/Heidelberg: Springer-Verlag. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783642234231">978-3-642-23423-1</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783642234248">978-3-642-
 * 23424-8</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2011935740">2011935740</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/978-3-642-23424-8">10.1007/978-3-
 * 642-23424-8</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=B2ONePP40MEC">B2ONePP40MEC</a>;
 * further information: [<a
 * href="http://www.it-weise.de/documents/files/ea-app-book/index.html"
 * >1</a>]</div></li>
 * <li><div><span id="cite_DJ2006ECAUA" /><a
 * href="http://cs.gmu.edu/~kdejong/">Kenneth Alan De Jong</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evolutionary
 * Computation: A Unified Approach,&rdquo;</span> February&nbsp;2006,
 * volume 4 of Complex Adaptive Systems, Bradford Books, Cambridge, MA,
 * USA: MIT Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/8120330021">8120330021</a>, <a
 * href="https://www.worldcat.org/isbn/0262041944">0262041944</a>, <a
 * href="https://www.worldcat.org/isbn/9780262041942">978-0262041942</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9788120330023">978-8120330023</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/276452339">276452339</a>, <a
 * href="https://www.worldcat.org/oclc/46500047">46500047</a>, and&nbsp;<a
 * href="https://www.worldcat.org/oclc/182530408">182530408</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=OIRQAAAAMAAJ">OIRQAAAAMAAJ</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10894365">1089-4365</a>. <div>link:
 * [<a href=
 * "http://www.inf.ufg.br/~telma/topicoscomputacaoevolutiva/EvolutionaryComputation.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_BFM1997EA" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Handbook of
 * Evolutionary Computation,&rdquo;</span> January&nbsp;1, 1997, <a
 * href="http://www.liacs.nl/~baeck/contact.htm">Thomas B&#228;ck</a>, <a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>, and&nbsp;<a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>,
 * editors, Computational Intelligence Library, New York, NY, USA: Oxford
 * University Press, Inc., Dirac House, Temple Back, Bristol, UK: Institute
 * of Physics Publishing Ltd. (IOP), and&nbsp;Boca Raton, FL, USA: CRC
 * Press, Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0750308958">0-7503-0895-8</a>, <a
 * href="https://www.worldcat.org/isbn/0750303921">0-7503-0392-1</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9780750308953">978-0-7503-0895-3</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780750303927">978-0-7503
 * -0392-7</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/97004461">97004461</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/327018351">327018351</a>, <a
 * href="https://www.worldcat.org/oclc/173074676">173074676</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/327018351">327018351</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=n5nuiIZvmpAC">n5nuiIZvmpAC</a>,
 * <a
 * href="http://books.google.com/books?id=neKNGAAACAAJ">neKNGAAACAAJ</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=kgqGQgAACAAJ">kgqGQgAACAAJ</a>;
 * PPN:&nbsp;<a href="http://gso.gbv.de/PPNSET?PPN=224708430">224708430</a>
 * and&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=364589272">364589272</a></div></li>
 * <li><div><span id="cite_SMY2002EO" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evolutionary
 * Optimization,&rdquo;</span> 2002, <a
 * href="http://seit.unsw.adfa.edu.au/staff/sites/rsarker/">Ruhul Amin
 * Sarker</a>, <a href=
 * "http://www.canberra.edu.au/faculties/ise/research/staff/masoud-mohammadian"
 * >Masoud Mohammadian</a>, and&nbsp;<a
 * href="http://www.cs.bham.ac.uk/~xin/">Xin Yao</a> <span
 * style="color:gray">[&#23002;&#26032;</span>], editors, volume 48 of
 * International Series in Operations Research &amp; Management Science,
 * Norwell, MA, USA: Kluwer Academic Publishers, Dordrecht, Netherlands:
 * Springer Netherlands, and&nbsp;Boston, MA, USA: Springer US.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0306480417">0-306-48041-7</a>, <a
 * href="https://www.worldcat.org/isbn/0792376544">0-7923-7654-4</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780792376545">978-0-7923
 * -7654-5</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/b101816">10.1007/b101816</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/08848289">0884-8289</a></div></li>
 * <li><div><span id="cite_MSY2003CIIC" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Computational
 * Intelligence in Control,&rdquo;</span> 2003, <a href=
 * "http://www.canberra.edu.au/faculties/ise/research/staff/masoud-mohammadian"
 * >Masoud Mohammadian</a>, <a
 * href="http://seit.unsw.adfa.edu.au/staff/sites/rsarker/">Ruhul Amin
 * Sarker</a>, and&nbsp;<a href="http://www.cs.bham.ac.uk/~xin/">Xin
 * Yao</a> <span style="color:gray">[&#23002;&#26032;</span>], editors, New
 * York, NY, USA: Idea Group Publishing (Idea Group Inc., IGI Global).
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1591400376">1591400376</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781591400370">9781591400370</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=N3m6XYXL5K0C"
 * >N3m6XYXL5K0C</a></div></li>
 * <li><div><span id="cite_HLY2006EH" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evolvable
 * Hardware,&rdquo;</span> 2006, Takahide Higuchi, Yong Liu, and&nbsp;<a
 * href="http://www.cs.bham.ac.uk/~xin/">Xin Yao</a> <span
 * style="color:gray">[&#23002;&#26032;</span>], editors, volume 11 of
 * Genetic Algorithms and Evolutionary Computation, Boston, MA, USA:
 * Springer US and&nbsp;Norwell, MA, USA: Kluwer Academic Publishers.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0387243860">0387243860</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780387243863">9780387243863</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=0Y1QAAAAMAAJ">0Y1QAAAAMAAJ</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/19320167">1932-0167</a></div></li>
 * <li><div><span id="cite_C1996GA" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Genetic Algorithm
 * and its Application (Y&#237;chu&#225;n Su&#224;nf&#462; J&#237; Q&#237;
 * Y&#236;ngy&#242;ng),&rdquo;</span> 1996&ndash;2001, <a
 * href="http://cs.ustc.edu.cn/szdw/ys/201006/t20100614_21994.html"
 * >Guoliang Chen</a> <span
 * style="color:gray">[&#38472;&#22269;&#33391;</span>], editor, National
 * High-Tech Key Books: Communications Technology (Qu&#225;ngu&#243;
 * G&#257;o J&#236;sh&#249; Zh&#242;ngdi&#462;n T&#250;sh&#363;:
 * T&#333;ngx&#236;n J&#236;sh&#249; L&#464;ngy&#249;), Beijing, China:
 * Post &amp; Telecom Press (PTPress, R&#233;nm&#237;n Y&#243;udi&#224;n
 * Ch&#363;b&#462;n Sh&#232;) <span
 * style="color:gray">[&#20154;&#27665;&#37038;&
 * #30005;&#20986;&#29256;&#31038;</span>]. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/7115059640">7115059640</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9787115059642">9787115059642</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/300052154">300052154</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=qmNoAAAACAAJ">qmNoAAAACAAJ
 * </a></div></li>
 * <li><div><span id="cite_BFM2000EC1BAAO" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evolutionary
 * Computation 1: Basic Algorithms and Operators,&rdquo;</span>
 * January&nbsp;2000, <a
 * href="http://www.liacs.nl/~baeck/contact.htm">Thomas B&#228;ck</a>, <a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>, and&nbsp;<a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>,
 * editors, Dirac House, Temple Back, Bristol, UK: Institute of Physics
 * Publishing Ltd. (IOP). ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0750306645">0750306645</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780750306645">9780750306645</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=4HMYCq9US78C"
 * >4HMYCq9US78C</a></div></li>
 * <li><div><span id="cite_G1989GA" /><a
 * href="http://www.davidegoldberg.com/de-goldberg.html">David Edward
 * Goldberg</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Genetic Algorithms
 * in Search, Optimization, and Machine Learning,&rdquo;</span> 1989,
 * Boston, MA, USA: Addison-Wesley Longman Publishing Co., Inc..
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0201157675">0-201-15767-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780201157673">978-0-201-
 * 15767-3</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/88006276">88006276</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/299467773">299467773</a>, <a
 * href="https://www.worldcat.org/oclc/255664278">255664278</a>, <a
 * href="https://www.worldcat.org/oclc/246916144">246916144</a>,
 * and&nbsp;<a href="https://www.worldcat.org/oclc/17674450">17674450</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=2IIJAAAACAAJ">2IIJAAAACAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=3_RQAAAAMAAJ">3_RQAAAAMAAJ</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=bO9SAAAACAAJ">bO9SAAAACAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=250764105">250764105</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=301364087">301364087</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=357619773">357619773</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=357663551">357663551</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=478167571">478167571</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=499485440">499485440</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=558515053">558515053</a>, and&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=62589894X">62589894X</a></div></li>
 * <li><div><span id="cite_DLJD2000EC" /><a
 * href="http://www.coneural.org/dumitrescu/">Dumitru (Dan) Dumitrescu</a>,
 * <a href="http://info.iet.unipi.it/~lazzerini/">Beatrice Lazzerini</a>,
 * <a href=
 * "http://www.unisanet.unisa.edu.au/staff/Homepage.asp?Name=lakhmi.jain"
 * >Lakhmi C. Jain</a>, and&nbsp;A. Dumitrescu: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evolutionary
 * Computation,&rdquo;</span> June&nbsp;2000, volume 18 of International
 * Series on Computational Intelligence, Boca Raton, FL, USA: CRC Press,
 * Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0849305888">0-8493-0588-8</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780849305887">978-0-8493
 * -0588-7</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/00030348">00030348</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/43894173">43894173</a>, <a
 * href="https://www.worldcat.org/oclc/247021679">247021679</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/468482770">468482770</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=MSU9ep79JvUC">MSU9ep79JvUC
 * </a></div></li>
 * <li><div><span id="cite_CCLVV2002EAFSMOP" /><a
 * href="http://delta.cs.cinvestav.mx/~ccoello/">Carlos Artemio Coello
 * Coello</a>, Gary B. Lamont, and&nbsp;David A. van Veldhuizen: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evolutionary
 * Algorithms for Solving Multi-Objective Problems,&rdquo;</span>
 * 2002&ndash;2007, volume 5 of Genetic Algorithms and Evolutionary
 * Computation, Boston, MA, USA: Springer US and&nbsp;Norwell, MA, USA:
 * Kluwer Academic Publishers. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0387332545">0387332545</a>, <a
 * href="https://www.worldcat.org/isbn/0306467623">0306467623</a>, <a
 * href="https://www.worldcat.org/isbn/9780387367972"
 * >978-0-387-36797-2</a>, <a
 * href="https://www.worldcat.org/isbn/9780387332543"
 * >978-0-387-33254-3</a>, and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780306467622">978-0306467622</a>;
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/978-0-387-36797-2">10.1007/978-0-
 * 387-36797-2</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=sgX_Cst_yTsC">sgX_Cst_yTsC</a>,
 * <a
 * href="http://books.google.com/books?id=rXIuAMw3lGAC">rXIuAMw3lGAC</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=_NZdOgAACAAJ">_NZdOgAACAAJ</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/19320167">1932-0167</a></div></li>
 * <li><div><span id="cite_BFM2000EC2BAAO" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evolutionary
 * Computation 2: Advanced Algorithms and Operators,&rdquo;</span>
 * November&nbsp;2000, <a
 * href="http://www.liacs.nl/~baeck/contact.htm">Thomas B&#228;ck</a>, <a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>, and&nbsp;<a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>,
 * editors, Dirac House, Temple Back, Bristol, UK: Institute of Physics
 * Publishing Ltd. (IOP). ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0750306653">0750306653</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780750306652">9780750306652
 * </a></div></li>
 * <li><div><span id="cite_EM1999EC" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evolutionary
 * Computation,&rdquo;</span> 1999, <a
 * href="http://www.cs.vu.nl/~gusz/">&#193;goston E. Eiben</a> aka.
 * Gusz/Guszti, editor, Theoretical Computer Science, Amsterdam, The
 * Netherlands: IOS Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/4274902692">4-274-90269-2</a>, <a
 * href="https://www.worldcat.org/isbn/9051994710">90-5199-471-0</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9789051994711">978-90-5199-471-1</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9784274902697">978-4-274-
 * 90269-7</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=8LVAGQAACAAJ"
 * >8LVAGQAACAAJ</a></div></li>
 * <li><div><span id="cite_ES2003EAI" /><a
 * href="http://www.cs.vu.nl/~gusz/">&#193;goston E. Eiben</a> aka.
 * Gusz/Guszti and&nbsp;<a href="http://www.cems.uwe.ac.uk/~jsmith/">James
 * E. Smith</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Introduction to
 * Evolutionary Computing,&rdquo;</span> November&nbsp;2003, Natural
 * Computing Series, New York, NY, USA: Springer New York. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540401849">3540401849</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540401841">978-3540401841</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=7IOE5VIpFpwC">7IOE5VIpFpwC</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=RRKo9xVFW_QC">RRKo9xVFW_QC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/16197127">1619-7127</a></div></li>
 * <li><div><span id="cite_C2009NIAFO" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Nature-Inspired
 * Algorithms for Optimisation,&rdquo;</span> April&nbsp;30, 2009, <a
 * href="http://www.swinburne.edu.my/iSECURES/index.php?do=rchiong">Raymond
 * Chiong</a>, editor, volume 193/2009 of Studies in Computational
 * Intelligence, Berlin/Heidelberg: Springer-Verlag. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3642002668">3-642-00266-8</a>, <a
 * href="https://www.worldcat.org/isbn/3642002676">3-642-00267-6</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9783642002663">978-3-642-00266-3</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783642002670">978-3-642-
 * 00267-0</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2009920517">2009920517</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/978-3-642-00267-0">10.1007/978-3-
 * 642-00267-0</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/423750494">423750494</a>, <a
 * href="https://www.worldcat.org/oclc/400530826">400530826</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/405547847">405547847</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=557PPQbkfNYC">557PPQbkfNYC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1860949X">1860-949X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18609503">1860-9503</a>; further
 * information: [<a href=
 * "http://www.springer.com/engineering/mathematical/book/978-3-642-00266-3"
 * >1</a>]</div></li>
 * <li><div><span id="cite_YDBR2008ECIP" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evolutionary
 * Computation in Practice,&rdquo;</span> 2008, <a
 * href="http://www.cs.mun.ca/~tinayu/Home.html">Gwoing Tina Yu</a>,
 * Lawrence Davis, Cem M. Baydar, and&nbsp;Rajkumar Roy, editors, volume
 * 88/2008 of Studies in Computational Intelligence, Berlin/Heidelberg:
 * Springer-Verlag. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540757702">978-3-540-75770-2</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540757719">978-3-540-
 * 75771-9</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2007940149">2007940149</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/978-3-540-75771-9">10.1007/978-3-
 * 540-75771-9</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=om-uG-vk6GgC">om-uG-vk6GgC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1860949X">1860-949X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18609503">1860-9503</a></div></li>
 * <li><div><span id="cite_AJG2005EMOTAAA" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evolutionary
 * Multiobjective Optimization &#8210; Theoretical Advances and
 * Applications,&rdquo;</span>, <a
 * href="http://www.softcomputing.net/">Ajith Abraham</a>, <a href=
 * "http://www.unisanet.unisa.edu.au/staff/Homepage.asp?Name=lakhmi.jain"
 * >Lakhmi C. Jain</a>, and&nbsp;Robert Goldberg, editors, Advanced
 * Information and Knowledge Processing, Berlin, Germany: Springer-Verlag
 * GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1852337877">1852337877</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781852337872">978-1-85233
 * -787-2</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=Ei7q1YSjiSAC"
 * >Ei7q1YSjiSAC</a></div></li>
 * <li><div><span id="cite_KCD2008MPSFN" /><a
 * href="http://www.cs.man.ac.uk/~jknowles/">Joshua D. Knowles</a>, <a
 * href=
 * "http://www.macs.hw.ac.uk/staff-directory/david-wolfe-corne.htm">David
 * Wolfe Corne</a>, and&nbsp;<a
 * href="http://www.iitk.ac.in/kangal/deb.shtml">Kalyanmoy Deb</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Multiobjective
 * Problem Solving from Nature &#8210; From Concepts to
 * Applications,&rdquo;</span> 2008, Natural Computing Series, New York,
 * NY, USA: Springer New York. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540729631">3-540-72963-1</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9783540729631">978-3-540-72963-1</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540729648">978-3-540-
 * 72964-8</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2007936865">2007936865</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/978-3-540-72964-8">10.1007/978-3-
 * 540-72964-8</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/315697103">315697103</a>, <a
 * href="https://www.worldcat.org/oclc/300164898">300164898</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/255894619">255894619</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=pzq8t9rCKC8C">pzq8t9rCKC8C</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/16197127">1619-7127</a></div></li>
 * <li><div><span id="cite_COS2000TOHAAT" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Telecommunications
 * Optimization: Heuristic and Adaptive Techniques,&rdquo;</span>
 * September&nbsp;2000, <a
 * href="http://www.macs.hw.ac.uk/staff-directory/david-wolfe-corne.htm"
 * >David Wolfe Corne</a>, Martin J. Oates, and&nbsp;George D. Smith,
 * editors, New York, NY, USA: John Wiley &amp; Sons Ltd.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0471988553">0471988553</a>, <a
 * href="https://www.worldcat.org/isbn/047084163X">047084163X</a>, <a
 * href="https://www.worldcat.org/isbn/9780470841631">9780470841631</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780471988557">9780471988557</a>;
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1002/047084163X">10.1002/047084163X</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/50745182">50745182</a>, <a
 * href="https://www.worldcat.org/oclc/123099960">123099960</a>, <a
 * href="https://www.worldcat.org/oclc/43903625">43903625</a>, and&nbsp;<a
 * href="https://www.worldcat.org/oclc/43187002">43187002</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=EgNTAAAAMAAJ">EgNTAAAAMAAJ
 * </a></div></li>
 * <li><div><span id="cite_GT2002AIECTAA" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Advances in
 * Evolutionary Computing &#8210; Theory and Applications,&rdquo;</span>
 * November&nbsp;22, 2002, Ashish Ghosh and&nbsp;<a
 * href="http://www2.hannan-u.ac.jp/~tsutsui/index-e.html">Shigeyoshi
 * Tsutsui</a> <span style="color:gray">[&#31570;&#20117;
 * &#33538;&#32681;</span>], editors, Natural Computing Series, New York,
 * NY, USA: Springer New York. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540433309">3-540-43330-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540433309">978-3-540-
 * 43330-9</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2002029164">2002029164</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/50207987">50207987</a>, <a
 * href="https://www.worldcat.org/oclc/314328282">314328282</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/249607003">249607003</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=OGMEMC9P3vMC">OGMEMC9P3vMC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/16197127">1619-7127</a>;
 * PPN:&nbsp;<a href="http://gso.gbv.de/PPNSET?PPN=35332180X">35332180X</a>
 * and&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=358205859">358205859</a></div></li>
 * <li><div><span id="cite_PT2009BIAFTVRP" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Bio-inspired
 * Algorithms for the Vehicle Routing Problem,&rdquo;</span> 2009, <a href=
 * "http://www.researchgate.net/profile/Francisco_Pereira3/publications/"
 * >Francisco Jose Baptista Pereira</a> and&nbsp;<a
 * href="http://jorgetavares.com/tag/evolutionary-computation/">Jorge
 * Tavares</a>, editors, volume 161 of Studies in Computational
 * Intelligence, Berlin/Heidelberg: Springer-Verlag. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540851518">3-540-85151-8</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9783540851516">978-3-540-85151-6</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540851523">978-3-540-
 * 85152-3</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2008933225">2008933225</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/978-3-540-85152-3">10.1007/978-3-
 * 540-85152-3</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1860949X">1860-949X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18609503">1860-9503</a></div></li>
 * <li><div><span id="cite_YSB2008SIEC" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Success in
 * Evolutionary Computation,&rdquo;</span> January&nbsp;16, 2008, Ang Yang,
 * Yin Shan, and&nbsp;Lam Thu Bui, editors, volume 92/2008 of Studies in
 * Computational Intelligence, Berlin/Heidelberg: Springer-Verlag.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/354076285X">3-540-76285-X</a>, <a
 * href="https://www.worldcat.org/isbn/3540762868">3-540-76286-8</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9783540762867">978-3-540-76286-7</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540762850">978-3-540-
 * 76285-0</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2007939404">2007939404</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/978-3-540-76286-7">10.1007/978-3-
 * 540-76286-7</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/261325352">261325352</a>, <a
 * href="https://www.worldcat.org/oclc/300248166">300248166</a>, <a
 * href="https://www.worldcat.org/oclc/244024657">244024657</a>, <a
 * href="https://www.worldcat.org/oclc/244040858">244040858</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/181090645">181090645</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=wytCwGYoAowC">wytCwGYoAowC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1860949X">1860-949X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18609503">1860-9503</a></div></li>
 * <li><div><span id="cite_PV2000CIITN" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Computational
 * Intelligence in Telecommunications Networks,&rdquo;</span>
 * September&nbsp;15, 2000, Witold Pedrycz and&nbsp;Athanasios V.
 * Vasilakos, editors, Boca Raton, FL, USA: CRC Press, Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/084931075X">084931075X</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780849310751">9780849310751</a>;
 * OCLC:&nbsp;<a href="https://www.worldcat.org/oclc/43894172">43894172</a>
 * and&nbsp;<a href="https://www.worldcat.org/oclc/55103484">55103484</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=dE6d-jPoDEEC">dE6d-jPoDEEC</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=7lVj5pqSxXIC">7lVj5pqSxXIC
 * </a></div></li>
 * <li><div><span id="cite_GAI2007HEA" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Hybrid
 * Evolutionary Algorithms,&rdquo;</span> 2007, Crina Grosan, <a
 * href="http://www.softcomputing.net/">Ajith Abraham</a>, and&nbsp;<a
 * href=
 * "http://www.ie.osakafu-u.ac.jp/~hisaoi/ci_lab_e/personal/ishibuchi/"
 * >Hisao Ishibuchi</a>, editors, volume 75/2007 of Studies in
 * Computational Intelligence, Berlin/Heidelberg: Springer-Verlag.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540732969">3-540-73296-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540732969">978-3-540-
 * 73296-9</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2007932399">2007932399</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/978-3-540-73297-6">10.1007/978-3-
 * 540-73297-6</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=II7LCqiGFlEC">II7LCqiGFlEC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1860949X">1860-949X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18609503">1860-9503</a></div></li>
 * <li><div><span id="cite_B1999EDC" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evolutionary
 * Design by Computers,&rdquo;</span> May&nbsp;1999, <a
 * href="http://peterjbentley.com/">Peter John Bentley</a>, editor, San
 * Francisco, CA, USA: Morgan Kaufmann Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/155860605X">1-55860-605-X</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558606050">9781558606050</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=EgC6LBAH5r8C">EgC6LBAH5r8C</a>;
 * further information: [<a
 * href="http://www.cs.ucl.ac.uk/staff/P.Bentley/evdes.html">1</a>]</div></li>
 * <li><div><span id="cite_C2002ECIEF" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evolutionary
 * Computation in Economics and Finance,&rdquo;</span> August&nbsp;5, 2002,
 * Shu-Heng Chen, editor, volume 100 of Studies in Fuzziness and Soft
 * Computing, Berlin, Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3790814768">3790814768</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/14349922">1434-9922</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18600808">1860-0808</a></div></li>
 * <li><div><span id="cite_LLM2007PSIEA" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Parameter Setting
 * in Evolutionary Algorithms,&rdquo;</span> March&nbsp;2007, <a
 * href="http://w3.ualg.pt/~flobo/">Fernando G. Lobo</a>, <a
 * href="http://uk.linkedin.com/in/cflima">Cl&#225;udio F. Lima</a>,
 * and&nbsp;<a href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew
 * Michalewicz</a>, editors, volume 54 of Studies in Computational
 * Intelligence, Berlin/Heidelberg: Springer-Verlag. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540694315">3-540-69431-5</a>, <a
 * href="https://www.worldcat.org/isbn/3540694323">3-540-69432-3</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9783540694311">978-3-540-69431-1</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540694328">978-3-540-
 * 69432-8</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2006939345">2006939345</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/978-3-540-69432-8">10.1007/978-3-
 * 540-69432-8</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=vfjLsGBmi7kC">vfjLsGBmi7kC</a>,
 * <a
 * href="http://books.google.com/books?id=voAuHwAACAAJ">voAuHwAACAAJ</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=WgMmGQAACAAJ">WgMmGQAACAAJ</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1860949X">1860-949X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18609503">1860-9503</a></div></li>
 * <li><div><span id="cite_YOJ2007ECIDUE" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evolutionary
 * Computation in Dynamic and Uncertain Environments,&rdquo;</span> 2007,
 * <a href="http://www.cs.le.ac.uk/people/sy11/">Shengxiang Yang</a>, <a
 * href="http://www.ntu.edu.sg/home/asysong/">Yew-Soon Ong</a> <span
 * style="color:gray">[&#29579;&#21451;&#39034;</span>], and&nbsp;Yaochu
 * Jin <span style="color:gray">[&#37329;&#32768;&#21021;</span>], editors,
 * volume 51/2007 of Studies in Computational Intelligence,
 * Berlin/Heidelberg: Springer-Verlag. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540497722">3-540-49772-2</a>, <a
 * href="https://www.worldcat.org/isbn/3540497749">3-540-49774-9</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9783540497721">978-3-540-49772-1</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540497745">978-3-540-
 * 49774-5</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/180965995">180965995</a>, <a
 * href="https://www.worldcat.org/oclc/77013308">77013308</a>, and&nbsp;<a
 * href="https://www.worldcat.org/oclc/318293101">318293101</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=ciktAAAACAAJ">ciktAAAACAAJ</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=8w_HGQAACAAJ">8w_HGQAACAAJ</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1860949X">1860-949X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18609503">1860-9503</a>. <div>partly
 * available: [<a
 * href="http://www.soft-computing.de/Jin_CEC04T.pdf.gz">1</a>]</div></div>
 * </li>
 * <li><div><span id="cite_CLO2008GAECFIPAA" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Genetic and
 * Evolutionary Computation for Image Processing and
 * Analysis,&rdquo;</span> February&nbsp;2008, <a
 * href="http://www.ce.unipr.it/people/cagnoni/">Stefano Cagnoni</a>, <a
 * href="http://evelyne.lutton.free.fr/">Evelyne Lutton</a>,
 * and&nbsp;Gustavo Olague, editors, volume 8 of EURASIP Book Series on
 * Signal Processing and Communications, New York, NY, USA: Hindawi
 * Publishing Corporation. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9789774540011"
 * >978-977-454-001-1</a>. <div>link: [<a
 * href="http://www.hindawi.com/books/9789774540011.pdf">1</a>]</div></div>
 * </li>
 * <li><div><span id="cite_F1965ECTFR" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evolutionary
 * Computation: The Fossil Record,&rdquo;</span> May&nbsp;1998, <a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>, editor, Los Alamitos, CA, USA: IEEE Computer Society Press
 * and&nbsp;Chichester, West Sussex, UK: Wiley Interscience. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780334817">0-7803-3481-7</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780334816">978-0-7803
 * -3481-6</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/38270557">38270557</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=ahYLAAAACAAJ">ahYLAAAACAAJ
 * </a></div></li>
 * <li><div><span id="cite_KES2001SICA" />James Kennedy and&nbsp;Russel C.
 * Eberhart: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Swarm
 * Intelligence: Collective, Adaptive,&rdquo;</span> 2001, <a href=
 * "http://www.xjtlu.edu.cn/en/faculty/academic-subject-staff/item/102-yuhui-shi.html"
 * >Yuhui Shi</a> <span
 * style="color:gray">[&#21490;&#29577;&#22238;</span>] and&nbsp;San
 * Francisco, CA, USA: Morgan Kaufmann Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558605959">1558605959</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558605954">9781558605954</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=pHku3gYfL2UC">pHku3gYfL2UC</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=vOx-QV3sRQsC">vOx-QV3sRQsC</a>.
 * <div>partly available: [<a
 * href="http://www.engr.iupui.edu/~eberhart/web/PSObook.html"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_NADMM2006EC" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Parallel
 * Evolutionary Computations,&rdquo;</span> June&nbsp;2006, <a
 * href="http://www.eng.uerj.br/~nadia/english.html">Nadia Nedjah</a>, <a
 * href="http://www.lcc.uma.es/~eat/">Enrique Alba Torres</a>,
 * and&nbsp;Luiza de Macedo Mourelle, editors, volume 22/2006 of Studies in
 * Computational Intelligence, Berlin/Heidelberg: Springer-Verlag.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540328378">3-540-32837-8</a>, <a
 * href="https://www.worldcat.org/isbn/3540328394">3-540-32839-4</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9783540328377">978-3-540-32837-7</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540328391">978-3-540-
 * 32839-1</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2006921792">2006921792</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-32839-4"
 * >10.1007/3-540-32839-4</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=J36LHQAACAAJ">J36LHQAACAAJ</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=kOC8GAAACAAJ">kOC8GAAACAAJ</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1860949X">1860-949X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18609503">1860-9503</a></div></li>
 * <li><div><span id="cite_M2004EADEADE" /><a
 * href="http://www.revolutionaryengineering.com/custom.html">Ronald Walter
 * Morrison</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Designing
 * Evolutionary Algorithms for Dynamic Environments,&rdquo;</span>
 * June&nbsp;2004, volume 24 of Natural Computing Series, New York, NY,
 * USA: Springer New York. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540212310">3-540-21231-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540212317">978-3-540-
 * 21231-7</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2004102479">2004102479</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/55746331">55746331</a> and&nbsp;<a
 * href="https://www.worldcat.org/oclc/57168232">57168232</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=EaNkNDYq_8">EaNkNDYq_8</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/16197127">1619-7127</a></div></li>
 * <li><div><span id="cite_BA2008MOOICITAP" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Multi-Objective
 * Optimization in Computational Intelligence: Theory and
 * Practice,&rdquo;</span> March&nbsp;30, 2008, Lam Thu Bui and&nbsp;Sameer
 * Alam, editors, Premier Reference Source, New York, NY, USA: Idea Group
 * Publishing (Idea Group Inc., IGI Global). ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1599044986">1599044986</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781599044989">978-1599044989
 * </a></div></li>
 * <li><div><span id="cite_GJ2005ECIDM" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evolutionary
 * Computation in Data Mining,&rdquo;</span> 2005, Ashish Ghosh and&nbsp;<a
 * href =
 * "http://www.unisanet.unisa.edu.au/staff/Homepage.asp?Name=lakhmi.jain">
 * Lakhmi C. Jain</a>, editors, volume 163/2005 of Studies in Fuzziness and
 * Soft Computing, Berlin, Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540223703">3-540-22370-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540223702">978-3-540-
 * 22370-2</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2004111009">2004111009</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-32358-9"
 * >10.1007/3-540-32358-9</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/320964275">320964275</a>, <a
 * href="https://www.worldcat.org/oclc/249236978">249236978</a>, <a
 * href="https://www.worldcat.org/oclc/318293100">318293100</a>,
 * and&nbsp;<a href="https://www.worldcat.org/oclc/56658773">56658773</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=Caevy3rrjPUC">Caevy3rrjPUC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/14349922">1434-9922</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18600808">1860-0808</a></div></li>
 * <li><div><span id="cite_K2008SAHFEC" />Oliver Kramer: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Self-Adaptive
 * Heuristics for Evolutionary Computation,&rdquo;</span> July&nbsp;2008,
 * volume 147 of Studies in Computational Intelligence, Berlin/Heidelberg:
 * Springer-Verlag. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540692800">3-540-69280-0</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9783540692805">978-3-540-69280-5</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540692812">978-3-540-
 * 69281-2</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2008928449">2008928449</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/978-3-540-69281-2">10.1007/978-3-
 * 540-69281-2</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/233933242">233933242</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=f4QwC_AJEjwC">f4QwC_AJEjwC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1860949X">1860-949X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18609503">1860-9503</a></div></li>
 * <li><div><span id="cite_R2002RFGAEA" /><a
 * href="http://wi.bwl.uni-mainz.de/rothlauf.html.de">Franz Rothlauf</a>:
 * <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Representations
 * for Genetic and Evolutionary Algorithms,&rdquo;</span> 2002&ndash;2006,
 * volume 104 of Studies in Fuzziness and Soft Computing, Heidelberg,
 * Germany: Physica-Verlag GmbH &amp; Co. and&nbsp;Berlin, Germany:
 * Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/354025059X">3-540-25059-X</a>, <a
 * href="https://www.worldcat.org/isbn/3790814962">3790814962</a>, <a
 * href="https://www.worldcat.org/isbn/9783540250593"
 * >978-3-540-25059-3</a>, and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783790814965">978-3790814965</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=fQrSUwop4JkC">fQrSUwop4JkC</a>,
 * <a
 * href="http://books.google.com/books?id=tHYlQfvsP6cC">tHYlQfvsP6cC</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=rNxNAAAACAAJ">rNxNAAAACAAJ</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/14349922">1434-9922</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18600808">1860-0808</a></div></li>
 * <li><div><span id="cite_TB1996EA" /><a
 * href="http://www.liacs.nl/~baeck/contact.htm">Thomas B&#228;ck</a>:
 * <span style="font-style:italic;font-family:cursive;">&ldquo;Evolutionary
 * Algorithms in Theory and Practice: Evolution Strategies, Evolutionary
 * Programming, Genetic Algorithms,&rdquo;</span> January&nbsp;1996, New
 * York, NY, USA: Oxford University Press, Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0195099710">0-19-509971-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780195099713">978-0-19-509971
 * -3</a>; LCCN:&nbsp;<a href="http://lccn.loc.gov/95013506">95013506</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/246886085">246886085</a> and&nbsp;<a
 * href="https://www.worldcat.org/oclc/32350061">32350061</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=EaN7kvl5coYC">EaN7kvl5coYC</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=6MOqAAAACAAJ">6MOqAAAACAAJ</a>;
 * PPN:&nbsp;<a href="http://gso.gbv.de/PPNSET?PPN=185320007">185320007</a>
 * and&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=27885365X">27885365X</a></div></li>
 * <li><div><span id="cite_F2002DMAKDWEA" /><a
 * href="http://www.cs.kent.ac.uk/people/staff/aaf/">Alex Alves
 * Freitas</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Data Mining and
 * Knowledge Discovery with Evolutionary Algorithms,&rdquo;</span> 2002,
 * Natural Computing Series, New York, NY, USA: Springer New York.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540433317">3-540-43331-7</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540433316">978-3-540-
 * 43331-6</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2002021728">2002021728</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/49415761">49415761</a>, <a
 * href="https://www.worldcat.org/oclc/248441650">248441650</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/492385968">492385968</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=KkdZlfQJvbYC">KkdZlfQJvbYC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/16197127">1619-7127</a></div></li>
 * <li><div><span id="cite_C2000EAIMD" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evolutionary
 * Algorithms in Molecular Design,&rdquo;</span> September&nbsp;11, 2000,
 * David E. Clark, editor, volume 8 of Methods and Principles in Medicinal
 * Chemistry, Weinheim, Germany: Wiley-VCH Verlag GmbH &amp; Co. KGaA.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3527301550">3527301550</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783527301553">978-3527301553
 * </a></div></li>
 * <li><div><span id="cite_D2001EAMO" /><a
 * href="http://www.iitk.ac.in/kangal/deb.shtml">Kalyanmoy Deb</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Multi-Objective
 * Optimization Using Evolutionary Algorithms,&rdquo;</span> May&nbsp;2001,
 * Wiley Interscience Series in Systems and Optimization, New York, NY,
 * USA: John Wiley &amp; Sons Ltd.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/047187339X">047187339X</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780471873396">978-0471873396</a>;
 * OCLC:&nbsp;<a href="https://www.worldcat.org/oclc/46472121">46472121</a>
 * and&nbsp;<a href="https://www.worldcat.org/oclc/46694962">46694962</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=ll_Blt03u5IC">ll_Blt03u5IC</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=OSTn4GSy2uQC">OSTn4GSy2uQC</a>;
 * ASIN:&nbsp;<a
 * href="http://www.amazon.com/dp/047187339X">047187339X</a></div></li>
 * <li><div><span id="cite_W2002EA" /><a
 * href="http://portal.imn.htwk-leipzig.de/fakultaet/weicker">Karsten
 * Weicker</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evolution&#228;re
 * Algorithmen,&rdquo;</span> March&nbsp;2002, Leitf&#228;den der
 * Informatik, Leipzig, Germany: B. G. Teubner. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3519003627">3-519-00362-7</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783519003625">978-3-519-
 * 00362-5</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/51984239">51984239</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=3-519-00362-7">3-519-00362-7</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=098084860">098084860</a></div></li>
 * <li><div><span id="cite_M1998GA" /><a
 * href="http://web.cecs.pdx.edu/~mm/">Melanie Mitchell</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;An Introduction to
 * Genetic Algorithms,&rdquo;</span> February&nbsp;1998, Complex Adaptive
 * Systems, Bradford Books, Cambridge, MA, USA: MIT Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0262631857">0-262-63185-7</a>, <a
 * href="https://www.worldcat.org/isbn/0262133164">0-262-13316-4</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9780262631853">978-0-262-63185-3</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780262133166">978-0-262-
 * 13316-6</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/95024489">95024489</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/256769418">256769418</a> and&nbsp;<a
 * href="https://www.worldcat.org/oclc/44708663">44708663</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=0eznlz0TF-IC">0eznlz0TF-IC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10894365">1089-4365</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=240449282">240449282</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=557262135">557262135</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=269703497">269703497</a>, and&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=083660852">083660852</a></div></li>
 * <li><div><span id="cite_M1996GADSEP" /><a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>:
 * <span style="font-style:italic;font-family:cursive;">&ldquo;Genetic
 * Algorithms + Data Structures = Evolution Programs,&rdquo;</span> 1996,
 * Berlin, Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540606769">3-540-60676-9</a>, <a
 * href="https://www.worldcat.org/isbn/3540580905">3-540-58090-5</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9783540606765">978-3-540-60676-5</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783642082337">978-3-642-
 * 08233-7</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=vlhLAobsK68C">vlhLAobsK68C</a>;
 * ASIN:&nbsp;<a
 * href="http://www.amazon.com/dp/3540606769">3540606769</a></div></li>
 * <li><div><span id="cite_C1995PHOGAA" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Practical Handbook
 * of Genetic Algorithms: Applications,&rdquo;</span> 1995, Lance D.
 * Chambers, editor, volume I of Practical Handbook of Genetic Algorithms,
 * London, UK: Chapman &amp; Hall and&nbsp;Boca Raton, FL, USA: CRC Press,
 * Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1584882409">1584882409</a>, <a
 * href="https://www.worldcat.org/isbn/0849325196">0849325196</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781584882404">978-1584882404
 * </a></div></li>
 * <li><div><span id="cite_B1987GAASA" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Genetic Algorithms
 * and Simulated Annealing,&rdquo;</span> October&nbsp;1987&ndash;1990,
 * Lawrence Davis, editor, Research Notes in Artificial Intelligence,
 * London, UK: Pitman and&nbsp;San Francisco, CA, USA: Morgan Kaufmann
 * Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0934613443">0934613443</a>, <a
 * href="https://www.worldcat.org/isbn/0273087711">0273087711</a>, <a
 * href="https://www.worldcat.org/isbn/9780934613446">978-0934613446</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780273087717">9780273087717</a>;
 * LCCN:&nbsp;<a href="http://lccn.loc.gov/87021357">87021357</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/16355405">16355405</a>, <a
 * href="https://www.worldcat.org/oclc/476337140">476337140</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/257879939">257879939</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=edfSSAAACAAJ">edfSSAAACAAJ
 * </a></div></li>
 * <li><div><span id="cite_D1991HOGA" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Handbook of
 * Genetic Algorithms,&rdquo;</span> January&nbsp;1991, Lawrence Davis,
 * editor, VNR Computer Library, Stamford, CT, USA: Thomson Publishing
 * Group, Inc. and&nbsp;New York, NY, USA: Van Nostrand Reinhold Co..
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0442001738">0-442-00173-8</a>, <a
 * href="https://www.worldcat.org/isbn/1850328250">1850328250</a>, <a
 * href="https://www.worldcat.org/isbn/9780442001735"
 * >978-0-442-00173-5</a>, and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781850328254">978-1850328254</a>;
 * LCCN:&nbsp;<a href="http://lccn.loc.gov/90012823">90012823</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/300431834">300431834</a>, <a
 * href="https://www.worldcat.org/oclc/231258310">231258310</a>, <a
 * href="https://www.worldcat.org/oclc/23081440">23081440</a>, and&nbsp;<a
 * href="https://www.worldcat.org/oclc/36495685">36495685</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=vTG5PAAACAAJ">vTG5PAAACAAJ</a>;
 * ASIN:&nbsp;<a href="http://www.amazon.com/dp/0442001738">0442001738</a>;
 * PPN:&nbsp;<a href="http://gso.gbv.de/PPNSET?PPN=01945077X">01945077X</a>
 * and&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=276468937">276468937</a></div></li>
 * <li><div><span id="cite_C1995PHOGANF" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Practical Handbook
 * of Genetic Algorithms: New Frontiers,&rdquo;</span> II, 1995, Lance D.
 * Chambers, editor, Boca Raton, FL, USA: CRC Press, Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0849325293">0849325293</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780849325298">978-0849325298</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/503637768">503637768</a>, <a
 * href="https://www.worldcat.org/oclc/468627012">468627012</a>, <a
 * href="https://www.worldcat.org/oclc/32394368">32394368</a>, <a
 * href="https://www.worldcat.org/oclc/35865793">35865793</a>, <a
 * href="https://www.worldcat.org/oclc/54016160">54016160</a>, and&nbsp;<a
 * href="https://www.worldcat.org/oclc/61827855">61827855</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=9RCE3pgj9K4C">9RCE3pgj9K4C
 * </a></div></li>
 * <li><div><span id="cite_C2006ETSOLLGATP" /><a
 * href="http://people.cs.nctu.edu.tw/~ypchen/">Ying-Ping Chen</a> <span
 * style="color:gray">[&#38515;&#31310;&#24179;</span>]: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Extending the
 * Scalability of Linkage Learning Genetic Algorithms &#8210; Theory &amp;
 * Practice,&rdquo;</span> 2004&ndash;2006, volume 190/2006 of Studies in
 * Fuzziness and Soft Computing, Berlin, Germany: Springer-Verlag GmbH.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540284591">3-540-28459-1</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540284598">978-3-540-
 * 28459-8</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/b102053">10.1007/b102053</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=kKr3rKhPU7oC">kKr3rKhPU7oC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/14349922">1434-9922</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18600808">1860-0808</a></div></li>
 * <li><div><span id="cite_CP2000EAAPGA" />Erick Cant&#250;-Paz: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Efficient and
 * Accurate Parallel Genetic Algorithms,&rdquo;</span> December&nbsp;15,
 * 2000, volume 1 of Genetic Algorithms and Evolutionary Computation,
 * Boston, MA, USA: Springer US and&nbsp;Norwell, MA, USA: Kluwer Academic
 * Publishers. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0792372212">0-7923-7221-2</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780792372219">978-0-7923
 * -7221-9</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/45058477">45058477</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=UXkGGQbmsfAC">UXkGGQbmsfAC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/19320167">1932-0167</a></div></li>
 * <li><div><span id="cite_RSM1999EOBGA" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Electromagnetic
 * Optimization by Genetic Algorithms,&rdquo;</span> June&nbsp;1999, Yahya
 * Rahmat-Samii and&nbsp;Eric Michielssen, editors, Wiley Series in
 * Microwave and Optical Engineering, New York, NY, USA: John Wiley &amp;
 * Sons Ltd.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0471295450">0-471-29545-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780471295457">978-0-471-
 * 29545-7</a>; further information: [<a
 * href="http://books.google.com/books?id=0x5TAAAAMAAJ">1</a>]</div></li>
 * <li><div><span id="cite_CHH2001GFSETALOFKB" />&#211;scar Cord&#243;n,
 * Francisco Herrera Triguero, and&nbsp;Achim G. Hoffmann: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Genetic Fuzzy
 * Systems: Evolutionary Tuning and Learning of Fuzzy Knowledge
 * Bases,&rdquo;</span> 2001, volume 19 of Advances in Fuzzy Systems,
 * Singapore: World Scientific Publishing Co.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9810240163">981-02-4016-3</a>, <a
 * href="https://www.worldcat.org/isbn/9810240171">981-02-4017-1</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9789810240165">978-981-02
 * -4016-5</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2001275437">2001275437</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/47768307">47768307</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=BWmSV-38fKAC">BWmSV-38fKAC</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=bwa9QgAACAAJ">bwa9QgAACAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=334123739">334123739</a></div></li>
 * <li><div><span id="cite_G2006GA" />Tomasz Dominik Gwiazda: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Genetic Algorithms
 * Reference &#8210; Volume 1 &#8210; Crossover for Single-Objective
 * Numerical Optimization Problems,&rdquo;</span> May&nbsp;16, 2006,
 * &#321;omianki, Poland: TOMASZGWIAZDA Ebooks. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/8392395832">83-923958-3-2</a>, <a
 * href="https://www.worldcat.org/isbn/8392395840">83-923958-4-0</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9788392395812">978-83-923958-1-2</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9788392395836">978-83-923958-3-6</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9788392395843">978-83-923958
 * -4-3</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/300393138">300393138</a></div></li>
 * <li><div><span id="cite_BK2005FOLCS" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Foundations of
 * Learning Classifier Systems,&rdquo;</span> September&nbsp;1, 2005, Larry
 * Bull and&nbsp;<a href="http://www.cs.bris.ac.uk/~kovacs/">Tim
 * Kovacs</a>, editors, Studies in Fuzziness and Soft Computing, Berlin,
 * Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540250735">3540250735</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540250739">978-3540250739</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/14349922">1434-9922</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18600808">1860-0808</a></div></li>
 * <li><div><span id="cite_C1998PHOGACCS" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Practical Handbook
 * of Genetic Algorithms: Complex Coding Systems,&rdquo;</span>
 * December&nbsp;23, 1998, Lance D. Chambers, editor, volume III of
 * Practical Handbook of Genetic Algorithms, London, UK: Chapman &amp; Hall
 * and&nbsp;Boca Raton, FL, USA: CRC Press, Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0849325390">0849325390</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780849325397">978-0849325397
 * </a></div></li>
 * <li><div><span id="cite_JCS2003HC" />James C. Spall: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Introduction to
 * Stochastic Search and Optimization,&rdquo;</span> June&nbsp;2003,
 * Estimation, Simulation, and Control &#8211; Wiley-Interscience Series in
 * Discrete Mathematics and Optimization, Chichester, West Sussex, UK:
 * Wiley Interscience. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0471330523">0-471-33052-3</a>, <a
 * href="https://www.worldcat.org/isbn/0471722138">0-471-72213-8</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9780471330523">978-0-471-33052-3</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780471722137">978-0-471-
 * 72213-7</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2002038049">2002038049</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/51235522">51235522</a>, <a
 * href="https://www.worldcat.org/oclc/637018778">637018778</a>, <a
 * href="https://www.worldcat.org/oclc/474647590">474647590</a>,
 * and&nbsp;<a href="https://www.worldcat.org/oclc/50773216">50773216</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=f66OIvvkKnAC"
 * >f66OIvvkKnAC</a></div></li>
 * <li><div><span id="cite_W1983EOTEOGAICO" />A. Wetzel: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evaluation of the
 * Effectiveness of Genetic Algorithms in Combinatorial
 * Optimization,&rdquo;</span> 1983, Pittsburgh, PA, USA: University of
 * Pittsburgh</div></li>
 * <li><div><span id="cite_AD2008CGA" /><a
 * href="http://www.lcc.uma.es/~eat/">Enrique Alba Torres</a>
 * and&nbsp;Bernab&#233; Dorronsoro D&#237;az: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Cellular Genetic
 * Algorithms,&rdquo;</span> June&nbsp;2008, volume 42 of Operations
 * Research/Computer Science Interfaces Series, Berlin, Germany:
 * Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0387776095">0387776095</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780387776095">978-0-387-
 * 77609-5</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1387666X">1387-666X</a></div></li>
 * <li><div><span id="cite_H1992AINAASAIAWATBCAIA" /><a
 * href="https://en.wikipedia.org/wiki/John_Henry_Holland">John Henry
 * Holland</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Adaptation in
 * Natural and Artificial Systems: An Introductory Analysis with
 * Applications to Biology, Control, and Artificial
 * Intelligence,&rdquo;</span> May&nbsp;1992, Dublin, OH, USA: NetLibrary,
 * Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0585038449">0585038449</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780585038445">9780585038445</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=nALfIAAACAAJ">nALfIAAACAAJ</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=Jev3AQAACAAJ">Jev3AQAACAAJ
 * </a></div></li>
 * <li><div><span id="cite_K2003GAAGPCAS" /><a
 * href="https://en.wikipedia.org/wiki/John_Koza">John R. Koza</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Genetic Algorithms
 * and Genetic Programming at Stanford,&rdquo;</span> Fall&nbsp;2003,
 * Stanford, CA, USA: Stanford University Bookstore, Stanford University.
 * further information: [<a
 * href="http://www.genetic-programming.com/coursemainpage.html"
 * >1</a>]</div></li>
 * <li><div><span id="cite_QPPW1997GAAESIEACS" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Genetic Algorithms
 * and Evolution Strategy in Engineering and Computer Science: Recent
 * Advances and Industrial Applications,&rdquo;</span> 1998, D.
 * Quagliarella, J. P&#233;riaux, Carlo Poloni, and&nbsp;Gabriel Winter,
 * editors, New York, NY, USA: John Wiley &amp; Sons Ltd.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0471977101">0471977101</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780471977100">978-0471977100</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/38935523">38935523</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=y_NQAAAAMAAJ">y_NQAAAAMAAJ
 * </a></div></li>
 * <li><div><span id="cite_H1975GA" /><a
 * href="https://en.wikipedia.org/wiki/John_Henry_Holland">John Henry
 * Holland</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Adaptation in
 * Natural and Artificial Systems: An Introductory Analysis with
 * Applications to Biology, Control, and Artificial
 * Intelligence,&rdquo;</span> 1975, Ann Arbor, MI, USA: University of
 * Michigan Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0472084607">0-472-08460-7</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780472084609">978-0-472-
 * 08460-9</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/74078988">74078988</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/1531617">1531617</a> and&nbsp;<a
 * href="https://www.worldcat.org/oclc/266623839">266623839</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=JE5RAAAAMAAJ">JE5RAAAAMAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=YE5RAAAAMAAJ">YE5RAAAAMAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=Qk5RAAAAMAAJ">Qk5RAAAAMAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=vk5RAAAAMAAJ">vk5RAAAAMAAJ</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=JOv3AQAACAAJ">JOv3AQAACAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=075982293">075982293</a></div></li>
 * <li><div><span id="cite_R1973ES" /><a
 * href="https://en.wikipedia.org/wiki/Ingo_Rechenberg">Ingo
 * Rechenberg</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo
 * ;Evolutionsstrategie: Optimierung technischer Systeme nach Prinzipien
 * der biologischen Evolution,&rdquo;</span> PhD Thesis, 1971&ndash;1973,
 * Berlin, Germany: Technische Universit&#228;t Berlin. volume 15 of
 * Problemata, Stuttgart, Germany: Friedrick Frommann Verlag and&nbsp;Bad
 * Cannstadt, Stuttgart, Baden-W&#252;rttemberg, Germany: Frommann-Holzboog
 * Verlag. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3772803741">3-7728-0374-1</a>, <a
 * href="https://www.worldcat.org/isbn/3772803733">3-7728-0373-3</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9783772803741">978-3-7728-0374-1</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783772803734">978-3-7728
 * -0373-4</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/74320689">74320689</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/9020616">9020616</a>, <a
 * href="https://www.worldcat.org/oclc/500569005">500569005</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/462818122">462818122</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=QcNNGQAACAAJ">QcNNGQAACAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=024852090">024852090</a></div></li>
 * <li><div><span id="cite_R1994ES" /><a
 * href="https://en.wikipedia.org/wiki/Ingo_Rechenberg">Ingo
 * Rechenberg</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo
 * ;Evolutionsstrategie '94,&rdquo;</span> 1994, volume 1 of Werkstatt
 * Bionik und Evolutionstechnik, Bad Cannstadt, Stuttgart,
 * Baden-W&#252;rttemberg, Germany: Frommann-Holzboog Verlag. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3772816428">3-7728-1642-8</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783772816420">978-3-772-
 * 81642-0</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/75424354">75424354</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=savAAAACAAJ">savAAAACAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=153251220">153251220</a></div></li>
 * <li><div><span id="cite_RB1995GPCAUS" />Justinian P. Rosca and&nbsp;Dana
 * H. Ballard: <span style="font-weight:bold">&ldquo;Causality in Genetic
 * Programming,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the Sixth
 * International Conference on Genetic Algorithms (ICGA'95)</span>,
 * July&nbsp;15&ndash;19, 1995, Pittsburgh, PA, USA: University of
 * Pittsburgh, pages 256&ndash;263, Larry J. Eshelman, editor, San
 * Francisco, CA, USA: Morgan Kaufmann Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558603700">1-55860-370-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558603707">9781558603707</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=9xRRAAAAMAAJ">9xRRAAAAMAAJ</a>.
 * <div>link: [<a
 * href="http://citeseer.ist.psu.edu/rosca95causality.html">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.26.2503">10.1
 * .1.26 .2503</a></div></div></li>
 * <li><div><span id="cite_DJ1975GA" /><a
 * href="http://cs.gmu.edu/~kdejong/">Kenneth Alan De Jong</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;An Analysis of the
 * Behavior of a Class of Genetic Adaptive Systems,&rdquo;</span> PhD
 * Thesis, August&nbsp;1975, Ann Arbor, MI, USA: University of Michigan.
 * <div>link: [<a
 * href="http://cs.gmu.edu/~eclab/kdj_thesis.html">1</a>]</div></div></li>
 * <li><div><span id="cite_H1992GA" /><a
 * href="https://en.wikipedia.org/wiki/John_Henry_Holland">John Henry
 * Holland</a>: <span style="font-weight:bold">&ldquo;Genetic Algorithms
 * &#8210; Computer programs that &quot;evolve&quot; in ways that resemble
 * natural selection can solve complex problems even their creators do not
 * fully understand,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Scientific
 * American</span> 267(1):44&ndash;50, July&nbsp;1992; published by New
 * York, NY, USA: Scientific American, Inc.. <div>links: [<a
 * href="http://www2.econ.iastate.edu/tesfatsi/holland.gaintro.htm">1</a>],
 * [<a href =
 * "http://www.cc.gatech.edu/~turk/bio_sim/articles/genetic_algorithm.pdf"
 * >2 </a>], [<a
 * href="http://members.fortunecity.com/templarseries/algo.html">3</a>],
 * and&nbsp;[<a
 * href="http://www.im.isu.edu.tw/faculty/pwu/heuristic/GA_1.pdf">4<
 * /a>]</div></div></li>
 * <li><div><span id="cite_MFH1992RRGAFLGAP" /><a
 * href="http://web.cecs.pdx.edu/~mm/">Melanie Mitchell</a>, <a
 * href="http://www.cs.unm.edu/~forrest/">Stephanie Forrest</a>,
 * and&nbsp;<a href="https://en.wikipedia.org/wiki/John_Henry_Holland">John
 * Henry Holland</a>: <span style="font-weight:bold">&ldquo;The Royal Road
 * for Genetic Algorithms: Fitness Landscapes and GA
 * Performance,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Toward a Practice of
 * Autonomous Systems: Proceedings of the First European Conference on
 * Artificial Life (Actes de la Premi&#232;re Conf&#233;rence
 * Europ&#233;enne sur la Vie Artificielle) (ECAL'91)</span>,
 * December&nbsp;11&ndash;13, 1991, Paris, France, pages 245&ndash;254,
 * Francisco J. Varela and&nbsp;Paul Bourgine, editors, Bradford Books,
 * Cambridge, MA, USA: MIT Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0262720191">0-262-72019-1</a>.
 * <div>links: [<a
 * href="http://citeseer.ist.psu.edu/mitchell91royal.html">1</a>]
 * and&nbsp;[<a href="http://web.cecs.pdx.edu/~mm/ecal92.pdf">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.49.212">10.1
 * .1.49 .212</a></div></div></li>
 * <li><div><span id="cite_BS2002ES" /><a
 * href="https://homepages.fhv.at/hgb/">Hans-Georg Beyer</a> and&nbsp;<a
 * href="https://en.wikipedia.org/wiki/Hans-Paul_Schwefel">Hans-Paul
 * Schwefel</a>: <span style="font-weight:bold">&ldquo;Evolution Strategies
 * &#8210; A Comprehensive Introduction,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Natural Computing: An
 * International Journal</span> 1(1):3&ndash;52, March&nbsp;2002; published
 * by Norwell, MA, USA: Kluwer Academic Publishers and&nbsp;Dordrecht,
 * Netherlands: Springer Netherlands. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1023/A:1015059928466"
 * >10.1023/A:1015059928466</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/15677818">1567-7818</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15729796">1572-9796</a>. <div>link:
 * [<a href="http://www.cs.bham.ac.uk/~pxt/NIL/es.pdf">1</a>]</div></div></li>
 * <li><div><span id="cite_B1997AAEFTMIWGAO" /><a
 * href="https://homepages.fhv.at/hgb/">Hans-Georg Beyer</a>: <span
 * style="font-weight:bold">&ldquo;An Alternative Explanation for the
 * Manner in which Genetic Algorithms Operate,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Biosystems</span>
 * 41(1):1&ndash;15, January&nbsp;1997; published by East Park, Shannon,
 * Ireland: Elsevier Science Ireland Ltd. and&nbsp;Amsterdam, The
 * Netherlands: North-Holland Scientific Publishers Ltd.. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1016/S0303-2647(96)01657-7"
 * >10.1016/S0303-2647(96)01657-7</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03032647">0303-2647</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href
 * ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.39.307"
 * >10.1.1.39 .307</a></div></div></li>
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
 * href="http://dx.doi.org/10.1109/CIPLS.2013.6595203">10.1109/CIPLS
 * .2013.6595203</a>; INSPEC Accession Number:&nbsp;13752116;
 * EI:&nbsp;20134116837899. <div>link: [<a
 * href="http://www.it-weise.de/documents/files/OWDC2013SADAFTSP.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_PBJ2007OTIOPPOGD" /><a
 * href="http://www.aifb.kit.edu/web/Ingo_Paenke/en">Ingo Paenke</a>, <a
 * href="http://www.wbs.ac.uk/about/person/juergen-branke/">J&#252;rgen
 * Branke</a>, and&nbsp;Yaochu Jin <span
 * style="color:gray">[&#37329;&#32768;&#21021;</span>]: <span
 * style="font-weight:bold">&ldquo;On the Influence of Phenotype Plasticity
 * on Genotype Diversity,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">The First IEEE Symposium
 * on Foundations of Computational Intelligence (FOCI'07)</span>,
 * April&nbsp;1&ndash;5, 2007, Honolulu, HI, USA: Hilton Hawaiian Village
 * Hotel (Beach Resort &amp; Spa), pages 33&ndash;40, Jerry M. Mendel,
 * Takashi Omari, and&nbsp;<a href="http://www.cs.bham.ac.uk/~xin/">Xin
 * Yao</a> <span style="color:gray">[&#23002;&#26032;</span>], editors,
 * Piscataway, NJ, USA: IEEE Computer Society. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1424407036">1424407036</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781424407033">9781424407033</a>;
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/FOCI.2007.372144">10.1109/FOCI.2007
 * .372144</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=-8kaOAAACAAJ">-8kaOAAACAAJ</a>.
 * <div>links: [<a href="http://www.soft-computing.de/S001P005.pdf">1</a>]
 * and&nbsp;[<a
 * href="http://www.honda-ri.org/intern/Publications/PN%2052-06">2</
 * a>]</div></div></li>
 * <li><div><span id="cite_OGC1991TSNATPOD" />Christopher K. Oei, <a
 * href="http://www.davidegoldberg.com/de-goldberg.html">David Edward
 * Goldberg</a>, and&nbsp;Shau-Jin Chang: <span
 * style="font-weight:bold">&ldquo;Tournament Selection, Niching, and the
 * Preservation of Diversity,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;91011, December&nbsp;1991; published by Urbana-Champaign,
 * IL, USA: University of Illinois at Urbana-Champaign, Department of
 * Computer Science, Department of General Engineering, Illinois Genetic
 * Algorithms Laboratory (IlliGAL). <div>link: [<a
 * href="http://www.illigal.uiuc.edu/pub/papers/IlliGALs/91011.ps.Z"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_R1995GA" />Simon Ronald: <span
 * style="font-weight:bold">&ldquo;Preventing Diversity Loss in a Routing
 * Genetic Algorithm with Hash Tagging,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Complexity
 * International</span> 2(2), April&nbsp;1995; published by Victoria,
 * Australia: Monash University, Faculty of Information Technology.
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/13200682">1320-0682</a>. <div>link:
 * [<a href="http://www.complexity.org.au/ci/vol02/sr_hash/">1</a>]</div></
 * div></li>
 * <li><div><span id="cite_R1996GA" />Simon Ronald: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Genetic Algorithms
 * and Permutation-Encoded Problems. Diversity Preservation and a Study of
 * Multimodality,&rdquo;</span> PhD Thesis, 1996, Mawson Lakes, SA,
 * Australia: University of South Australia (UniSA), Department of Computer
 * and Information Science</div></li>
 * <li><div><span id="cite_BBM1993AOOGAP1F" />David Beasley, <a
 * href="http://www.cems.uwe.ac.uk/~lbull/">David R. Bull</a>,
 * and&nbsp;Ralph R. Martin: <span style="font-weight:bold">&ldquo;An
 * Overview of Genetic Algorithms: Part 1. Fundamentals,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">University
 * Computing</span> 15(2):58&ndash;69, 1993; published by Cambridge, MA,
 * USA: Inter-University Committee on Computing. <div>link: [<a
 * href="http://ralph.cs.cf.ac.uk/papers/GAs/ga_overview1.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.61.3619">10.1
 * .1.61 .3619</a> and&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.44.3757"
 * >10.1.1.44.3757</a></div></div></li>
 * <li><div><span id="cite_BT2002EA" />Peter A. N. Bosman and&nbsp;Dirk
 * Thierens: <span style="font-weight:bold">&ldquo;Multi-Objective
 * Optimization with Diversity Preserving Mixture-based Iterated Density
 * Estimation Evolutionary Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">International Journal
 * Approximate Reasoning</span> 31(2):259&ndash;289, November&nbsp;2002;
 * published by Essex, UK: Elsevier Science Publishers B.V.. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1016/S0888-613X(02)00090-7"
 * >10.1016/S0888-613X(02)00090-7</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/0888613X">0888-613X</a></div></li>
 * <li><div><span id="cite_BT2002EAMIDEA" />Peter A. N. Bosman
 * and&nbsp;Dirk Thierens: <span style="font-weight:bold">&ldquo;A Thorough
 * Documentation of Obtained Results on Real-Valued Continuous And
 * Combinatorial Multi-Objective Optimization Problems Using Diversity
 * Preserving Mixture-Based Iterated Density Estimation Evolutionary
 * Algorithms,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;UU-CS-2002-052, December&nbsp;2002; published by Utrecht,
 * The Netherlands: Utrecht University, Institute of Information and
 * Computing Sciences. <div>link: [<a
 * href="http://www.cs.uu.nl/research/techreps/repo/CS-2002/2002-052.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_LTDZ2001EA" /><a
 * href="http://www.ifor.math.ethz.ch/staff/laumanns">Marco Laumanns</a>,
 * <a
 * href="http://www.lamsade.dauphine.fr/mcda/biblio/Author/THIELE-L.html"
 * >Lothar Thiele</a>, <a
 * href="http://www.iitk.ac.in/kangal/deb.shtml">Kalyanmoy Deb</a>,
 * and&nbsp;Eckart Zitzler: <span style="font-weight:bold">&ldquo;On the
 * Convergence and Diversity-Preservation Properties of Multi-Objective
 * Evolutionary Algorithms,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;108, June&nbsp;13, 2001; published by Kanpur, Uttar Pradesh,
 * India: Kanpur Genetic Algorithms Laboratory (KanGAL), Department of
 * Mechanical Engineering, Indian Institute of Technology Kanpur (IIT)
 * and&nbsp;Z&#252;rich, Switzerland: Eidgen&#246;ssische Technische
 * Hochschule (ETH) Z&#252;rich, Department of Electrical Engineering,
 * Computer Engineering and Networks Laboratory (TIK). <div>links: [<a
 * href="http://e-collection.ethbib.ethz.ch/view/eth:24717">1</a>]
 * and&nbsp;[<a
 * href="http://citeseer.ist.psu.edu/laumanns01convergence.html">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.28.6480">10.1
 * .1.28 .6480</a></div></div></li>
 * <li><div><span id="cite_M2004MOEA" />Sanaz Mostaghim: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Multi-objective
 * Evolutionary Algorithms: Data structures, Convergence and,
 * Diversity,&rdquo;</span> PhD Thesis, February&nbsp;2005, Paderborn,
 * Germany: Universit&#228;t Paderborn, Fakult&#228;t f&#252;r
 * Elektrotechnik, Informatik und Mathematik. Aachen, North
 * Rhine-Westphalia, Germany: Shaker Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3832236619">3-8322-3661-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783832236618">978-3-8322
 * -3661-8</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/62900366">62900366</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=HoVLAAAACAAJ">HoVLAAAACAAJ</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=pJpeOQAACAAJ">pJpeOQAACAAJ
 * </a></div></li>
 * <li><div><span id="cite_SSSZ2004EAIM" />Christian Spieth, Felix
 * Streichert, Nora Speer, and&nbsp;Andreas Zell: <span
 * style="font-weight:bold">&ldquo;Utilizing an Island Model for EA to
 * Preserve Solution Diversity for Inferring Gene Regulatory
 * Networks,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'04)</span>,
 * June&nbsp;20&ndash;23, 2004, Portland, OR, USA, pages 146&ndash;151, Los
 * Alamitos, CA, USA: IEEE Computer Society Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780385152">0-7803-8515-2</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780385153">978-0-7803
 * -8515-3</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2004.1330850">10.1109/CEC.2004
 * .1330850</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=I8_5AAAACAAJ">I8_5AAAACAAJ</a>.
 * <div>link: [<a href=
 * "http://www-ra.informatik.uni-tuebingen.de/software/JCell/publications.html"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_SD2006O" />Gulshan Singh and&nbsp;<a
 * href="http://www.iitk.ac.in/kangal/deb.shtml">Kalyanmoy Deb</a>: <span
 * style="font-weight:bold">&ldquo;Comparison of Multi-Modal Optimization
 * Algorithms based on Evolutionary Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 8th
 * Annual Conference on Genetic and Evolutionary Computation
 * (GECCO'06)</span>, July&nbsp;8&ndash;12, 2006, Seattle, WA, USA:
 * Renaissance Seattle Hotel, pages 1305&ndash;1312, Maarten Keijzer
 * and&nbsp;Mike Cattolico, editors, New York, NY, USA: ACM Press.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1595931864">1-59593-186-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781595931863">978-1-59593
 * -186-3</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1145/1143997.1144200"
 * >10.1145/1143997.1144200</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/71335687">71335687</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=6y1pAAAACAAJ">6y1pAAAACAAJ</a>.
 * <div>link: [<a href=
 * "https://ceprofs.civil.tamu.edu/ezechman/CVEN689/References/singh_deb_2006.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_DJWP2001RBAPDUMOM" />Edwin D. de Jong, Richard
 * A. Watson, and&nbsp;<a
 * href="http://pages.cs.brandeis.edu/~pollack/">Jordan B. Pollack</a>:
 * <span style="font-weight:bold">&ldquo;Reducing Bloat and Promoting
 * Diversity using Multi-Objective Methods,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * Genetic and Evolutionary Computation Conference (GECCO'01)</span>,
 * July&nbsp;7&ndash;11, 2001, San Francisco, CA, USA: Holiday Inn Golden
 * Gateway Hotel, pages 11&ndash;18, Lee Spector, <a
 * href="http://www.egr.msu.edu/~goodman/">Erik D. Goodman</a>, Annie S.
 * Wu, <a href="http://www0.cs.ucl.ac.uk/staff/W.Langdon/">William Benjamin
 * Langdon</a>, Hans-Michael Voigt, Mitsuo Gen, Sandip Sen, <a
 * href="https://en.wikipedia.org/wiki/Marco_Dorigo">Marco Dorigo</a>,
 * Shahram Pezeshk, Max H. Garzon, and&nbsp;<a
 * href="http://www.asap.cs.nott.ac.uk/external/atr/people/ekb.shtml"
 * >Edmund K. Burke</a>, editors, San Francisco, CA, USA: Morgan Kaufmann
 * Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558607749">1-55860-774-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558607743">978-155860-774-3</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=81QKAAAACAAJ">81QKAAAACAAJ</a>.
 * <div>links: [<a href="http://citeseer.ist.psu.edu/440305.html">1</a>],
 * [<a href
 * ="http://www.cs.bham.ac.uk/~wbl/biblio/gp-html/jong_2001_gecco.html"
 * >2</a>], and&nbsp;[<a
 * href="http://www.demo.cs.brandeis.edu/papers/rbpd_gecco01.ps.gz">3</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.28.4549">10.1
 * .1.28 .4549</a></div></div></li>
 * <li><div><span id="cite_G2004AAODIGP" /><a
 * href="http://www.gustafsonresearch.com/publications.html">Steven Matt
 * Gustafson</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;An Analysis of
 * Diversity in Genetic Programming,&rdquo;</span> PhD Thesis,
 * February&nbsp;2004, Nottingham, UK: University of Nottingham, School of
 * Computer Science &amp; Information Technology. <div>links: [<a
 * href="http://www.gustafsonresearch.com/thesis_html/index.html">1</a>]
 * and&nbsp;[<a
 * href="http://citeseer.ist.psu.edu/gustafson04analysis.html">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.2.9746">10.1
 * .1.2 .9746</a></div></div></li>
 * <li><div><span id="cite_GEBK2004PDACGIGP" /><a
 * href="http://www.gustafsonresearch.com/publications.html">Steven Matt
 * Gustafson</a>, <a
 * href="http://keg.cs.aston.ac.uk/stfDtls/stfDtls.php?id=22">Anik&#243;
 * Ek&#225;rt</a>, <a
 * href="http://www.asap.cs.nott.ac.uk/external/atr/people/ekb.shtml"
 * >Edmund K. Burke</a>, and&nbsp;<a href=
 * "https://www.nottingham.ac.uk/computerscience/people/graham.kendall"
 * >Graham Kendall</a>: <span style="font-weight:bold">&ldquo;Problem
 * Difficulty and Code Growth in Genetic Programming,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Genetic Programming
 * and Evolvable Machines</span> 5(3):271&ndash;290, September&nbsp;2004;
 * published by Dordrecht, Netherlands: Springer Netherlands
 * and&nbsp;Norwell, MA, USA: Kluwer Academic Publishers. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1023/B:GENP.0000030194.98244.e3"
 * >10.1023/B:GENP.0000030194.98244.e3</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/13892576">1389-2576</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15737632">1573-7632</a>. <div>links:
 * [<a href=
 * "http://www.gustafsonresearch.com/research/publications/gustafson-gpem2004.pdf"
 * >1</a>] and&nbsp;[<a
 * href="http://citeseer.ist.psu.edu/730858.html">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.59.6659">10.1
 * .1.59 .6659</a></div></div></li>
 * <li><div><span id="cite_BGKK2003IIDIGPBAAOTEOF" /><a
 * href="http://www.asap.cs.nott.ac.uk/external/atr/people/ekb.shtml"
 * >Edmund K. Burke</a>, <a
 * href="http://www.gustafsonresearch.com/publications.html">Steven Matt
 * Gustafson</a>, <a href=
 * "https://www.nottingham.ac.uk/computerscience/people/graham.kendall"
 * >Graham Kendall</a>, and&nbsp;<a
 * href="http://www.cs.nott.ac.uk/~nxk/">Natalio Krasnogor</a>: <span
 * style="font-weight:bold">&ldquo;Is Increasing Diversity in Genetic
 * Programming Beneficial? An Analysis of the Effects on
 * Fitness,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'03)</span>,
 * December&nbsp;8&ndash;13, 2003, Canberra, Australia, pages
 * 1398&ndash;1405, <a
 * href="http://seit.unsw.adfa.edu.au/staff/sites/rsarker/">Ruhul Amin
 * Sarker</a>, <a
 * href="http://ai.cs.wayne.edu/ai/member%20webs/Reynolds/">Robert G.
 * Reynolds</a>, <a
 * href="http://seit.unsw.adfa.edu.au/staff/sites/abbass/">Hussein Aly
 * Abbass Amein</a>, <a href="http://vlab.ee.nus.edu.sg/~kctan/">Kay Chen
 * Tan</a>, <a href="http://sc.snu.ac.kr/~rim/index.html">Robert Ian
 * McKay</a>, Daryl Leslie Essam, and&nbsp;Tom Gedeon, editors, Piscataway,
 * NJ, USA: IEEE Computer Society. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780378040">0-7803-7804-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780378049">978-0-7803
 * -7804-9</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2003.1299834">10.1109/CEC.2003
 * .1299834</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=CyBpAAAACAAJ">CyBpAAAACAAJ</a>.
 * <div>links: [<a href=
 * "http://www.gustafsonresearch.com/research/publications/cec-2003.pdf"
 * >1</a>] and&nbsp;[<a
 * href="http://citeseer.ist.psu.edu/700607.html">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.1.1929">10.1
 * .1.1 .1929</a></div></div></li>
 * <li><div><span id="cite_BGK2004DIGPAAOMACF" /><a
 * href="http://www.asap.cs.nott.ac.uk/external/atr/people/ekb.shtml"
 * >Edmund K. Burke</a>, <a
 * href="http://www.gustafsonresearch.com/publications.html">Steven Matt
 * Gustafson</a>, and&nbsp;<a href=
 * "https://www.nottingham.ac.uk/computerscience/people/graham.kendall"
 * >Graham Kendall</a>: <span style="font-weight:bold">&ldquo;Diversity in
 * Genetic Programming: An Analysis of Measures and Correlation with
 * Fitness,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">IEEE Transactions on
 * Evolutionary Computation (IEEE-EC)</span> 8(1):47&ndash;62,
 * February&nbsp;2004; edited by <a
 * href="http://www.cs.bham.ac.uk/~xin/">Xin Yao</a> <span
 * style="color:gray">[&#23002;&#26032;</span>]; published by Washington,
 * DC, USA: IEEE Computer Society. LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/97648327">97648327</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/TEVC.2003.819263"
 * >10.1109/TEVC.2003.819263</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1089778X">1089-778X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/19410026">1941-0026</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=ITEVF5">
 * ITEVF5</a>; further information: [<a
 * href="http://www.ieee-cis.org/pubs/tec/">1</a>]. <div>links: [<a href=
 * "http://www.gustafsonresearch.com/research/publications/gustafson-ieee2004.pdf"
 * >1</a>] and&nbsp;[<a
 * href="http://citeseer.ist.psu.edu/burke04diversity.html">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.59.6663">10.1
 * .1.59 .6663</a></div></div></li>
 * <li><div><span id="cite_BB2002LGPECD" />Markus F. Brameier and&nbsp;<a
 * href="http://www.cs.mun.ca/~banzhaf/">Wolfgang Banzhaf</a>: <span
 * style="font-weight:bold">&ldquo;Explicit Control of Diversity and
 * Effective Variation Distance in Linear Genetic
 * Programming,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 5th
 * European Conference on Genetic Programming (EuroGP'02)</span>,
 * April&nbsp;3&ndash;5, 2002, Kinsale, Ireland, pages 37&ndash;49, <a
 * href="http://www.sci.uidaho.edu/biosci/faculty/Foster.htm">James A.
 * Foster</a>, <a href="http://evelyne.lutton.free.fr/">Evelyne Lutton</a>,
 * <a href="http://www.elec.york.ac.uk/staff/jfm7.html">Julian Francis
 * Miller</a>, <a href="http://www.csis.ul.ie/staff/ConorRyan/">Conor
 * Ryan</a>, and&nbsp;Andrea G. B. Tettamanzi, editors, volume 2278/2002 of
 * Lecture Notes in Computer Science (LNCS), Berlin, Germany:
 * Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540433783">3-540-43378-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540433781">978-3-540-
 * 43378-1</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-45984-7_4"
 * >10.1007/3-540-45984-7_4</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/314133163">314133163</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=RpNQAAAAMAAJ">RpNQAAAAMAAJ</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>. <div>links:
 * [<a href="http://citeseer.ist.psu.edu/552561.html">1</a>], [<a
 * href="http://www.cs.mun.ca/~banzhaf/papers/eurogp02_dist.pdf">2</a>],
 * and&nbsp;[<a href=
 * "http://eldorado.uni-dortmund.de:8080/bitstream/2003/5419/1/123.pdf"
 * >3</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.11.5531"
 * >10.1.1.11.5531</a></div></div></li>
 * <li><div><span id="cite_M1989MA" /><a href=
 * "http://www.newcastle.edu.au/staff/research-profile/Pablo_Moscato/"
 * >Pablo Moscato</a>: <span style="font-weight:bold">&ldquo;On Evolution,
 * Search, Optimization, Genetic Algorithms and Martial Arts: Towards
 * Memetic Algorithms,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;C3P 826, 1989; published by Pasadena, CA, USA: California
 * Institute of Technology (Caltech), Caltech Concurrent Computation
 * Program (C3P). <div>links: [<a
 * href="http://www.densis.fee.unicamp.br/~moscato/papers/bigone.ps">1</a>]
 * and&nbsp;[<a href=
 * "http://www.each.usp.br/sarajane/SubPaginas/arquivos_aulas_IA/memetic.pdf"
 * >2</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.27.9474"
 * >10.1.1.27.9474</a></div></div></li>
 * <li><div><span id="cite_M2002MA" /><a href=
 * "http://www.newcastle.edu.au/staff/research-profile/Pablo_Moscato/"
 * >Pablo Moscato</a>: <span style="font-weight:bold">&ldquo;Memetic
 * Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Handbook of Applied
 * Optimization</span>, chapter 157&ndash;167, pages 157&ndash;167, Panos
 * M. Pardalos and&nbsp;Mauricio G.C. Resende, editors, New York, NY, USA:
 * Oxford University Press, Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0195125940">0-19-512594-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780195125948">978-0-19-512594
 * -8</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/45532495">45532495</a></div></li>
 * <li><div><span id="cite_MC2003AGITMA" /><a href=
 * "http://www.newcastle.edu.au/staff/research-profile/Pablo_Moscato/"
 * >Pablo Moscato</a> and&nbsp;<a
 * href="http://www.lcc.uma.es/~ccottap/">Carlos Cotta</a>: <span
 * style="font-weight:bold">&ldquo;A Gentle Introduction to Memetic
 * Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Handbook of
 * Metaheuristics</span>, chapter 105&ndash;144, pages 105&ndash;144, <a
 * href="http://en.wikipedia.org/wiki/Fred_W._Glover">Fred W. Glover</a>
 * and&nbsp;<a href=
 * "http://www.ucdenver.edu/academics/colleges/business/Faculty-Research/FacultyDirectory/Pages/Gary-Kochenberger.aspx"
 * >Gary A. Kochenberger</a>, editors, volume 57 of International Series in
 * Operations Research &amp; Management Science, Norwell, MA, USA: Kluwer
 * Academic Publishers, Dordrecht, Netherlands: Springer Netherlands,
 * and&nbsp;Boston, MA, USA: Springer US. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1402072635">1-4020-7263-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780306480560">978-0-306-
 * 48056-0</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/0-306-48056-5_5"
 * >10.1007/0-306-48056-5_5</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=O_10T_KeqOgC">O_10T_KeqOgC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/08848289">0884-8289</a>. <div>link:
 * [<a
 * href="http://www.lcc.uma.es/~ccottap/papers/handbook03memetic.pdf">1<
 * /a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.77.5300"
 * >10.1.1.77 .5300</a></div></div></li>
 * <li><div><span id="cite_ES2003HWOTMA" /><a
 * href="http://www.cs.vu.nl/~gusz/">&#193;goston E. Eiben</a> aka.
 * Gusz/Guszti and&nbsp;<a href="http://www.cems.uwe.ac.uk/~jsmith/">James
 * E. Smith</a>: <span style="font-weight:bold">&ldquo;Hybridisation with
 * other Techniques: Memetic Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Introduction to
 * Evolutionary Computing</span>, chapter 173&ndash;188, pages
 * 173&ndash;188, Natural Computing Series, New York, NY, USA: Springer New
 * York. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540401849">3540401849</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540401841">978-3540401841</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=7IOE5VIpFpwC">7IOE5VIpFpwC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/16197127">1619-7127</a></div></li>
 * <li><div><span id="cite_HKS2005RAIMA" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Recent Advances in
 * Memetic Algorithms,&rdquo;</span> 2005, <a
 * href="http://www.cs.sandia.gov/~wehart/Main/Home.html">William Eugene
 * Hart</a>, <a href="http://www.cs.nott.ac.uk/~nxk/">Natalio
 * Krasnogor</a>, and&nbsp;<a
 * href="http://www.cems.uwe.ac.uk/~jsmith/">James E. Smith</a>, editors,
 * volume 166/2005 of Studies in Fuzziness and Soft Computing, Berlin,
 * Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540229043">3-540-22904-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540229049">978-3-540-
 * 22904-9</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2004111139">2004111139</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-32363-5"
 * >10.1007/3-540-32363-5</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/318297267">318297267</a> and&nbsp;<a
 * href="https://www.worldcat.org/oclc/56697114">56697114</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=LYf7YW4DmkUC">LYf7YW4DmkUC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/14349922">1434-9922</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18600808">1860-0808</a></div></li>
 * <li><div><span id="cite_DM2004MA" />Jason Digalakis
 * and&nbsp;Konstantinos Margaritis: <span
 * style="font-weight:bold">&ldquo;Performance Comparison of Memetic
 * Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of Applied
 * Mathematics and Computation</span> 158:237&ndash;252, October&nbsp;2004;
 * published by Essex, UK: Elsevier Science Publishers B.V.. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1016/j.amc.2003.08.115"
 * >10.1016/j.amc.2003.08.115</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00963003">0096-3003</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=AMHCBQ">
 * AMHCBQ</a>. <div>links: [<a
 * href="http://citeseer.ist.psu.edu/458892.html">1</a>], [<a href=
 * "http://www.complexity.org.au/ci/draft/draft/digala02/digala02s.pdf"
 * >2</a>], and&nbsp;[<a
 * href="http://www.complexity.org.au/ci/vol10/digala02/digala02s.pdf"
 * >3</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.21.5495"
 * >10.1.1.21 .5495</a></div></div></li>
 * <li><div><span id="cite_RS1994FMA" /><a
 * href="http://users.breathe.com/njr/">Nicholas J. Radcliffe</a>
 * and&nbsp;<a href="http://www.linkedin.com/in/patricksurry">Patrick David
 * Surry</a>: <span style="font-weight:bold">&ldquo;Formal Memetic
 * Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * Workshop on Artificial Intelligence and Simulation of Behaviour,
 * International Workshop on Evolutionary Computing, Selected Papers
 * (AISB'94)</span>, April&nbsp;11&ndash;13, 1994, Leeds, UK, pages
 * 1&ndash;16, Terence Claus Fogarty, editor, volume 865/1994 of Lecture
 * Notes in Computer Science (LNCS), Berlin, Germany: Springer-Verlag GmbH
 * and&nbsp;Chichester, West Sussex, UK: Society for the Study of
 * Artificial Intelligence and the Simulation of Behaviour (SSAISB).
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540584838">3-540-58483-8</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540584834">978-3-540-
 * 58483-4</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-58483-8_1"
 * >10.1007/3-540-58483-8_1</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=59512478X">59512478X</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href
 * ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.38.9885"
 * >10.1.1.38 .9885</a></div></div></li>
 * </ol>
 */
public class EA extends TSPAlgorithm {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the &#956; parameter: {@value} , see {@link #m_mu} */
  public static final String PARAM_MU = "mu"; //$NON-NLS-1$
  /** the &#955; parameter: {@value} , see {@link #m_lambda} */
  public static final String PARAM_LAMBDA = "lambda"; //$NON-NLS-1$

  /** the steady state parameter: {@value} , see {@link #m_steady} */
  public static final String PARAM_PARENTS_SURVIVE = "parentsSurvive"; //$NON-NLS-1$

  /** the crossover rate parameter: {@value} , see #m_cr */
  public static final String PARAM_CR = "crossoverRate"; //$NON-NLS-1$

  /** the gpm parameter: {@value} , see {@link #m_gpm} */
  public static final String PARAM_GPM = "gpm"; //$NON-NLS-1$

  /** the nullary operation parameter: {@value} , see {@link #m_nullary} */
  public static final String PARAM_NULLARY = "nullaryOp"; //$NON-NLS-1$
  /** the unary operation parameter: {@value} , see {@link #m_unary} */
  public static final String PARAM_UNARY = "unaryOp"; //$NON-NLS-1$
  /** the binary operation parameter: {@value} , see {@link #m_binary} */
  public static final String PARAM_BINARY = "binaryOp"; //$NON-NLS-1$

  /** the selection algorithm: {@value} , see {@link #m_selection} */
  public static final String PARAM_SELECTION_ALGORITHM = "selectionAlgorithm"; //$NON-NLS-1$

  /** the fitness assignment process: {@value} , see {@link #m_fap} */
  public static final String PARAM_FITNESS_ASSIGNMENT_PROCESS = "fitnessAssignmentProcess"; //$NON-NLS-1$

  /** the number of performed generations: {@value} */
  public static final String INFO_COMPLETED_GENERATIONS = "eaCompletedGenerations"; //$NON-NLS-1$

  /** the default &#956;: {@value} , see {@link #m_mu} */
  private static final int DEFAULT_MU = 16;

  /** the default &#955;: {@value} , see {@link #m_lambda} */
  private static final int DEFAULT_LAMBDA = 64;

  /** the default parents survival: {@value} , see {@link #m_steady} */
  public static final boolean DEFAULT_PARENTS_SURVIVE = true;

  /** the default crossover rate: {@value} , see {@link #m_cr} */
  public static final double DEFAULT_CR = (1d / 3d);

  /**
   * the &#956; parameter, i.e., the number of selected parents
   * 
   * @serial a positive integer number
   * @see #getMu()
   * @see #setMu(int)
   */
  private int m_mu;

  /**
   * the &#955;, i.e., the number of produced offspring
   * 
   * @serial a positive integer number
   * @see #getLambda()
   * @see #setLambda(int)
   */
  private int m_lambda;

  /**
   * Is the algorithm a steady-state algorithm? If so, parents survive and
   * compete with their children, otherwise parents die. If this variable
   * is {@code true}, the algorithm roughly follows a (&#956;+&#955;)
   * population strategy (except that it can use an arbitrary
   * {@link #m_selection selection scheme}) and if the value is
   * {@code false}, it is more a (&#956;,&#955;)-type of method.
   * 
   * @serial a boolean value
   * @see #doParentsSurvive()
   * @see #setParentsSurvive(boolean)
   */
  private boolean m_steady;

  /**
   * the
   * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.SelectionAlgorithm
   * selection algorithm} chooses the individuals from the populations
   * which will become the parents of the next generation, based on their
   * {@link #m_fap fitness} [by default, this will be the
   * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.selection.TruncationSelection
   * truncation selection} method which picks the best individuals]
   * 
   * @serial a non-{@code null} instance of the
   *         {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.SelectionAlgorithm
   *         selection algorithm} to use, defaults to
   *         {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.selection.TruncationSelection#INSTANCE
   *         truncation selection}
   * @see #getSelectionAlgorithm()
   * @see #setSelectionAlgorithm(SelectionAlgorithm)
   */
  private SelectionAlgorithm m_selection;

  /**
   * The {@link org.logisticPlanning.tsp.solving.gpm.GPM genotype-phenotype
   * mapping} (GPM) translating genotypes to phenotypes (tours) [by
   * default, genotypes and phenotypes are the same]
   * 
   * @serial a non-{@code null} instance of the
   *         {@link org.logisticPlanning.tsp.solving.gpm.GPM GPM} to use,
   *         defaults normally to the
   *         {@link org.logisticPlanning.tsp.solving.gpm.IdentityMapping#INSTANCE
   *         identity mapping}
   * @see #getGPM()
   * @see #setGPM(GPM)
   */
  private GPM<Object> m_gpm;

  /**
   * the {@link org.logisticPlanning.tsp.solving.operators.UnaryOperator
   * unary search operator}, i.e., the mutation operation creating one
   * offspring from one parent
   * 
   * @serial a non-{@code null} instance of the
   *         {@link org.logisticPlanning.tsp.solving.operators.UnaryOperator
   *         unary search operation} to use, defaults to the
   *         {@link org.logisticPlanning.tsp.solving.operators.UnaryOperator#DUMMY
   *         dummy operator}
   * @see #getUnaryOperator()
   * @see #setUnaryOperator(UnaryOperator)
   */
  private UnaryOperator<Object> m_unary;

  /**
   * the {@link org.logisticPlanning.tsp.solving.operators.BinaryOperator
   * binary operator}, i.e., the crossover operation creating one offspring
   * from two parent individuals
   * 
   * @serial a non-{@code null} instance of the
   *         {@link org.logisticPlanning.tsp.solving.operators.BinaryOperator
   *         binary search operation} to use, defaults to the
   *         {@link org.logisticPlanning.tsp.solving.operators.BinaryOperator#DUMMY
   *         dummy operator}
   * @see #getBinaryOperator()
   * @see #setBinaryOperator(BinaryOperator)
   */
  private BinaryOperator<Object> m_binary;

  /**
   * the {@link org.logisticPlanning.tsp.solving.operators.NullaryOperator
   * nullary operator}, i.e., the operator producing the initial (random)
   * individuals
   * 
   * @serial a non-{@code null} instance of the
   *         {@link org.logisticPlanning.tsp.solving.operators.NullaryOperator
   *         nullary search operation} to use, defaults to the
   *         {@link org.logisticPlanning.tsp.solving.operators.NullaryOperator#DUMMY
   *         dummy operator}
   * @see #getNullaryOperator()
   * @see #setNullaryOperator(NullaryOperator)
   */
  private NullaryOperator<Object> m_nullary;

  /**
   * the crossover rate which is between 0 and 1 determines the fraction of
   * offspring created by crossover, the rest is created via mutation
   * 
   * @serial a {@code double} value in the real intervall {@code [0,1]}
   *         (with inclusive boundaries)
   * @see #getCrossoverRate()
   * @see #setCrossoverRate(double)
   */
  private double m_cr;

  /**
   * the
   * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.FitnessAssignmentProcess
   * fitness assignment process} transforms a tour length to a fitness [by
   * default,
   * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.fitness.FitnessIsObjectiveValue#INSTANCE
   * fitness = tour length}]
   * 
   * @serial a non-{@code null} instance of the
   *         {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.FitnessAssignmentProcess
   *         fitness assignment process} to use, defaults to the
   *         {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.fitness.FitnessIsObjectiveValue#INSTANCE
   *         fitness=tour length}-approach
   * @see #getFitnessAssignmentProcess()
   * @see #setFitnessAssignmentProcess(FitnessAssignmentProcess)
   */
  private FitnessAssignmentProcess m_fap;

  /**
   * an internal counter counting the number of performed generations, see
   * {@link #INFO_COMPLETED_GENERATIONS}
   */
  private transient int m_completedGenerations;

  /**
   * create the EA an initialize all parameters to default values
   * 
   * @param name
   *          the algorithm's name
   */
  public EA(final String name) {
    super((name != null) ? name : "Evolutionary Algorithm");//$NON-NLS-1$

    this.m_mu = EA.DEFAULT_MU;
    this.m_lambda = EA.DEFAULT_LAMBDA;
    this.m_steady = EA.DEFAULT_PARENTS_SURVIVE;
    this.m_selection = TruncationSelection.INSTANCE;
    this.m_gpm = IdentityMapping.INSTANCE;
    this.m_binary = BinaryOperator.DUMMY;
    this.m_unary = UnaryOperator.DUMMY;
    this.m_nullary = NullaryOperator.DUMMY;
    this.m_cr = EA.DEFAULT_CR;
    this.m_fap = FitnessIsObjectiveValue.INSTANCE;

    this.m_completedGenerations = (-1);
  }

  /**
   * get the &#956;
   * 
   * @return the &#956;
   * @see #m_mu
   * @see #setMu(int)
   */
  public final int getMu() {
    return this.m_mu;
  }

  /**
   * set the &#956;
   * 
   * @param mu
   *          the &#956;
   * @see #m_mu
   * @see #getMu()
   */
  public final void setMu(final int mu) {
    this.m_mu = ((mu > 0) ? mu : EA.DEFAULT_MU);
  }

  /**
   * get the &#955;
   * 
   * @return the &#955;
   * @see #m_lambda
   * @see #setLambda(int)
   */
  public final int getLambda() {
    return this.m_lambda;
  }

  /**
   * set the &#955;
   * 
   * @param lambda
   *          the &#955;
   * @see #m_lambda
   * @see #getLambda()
   */
  public final void setLambda(final int lambda) {
    this.m_lambda = ((lambda > 0) ? lambda : EA.DEFAULT_LAMBDA);
  }

  /**
   * get the crossover rate
   * 
   * @return the crossover rate
   * @see #m_cr
   * @see #setCrossoverRate(double)
   */
  public final double getCrossoverRate() {
    return this.m_cr;
  }

  /**
   * set the crossover rate
   * 
   * @param cr
   *          the crossover rate
   * @see #m_cr
   * @see #getCrossoverRate()
   */
  public final void setCrossoverRate(final double cr) {
    this.m_cr = ((cr > 0) ? cr : EA.DEFAULT_CR);
  }

  /**
   * get the steady state
   * 
   * @return the steady state
   * @see #m_steady
   * @see #setParentsSurvive(boolean)
   */
  public final boolean doParentsSurvive() {
    return this.m_steady;
  }

  /**
   * Set the steady state
   * 
   * @param steady
   *          the steady state
   * @see #m_steady
   * @see #doParentsSurvive()
   */
  public final void setParentsSurvive(final boolean steady) {
    this.m_steady = steady;
  }

  /**
   * set the selection algorithm
   * 
   * @param sel
   *          the algorithm
   * @see #m_selection
   * @see #setSelectionAlgorithm(SelectionAlgorithm)
   */
  public final void setSelectionAlgorithm(final SelectionAlgorithm sel) {
    this.m_selection = ((sel != null) ? sel : TruncationSelection.INSTANCE);
  }

  /**
   * get the selection algorithm
   * 
   * @return the selection algorithm
   * @see #m_selection
   * @see #setSelectionAlgorithm(SelectionAlgorithm)
   */
  public final SelectionAlgorithm getSelectionAlgorithm() {
    return this.m_selection;
  }

  /**
   * set the fitness assignment process
   * 
   * @param fap
   *          the fitness assignment process
   * @see #m_fap
   * @see #getFitnessAssignmentProcess()
   */
  public final void setFitnessAssignmentProcess(
      final FitnessAssignmentProcess fap) {
    this.m_fap = ((fap != null) ? fap : FitnessIsObjectiveValue.INSTANCE);
  }

  /**
   * get the fitness assignment process
   * 
   * @return the fitness assignment process
   * @see #m_fap
   * @see #setFitnessAssignmentProcess(FitnessAssignmentProcess)
   */
  public final FitnessAssignmentProcess getFitnessAssignmentProcess() {
    return this.m_fap;
  }

  /**
   * set the genotype-phenotype mapping
   * 
   * @param gpm
   *          the genotype-phenotype mapping
   * @see #m_gpm
   * @see #getGPM()
   */
  @SuppressWarnings("unchecked")
  public final void setGPM(final GPM<?> gpm) {
    if (gpm != null) {
      this.m_gpm = ((GPM<Object>) (gpm));
    } else {
      this.m_gpm = IdentityMapping.INSTANCE;
    }
  }

  /**
   * get the genotype-phenotype mapping
   * 
   * @return the genotype-phenotype mapping
   * @see #m_gpm
   * @see #setGPM(GPM)
   */
  public final GPM<Object> getGPM() {
    return this.m_gpm;
  }

  /**
   * set the binary operator
   * 
   * @param op
   *          the binary operator
   * @see #m_binary
   * @see #getBinaryOperator()
   */
  @SuppressWarnings("unchecked")
  public final void setBinaryOperator(final BinaryOperator<?> op) {
    if (op != null) {
      this.m_binary = ((BinaryOperator<Object>) (op));
    } else {
      this.m_binary = BinaryOperator.DUMMY;
    }
  }

  /**
   * get the binary search operator
   * 
   * @return the binary search operator
   * @see #m_binary
   * @see #setBinaryOperator(BinaryOperator)
   */
  public final BinaryOperator<Object> getBinaryOperator() {
    return this.m_binary;
  }

  /**
   * set the unary operator
   * 
   * @param op
   *          the unary operator
   * @see #m_unary
   * @see #getUnaryOperator()
   */
  @SuppressWarnings("unchecked")
  public final void setUnaryOperator(final UnaryOperator<?> op) {
    if (op != null) {
      this.m_unary = ((UnaryOperator<Object>) (op));
    } else {
      this.m_unary = UnaryOperator.DUMMY;
    }
  }

  /**
   * get the unary search operator
   * 
   * @return the unary search operator
   * @see #m_unary
   * @see #setUnaryOperator(UnaryOperator)
   */
  public final UnaryOperator<Object> getUnaryOperator() {
    return this.m_unary;
  }

  /**
   * set the nullary operator
   * 
   * @param op
   *          the nullary operator
   * @see #m_nullary
   * @see #getNullaryOperator()
   */
  @SuppressWarnings("unchecked")
  public final void setNullaryOperator(final NullaryOperator<?> op) {
    if (op != null) {
      this.m_nullary = ((NullaryOperator<Object>) (op));
    } else {
      this.m_nullary = NullaryOperator.DUMMY;
    }
  }

  /**
   * get the nullary search operator
   * 
   * @return the nullary search operator
   * @see #m_nullary
   * @see #setNullaryOperator(NullaryOperator)
   */
  public final NullaryOperator<Object> getNullaryOperator() {
    return this.m_nullary;
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {

    super.configure(config);

    this.m_lambda = config.getInt(EA.PARAM_LAMBDA, 1, Integer.MAX_VALUE,
        this.m_lambda);

    this.m_mu = config.getInt(EA.PARAM_MU, 1, this.m_lambda,
        Math.min(this.m_lambda, this.m_mu));

    this.m_cr = config.getDouble(EA.PARAM_CR, 0d, 1d, this.m_cr);

    this.m_steady = config.getBoolean(EA.PARAM_PARENTS_SURVIVE,
        this.m_steady);

    this.m_gpm = config.getInstance(EA.PARAM_GPM, GPM.class, null,
        this.m_gpm);

    this.m_nullary = config.getInstance(EA.PARAM_NULLARY,
        NullaryOperator.class, null, this.m_nullary);

    this.m_unary = config.getInstance(EA.PARAM_UNARY, UnaryOperator.class,
        null, this.m_unary);

    this.m_binary = config.getInstance(EA.PARAM_BINARY,
        BinaryOperator.class, null, this.m_binary);

    this.m_selection = config.getInstance(EA.PARAM_SELECTION_ALGORITHM,
        SelectionAlgorithm.class, null, this.m_selection);

    this.m_fap = config.getInstance(EA.PARAM_FITNESS_ASSIGNMENT_PROCESS,
        FitnessAssignmentProcess.class, null, this.m_fap);
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(EA.PARAM_LAMBDA, ps);
    ps.println(this.m_lambda);

    Configurable.printKey(EA.PARAM_MU, ps);
    ps.println(this.m_mu);

    Configurable.printKey(EA.PARAM_PARENTS_SURVIVE, ps);
    ps.println(this.m_steady);

    Configurable.printKey("populationStructure", ps); //$NON-NLS-1$
    ps.print('(');
    ps.print(this.m_mu);
    ps.print(this.m_steady ? '+' : ',');
    ps.print(this.m_lambda);
    ps.println(')');

    Configurable.printKey(EA.PARAM_FITNESS_ASSIGNMENT_PROCESS, ps);
    Configurable.printlnObject(this.m_fap, ps);

    Configurable.printKey(EA.PARAM_SELECTION_ALGORITHM, ps);
    Configurable.printlnObject(this.m_selection, ps);

    Configurable.printKey(EA.PARAM_CR, ps);
    ps.println(this.m_cr);

    Configurable.printKey(EA.PARAM_NULLARY, ps);
    Configurable.printlnObject(this.m_nullary, ps);

    Configurable.printKey(EA.PARAM_UNARY, ps);
    Configurable.printlnObject(this.m_unary, ps);

    Configurable.printKey(EA.PARAM_BINARY, ps);
    Configurable.printlnObject(this.m_binary, ps);

    Configurable.printKey(EA.PARAM_GPM, ps);
    Configurable.printlnObject(this.m_gpm, ps);

    if (this.m_completedGenerations >= 0) {
      Configurable.printKey(EA.INFO_COMPLETED_GENERATIONS, ps);
      ps.println(this.m_completedGenerations);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {

    super.printParameters(ps);

    Configurable.printKey(EA.PARAM_LAMBDA, ps);
    ps.println("the number of offsprings per generation."); //$NON-NLS-1$

    Configurable.printKey(EA.PARAM_MU, ps);
    ps.println("the mating pool size, i.e., the number of selected individuals."); //$NON-NLS-1$

    Configurable.printKey(EA.PARAM_PARENTS_SURVIVE, ps);
    ps.println("will parents compete with children (mu+lambda) or not (mu,lambda)."); //$NON-NLS-1$

    Configurable.printKey(EA.PARAM_FITNESS_ASSIGNMENT_PROCESS, ps);
    ps.println("the class of the fitness assignment process."); //$NON-NLS-1$
    this.m_fap.printParameters(ps);

    Configurable.printKey(EA.PARAM_SELECTION_ALGORITHM, ps);
    ps.println("the class of the selection algorithm."); //$NON-NLS-1$
    this.m_selection.printParameters(ps);

    Configurable.printKey(EA.PARAM_CR, ps);
    ps.println("the crossover rate."); //$NON-NLS-1$

    Configurable.printKey(EA.PARAM_NULLARY, ps);
    ps.println("the class of the nullary search operation (creation)."); //$NON-NLS-1$
    this.m_nullary.printParameters(ps);

    Configurable.printKey(EA.PARAM_UNARY, ps);
    ps.println("the class of the  unary search operation (mutation)."); //$NON-NLS-1$
    this.m_unary.printParameters(ps);

    Configurable.printKey(EA.PARAM_BINARY, ps);
    ps.println("the class of the binary search operation (crossover/recombination)."); //$NON-NLS-1$
    this.m_binary.printParameters(ps);

    Configurable.printKey(EA.PARAM_GPM, ps);
    ps.println("the class of the genotype-phenotype mapping (gpm)."); //$NON-NLS-1$
    this.m_gpm.printParameters(ps);
  }

  /**
   * complete an individual's setup
   * 
   * @param ind
   *          the individual
   * @param f
   *          the objective function
   * @param gpm
   *          the gpm
   */
  protected void complete(final Individual<Object> ind,
      final ObjectiveFunction f, final GPM<Object> gpm) {
    ind.f = Double.POSITIVE_INFINITY;
    gpm.gpm(f, ind);
    if ((ind.tourLength >= Individual.TOUR_LENGTH_NOT_SET)
        && (ind.solution != null)) {
      ind.tourLength = f.evaluate((int[]) (ind.solution));
    }
  }

  /**
   * create the first generation
   * 
   * @param pop
   *          the population to be filled with new individuals
   * @param f
   *          the objective function
   */
  protected void createFirstGeneration(final Individual<Object>[] pop,
      final ObjectiveFunction f) {
    final NullaryOperator<Object> op0;
    int i;
    Individual<Object> ind;

    op0 = this.getNullaryOperator();

    for (i = pop.length; (--i) >= 0;) {
      pop[i] = ind = new Individual<>();
      op0.create(ind, f);
    }
  }

  /** {@inheritDoc} */
  @SuppressWarnings("unchecked")
  @Override
  public void solve(final ObjectiveFunction f) {
    Individual<Object>[] pop, mate;
    final boolean ss;
    final int lambda, mu;
    int i;
    final UnaryOperator<Object> op1;
    final BinaryOperator<Object> op2;
    Individual<Object> ind;
    final GPM<Object> gpm;
    final SelectionAlgorithm sr;
    final FitnessAssignmentProcess fap;
    final Randomizer r;
    final double cr;

    ss = this.doParentsSurvive();
    lambda = this.getLambda();
    mu = this.getMu();

    pop = new Individual[lambda];
    mate = new Individual[mu];

    op1 = this.getUnaryOperator();
    op2 = this.getBinaryOperator();
    gpm = this.getGPM();
    sr = this.getSelectionAlgorithm();
    cr = this.getCrossoverRate();
    fap = this.getFitnessAssignmentProcess();
    r = f.getRandom();

    // Produce the first generation of individuals. These individuals will
    // usually be random, may have also been produced with a heuristic
    // and/or
    // refined with a local search
    this.createFirstGeneration(pop, f);

    for (;;) {
      // loop forever - termination criterion is checked in loop body

      // increase number of completed generations
      this.m_completedGenerations++;

      // tell the GPM that a new generation is beginning
      gpm.beforeGeneration(f);

      // At the beginning of each generation, we "complete" the
      // individuals in
      // the population, i.e., perform GPMs and calculate the tour lengths
      for (i = lambda; (--i) >= 0;) {
        ind = pop[i];
        this.complete(ind, f, gpm);
        if (f.shouldTerminate()) {
          // if the computational budget is spent, we return
          return;
        }
      }

      // ok, all individuals have been processed by the gpm
      gpm.afterGeneration(f);

      // use the tour lengths (and potentially other individual features)
      // to
      // create the fitness of each individual (by default, fitness=tour
      // length, but the fitness assignment process may do whatever it
      // pleases
      // to do here)
      fap.assignFitness(pop, f);

      // select mu individuals out of the population of size lambda (or
      // lambda+mu in the steady-state case)
      sr.select(pop, mate, f);

      if (ss) {
        // in the steady state case, the population of the next
        // generation will
        // contain lambda new offspring and the mu selected individuals)
        if (pop.length <= lambda) {
          // only in the very first generation, we need to re-allocate
          // the
          // population array
          pop = new Individual[mu + lambda];
        }
        // and copy the parents to the next generation
        System.arraycopy(mate, 0, pop, lambda, mu);
      }

      // now we can fill the population with lambda new offspring
      for (i = lambda; (--i) >= 0;) {
        pop[i] = ind = new Individual<>();
        // With probability cr, an offspring results from crossover
        // (binary
        // search operator). With probability (1-cr), it results from
        // mutation
        // (unary search operator).
        if (r.nextDouble() < cr) {
          op2.recombine(ind, f, mate[i % mu], mate[r.nextInt(mu)]);
        } else {
          op1.mutate(ind, f, mate[i % mu]);
        }
        if (f.shouldTerminate()) {
          // if the computational budget is spent, we return
          return;
        }
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public EA clone() {
    EA cfg;

    cfg = ((EA) (super.clone()));

    cfg.m_binary = ((BinaryOperator<Object>) (cfg.m_binary.clone()));
    cfg.m_unary = ((UnaryOperator<Object>) (cfg.m_unary.clone()));
    cfg.m_nullary = ((NullaryOperator<Object>) (cfg.m_nullary.clone()));
    cfg.m_gpm = ((GPM<Object>) (cfg.m_gpm.clone()));
    cfg.m_selection = ((SelectionAlgorithm) (cfg.m_selection.clone()));
    cfg.m_completedGenerations = (-1);

    return cfg;
  }

  /** {@inheritDoc} */
  @SuppressWarnings("rawtypes")
  @Override
  public void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);
    this.m_completedGenerations = 0;
    this.m_nullary.beginRun(f);
    if ((!(this.m_binary instanceof BinaryOperatorFollowedByUnary))
        || ((((BinaryOperatorFollowedByUnary) (this.m_binary))
            .getUnaryOperator()) != this.m_unary)) {
      this.m_unary.beginRun(f);
    }
    this.m_binary.beginRun(f);
    this.m_gpm.beginRun(f);
    this.m_fap.beginRun(f);
    this.m_selection.beginRun(f);
  }

  /** {@inheritDoc} */
  @SuppressWarnings("rawtypes")
  @Override
  public void endRun(final ObjectiveFunction f) {
    try {
      this.m_selection.endRun(f);
    } finally {
      try {
        this.m_fap.endRun(f);
      } finally {
        try {
          this.m_gpm.endRun(f);
        } finally {
          try {
            this.m_binary.endRun(f);
          } finally {
            try {
              if ((!(this.m_binary instanceof BinaryOperatorFollowedByUnary))
                  || ((((BinaryOperatorFollowedByUnary) (this.m_binary))
                      .getUnaryOperator()) != this.m_unary)) {
                this.m_unary.endRun(f);
              }
            } finally {
              try {
                this.m_nullary.endRun(f);
              } finally {
                super.endRun(f);
              }
            }
          }
        }
      }
    }
  }
}
