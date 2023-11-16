package com.examserver.exam.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ApiResponse {


	private String message;
	private boolean status;
	private String errorCode;
}
