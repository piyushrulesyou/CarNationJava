package com.carrentingservice.vehiclelisting.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "producer_company")
public class ProducerTypeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "producer_code")
	private String producerCode;

	@Column(name = "producer_name")
	private String producerName;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;
}
