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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;

public abstract class AbstractClassifier implements Classifier {

    private static final long serialVersionUID = -4461661354949399603L;

    // 父类的类别，注意parentCLasses选用的数据结构;
    protected Set<Object> parentClasses = null;

    /**
     * 获取训练数据样本分类或标签.
     *
     * @param data the data set to be used to create the classifier 训练数据.
     */
    @Override
    public void buildClassifier(Dataset data) {
        this.parentClasses = new HashSet<Object>();
        parentClasses.addAll(data.classes());
    }

    /**
     * 分类.
     *
     * @param instance the instance to be classified
     * @return
     */
    @Override
    public Object classify(Instance instance) {
        // 获取每个分类的得分.
        Map<Object, Double> distribution = classDistribution(instance);
        // 使用贪心的原则输出分类或标签号.
        double max = 0;
        Object out = null;
        for (Object key : distribution.keySet()) {
            if (distribution.get(key) > max) {
                max = distribution.get(key);
                out = key;
            }
        }
        return out;
    }

    /**
     * 分类得分.
     *
     * @param instance the instance to be classified
     * @return
     */
    @Override
    public Map<Object, Double> classDistribution(Instance instance) {
        HashMap<Object, Double> out = new HashMap<Object, Double>();
        for (Object o : parentClasses) {
            out.put(o, 0.0);
        }
        out.put(classify(instance), 1.0);
        return out;
    }

}
