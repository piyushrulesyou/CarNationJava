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
@Table(name = "color_master")
public class ColorMasterEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "color_code")
	private String colorCode;

	@Column(name = "color_name")
	private String colorName;

	@Column(name = "color_image")
	private String colorImage;

	@ManyToMany(mappedBy = "colorMaster")
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
