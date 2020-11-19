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
 * This similarity based distance measure actually measures the angle between two vectors.
 * The value returned lies in the interval [0,1].
 * 余弦相似度, 用向量空间中两个向量夹角的余弦值作为衡量两个个体之间差异的大小.
 * 余弦相似度更加注重两个向量在方向上的差异，而非距离上的长度.
 *
 * @author Thomas Abeel
 */
public class CosineSimilarity extends AbstractSimilarity {

    private static final long serialVersionUID = 330926456281777694L;

    @Override
    public double measure(Instance x, Instance y) {
        if (x.noAttributes() != y.noAttributes()) {
            throw new RuntimeException("Both instances should contain the same number of values.");
        }
        double sumTop = 0;
        double sumOne = 0;
        double sumTwo = 0;
        for (int i = 0; i < x.noAttributes(); i++) {
            sumTop += x.value(i) * y.value(i);
            sumOne += x.value(i) * x.value(i);
            sumTwo += y.value(i) * y.value(i);
        }
        double cosSim = sumTop / (Math.sqrt(sumOne) * Math.sqrt(sumTwo));
        if (cosSim < 0) {
            // This should not happen, but does because of rounding error cosSim.
            // 实际余弦相似度的取值范围在[-1, 1]之间，0度的余弦值为1，180度的余弦值为-1. 距离计算时负数可能无意义.
            return 0;
        }
        return cosSim;
    }

}
