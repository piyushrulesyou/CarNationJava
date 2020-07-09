package com.carrentingservice.vehiclelisting.controller.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityMasterResponseDTO {

	private List<CityMasterDTO> cities;
	private List<CityMasterDTO> activeCities;
}
