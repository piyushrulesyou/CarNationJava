package com.carrentingservice.vehiclelisting.delegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carrentingservice.vehiclelisting.controller.dto.InventoryRequestDTO;
import com.carrentingservice.vehiclelisting.controller.dto.VehicleInventoryDTO;
import com.carrentingservice.vehiclelisting.service.VehicleInventoryService;

@Component
public class VehicleInventoryDelegate {

	@Autowired
	private VehicleInventoryService vehicleInventoryService;

	public List<VehicleInventoryDTO> getVehicleInventory() throws Exception {
		return vehicleInventoryService.getVehicleInventory();
	}

	public VehicleInventoryDTO getVehicleInventoryById(String vehicleId) throws Exception {
		return vehicleInventoryService.getVehicleInventoryById(vehicleId);
	}

	public InventoryRequestDTO addInventory(InventoryRequestDTO inventoryDetails) {
		return vehicleInventoryService.addInventory(inventoryDetails);
	}
}
