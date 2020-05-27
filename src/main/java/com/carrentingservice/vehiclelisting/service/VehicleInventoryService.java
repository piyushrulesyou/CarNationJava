package com.carrentingservice.vehiclelisting.service;

import java.util.List;

import com.carrentingservice.vehiclelisting.domain.VehicleInventoryEntity;

public interface VehicleInventoryService {

	public List<VehicleInventoryEntity> getVehicleInventory();

//	public List<VehicleCityManyToManyEntity> getVehicleCity();

}
