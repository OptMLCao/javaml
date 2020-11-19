/**
 * This file is part of the Java Machine Learning Library
 * <p>
 * The Java Machine Learning Library is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * <p>
 * The Java Machine Learning Library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with the Java Machine Learning Library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 * <p>
 * Copyright (c) 2006-2012, Thomas Abeel
 * <p>
 * Project: http://java-ml.sourceforge.net/
 */
package net.sf.javaml.clustering;

import java.io.File;

import org.junit.Test;
import org.junit.experimental.theories.ParametersSuppliedBy;

import lombok.extern.slf4j.Slf4j;
import net.sf.javaml.clustering.evaluation.AICScore;
import net.sf.javaml.clustering.evaluation.BICScore;
import net.sf.javaml.clustering.evaluation.ClusterEvaluation;
import net.sf.javaml.clustering.evaluation.SumOfSquaredErrors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;

/**
 * Shows how to use the different cluster evaluation measure that are
 * implemented in Java-ML.
 *
 * @author Thomas Abeel
 * @see net.sf.javaml.clustering.evaluation
 */
@Slf4j
public class TutorialClusterEvaluation {

    @Test
    public void testKMeans() throws Exception {
        String filePath = "./src/test/resources/net.sf.javaml/data/devtools/iris.data";
        // Load a dataset.
        Dataset data = FileHandler.loadDataset(new File(filePath), 4, ",");
        /*
         * Create a new instance of the KMeans algorithm that will create 3
         * clusters and create one that will make 4 clusters.
         */
        Clusterer km3 = new KMeans(3);
        Clusterer km4 = new KMeans(4);
        // Cluster the data, we will create 3 and 4 clusters.
        Dataset[] clusters3 = km3.cluster(data);
        Dataset[] clusters4 = km4.cluster(data);
        ClusterEvaluation aic = new AICScore();
        ClusterEvaluation bic = new BICScore();
        ClusterEvaluation sse = new SumOfSquaredErrors();
        double aicScore3 = aic.score(clusters3);
        double bicScore3 = bic.score(clusters3);
        double sseScore3 = sse.score(clusters3);
        double aicScore4 = aic.score(clusters4);
        double bicScore4 = bic.score(clusters4);
        double sseScore4 = sse.score(clusters4);
        log.info("AIC score: " + aicScore3 + "\t" + aicScore4);
        log.info("BIC score: " + bicScore3 + "\t" + bicScore4);
        log.info("Sum of squared errors: " + sseScore3 + "\t" + sseScore4);
    }
}
