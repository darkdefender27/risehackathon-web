package com.supernova.controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.supernova.gateway.BarclayApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by asakhare on 9/24/16.
 */
@RestController
public class AccountController {

    @Autowired
    BarclayApiDelegate barclayApiDelegate;

    @RequestMapping(value = "/rest/customer/{customerId}/accounts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllAccountsDetails(@PathVariable String customerId) throws UnirestException {

        return barclayApiDelegate.getAccountDetails();
    }

    @RequestMapping(value = "/rest/customer/{customerId}/accounts/internal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getInternalAccountsDetails(@PathVariable String customerId) throws UnirestException {

        return barclayApiDelegate.getInternalAccountDetails();
    }

    @RequestMapping(value = "/rest/customer/{customerId}/account/{accountId}/transaction", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAccountsTransactionDetails(@PathVariable String customerId,@PathVariable String accountId) throws UnirestException {

        return barclayApiDelegate.getTransactionDetails();
    }

    @RequestMapping(value = "/rest/customer/{customerId}/account/{accountId}/transaction", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAccountsTransactionDetails(@PathVariable String customerId,@PathVariable String accountId,@RequestBody String transactionData) throws UnirestException {

        return barclayApiDelegate.postTransactionDetails( transactionData);
    }
}
