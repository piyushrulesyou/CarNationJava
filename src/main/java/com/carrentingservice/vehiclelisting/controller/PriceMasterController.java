package com.carrentingservice.vehiclelisting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrentingservice.vehiclelisting.controller.dto.PriceMasterDTO;
import com.carrentingservice.vehiclelisting.controller.dto.ResponseTO;
import com.carrentingservice.vehiclelisting.delegate.PriceMasterDelegate;
import com.carrentingservice.vehiclelisting.exceptions.RecordNotFoundException;

@RestController
@RequestMapping("/price")
public class PriceMasterController {

	@Autowired
	private PriceMasterDelegate priceMasterDelegate;

	@GetMapping(value = "/get-price-list")
	public ResponseEntity<ResponseTO<List<PriceMasterDTO>>> getAllPrices() throws RecordNotFoundException {
		ResponseTO<List<PriceMasterDTO>> responseTO = new ResponseTO<>();
		List<PriceMasterDTO> priceMasterResponseTO = priceMasterDelegate.getAllPrices();
		responseTO.setData(priceMasterResponseTO);
		return new ResponseEntity<>(responseTO, HttpStatus.OK);
	}
}
