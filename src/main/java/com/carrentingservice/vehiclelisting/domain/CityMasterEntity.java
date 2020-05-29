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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "city_master")

public class CityMasterEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "city_code")
	private String cityCode;

	@Column(name = "city_name")
	private String cityName;

	@JsonIgnore
	@ManyToMany(mappedBy = "cityMaster")
	private List<VehicleInventoryEntity> vehicleInventory;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;

	public CityMasterEntity(String cityCode) {
		this.cityCode = cityCode;
	}
}
