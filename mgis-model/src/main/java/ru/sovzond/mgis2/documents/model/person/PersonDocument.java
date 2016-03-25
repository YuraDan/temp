package ru.sovzond.mgis2.documents.model.person;

import ru.sovzond.mgis2.documents.model.common.Document;
import ru.sovzond.mgis2.persons.model.PersonType;

import javax.persistence.*;

/**
 * Created by Sergey Lvov on 24.03.16.
 *
 * Documents for person
 */
@Entity
@Table(name = "mgis2_docs_person")
public class PersonDocument extends Document implements Cloneable {

	@Column
	@Enumerated(EnumType.STRING)
	private PersonType personType;

	@ManyToOne(fetch = FetchType.LAZY)
	private PersonDocumentType personDocumentType;

	public PersonType getPersonType() {
		return personType;
	}

	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}

	public PersonDocumentType getPersonDocumentType() {
		return personDocumentType;
	}

	public void setPersonDocumentType(PersonDocumentType personDocumentType) {
		this.personDocumentType = personDocumentType;
	}

	public PersonDocument clone() {
		PersonDocument document = (PersonDocument) super.clone();
		if(document == null) return null;
		document.setPersonType(getPersonType());
		document.setPersonDocumentType(getPersonDocumentType() != null ? getPersonDocumentType().clone() : null);
		return document;
	}
}
