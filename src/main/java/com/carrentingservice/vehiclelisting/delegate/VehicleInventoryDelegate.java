package com.carrentingservice.vehiclelisting.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carrentingservice.vehiclelisting.controller.dto.InventoryRequestDTO;
import com.carrentingservice.vehiclelisting.controller.dto.InventoryResponseTO;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;
import com.carrentingservice.vehiclelisting.service.VehicleInventoryService;

@Component
public class VehicleInventoryDelegate {

	@Autowired
	private VehicleInventoryService vehicleInventoryService;

	public InventoryResponseTO getVehicleInventory(Long startPage, Long size) throws RecordNotFoundException {
		return vehicleInventoryService.getVehicleInventory(startPage, size);
	}

	public InventoryResponseTO getVehicleInventoryById(String vehicleId) throws RecordNotFoundException {
		return vehicleInventoryService.getVehicleInventoryById(vehicleId);
	}

	public InventoryRequestDTO addInventory(InventoryRequestDTO inventoryDetails) {
		return vehicleInventoryService.addInventory(inventoryDetails);
	}
}
