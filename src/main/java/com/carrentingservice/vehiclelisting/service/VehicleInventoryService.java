package com.carrentingservice.vehiclelisting.service;

import java.util.List;

import com.carrentingservice.vehiclelisting.controller.dto.InventoryRequestDTO;
import com.carrentingservice.vehiclelisting.controller.dto.VehicleInventoryDTO;

public interface VehicleInventoryService {

	public List<VehicleInventoryDTO> getVehicleInventory() throws Exception;

	public VehicleInventoryDTO getVehicleInventoryById(String vehicleId) throws Exception;

	public InventoryRequestDTO addInventory(InventoryRequestDTO inventoryDetails);
}
