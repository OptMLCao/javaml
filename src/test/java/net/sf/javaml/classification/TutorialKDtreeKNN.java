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

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.data.FileHandler;

/**
 * This tutorial show how to use a the k-nearest neighbors classifier.
 *
 * @author Thomas Abeel
 */
@Slf4j
public class TutorialKDtreeKNN {

    @Test
    public void testKDtreeKNN() throws Exception {
        // Load a data set
        String filePath = "./src/test/resources/net.sf.javaml/data/devtools/iris.data";
        Dataset data = FileHandler.loadDataset(new File(filePath), 4, ",");
        // Construct a KNN classifier that uses 5 neighbors to make a decision.
        Classifier knn = new KDtreeKNN(5);
        knn.buildClassifier(data);
        // Load a data set, this can be a different one, but we will use the same one.
        Dataset dataForClassification = FileHandler.loadDataset(new File(filePath), 4, ",");
        // Counters for correct and wrong predictions.
        int correct = 0;
        int wrong = 0;
        // Classify all instances and check with the correct class values.
        for (Instance inst : dataForClassification) {
            Object predictedClassValue = knn.classify(inst);
            Object realClassValue = inst.classValue();
            if (predictedClassValue.equals(realClassValue)) {
                correct++;
            } else {
                wrong++;
            }
        }
        log.info("Correct predictions  " + correct);
        log.info("Wrong predictions " + wrong);
    }

}
