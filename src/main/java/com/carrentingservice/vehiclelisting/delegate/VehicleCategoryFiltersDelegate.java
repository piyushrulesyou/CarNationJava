package com.carrentingservice.vehiclelisting.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carrentingservice.vehiclelisting.controller.dto.InventoryResponseTO;
import com.carrentingservice.vehiclelisting.controller.dto.request.BrandFilterRequestDTO;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;
import com.carrentingservice.vehiclelisting.service.VehicleCategoryFiltersService;

@Component
public class VehicleCategoryFiltersDelegate {

	@Autowired
	private VehicleCategoryFiltersService vehicleCategoryFiltersService;

	public InventoryResponseTO filterByTransmissionType(boolean manualTransmission, boolean autoTransmission,
			Long startPage, Long size) throws RecordNotFoundException {
		return vehicleCategoryFiltersService.filterByTransmissionType(manualTransmission, autoTransmission, startPage,
				size);
	}

	public InventoryResponseTO filterByFuelType(boolean petrol, boolean diesel, Long startPage, Long size)
			throws RecordNotFoundException {
		return vehicleCategoryFiltersService.filterByFuelType(petrol, diesel, startPage, size);
	}

	public InventoryResponseTO filterBySegmentType(boolean suv, boolean sedan, boolean hatchback, Long startPage,
			Long size) throws RecordNotFoundException {
		return vehicleCategoryFiltersService.filterBySegmentType(suv, sedan, hatchback, startPage, size);
	}

	public InventoryResponseTO filterByBrandName(BrandFilterRequestDTO brands, Long startPage, Long size)
			throws RecordNotFoundException {
		return vehicleCategoryFiltersService.filterByBrandName(brands, startPage, size);
	}

	public InventoryResponseTO filterByCityName(String city, Long startPage, Long size) throws RecordNotFoundException {
		return vehicleCategoryFiltersService.filterByCityName(city, startPage, size);
	}

	public InventoryResponseTO filterByPriceRange(Long minPrice, Long maxPrice, Long startPage, Long size)
			throws RecordNotFoundException {
		return vehicleCategoryFiltersService.filterByPriceRange(minPrice, maxPrice, startPage, size);
	}
}
