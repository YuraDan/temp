package ru.sovzond.mgis2.documents.model.common;

import ru.sovzond.mgis2.documents.model.isogd.document.parts.CommonPart;
import ru.sovzond.mgis2.persons.model.Person;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sergey Lvov on 24.03.16.
 *
 * Superclass Document
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "mgis2_docs")
public class Document implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_docs_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private String docNumber;

	@Column
	private Date docDate;

	@Column
	private String name;

	@ManyToOne(targetEntity = Person.class)
	@JoinColumn(name = "author_id")
	private Person author;

	@ManyToOne(targetEntity = Person.class)
	@JoinColumn(name = "applicant_id")
	private Person applicant;

	@OneToOne(mappedBy = "document", cascade = CascadeType.REMOVE)
	private CommonPart commonPart;

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

	public String getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	public Person getApplicant() {
		return applicant;
	}

	public void setApplicant(Person applicant) {
		this.applicant = applicant;
	}

	public CommonPart getCommonPart() {
		return commonPart;
	}

	public void setCommonPart(CommonPart commonPart) {
		this.commonPart = commonPart;
	}

	public Object clone() {
		Document document;
		try {
			document = (Document) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		document.setAuthor(getAuthor() != null ? getAuthor().clone() : null);
		document.setApplicant(getApplicant() != null ? getApplicant().clone() : null);
		document.setCommonPart(getCommonPart() != null ? getCommonPart().clone() : null);
		return document;
	}

}
