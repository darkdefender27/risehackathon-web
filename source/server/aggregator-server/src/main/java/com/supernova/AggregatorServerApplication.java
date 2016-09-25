package com.supernova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class AggregatorServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AggregatorServerApplication.class, args);
    }

   /* @Bean
    public HttpClient getHttpClient() throws KeyStoreException {
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        TrustStrategy allTrust = new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        };

        CloseableHttpClient httpClient =
                HttpClients.custom()
                        .setSSLHostnameVerifier(new NoopHostnameVerifier())
                        .build();

      *//*  SSLContext sslcontext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, allTrust).build();

        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        return httpClient;*//*
        return httpClient;
    }*/

   /* @Bean
    public RestTemplate restTemplate() throws KeyStoreException {
        return new RestTemplate(clientHttpRequestFactory());
    }*/

    /*private ClientHttpRequestFactory clientHttpRequestFactory() throws KeyStoreException {
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setReadTimeout(2000);
//        factory.setConnectTimeout(2000);
//        return factory;

        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        TrustStrategy allTrust = new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        };

        CloseableHttpClient httpClient =
                HttpClients.custom()
                        .setSSLHostnameVerifier(new NoopHostnameVerifier())
                        .build();

      *//*  SSLContext sslcontext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, allTrust).build();

        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        return httpClient;*//*
        return (ClientHttpRequestFactory) httpClient;
    }
*/
}
