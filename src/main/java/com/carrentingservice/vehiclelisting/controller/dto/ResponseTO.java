package com.carrentingservice.vehiclelisting.controller.dto;

import java.io.Serializable;
import java.util.List;

import com.carrentingservice.vehiclelisting.error.Error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTO<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean error;
	private List<Error> errors;
	private T data;

	public ResponseTO(T result) {

	}

}
