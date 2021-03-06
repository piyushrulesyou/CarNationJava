package com.carrentingservice.vehiclelisting.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.carrentingservice.vehiclelisting.domain.CityMasterEntity;
import com.carrentingservice.vehiclelisting.domain.relationship.InventoryCityId;
import com.carrentingservice.vehiclelisting.domain.relationship.InventoryCityMasterEntity;

@Repository
public interface InventoryCityMasterRepo extends JpaRepository<InventoryCityMasterEntity, InventoryCityId> {

	List<InventoryCityMasterEntity> findByCityMasterCityCode(CityMasterEntity city);

	@Query(value = "select distinct veh.city_master_city_code FROM vehicle_inventory_city_master veh", nativeQuery = true)
	List<String> findAllDistinctCityCodes();

}