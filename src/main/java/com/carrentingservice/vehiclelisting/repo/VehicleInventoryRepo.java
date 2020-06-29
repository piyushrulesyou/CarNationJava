package com.carrentingservice.vehiclelisting.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carrentingservice.vehiclelisting.domain.CarTypeEntity;
import com.carrentingservice.vehiclelisting.domain.FuelTypeEntity;
import com.carrentingservice.vehiclelisting.domain.PriceMasterEntity;
import com.carrentingservice.vehiclelisting.domain.ProducerTypeEntity;
import com.carrentingservice.vehiclelisting.domain.TransmissionTypeEntity;
import com.carrentingservice.vehiclelisting.domain.VehicleInventoryEntity;

@Repository
public interface VehicleInventoryRepo extends JpaRepository<VehicleInventoryEntity, String> {

	public Page<VehicleInventoryEntity> findById(String id, Pageable page);

	public Page<VehicleInventoryEntity> findByTransmissionType(TransmissionTypeEntity transmissionType,
			Pageable pageData);

	public Page<VehicleInventoryEntity> findByFuelType(FuelTypeEntity fuelTypeEntity, Pageable pageData);

	public Page<VehicleInventoryEntity> findByCarType(CarTypeEntity carTypeEntity, Pageable pageData);

	@Query("select veh from VehicleInventoryEntity veh where veh.producer in (:producersList)")
	public Page<VehicleInventoryEntity> findByProducer(@Param("producersList") List<ProducerTypeEntity> producersList,
			Pageable pageData);

	@Query("select veh from VehicleInventoryEntity veh where veh.id in (:vehicleIdList)")
	public Page<VehicleInventoryEntity> findByIdList(List<String> vehicleIdList, Pageable pageData);

	@Query("select veh from VehicleInventoryEntity veh where veh.priceMaster BETWEEN :minPrice AND :maxPrice")
	public Page<VehicleInventoryEntity> findByPriceRange(@Param("minPrice") PriceMasterEntity minPrice,
			@Param("maxPrice") PriceMasterEntity maxPrice, Pageable pageData);
}
