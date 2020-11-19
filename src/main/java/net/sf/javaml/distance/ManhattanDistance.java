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
 * The Manhattan distance is the sum of the (absolute) differences of their
 * coordinates. The taxicab metric is also known as recti-linear distance,
 * Minkowski's L1 distance, city block distance, or Manhattan distance.
 * 曼哈顿距离.
 *
 * @author Thomas Abeel
 * @linkplain http://en.wikipedia.org/wiki/Taxicab_geometry
 * @linkplain http://www.nist.gov/dads/HTML/manhattanDistance.html
 * @linkplain http://mathworld.wolfram.com/TaxicabMetric.html
 */
public class ManhattanDistance extends AbstractDistance {

    private static final long serialVersionUID = 4954621120642494177L;

    /**
     * Calculates the Manhattan distance as the sum of the absolute differences of their coordinates.
     *
     * @return the Manhattan distance between the two instances.
     */
    public double measure(Instance x, Instance y) {
        if (x.noAttributes() != y.noAttributes()) {
            throw new RuntimeException("Both instances should contain the same number of values.");
        }
        double sum = 0.0;
        for (int i = 0; i < x.noAttributes(); i++) {
            sum += Math.abs(x.value(i) - y.value(i));
        }
        return sum;
    }

}
