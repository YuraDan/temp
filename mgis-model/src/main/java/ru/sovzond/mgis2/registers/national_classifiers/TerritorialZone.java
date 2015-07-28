package ru.sovzond.mgis2.registers.national_classifiers;

import javax.persistence.*;

@Entity
@Table(name = "nc_territorial_zone")
public class TerritorialZone {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "nc_territorial_zone_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(unique = true, nullable = false)
	private String code;

	@Column
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}