package com.hackthon.jarvis.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jerry on 12-06-2016.
 */
public class OfferDetails {

    Map<String,String> offerMap;

    public OfferDetails() {
        offerMap = new HashMap<>();
        offerMap.put("HDFC","Hi, Enjoy 5X Reward points on HDFC Credit Card & 5% Cashback on Debit Card on all transactions.!!");
        offerMap.put("ICICI","Maximum Cashback of 20 percent using ICICI Credit cards");
        offerMap.put("SBI","16% off while shopping with SBI credit card!!");
        offerMap.put("HSBC","Now share your happiness with friends,Exclusive 20% discount offer while buying movie tickets");
        offerMap.put("BARCLAYS","Now take the loan at very lower percent of 6% with Barclays");
        offerMap.put("CITI","Maximum Cashback of 32 percent using CITI Credit cards");
        offerMap.put(null,"No offer available.");
    }
    public String getOffer(String transactionType){
        return offerMap.get(transactionType);
    }
}
