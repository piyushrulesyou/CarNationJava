package com.carrentingservice.vehiclelisting.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carrentingservice.vehiclelisting.controller.dto.InventoryResponseTO;
import com.carrentingservice.vehiclelisting.controller.dto.request.BrandFilterRequestDTO;
import com.carrentingservice.vehiclelisting.controller.dto.request.VehicleListingFiltersRequestDTO;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;
import com.carrentingservice.vehiclelisting.service.VehicleCategoryFiltersService;

@Component
public class VehicleCategoryFiltersDelegate {

	@Autowired
	private VehicleCategoryFiltersService vehicleCategoryFiltersService;

	public InventoryResponseTO filterByTransmissionType(boolean manualTransmission, boolean autoTransmission)
			throws RecordNotFoundException {
		return vehicleCategoryFiltersService.filterByTransmissionType(manualTransmission, autoTransmission);
	}

	public InventoryResponseTO filterByFuelType(boolean petrol, boolean diesel) throws RecordNotFoundException {
		return vehicleCategoryFiltersService.filterByFuelType(petrol, diesel);
	}

	public InventoryResponseTO filterBySegmentType(boolean suv, boolean sedan, boolean hatchback)
			throws RecordNotFoundException {
		return vehicleCategoryFiltersService.filterBySegmentType(suv, sedan, hatchback);
	}

	public InventoryResponseTO filterByBrandName(BrandFilterRequestDTO brands) throws RecordNotFoundException {
		return vehicleCategoryFiltersService.filterByBrandName(brands);
	}

	public InventoryResponseTO filterByCityName(String city) throws RecordNotFoundException {
		return vehicleCategoryFiltersService.filterByCityName(city);
	}

	public InventoryResponseTO filterByPriceRange(Long minPrice, Long maxPrice) throws RecordNotFoundException {
		return vehicleCategoryFiltersService.filterByPriceRange(minPrice, maxPrice);
	}

	public InventoryResponseTO vehicleListingFilters(VehicleListingFiltersRequestDTO vehicleFilters, Long startPage,
			Long size) throws RecordNotFoundException {
		return vehicleCategoryFiltersService.vehicleListingFilters(vehicleFilters, startPage, size);
	}
}