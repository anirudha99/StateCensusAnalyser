package com.censusanalyser;

@SuppressWarnings("serial")
public class MyException extends Exception {

	public enum ExceptionType {
		FILE_NOT_FOUND, INCORRECT_TYPE, DELIMITER_NOT_FOUND, INCORRECT_HEADER
	}

	public ExceptionType type;

	public MyException(ExceptionType type, String message) {
		super(message);
		this.type = type;
	}

	public MyException(ExceptionType type) {
		this.type = type;
	}

}
