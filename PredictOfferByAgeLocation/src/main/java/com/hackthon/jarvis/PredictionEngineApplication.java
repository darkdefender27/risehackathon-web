package com.hackthon.jarvis;

import com.hackthon.jarvis.domain.StanfordNLPParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import weka.classifiers.Classifier;
import weka.core.Attribute;

import java.io.File;
import java.util.ArrayList;

@EnableScheduling
@SpringBootApplication
public class PredictionEngineApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PredictionEngineApplication.class, args);
    }

}
