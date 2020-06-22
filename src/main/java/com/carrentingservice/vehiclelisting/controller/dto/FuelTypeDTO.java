package com.carrentingservice.vehiclelisting.controller.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuelTypeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fuelCode;

	private String fuelName;

	private String fuelTypeImage;

	private String createdBy;

	private Date createdDate;

	private String modifiedBy;

	private Date modifiedDate;
}
