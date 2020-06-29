package com.carrentingservice.vehiclelisting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carrentingservice.vehiclelisting.controller.dto.ResponseTO;
import com.carrentingservice.vehiclelisting.controller.dto.VehicleInventoryDTO;
import com.carrentingservice.vehiclelisting.delegate.VehicleCategoryFiltersDelegate;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;

@RestController
@RequestMapping("/filters")
public class VehicleCategoryFiltersController {

	@Autowired
	private VehicleCategoryFiltersDelegate vehicleCategoryFiltersDelegate;

	@GetMapping("/transmission")
	public ResponseEntity<ResponseTO<List<VehicleInventoryDTO>>> filterByTransmissionType(
			@RequestParam("manual") boolean manualTransmission, @RequestParam("auto") boolean autoTransmission)
			throws RecordNotFoundException {

		ResponseTO<List<VehicleInventoryDTO>> responseTO = new ResponseTO<>();
		List<VehicleInventoryDTO> vehicleList = vehicleCategoryFiltersDelegate
				.filterByTransmissionType(manualTransmission, autoTransmission);
		responseTO.setData(vehicleList);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}

	@GetMapping("/fuel")
	public ResponseEntity<ResponseTO<List<VehicleInventoryDTO>>> filterByFuelType(
			@RequestParam("petrol") boolean petrol, @RequestParam("diesel") boolean diesel)
			throws RecordNotFoundException {

		ResponseTO<List<VehicleInventoryDTO>> responseTO = new ResponseTO<>();
		List<VehicleInventoryDTO> vehicleList = vehicleCategoryFiltersDelegate.filterByFuelType(petrol, diesel);
		responseTO.setData(vehicleList);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}

	@GetMapping("/segment")
	public ResponseEntity<ResponseTO<List<VehicleInventoryDTO>>> filterBySegmentType(@RequestParam("suv") boolean suv,
			@RequestParam("hatchback") boolean hatchback, @RequestParam("sedan") boolean sedan)
			throws RecordNotFoundException {

		ResponseTO<List<VehicleInventoryDTO>> responseTO = new ResponseTO<>();
		List<VehicleInventoryDTO> vehicleList = vehicleCategoryFiltersDelegate.filterBySegmentType(suv, sedan,
				hatchback);
		responseTO.setData(vehicleList);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}

	@GetMapping("/brand")
	public ResponseEntity<ResponseTO<List<VehicleInventoryDTO>>> filterByBrandName(
			@RequestParam("brands") List<String> brands) throws RecordNotFoundException {

		ResponseTO<List<VehicleInventoryDTO>> responseTO = new ResponseTO<>();
		List<VehicleInventoryDTO> vehicleList = vehicleCategoryFiltersDelegate.filterByBrandName(brands);
		responseTO.setData(vehicleList);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}

	@GetMapping("/city")
	public ResponseEntity<ResponseTO<List<VehicleInventoryDTO>>> filterByCityName(@RequestParam("city") String city)
			throws RecordNotFoundException {

		ResponseTO<List<VehicleInventoryDTO>> responseTO = new ResponseTO<>();
		List<VehicleInventoryDTO> vehicleList = vehicleCategoryFiltersDelegate.filterByCityName(city);
		responseTO.setData(vehicleList);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}

	@GetMapping("/price")
	public ResponseEntity<ResponseTO<List<VehicleInventoryDTO>>> filterByPriceRange(
			@RequestParam("minPrice") Long minPrice, @RequestParam("maxPrice") Long maxPrice)
			throws RecordNotFoundException {

		ResponseTO<List<VehicleInventoryDTO>> responseTO = new ResponseTO<>();
		List<VehicleInventoryDTO> vehicleList = vehicleCategoryFiltersDelegate.filterByPriceRange(minPrice, maxPrice);
		responseTO.setData(vehicleList);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}
}
