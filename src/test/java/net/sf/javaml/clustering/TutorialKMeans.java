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

import lombok.extern.slf4j.Slf4j;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;

/**
 * This tutorial shows how to use a clustering algorithm to cluster a data set.
 *
 * @author Thomas Abeel
 */
@Slf4j
public class TutorialKMeans {

    @Test
    public void testKMeans() throws Exception {
        String filePath = "./src/test/resources/net.sf.javaml/data/devtools/iris.data";
        /* Load a dataset */
        Dataset data = FileHandler.loadDataset(new File(filePath), 4, ",");
        /*
         * Create a new instance of the KMeans algorithm, with no options
         * specified. By default this will generate 4 clusters.
         */
        Clusterer km = new KMeans();
        /*
         * Cluster the data, it will be returned as an array of data sets, with
         * each dataset representing a cluster
         */
        Dataset[] clusters = km.cluster(data);
        log.info("Cluster count: " + clusters.length);
    }

}
