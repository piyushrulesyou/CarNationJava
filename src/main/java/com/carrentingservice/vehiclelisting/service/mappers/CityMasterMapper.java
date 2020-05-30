package com.carrentingservice.vehiclelisting.service.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.carrentingservice.vehiclelisting.controller.dto.CityMasterDTO;
import com.carrentingservice.vehiclelisting.domain.CityMasterEntity;

@Mapper(componentModel = "spring")
public interface CityMasterMapper {

	public List<CityMasterDTO> toCityMasterDTO(List<CityMasterEntity> cityMasterEntity);

	@Mapping(target = "vehicleInventory", ignore = true)
	public CityMasterDTO toCityDTO(CityMasterEntity cityMasterEntity);
}
