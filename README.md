# [TSPSuite](http://optimizationbenchmarking.github.io/tspSuite/)

The *TSPSuite* is a holistic benchmarking environment for
algorithms solving the Traveling Salesman Problem (TSP) written in Java. It is based
on the <a href="https://www.iwr.uni-heidelberg.de/groups/comopt/software/TSPLIB95/">TSPLib</a>
benchmark cases and offers integrated support for implementing, testing, benchmarking, and
comparing algorithms. It also features a large set of implemented algorithms.</p>

In the *TSPSuite*, we focus on collecting
information regarding how long an algorithm needs to reach a certain solution
quality and what solution quality we can expect after a certain runtime. This is
especially interesting for comparing anytime algorithms, such as metaheuristics
that step-by-step refine and combine solutions in order to obtain better tours.
For each algorithm tested, comprehensive logging information is collected
regarding not only the solution quality and runtime (according to different time
measures such as FEs and real time), but also the environment the algorithm was
executed in and the parameters of the algorithm, rendering each log file self-
explaining. TSPSuite contains an evaluator utility which can load these log files
and create a <a href="https://en.wikipedia.org/wiki/LaTeX">LaTeX</a> or XHTML document summarizing an algorithm's performance from
different perspectives and comparing different algorithms. Finally, we also
implement a set of basic algorithms for solving TSPs. All of this is done under
the *[GNU General Public License Version 3](http://www.gnu.org/meta/licenses/gpl-3.0-standalone.html)* (see document [LICENSE.md](http://github.com/optimizationBenchmarking/tspSuite/tree/master/LICENSE.md)).

## The TSP

The Traveling Salesman Problem (<a href="https://en.wikipedia.org/wiki/Travelling_salesman_problem">TSP</a>) is one of
the oldest and most well-researched combinatorial problems in logistics
planning and operations research as a whole.  In this problem, a set of <span style="font-style:italic">n</span> cities
(nodes in a graph) are given and the goal is to find the tour
that visits each of the cities exactly once and then returns back to its origin
with the lowest possible distance (or cost). Two cities <span style="font-style:italic">i</span> and <span style="font-style:italic">j</span>
have the distance <span style="font-style:italic">dist(i,j)</span>. The starting city
(to which the tour must return) can freely be chosen. The problem is known to be
NP-hard, but today, even large-scale TSP instances can be solved to
optimality.

The TSP is well-known and well-researched, but much works focus on the final
result quality or on whether a problem instance can be solved to optimality,
i.e., whether the globally shortest round-trip tour can be found. Especially
achieving the latter with a given algorithm may require a long runtime. The
former, the final solution quality obtained with a TSP solver, does not give
much information if we do not know the runtime necessary to reach it.

## Contact

The main author, copyright holder, and corresponding author of
*the project* is Dr. Thomas Weise.

**Dr. Thomas Weise**  
Nature Inspired Computation and Applications Laboratory (NICAL)  
USTC-Birmingham Joint Research Institute in Intelligent Computation and Its Applications (UBRI)  
School of Computer Science and Technology (SCST)  
University of Science and Technology of China (USTC)  
West Campus, Science and Technology Building, West Wing, Room 601  
Huangshan Road/Feixi Road, Hefei 230027, Anhui, China  
Web:    [http://www.it-weise.de/](http://www.it-weise.de/)  
Email:  [tweise@gmx.de](mailto:tweise@gmx.de), [tweise@ustc.edu.cn](mailto:tweise@ustc.edu.cn)