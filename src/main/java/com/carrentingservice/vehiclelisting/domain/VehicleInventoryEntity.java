package com.carrentingservice.vehiclelisting.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
	@JoinColumn(name = "variant_fkid", referencedColumnName = "variant_code")
	private VariantTypeEntity variant;

	@Column(name = "model")
	private String model;

	@ManyToOne
	@JoinColumn(name = "fuel_type_fkid", referencedColumnName = "fuel_code")
	private FuelTypeEntity fuelType;

	@ManyToOne
	@JoinColumn(name = "transmission_type_fkid", referencedColumnName = "transmission_type_code")
	private TransmissionTypeEntity transmissionType;

	@ManyToMany
//	@JoinTable(name = "vehicle_color_manytomany", joinColumns = @JoinColumn(name = "vehicle_inventory_fkid", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "color_master_fkid", referencedColumnName = "color_code"))
	private List<ColorMasterEntity> colorMaster;

	@ManyToOne
	@JoinColumn(name = "car_type_fkid", referencedColumnName = "car_type_code")
	private CarTypeEntity carType;

	@Column(name = "seats")
	private String seats;

	@ManyToOne
	@JoinColumn(name = "producer_fkid", referencedColumnName = "producer_code")
	private ProducerTypeEntity producer;

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

	@ManyToOne
	@JoinColumn(name = "price_fkid", referencedColumnName = "base_price")
	private PriceMasterEntity priceMaster;

	@Column(name = "insurance_cost")
	private long insuranceCost;

	@ManyToMany
	private List<CityMasterEntity> cityMaster;

	@Column(name = "security_deposit")
	private long securityDeposit;

	@Column(name = "show_variant")
	private boolean showVariant;

	@ManyToMany
	private List<TenurePriceMasterEntity> tenureMaster;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;

	public VehicleInventoryEntity(String id) {
		this.id = id;
	}
}