package com.carrentingservice.vehiclelisting.service;

import java.util.List;

import com.carrentingservice.vehiclelisting.controller.dto.InventoryRequestDTO;
import com.carrentingservice.vehiclelisting.controller.dto.VehicleInventoryDTO;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;

public interface VehicleInventoryService {

	public List<VehicleInventoryDTO> getVehicleInventory() throws RecordNotFoundException;

	public VehicleInventoryDTO getVehicleInventoryById(String vehicleId) throws RecordNotFoundException;

	public InventoryRequestDTO addInventory(InventoryRequestDTO inventoryDetails);

}
