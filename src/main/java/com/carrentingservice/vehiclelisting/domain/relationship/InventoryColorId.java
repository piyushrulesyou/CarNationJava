package com.carrentingservice.vehiclelisting.domain.relationship;

import java.io.Serializable;

import lombok.Data;

@Data
public class InventoryColorId implements Serializable {

	private static final long serialVersionUID = 1L;
	private String vehicleInventoryId;
	private String colorMasterColorCode;
}