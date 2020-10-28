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
import java.util.Map;

import com.sun.security.auth.UnixNumericUserPrincipal;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;

/**
 * Abstract classifier class that is the parent of all classifiers that require
 * the mean of each class as training.
 *
 * @author Thomas Abeel
 */
public class AbstractMeanClassifier extends AbstractClassifier {

    private static final long serialVersionUID = 8596181454461400908L;

    protected Map<Object, Instance> mean;

    public Instance getMean(Object clazz) {
        return mean.get(clazz);

    }

    @Override
    public void buildClassifier(Dataset data) {
        super.buildClassifier(data);
        mean = new HashMap<Object, Instance>();
        HashMap<Object, Integer> count = new HashMap<Object, Integer>();
        for (Instance instance : data) {
            if (!mean.containsKey(instance.classValue())) {
                mean.put(instance.classValue(), instance);
                count.put(instance.classValue(), 1);
            } else {
                mean.put(instance.classValue(), mean.get(instance.classValue()).add(instance));
                count.put(instance.classValue(), count.get(instance.classValue()) + 1);
            }
        }
        for (Object o : mean.keySet()) {
            mean.put(o, mean.get(o).divide(count.get(o)));
        }

    }
}
