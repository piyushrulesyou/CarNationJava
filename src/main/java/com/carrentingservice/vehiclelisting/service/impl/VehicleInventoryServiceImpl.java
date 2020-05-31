package com.carrentingservice.vehiclelisting.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrentingservice.vehiclelisting.controller.dto.InventoryRequestDTO;
import com.carrentingservice.vehiclelisting.controller.dto.VehicleInventoryDTO;
import com.carrentingservice.vehiclelisting.domain.CarTypeEntity;
import com.carrentingservice.vehiclelisting.domain.CityMasterEntity;
import com.carrentingservice.vehiclelisting.domain.ColorMasterEntity;
import com.carrentingservice.vehiclelisting.domain.FuelTypeEntity;
import com.carrentingservice.vehiclelisting.domain.ModelTypeEntity;
import com.carrentingservice.vehiclelisting.domain.PriceMasterEntity;
import com.carrentingservice.vehiclelisting.domain.ProducerTypeEntity;
import com.carrentingservice.vehiclelisting.domain.TenurePriceMasterEntity;
import com.carrentingservice.vehiclelisting.domain.TransmissionTypeEntity;
import com.carrentingservice.vehiclelisting.domain.VariantTypeEntity;
import com.carrentingservice.vehiclelisting.domain.VehicleInventoryEntity;
import com.carrentingservice.vehiclelisting.domain.relationship.InventoryCityMasterEntity;
import com.carrentingservice.vehiclelisting.domain.relationship.InventoryColorMasterEntity;
import com.carrentingservice.vehiclelisting.domain.relationship.InventoryTenureMasterEntity;
import com.carrentingservice.vehiclelisting.repo.InventoryCityMasterRepo;
import com.carrentingservice.vehiclelisting.repo.InventoryColorMasterRepo;
import com.carrentingservice.vehiclelisting.repo.InventoryTenureMasterRepo;
import com.carrentingservice.vehiclelisting.repo.VehicleInventoryRepo;
import com.carrentingservice.vehiclelisting.service.VehicleInventoryService;
import com.carrentingservice.vehiclelisting.service.mappers.VehicleInventoryMapper;

@Service
public class VehicleInventoryServiceImpl implements VehicleInventoryService {

	@Autowired
	private VehicleInventoryRepo vehicleInventoryRepo;

	@Autowired
	private InventoryCityMasterRepo inventoryCityMasterRepo;

	@Autowired
	private InventoryColorMasterRepo inventoryColorMasterRepo;

	@Autowired
	private InventoryTenureMasterRepo inventoryTenureMasterRepo;

	@Autowired
	private VehicleInventoryMapper vehicleInventoryMapper;

	@Override
	public List<VehicleInventoryDTO> getVehicleInventory() throws Exception {
		List<VehicleInventoryEntity> vehicleInventoryEntity = vehicleInventoryRepo.findAll();

		if (vehicleInventoryEntity.isEmpty()) {
			throw new Exception();
		}
		return vehicleInventoryMapper.toVehicleInventoryDTO(vehicleInventoryEntity);
	}

	@Override
	public VehicleInventoryDTO getVehicleInventoryById(String vehicleId) throws Exception {
		Optional<VehicleInventoryEntity> vehicleInventoryEntity = vehicleInventoryRepo.findById(vehicleId);

		if (!vehicleInventoryEntity.isPresent()) {
			throw new Exception();
		}
		return vehicleInventoryMapper.toVehicleInventoryDTO(vehicleInventoryEntity.get());
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public InventoryRequestDTO addInventory(InventoryRequestDTO inventoryDetails) {
		VehicleInventoryEntity vehicleInventoryEntity = mapVehicleDtoTOEntity(inventoryDetails);
		vehicleInventoryRepo.save(vehicleInventoryEntity);
		inventoryCityMasterRepo.saveAll(prepareInventoryCityEntity(inventoryDetails));
		inventoryColorMasterRepo.saveAll(prepareInventoryColorEntity(inventoryDetails));
		inventoryTenureMasterRepo.saveAll(prepareInventoryTenureEntity(inventoryDetails));
		return inventoryDetails;
	}

	private List<InventoryTenureMasterEntity> prepareInventoryTenureEntity(InventoryRequestDTO inventoryDetails) {
		List<InventoryTenureMasterEntity> listTenureEntity = new ArrayList<>();
		for (String eachTenure : inventoryDetails.getTenureMaster()) {
			InventoryTenureMasterEntity tenureEntity = new InventoryTenureMasterEntity();
			tenureEntity.setVehicleInventoryId(new VehicleInventoryEntity(inventoryDetails.getId()));
			tenureEntity.setTenureMasterTenureDuration(new TenurePriceMasterEntity(eachTenure));
			listTenureEntity.add(tenureEntity);
		}
		return listTenureEntity;
	}

	private List<InventoryColorMasterEntity> prepareInventoryColorEntity(InventoryRequestDTO inventoryDetails) {
		List<InventoryColorMasterEntity> listColorEntity = new ArrayList<>();
		for (String eachColor : inventoryDetails.getColorMaster()) {
			InventoryColorMasterEntity colorEntity = new InventoryColorMasterEntity();
			colorEntity.setVehicleInventoryId(new VehicleInventoryEntity(inventoryDetails.getId()));
			colorEntity.setColorMasterColorCode(new ColorMasterEntity(eachColor));
			listColorEntity.add(colorEntity);
		}
		return listColorEntity;
	}

	private List<InventoryCityMasterEntity> prepareInventoryCityEntity(InventoryRequestDTO inventoryDetails) {
		List<InventoryCityMasterEntity> listCityEntity = new ArrayList<>();
		for (String eachCity : inventoryDetails.getCityMaster()) {
			InventoryCityMasterEntity cityEntity = new InventoryCityMasterEntity();
			cityEntity.setVehicleInventoryId(new VehicleInventoryEntity(inventoryDetails.getId()));
			cityEntity.setCityMasterCityCode(new CityMasterEntity(eachCity));
			listCityEntity.add(cityEntity);
		}
		return listCityEntity;
	}

	private VehicleInventoryEntity mapVehicleDtoTOEntity(InventoryRequestDTO inventoryDetails) {
		VehicleInventoryEntity vehicleInventoryEntity = new VehicleInventoryEntity();
		vehicleInventoryEntity.setId(inventoryDetails.getId());
		vehicleInventoryEntity.setModel(new ModelTypeEntity(inventoryDetails.getModel()));
		vehicleInventoryEntity.setVariant(new VariantTypeEntity(inventoryDetails.getVariant()));
		vehicleInventoryEntity.setFuelType(new FuelTypeEntity(inventoryDetails.getFuelType()));
		vehicleInventoryEntity.setTransmissionType(new TransmissionTypeEntity(inventoryDetails.getTransmissionType()));
		vehicleInventoryEntity.setCarType(new CarTypeEntity(inventoryDetails.getCarType()));
		vehicleInventoryEntity.setSeats(inventoryDetails.getSeats());
		vehicleInventoryEntity.setProducer(new ProducerTypeEntity(inventoryDetails.getProducer()));
		vehicleInventoryEntity.setSortOrder(inventoryDetails.getSortOrder());
		vehicleInventoryEntity.setFullSizeImage(inventoryDetails.getFullSizeImage());
		vehicleInventoryEntity.setSmallSizeImage(inventoryDetails.getSmallSizeImage());
		vehicleInventoryEntity.setPopular(inventoryDetails.isPopular());
		vehicleInventoryEntity.setExtraKmCharge(inventoryDetails.getExtraKmCharge());
		vehicleInventoryEntity.setPriceMaster(new PriceMasterEntity(inventoryDetails.getPriceMaster()));
		vehicleInventoryEntity.setInsuranceCost(inventoryDetails.getInsuranceCost());
		vehicleInventoryEntity.setSecurityDeposit(inventoryDetails.getSecurityDeposit());
		vehicleInventoryEntity.setShowVariant(inventoryDetails.isShowVariant());
		return vehicleInventoryEntity;
	}
}
