package com.carrentingservice.vehiclelisting.controller.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponseTO {

	private List<VehicleInventoryDTO> listVehicleDTO;
	private long totalPages;
	private long totalEnteries;
}
