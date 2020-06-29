package com.carrentingservice.vehiclelisting.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrentingservice.vehiclelisting.domain.CityMasterEntity;
import com.carrentingservice.vehiclelisting.domain.relationship.InventoryCityId;
import com.carrentingservice.vehiclelisting.domain.relationship.InventoryCityMasterEntity;

@Repository
public interface InventoryCityMasterRepo extends JpaRepository<InventoryCityMasterEntity, InventoryCityId> {

	Page<InventoryCityMasterEntity> findByCityMasterCityCode(CityMasterEntity city, Pageable pageData);

}