package ru.sovzond.mgis2.registers.national_classifiers;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 11.09.15.
 */
@Entity
@Table(name = "nc_okved", indexes = {@Index(columnList = "code", name = "nc_okved_code_index")})
public class OKVED implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "nc_okved_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private String section;

	@Column(unique = true)
	private String code;

	@Column(length = 1000)
	private String name;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
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
	public OKVED clone() {
		OKVED okved = new OKVED();
		okved.setId(id);
		okved.setCode(code);
		okved.setName(name);
		okved.setSection(section);
		return okved;
	}
}
