package com.carrentingservice.vehiclelisting.service;

import java.util.List;

import com.carrentingservice.vehiclelisting.controller.dto.InventoryRequestDTO;
import com.carrentingservice.vehiclelisting.controller.dto.VehicleInventoryDTO;
import com.carrentingservice.vehiclelisting.domain.VehicleInventoryEntity;

public interface VehicleInventoryService {

	public List<VehicleInventoryDTO> getVehicleInventory() throws Exception;

	public InventoryRequestDTO addInventory(InventoryRequestDTO inventoryDetails);
}
