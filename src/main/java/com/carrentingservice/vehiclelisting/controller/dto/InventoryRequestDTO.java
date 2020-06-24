package com.carrentingservice.vehiclelisting.controller.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequestDTO {

	private String id;
	private String variant;
	private String model;
	private String fuelType;
	private String transmissionType;
	private List<String> colorMaster;
	private String carType;
	private String seats;
	private String producer;
	private long sortOrder;
	private String fullSizeImage;
	private String smallSizeImage;
	private boolean isPopular;
	private long extraKmCharge;
	private String priceMaster;
	private long insuranceCost;
	private List<String> cityMaster;
	private long securityDeposit;
	private boolean showVariant;
	private List<Long> tenureMaster;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
}
