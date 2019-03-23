package com.example.rentalHome.Domains;

import lombok.*;

@Data
public class JSONResponse {
	//JSONStatus
	public static String FAIL="FAIL";
	public static String ERROR = "ERROR";
	public static String SUCCESS="SUCCESS";
	private String result;
	private String message;
	private Object data;
}
