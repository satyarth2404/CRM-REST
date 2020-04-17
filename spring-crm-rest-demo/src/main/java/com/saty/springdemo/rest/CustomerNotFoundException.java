package com.saty.springdemo.rest;

public class CustomerNotFoundException extends RuntimeException{
	
	public CustomerNotFoundException(String message) {
		super(message);
	}
}
