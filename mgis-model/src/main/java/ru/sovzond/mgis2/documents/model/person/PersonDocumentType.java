package ru.sovzond.mgis2.documents.model.person;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Sergey Lvov on 24.03.16.
 *
 * Person document type
 */
@Entity
@Table(name = "mgis2_docs_person_doctype")
public class PersonDocumentType {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_docs_person_doctype_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@NotNull
	@Column(unique = true)
	private String code;

	@NotNull
	@Column(unique = true)
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

	public PersonDocumentType clone() {
		PersonDocumentType type;
		try {
			type = (PersonDocumentType) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		return type;
	}
}
