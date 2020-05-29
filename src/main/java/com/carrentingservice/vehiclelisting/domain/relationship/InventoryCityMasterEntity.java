package com.carrentingservice.vehiclelisting.domain.relationship;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.carrentingservice.vehiclelisting.domain.CityMasterEntity;
import com.carrentingservice.vehiclelisting.domain.VehicleInventoryEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicle_inventory_city_master")
@IdClass(InventoryCityId.class)
public class InventoryCityMasterEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "vehicle_inventory_id", referencedColumnName = "id")
	private VehicleInventoryEntity vehicleInventoryId;

	@Id
	@ManyToOne
	@JoinColumn(name = "city_master_city_code", referencedColumnName = "city_code")
	private CityMasterEntity cityMasterCityCode;
}