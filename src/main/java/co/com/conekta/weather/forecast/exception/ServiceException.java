package co.com.conekta.weather.forecast.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {
	
	private static final long serialVersionUID = -2425480730227459736L;
	private String error;
	private HttpStatus httpStatus;
	
	public ServiceException(String error, HttpStatus httpStatus) {
		this.error = error;
		this.httpStatus = httpStatus;
	}
}
