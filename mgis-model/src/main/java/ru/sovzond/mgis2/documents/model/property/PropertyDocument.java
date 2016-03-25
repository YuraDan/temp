package ru.sovzond.mgis2.documents.model.property;

import ru.sovzond.mgis2.documents.model.common.Document;
import ru.sovzond.mgis2.persons.model.LegalPerson;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sergey Lvov on 24.03.16.
 *
 * Documents for property objects
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "mgis2_docs_property")
public class PropertyDocument extends Document implements Cloneable {

	@Column
	private Date internalDate;

	@Column
	private String internalNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	private LegalPerson issuingOrganization;

	@Column
	private String comment;

	public Date getInternalDate() {
		return internalDate;
	}

	public void setInternalDate(Date internalDate) {
		this.internalDate = internalDate;
	}

	public String getInternalNumber() {
		return internalNumber;
	}

	public void setInternalNumber(String internalNumber) {
		this.internalNumber = internalNumber;
	}

	public LegalPerson getIssuingOrganization() {
		return issuingOrganization;
	}

	public void setIssuingOrganization(LegalPerson issuingOrganization) {
		this.issuingOrganization = issuingOrganization;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Object clone() {
		PropertyDocument document;
		document = (PropertyDocument) super.clone();
		if(document == null) return null;
		document.setInternalDate(getInternalDate());
		document.setInternalNumber(getInternalNumber());
		document.setIssuingOrganization(getIssuingOrganization() != null ? getIssuingOrganization().clone() : null);
		document.setComment(getComment());
		return document;
	}
}
