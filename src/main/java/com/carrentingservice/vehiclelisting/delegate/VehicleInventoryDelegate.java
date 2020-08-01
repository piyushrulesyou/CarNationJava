package com.carrentingservice.vehiclelisting.delegate;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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

	public InventoryRequestDTO addInventory(InventoryRequestDTO inventoryDetails, MultipartFile smallSizeImage, MultipartFile fullSizeImage) throws IOException {
		return vehicleInventoryService.addInventory(inventoryDetails, smallSizeImage, fullSizeImage);
	}
}
