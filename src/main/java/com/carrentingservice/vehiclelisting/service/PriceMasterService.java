package com.carrentingservice.vehiclelisting.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carrentingservice.vehiclelisting.controller.dto.PriceMasterDTO;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;

@Service
public interface PriceMasterService {

	public List<PriceMasterDTO> getAllPrices() throws RecordNotFoundException;

}
