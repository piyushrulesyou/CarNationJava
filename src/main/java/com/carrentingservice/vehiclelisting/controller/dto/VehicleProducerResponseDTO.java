package com.carrentingservice.vehiclelisting.controller.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleProducerResponseDTO {

	private List<ProducerTypeDTO> producers;
}
