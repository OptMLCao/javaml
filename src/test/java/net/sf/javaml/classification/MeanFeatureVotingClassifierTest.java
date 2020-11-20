package net.sf.javaml.classification;

import java.io.IOException;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.sf.javaml.TutorialData;
import net.sf.javaml.core.Dataset;

/**
 * @author Grand Cao
 * @date 2020.11.20
 */
@Slf4j
public class MeanFeatureVotingClassifierTest {

    @Test
    public void testMeanFeatureVotingClassifierRunning() {
        try {
            Dataset irisDataSet = TutorialData.IRIS.load();
            log.info("");
            MeanFeatureVotingClassifier meanFeatureVotingClassifier = new MeanFeatureVotingClassifier();
            meanFeatureVotingClassifier.buildClassifier(irisDataSet);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
