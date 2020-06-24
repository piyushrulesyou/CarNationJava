package com.carrentingservice.vehiclelisting.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrentingservice.vehiclelisting.domain.relationship.InventoryColorId;
import com.carrentingservice.vehiclelisting.domain.relationship.InventoryColorMasterEntity;

@Repository
public interface InventoryColorMasterRepo extends JpaRepository<InventoryColorMasterEntity, InventoryColorId> {

}