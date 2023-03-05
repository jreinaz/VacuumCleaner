package edu.uoc.uocleaner.model;

public class DumpsterException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String ERROR_LOAD_NEGATIVE_VALUE = "[ERROR] Load cannot be negative!!";
	
	public DumpsterException() {
		super();
	}
	
	public DumpsterException(String msg) {
		super(msg);
	}
	
	public void addLoad​(int load)throws DumpsterException{
		
	}

}
