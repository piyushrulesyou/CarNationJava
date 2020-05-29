package com.carrentingservice.vehiclelisting.controller.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.carrentingservice.vehiclelisting.domain.CarTypeEntity;
import com.carrentingservice.vehiclelisting.domain.CityMasterEntity;
import com.carrentingservice.vehiclelisting.domain.ColorMasterEntity;
import com.carrentingservice.vehiclelisting.domain.FuelTypeEntity;
import com.carrentingservice.vehiclelisting.domain.ModelTypeEntity;
import com.carrentingservice.vehiclelisting.domain.PriceMasterEntity;
import com.carrentingservice.vehiclelisting.domain.ProducerTypeEntity;
import com.carrentingservice.vehiclelisting.domain.TenurePriceMasterEntity;
import com.carrentingservice.vehiclelisting.domain.TransmissionTypeEntity;
import com.carrentingservice.vehiclelisting.domain.VariantTypeEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleInventoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private VariantTypeEntity variant;

	private ModelTypeEntity model;

	private FuelTypeEntity fuelType;

	private TransmissionTypeEntity transmissionType;

	private List<ColorMasterEntity> colorMaster;

	private CarTypeEntity carType;

	private String seats;

	private ProducerTypeEntity producer;

	private long sortOrder;

	private String fullSizeImage;

	private String smallSizeImage;

	private boolean isPopular;

	private long extraKmCharge;

	private PriceMasterEntity priceMaster;

	private long insuranceCost;

	private List<CityMasterEntity> cityMaster;

	private long securityDeposit;

	private boolean showVariant;

	private List<TenurePriceMasterEntity> tenureMaster;

	private String createdBy;

	private Date createdDate;

	private String modifiedBy;

	private Date modifiedDate;
}