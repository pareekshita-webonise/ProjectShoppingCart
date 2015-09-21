package com.shopperszone.custom.exceptions;

public class ShoppersZoneException extends Exception{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -2501020546896428200L;
	private String message = null;
	 
	    public ShoppersZoneException() {
	        super();
	    }
	 
	    public ShoppersZoneException(String message) {
	        super(message);
	        this.message = message;
	    }
	 
	    public ShoppersZoneException(Throwable cause) {
	        super(cause);
	    }
	 
	    @Override
	    public String toString() {
	        return message;
	    }
	 
	    @Override
	    public String getMessage() {
	        return message;
	    }
}
