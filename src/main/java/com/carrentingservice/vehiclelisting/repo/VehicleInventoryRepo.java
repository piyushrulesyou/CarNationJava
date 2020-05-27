package com.carrentingservice.vehiclelisting.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carrentingservice.vehiclelisting.domain.VehicleInventoryEntity;

public interface VehicleInventoryRepo extends JpaRepository<VehicleInventoryEntity, String> {

}
