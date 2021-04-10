package com.stackroute.favouriteservice.Exception;

public class CricketMatchAlreadyExists extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String message;

	public CricketMatchAlreadyExists (String cricketMatch){
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
		return "Cricket Match already eixits [message=" + message + "]";
	}
	
	
}
