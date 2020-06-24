package com.carrentingservice.vehiclelisting.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tenure_price_master")
public class TenurePriceMasterEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tenure_id")
	private Long tenureId;

	@Column(name = "months3")
	private double months3;

	@Column(name = "months6")
	private double months6;

	@Column(name = "months12")
	private double months12;

	@Column(name = "months18")
	private double months18;

	@Column(name = "months24")
	private double months24;

	@Column(name = "months36")
	private double months36;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;
}
