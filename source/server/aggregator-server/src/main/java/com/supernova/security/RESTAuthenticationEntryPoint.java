package com.supernova.security;

import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RESTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        String queryString = request.getQueryString();
        LoggerFactory.getLogger(this.getClass()).info("QueryString {}", queryString);
        if (queryString.equals("Anup")) {
            //response.sendRedirect("/greeting");
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}