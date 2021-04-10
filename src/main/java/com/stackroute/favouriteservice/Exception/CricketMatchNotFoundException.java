package com.stackroute.favouriteservice.Exception;

public class CricketMatchNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	String message;

	public CricketMatchNotFoundException(String cricketMatch){
		super (cricketMatch);
		this.message = cricketMatch;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Cricket Match not Found [message=" + message + "]";
	}


}
