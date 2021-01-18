package gov.naco.soch.lfu.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class ErrorResponse {

	public ErrorResponse(String message, List<ErrorDto> details, List<String> detailsSimplified) {
		super();
		this.message = message;
		this.details = details;
		this.detailsSimplified = detailsSimplified;
	}

	public ErrorResponse(String message, List<String> detailsSimplified) {
		super();
		this.message = message;
		this.detailsSimplified = detailsSimplified;
	}

	public ErrorResponse() {
	}

	// Error message
	private String message;

	// Complete Error details in API request
	private List<ErrorDto> details;

	// Only string representation of all errors in API request. This property can be
	// used as no need to iterate through all ErrorDto objects to display the error
	// messages in UI.
	private List<String> detailsSimplified;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ErrorDto> getDetails() {
		return details;
	}

	public void setDetails(List<ErrorDto> details) {
		this.details = details;
	}

	public List<String> getDetailsSimplified() {
		return detailsSimplified;
	}

	public void setDetailsSimplified(List<String> detailsSimplified) {
		this.detailsSimplified = detailsSimplified;
	}

	@Override
	public String toString() {
		return "ErrorResponse [message=" + message + ", details=" + details + ", detailsSimplified=" + detailsSimplified
				+ "]";
	}

}