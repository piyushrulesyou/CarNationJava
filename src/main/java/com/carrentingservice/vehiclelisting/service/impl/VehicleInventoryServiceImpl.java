package com.carrentingservice.vehiclelisting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrentingservice.vehiclelisting.domain.VehicleInventoryEntity;
import com.carrentingservice.vehiclelisting.repo.VehicleInventoryRepo;
import com.carrentingservice.vehiclelisting.service.VehicleInventoryService;

@Service
public class VehicleInventoryServiceImpl implements VehicleInventoryService {

	@Autowired
	VehicleInventoryRepo vehicleInventoryRepo;

	/*
	 * @Autowired VehicleCityRepo vehicleCityRepo;
	 */

	@Override
	public List<VehicleInventoryEntity> getVehicleInventory() {
		List<VehicleInventoryEntity> vehicleInventoryEntity = vehicleInventoryRepo.findAll();
		return vehicleInventoryEntity;
	}

//	@Override
//	public List<VehicleCityManyToManyEntity> getVehicleCity() {
//		List<VehicleCityManyToManyEntity> vehicleCityEntity = vehicleCityRepo.findAll();
//		return vehicleCityEntity;
//	}

}
