package com.carrentingservice.vehiclelisting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrentingservice.vehiclelisting.constants.ErrorConstants;
import com.carrentingservice.vehiclelisting.controller.dto.ColorMasterDTO;
import com.carrentingservice.vehiclelisting.domain.ColorMasterEntity;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;
import com.carrentingservice.vehiclelisting.repo.ColorMasterRepo;
import com.carrentingservice.vehiclelisting.service.ColorMasterService;
import com.carrentingservice.vehiclelisting.service.mappers.ColorMasterMapper;

@Service
public class ColorMasterServiceImpl implements ColorMasterService {

	@Autowired
	private ColorMasterMapper colorMasterMapper;

	@Autowired
	private ColorMasterRepo colorMasterRepo;

	@Override
	public List<ColorMasterDTO> findAllColors() throws RecordNotFoundException {
		List<ColorMasterEntity> colorMasterEntity = colorMasterRepo.findAll();
		if (colorMasterEntity.isEmpty())
			throw new RecordNotFoundException("Error occured in method " + " findAllColors() " + " of class "
					+ this.getClass().getName() + ". Exception code is " + ErrorConstants.COLORS_NOT_FOUND_ERROR_CODE
					+ " and exception message is " + ErrorConstants.COLOR_NOT_FOUND_ERROR + ".");
		return colorMasterMapper.toColorMasterDTO(colorMasterEntity);
	}

}
