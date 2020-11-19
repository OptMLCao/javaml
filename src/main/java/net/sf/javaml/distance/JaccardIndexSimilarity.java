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

import java.util.HashSet;
import java.util.Set;

import net.sf.javaml.core.Instance;

/**
 * Jaccard index. The distance between two sets is computed in the following way:
 * 杰卡德相似系数：两个集合A和B交集元素德个数在A、B并集中所占用的比例，称为这两个集合德杰卡德系数.
 * 杰卡德距离(Jaccard Distance)是y用来衡量两个集合差异性德一种指标，它是杰卡德相似系数的补集.
 *
 * <pre>
 *               n1 + n2 - 2*n12
 * D(S1, S2) = ------------------
 *               n1 + n2 - n12
 *
 *
 * </pre>
 *
 * <pre>
 * D(S1,S2) = |S1 ^ S2|
 *            ---------
 *            |S1 u S2|
 * </pre>
 * <p>
 * <p>
 * Where n1 and n2 are the numbers of elements in sets S1 and S2, respectively,
 * and n12 is the number that is in both sets (section).
 *
 * @author Thomas Abeel
 * @version %SVN.VERSION%
 * @linkplain http://en.wikipedia.org/wiki/Jaccard_index
 */
public class JaccardIndexSimilarity extends AbstractSimilarity {

    private static final long serialVersionUID = 6715828721744669500L;

    public double measure(Instance a, Instance b) {
        HashSet<Integer> set1 = new HashSet<Integer>();
        HashSet<Integer> set2 = new HashSet<Integer>();

        for (int i = 0; i < a.noAttributes(); i++) {
            set1.add((int) a.value(i));
        }
        for (int i = 0; i < b.noAttributes(); i++) {
            set2.add((int) b.value(i));
        }
        // 并集
        Set<Integer> union = new HashSet<Integer>();
        union.addAll(set1);
        union.addAll(set2);
        // 保证最终结果的除数不为0;
        assert union.size() > 0;
        // 差集
        Set<Integer> intersection = new HashSet<Integer>();
        intersection.addAll(set1);
        intersection.retainAll(set2);
        // return
        return ((double) intersection.size()) / ((double) union.size());
    }

}
