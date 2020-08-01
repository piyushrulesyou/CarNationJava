package com.carrentingservice.vehiclelisting.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carrentingservice.vehiclelisting.domain.ColorMasterEntity;

public interface ColorMasterRepo extends JpaRepository<ColorMasterEntity, String> {

}
