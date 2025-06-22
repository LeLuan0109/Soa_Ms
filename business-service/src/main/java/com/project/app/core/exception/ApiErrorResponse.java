	package com.project.app.core.exception;

  import java.util.List;
  import lombok.Data;
  import lombok.NoArgsConstructor;
  import org.springframework.http.HttpStatus;

	@Data
	@NoArgsConstructor
	public class ApiErrorResponse {
		private HttpStatus status;
		private String message;
		private List<ApiSubError> errors;
		private String path;
		public ApiErrorResponse(HttpStatus status, String message, List<ApiSubError> errors) {
			this.status = status;
			this.message = message;
			this.errors = errors;
		}
	}
