package com.carrentingservice.vehiclelisting.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {

	private String errorCode;
	private String errorMessage;

}
