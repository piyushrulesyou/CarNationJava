package com.carrentingservice.vehiclelisting.controller.dto.request;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VehicleListingFiltersRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean city;
	private String cityName;
	private boolean price;
	private Long minPrice;
	private Long maxPrice;
	private boolean segment;
	private boolean suvSegment;
	private boolean hatchBackSegment;
	private boolean sedanSegment;
	private boolean fuel;
	private boolean petrolFuel;
	private boolean dieselFuel;
	private boolean transmission;
	private boolean manualTransmission;
	private boolean automaticTransmission;
	private boolean brand;
	private List<String> brands;

}
