package com.carrentingservice.vehiclelisting.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrentingservice.vehiclelisting.domain.TenurePriceMasterEntity;

@Repository
public interface TenurePriceMasterRepo extends JpaRepository<TenurePriceMasterEntity, Long> {

}
