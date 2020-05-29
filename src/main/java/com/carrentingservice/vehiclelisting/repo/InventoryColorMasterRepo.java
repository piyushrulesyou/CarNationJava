package com.carrentingservice.vehiclelisting.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carrentingservice.vehiclelisting.domain.relationship.InventoryColorId;
import com.carrentingservice.vehiclelisting.domain.relationship.InventoryColorMasterEntity;

public interface InventoryColorMasterRepo extends JpaRepository<InventoryColorMasterEntity, InventoryColorId> {

}