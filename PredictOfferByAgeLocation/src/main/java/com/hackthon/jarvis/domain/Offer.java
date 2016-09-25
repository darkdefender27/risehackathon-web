package com.hackthon.jarvis.domain;

/**
 * Created by Jerry on 12-06-2016.
 */

    public  class Offer {
        private String offer;

    public Offer() {
    }

    public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        private String category;

        public Offer(String offer) {
            this.offer = offer;
        }

        public String getOffer() {
            return offer;
        }

        public void setOffer(String offer) {
            this.offer = offer;
        }

}
