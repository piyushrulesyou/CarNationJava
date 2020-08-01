package com.carrentingservice.vehiclelisting.service;

import java.util.List;

import com.carrentingservice.vehiclelisting.controller.dto.ColorMasterDTO;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;

public interface ColorMasterService {

	List<ColorMasterDTO> findAllColors() throws RecordNotFoundException;

}