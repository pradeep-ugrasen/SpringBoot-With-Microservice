package com.javabuster.exception;

public class BookIdMismatchException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookIdMismatchException() {
    }

    public BookIdMismatchException(String message) {
        super(message);
    }

    public BookIdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookIdMismatchException(Throwable cause) {
        super(cause);
    }

    public BookIdMismatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}