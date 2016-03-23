package ru.sovzond.mgis2.authentication.model;

import javax.persistence.*;

@Entity
@Table(name = "mgis2_privilege")
public class Privilege implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_user_privilege_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(unique = true, nullable = false, updatable = false)
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings("CloneDoesntCallSuperClone")
	@Override
	public Privilege clone() {
		Privilege privilege = new Privilege();
		privilege.setId(id);
		privilege.setName(name);
		return privilege;
	}
}
