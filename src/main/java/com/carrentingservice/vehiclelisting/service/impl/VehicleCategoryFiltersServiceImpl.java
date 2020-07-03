package com.carrentingservice.vehiclelisting.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	public InventoryResponseTO filterByTransmissionType(boolean manualTransmission, boolean autoTransmission,
			Long startPage, Long size) throws RecordNotFoundException {
		if (manualTransmission && autoTransmission)
			return vehicleInventoryServiceImpl.getVehicleInventory(startPage, size);

		Pageable pageData = PageRequest.of(startPage.intValue(), size.intValue());
		Page<VehicleInventoryEntity> vehicleEntityList = null;
		if (manualTransmission) {
			vehicleEntityList = vehicleInventoryRepo.findByTransmissionType(
					new TransmissionTypeEntity(CommonConstants.MANUAL_TRANSMISSION_TYPE), pageData);
		} else if (autoTransmission) {
			vehicleEntityList = vehicleInventoryRepo.findByTransmissionType(
					new TransmissionTypeEntity(CommonConstants.AUTOMATIC_TRANSMISSION_TYPE), pageData);
		}
		if (vehicleEntityList == null || vehicleEntityList.isEmpty()) {
			throw new RecordNotFoundException("Error occured in method " + " filterByTransmissionType() " + " of class "
					+ this.getClass().getName() + ". Exception code is "
					+ ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR_CODE + " and exception message is "
					+ ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR + ".");
		}
		InventoryResponseTO inventoryTO = new InventoryResponseTO();
		inventoryTO.setListVehicleDTO(vehicleInventoryMapper.toVehicleInventoryDTO(vehicleEntityList.getContent()));
		inventoryTO.setTotalPages(vehicleEntityList.getTotalPages());
		inventoryTO.setTotalEnteries(vehicleEntityList.getTotalElements());
		return inventoryTO;
	}

	@Override
	public InventoryResponseTO filterByFuelType(boolean petrol, boolean diesel, Long startPage, Long size)
			throws RecordNotFoundException {
		if (petrol && diesel)
			return vehicleInventoryServiceImpl.getVehicleInventory(startPage, size);

		Pageable pageData = PageRequest.of(startPage.intValue(), size.intValue());
		Page<VehicleInventoryEntity> vehicleEntityList = null;
		if (petrol) {
			vehicleEntityList = vehicleInventoryRepo
					.findByFuelType(new FuelTypeEntity(CommonConstants.PETROL_FUEL_TYPE), pageData);
		} else if (diesel) {
			vehicleEntityList = vehicleInventoryRepo
					.findByFuelType(new FuelTypeEntity(CommonConstants.DIESEL_FUEL_TYPE), pageData);
		}
		if (vehicleEntityList == null || vehicleEntityList.isEmpty()) {
			throw new RecordNotFoundException(
					"Error occured in method " + " filterByFuelType() " + " of class " + this.getClass().getName()
							+ ". Exception code is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR_CODE
							+ " and exception message is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR + ".");
		}
		InventoryResponseTO inventoryTO = new InventoryResponseTO();
		inventoryTO.setListVehicleDTO(vehicleInventoryMapper.toVehicleInventoryDTO(vehicleEntityList.getContent()));
		inventoryTO.setTotalPages(vehicleEntityList.getTotalPages());
		inventoryTO.setTotalEnteries(vehicleEntityList.getTotalElements());
		return inventoryTO;
	}

	@Override
	public InventoryResponseTO filterBySegmentType(boolean suv, boolean sedan, boolean hatchback, Long startPage,
			Long size) throws RecordNotFoundException {
		if (suv && sedan && hatchback)
			return vehicleInventoryServiceImpl.getVehicleInventory(startPage, size);

		Pageable pageData = PageRequest.of(startPage.intValue(), size.intValue());
		Page<VehicleInventoryEntity> vehicleEntityList = null;
		if (suv && !sedan && !hatchback) {
			vehicleEntityList = vehicleInventoryRepo.findByCarType(new CarTypeEntity(CommonConstants.SUV_CAR_TYPE),
					pageData);
		} else if (!suv && sedan && !hatchback) {
			vehicleEntityList = vehicleInventoryRepo.findByCarType(new CarTypeEntity(CommonConstants.SEDAN_CAR_TYPE),
					pageData);
		} else if (!suv && !sedan && hatchback) {
			vehicleEntityList = vehicleInventoryRepo
					.findByCarType(new CarTypeEntity(CommonConstants.HATCHBACK_CAR_TYPE), pageData);
		} else if (suv && sedan && !hatchback) {
			vehicleEntityList = vehicleInventoryRepo.findByMultipleCarTypes(
					new CarTypeEntity(CommonConstants.SUV_CAR_TYPE), new CarTypeEntity(CommonConstants.SEDAN_CAR_TYPE),
					pageData);
		} else if (suv && !sedan && hatchback) {
			vehicleEntityList = vehicleInventoryRepo.findByMultipleCarTypes(
					new CarTypeEntity(CommonConstants.SUV_CAR_TYPE),
					new CarTypeEntity(CommonConstants.HATCHBACK_CAR_TYPE), pageData);
		} else if (!suv && sedan && hatchback) {
			vehicleEntityList = vehicleInventoryRepo.findByMultipleCarTypes(
					new CarTypeEntity(CommonConstants.SEDAN_CAR_TYPE),
					new CarTypeEntity(CommonConstants.HATCHBACK_CAR_TYPE), pageData);
		}
		if (vehicleEntityList == null || vehicleEntityList.isEmpty()) {
			throw new RecordNotFoundException(
					"Error occured in method " + " filterBySegmentType() " + " of class " + this.getClass().getName()
							+ ". Exception code is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR_CODE
							+ " and exception message is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR + ".");
		}
		InventoryResponseTO inventoryTO = new InventoryResponseTO();
		inventoryTO.setListVehicleDTO(vehicleInventoryMapper.toVehicleInventoryDTO(vehicleEntityList.getContent()));
		inventoryTO.setTotalPages(vehicleEntityList.getTotalPages());
		inventoryTO.setTotalEnteries(vehicleEntityList.getTotalElements());
		return inventoryTO;
	}

	@Override
	public InventoryResponseTO filterByBrandName(BrandFilterRequestDTO brands, Long startPage, Long size)
			throws RecordNotFoundException {

		Pageable pageData = PageRequest.of(startPage.intValue(), size.intValue());
		Page<VehicleInventoryEntity> vehicleEntityList = vehicleInventoryRepo
				.findByProducer(prepareProducerTypeEntityList(brands.getBrands()), pageData);
		if (vehicleEntityList == null || vehicleEntityList.isEmpty()) {
			throw new RecordNotFoundException(
					"Error occured in method " + " filterByBrandName() " + " of class " + this.getClass().getName()
							+ ". Exception code is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR_CODE
							+ " and exception message is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR + ".");
		}
		InventoryResponseTO inventoryTO = new InventoryResponseTO();
		inventoryTO.setListVehicleDTO(vehicleInventoryMapper.toVehicleInventoryDTO(vehicleEntityList.getContent()));
		inventoryTO.setTotalPages(vehicleEntityList.getTotalPages());
		inventoryTO.setTotalEnteries(vehicleEntityList.getTotalElements());
		return inventoryTO;
	}

	@Override
	public InventoryResponseTO filterByCityName(String city, Long startPage, Long size) throws RecordNotFoundException {

		Pageable pageData = PageRequest.of(startPage.intValue(), size.intValue());
		Page<InventoryCityMasterEntity> inventoryCityList = inventoryCityMasterRepo
				.findByCityMasterCityCode(new CityMasterEntity(city), pageData);
		if (inventoryCityList == null || inventoryCityList.isEmpty())
			throw new RecordNotFoundException(
					"Error occured in method " + " filterByCityName() " + " of class " + this.getClass().getName()
							+ ". Exception code is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR_CODE
							+ " and exception message is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR + ".");
		List<VehicleInventoryEntity> vehicleEntityList = new ArrayList<>();
		for (InventoryCityMasterEntity inventoryCityMaster : inventoryCityList.getContent()) {
			vehicleEntityList.add(inventoryCityMaster.getVehicleInventoryId());
		}
		InventoryResponseTO inventoryTO = new InventoryResponseTO();
		inventoryTO.setListVehicleDTO(vehicleInventoryMapper.toVehicleInventoryDTO(vehicleEntityList));
		inventoryTO.setTotalPages(inventoryCityList.getTotalPages());
		inventoryTO.setTotalEnteries(inventoryCityList.getTotalElements());
		return inventoryTO;
	}

	@Override
	public InventoryResponseTO filterByPriceRange(Long minPrice, Long maxPrice, Long startPage, Long size)
			throws RecordNotFoundException {

		if (minPrice > maxPrice) {
			throw new RecordNotFoundException(
					"Error occured in method " + " filterByPriceRange() " + " of class " + this.getClass().getName()
							+ ". Exception code is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR_CODE
							+ " and exception message is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR + ".");
		}

		Pageable pageData = PageRequest.of(startPage.intValue(), size.intValue());
		Page<VehicleInventoryEntity> vehicleEntityList = vehicleInventoryRepo
				.findByPriceRange(new PriceMasterEntity(minPrice), new PriceMasterEntity(maxPrice), pageData);
		if (vehicleEntityList == null || vehicleEntityList.isEmpty())
			throw new RecordNotFoundException(
					"Error occured in method " + " filterByPriceRange() " + " of class " + this.getClass().getName()
							+ ". Exception code is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR_CODE
							+ " and exception message is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR + ".");
		InventoryResponseTO inventoryTO = new InventoryResponseTO();
		inventoryTO.setListVehicleDTO(vehicleInventoryMapper.toVehicleInventoryDTO(vehicleEntityList.getContent()));
		inventoryTO.setTotalPages(vehicleEntityList.getTotalPages());
		inventoryTO.setTotalEnteries(vehicleEntityList.getTotalElements());
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
	public InventoryResponseTO filterByPriceRange(VehicleListingFiltersRequestDTO vehicleFilters, Long startPage,
			Long size) throws RecordNotFoundException {
		Integer totalFilters = 0;
		HashMap<String, Integer> vehicleMap = new HashMap<>();
		if (vehicleFilters.isTransmission()) {
			totalFilters++;
			List<VehicleInventoryDTO> response = filterByTransmissionType(vehicleFilters.isManualTransmission(),
					vehicleFilters.isAutomaticTransmission(), startPage, size).getListVehicleDTO();
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
					vehicleFilters.isDieselFuel(), startPage, size).getListVehicleDTO();
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
					vehicleFilters.isSedanSegment(), vehicleFilters.isHatchBackSegment(), startPage, size)
							.getListVehicleDTO();
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
			List<VehicleInventoryDTO> response = filterByCityName(vehicleFilters.getCityName(), startPage, size)
					.getListVehicleDTO();
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
					vehicleFilters.getMaxPrice(), startPage, size).getListVehicleDTO();
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
					new BrandFilterRequestDTO(vehicleFilters.getBrands()), startPage, size).getListVehicleDTO();
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
