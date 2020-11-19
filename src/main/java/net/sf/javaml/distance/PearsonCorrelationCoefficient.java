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
 * Calculates the Pearson Correlation Coeffient between two vectors.
 * 皮尔森相关系数（Person correlation coefficient）.
 * 根据样本的协方差和标准差，可得到样本相关系数（即样本皮尔森相关系数）.
 * 皮尔森相关系数(r)反映了两个变量的线性相关性的强弱程度.值域范围[-1, 1]. r的绝对值越大说明相关性越强.
 * 物理意义：
 * 当r>0时，表明两个变量正相关，即一个变量值越大则另一个变量值也会越大;
 * 当r<0时，表明两个变量负相关，即一个变量值越大则另一个变量值反而会越小；
 * 当r=0时，表明两个变量不是线性相关的（注意只是非线性相关），但可能存在其他方式的相关性（比如曲线方式）；
 * 当r=1||r=-1时，表明两个变量X和Y可以很好的由直线方程来描述，所有样本点都很好的落在一条直线上;
 * <p>
 * <p>
 * The returned value lies in the interval [-1,1]. A value of 1 shows that a
 * linear equation describes the relationship perfectly and positively, with all
 * data points lying on the same line and with Y increasing with X. A score of
 * ?1 shows that all data points lie on a single line but that Y increases as X
 * decreases. A value of 0 shows that a linear model is inappropriate / that
 * there is no linear relationship between the variables.
 * <p>
 * http://davidmlane.com/hyperstat/A56626.html
 * http://en.wikipedia.org/wiki/Pearson_product-moment_correlation_coefficient
 *
 * @author Thomas Abeel
 */
public class PearsonCorrelationCoefficient extends AbstractCorrelation {

    private static final long serialVersionUID = -5805322724874919246L;

    /**
     * Measures the Pearson Correlation Coefficient between the two supplied instances.
     *
     * @param a the first instance
     * @param b the second instance
     */
    public double measure(Instance a, Instance b) {
        if (a.noAttributes() != b.noAttributes()) {
            throw new RuntimeException("Both instances should have the same length");
        }
        double xy = 0, x = 0, x2 = 0, y = 0, y2 = 0;
        for (int i = 0; i < a.noAttributes(); i++) {
            xy += a.value(i) * b.value(i);
            x += a.value(i);
            y += b.value(i);
            x2 += a.value(i) * a.value(i);
            y2 += b.value(i) * b.value(i);
        }
        int n = a.noAttributes();
        return (xy - (x * y) / n) / Math.sqrt((x2 - (x * x) / n) * (y2 - (y * y) / n));
    }

}
