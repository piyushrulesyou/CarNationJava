package com.carrentingservice.vehiclelisting.delegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carrentingservice.vehiclelisting.controller.dto.VehicleInventoryDTO;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;
import com.carrentingservice.vehiclelisting.service.VehicleCategoryFiltersService;

@Component
public class VehicleCategoryFiltersDelegate {

	@Autowired
	private VehicleCategoryFiltersService vehicleCategoryFiltersService;

	public List<VehicleInventoryDTO> filterByTransmissionType(boolean manualTransmission, boolean autoTransmission)
			throws RecordNotFoundException {
		return vehicleCategoryFiltersService.filterByTransmissionType(manualTransmission, autoTransmission);
	}

	public List<VehicleInventoryDTO> filterByFuelType(boolean petrol, boolean diesel) throws RecordNotFoundException {
		return vehicleCategoryFiltersService.filterByFuelType(petrol, diesel);
	}

	public List<VehicleInventoryDTO> filterBySegmentType(boolean suv, boolean sedan, boolean hatchback)
			throws RecordNotFoundException {
		return vehicleCategoryFiltersService.filterBySegmentType(suv, sedan, hatchback);
	}

	public List<VehicleInventoryDTO> filterByBrandName(List<String> brands) throws RecordNotFoundException {
		return vehicleCategoryFiltersService.filterByBrandName(brands);
	}

	public List<VehicleInventoryDTO> filterByCityName(String city) throws RecordNotFoundException {
		return vehicleCategoryFiltersService.filterByCityName(city);
	}

	public List<VehicleInventoryDTO> filterByPriceRange(Long minPrice, Long maxPrice) throws RecordNotFoundException {
		return vehicleCategoryFiltersService.filterByPriceRange(minPrice, maxPrice);
	}
}
