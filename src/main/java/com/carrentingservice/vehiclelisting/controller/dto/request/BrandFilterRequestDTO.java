package com.carrentingservice.vehiclelisting.controller.dto.request;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandFilterRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> brands;

}
