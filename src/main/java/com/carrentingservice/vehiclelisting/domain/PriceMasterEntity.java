package com.carrentingservice.vehiclelisting.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "price_master")
public class PriceMasterEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "base_price")
	private long basePrice;

	@Column(name = "discount_absolute")
	private long discountAbsolute;

	@Column(name = "discount_percentage")
	private double discountPercentage;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;

	public PriceMasterEntity(long basePrice) {
		this.basePrice = basePrice;
	}
}