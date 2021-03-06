package com.carrentingservice.vehiclelisting.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.carrentingservice.vehiclelisting.controller.dto.InventoryRequestDTO;
import com.carrentingservice.vehiclelisting.controller.dto.InventoryResponseTO;
import com.carrentingservice.vehiclelisting.controller.dto.ResponseTO;
import com.carrentingservice.vehiclelisting.delegate.VehicleInventoryDelegate;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
@RequestMapping("/vehicle-inventory")
public class VehicleInventoryController {

	@Autowired
	private VehicleInventoryDelegate vehicleInventoryDelegate;

	@GetMapping(value = "/get-vehicle")
	public ResponseEntity<ResponseTO<InventoryResponseTO>> getVehicleInventory(
			@RequestParam("startPage") Long startPage, @RequestParam("size") Long size) throws RecordNotFoundException {
		ResponseTO<InventoryResponseTO> responseTO = new ResponseTO<>();
		InventoryResponseTO inventoryResponseTO = vehicleInventoryDelegate.getVehicleInventory(startPage, size);
		responseTO.setData(inventoryResponseTO);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}

	@GetMapping(value = "/get-vehicle/{vehicleId}")
	public ResponseEntity<ResponseTO<InventoryResponseTO>> getVehicleInventoryById(
			@PathVariable("vehicleId") String vehicleId) throws RecordNotFoundException {
		ResponseTO<InventoryResponseTO> responseTO = new ResponseTO<>();
		InventoryResponseTO vehicleInventory = vehicleInventoryDelegate.getVehicleInventoryById(vehicleId);
		responseTO.setData(vehicleInventory);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}

	@PostMapping(value = "/add-inventory")
	public ResponseEntity<ResponseTO<InventoryRequestDTO>> addInventory(
			@RequestParam("smallSizeImage") MultipartFile smallSizeImage,
			@RequestParam("fullSizeImage") MultipartFile fullSizeImage,
			@RequestParam("vehicleInventory") String vehicleInventory) throws IOException {
		ResponseTO<InventoryRequestDTO> responseTO = new ResponseTO<>();
		InventoryRequestDTO inventoryDetails = new ObjectMapper().readValue(vehicleInventory,
				InventoryRequestDTO.class);
		InventoryRequestDTO addInventory = vehicleInventoryDelegate.addInventory(inventoryDetails, fullSizeImage,
				fullSizeImage);
		responseTO.setData(addInventory);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}
}
