package com.carrentingservice.vehiclelisting.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrentingservice.vehiclelisting.constants.CommonConstants;
import com.carrentingservice.vehiclelisting.constants.ErrorConstants;
import com.carrentingservice.vehiclelisting.controller.dto.VehicleInventoryDTO;
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
	public List<VehicleInventoryDTO> filterByTransmissionType(boolean manualTransmission, boolean autoTransmission)
			throws RecordNotFoundException {
		if (manualTransmission && autoTransmission)
			return vehicleInventoryServiceImpl.getVehicleInventory();

		List<VehicleInventoryEntity> vehicleEntityList = null;
		if (manualTransmission) {
			vehicleEntityList = vehicleInventoryRepo
					.findByTransmissionType(new TransmissionTypeEntity(CommonConstants.MANUAL_TRANSMISSION_TYPE));
		} else if (autoTransmission) {
			vehicleEntityList = vehicleInventoryRepo
					.findByTransmissionType(new TransmissionTypeEntity(CommonConstants.AUTOMATIC_TRANSMISSION_TYPE));
		}
		if (vehicleEntityList == null || vehicleEntityList.isEmpty()) {
			throw new RecordNotFoundException("Error occured in method " + " filterByTransmissionType() " + " of class "
					+ this.getClass().getName() + ". Exception code is " + ErrorConstants.NOT_FOUND_ERROR_CODE
					+ " and exception message is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND + ".");
		}
		return vehicleInventoryMapper.toVehicleInventoryDTO(vehicleEntityList);
	}

	@Override
	public List<VehicleInventoryDTO> filterByFuelType(boolean petrol, boolean diesel) throws RecordNotFoundException {
		if (petrol && diesel)
			return vehicleInventoryServiceImpl.getVehicleInventory();

		List<VehicleInventoryEntity> vehicleEntityList = null;
		if (petrol) {
			vehicleEntityList = vehicleInventoryRepo
					.findByFuelType(new FuelTypeEntity(CommonConstants.PETROL_FUEL_TYPE));
		} else if (diesel) {
			vehicleEntityList = vehicleInventoryRepo
					.findByFuelType(new FuelTypeEntity(CommonConstants.DIESEL_FUEL_TYPE));
		}
		if (vehicleEntityList == null || vehicleEntityList.isEmpty()) {
			throw new RecordNotFoundException("Error occured in method " + " filterByFuelType() " + " of class "
					+ this.getClass().getName() + ". Exception code is " + ErrorConstants.NOT_FOUND_ERROR_CODE
					+ " and exception message is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND + ".");
		}
		return vehicleInventoryMapper.toVehicleInventoryDTO(vehicleEntityList);
	}

	@Override
	public List<VehicleInventoryDTO> filterBySegmentType(boolean suv, boolean sedan, boolean hatchback)
			throws RecordNotFoundException {
		if (suv && sedan && hatchback)
			return vehicleInventoryServiceImpl.getVehicleInventory();

		List<VehicleInventoryEntity> vehicleEntityList = null;
		if (suv && !sedan && !hatchback) {
			vehicleEntityList = vehicleInventoryRepo.findByCarType(new CarTypeEntity(CommonConstants.SUV_CAR_TYPE));
		} else if (!suv && sedan && !hatchback) {
			vehicleEntityList = vehicleInventoryRepo.findByCarType(new CarTypeEntity(CommonConstants.SEDAN_CAR_TYPE));
		} else if (!suv && !sedan && hatchback) {
			vehicleEntityList = vehicleInventoryRepo
					.findByCarType(new CarTypeEntity(CommonConstants.HATCHBACK_CAR_TYPE));
		}
		if (vehicleEntityList == null || vehicleEntityList.isEmpty()) {
			throw new RecordNotFoundException("Error occured in method " + " filterBySegmentType() " + " of class "
					+ this.getClass().getName() + ". Exception code is " + ErrorConstants.NOT_FOUND_ERROR_CODE
					+ " and exception message is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND + ".");
		}
		return vehicleInventoryMapper.toVehicleInventoryDTO(vehicleEntityList);
	}

	@Override
	public List<VehicleInventoryDTO> filterByBrandName(List<String> brands) throws RecordNotFoundException {
		List<VehicleInventoryEntity> vehicleEntityList = vehicleInventoryRepo
				.findByProducer(prepareProducerTypeEntityList(brands));
		if (vehicleEntityList == null || vehicleEntityList.isEmpty()) {
			throw new RecordNotFoundException("Error occured in method " + " filterByBrandName() " + " of class "
					+ this.getClass().getName() + ". Exception code is " + ErrorConstants.NOT_FOUND_ERROR_CODE
					+ " and exception message is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND + ".");
		}
		return vehicleInventoryMapper.toVehicleInventoryDTO(vehicleEntityList);
	}

	@Override
	public List<VehicleInventoryDTO> filterByCityName(String city) throws RecordNotFoundException {
		List<InventoryCityMasterEntity> inventoryCityList = inventoryCityMasterRepo
				.findByCityMasterCityCode(new CityMasterEntity(city));
		if (inventoryCityList == null || inventoryCityList.isEmpty())
			throw new RecordNotFoundException("Error occured in method " + " filterByCityName() " + " of class "
					+ this.getClass().getName() + ". Exception code is " + ErrorConstants.NOT_FOUND_ERROR_CODE
					+ " and exception message is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND + ".");
		List<VehicleInventoryEntity> vehicleList = new ArrayList<>();
		for (InventoryCityMasterEntity inventoryCityMaster : inventoryCityList) {
			vehicleList.add(inventoryCityMaster.getVehicleInventoryId());
		}
		return vehicleInventoryMapper.toVehicleInventoryDTO(vehicleList);
	}

	@Override
	public List<VehicleInventoryDTO> filterByPriceRange(Long minPrice, Long maxPrice) throws RecordNotFoundException {
		System.out.println("**" + minPrice + "##" + maxPrice);
		List<VehicleInventoryEntity> vehicleEntity = vehicleInventoryRepo
				.findByPriceRange(new PriceMasterEntity(minPrice), new PriceMasterEntity(maxPrice));
		if (vehicleEntity == null || vehicleEntity.isEmpty())
			throw new RecordNotFoundException("Error occured in method " + " filterByPriceRange() " + " of class "
					+ this.getClass().getName() + ". Exception code is " + ErrorConstants.NOT_FOUND_ERROR_CODE
					+ " and exception message is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND + ".");
		return vehicleInventoryMapper.toVehicleInventoryDTO(vehicleEntity);
	}

	private List<ProducerTypeEntity> prepareProducerTypeEntityList(List<String> brands) {
		List<ProducerTypeEntity> producerEntityList = new ArrayList<>();
		for (String brand : brands) {
			producerEntityList.add(new ProducerTypeEntity(brand));
		}
		return producerEntityList;
	}
}
