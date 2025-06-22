package com.project.app.core.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiSubError {
	private String name;
	private Object value;
	private String message;
}
