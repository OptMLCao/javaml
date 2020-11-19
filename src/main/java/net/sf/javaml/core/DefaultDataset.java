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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import net.sf.javaml.distance.DistanceMeasure;

/**
 * Provides a standard data set implementation.
 *
 * @author Thomas Abeel
 * @see Dataset
 */
public class DefaultDataset extends Vector<Instance> implements Dataset {

    private int maxAttributes = 0;

    private static final long serialVersionUID = 8586030444860912681L;

    // classes使用TreeSe, 天生具备排序属性;
    private TreeSet<Object> classes = new TreeSet<Object>();

    /**
     * Creates a data set that contains the provided instances
     *
     * @param collection collection with instances
     */
    public DefaultDataset(Collection<Instance> collection) {
        this.addAll(collection);
    }

    /**
     * Creates an empty data set.
     */
    public DefaultDataset() {
        // nothing to do.
    }

    private void check(Collection<? extends Instance> instances) {
        for (Instance instance : instances)
            check(instance);
    }

    private void check(Instance instance) {
        if (instance.classValue() != null) {
            classes.add(instance.classValue());
        }
        if (instance.noAttributes() > maxAttributes) {
            maxAttributes = instance.noAttributes();
        }
    }

    @Override
    public synchronized boolean addAll(Collection<? extends Instance> instances) {
        check(instances);
        return super.addAll(instances);
    }

    @Override
    public synchronized boolean addAll(int index, Collection<? extends Instance> instances) {
        check(instances);
        return super.addAll(index, instances);
    }

    @Override
    public void clear() {
        classes.clear();
        super.clear();
    }

    @Override
    public synchronized boolean add(Instance e) {
        check(e);
        return super.add(e);
    }

    @Override
    public void add(int index, Instance e) {
        check(e);
        super.add(index, e);
    }

    @Override
    public synchronized void addElement(Instance e) {
        check(e);
        super.addElement(e);
    }

    @Override
    public synchronized void insertElementAt(Instance e, int index) {
        check(e);
        super.insertElementAt(e, index);
    }

    @Override
    public synchronized void setElementAt(Instance e, int index) {
        check(e);
        super.setElementAt(e, index);
    }

    @Override
    public Instance instance(int index) {
        return super.get(index);
    }

    @Override
    public SortedSet<Object> classes() {
        // 使用TreeSet储存元素，故此无需再次排序，直接返回有序队列.
        return classes;
    }

    /**
     * Returns the k instances of the given data set that are the closest to the instance that is given as a parameter.
     *
     * @param distanceMeasure the distance measure used to calculate the distance between instances.
     * @param inst            the instance for which we need to find the closest
     * @return the instances from the supplied data set that are closest to the supplied instance
     */
    @Override
    public Set<Instance> kNearest(int k, Instance inst, DistanceMeasure distanceMeasure) {
        // k最邻近, 使用hashMap作为存储单元.
        Map<Instance, Double> closest = new HashMap<Instance, Double>();
        double max = distanceMeasure.getMaxValue();
        for (Instance instance : this) {
            double d = distanceMeasure.measure(inst, instance);
            if (distanceMeasure.compare(d, max) && !inst.equals(instance)) {
                closest.put(instance, d);
                if (closest.size() > k)
                    max = removeFarthest(closest, distanceMeasure);
            }

        }
        return closest.keySet();
    }

    /*
     * Removes the element from the vector that is farthest from the supplied element.
     * && return the max distance for the KNear Instance.
     */
    private double removeFarthest(Map<Instance, Double> vector, DistanceMeasure distanceMeasure) {
        Instance tmp = null;
        double max = distanceMeasure.getMinValue();
        for (Instance instance : vector.keySet()) {
            double d = vector.get(instance);
            if (distanceMeasure.compare(max, d)) {
                max = d;
                tmp = instance;
            }
        }
        vector.remove(tmp);
        return max;
    }

    /**
     * @param numFolds the number of folds to create 对折的次数.
     * @param random   选择元素的随机因子.
     * @return 对折划分后的采样子集.
     */
    @Override
    public Dataset[] folds(int numFolds, Random random) {
        Dataset[] out = new Dataset[numFolds];
        List<Integer> indices = new Vector<Integer>();
        for (int i = 0; i < this.size(); i++) {
            indices.add(i);
        }
        int size = (this.size() / numFolds) + 1;
        int[][] array = new int[numFolds][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < numFolds; j++) {
                if (indices.size() > 0) {
                    array[j][i] = indices.remove(random.nextInt(indices.size()));
                } else {
                    array[j][i] = -1;
                }
            }
        }
        for (int i = 0; i < numFolds; i++) {
            int[] indi;
            if (array[i][size - 1] == -1) {
                indi = new int[size - 1];
                System.arraycopy(array[i], 0, indi, 0, size - 1);
            } else {
                indi = new int[size];
                System.arraycopy(array[i], 0, indi, 0, size);
            }
            out[i] = new Fold(this, indi);
        }
        return out;
    }

    @Override
    public int noAttributes() {
        if (this.size() == 0)
            return 0;
        return maxAttributes;
    }

    @Override
    public int classIndex(Object clazz) {
        if (clazz != null) {
            return this.classes().headSet(clazz).size();
        } else {
            return -1;
        }
    }

    @Override
    public Object classValue(int index) {
        int i = 0;
        for (Object o : this.classes) {
            if (i == index) {
                return o;
            }
            i++;
        }
        return null;
    }

    @Override
    public Dataset copy() {
        DefaultDataset out = new DefaultDataset();
        for (Instance i : this) {
            out.add(i.copy());
        }
        return out;
    }

}
