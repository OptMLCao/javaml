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

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

/**
 * The interface for instances in a data set.
 * The Instance Interface actually is another Map data structure.
 * noteL: suggest definition super interface as follow:
 * public interface MLInstance<T, S> extends Map<T, S>, Iterable<S>, Serializable;
 *
 * @author Thomas Abeel, Grand Cao.
 * @see Dataset
 * @see DenseInstance
 * @see SparseInstance
 */
public interface Instance extends Map<Integer, Double>, Iterable<Double>, Serializable {
    /**
     * Returns the class value for this instance.
     * 返回实例的分类.
     *
     * @return class value of this instance, or null if the class is not set
     */
    public Object classValue();

    /**
     * 设置实例的分类.
     *
     * @param value
     */
    public void setClassValue(Object value);

    /**
     * Returns the number of attributes this instance has.
     * 返回特征维度.
     *
     * @return number of attributes
     */
    public int noAttributes();

    /**
     * 作用同 public int noAttributes() 方法，且该方法已过时，不建议使用.
     */
    @Override
    @Deprecated
    public int size();

    /**
     * 返回指定维度上的特征的数值, 在此可以看出，在框架中每个维度对应的特征数值为double.
     *
     * @param pos: 游标或索引.
     * @return double: 对应维度上的数值.
     */
    public double value(int pos);

    /**
     * 返回有序的key集合.
     *
     * @return
     */
    @Override
    public SortedSet<Integer> keySet();

    /**
     * Subtract a scalar from this instance and returns the results.
     * Note: This method does not modify this instance, but returns the result.
     * 特征向量的每一个维度都减去一个固定的value.
     *
     * @return result of the subtraction
     */
    public Instance minus(double value);

    /**
     * Subtract an instance from this instance and returns the results.
     * Note: This method does not modify this instance, but returns the result.
     * 两个特征向量减法，[注意]要求返回一个新的Instance对象，不改变原有对象.
     *
     * @return result of the subtraction
     */
    public Instance minus(Instance min);

    /**
     * Add a scalar value to this instance and returns the results.
     * This method does not modify this instance, but returns the result.
     * 向量与数值相加，返回一个新的Instance.
     *
     * @param value value to add
     * @return result of the addition
     */
    public Instance add(double value);

    /**
     * Add an instance to this instance and returns the results.
     * Note: This method does not modify this instance, but returns the result.
     * 两个向量相加，返回一个新的Instance.
     *
     * @return result of the addition
     */
    public Instance add(Instance max);

    /**
     * Divide each value of this instance by a scalar value and returns the results.
     * This method does not modify this instance, but returns the result.
     * 向量与数值的除法，返回一个新的向量.
     *
     * @return result of the division
     */
    public Instance divide(double value);

    /**
     * Divide each value in this instance with the corresponding value of the
     * other instance and returns the results.
     * This method does not modify this instance, but returns the result.
     * 向量的除法，返回一个新的向量.
     *
     * @return result of the division
     */
    public Instance divide(Instance currentRange);


    /**
     * Multiply each value of this instance with a scalar value and return the result.
     * 向量与数值的乘法，返回一个新的向量.
     *
     * @param value scalar to multiply with
     * @return result of multiplication
     */
    public Instance multiply(double value);

    /**
     * Multiply each value in this instance with the corresponding value in provide instance.
     * 向量与向量的乘法，返回一个新的向量.
     *
     * @param value instance to multiply with
     * @return result of multiplication.
     */
    public Instance multiply(Instance value);

    /**
     * Removes an attribute from the instance.
     * 删除特征向量上某维度的特征值.
     *
     * @param i the index of the attribute to remove
     */
    public void removeAttribute(int i);

    /**
     * Take square root of all attributes.
     *
     * @return square root of attribute values
     */
    public Instance sqrt();

    /**
     * Returns a unique identifier for this instance.
     *
     * @return unique identifier
     */
    public int getID();

    /**
     * Create a deep copy of this instance
     *
     * @return a deep copy of this instance
     */
    public Instance copy();

    /**
     * Removes a set of attributes from the instance.
     *
     * @param indices set of indices that should be removed
     */
    public void removeAttributes(Set<Integer> indices);

}
