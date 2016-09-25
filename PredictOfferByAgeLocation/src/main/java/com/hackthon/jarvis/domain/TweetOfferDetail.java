package com.hackthon.jarvis.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jerry on 12-06-2016.
 */
public class TweetOfferDetail {
    Map<String,String> offerMap;

    public TweetOfferDetail() {
        offerMap = new HashMap<>();
        offerMap.put("Food","Hi, now you can enjoy your favourite meals at an amazing price!!!\n Get 50% of at your favourite hotels with our new Barclays Food Card!!");
        offerMap.put("Mumbai_Food","Hi, now you can enjoy your favourite meals at an amazing price!!!\n Get 50% of at your favourite hotels with our new Barclays Food Card!!");
        offerMap.put("Health","Take care of yourself and your loved ones, make your health care affordable with our new Barclays insurance plans.");
        offerMap.put("Travel","Love Travelling? Redeem your traveller points and get free stay at best hotels!!");
        offerMap.put("Pune_Travel","Love Travelling? Redeem your traveller points and get free stay at best hotels!!");
        offerMap.put("Entertainment","Now share your happiness with your friends? Exclusive monthly offer for free movie tickets just for you.");
        offerMap.put("Hackathon_Entertainment","Now share your happiness with your friends? Exclusive monthly offer for free movie tickets just for you.");
        offerMap.put(null,"No offer available.");
    }
    public String getOffer(String transactionType){
        return offerMap.get(transactionType);
    }
}
