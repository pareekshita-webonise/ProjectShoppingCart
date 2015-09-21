package com.shopperszone.custom.exceptions;

public class DataLimitException extends Exception{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -3041546899858952184L;
	private String message = null;
	 
	    public DataLimitException() {
	        super();
	    }
	 
	    public DataLimitException(String message) {
	        super(message);
	        this.message = message;
	    }
	 
	    public DataLimitException(Throwable cause) {
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
