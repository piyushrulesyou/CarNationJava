package com.carrentingservice.vehiclelisting.service;

import com.carrentingservice.vehiclelisting.controller.dto.InventoryRequestDTO;
import com.carrentingservice.vehiclelisting.controller.dto.InventoryResponseTO;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;

public interface VehicleInventoryService {

	public InventoryResponseTO getVehicleInventory(Long startPage, Long size) throws RecordNotFoundException;

	public InventoryResponseTO getVehicleInventoryById(String vehicleId) throws RecordNotFoundException;

	public InventoryRequestDTO addInventory(InventoryRequestDTO inventoryDetails);

}
