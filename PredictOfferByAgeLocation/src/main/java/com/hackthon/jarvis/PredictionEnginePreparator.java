package com.hackthon.jarvis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instances;

import java.io.File;
import java.util.ArrayList;

@SpringBootApplication
public class PredictionEnginePreparator {

	public ArrayList<Attribute> prepareForAttributes() throws Exception {
		FastVector userCity = new FastVector(5);
		userCity.addElement("Pune");
		userCity.addElement("Mumbai");
		userCity.addElement("Chennai");
		userCity.addElement("Kolkatta");
		userCity.addElement("Delhi");

		FastVector transactionType = new FastVector(4);
		transactionType.addElement("HDFC");
		transactionType.addElement("ICICI");
		transactionType.addElement("SBI");
		transactionType.addElement("HSBC");
		transactionType.addElement("BARCLAYS");

		FastVector userAge = new FastVector(5);
		userAge.addElement("LessTwenty");
		userAge.addElement("Twenties");
		userAge.addElement("Thirties");
		userAge.addElement("Forties");
		userAge.addElement("FortyPlus");

		ArrayList<Attribute> atts = new ArrayList<Attribute>();
		atts.add(new Attribute("userId", Attribute.NUMERIC));
		atts.add(new Attribute("userCity", userCity));
		atts.add(new Attribute("userAge", userAge));
		atts.add(new Attribute("transactionType", transactionType));

		return atts;
	}
}
