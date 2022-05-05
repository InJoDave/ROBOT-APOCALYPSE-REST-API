package com.codechallenge.apocalypse.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Survivors {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column
	@NotNull(message = "{NotNull.Survivors.name}")
	private String name;

	@Column
	@NotNull(message = "{NotNull.Survivors.age}")
	private Long age;

	@Column
	@NotNull(message = "{NotNull.Survivors.gender}")
	private String gender;

	@Column
	private Boolean infected;

	@Column
	private Long totalInfection;

	// Unidirectional
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	private Location location;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "resources_id", referencedColumnName = "id")
	private Resources resources;

}
