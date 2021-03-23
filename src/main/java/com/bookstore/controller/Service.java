package com.bookstore.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Service {
	
	Logger logger = LoggerFactory.getLogger(Service.class);
	
	/**
	 * Common method to return error messages
	 * @param error
	 * @param status
	 * @return
	 */
	public ResponseEntity<Object> sendErrorMessage(String error, HttpStatus status) {
		logger.error(error);
		Map<String, String> errorMap = new HashMap<String, String>(){{
            put("error", error);
        }};
		return new ResponseEntity<>(errorMap, status);
	}
}
