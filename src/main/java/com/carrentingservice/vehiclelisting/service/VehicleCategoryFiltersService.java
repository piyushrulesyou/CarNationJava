package com.carrentingservice.vehiclelisting.service;

import org.springframework.stereotype.Service;

import com.carrentingservice.vehiclelisting.controller.dto.InventoryResponseTO;
import com.carrentingservice.vehiclelisting.controller.dto.request.BrandFilterRequestDTO;
import com.carrentingservice.vehiclelisting.controller.dto.request.VehicleListingFiltersRequestDTO;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;

@Service
public interface VehicleCategoryFiltersService {

	InventoryResponseTO filterByTransmissionType(boolean manualTransmission, boolean autoTransmission)
			throws RecordNotFoundException;

	InventoryResponseTO filterByFuelType(boolean petrol, boolean diesel) throws RecordNotFoundException;

	InventoryResponseTO filterBySegmentType(boolean suv, boolean sedan, boolean hatchback)
			throws RecordNotFoundException;

	InventoryResponseTO filterByBrandName(BrandFilterRequestDTO brands) throws RecordNotFoundException;

	InventoryResponseTO filterByCityName(String city) throws RecordNotFoundException;

	InventoryResponseTO filterByPriceRange(Long minPrice, Long maxPrice) throws RecordNotFoundException;

	InventoryResponseTO vehicleListingFilters(VehicleListingFiltersRequestDTO vehicleFilters, Long startPage, Long size)
			throws RecordNotFoundException;

}
