package com.hackthon.jarvis;

import com.hackthon.jarvis.domain.Offer;
import com.hackthon.jarvis.domain.OfferDetails;
import com.hackthon.jarvis.domain.UserTransaction;
import org.springframework.stereotype.Component;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.util.ArrayList;

@Component
public class PredictionEnginePredictor {

    private OfferDetails offerDetails = new OfferDetails();

    public Offer predict(ArrayList<Attribute> attributes, UserTransaction userTransaction, Classifier classifier) throws Exception {
        Instances test = new Instances("UserTransaction", attributes, 0);

        if (test.classIndex() == -1)
            test.setClassIndex(test.numAttributes() - 1);

        Instance iExample = new DenseInstance(4);
        iExample.setValue(attributes.get(0), userTransaction.getUserId());

        if (userTransaction.getCity() != null)
            iExample.setValue(attributes.get(1), userTransaction.getCity());

        if (userTransaction.getAge() != null)
            iExample.setValue(attributes.get(2), userTransaction.getAge());

        iExample.setDataset(test);
        test.add(iExample);

        System.out.println(iExample);
        double value = classifier.classifyInstance(iExample);
        iExample.setClassValue(value);
        String predictedResult = iExample.classAttribute().value((int) value);
        System.out.println(predictedResult);
        Offer offer = new Offer();
        offer.setCategory(predictedResult);
        offer.setOffer(offerDetails.getOffer(predictedResult));
        return offer;
    }
}
