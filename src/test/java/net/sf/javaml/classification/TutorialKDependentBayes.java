/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sf.javaml.classification;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.sf.javaml.classification.bayes.KDependentBayesClassifier;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.filter.discretize.EqualWidthBinning;
import net.sf.javaml.TutorialData;

/**
 * Tutorial for K Dependent Bayes classifier
 *
 * @author Lieven Baeyens
 * @author Thomas Abeel
 */
@Slf4j
public class TutorialKDependentBayes {

    @Test
    public void teatKDependentBayes() throws Exception {

        /* Load a data set */
        Dataset data = TutorialData.IRIS.load();
        EqualWidthBinning eb = new EqualWidthBinning(3);
        System.out.println("Start discretisation");
        eb.build(data);
        Dataset ddata = data.copy();
        eb.filter(ddata);
        double treshold = 0.0;
        KDependentBayesClassifier nbc = new KDependentBayesClassifier(false,
                treshold, new int[]{0, 1, 2, 4, 5, 8});
        nbc.buildClassifier(ddata);
        // Algorithm needs to know which Bayesian network (which k value)
        // you need to classify the sample with
        nbc.setcurrentWorkingK(5);
        log.info("Start classification:");
        /*
         * Load a data set, this can be a different one, but we will use the
         * same one.
         */
        Dataset dataForClassification = TutorialData.IRIS.load();
        /* Counters for correct and wrong predictions. */
        int correct = 0, wrong = 0;
        /* Classify all instances and check with the correct class values */
        double cnt = 0;
        double overallF = dataForClassification.size();
        for (Instance inst : dataForClassification) {
            log.info(((++cnt) / overallF * 100) + "%");
            eb.filter(inst);
            Object predictedClassValue = nbc.classify(inst);
            Object realClassValue = inst.classValue();
            // System.out.println("realClassValue "+ realClassValue);
            if (predictedClassValue.equals(realClassValue))
                correct++;
            else {
                wrong++;

            }
        }

    }

}
