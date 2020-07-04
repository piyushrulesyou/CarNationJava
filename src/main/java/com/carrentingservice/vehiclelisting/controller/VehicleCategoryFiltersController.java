package com.carrentingservice.vehiclelisting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carrentingservice.vehiclelisting.controller.dto.InventoryResponseTO;
import com.carrentingservice.vehiclelisting.controller.dto.ResponseTO;
import com.carrentingservice.vehiclelisting.controller.dto.request.BrandFilterRequestDTO;
import com.carrentingservice.vehiclelisting.controller.dto.request.VehicleListingFiltersRequestDTO;
import com.carrentingservice.vehiclelisting.delegate.VehicleCategoryFiltersDelegate;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;

@RestController
@RequestMapping("/filters")
public class VehicleCategoryFiltersController {

	@Autowired
	private VehicleCategoryFiltersDelegate vehicleCategoryFiltersDelegate;

	@GetMapping("/transmission")
	public ResponseEntity<ResponseTO<InventoryResponseTO>> filterByTransmissionType(
			@RequestParam("manual") boolean manualTransmission, @RequestParam("auto") boolean autoTransmission)
			throws RecordNotFoundException {

		ResponseTO<InventoryResponseTO> responseTO = new ResponseTO<>();
		InventoryResponseTO vehicleList = vehicleCategoryFiltersDelegate.filterByTransmissionType(manualTransmission,
				autoTransmission);
		responseTO.setData(vehicleList);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}

	@GetMapping("/fuel")
	public ResponseEntity<ResponseTO<InventoryResponseTO>> filterByFuelType(@RequestParam("petrol") boolean petrol,
			@RequestParam("diesel") boolean diesel) throws RecordNotFoundException {

		ResponseTO<InventoryResponseTO> responseTO = new ResponseTO<>();
		InventoryResponseTO vehicleList = vehicleCategoryFiltersDelegate.filterByFuelType(petrol, diesel);
		responseTO.setData(vehicleList);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}

	@GetMapping("/segment")
	public ResponseEntity<ResponseTO<InventoryResponseTO>> filterBySegmentType(@RequestParam("suv") boolean suv,
			@RequestParam("sedan") boolean sedan, @RequestParam("hatchback") boolean hatchback)
			throws RecordNotFoundException {

		ResponseTO<InventoryResponseTO> responseTO = new ResponseTO<>();
		InventoryResponseTO vehicleList = vehicleCategoryFiltersDelegate.filterBySegmentType(suv, sedan, hatchback);
		responseTO.setData(vehicleList);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}

	@PostMapping("/brand")
	public ResponseEntity<ResponseTO<InventoryResponseTO>> filterByBrandName(@RequestBody BrandFilterRequestDTO brands)
			throws RecordNotFoundException {

		ResponseTO<InventoryResponseTO> responseTO = new ResponseTO<>();
		InventoryResponseTO vehicleList = vehicleCategoryFiltersDelegate.filterByBrandName(brands);
		responseTO.setData(vehicleList);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}

	@GetMapping("/city")
	public ResponseEntity<ResponseTO<InventoryResponseTO>> filterByCityName(@RequestParam("city") String city)
			throws RecordNotFoundException {

		ResponseTO<InventoryResponseTO> responseTO = new ResponseTO<>();
		InventoryResponseTO vehicleList = vehicleCategoryFiltersDelegate.filterByCityName(city);
		responseTO.setData(vehicleList);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}

	@GetMapping("/price")
	public ResponseEntity<ResponseTO<InventoryResponseTO>> filterByPriceRange(@RequestParam("minPrice") Long minPrice,
			@RequestParam("maxPrice") Long maxPrice) throws RecordNotFoundException {

		ResponseTO<InventoryResponseTO> responseTO = new ResponseTO<>();
		InventoryResponseTO vehicleList = vehicleCategoryFiltersDelegate.filterByPriceRange(minPrice, maxPrice);
		responseTO.setData(vehicleList);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}

	@PostMapping("/all-filters")
	public ResponseEntity<ResponseTO<InventoryResponseTO>> vehicleListingFilters(
			@RequestBody VehicleListingFiltersRequestDTO vehicleFilters, @RequestParam("startPage") Long startPage,
			@RequestParam("size") Long size) throws RecordNotFoundException {
		ResponseTO<InventoryResponseTO> responseTO = new ResponseTO<>();
		InventoryResponseTO vehicleList = vehicleCategoryFiltersDelegate.vehicleListingFilters(vehicleFilters,
				startPage, size);
		responseTO.setData(vehicleList);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}
}
