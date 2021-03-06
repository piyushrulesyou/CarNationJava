package com.carrentingservice.vehiclelisting.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.carrentingservice.vehiclelisting.controller.dto.InventoryRequestDTO;
import com.carrentingservice.vehiclelisting.controller.dto.InventoryResponseTO;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;

public interface VehicleInventoryService {

	public InventoryResponseTO getVehicleInventory(Long startPage, Long size) throws RecordNotFoundException;

	public InventoryResponseTO getVehicleInventoryById(String vehicleId) throws RecordNotFoundException;

	public InventoryRequestDTO addInventory(InventoryRequestDTO inventoryDetails, MultipartFile smallSizeImage, MultipartFile fullSizeImage) throws IOException;

	public InventoryResponseTO getVehicleInventoryByIdList(List<String> vehicleIds, Long startPage, Long size)
			throws RecordNotFoundException;
}
