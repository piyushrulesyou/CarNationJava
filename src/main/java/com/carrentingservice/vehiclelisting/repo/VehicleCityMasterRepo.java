package com.carrentingservice.vehiclelisting.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrentingservice.vehiclelisting.domain.CityMasterEntity;

@Repository
public interface VehicleCityMasterRepo extends JpaRepository<CityMasterEntity, String> {

}
