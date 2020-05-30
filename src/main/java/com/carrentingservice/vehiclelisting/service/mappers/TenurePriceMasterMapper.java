package com.carrentingservice.vehiclelisting.service.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.carrentingservice.vehiclelisting.controller.dto.TenurePriceMasterDTO;
import com.carrentingservice.vehiclelisting.domain.TenurePriceMasterEntity;

@Mapper(componentModel = "spring")
public interface TenurePriceMasterMapper {

	public List<TenurePriceMasterDTO> toTenurePriceMasterDTO(List<TenurePriceMasterEntity> colorMasterEntity);

	@Mapping(target = "vehicleInventory", ignore = true)
	public TenurePriceMasterDTO toTenurePriceDTO(TenurePriceMasterEntity tenurePriceMasterEntity);
}
