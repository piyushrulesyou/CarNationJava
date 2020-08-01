package com.carrentingservice.vehiclelisting.service.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.carrentingservice.vehiclelisting.controller.dto.PriceMasterDTO;
import com.carrentingservice.vehiclelisting.domain.PriceMasterEntity;

@Mapper(componentModel = "spring")
public interface PriceMasterMapper {

	List<PriceMasterDTO> toPriceMasterDTO(List<PriceMasterEntity> priceMasterEntity);

}
