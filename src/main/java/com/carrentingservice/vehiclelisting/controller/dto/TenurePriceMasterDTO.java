package com.carrentingservice.vehiclelisting.controller.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenurePriceMasterDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long tenureId;

	private double months3;

	private double months6;

	private double months12;

	private double months18;

	private double months24;

	private double months36;

	private String createdBy;

	private Date createdDate;

	private String modifiedBy;

	private Date modifiedDate;
}
