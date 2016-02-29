package com.davon.metapro.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.davon.metapro.api.dto.ValidationErrorDTO;

@ControllerAdvice
public class RestErrorHandler {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RestErrorHandler.class);

	// @ExceptionHandler(Exception.class)
	// @ResponseStatus(HttpStatus.NOT_FOUND)
	// public void handleTodoNotFoundException(Exception ex) {
	// LOGGER.debug("Exception occured.", ex);
	// }

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ValidationErrorDTO processValidationError(
			MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();

		return processFieldErrors(fieldErrors);
	}

	private ValidationErrorDTO processFieldErrors(
			List<FieldError> fieldErrors) {
		ValidationErrorDTO dto = new ValidationErrorDTO();

		for (FieldError fieldError : fieldErrors) {
			String localizedErrorMessage = fieldError.getDefaultMessage();
			dto.addFieldError(fieldError.getField(), localizedErrorMessage);
		}

		return dto;
	}
}