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
import Jama.Matrix;

/**
 * 马氏距离.
 * 马氏距离和量纲（尺寸）无关，排除变量之间的相关性.
 */
public class MahalanobisDistance extends AbstractDistance {

    private static final long serialVersionUID = -5844297515283628612L;

    public double measure(Instance i, Instance j) {
        double[][] del = new double[3][1];
        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 1; n++) {
                del[m][n] = i.value(m) - j.value(m);
            }
        }
        Matrix M1 = new Matrix(del);
        // 求矩阵的转置.
        Matrix M2 = M1.transpose();
        // 构建covariance matrix
        double[][] covar = new double[3][3];
        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 3; n++) {
                covar[m][n] += (i.value(m) - j.value(m)) * (i.value(n) - j.value(n));
            }
        }
        Matrix cov = new Matrix(covar);
        Matrix covInv = cov.inverse();
        Matrix temp1 = M2.times(covInv);
        Matrix temp2 = temp1.times(M1);
        double dist = temp2.trace();
        if (dist > 0.) {
            return Math.sqrt(dist);
        }
        return dist;
    }

}
