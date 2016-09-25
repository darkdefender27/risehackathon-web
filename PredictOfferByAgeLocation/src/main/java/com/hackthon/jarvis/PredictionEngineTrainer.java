package com.hackthon.jarvis;

import org.springframework.stereotype.Component;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

import java.io.File;
import java.util.Random;

@Component
public class PredictionEngineTrainer {

    public Classifier train(File file) throws Exception {
        ArffLoader loader = new ArffLoader();
        loader.setFile(file);
        Instances structure = loader.getStructure();

        ConverterUtils.DataSource source = new ConverterUtils.DataSource(file.getAbsolutePath());
        Instances data = source.getDataSet();
        System.out.println(data);
        if (data.classIndex() == -1)
            data.setClassIndex(data.numAttributes() - 1);
        filter(data);
        Classifier classify = classify(loader, data);
        evaluate(classify, data);
        return classify;
    }

    private static Classifier classify(ArffLoader loader, Instances structure) throws Exception {
        NaiveBayesUpdateable nb = new NaiveBayesUpdateable();
        nb.buildClassifier(structure);
        Instance current;
        while ((current = loader.getNextInstance(structure)) != null)
            nb.updateClassifier(current);
        return nb;
    }

    private static void filter(Instances data) throws Exception {
        String[] options = new String[2];
        options[0] = "-R";
        options[1] = "1";
        Remove remove = new Remove();
        remove.setOptions(options);
        remove.setInputFormat(data);
        data = Filter.useFilter(data, remove);
    }

    private static void evaluate(Classifier nb, Instances newData) throws Exception {
        Evaluation eval = new Evaluation(newData);
        eval.crossValidateModel(nb, newData, 10, new Random(1));
        System.out.println(eval.toSummaryString("\nResult : \n", false));
    }
}
