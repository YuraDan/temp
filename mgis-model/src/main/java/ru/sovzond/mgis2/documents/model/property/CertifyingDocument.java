package ru.sovzond.mgis2.documents.model.property;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Sergey Lvov on 24.03.16.
 *
 * Certifying documents - правоудостоверяющие документы
 */
@Entity
@Table(name = "mgis2_docs_property_certifying")
public class CertifyingDocument extends PropertyDocument implements Cloneable {

	@Column
	private Date submissionDate;

	@Column
	private Date registrationDate;

	@Column
	private String registrationNumber;

	@Column
	private String registrar;

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getRegistrar() {
		return registrar;
	}

	public void setRegistrar(String registrar) {
		this.registrar = registrar;
	}

	public CertifyingDocument clone() {
		CertifyingDocument document;
		document = (CertifyingDocument) super.clone();
		if(document == null) return null;
		document.setSubmissionDate(getSubmissionDate());
		document.setRegistrationDate(getRegistrationDate());
		document.setRegistrationNumber(getRegistrationNumber());
		document.setRegistrar(getRegistrar());
		return document;
	}
}
