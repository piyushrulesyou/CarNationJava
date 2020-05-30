package com.carrentingservice.vehiclelisting.service.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.carrentingservice.vehiclelisting.controller.dto.VehicleInventoryDTO;
import com.carrentingservice.vehiclelisting.domain.VehicleInventoryEntity;

@Mapper(componentModel = "spring", uses = { CityMasterMapper.class, ColorMasterMapper.class,
		TenurePriceMasterMapper.class })
public interface VehicleInventoryMapper {

	public List<VehicleInventoryDTO> toVehicleInventoryDTO(List<VehicleInventoryEntity> vehicleInventoryEntity);

}
