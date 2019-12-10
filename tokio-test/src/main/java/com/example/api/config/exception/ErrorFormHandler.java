package com.example.api.config.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.api.dto.ErrorFormDTO;

@RestControllerAdvice
public class ErrorFormHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorFormDTO> handler(MethodArgumentNotValidException exception) {
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		return createErrorList(fieldErrors);
	}

	private List<ErrorFormDTO> createErrorList(List<FieldError> fieldErrors) {
		List<ErrorFormDTO> errorsDTO = new ArrayList<>();

		for (FieldError fieldError : fieldErrors) {
			String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			ErrorFormDTO erro = new ErrorFormDTO(fieldError.getField(), message);
			errorsDTO.add(erro);
		}

		return errorsDTO;
	}

}
