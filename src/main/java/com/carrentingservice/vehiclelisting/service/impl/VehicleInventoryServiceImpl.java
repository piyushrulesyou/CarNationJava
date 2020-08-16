package com.carrentingservice.vehiclelisting.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.carrentingservice.vehiclelisting.constants.ErrorConstants;
import com.carrentingservice.vehiclelisting.controller.dto.InventoryRequestDTO;
import com.carrentingservice.vehiclelisting.controller.dto.InventoryResponseTO;
import com.carrentingservice.vehiclelisting.controller.dto.VehicleInventoryDTO;
import com.carrentingservice.vehiclelisting.domain.CarTypeEntity;
import com.carrentingservice.vehiclelisting.domain.CityMasterEntity;
import com.carrentingservice.vehiclelisting.domain.ColorMasterEntity;
import com.carrentingservice.vehiclelisting.domain.FuelTypeEntity;
import com.carrentingservice.vehiclelisting.domain.PriceMasterEntity;
import com.carrentingservice.vehiclelisting.domain.ProducerTypeEntity;
import com.carrentingservice.vehiclelisting.domain.TenurePriceMasterEntity;
import com.carrentingservice.vehiclelisting.domain.TransmissionTypeEntity;
import com.carrentingservice.vehiclelisting.domain.VehicleInventoryEntity;
import com.carrentingservice.vehiclelisting.domain.relationship.InventoryCityMasterEntity;
import com.carrentingservice.vehiclelisting.domain.relationship.InventoryColorMasterEntity;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;
import com.carrentingservice.vehiclelisting.repo.InventoryCityMasterRepo;
import com.carrentingservice.vehiclelisting.repo.InventoryColorMasterRepo;
import com.carrentingservice.vehiclelisting.repo.TenurePriceMasterRepo;
import com.carrentingservice.vehiclelisting.repo.VehicleInventoryRepo;
import com.carrentingservice.vehiclelisting.service.AWSService;
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
	private VehicleInventoryMapper vehicleInventoryMapper;

	@Autowired
	private TenurePriceMasterRepo tenurePriceMasterRepo;

	@Autowired
	private AWSService awsService;

	@Override
	public InventoryResponseTO getVehicleInventory(Long startPage, Long size) throws RecordNotFoundException {

		Pageable pageData = PageRequest.of(startPage.intValue(), size.intValue());
		Page<VehicleInventoryEntity> vehicleEntityList = vehicleInventoryRepo.findAll(pageData);
		if (vehicleEntityList.isEmpty()) {
			throw new RecordNotFoundException(
					"Error occured in method " + " getVehicleInventory() " + " of class " + this.getClass().getName()
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
	public InventoryResponseTO getVehicleInventoryById(String vehicleId) throws RecordNotFoundException {

		Optional<VehicleInventoryEntity> vehicleInventoryEntity = vehicleInventoryRepo.findById(vehicleId);
		if (!vehicleInventoryEntity.isPresent()) {
			throw new RecordNotFoundException(
					"Error occured in method " + " getVehicleInventory() " + " of class " + this.getClass().getName()
							+ ". Exception code is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR_CODE
							+ " and exception message is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR + ".");
		}
		InventoryResponseTO inventoryTO = new InventoryResponseTO();
		List<VehicleInventoryDTO> listVehicle = new ArrayList<>();
		listVehicle.add(vehicleInventoryMapper.toVehicleInventoryDTO(vehicleInventoryEntity.get()));
		inventoryTO.setListVehicleDTO(listVehicle);
		return inventoryTO;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public InventoryRequestDTO addInventory(InventoryRequestDTO inventoryDetails, MultipartFile smallSizeImage,
			MultipartFile fullSizeImage) throws IOException {
		inventoryDetails = uploadImageToS3(inventoryDetails, smallSizeImage, fullSizeImage);
		System.out.println("111111111");
		inventoryDetails = generateVehicleId(inventoryDetails);
		System.out.println("222222222");
		System.out.println(inventoryDetails.getId());
		System.out.println("333333333");
		TenurePriceMasterEntity tenurePriceMaster = tenurePriceMasterRepo
				.save(prepareTenurePriceEntity(inventoryDetails));
		System.out.println(tenurePriceMaster);
		System.out.println("444444444");
		VehicleInventoryEntity vehicleInventoryEntity = mapVehicleDtoToEntity(inventoryDetails, tenurePriceMaster);
		vehicleInventoryRepo.save(vehicleInventoryEntity);
		inventoryCityMasterRepo.saveAll(prepareInventoryCityEntity(inventoryDetails));
		inventoryColorMasterRepo.saveAll(prepareInventoryColorEntity(inventoryDetails));
		return inventoryDetails;
	}

	@Override
	public InventoryResponseTO getVehicleInventoryByIdList(List<String> vehicleIds, Long startPage, Long size)
			throws RecordNotFoundException {
		Pageable pageData = PageRequest.of(startPage.intValue(), size.intValue());
		Page<VehicleInventoryEntity> vehicleEntityList = vehicleInventoryRepo.findAllById(vehicleIds, pageData);
		if (vehicleEntityList.isEmpty()) {
			throw new RecordNotFoundException(
					"Error occured in method " + " getVehicleInventory() " + " of class " + this.getClass().getName()
							+ ". Exception code is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR_CODE
							+ " and exception message is " + ErrorConstants.VEHICLE_INVENTORY_NOT_FOUND_ERROR + ".");
		}
		InventoryResponseTO inventoryTO = new InventoryResponseTO();
		inventoryTO.setListVehicleDTO(vehicleInventoryMapper.toVehicleInventoryDTO(vehicleEntityList.getContent()));
		inventoryTO.setTotalPages(vehicleEntityList.getTotalPages());
		inventoryTO.setTotalEnteries(vehicleEntityList.getTotalElements());
		return inventoryTO;
	}

	private InventoryRequestDTO generateVehicleId(InventoryRequestDTO inventoryDetails) {
		System.out.println("aaaaaa");
		System.out.println(inventoryDetails.getProducer().substring(0, 3));
		System.out.println("bbbbbb");
		System.out.println(inventoryDetails.getModel().substring(0, 3));
		System.out.println("cccccc");
		System.out.println(inventoryDetails.getFuelType());
		System.out.println("dddddd");
		String vehicleId = inventoryDetails.getProducer().substring(0, 3) + inventoryDetails.getModel().substring(0, 3)
				+ inventoryDetails.getFuelType();
		inventoryDetails.setId(vehicleId);
		return inventoryDetails;
	}

	private InventoryRequestDTO uploadImageToS3(InventoryRequestDTO inventoryDetails, MultipartFile smallSizeImage,
			MultipartFile fullSizeImage) throws IOException {
		List<MultipartFile> multipartFileList = new ArrayList<>();
		multipartFileList.add(smallSizeImage);
		multipartFileList.add(fullSizeImage);
		List<String> carImageS3URL = awsService.uploadFilesToS3(multipartFileList);
		inventoryDetails.setSmallSizeImageURL(carImageS3URL.get(0));
		inventoryDetails.setFullSizeImageURL(carImageS3URL.get(1));
		return inventoryDetails;
	}

	private TenurePriceMasterEntity prepareTenurePriceEntity(InventoryRequestDTO inventoryDetails) {
		TenurePriceMasterEntity tenurePriceMasterEntity = new TenurePriceMasterEntity();
		tenurePriceMasterEntity.setMonths3(inventoryDetails.getTenureMaster().get(0));
		tenurePriceMasterEntity.setMonths6(inventoryDetails.getTenureMaster().get(1));
		tenurePriceMasterEntity.setMonths12(inventoryDetails.getTenureMaster().get(2));
		tenurePriceMasterEntity.setMonths18(inventoryDetails.getTenureMaster().get(3));
		tenurePriceMasterEntity.setMonths24(inventoryDetails.getTenureMaster().get(4));
		tenurePriceMasterEntity.setMonths36(inventoryDetails.getTenureMaster().get(5));
		tenurePriceMasterEntity.setCreatedBy(inventoryDetails.getCreatedBy());
		tenurePriceMasterEntity.setCreatedDate(inventoryDetails.getCreatedDate());
		tenurePriceMasterEntity.setModifiedBy(inventoryDetails.getModifiedBy());
		tenurePriceMasterEntity.setModifiedDate(inventoryDetails.getModifiedDate());
		return tenurePriceMasterEntity;
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

	private VehicleInventoryEntity mapVehicleDtoToEntity(InventoryRequestDTO inventoryDetails,
			TenurePriceMasterEntity tenurePriceMaster) {
		VehicleInventoryEntity vehicleInventoryEntity = new VehicleInventoryEntity();
		vehicleInventoryEntity.setId(inventoryDetails.getId());
		vehicleInventoryEntity.setModel(inventoryDetails.getModel());
		vehicleInventoryEntity.setVariant(inventoryDetails.getVariant());
		vehicleInventoryEntity.setFuelType(new FuelTypeEntity(inventoryDetails.getFuelType()));
		vehicleInventoryEntity.setTransmissionType(new TransmissionTypeEntity(inventoryDetails.getTransmissionType()));
		vehicleInventoryEntity.setCarType(new CarTypeEntity(inventoryDetails.getCarType()));
		vehicleInventoryEntity.setSeats(inventoryDetails.getSeats());
		vehicleInventoryEntity.setProducer(new ProducerTypeEntity(inventoryDetails.getProducer()));
		vehicleInventoryEntity.setSortOrder(inventoryDetails.getSortOrder());
		vehicleInventoryEntity.setFullSizeImage(inventoryDetails.getFullSizeImageURL());
		vehicleInventoryEntity.setSmallSizeImage(inventoryDetails.getSmallSizeImageURL());
		vehicleInventoryEntity.setPopular(inventoryDetails.isPopular());
		vehicleInventoryEntity.setExtraKmCharge(inventoryDetails.getExtraKmCharge());
		vehicleInventoryEntity.setTenureMaster(tenurePriceMaster);
		vehicleInventoryEntity.setPriceMaster(new PriceMasterEntity(inventoryDetails.getPriceMaster()));
		vehicleInventoryEntity.setInsuranceCost(inventoryDetails.getInsuranceCost());
		vehicleInventoryEntity.setSecurityDeposit(inventoryDetails.getSecurityDeposit());
		vehicleInventoryEntity.setShowVariant(inventoryDetails.isShowVariant());
		return vehicleInventoryEntity;
	}
}