package ru.sovzond.mgis2.registers.national_classifiers;

/**
 * Created by donchenko-y on 2/4/16.
 */

import javax.persistence.*;


@Entity
@Table(name = "nc_objectsPurpose")
public class ObjectsPurpose implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "nc_objectsPurpose_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(nullable = false)
	private String code; //code

	@Column(length = 100)
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

	@SuppressWarnings("CloneDoesntCallSuperClone")
	public ObjectsPurpose clone() {
		ObjectsPurpose objectsPurpose = new ObjectsPurpose();
		objectsPurpose.setCode(code);
		objectsPurpose.setName(name);
		return objectsPurpose;
	}
}
