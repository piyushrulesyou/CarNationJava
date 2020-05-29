package com.carrentingservice.vehiclelisting.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carrentingservice.vehiclelisting.domain.relationship.InventoryCityId;
import com.carrentingservice.vehiclelisting.domain.relationship.InventoryCityMasterEntity;

public interface InventoryCityMasterRepo extends JpaRepository<InventoryCityMasterEntity, InventoryCityId> {

}