# [TSPSuite](http://optimizationbenchmarking.github.io/tspSuite/)

**This project is no longer actively developed or maintained.
This code works, but I will not update or improve it.
Check [moptipyapps](https://github.com/thomasWeise/moptipyapps) for a similar package for Python.**

## 1. Introduction
The [*TSPSuite*](https://github.com/thomasWeise/tspSuite) is a holistic benchmarking environment for algorithms solving the Traveling Salesperson Problem&nbsp;(TSP) written in Java.
It is based on the TSPLib benchmark cases and offers integrated support for implementing, testing benchmarking, and comparing algorithms.
It also features a large set of implemented algorithms.

In the *TSPSuite*, we focus on collecting information regarding how long an algorithm needs to reach a certain solution quality and what solution quality we can expect after a certain runtime.
This is especially interesting for comparing anytime algorithms, such as metaheuristics that step-by-step refine and combine solutions in order to obtain better tours.
For each algorithm tested, comprehensive logging information is collected regarding not only the solution quality and runtime (according to different time measures such as FEs and real time), but also the environment the algorithm was executed in and the parameters of the algorithm, rendering each log file self-explaining.
TSPSuite contains an evaluator utility which can load these log files
and create a <a href="https://en.wikipedia.org/wiki/LaTeX">LaTeX</a> or XHTML document summarizing an algorithm's performance from different perspectives and comparing different algorithms.
Finally, we also
implement a set of basic algorithms for solving TSPs.
All of this is done under
the *[GNU General Public License Version 3](http://www.gnu.org/meta/licenses/gpl-3.0-standalone.html)* (see document [LICENSE.md](http://github.com/thomasWeise/tspSuite/tree/master/LICENSE.md)).

## 2. The TSP
The Traveling Salesperson Problem&nbsp;(TSP) is one of  the oldest and most well-researched combinatorial problems in logistics planning and operations research as a whole. 
In this problem, a set of *n* cities (nodes in a graph) are given and the goal is to find the tour that visits each of the cities exactly once and then returns back to its origin with the lowest possible distance (or cost).
Two cities *i* and *j* have the distance *dist(i,j)*.
The starting city (to which the tour must return) can freely be chosen.
The problem is known to be NP-hard, but today, even large-scale TSP instances can be solved to  optimality.

The TSP is well-known and well-researched, but much works focus on the final result quality or on whether a problem instance can be solved to optimality, i.e., whether the globally shortest round-trip tour can be found.
Especially achieving the latter with a given algorithm may require a long runtime.
The former, the final solution quality obtained with a TSP solver, does not give much information if we do not know the runtime necessary to reach it.

## 3. Contact
If you have any questions or suggestions, please contact
Prof. Dr. [Thomas Weise](https://thomasweise.github.io) (汤卫思教授) of the 
School of Artificial Intelligence and Big Data ([人工智能与大数据学院](http://www.hfuu.edu.cn/aibd)) at
[Hefei University](http://www.hfuu.edu.cn/english) ([合肥大学](http://www.hfuu.edu.cn)) in
Hefei, Anhui, China (中国安徽省合肥市) via
email to [tweise@hfuu.edu.cn](mailto:tweise@hfuu.edu.cn) with CC to [tweise@ustc.edu.cn](mailto:tweise@ustc.edu.cn).
