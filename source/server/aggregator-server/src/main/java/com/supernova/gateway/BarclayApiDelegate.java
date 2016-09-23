package com.supernova.gateway;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by asakhare on 9/23/16.
 */
@Component
public class BarclayApiDelegate {

    private static final Logger LOG = LoggerFactory.getLogger(BarclayApiDelegate.class);

    public String getInternalAccountDetails() throws UnirestException {

        HttpResponse<String> response = Unirest.get("https://api108567live.gateway.akana.com:443/accounts/1234/internal-accounts")
                .header("accept", "application/json")
                .header("cache-control", "no-cache")
                .asString();
        LOG.info("Response:" + response.getBody());
        return response.getBody();
    }

    public String getAccountDetails() throws UnirestException {

        HttpResponse<String> response = Unirest.get("https://api108567live.gateway.akana.com:443/accounts/1234")
                .header("accept", "application/json")
                .header("cache-control", "no-cache")
                .asString();
        LOG.info("Response:" + response.getBody());
        return response.getBody();
    }

    public String getTransactionDetails() throws UnirestException {

        HttpResponse<String> response = Unirest.get("https://api108567live.gateway.akana.com:443/accounts/1234/transactions")
                .header("accept", "application/json")
                .header("cache-control", "no-cache")
                .asString();
        LOG.info("Response:" + response.getBody());
        return response.getBody();
    }

    /**
     * Save a new transaction. Input for saving a transaction is a JSON object with list of fields as shown in below example.
     * Transaction amount is a JSON object with direction and value properties.
     * If money is going out of account then the direction is "OUT" and if the money is coming in to account then
     * the direction is "IN". Value always stays as positive number. Payment descriptor should be unique identification
     * of an existing [MERCHANT, PAYEE, P2P or INTERNAL_ACCOUNT]. If you want to create a transaction on a new Payment
     * descriptor(like merchant), please use the relative end point to create this new Payment descriptor before making
     * the transaction. PaymentMethod - should be one of [CARD, ONLINE_TRANSFER, STANDING_ORDER, DIRECT_DEBIT, P2P,
     * FASTER_PAYMENT]. Meta data is a plain key value pair, you can save any data in here, like url to receipts.
     * Tags can be used to group transactions. Meta data and tags can be added after creating a transaction.
     * Tags can only be in uppercase with no spaces.
     *
     * @param transactionDetails
     * @return
     * @throws UnirestException
     */
    public String postTransactionDetails(String transactionDetails) throws UnirestException {

        HttpResponse<String> response = Unirest.post("https://api108567live.gateway.akana.com:443/accounts/1234/transactions")
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("cache-control", "no-cache")
                // .body(" {\n\t\"amount\": {\n\t\"direction\": \"OUT\",\n\t\"value\": \"100.00\"\n\t},\n\t\"description\": \"New Fender Acoustic Guitar\",\n\t\"paymentDescriptor\": {\n\t\"paymentDescriptorType\": \"MERCHANT\",\n\t\"id\": \"97012293105189\"\n\t},\n\t\"metadata\": [\n\t{\n\t\"key\": \"RECEIPT\",\n\t\"value\": \"https://upload.wikimedia.org/wikipedia/common\"\n\t}\n\t],\n\t\"tags\": [\n\t\"RETAIL\"\n\t],\n\t\"notes\": null,\n\t\"paymentMethod\": \"CARD\"\n}")
                .body(transactionDetails)
                .asString();
        /**
         *
         Sample Input String
         {
         "amount": {
         "direction": "OUT",
         "value": "100.00"
         },
         "description": "New Fender Acoustic Guitar",
         "paymentDescriptor": {
         "paymentDescriptorType": "MERCHANT",
         "id": "97012293105189"
         },
         "metadata": [
         {
         "key": "RECEIPT",
         "value": "https://upload.wikimedia.org/wikipedia/common"
         }
         ],
         "tags": [
         "RETAIL"
         ],
         "notes": null,
         "paymentMethod": "CARD"
         }
         */
        LOG.info("Response:" + response.getBody());
        return response.getBody();
    }

}
