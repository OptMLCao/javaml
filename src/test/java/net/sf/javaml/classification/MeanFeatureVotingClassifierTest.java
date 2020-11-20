package net.sf.javaml.classification;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.sf.javaml.TutorialData;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;

/**
 * MeanFeatureVoting Classifier.
 *
 * @author Grand Cao
 * @date 2020.11.20
 */
@Slf4j
public class MeanFeatureVotingClassifierTest {

    @Test
    public void testMeanFeatureVotingClassifierRunning() {
        int randomSeed = 10;
        int numFolds = 5;
        try {
            Random random = new Random(randomSeed);
            Dataset irisDataSet = TutorialData.IRIS.load();
            Dataset[] foldIrisDataSet = irisDataSet.folds(numFolds, random);
            log.info("foldIrisDataSet size {}", foldIrisDataSet.length);
            MeanFeatureVotingClassifier meanFeatureVotingClassifier = new MeanFeatureVotingClassifier();
            List<Dataset> originDataSet = Arrays.asList(foldIrisDataSet);
            log.info("begin training with {} Classifier.", meanFeatureVotingClassifier.getClass().getSimpleName());
            for (Dataset dataset : originDataSet.subList(0, numFolds)) {
                meanFeatureVotingClassifier.buildClassifier(dataset);
            }
            log.info("finish training ...");
            log.info("begin predict ...");
            for (Instance instance : originDataSet.get(originDataSet.size() - 1)) {
                Map<Object, Double> distributionRadio = meanFeatureVotingClassifier.classDistribution(instance);
                log.info("distributionRadio {}", distributionRadio);
                Object classify = meanFeatureVotingClassifier.classify(instance);
                log.info("finally classify {}", classify);
            }
            log.info("end predict ...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
