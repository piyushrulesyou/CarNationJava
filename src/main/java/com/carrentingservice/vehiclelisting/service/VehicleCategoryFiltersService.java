package com.carrentingservice.vehiclelisting.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carrentingservice.vehiclelisting.controller.dto.VehicleInventoryDTO;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;

@Service
public interface VehicleCategoryFiltersService {

	List<VehicleInventoryDTO> filterByTransmissionType(boolean manualTransmission, boolean autoTransmission)
			throws RecordNotFoundException;

	List<VehicleInventoryDTO> filterByFuelType(boolean petrol, boolean diesel) throws RecordNotFoundException;

	List<VehicleInventoryDTO> filterBySegmentType(boolean suv, boolean sedan, boolean hatchback)
			throws RecordNotFoundException;

	List<VehicleInventoryDTO> filterByBrandName(List<String> brands) throws RecordNotFoundException;

	List<VehicleInventoryDTO> filterByCityName(String city) throws RecordNotFoundException;

	List<VehicleInventoryDTO> filterByPriceRange(Long minPrice, Long maxPrice) throws RecordNotFoundException;

}
