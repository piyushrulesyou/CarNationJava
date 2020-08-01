package com.carrentingservice.vehiclelisting.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carrentingservice.vehiclelisting.domain.PriceMasterEntity;

public interface PriceMasterRepo extends JpaRepository<PriceMasterEntity, Long> {

}
