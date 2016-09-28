package org.logisticPlanning.tsp.benchmarking.instances;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer;
import org.logisticPlanning.utils.math.statistics.aggregates.StatisticInfo;

/** print instance features */
public class PrintInstanceFeatures {

  /** the class prefix */
  private static final String CLASS_PREFIX = "TSPSuiteInput."; //$NON-NLS-1$

  /** the description prefix */
  private static final String VAR_PREFIX = "var_"; //$NON-NLS-1$

  /**
   * the main routine
   *
   * @param args
   *          the arguments
   */
  public static final void main(final String[] args) {

    final __Holder[] data;

    data = new __Holder[] {
        new __Holder(Instance.A280, "Drilling problem (Ludwig).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.ALI535,
            "535 Airports around the globe (Padberg/Rinaldi).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "GEO", //$NON-NLS-1$ ,
            "geographical distances"), //$NON-NLS-1$

        new __Holder(Instance.ATT48,
            "48 capitals of the US (Padberg/Rinaldi).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "ATT", //$NON-NLS-1$ ,
            "pseudo-Euclidean distance in two dimensions"), //$NON-NLS-1$

        new __Holder(Instance.ATT532,
            "The so-called AT&amp,T532 is 532 city problem solved to optimality by Padberg and Rinaldi using branch-and-cut methods.", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "ATT", //$NON-NLS-1$ ,
            "pseudo-Euclidean distance in two dimensions"), //$NON-NLS-1$

        new __Holder(Instance.BAYG29,
            "29 Cities in Bavaria, geographical distances (Gr&ouml,tschel,J&uuml,nger,Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "GEO", //$NON-NLS-1$ ,
            "geographical distances"), //$NON-NLS-1$

        new __Holder(Instance.BAYS29,
            "29 cities in Bavaria, street distances (Gr&ouml,tschel,J&uuml,nger,Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$
        // "FULL_MATRIX"
        new __Holder(Instance.BERLIN52,
            "52 locations in Berlin (Gr&ouml,tschel).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.BIER127,
            "127 Bierg&auml,rten (&quot,Beer Gardens&quot,, open-air beer restaurants) in Augsburg, Germany (J&uuml,nger/Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.BRAZIL58, "58 cities in Brazil (Ferreira).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "GEO", //$NON-NLS-1$ ,
            "geographical distances"), //$NON-NLS-1$

        new __Holder(Instance.BRD14051,
            "West-Germany in the borders of 1989 (Bachem/Wottawa).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.BRG180,
            "Bridge tournament problem (Rinaldi).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "BRIDGE", //$NON-NLS-1$
            "Bridge tournament problem"), //$NON-NLS-1$

        new __Holder(Instance.BURMA14, "14 cities in Burma (Zaw Win).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "GEO", //$NON-NLS-1$ ,
            "geographical distances"), //$NON-NLS-1$

        new __Holder(Instance.CH130, "130 city problem (Churritz).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.CH150, "150 city Problem (churritz).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.D198, "Drilling problem (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.D493, "Drilling problem (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.D657, "Drilling problem (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.D1291, "Drilling problem (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.D1655, "Drilling problem (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.D2103, "Drilling problem (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.D15112, "Germany-Problem (A.Rohe).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.D18512,
            "Germany (united with GDR, the former East Germany) (Bachem/Wottawa).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.DANTZIG42, "42 cities (Dantzig).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "GEO", //$NON-NLS-1$ ,
            "geographical distances"), //$NON-NLS-1$

        new __Holder(Instance.DSJ1000,
            "Clustered random problem (Johnson).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "CEIL_2D", //$NON-NLS-1$
            "rounded-up Euclidean distance in 2D plane"), //$NON-NLS-1$

        new __Holder(Instance.EIL51,
            "51-city problem (Christofides/Eilon).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.EIL76,
            "76-city problem (Christofides/Eilon).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.EIL101,
            "101-city problem (Christofides/Eilon).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.FL417, "Drilling problem (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.FL1400, "Drilling problem (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.FL1577, "Drilling problem (Reinelt)", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.FL3795, "Drilling problem (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.FNL4461,
            "The new five provinces of Germany, i.e., the former GDR (Eastern Germany) (Bachem/Wottawa).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.FRI26, "26 cities (Fricker).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "GEO", //$NON-NLS-1$ ,
            "geographical distances"), //$NON-NLS-1$

        new __Holder(Instance.GIL262, "262-city problem (Gillet/Johnson).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.GR17, "17-city problem (Gr&ouml,tschel).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$
        // "LOWER_DIAG_ROW"

        new __Holder(Instance.GR21, "21-city problem (Gr&ouml,tschel).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$
        // "LOWER_DIAG_ROW"

        new __Holder(Instance.GR24, "24-city problem (Gr&ouml,tschel).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$
        // "LOWER_DIAG_ROW"

        new __Holder(Instance.GR48, "48-city problem (Gr&ouml,tschel).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$
        // "LOWER_DIAG_ROW"

        new __Holder(Instance.GR96,
            "Africa-Subproblem of 666-city TSP (Gr&ouml,tschel).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "GEO", //$NON-NLS-1$ ,
            "geographical distances"), //$NON-NLS-1$

        new __Holder(Instance.GR120,
            "120 cities in Germany (Gr&ouml,tschel).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "GEO", //$NON-NLS-1$ ,
            "geographical distances"), //$NON-NLS-1$

        new __Holder(Instance.GR137,
            "America-Subproblem of 666-city TSP (Gr&ouml,tschel).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "GEO", //$NON-NLS-1$ ,
            "geographical distances"), //$NON-NLS-1$

        new __Holder(Instance.GR202,
            "Europe-Subproblem of 666-city TSP (Gr&ouml,tschel).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "GEO", //$NON-NLS-1$ ,
            "geographical distances"), //$NON-NLS-1$

        new __Holder(Instance.GR229,
            "Asia/Australia-Subproblem of 666-city TSP (Gr&ouml,tschel).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "GEO", //$NON-NLS-1$ ,
            "geographical distances"), //$NON-NLS-1$

        new __Holder(Instance.GR431,
            "Europe/Asia/Australia-Subproblem of 666-city TSP (Gr&ouml,tschel).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "GEO", //$NON-NLS-1$ ,
            "geographical distances"), //$NON-NLS-1$

        new __Holder(Instance.GR666,
            "666 cities around the world (Gr&ouml,tschel).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "GEO", //$NON-NLS-1$ ,
            "geographical distances"), //$NON-NLS-1$

        new __Holder(Instance.HK48, "48-city problem (Held/Karp).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$
        // "LOWER_DIAG_ROW"

        new __Holder(Instance.KROA100,
            "100-city problem A (Krolak/Felts/Nelson).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.KROB100,
            "100-city problem B (Krolak/Felts/Nelson).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.KROC100,
            "100-city problem C (Krolak/Felts/Nelson).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.KROD100,
            "100-city problem D (Krolak/Felts/Nelson).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.KROE100,
            "100-city problem E (Krolak/Felts/Nelson).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.KROA150,
            "150-city problem A (Krolak/Felts/Nelson).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.KROB150,
            "150-city problem B (Krolak/Felts/Nelson).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.KROA200,
            "200-city problem A (Krolak/Felts/Nelson).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.KROB200,
            "200-city problem B (Krolak/Felts/Nelson).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.LIN105,
            "105-city problem (Subproblem of lin318).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.LIN318,
            "The problem is posed by Lin and Kernighan as an open tour with fixed ends, but it can easily be converted to a TSP. Padberg and Gr&ouml,tschel used a combination of cutting-plane and branch-and-bound methods to find the optimal tour for this problem.", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.NRW1379,
            "1379 Orte in Nordrhein-Westfalen (Bachem/Wottawa).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.P654, "Drilling problem (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.PA561, "561-city problem (Kleinschmidt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$
        // LOWER_DIAG_ROW,

        new __Holder(Instance.PCB442,
            "Drilling problem (Gr&ouml,tschel/J&uuml,nger/Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.PCB1173,
            "Drilling problem (J&uuml,nger/Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.PCB3038,
            "Drilling problem (Junger/Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.PLA7397, "Programmed logic array (Johnson).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "CEIL_2D", //$NON-NLS-1$
            "rounded-up Euclidean distance in 2D plane"), //$NON-NLS-1$

        new __Holder(Instance.PLA33810,
            "Programmed logic array (Johnson).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "CEIL_2D", //$NON-NLS-1$
            "rounded-up Euclidean distance in 2D plane"), //$NON-NLS-1$

        new __Holder(Instance.PLA85900,
            "Programmed logic array (Johnson).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "CEIL_2D", //$NON-NLS-1$
            "rounded-up Euclidean distance in 2D plane"), //$NON-NLS-1$

        new __Holder(Instance.PR76, "76-city problem (Padberg/Rinaldi).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.PR107, "107-city problem (Padberg/Rinaldi).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.PR124, "124-city problem (Padberg/Rinaldi).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.PR136, "136-city problem (Padberg/Rinaldi).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.PR144, "144-city problem (Padberg/Rinaldi).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.PR152, "152-city problem (Padberg/Rinaldi).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.PR226, "226-city problem (Padberg/Rinaldi).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.PR264, "264-city problem (Padberg/Rinaldi).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.PR299, "299-city problem (Padberg/Rinaldi).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.PR439, "439-city problem (Padberg/Rinaldi).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.PR1002,
            "1002-city problem (Padberg/Rinaldi)", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.PR2392,
            "2392-city problem (Padberg/Rinaldi).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.RAT99, "Rattled grid (Pulleyblank).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.RAT195, "Rattled grid (Pulleyblank).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.RAT575, "Rattled grid (Pulleyblank).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.RAT783,
            "Rattled grid (Pulleyblank): The city positions of this problem are obtained by small, random displacements from a regular 27&times,29 lattice. This problem has been solved to optimality by Cook et al.", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.RD100, "100-city random TSP (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.RD400, "400-city random TSP (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.RL1304, "1304-city TSP (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.RL1323, "1323-city TSP (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.RL1889, "1889-city TSP (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.RL5915, "5915-city TSP (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.RL5934, "5934-city TSP (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.RL11849, "11849-city TSP (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.SI175, "TSP (M. Hofmeister).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$
        // "UPPER_DIAG_ROW"

        new __Holder(Instance.SI535, "TSP (M. Hofmeister).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$
        // "UPPER_DIAG_ROW"

        new __Holder(Instance.SI1032, "TSP (M. Hofmeister).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$
        // "UPPER_DIAG_ROW"

        new __Holder(Instance.ST70, "70-city problem (Smith/Thompson).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.SWISS42,
            "42 cities in Switzerland (Fricker).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "GEO", //$NON-NLS-1$ ,
            "geographical distances"), //$NON-NLS-1$

        new __Holder(Instance.TS225,
            "225-city problem (J&uuml,nger,R&auml,cke,Tsch&ouml,cke).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.TSP225, "A TSP problem (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.U159, "Drilling problem (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.U574, "Drilling problem (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.U724, "Drilling problem (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.U1060, "Drilling problem problem (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.U1432, "Drilling problem (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.U1817, "Drilling problem (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.U2152, "Drilling problem (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.U2319, "Drilling problem (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.ULYSSES16,
            "Odyssey of Ulysses (Gr&ouml,tschel/Padberg).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "GEO", //$NON-NLS-1$ ,
            "geographical distances"), //$NON-NLS-1$

        new __Holder(Instance.ULYSSES22,
            "Odyssey of Ulysses (Gr&ouml,tschel/Padberg).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "GEO", //$NON-NLS-1$ ,
            "geographical distances"), //$NON-NLS-1$

        new __Holder(Instance.USA13509,
            "Cities with population at least 500 in the continental US. Contributed by David Applegate and Andre Rohe, based on the data set &quot,US.lat-long&quot, from the ftp site <a href=\"ftp://ftp.cs.toronto.edu\">ftp.cs.toronto.edu</a>. The file US.lat-long.Z can be found in the directory /doc/geography.", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.VM1084, "1084-city problem (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.VM1748, "1784-city problem (Reinelt).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "EUC_2D", //$NON-NLS-1$
            "nodes are points in the two-dimensional Euclidean plane"), //$NON-NLS-1$

        new __Holder(Instance.BR17, "17 city problem (Repetto).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$ "FULL_MATRIX"

        new __Holder(Instance.FT53, "Asymmetric TSP (Fischetti).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$ "FULL_MATRIX"

        new __Holder(Instance.FT70, "Asymmetric TSP (Fischetti).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$ "FULL_MATRIX"

        new __Holder(Instance.FTV33, "Asymmetric TSP (Fischetti).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$ "FULL_MATRIX"

        new __Holder(Instance.FTV35, "Asymmetric TSP (Fischetti).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$ "FULL_MATRIX"

        new __Holder(Instance.FTV38, "Asymmetric TSP (Fischetti).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$ "FULL_MATRIX"

        new __Holder(Instance.FTV44, "Asymmetric TSP (Fischetti).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$ "FULL_MATRIX"

        new __Holder(Instance.FTV47, "Asymmetric TSP (Fischetti).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$ "FULL_MATRIX"

        new __Holder(Instance.FTV55, "Asymmetric TSP (Fischetti).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$ "FULL_MATRIX"

        new __Holder(Instance.FTV64, "Asymmetric TSP (Fischetti).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$ "FULL_MATRIX"

        new __Holder(Instance.FTV70, "Asymmetric TSP (Fischetti).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$ "FULL_MATRIX"

        new __Holder(Instance.FTV170, "Asymmetric TSP (Fischetti).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$ "FULL_MATRIX"

        new __Holder(Instance.KRO124P, "Asymmetric TSP (Fischetti).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$ "FULL_MATRIX"

        new __Holder(Instance.P43, "Asymmetric TSP (Repetto,Pekny).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$ "FULL_MATRIX"

        new __Holder(Instance.RBG323,
            "Stacker crane application (Ascheuer).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$ "FULL_MATRIX"

        new __Holder(Instance.RBG358,
            "Stacker crane application (Ascheuer).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$ "FULL_MATRIX"

        new __Holder(Instance.RBG403,
            "Stacker crane application (Ascheuer).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$ "FULL_MATRIX"

        new __Holder(Instance.RBG443,
            "Stacker crane application (Ascheuer).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$ "FULL_MATRIX"

        new __Holder(Instance.RY48P, "Asymmetric TSP (Fischetti).", //$NON-NLS-1$
            PrintInstanceFeatures.CLASS_PREFIX + "MATRIX", //$NON-NLS-1$ ,
            "distances provided as matrix, origin unclear"), //$NON-NLS-1$ "FULL_MATRIX"
    };

    Arrays.sort(data);
    for (final __Holder holder : data) {
      holder.run();
    }
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    PrintInstanceFeatures.__flush();
  }

  /** the described feature values */
  private static final HashMap<String, HashSet<Object>> DESCRIBED_FEATURES = new HashMap<>();

  /**
   * print a feature value
   *
   * @param feature
   *          the feature
   * @param desc
   *          the feature description
   * @param value
   *          the value
   * @param valueDesc
   *          the value description
   */
  private static final void __featureValue(final String feature,
      final String desc, final Object value, final String valueDesc) {
    System.out.print("i.setFeatureValue(");//$NON-NLS-1$
    System.out.print(feature);
    System.out.println(',');

    if (PrintInstanceFeatures.DESCRIBED_FEATURES.containsKey(feature)) {
      System.out.println("null,");//$NON-NLS-1$
    } else {
      PrintInstanceFeatures.DESCRIBED_FEATURES.put(feature,
          new HashSet<>());
      System.out.print(PrintInstanceFeatures.__print(desc));
      System.out.print(',');
    }

    System.out.print(PrintInstanceFeatures.__print(value));
    System.out.print(',');

    System.out.print(PrintInstanceFeatures.__print(valueDesc));
    System.out.println(");");//$NON-NLS-1$
  }

  /**
   * print a given instance
   *
   * @param instance
   *          the instance
   * @param desc
   *          the description
   * @param type
   *          the type
   * @param typeDesc
   *          the type description
   */
  static final void _makeInstance(final Instance instance,
      final String desc, final String type, final String typeDesc) {
    System.out.println(
        "try (final IInstanceContext i = esb.createInstance()) {"); //$NON-NLS-1$

    System.out.print("i.setName(TSPSuiteInput.");//$NON-NLS-1$
    System.out.print(instance.name().toUpperCase());
    System.out.println(");"); //$NON-NLS-1$

    if (desc != null) {
      System.out.print("i.setDescription(");//$NON-NLS-1$
      System.out.print(PrintInstanceFeatures.__print(desc));
      System.out.println(");"); //$NON-NLS-1$
    }

    System.out.print(//
        "i.setLowerBound(TSPSuiteInput.LENGTH, ");//$NON-NLS-1$
    System.out.print(
        PrintInstanceFeatures.__print(Long.valueOf(instance.optimum())));
    System.out.println(");"); //$NON-NLS-1$

    PrintInstanceFeatures.__featureValue(
        PrintInstanceFeatures.CLASS_PREFIX + "SYMMETRIC", //$NON-NLS-1$
        "whether the TSP is symmetric, i.e., whether dist(i,j)=dist(j,i)", //$NON-NLS-1$
        Boolean.valueOf(instance.symmetric()), //
        instance.symmetric() ? "the TSP is symmetric" //$NON-NLS-1$
            : "the TSP is asymmetric");//$NON-NLS-1$

    PrintInstanceFeatures.__featureValue(
        PrintInstanceFeatures.CLASS_PREFIX + "DISTANCE_TYPE", //$NON-NLS-1$
        "the distance measure", //$NON-NLS-1$
        type, typeDesc);

    PrintInstanceFeatures.__featureValue(
        PrintInstanceFeatures.CLASS_PREFIX + "SCALE", //$NON-NLS-1$
        "the number of cities in the TSP", //$NON-NLS-1$
        Long.valueOf(instance.n()), null);

    DistanceComputer comp;
    try {
      comp = instance.load(10000);
    } catch (final Throwable error) {
      throw new RuntimeException(error);
    }
    PrintInstanceFeatures.__statFeatures(comp);

    System.out.println('}');
  }

  /** a map with the data */
  private static final LinkedHashMap<Object, String> OBJECTS = new LinkedHashMap<>();

  /**
   * print the given object
   *
   * @param object
   *          the object
   * @return the string to print
   */
  private static final String __print(final Object object) {
    String string;

    if (object == null) {
      return "null"; //$NON-NLS-1$
    }

    if (object instanceof String) {
      string = ((String) object);
      if (string.startsWith(PrintInstanceFeatures.CLASS_PREFIX)) {
        return string;
      }
    } else {
      if (object instanceof Boolean) {
        return (((Boolean) object).booleanValue() ? "Boolean.TRUE"//$NON-NLS-1$
            : "Boolean.FALSE");//$NON-NLS-1$
      }
    }

    string = PrintInstanceFeatures.OBJECTS.get(object);
    if (string == null) {
      string = PrintInstanceFeatures.VAR_PREFIX + Integer.toString(
          PrintInstanceFeatures.OBJECTS.size(), Character.MAX_RADIX) + '_';
      PrintInstanceFeatures.OBJECTS.put(object, string);
    }
    return string;
  }

  /** flush all cached objects */
  private static final void __flush() {
    Object value;
    for (final Entry<Object, String> entry : PrintInstanceFeatures.OBJECTS
        .entrySet()) {
      value = entry.getKey();

      if (value instanceof Byte) {
        System.out.print("final Byte ");//$NON-NLS-1$
        System.out.print(entry.getValue());
        System.out.print(" = Byte.valueOf((byte)");//$NON-NLS-1$
        System.out.print(((Byte) value).byteValue());
        System.out.println(");");//$NON-NLS-1$
      } else {
        if (value instanceof Short) {
          System.out.print("final Short ");//$NON-NLS-1$
          System.out.print(entry.getValue());
          System.out.print(" = Short.valueOf((short)");//$NON-NLS-1$
          System.out.print(((Short) value).shortValue());
          System.out.println(");");//$NON-NLS-1$
        } else {
          if (value instanceof Integer) {
            System.out.print("final Integer ");//$NON-NLS-1$
            System.out.print(entry.getValue());
            System.out.print(" = Integer.valueOf(");//$NON-NLS-1$
            System.out.print(((Integer) value).intValue());
            System.out.println(");");//$NON-NLS-1$
          } else {
            if (value instanceof Long) {
              System.out.print("final Long ");//$NON-NLS-1$
              System.out.print(entry.getValue());
              System.out.print(" = Long.valueOf(");//$NON-NLS-1$
              System.out.print(((Long) value).longValue());
              System.out.println("L);");//$NON-NLS-1$
            } else {
              if (value instanceof Float) {
                System.out.print("final Float ");//$NON-NLS-1$
                System.out.print(entry.getValue());
                System.out.print(" = Float.valueOf(");//$NON-NLS-1$
                System.out.print(
                    Float.toHexString(((Float) value).floatValue()));
                System.out.println(");");//$NON-NLS-1$
              } else {
                if (value instanceof Double) {
                  System.out.print("final Double ");//$NON-NLS-1$
                  System.out.print(entry.getValue());
                  System.out.print(" = Double.valueOf(");//$NON-NLS-1$
                  System.out.print(
                      Double.toHexString(((Double) value).doubleValue()));
                  System.out.println("d);");//$NON-NLS-1$
                } else {
                  if (value instanceof Boolean) {
                    System.out.print("final Boolean ");//$NON-NLS-1$
                    System.out.print(entry.getValue());
                    System.out.print(" = "); //$NON-NLS-1$
                    System.out.print(
                        ((Boolean) value).booleanValue() ? "Boolean.TRUE"//$NON-NLS-1$
                            : "Boolean.FALSE");//$NON-NLS-1$
                    System.out.println(';');
                  } else {
                    if (value instanceof Character) {
                      System.out.print("final Character ");//$NON-NLS-1$
                      System.out.print(entry.getValue());
                      System.out.print(" = Character.valueOf(");//$NON-NLS-1$
                      final char ch = ((Character) value).charValue();
                      if (ch == '\'') {
                        System.out.print('\\');
                      }
                      System.out.print(ch);
                      System.out.println(");");//$NON-NLS-1$
                    } else {
                      if (value instanceof String) {
                        if (((String) value).startsWith(
                            PrintInstanceFeatures.CLASS_PREFIX)) {
                          System.out.print("final String ");//$NON-NLS-1$
                          System.out.print(entry.getValue());
                          System.out.print(" = "); //$NON-NLS-1$
                          System.out.print(value);
                          System.out.print(';');
                        } else {
                          System.out.print("final String ");//$NON-NLS-1$
                          System.out.print(entry.getValue());
                          System.out.print(" = \"");//$NON-NLS-1$
                          System.out.print(((String) value).replace("\"", //$NON-NLS-1$
                              "\\\""));//$NON-NLS-1$ ;
                          System.out.println("\";//$NON-NLS-1$");//$NON-NLS-1$
                        }
                      } else {
                        throw new IllegalArgumentException(
                            value.toString());
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }

    }
  }

  /** the number of order features */
  private static final int ORDER_FEATURE_NUM = 7;

  /**
   * get the mean distance
   *
   * @param comp
   *          the distance computer
   * @return the mean distance
   */
  private static final double __statFeatures(final DistanceComputer comp) {
    final int numNodes;
    long totalDistanceSum, currentDistanceSum, copy1, copy2, temp, oldsum;
    StatisticInfo info, cityInfo;
    int i, j, currentDistance, distancesShorterThanMean, totalMinDistance,
        totalMaxDistance;
    int[] distancesFromNode;
    double[] medianDistancesFromNodes;
    final double mean, medmed;
    final long totalDistanceCount;
    long[] smallestAvgDistances, largestAvgDistances;
    StatisticInfo[] nearest, farthest;

    totalDistanceSum = 0;
    numNodes = comp.n();
    info = new StatisticInfo();
    totalMinDistance = Integer.MAX_VALUE;
    totalMaxDistance = Integer.MIN_VALUE;
    distancesFromNode = new int[numNodes - 1];
    medianDistancesFromNodes = new double[numNodes];

    smallestAvgDistances = new long[PrintInstanceFeatures.ORDER_FEATURE_NUM];
    Arrays.fill(smallestAvgDistances, Long.MAX_VALUE);
    largestAvgDistances = new long[PrintInstanceFeatures.ORDER_FEATURE_NUM];
    Arrays.fill(largestAvgDistances, Long.MIN_VALUE);

    cityInfo = new StatisticInfo();
    for (i = numNodes; i > 0; --i) {
      currentDistanceSum = 0L;
      for (j = numNodes; j > 0; --j) {
        if (i == j) {
          continue;
        }
        currentDistance = comp.distance(i, j);
        if (currentDistance < 0) {
          throw new IllegalStateException();
        }
        oldsum = totalDistanceSum;
        totalDistanceSum += currentDistance;
        if (totalDistanceSum < oldsum) {
          throw new IllegalStateException();
        }
        info.visitInt(currentDistance);
        if (currentDistance < totalMinDistance) {
          totalMinDistance = currentDistance;
        }
        if (currentDistance > totalMaxDistance) {
          totalMaxDistance = currentDistance;
        }
        currentDistanceSum += currentDistance;
        distancesFromNode[((j < i) ? j : (j - 1)) - 1] = currentDistance;
      }
      Arrays.sort(distancesFromNode);
      medianDistancesFromNodes[i - 1] = ((numNodes & 1) == 0)
          ? distancesFromNode[(numNodes - 1) >>> 1]
          : ((0.5d * distancesFromNode[(numNodes - 1) >>> 1])
              + (0.5d * distancesFromNode[(numNodes - 1) >>> 1]));

      copy1 = copy2 = currentDistanceSum;
      for (j = 0; j < PrintInstanceFeatures.ORDER_FEATURE_NUM; j++) {
        if (copy1 < smallestAvgDistances[j]) {
          temp = smallestAvgDistances[j];
          smallestAvgDistances[j] = copy1;
          copy1 = temp;
        }
        if (copy2 > largestAvgDistances[j]) {
          temp = largestAvgDistances[j];
          largestAvgDistances[j] = copy2;
          copy2 = temp;
        }

        cityInfo.visitDouble(currentDistanceSum / (numNodes - 1d));
      }
    }
    Arrays.sort(medianDistancesFromNodes);

    totalDistanceCount = (numNodes * (numNodes - 1L));
    temp = (totalDistanceSum / totalDistanceCount);
    if ((temp * totalDistanceCount) == totalDistanceSum) {
      mean = temp;
    } else {
      mean = (totalDistanceSum / ((double) totalDistanceCount));
    }

    PrintInstanceFeatures.__featureValue(
        PrintInstanceFeatures.CLASS_PREFIX + "DIST_CV", //$NON-NLS-1$
        "The coefficient of variation of the city distances, i.e., the standard deviation of the distances divided by the mean distance.", //$NON-NLS-1$
        Double.valueOf(info.getStandardDeviation() / mean), null);

    PrintInstanceFeatures.__featureValue(
        PrintInstanceFeatures.CLASS_PREFIX + "DIST_SKEWNESS", //$NON-NLS-1$
        "The skewness of all city distances.", //$NON-NLS-1$
        Double.valueOf(info.getSkewness()), null);

    PrintInstanceFeatures.__featureValue(
        PrintInstanceFeatures.CLASS_PREFIX + "DIST_KURTOSIS", //$NON-NLS-1$
        "The kurtosis of all city distances.", //$NON-NLS-1$
        Double.valueOf(info.getKurtosis()), null);

    medmed = ((numNodes & 1) == 0)
        ? ((0.5d * medianDistancesFromNodes[(numNodes >> 1) - 1])
            + (0.5d * medianDistancesFromNodes[numNodes >> 1]))
        : medianDistancesFromNodes[(numNodes >> 1)];
    PrintInstanceFeatures.__featureValue(
        PrintInstanceFeatures.CLASS_PREFIX + "DIST_MED_MED_DIV_MEAN", //$NON-NLS-1$
        "The median of the median city distances starting at each city, divided by the mean distance.", //$NON-NLS-1$
        Double.valueOf(medmed / mean), null);
    medianDistancesFromNodes = null;

    PrintInstanceFeatures.__featureValue(
        PrintInstanceFeatures.CLASS_PREFIX + "AVG_DIST_CV", //$NON-NLS-1$
        "We compute the average distance from a city to all of its neighbors and this is the coefficient of variation over all these averages.", //$NON-NLS-1$
        Double.valueOf(cityInfo.getCoefficientOfVariation()), null);

    PrintInstanceFeatures.__featureValue(
        PrintInstanceFeatures.CLASS_PREFIX + "AVG_DIST_SKEWNESS", //$NON-NLS-1$
        "We compute the average distance from a city to all of its neighbors and this is the skewness over all these averages.", //$NON-NLS-1$
        Double.valueOf(cityInfo.getSkewness()), null);

    PrintInstanceFeatures.__featureValue(
        PrintInstanceFeatures.CLASS_PREFIX + "AVG_DIST_KURTOSIS", //$NON-NLS-1$
        "We compute the average distance from a city to all of its neighbors and this is the kurtosis over all these averages.", //$NON-NLS-1$
        Double.valueOf(cityInfo.getKurtosis()), null);

    for (i = 0; i < PrintInstanceFeatures.ORDER_FEATURE_NUM; i++) {
      PrintInstanceFeatures.__featureValue(
          PrintInstanceFeatures.CLASS_PREFIX + "SMALLEST_AVG_DISTANCE_" //$NON-NLS-1$
              + (i + 1),
          "We compute the average distance from a city to all of its neighbors. This is the " //$NON-NLS-1$
              + i
              + "th smallest such average distance among all cities, divided by the mean city distance.", //$NON-NLS-1$
          Double.valueOf(
              smallestAvgDistances[i] / ((numNodes - 1) * mean)),
          null);
      PrintInstanceFeatures.__featureValue(
          PrintInstanceFeatures.CLASS_PREFIX + "LARGEST_AVG_DISTANCE_" //$NON-NLS-1$
              + (i + 1),
          "We compute the average distance from a city to all of its neighbors. This is the " //$NON-NLS-1$
              + i
              + "th largest such average distance among all cities, divided by the mean city distance.", //$NON-NLS-1$
          Double.valueOf(
              smallestAvgDistances[i] / ((numNodes - 1) * mean)),
          null);
    }

    distancesShorterThanMean = 0;
    nearest = new StatisticInfo[PrintInstanceFeatures.ORDER_FEATURE_NUM];
    farthest = new StatisticInfo[PrintInstanceFeatures.ORDER_FEATURE_NUM];
    for (i = PrintInstanceFeatures.ORDER_FEATURE_NUM; (--i) >= 0; ) {
      nearest[i] = new StatisticInfo();
      farthest[i] = new StatisticInfo();
    }

    for (i = numNodes; i > 0; --i) {
      for (j = numNodes; j > 0; --j) {
        if (i == j) {
          continue;
        }
        currentDistance = comp.distance(i, j);
        if (currentDistance < 0) {
          throw new IllegalStateException();
        }
        if (currentDistance < mean) {
          distancesShorterThanMean++;
        }
        distancesFromNode[((j < i) ? j : (j - 1)) - 1] = currentDistance;
      }
      Arrays.sort(distancesFromNode);

      for (i = PrintInstanceFeatures.ORDER_FEATURE_NUM; (--i) >= 0; --i) {
        nearest[i].visitDouble(distancesFromNode[i] / mean);
        farthest[i]
            .visitDouble(distancesFromNode[numNodes - 2 - i] / mean);
      }
    }

    PrintInstanceFeatures.__featureValue(
        PrintInstanceFeatures.CLASS_PREFIX
            + "FRAC_OF_DISTS_SHORTER_THAN_MEAN", //$NON-NLS-1$
        "The fraction of distances shorter than the mean.", //$NON-NLS-1$
        Double.valueOf(distancesShorterThanMean / mean), null);

    for (i = 0; i < PrintInstanceFeatures.ORDER_FEATURE_NUM; i++) {
      PrintInstanceFeatures.__featureValue(
          PrintInstanceFeatures.CLASS_PREFIX + "NEAREST_NEIGHBOR_" //$NON-NLS-1$
              + (i + 1) + "_AVG", //$NON-NLS-1$
          "The average of the distance to the " //$NON-NLS-1$
              + i
              + "th nearest neighbor of each city divided by the mean city distance.", //$NON-NLS-1$
          Double.valueOf(nearest[i].getArithmeticMean()), null);
      PrintInstanceFeatures.__featureValue(
          PrintInstanceFeatures.CLASS_PREFIX + "NEAREST_NEIGHBOR_" //$NON-NLS-1$
              + (i + 1) + "_STDDEV", //$NON-NLS-1$
          "The standard deviation of the distance to the " //$NON-NLS-1$
              + i
              + "th nearest neighbor of each city divided by the mean city distance.", //$NON-NLS-1$
          Double.valueOf(nearest[i].getStandardDeviation()), null);
      PrintInstanceFeatures.__featureValue(
          PrintInstanceFeatures.CLASS_PREFIX + "NEAREST_NEIGHBOR_" //$NON-NLS-1$
              + (i + 1) + "_CV", //$NON-NLS-1$
          "The coefficient of variation of the distance to the " //$NON-NLS-1$
              + i
              + "th nearest neighbor of each city divided by the mean city distance.", //$NON-NLS-1$
          Double.valueOf(nearest[i].getCoefficientOfVariation()), null);
      PrintInstanceFeatures.__featureValue(
          PrintInstanceFeatures.CLASS_PREFIX + "NEAREST_NEIGHBOR_" //$NON-NLS-1$
              + (i + 1) + "_SKEW", //$NON-NLS-1$
          "The skewness of the distance to the " //$NON-NLS-1$
              + i
              + "th nearest neighbor of each city divided by the mean city distance.", //$NON-NLS-1$
          Double.valueOf(nearest[i].getSkewness()), null);
      PrintInstanceFeatures.__featureValue(
          PrintInstanceFeatures.CLASS_PREFIX + "NEAREST_NEIGHBOR_" //$NON-NLS-1$
              + (i + 1) + "_KURTOSIS", //$NON-NLS-1$
          "The kurtosis of the distance to the " //$NON-NLS-1$
              + i
              + "th nearest neighbor of each city divided by the mean city distance.", //$NON-NLS-1$
          Double.valueOf(nearest[i].getKurtosis()), null);

      PrintInstanceFeatures.__featureValue(
          PrintInstanceFeatures.CLASS_PREFIX + "FAREST_NEIGHBOR_" + (i + 1) //$NON-NLS-1$
              + "_AVG", //$NON-NLS-1$
          "The average of the distance to the " //$NON-NLS-1$
              + i
              + "th farthest neighbor of each city divided by the mean city distance.", //$NON-NLS-1$
          Double.valueOf(farthest[i].getArithmeticMean()), null);
      PrintInstanceFeatures.__featureValue(
          PrintInstanceFeatures.CLASS_PREFIX + "FAREST_NEIGHBOR_" + (i + 1) //$NON-NLS-1$
              + "_STDDEV", //$NON-NLS-1$
          "The standard deviation of the distance to the " //$NON-NLS-1$
              + i
              + "th farthest neighbor of each city divided by the mean city distance.", //$NON-NLS-1$
          Double.valueOf(farthest[i].getStandardDeviation()), null);
      PrintInstanceFeatures.__featureValue(
          PrintInstanceFeatures.CLASS_PREFIX + "FAREST_NEIGHBOR_" + (i + 1) //$NON-NLS-1$
              + "_CV", //$NON-NLS-1$
          "The coefficient of variation of the distance to the " //$NON-NLS-1$
              + i
              + "th farthest neighbor of each city divided by the mean city distance.", //$NON-NLS-1$
          Double.valueOf(farthest[i].getCoefficientOfVariation()), null);
      PrintInstanceFeatures.__featureValue(
          PrintInstanceFeatures.CLASS_PREFIX + "FAREST_NEIGHBOR_" + (i + 1) //$NON-NLS-1$
              + "_SKEW", //$NON-NLS-1$
          "The skewness of the distance to the " //$NON-NLS-1$
              + i
              + "th farthest neighbor of each city divided by the mean city distance.", //$NON-NLS-1$
          Double.valueOf(farthest[i].getSkewness()), null);
      PrintInstanceFeatures.__featureValue(
          PrintInstanceFeatures.CLASS_PREFIX + "FAREST_NEIGHBOR_" + (i + 1) //$NON-NLS-1$
              + "_KURTOSIS", //$NON-NLS-1$
          "The kurtosis of the distance to the " //$NON-NLS-1$
              + i
              + "th farthest neighbor of each city divided by the mean city distance.", //$NON-NLS-1$
          Double.valueOf(farthest[i].getKurtosis()), null);

    }

    return mean;
  }

  /** the data holder */
  private static final class __Holder
      implements Runnable, Comparable<__Holder> {
    /** the instance */
    private final Instance m_instance;
    /** the desription */
    private final String m_desc;
    /** the type */
    private final String m_type;
    /** the type desription */
    private final String m_typeDesc;

    /**
     * print a given instance
     *
     * @param instance
     *          the instance
     * @param desc
     *          the description
     * @param type
     *          the type
     * @param typeDesc
     *          the type description
     */
    __Holder(final Instance instance, final String desc, final String type,
        final String typeDesc) {
      super();
      this.m_instance = instance;
      this.m_type = type;
      this.m_desc = desc;
      this.m_typeDesc = typeDesc;
    }

    /** {@inheritDoc} */
    @Override
    public void run() {
      PrintInstanceFeatures._makeInstance(this.m_instance, this.m_desc,
          this.m_type, this.m_typeDesc);
    }

    /** {@inheritDoc} */
    @Override
    public int compareTo(final __Holder o) {
      int res;

      res = Integer.compare(this.m_instance.n(), o.m_instance.n());
      if (res != 0) {
        return res;
      }

      if (this.m_instance.symmetric()) {
        if (!o.m_instance.symmetric()) {
          return -1;
        }
      } else {
        if (o.m_instance.symmetric()) {
          return 1;
        }
      }

      return this.m_instance.name().compareTo(o.m_instance.name());
    }

  }
}
