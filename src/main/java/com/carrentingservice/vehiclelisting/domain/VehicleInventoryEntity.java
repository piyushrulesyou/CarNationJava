package com.carrentingservice.vehiclelisting.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vehicle_inventory")
public class VehicleInventoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@ManyToOne
	@Column(name = "variant_fkid")
	private VariantTypeEntity variant;

	@ManyToOne
	@Column(name = "model_fkid")
	private ModelTypeEntity model;

	@ManyToOne
	@Column(name = "fuel_type_fkid")
	private FuelTypeEntity fuelType;

	@ManyToOne
	@Column(name = "transmission_type_fkid")
	private TransmissionTypeEntity transmissionType;

	@ManyToOne
	@Column(name = "car_type_fkid")
	private CarTypeEntity carType;

	@Column(name = "seats")
	private String seats;

	@ManyToOne
	@Column(name = "producer_fkid")
	private ProducerEntity producer;

	@Column(name = "sort_order")
	private long sortOrder;

	@Column(name = "full_size_image")
	private String fullSizeImage;

	@Column(name = "small_size_image")
	private String smallSizeImage;

	@Column(name = "is_popular")
	private boolean isPopular;

	@Column(name = "extra_km_charge")
	private long extraKmCharge;

	@Column(name = "insurance_cost")
	private long insuranceCost;

	@Column(name = "security_deposit")
	private long securityDeposit;

	@Column(name = "show_variant")
	private boolean showVariant;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;
}