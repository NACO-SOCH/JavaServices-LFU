package gov.naco.soch.lfu.exception;

import org.springframework.http.HttpStatus;

import gov.naco.soch.lfu.dto.ErrorResponse;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private HttpStatus httpStatus;
	private ErrorResponse errorResponse;
	
	public ServiceException() {
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String exception) {
		super(exception);
	}
	
	public ServiceException(String exceptionMessage, ErrorResponse errorResponse, HttpStatus httpStatus) {
		super(exceptionMessage);
		this.errorResponse = errorResponse;
		this.httpStatus = httpStatus;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
	
	

}
