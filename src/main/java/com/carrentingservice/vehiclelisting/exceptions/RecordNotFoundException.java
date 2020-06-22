package com.carrentingservice.vehiclelisting.exceptions;

public class RecordNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public RecordNotFoundException(String errorCode) {
		super(errorCode);
	}
}
