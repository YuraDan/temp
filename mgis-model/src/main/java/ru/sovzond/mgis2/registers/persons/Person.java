package ru.sovzond.mgis2.registers.persons;

import ru.sovzond.mgis2.documents.model.isogd.document.IncludedDocuments;

import javax.persistence.*;

@Entity
@Table(name = "mgis2_person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_person_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@OneToOne
	private IncludedDocuments documents;

	public IncludedDocuments getDocuments() {
		return documents;
	}

	public void setDocuments(IncludedDocuments documents) {
		this.documents = documents;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@SuppressWarnings("CloneDoesntCallSuperClone")
	public Person clone() {
		Person person = new Person();
		person.setId(id);
		person.setDocuments(getDocuments() != null ? getDocuments().clone() : null);
		return person;
	}
}
