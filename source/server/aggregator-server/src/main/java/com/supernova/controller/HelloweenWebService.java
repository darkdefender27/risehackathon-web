package com.supernova.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController()
public class HelloweenWebService {

    @RequestMapping(value = "/rest/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HelloweenResponse> hello(@RequestParam(required = false) String message) {

        return new ResponseEntity<HelloweenResponse>(
                new HelloweenResponse("Happy Halloween, " + message + "!"), HttpStatus.OK);
    }

    public static class HelloweenResponse {
        private String message;

        public HelloweenResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}