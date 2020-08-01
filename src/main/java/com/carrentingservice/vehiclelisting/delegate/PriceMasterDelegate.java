package com.carrentingservice.vehiclelisting.delegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carrentingservice.vehiclelisting.controller.dto.PriceMasterDTO;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;
import com.carrentingservice.vehiclelisting.service.PriceMasterService;

@Component
public class PriceMasterDelegate {

	@Autowired
	private PriceMasterService priceMasterService;

	public List<PriceMasterDTO> getAllPrices() throws RecordNotFoundException {
		return priceMasterService.getAllPrices();
	}

}
