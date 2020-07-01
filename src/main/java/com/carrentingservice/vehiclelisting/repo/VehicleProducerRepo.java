package com.carrentingservice.vehiclelisting.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrentingservice.vehiclelisting.domain.ProducerTypeEntity;

@Repository
public interface VehicleProducerRepo extends JpaRepository<ProducerTypeEntity, String> {

}
