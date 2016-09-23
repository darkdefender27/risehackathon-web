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
        Object response = delegate.postTransactionDetails("Sample String");
        Assert.assertTrue(response != null);
    }

}