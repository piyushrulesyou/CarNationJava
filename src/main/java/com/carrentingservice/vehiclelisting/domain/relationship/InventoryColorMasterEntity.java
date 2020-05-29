package com.carrentingservice.vehiclelisting.domain.relationship;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.carrentingservice.vehiclelisting.domain.ColorMasterEntity;
import com.carrentingservice.vehiclelisting.domain.VehicleInventoryEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "vehicle_inventory_color_master")
@IdClass(InventoryColorId.class)
public class InventoryColorMasterEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "vehicle_inventory_id", referencedColumnName = "id")
	private VehicleInventoryEntity vehicleInventoryId;

	@Id
	@ManyToOne
	@JoinColumn(name = "color_master_color_code", referencedColumnName = "color_code")
	private ColorMasterEntity colorMasterColorCode;
}