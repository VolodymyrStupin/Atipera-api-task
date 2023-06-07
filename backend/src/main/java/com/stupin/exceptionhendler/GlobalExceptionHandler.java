package com.stupin.exceptionhendler;

import com.stupin.model.dto.ApiErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import static com.stupin.config.Constants.Message.NOT_ACCEPTABLE_MESSAGE;
import static com.stupin.config.Constants.Message.NOT_FOUND_MESSAGE;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ResponseEntity<String> handleMediaTypeNotAcceptable(final HttpMediaTypeNotAcceptableException e) {
		LOG.debug("In handleMediaTypeNotAcceptable - {}", e.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(NOT_ACCEPTABLE_MESSAGE);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<ApiErrorDto> handleClientNotFoundException(final HttpClientErrorException e) {
		LOG.debug("In handleClientNotFoundException - {}", e.getMessage());

		final var error = createApiError(e.getStatusCode().value(), NOT_FOUND_MESSAGE);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	private ApiErrorDto createApiError(final int statusCode, final String errorMessage) {
		return new ApiErrorDto.Builder()
			.withStatusCode(statusCode)
			.withErrorMessage(errorMessage)
			.build();
	}
}
