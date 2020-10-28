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

import java.io.Serializable;
import java.util.Map;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;

/**
 * Interface for all classifiers.
 * 分类器.
 *
 * @author Thomas Abeel
 */
public interface Classifier extends Serializable {
    /**
     * Create a classifier from the given data set.
     * 通过训练数据集构建分类器.
     *
     * @param data the data set to be used to create the classifier
     */

    void buildClassifier(Dataset data);

    /**
     * Classify the instance according to this classifier.
     * 通过分类器对测试样例进行分类.
     *
     * @param instance the instance to be classified
     * @return the class to which this instance belongs or null if it doesn't
     * belong to any of the known classes.
     */
    Object classify(Instance instance);

    /**
     * 分类器输出训练样例数据得到的分布比例.
     * <p>
     * Generate the membership distribution for this instance using this
     * classifier. All values should be in the interval [0,1]
     * <p>
     * Note: The returned map may not contain a value for all classes that were
     * present in the data set used for training. If the map does not contain a
     * value, the value for that class equals zero.
     *
     * @param instance the instance to be classified
     * @return an array with membership degrees for all the various classes in
     * the data set
     */
    Map<Object, Double> classDistribution(Instance instance);

}
