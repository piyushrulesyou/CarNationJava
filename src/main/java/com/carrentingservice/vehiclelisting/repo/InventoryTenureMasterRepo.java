package com.carrentingservice.vehiclelisting.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carrentingservice.vehiclelisting.domain.relationship.InventoryTenureId;
import com.carrentingservice.vehiclelisting.domain.relationship.InventoryTenureMasterEntity;

public interface InventoryTenureMasterRepo extends JpaRepository<InventoryTenureMasterEntity, InventoryTenureId> {

}