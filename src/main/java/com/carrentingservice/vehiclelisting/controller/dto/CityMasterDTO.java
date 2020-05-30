package com.carrentingservice.vehiclelisting.controller.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CityMasterDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cityCode;

	private String cityName;

	private List<VehicleInventoryDTO> vehicleInventory;

	private String createdBy;

	private Date createdDate;

	private String modifiedBy;

	private Date modifiedDate;
}
