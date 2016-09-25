package com.hackthon.jarvis;

import com.hackthon.jarvis.domain.Offer;
import com.hackthon.jarvis.domain.OfferDetails;
import com.hackthon.jarvis.domain.StanfordNLPParser;
import com.hackthon.jarvis.domain.UserTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import weka.classifiers.Classifier;
import weka.core.Attribute;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Jerry on 12-06-2016.
 */
@RestController
public class PredictionController {

    @Autowired
    PredictionEnginePredictor predictionEnginePredictor;

    private ArrayList<Attribute> attributes;
    private Classifier classifier;

    public PredictionController() throws Exception {
        PredictionEngineTrainer predictionEngineTrainer = new PredictionEngineTrainer();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("data.arff").getFile());

        this.classifier = predictionEngineTrainer.train(file);

        PredictionEnginePreparator predictionEnginePreparator = new PredictionEnginePreparator();
        this.attributes = predictionEnginePreparator.prepareForAttributes();
    }


    //Normal prediction
    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(path = "/transaction/offer/predict", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Offer predictTransactionType(@RequestBody UserTransaction userTransaction) throws Exception {
        Offer offer = null;

        try {
            offer = predictionEnginePredictor.predict(attributes, userTransaction, classifier);
        } catch(Exception e) {
            offer = new Offer(new OfferDetails().getOffer(null));
        }
//offer[1] = new Offer("CITI");
        return offer;
    }

    //NLP for twitter real time

    @RequestMapping(path = "/activity/intent/predict", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String predictActivity(@RequestBody String userStatus) throws Exception {
        return StanfordNLPParser.posTagging(userStatus).get("NNP");
    }

}
