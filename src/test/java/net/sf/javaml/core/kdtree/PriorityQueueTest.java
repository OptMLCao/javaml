package net.sf.javaml.core.kdtree;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Grand Cao
 * @date 2020.11.19
 */
@Slf4j
public class PriorityQueueTest {

    @Test
    public void testInitPriorityQueue() {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.add(11, 1);
        priorityQueue.add(10, 11);
        priorityQueue.add(100, 5);
        log.info("priority queue {}", priorityQueue);
    }

}
