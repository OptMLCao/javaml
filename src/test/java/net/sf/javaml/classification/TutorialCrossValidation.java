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
package net.sf.javaml.classification;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.junit.Test;

import net.sf.javaml.classification.evaluation.CrossValidation;
import net.sf.javaml.classification.evaluation.PerformanceMeasure;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;

/**
 * This tutorial shows how you can do cross-validation with Java-ML
 *
 * @author Thomas Abeel
 */
public class TutorialCrossValidation {
    /**
     * Default cross-validation with little options.
     */
    @Test
    public void testCrossValidation() {
        /* Load data */
        Dataset data = null;
        try {
            data = FileHandler.loadDataset(new File("/Users/caogaoli/IdeaProjects/javaml/src/test/resources/devtools/data/iris.data"), 4, ",");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* Construct KNN classifier */
        Classifier knn = new KNearestNeighbors(5);
        /* Construct new cross validation instance with the KNN classifier */
        CrossValidation cv = new CrossValidation(knn);
        /* Perform 5-fold cross-validation on the data set */
        Map<Object, PerformanceMeasure> p = cv.crossValidation(data);

        System.out.println("Accuracy=" + p.get("Iris-setosa").getAccuracy());
        System.out.println(p);
    }

}
