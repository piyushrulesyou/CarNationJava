package com.carrentingservice.vehiclelisting.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tenure_price_master")
public class TenurePriceMasterEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "tenure_duration")
	private String tenureDuration;

	@Column(name = "price_multiplier")
	private double priceMultiplier;

	@ManyToMany(mappedBy = "tenureMaster")
	@JsonIgnore
	private List<VehicleInventoryEntity> vehicleInventory;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;
}
