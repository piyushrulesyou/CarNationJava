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

	public List<VehicleInventoryEntity> findByTransmissionType(TransmissionTypeEntity transmissionType);

	@Query("select veh from VehicleInventoryEntity veh where veh.transmissionType in (:transmissionType1, :transmissionType2)")
	public List<VehicleInventoryEntity> findByTransmissionType(
			@Param("transmissionType1") TransmissionTypeEntity transmissionType1,
			@Param("transmissionType2") TransmissionTypeEntity transmissionType2);

	public List<VehicleInventoryEntity> findByFuelType(FuelTypeEntity fuelTypeEntity);

	@Query("select veh from VehicleInventoryEntity veh where veh.fuelType in (:fuelTypeEntity1, :fuelTypeEntity2)")
	public List<VehicleInventoryEntity> findByFuelType(@Param("fuelTypeEntity1") FuelTypeEntity fuelTypeEntity1,
			@Param("fuelTypeEntity2") FuelTypeEntity fuelTypeEntity2);

	public List<VehicleInventoryEntity> findByCarType(CarTypeEntity carTypeEntity);

	@Query("select veh from VehicleInventoryEntity veh where veh.carType in (:carTypeEntity1, :carTypeEntity2)")
	public List<VehicleInventoryEntity> findByMultipleCarTypes(@Param("carTypeEntity1") CarTypeEntity carTypeEntity1,
			@Param("carTypeEntity2") CarTypeEntity carTypeEntity2);

	@Query("select veh from VehicleInventoryEntity veh where veh.carType in (:carTypeEntity1, :carTypeEntity2, :carTypeEntity3)")
	public List<VehicleInventoryEntity> findByMultipleCarTypes(@Param("carTypeEntity1") CarTypeEntity carTypeEntity1,
			@Param("carTypeEntity2") CarTypeEntity carTypeEntity2,
			@Param("carTypeEntity3") CarTypeEntity carTypeEntity3);

	@Query("select veh from VehicleInventoryEntity veh where veh.producer in (:producersList)")
	public List<VehicleInventoryEntity> findByProducer(@Param("producersList") List<ProducerTypeEntity> producersList);

	@Query("select veh from VehicleInventoryEntity veh where veh.id in (:vehicleIdList)")
	public List<VehicleInventoryEntity> findByIdList(@Param("vehicleIdList") List<String> vehicleIdList);

	@Query("select veh from VehicleInventoryEntity veh where veh.priceMaster BETWEEN :minPrice AND :maxPrice")
	public List<VehicleInventoryEntity> findByPriceRange(@Param("minPrice") PriceMasterEntity minPrice,
			@Param("maxPrice") PriceMasterEntity maxPrice);

	@Query("select veh from VehicleInventoryEntity veh where veh.id in (:vehicleIds)")
	public Page<VehicleInventoryEntity> findAllById(@Param("vehicleIds") List<String> vehicleIds, Pageable pageData);
}
