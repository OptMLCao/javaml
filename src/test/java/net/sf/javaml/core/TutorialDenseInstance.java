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
package net.sf.javaml.core;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * This tutorial shows the very first step in using Java-ML. It will show you
 * how to create an {@link net.sf.javaml.core.Instance} that can later be used
 * in a {@link Dataset} and in the machine learning
 * algorithms.
 * <p>
 * In this class we only work with the {@link net.sf.javaml.core.DenseInstance}.
 * This type of instance has a value for each attribute and has an optional
 * class label.
 * <p>
 * {@jmlSource}
 *
 * @author Thomas Abeel
 * @version 0.1.7
 * @see net.sf.javaml.core.Instance
 * @see net.sf.javaml.core.DenseInstance
 * @see net.sf.javaml.tools.InstanceTools
 */
@Slf4j
public class TutorialDenseInstance {

    /**
     * Shows how to construct an instance.
     * <p>
     * Here we will construct an instance with 10 attributes.
     */
    @Test
    public void testDenseInstance() {
        /* values of the attributes. */
        double[] values = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        /*
         * The simplest incarnation of the DenseInstance constructor will only
         * take a double array as argument an will create an instance with given
         * values as attributes and no class value set. For unsupervised machine
         * learning techniques this is probably the most convenient constructor.
         */
        Instance instance = new DenseInstance(values);

        System.out.println("Instance with only values set: ");
        log.info("Instance with only values set {}", instance);
        System.out.println(instance);
        System.out.println();
        /*
         * To create instances that have a class value set, you can use the two
         * argument constructor which takes the values and the class value as
         * parameters.
         */
        Instance instanceWithClassValue = new DenseInstance(values, 1);

        System.out.println("Instance with class value set to 1: ");
        log.info("Instance with class value set to 1: {}", instanceWithClassValue);
        System.out.println(instanceWithClassValue);
        System.out.println();

    }

}
