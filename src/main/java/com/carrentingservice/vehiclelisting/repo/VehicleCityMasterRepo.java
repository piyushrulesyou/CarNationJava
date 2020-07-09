package com.carrentingservice.vehiclelisting.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carrentingservice.vehiclelisting.domain.CityMasterEntity;

@Repository
public interface VehicleCityMasterRepo extends JpaRepository<CityMasterEntity, String> {

	@Query("select city from CityMasterEntity city where city.cityCode in (:cityCode)")
	List<CityMasterEntity> findCityByCityCode(@Param("cityCode") List<String> cityCode);

}
