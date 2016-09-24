package com.supernova.gateway;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by asakhare on 9/24/16.
 */
public class BarclayApiDelegateTest {

    @Test
    public void getInternalAccountDetails() throws Exception {
        BarclayApiDelegate delegate = new BarclayApiDelegate();
        Object response = delegate.getInternalAccountDetails();
        Assert.assertTrue(response != null);
    }

    @Test
    public void getAccountDetails() throws Exception {
        BarclayApiDelegate delegate = new BarclayApiDelegate();
        Object response = delegate.getAccountDetails();
        Assert.assertTrue(response != null);
    }

    @Test
    public void getTransactionDetails() throws Exception {
        BarclayApiDelegate delegate = new BarclayApiDelegate();
        Object response = delegate.getTransactionDetails();
        Assert.assertTrue(response != null);
    }

    @Test
    public void postTransactionDetails() throws Exception {
        BarclayApiDelegate delegate = new BarclayApiDelegate();
        Object response = delegate.postTransactionDetails("{\n" +
                "         \"amount\": {\n" +
                "         \"direction\": \"OUT\",\n" +
                "         \"value\": \"100.00\"\n" +
                "         },\n" +
                "         \"description\": \"New Fender Acoustic Guitar\",\n" +
                "         \"paymentDescriptor\": {\n" +
                "         \"paymentDescriptorType\": \"MERCHANT\",\n" +
                "         \"id\": \"97012293105189\"\n" +
                "         },\n" +
                "         \"metadata\": [\n" +
                "         {\n" +
                "         \"key\": \"RECEIPT\",\n" +
                "         \"value\": \"https://upload.wikimedia.org/wikipedia/common\"\n" +
                "         }\n" +
                "         ],\n" +
                "         \"tags\": [\n" +
                "         \"RETAIL\"\n" +
                "         ],\n" +
                "         \"notes\": null,\n" +
                "         \"paymentMethod\": \"CARD\"\n" +
                "         }");
        Assert.assertTrue(response != null);
    }

}