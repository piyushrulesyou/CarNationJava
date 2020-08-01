package com.carrentingservice.vehiclelisting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrentingservice.vehiclelisting.constants.ErrorConstants;
import com.carrentingservice.vehiclelisting.controller.dto.PriceMasterDTO;
import com.carrentingservice.vehiclelisting.domain.PriceMasterEntity;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;
import com.carrentingservice.vehiclelisting.repo.PriceMasterRepo;
import com.carrentingservice.vehiclelisting.service.PriceMasterService;
import com.carrentingservice.vehiclelisting.service.mappers.PriceMasterMapper;

@Service
public class PriceMasterServiceImpl implements PriceMasterService {

	@Autowired
	private PriceMasterRepo priceMasterRepo;

	@Autowired
	private PriceMasterMapper priceMasterMapper;

	@Override
	public List<PriceMasterDTO> getAllPrices() throws RecordNotFoundException {
		List<PriceMasterEntity> priceEntityList = priceMasterRepo.findAll();
		if (priceEntityList.isEmpty())
			throw new RecordNotFoundException("Error occured in method " + " getAllPrices() " + " of class "
					+ this.getClass().getName() + ". Exception code is " + ErrorConstants.PRICES_NOT_FOUND_ERROR_CODE
					+ " and exception message is " + ErrorConstants.PRICES_NOT_FOUND_ERROR + ".");
		return priceMasterMapper.toPriceMasterDTO(priceEntityList);
	}
}
