package net.sf.javaml.core;

import java.io.Serializable;
import java.util.Map;

/**
 * reDefinition general super Instance Interface.
 *
 * @author Grand Cao
 * @date 2020.11.16
 */
public interface SampleInstance<K, V> extends Map<K, V>, Iterable<V>, Serializable {



}
