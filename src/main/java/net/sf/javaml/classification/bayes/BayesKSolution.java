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
package net.sf.javaml.classification.bayes;

import java.util.Vector;

/**
 * Data structure used for Bayesian networks Stores Bayesian networks and
 * topology ordened features
 *
 * @author Lieven Baeyens
 * @author Thomas Abeel
 */
class BayesKSolution {

    private BayesNet BN;
    private Vector<Integer> top;

    BayesKSolution(BayesNet BN, Vector<Integer> top) {
        this.top = top;
        this.BN = BN;
    }

    BayesNet getBN() {
        return BN;
    }

    void setBN(BayesNet BN) {
        this.BN = BN;
    }

    void setTop(Vector<Integer> top2) {
        this.top = (Vector<Integer>) top2.clone();
    }

    Vector<Integer> getTop() {
        return top;
    }

}
