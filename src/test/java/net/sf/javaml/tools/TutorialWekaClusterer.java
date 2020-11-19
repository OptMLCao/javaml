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
package net.sf.javaml.tools;

import java.io.File;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;
import net.sf.javaml.tools.weka.WekaClusterer;
import weka.clusterers.XMeans;

/**
 * Tutorial how to use a Weka classifier in Java-ML.
 *
 * @author Thomas Abeel
 */
@Slf4j
public class TutorialWekaClusterer {

    @Test
    public void testWekaCluster() throws Exception {
        String filePath = "./src/test/resources/net.sf.javaml/data/devtools/iris.data";
        /* Load data */
        Dataset data = FileHandler.loadDataset(new File(filePath), 4, ",");
        /* Create Weka classifier */
        XMeans xm = new XMeans();
        /* Wrap Weka clusterer in bridge */
        Clusterer clusterer = new WekaClusterer(xm);
        /* Perform clustering */
        Dataset[] clusters = clusterer.cluster(data);
        /* Output results */
        log.info("cluster length {}", clusters.length);
    }

}
