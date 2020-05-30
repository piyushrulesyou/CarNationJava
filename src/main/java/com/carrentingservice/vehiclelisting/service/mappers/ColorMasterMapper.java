package com.carrentingservice.vehiclelisting.service.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.carrentingservice.vehiclelisting.controller.dto.ColorMasterDTO;
import com.carrentingservice.vehiclelisting.domain.ColorMasterEntity;

@Mapper(componentModel = "spring")
public interface ColorMasterMapper {

	public List<ColorMasterDTO> toColorMasterDTO(List<ColorMasterEntity> colorMasterEntity);

	@Mapping(target = "vehicleInventory", ignore = true)
	public ColorMasterDTO toColorDTO(ColorMasterEntity colorMasterEntity);
}
