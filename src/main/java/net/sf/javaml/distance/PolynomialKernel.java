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
 * 多项式核函数.
 */
public class PolynomialKernel implements DistanceMeasure {

    private static final long serialVersionUID = 113839833688979121L;

    private double exponent = 1;

    public PolynomialKernel(double exponent) {
        this.exponent = exponent;
    }

    @Override
    public double measure(Instance i, Instance j) {
        double result;
        result = dotProd(i, j);
        if (exponent != 1.0) {
            return Math.pow(result, exponent);
        }
        return result;
    }

    /**
     * Calculates a dot product between two instances
     * 求两个向量的内积（点乘）.
     * a = [a1, a2, ... , aM]
     * b = [b1, b2, ... , bM]
     * a•b = a1b1 + a2b2 + ... +  aMbM ;
     */
    protected final double dotProd(Instance inst1, Instance inst2) {

        double result = 0;
        for (int i = 0; i < inst1.noAttributes(); i++) {
            result += inst1.value(i) * inst2.value(i);
        }
        return result;
    }

    public boolean compare(double x, double y) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public double getMinValue() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public double getMaxValue() {
        throw new UnsupportedOperationException("Not implemented");
    }

}
