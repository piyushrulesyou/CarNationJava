package com.carrentingservice.vehiclelisting.service.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.carrentingservice.vehiclelisting.controller.dto.ProducerTypeDTO;
import com.carrentingservice.vehiclelisting.domain.ProducerTypeEntity;

@Mapper(componentModel = "spring")
public interface VehicleProducerMapper {

	public List<ProducerTypeDTO> toProducerTypeDTO(List<ProducerTypeEntity> producerTypeEntity);

}
