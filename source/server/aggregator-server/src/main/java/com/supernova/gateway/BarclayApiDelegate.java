package com.supernova.gateway;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by asakhare on 9/23/16.
 */
@Component
public class BarclayApiDelegate {

    private static final Logger LOG = LoggerFactory.getLogger(BarclayApiDelegate.class);

    private final RestTemplate restTemplate;

    public BarclayApiDelegate() throws KeyStoreException {

        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        TrustStrategy allTrust = new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        };

      /*  int timeout = 3;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();

        BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager();
        CloseableHttpClient httpClient =
                HttpClients
                        .custom()
                        .setDefaultRequestConfig(config)
                        .setSSLHostnameVerifier(new NoopHostnameVerifier()).setConnectionManager(connManager)
                        .build();*/

      /*  SSLContext sslcontext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, allTrust).build();

        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        return httpClient;*/
//        Unirest.setHttpClient(httpClient);

        this.restTemplate = new RestTemplate(getClientHttpRequestFactory());
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 3000;
        RequestConfig config =
                RequestConfig.custom()
                        .setConnectTimeout(timeout)
                        .setConnectionRequestTimeout(timeout)
                        .setSocketTimeout(timeout)
                        .build();
        CloseableHttpClient client =
                HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        return new HttpComponentsClientHttpRequestFactory(client);
    }

    public String getInternalAccountDetails() throws UnirestException {

         /*   HttpResponse<String> response = Unirest.get("https://api108567live.gateway.akana.com:443/accounts/1234/internal-accounts")
                .header("accept", "application/json")
                .header("cache-control", "no-cache")
                .asString();
*/
        String responseString = restTemplate.getForObject(
                "https://api108567live.gateway.akana.com:443/accounts/1234/internal-accounts",
                String.class);
        LOG.info("Response:" + responseString);
        return responseString;
    }

    public String getAccountDetails() throws UnirestException {

       /* HttpResponse<String> response = Unirest.get("https://api108567live.gateway.akana.com:443/accounts/1234")
                .header("accept", "application/json")
                .header("cache-control", "no-cache")
                .asString();
        LOG.info("Response:" + response.getBody());
*/
        String responseString = restTemplate.getForObject(
                "https://api108567live.gateway.akana.com:443/accounts/1234",
                String.class);
        return responseString;
    }

    public String getTransactionDetails() throws UnirestException {

        String responseString = restTemplate.getForObject(
                "https://api108567live.gateway.akana.com:443/accounts/1234/transactions",
                String.class);
        LOG.info("Response:" + responseString);
        return responseString;

       /* HttpResponse<String> response = Unirest.get("https://api108567live.gateway.akana.com:443/accounts/1234/transactions")
                .header("accept", "appliXXcation/json")
                .header("cache-control", "no-cache")
                .asString();
        LOG.info("Response:" + response.getBody());
        return response.getBody(); */
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
        return response.getBody();


       /* HttpEntity<JSONObject> request = new HttpEntity<JSONObject>(new JSONObject(transactionDetails));
        //request.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        ResponseEntity<String> responseStringObject = restTemplate.
                exchange("https://api108567live.gateway.akana.com:443/accounts/1234/transactions",
                        HttpMethod.POST,
                        request,
                        String.class);
        LOG.info("Response:" + responseStringObject.getBody());
        return responseStringObject.getBody();*/

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
        /*LOG.info("Response:" + response.getBody());
        return response.getBody();*/
    }

}
