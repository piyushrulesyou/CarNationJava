package com.carrentingservice.vehiclelisting.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrentingservice.vehiclelisting.domain.relationship.InventoryCityId;
import com.carrentingservice.vehiclelisting.domain.relationship.InventoryCityMasterEntity;

@Repository
public interface InventoryCityMasterRepo extends JpaRepository<InventoryCityMasterEntity, InventoryCityId> {

}