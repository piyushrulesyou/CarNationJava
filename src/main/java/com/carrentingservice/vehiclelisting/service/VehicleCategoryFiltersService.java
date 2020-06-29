package com.carrentingservice.vehiclelisting.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carrentingservice.vehiclelisting.controller.dto.InventoryResponseTO;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;

@Service
public interface VehicleCategoryFiltersService {

	InventoryResponseTO filterByTransmissionType(boolean manualTransmission, boolean autoTransmission, Long startPage,
			Long size) throws RecordNotFoundException;

	InventoryResponseTO filterByFuelType(boolean petrol, boolean diesel, Long startPage, Long size)
			throws RecordNotFoundException;

	InventoryResponseTO filterBySegmentType(boolean suv, boolean sedan, boolean hatchback, Long startPage, Long size)
			throws RecordNotFoundException;

	InventoryResponseTO filterByBrandName(List<String> brands, Long startPage, Long size)
			throws RecordNotFoundException;

	InventoryResponseTO filterByCityName(String city, Long startPage, Long size) throws RecordNotFoundException;

	InventoryResponseTO filterByPriceRange(Long minPrice, Long maxPrice, Long startPage, Long size)
			throws RecordNotFoundException;

}
