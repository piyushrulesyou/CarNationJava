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
public class VehicleInventoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String variant;

	private String model;

	private FuelTypeDTO fuelType;

	private TransmissionTypeDTO transmissionType;

	private List<ColorMasterDTO> colorMaster;

	private CarTypeDTO carType;

	private String seats;

	private ProducerTypeDTO producer;

	private long sortOrder;

	private String fullSizeImage;

	private String smallSizeImage;

	private boolean isPopular;

	private long extraKmCharge;

	private PriceMasterDTO priceMaster;

	private long insuranceCost;

	private List<CityMasterDTO> cityMaster;

	private long securityDeposit;

	private boolean showVariant;

	private TenurePriceMasterDTO tenureMaster;

	private String createdBy;

	private Date createdDate;

	private String modifiedBy;

	private Date modifiedDate;
}