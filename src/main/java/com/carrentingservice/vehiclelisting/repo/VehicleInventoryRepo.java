package com.carrentingservice.vehiclelisting.repo;

import java.util.List;

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

	public List<VehicleInventoryEntity> findByTransmissionType(TransmissionTypeEntity transmissionType);

	public List<VehicleInventoryEntity> findByFuelType(FuelTypeEntity fuelTypeEntity);

	public List<VehicleInventoryEntity> findByCarType(CarTypeEntity carTypeEntity);

	@Query("select veh from VehicleInventoryEntity veh where veh.producer in (:producersList)")
	public List<VehicleInventoryEntity> findByProducer(@Param("producersList") List<ProducerTypeEntity> producersList);

	@Query("select veh from VehicleInventoryEntity veh where veh.id in (:vehicleIdList)")
	public List<VehicleInventoryEntity> findByIdList(List<String> vehicleIdList);

	@Query("select veh from VehicleInventoryEntity veh where veh.priceMaster BETWEEN :minPrice AND :maxPrice")
	public List<VehicleInventoryEntity> findByPriceRange(@Param("minPrice") PriceMasterEntity minPrice,
			@Param("maxPrice") PriceMasterEntity maxPrice);

}
