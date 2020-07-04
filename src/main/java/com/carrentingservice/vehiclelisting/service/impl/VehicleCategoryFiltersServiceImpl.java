package com.carrentingservice.vehiclelisting.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrentingservice.vehiclelisting.constants.CommonConstants;
import com.carrentingservice.vehiclelisting.constants.ErrorConstants;
import com.carrentingservice.vehiclelisting.controller.dto.InventoryResponseTO;
import com.carrentingservice.vehiclelisting.controller.dto.VehicleInventoryDTO;
import com.carrentingservice.vehiclelisting.controller.dto.request.BrandFilterRequestDTO;
import com.carrentingservice.vehiclelisting.controller.dto.request.VehicleListingFiltersRequestDTO;
import com.carrentingservice.vehiclelisting.domain.CarTypeEntity;
import com.carrentingservice.vehiclelisting.domain.CityMasterEntity;
import com.carrentingservice.vehiclelisting.domain.FuelTypeEntity;
import com.carrentingservice.vehiclelisting.domain.PriceMasterEntity;
import com.carrentingservice.vehiclelisting.domain.ProducerTypeEntity;
import com.carrentingservice.vehiclelisting.domain.TransmissionTypeEntity;
import com.carrentingservice.vehiclelisting.domain.VehicleInventoryEntity;
import com.carrentingservice.vehiclelisting.domain.relationship.InventoryCityMasterEntity;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;
import com.carrentingservice.vehiclelisting.repo.InventoryCityMasterRepo;
import com.carrentingservice.vehiclelisting.repo.VehicleInventoryRepo;
import com.carrentingservice.vehiclelisting.service.VehicleCategoryFiltersService;
import com.carrentingservice.vehiclelisting.service.mappers.VehicleInventoryMapper;

@Service
public class VehicleCategoryFiltersServiceImpl implements VehicleCategoryFiltersService {

	@Autowired
	private VehicleInventoryServiceImpl vehicleInventoryServiceImpl;

	@Autowired
	private VehicleInventoryRepo vehicleInventoryRepo;

	@Autowired
	private VehicleInventoryMapper vehicleInventoryMapper;

	@Autowired
	private InventoryCityMasterRepo inventoryCityMasterRepo;

	@Override
	public InventoryResponseTO filterByTransmissionType(boolean manualTransmission, boolean autoTransmission)
			throws RecordNotFoundException {
		List<VehicleInventoryEntity> vehicleEntityList = null;

		if (manualTransmission && autoTransmission)
			vehicleEntityList = vehicleInventoryRepo.findByTransmissionType(
					new TransmissionTypeEntity(CommonConstants.MANUAL_TRANSMISSION_TYPE),
					new TransmissionTypeEntity(CommonConstants.AUTOMATIC_TRANSMISSION_TYPE));
		else if (manualTransmission)
			vehicleEntityList = vehicleInventoryRepo
					.findByTransmissionType(new TransmissionTypeEntity(CommonConstants.MANUAL_TRANSMISSION_TYPE));
		else if (autoTransmission)
			vehicleEntityList = vehicleInventoryRepo
					.findByTransmissionType(new TransmissionTypeEntity(CommonConstants.AUTOMATIC_TRANSMISSION_TYPE));

		if (vehicleEntityList == null || vehicleEntityList.isEmpty()) {
			throw new RecordNotFoundException("Error occured in method " + " filterByTransmissionType() " + " of class "
					+ this.getClass().getName() + ". Exception code is "
					+ ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR_CODE + " and exception message is "
					+ ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR + ".");
		}
		InventoryResponseTO inventoryTO = new InventoryResponseTO();
		inventoryTO.setListVehicleDTO(vehicleInventoryMapper.toVehicleInventoryDTO(vehicleEntityList));
		return inventoryTO;
	}

	@Override
	public InventoryResponseTO filterByFuelType(boolean petrol, boolean diesel) throws RecordNotFoundException {
		List<VehicleInventoryEntity> vehicleEntityList = null;
		if (petrol && diesel)
			vehicleEntityList = vehicleInventoryRepo.findByFuelType(
					new FuelTypeEntity(CommonConstants.PETROL_FUEL_TYPE),
					new FuelTypeEntity(CommonConstants.DIESEL_FUEL_TYPE));
		else if (petrol)
			vehicleEntityList = vehicleInventoryRepo
					.findByFuelType(new FuelTypeEntity(CommonConstants.PETROL_FUEL_TYPE));
		else if (diesel)
			vehicleEntityList = vehicleInventoryRepo
					.findByFuelType(new FuelTypeEntity(CommonConstants.DIESEL_FUEL_TYPE));

		if (vehicleEntityList == null || vehicleEntityList.isEmpty()) {
			throw new RecordNotFoundException(
					"Error occured in method " + " filterByFuelType() " + " of class " + this.getClass().getName()
							+ ". Exception code is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR_CODE
							+ " and exception message is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR + ".");
		}
		InventoryResponseTO inventoryTO = new InventoryResponseTO();
		inventoryTO.setListVehicleDTO(vehicleInventoryMapper.toVehicleInventoryDTO(vehicleEntityList));
		return inventoryTO;
	}

	@Override
	public InventoryResponseTO filterBySegmentType(boolean suv, boolean sedan, boolean hatchback)
			throws RecordNotFoundException {
		List<VehicleInventoryEntity> vehicleEntityList = null;

		if (suv && sedan && hatchback)
			vehicleEntityList = vehicleInventoryRepo.findByMultipleCarTypes(
					new CarTypeEntity(CommonConstants.SUV_CAR_TYPE), new CarTypeEntity(CommonConstants.SEDAN_CAR_TYPE),
					new CarTypeEntity(CommonConstants.HATCHBACK_CAR_TYPE));
		else if (suv && !sedan && !hatchback)
			vehicleEntityList = vehicleInventoryRepo.findByCarType(new CarTypeEntity(CommonConstants.SUV_CAR_TYPE));
		else if (!suv && sedan && !hatchback)
			vehicleEntityList = vehicleInventoryRepo.findByCarType(new CarTypeEntity(CommonConstants.SEDAN_CAR_TYPE));
		else if (!suv && !sedan && hatchback)
			vehicleEntityList = vehicleInventoryRepo
					.findByCarType(new CarTypeEntity(CommonConstants.HATCHBACK_CAR_TYPE));
		else if (suv && sedan && !hatchback)
			vehicleEntityList = vehicleInventoryRepo.findByMultipleCarTypes(
					new CarTypeEntity(CommonConstants.SUV_CAR_TYPE), new CarTypeEntity(CommonConstants.SEDAN_CAR_TYPE));
		else if (suv && !sedan && hatchback)
			vehicleEntityList = vehicleInventoryRepo.findByMultipleCarTypes(
					new CarTypeEntity(CommonConstants.SUV_CAR_TYPE),
					new CarTypeEntity(CommonConstants.HATCHBACK_CAR_TYPE));
		else if (!suv && sedan && hatchback)
			vehicleEntityList = vehicleInventoryRepo.findByMultipleCarTypes(
					new CarTypeEntity(CommonConstants.SEDAN_CAR_TYPE),
					new CarTypeEntity(CommonConstants.HATCHBACK_CAR_TYPE));

		if (vehicleEntityList == null || vehicleEntityList.isEmpty()) {
			throw new RecordNotFoundException(
					"Error occured in method " + " filterBySegmentType() " + " of class " + this.getClass().getName()
							+ ". Exception code is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR_CODE
							+ " and exception message is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR + ".");
		}
		InventoryResponseTO inventoryTO = new InventoryResponseTO();
		inventoryTO.setListVehicleDTO(vehicleInventoryMapper.toVehicleInventoryDTO(vehicleEntityList));
		return inventoryTO;
	}

	@Override
	public InventoryResponseTO filterByBrandName(BrandFilterRequestDTO brands) throws RecordNotFoundException {

		List<VehicleInventoryEntity> vehicleEntityList = vehicleInventoryRepo
				.findByProducer(prepareProducerTypeEntityList(brands.getBrands()));
		if (vehicleEntityList == null || vehicleEntityList.isEmpty()) {
			throw new RecordNotFoundException(
					"Error occured in method " + " filterByBrandName() " + " of class " + this.getClass().getName()
							+ ". Exception code is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR_CODE
							+ " and exception message is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR + ".");
		}
		InventoryResponseTO inventoryTO = new InventoryResponseTO();
		inventoryTO.setListVehicleDTO(vehicleInventoryMapper.toVehicleInventoryDTO(vehicleEntityList));
		return inventoryTO;
	}

	@Override
	public InventoryResponseTO filterByCityName(String city) throws RecordNotFoundException {

		List<InventoryCityMasterEntity> inventoryCityList = inventoryCityMasterRepo
				.findByCityMasterCityCode(new CityMasterEntity(city));
		if (inventoryCityList == null || inventoryCityList.isEmpty())
			throw new RecordNotFoundException(
					"Error occured in method " + " filterByCityName() " + " of class " + this.getClass().getName()
							+ ". Exception code is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR_CODE
							+ " and exception message is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR + ".");
		List<VehicleInventoryEntity> vehicleEntityList = new ArrayList<>();
		for (InventoryCityMasterEntity inventoryCityMaster : inventoryCityList) {
			vehicleEntityList.add(inventoryCityMaster.getVehicleInventoryId());
		}
		InventoryResponseTO inventoryTO = new InventoryResponseTO();
		inventoryTO.setListVehicleDTO(vehicleInventoryMapper.toVehicleInventoryDTO(vehicleEntityList));
		return inventoryTO;
	}

	@Override
	public InventoryResponseTO filterByPriceRange(Long minPrice, Long maxPrice) throws RecordNotFoundException {

		if (minPrice > maxPrice) {
			throw new RecordNotFoundException(
					"Error occured in method " + " filterByPriceRange() " + " of class " + this.getClass().getName()
							+ ". Exception code is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR_CODE
							+ " and exception message is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR + ".");
		}

		List<VehicleInventoryEntity> vehicleEntityList = vehicleInventoryRepo
				.findByPriceRange(new PriceMasterEntity(minPrice), new PriceMasterEntity(maxPrice));
		if (vehicleEntityList == null || vehicleEntityList.isEmpty())
			throw new RecordNotFoundException(
					"Error occured in method " + " filterByPriceRange() " + " of class " + this.getClass().getName()
							+ ". Exception code is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR_CODE
							+ " and exception message is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR + ".");
		InventoryResponseTO inventoryTO = new InventoryResponseTO();
		inventoryTO.setListVehicleDTO(vehicleInventoryMapper.toVehicleInventoryDTO(vehicleEntityList));
		return inventoryTO;
	}

	private List<ProducerTypeEntity> prepareProducerTypeEntityList(List<String> brands) {
		List<ProducerTypeEntity> producerEntityList = new ArrayList<>();
		for (String brand : brands) {
			producerEntityList.add(new ProducerTypeEntity(brand));
		}
		return producerEntityList;
	}

	@Override
	public InventoryResponseTO vehicleListingFilters(VehicleListingFiltersRequestDTO vehicleFilters, Long startPage,
			Long size) throws RecordNotFoundException {
		Integer totalFilters = 0;
		HashMap<String, Integer> vehicleMap = new HashMap<>();
		if (vehicleFilters.isTransmission()) {
			totalFilters++;
			List<VehicleInventoryDTO> response = filterByTransmissionType(vehicleFilters.isManualTransmission(),
					vehicleFilters.isAutomaticTransmission()).getListVehicleDTO();
			for (VehicleInventoryDTO vehicle : response) {
				if (!vehicleMap.containsKey(vehicle.getId())) {
					vehicleMap.put(vehicle.getId(), 1);
				} else {
					int value = vehicleMap.get(vehicle.getId()) + 1;
					vehicleMap.replace(vehicle.getId(), value);
				}
			}
		}
		if (vehicleFilters.isFuel()) {
			totalFilters++;
			List<VehicleInventoryDTO> response = filterByFuelType(vehicleFilters.isPetrolFuel(),
					vehicleFilters.isDieselFuel()).getListVehicleDTO();
			for (VehicleInventoryDTO vehicle : response) {
				if (!vehicleMap.containsKey(vehicle.getId())) {
					vehicleMap.put(vehicle.getId(), 1);
				} else {
					int value = vehicleMap.get(vehicle.getId()) + 1;
					vehicleMap.replace(vehicle.getId(), value);
				}
			}
		}
		if (vehicleFilters.isSegment()) {
			totalFilters++;
			List<VehicleInventoryDTO> response = filterBySegmentType(vehicleFilters.isSuvSegment(),
					vehicleFilters.isSedanSegment(), vehicleFilters.isHatchBackSegment()).getListVehicleDTO();
			for (VehicleInventoryDTO vehicle : response) {
				if (!vehicleMap.containsKey(vehicle.getId())) {
					vehicleMap.put(vehicle.getId(), 1);
				} else {
					int value = vehicleMap.get(vehicle.getId()) + 1;
					vehicleMap.replace(vehicle.getId(), value);
				}
			}
		}
		if (vehicleFilters.isCity()) {
			totalFilters++;
			List<VehicleInventoryDTO> response = filterByCityName(vehicleFilters.getCityName()).getListVehicleDTO();
			for (VehicleInventoryDTO vehicle : response) {
				if (!vehicleMap.containsKey(vehicle.getId())) {
					vehicleMap.put(vehicle.getId(), 1);
				} else {
					int value = vehicleMap.get(vehicle.getId()) + 1;
					vehicleMap.replace(vehicle.getId(), value);
				}
			}
		}
		if (vehicleFilters.isPrice()) {
			totalFilters++;
			List<VehicleInventoryDTO> response = filterByPriceRange(vehicleFilters.getMinPrice(),
					vehicleFilters.getMaxPrice()).getListVehicleDTO();
			for (VehicleInventoryDTO vehicle : response) {
				if (!vehicleMap.containsKey(vehicle.getId())) {
					vehicleMap.put(vehicle.getId(), 1);
				} else {
					int value = vehicleMap.get(vehicle.getId()) + 1;
					vehicleMap.replace(vehicle.getId(), value);
				}
			}
		}
		if (vehicleFilters.isBrand()) {
			totalFilters++;
			List<VehicleInventoryDTO> response = filterByBrandName(
					new BrandFilterRequestDTO(vehicleFilters.getBrands())).getListVehicleDTO();
			for (VehicleInventoryDTO vehicle : response) {
				if (!vehicleMap.containsKey(vehicle.getId())) {
					vehicleMap.put(vehicle.getId(), 1);
				} else {
					int value = vehicleMap.get(vehicle.getId()) + 1;
					vehicleMap.replace(vehicle.getId(), value);
				}
			}
		}

		if (vehicleMap.isEmpty()) {
			throw new RecordNotFoundException(
					"Error occured in method " + " filterByPriceRange() " + " of class " + this.getClass().getName()
							+ ". Exception code is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR_CODE
							+ " and exception message is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR + ".");
		}

		List<String> listID = new ArrayList<>();
		for (Map.Entry<String, Integer> me : vehicleMap.entrySet()) {
			if (me.getValue().equals(totalFilters)) {
				listID.add(me.getKey());
			}
		}

		return vehicleInventoryServiceImpl.getVehicleInventoryByIdList(listID, startPage, size);
	}
}
