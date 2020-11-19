package net.sf.javaml;
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

import java.io.File;
import java.io.IOException;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;

/**
 * Tutorial data sets
 *
 * @author Thomas Abeel
 */
public enum TutorialData {

    IRIS("./src/test/resources/net.sf.javaml/data/devtools/iris.data", 4, ",", null),
    SPARSE("net.sf.javaml/data/sparse.tsv", 0, " ", ":");

    private String file;
    private int classIndex;
    private String sep;
    private String sep2;

    private TutorialData(String file, int classindex, String sep, String sep2) {
        this.file = file;
        this.classIndex = classindex;
        this.sep = sep;
        this.sep2 = sep2;
    }

    public Dataset load() throws IOException {
        if (sep2 == null)
            return FileHandler.loadDataset(new File(file), classIndex, sep);
        else
            return FileHandler.loadSparseDataset(new File(file), classIndex,
                    sep, sep2);
    }

}
