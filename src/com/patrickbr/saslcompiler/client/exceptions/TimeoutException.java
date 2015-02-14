package com.patrickbr.saslcompiler.client.exceptions;

public class TimeoutException extends ReduceException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "<br>Timeout.";
	}
}