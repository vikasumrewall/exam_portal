package com.examserver.exam.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException  extends RuntimeException{

	/**
	 * 
	 */
	
	private String resourceName;
	private  String fieldName;
	private String fieldValue;
	private String errorCode;
	
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue,String errorCode) {
		super(resourceName+","+fieldName+","+fieldValue+","+errorCode);
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.errorCode=errorCode;
	}
	
	

}
