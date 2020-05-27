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
@Table(name = "model_type")
public class ModelTypeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "model_code")
	private String modelCode;

	@Column(name = "model_name")
	private String modelName;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;
}
