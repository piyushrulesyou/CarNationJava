package com.carrentingservice.vehiclelisting.domain.relationship;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.carrentingservice.vehiclelisting.domain.TenurePriceMasterEntity;
import com.carrentingservice.vehiclelisting.domain.VehicleInventoryEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicle_inventory_tenure_master")
@IdClass(InventoryTenureId.class)
public class InventoryTenureMasterEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "vehicle_inventory_id", referencedColumnName = "id")
	private VehicleInventoryEntity vehicleInventoryId;

	@Id
	@ManyToOne
	@JoinColumn(name = "tenure_master_tenure_duration", referencedColumnName = "tenure_duration")
	private TenurePriceMasterEntity tenureMasterTenureDuration;
}