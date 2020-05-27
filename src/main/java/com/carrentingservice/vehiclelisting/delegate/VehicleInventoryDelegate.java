package com.carrentingservice.vehiclelisting.delegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carrentingservice.vehiclelisting.domain.VehicleInventoryEntity;
import com.carrentingservice.vehiclelisting.service.VehicleInventoryService;

@Component
public class VehicleInventoryDelegate {

	@Autowired
	private VehicleInventoryService vehicleInventoryService;

	public List<VehicleInventoryEntity> getVehicleInventory() {
		return vehicleInventoryService.getVehicleInventory();
	}

//	public List<VehicleCityManyToManyEntity> getVehicleCity() {
//		return vehicleInventoryService.getVehicleCity();
//	}

}
