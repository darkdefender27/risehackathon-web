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
    public String postAccountsTransactionDetails(@PathVariable String customerId, @PathVariable String accountId, @RequestBody String transactionData) throws UnirestException {

        return barclayApiDelegate.postTransactionDetails( transactionData);
    }

    @RequestMapping(value = "/rest/customer/{customerId}/account/{accountId}/payee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String postAccountsPayeeDetails(@PathVariable String customerId, @PathVariable String accountId) throws UnirestException {

        return barclayApiDelegate.getPayeeDetails();
    }

    @RequestMapping(value = "/rest/customer/{customerId}/account/{accountId}/payee", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String postAccountsPayee(@PathVariable String customerId, @PathVariable String accountId, @RequestBody String payeeData) throws UnirestException {

        return barclayApiDelegate.postPayeeDetails(payeeData);
    }

    @RequestMapping(value = "/rest/customer/{customerId}/account/{accountId}/p2ptransaction", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAccountsP2PTransactionDetails(@PathVariable String customerId, @PathVariable String accountId) throws UnirestException {

        return barclayApiDelegate.getPingitTransactionsDetails();
    }

    @RequestMapping(value = "/rest/customer/{customerId}/account/{accountId}/p2ptransaction", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String postP2PTransaction(@PathVariable String customerId, @PathVariable String accountId, @RequestBody String p2pTransactionData) throws UnirestException {
        return barclayApiDelegate.postP2PTRansaction(p2pTransactionData);
    }
}
