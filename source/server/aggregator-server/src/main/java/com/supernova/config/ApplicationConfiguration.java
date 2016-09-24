package com.supernova.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by asakhare on 9/24/16.
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public Filter loggingFilter(){
        AbstractRequestLoggingFilter f = new AbstractRequestLoggingFilter() {

            @Override
            protected void beforeRequest(HttpServletRequest request, String message) {
                System.out.println("beforeRequest: " +message);
            }

            @Override
            protected void afterRequest(HttpServletRequest request, String message) {
                System.out.println("afterRequest: " +message);
            }
        };
        f.setIncludeClientInfo(true);
        f.setIncludePayload(true);
        f.setIncludeQueryString(true);

        f.setBeforeMessagePrefix("BEFORE REQUEST  [");
        f.setAfterMessagePrefix("AFTER REQUEST    [");
        f.setAfterMessageSuffix("]\n");
        return f;
    }
}
