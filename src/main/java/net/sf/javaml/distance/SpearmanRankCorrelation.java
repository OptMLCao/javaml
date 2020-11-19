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
package net.sf.javaml.distance;

import net.sf.javaml.core.Instance;

/**
 * Calculates the Spearman rank correlation of two instances. The value on
 * position 0 of the instance should be the rank of attribute 0. And so on and so forth.
 * 斯皮尔曼相关：斯皮尔曼等级相关是根据等级资料研究两个变量间相关关系的方法；
 * 根据两列的成对等级的各对等级数之差的来进行计算的，所以又称为"等级差数法".
 *
 * @author Thomas Abeel, Grand Cao.
 * @version 0.1.7
 * @linkplain http://en.wikipedia.org/wiki/Spearman's_rank_correlation_coefficient
 */
public class SpearmanRankCorrelation extends AbstractCorrelation {

    private static final long serialVersionUID = -6347213714272482397L;

    @Override
    public double measure(Instance a, Instance b) {

        // 两个特征向量长度必须一致.
        if (a.noAttributes() != b.noAttributes()) {
            throw new IllegalArgumentException("Instances should be compatible.");
        }
        // k特征的维度，也是等级的长度.
        long k = a.noAttributes();
        long denom = k * (k * k - 1);
        double sum = 0.0;
        for (int i = 0; i < a.noAttributes(); i++) {
            double diff = (a.value(i) - b.value(i));
            sum += (diff * diff);
        }
        return 1.0 - (6.0 * (sum / ((double) denom)));

    }

}
