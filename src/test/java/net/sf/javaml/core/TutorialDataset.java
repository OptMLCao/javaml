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

import java.util.SortedSet;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.sf.javaml.tools.InstanceTools;

/**
 * This tutorial show how to create a {@link Dataset} from a
 * collection of instances. This tutorial assumes you know how to create an
 * {@link net.sf.javaml.core.Instance}. To create instances for this tutorial
 * we will use a method from {@link net.sf.javaml.tools.InstanceTools} to create
 * random instances.
 * <p>
 * In this tutorial we will create a number of instances and group them in a
 * data set.
 * <p>
 * Basically a data set is a collection of instances.
 *
 * @author Thomas Abeel
 * @version 0.1.7
 * @see CreatingAnInstance
 * @see net.sf.javaml.core.Instance
 * @see Dataset
 * @see net.sf.javaml.core.DefaultDataset
 * @see net.sf.javaml.tools.InstanceTools
 */
@Slf4j
public class TutorialDataset {

    /**
     * Create a data set and put some instances in it.
     */
    @Test
    public void tesDataset() {
        Dataset data = new DefaultDataset();
        for (int i = 0; i < 10; i++) {
            Instance tmpInstance = InstanceTools.randomInstance(25);
            data.add(tmpInstance);
        }
        log.info("data {}", data);
        /* Retrieve all class values that are ever used in the data set */
        SortedSet<Object> classValues = data.classes();
        System.out.println(classValues);
        log.info("class values {}", classValues);
    }

}
