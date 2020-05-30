package com.carrentingservice.vehiclelisting.controller.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProducerTypeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String producerCode;

	private String producerName;

	private String createdBy;

	private Date createdDate;

	private String modifiedBy;

	private Date modifiedDate;
}
