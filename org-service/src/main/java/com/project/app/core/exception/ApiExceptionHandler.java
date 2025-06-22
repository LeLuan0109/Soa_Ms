package com.project.app.core.exception;

import com.project.app.core.exception.exceptionSub.DataNotFoundException;
import com.project.app.core.exception.exceptionSub.InvalidParamException;
import com.project.app.core.exception.exceptionSub.PermissionDenyException;
import com.project.app.core.exception.exceptionSub.RuntimeExceptionSls;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {
	private static final String CAUSE_LABEL = "Cause";

	@ExceptionHandler({MethodArgumentNotValidException.class})
	protected ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
		List<ApiSubError> subErrors = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			Object rejectedValue = ((FieldError) error).getRejectedValue();
			String message = error.getDefaultMessage();
			subErrors.add(new ApiSubError(fieldName, rejectedValue, message));
		});
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
				HttpStatus.BAD_REQUEST,
				ex.getMessage(),
				subErrors);
		        apiErrorResponse.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
	}

	@ExceptionHandler({IllegalArgumentException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
		ApiSubError subError = new ApiSubError(null, null, ex.getMessage());
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
				HttpStatus.BAD_REQUEST,
				ex.getMessage(),
				Collections.singletonList(subError));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
	}

	@ExceptionHandler({RuntimeExceptionSls.class}) // Thêm xử lý cho RuntimeException
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ApiErrorResponse> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
		if (ex == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error", new ArrayList<>()));
		}

		List<ApiSubError> subErrors = new ArrayList<>();
		Throwable currentCause = ex;
		int level = 1;

		while (currentCause != null) {
			subErrors.add(new ApiSubError("Cause Level " + level, currentCause.getClass().getName(), currentCause.getMessage()));
			currentCause = currentCause.getCause();
			level++;
		}

		// Lấy toàn bộ stack trace của lỗi
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
				HttpStatus.INTERNAL_SERVER_ERROR,
				ex.getMessage(),
				subErrors);
		apiErrorResponse.setPath(request.getRequestURI());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiErrorResponse);
	}


	@ExceptionHandler({Exception.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
		if (ex == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error", new ArrayList<>()));
		}

		List<ApiSubError> subErrors = new ArrayList<>();
		Throwable currentCause = ex;
		int level = 1;

		// Duyệt qua tất cả các cấp nguyên nhân gốc
		while (currentCause != null) {
			subErrors.add(new ApiSubError("Cause Level " + level, currentCause.getClass().getName(), currentCause.getMessage()));
			currentCause = currentCause.getCause();
			level++;
		}

		ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
				HttpStatus.INTERNAL_SERVER_ERROR,
				ex.getMessage() != null ? ex.getMessage() : "No message available",
				subErrors);
		apiErrorResponse.setPath(request.getRequestURI());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiErrorResponse);
	}

	@ExceptionHandler({DataNotFoundException.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ApiErrorResponse> handleDataNotFoundException(Exception ex, HttpServletRequest request) {
		List<ApiSubError> subErrors = new ArrayList<>();
		Throwable cause = ex.getCause();
		if (cause != null) {
			subErrors.add(new ApiSubError(CAUSE_LABEL, cause.getCause(), cause.getMessage()));
		}
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
				HttpStatus.INTERNAL_SERVER_ERROR,
				ex.getMessage(),
				subErrors
		);
		apiErrorResponse.setPath(request.getRequestURI());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiErrorResponse);
	}

	@ExceptionHandler({DataIntegrityViolationException.class})
	@ResponseStatus(HttpStatus.CONFLICT)
	public ResponseEntity<ApiErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
		List<ApiSubError> subErrors = new ArrayList<>();
		Throwable cause = ex.getCause();
		if (cause != null) {
			subErrors.add(new ApiSubError(CAUSE_LABEL, cause.getClass().getSimpleName(), cause.getMessage()));
		}
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
				HttpStatus.CONFLICT,
				ex.getMessage(),
				subErrors
		);
		        apiErrorResponse.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(apiErrorResponse);
	}

	@ExceptionHandler({PermissionDenyException.class})
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseEntity<ApiErrorResponse> handlePermissionDenyException(PermissionDenyException ex, HttpServletRequest request) {
		List<ApiSubError> subErrors = new ArrayList<>();
		Throwable cause = ex.getCause();
		if (cause != null) {
			subErrors.add(new ApiSubError(CAUSE_LABEL, cause.getClass().getSimpleName(), cause.getMessage()));
		}
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
				HttpStatus.FORBIDDEN,
				ex.getMessage(),
				subErrors
		);
		        apiErrorResponse.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiErrorResponse);
	}

}
