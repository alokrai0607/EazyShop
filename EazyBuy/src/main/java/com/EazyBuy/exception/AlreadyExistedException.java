package com.EazyBuy.exception;

public class AlreadyExistedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyExistedException() {
		super();
	}

	public AlreadyExistedException(String message) {
		super(message);
	}

}
