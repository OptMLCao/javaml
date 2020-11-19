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
 * The norm distance:
 * This class implements the Norm distance.
 * This is a generalization of the Euclidean distance,
 * in this respect that the power we use becomes a parameter instead of being fixed to two.
 * if power==1;
 * The x-Norm distance between two points P=(p1,p2,...,pn) and Q=(q1,q2,...,qn)
 * in the Euclidean n-space is defined as: ((p1-q1)^x + (p2-q2)^x + ... +
 * (pn-qn)^x)^(1/x).
 * Special instances are x=1, the Manhattan- or taxicab norm. Or x=infinity
 * gives the Chebychev distance.
 * if power==2:
 * The default is the Euclidean distance where x=2.
 *
 * @author Thomas Abeel
 * @linkplain http://en.wikipedia.org/wiki/Norm_%28mathematics%29
 * 向量范数.
 */
public class NormDistance extends AbstractDistance {

    private static final long serialVersionUID = 3431231902618783080L;

    public NormDistance() {
        this(2);
    }

    private double power;

    public NormDistance(double power) {
        this.power = power;
    }

    @Override
    public double measure(Instance x, Instance y) {
        // 注意power的取值.
        assert (x.noAttributes() == y.noAttributes());
        double sum = 0;
        for (int i = 0; i < x.noAttributes(); i++) {
            sum += Math.pow(Math.abs(y.value(i) - x.value(i)), power);
        }
        return Math.pow(sum, 1 / power);
    }

}
