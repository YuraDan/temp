package ru.sovzond.mgis2.property.model.rights;

import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.registers.national_classifiers.LandRightKind;
import ru.sovzond.mgis2.registers.national_classifiers.OKFS;
import ru.sovzond.mgis2.registers.persons.Person;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey Lvov on 21.03.16.
 *
 * Subject right
 */
@Entity
@Table(name = "mgis2_subject_right")
public class SubjectRight implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "subject_right_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@ManyToOne
	private LandRightKind rightKind;

	@ManyToOne
	private Person rightOwner;

	@ManyToOne
	private OKFS ownershipForm;

	@Column
	private Date ownershipDate;

	@Column
	private Date terminationDate;

	@Column
	private int shareNumerator;

	@Column
	private int shareDenominator;

	@ManyToMany
	@JoinTable(name = "mgis2_property_rights_reg_docs", joinColumns = @JoinColumn(name = "mgis2_property_rights_id"), inverseJoinColumns = @JoinColumn(name = "registration_doc_id"))
	private List<Document> registrationDocuments = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "mgis2_property_rights_cert_docs", joinColumns = @JoinColumn(name = "mgis2_property_rights_id"), inverseJoinColumns = @JoinColumn(name = "cert_doc_id"))
	private List<Document> documentsCertifyingRights = new ArrayList<>();

	@Column
	private float totalArea;

	@Column
	private float annualTax;

	@Column
	private boolean obligations;

	@Column
	private String comment;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LandRightKind getRightKind() {
		return rightKind;
	}

	public void setRightKind(LandRightKind rightKind) {
		this.rightKind = rightKind;
	}

	public Person getRightOwner() {
		return rightOwner;
	}

	public void setRightOwner(Person rightOwner) {
		this.rightOwner = rightOwner;
	}

	public OKFS getOwnershipForm() {
		return ownershipForm;
	}

	public void setOwnershipForm(OKFS ownershipForm) {
		this.ownershipForm = ownershipForm;
	}

	public Date getOwnershipDate() {
		return ownershipDate;
	}

	public void setOwnershipDate(Date ownershipDate) {
		this.ownershipDate = ownershipDate;
	}

	public Date getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	public int getShareNumerator() {
		return shareNumerator;
	}

	public void setShareNumerator(int shareNumerator) {
		this.shareNumerator = shareNumerator;
	}

	public int getShareDenominator() {
		return shareDenominator;
	}

	public void setShareDenominator(int shareDenominator) {
		this.shareDenominator = shareDenominator;
	}

	public List<Document> getRegistrationDocuments() {
		return registrationDocuments;
	}

	public void setRegistrationDocuments(List<Document> registrationDocuments) {
		this.registrationDocuments = registrationDocuments;
	}

	public List<Document> getDocumentsCertifyingRights() {
		return documentsCertifyingRights;
	}

	public void setDocumentsCertifyingRights(List<Document> documentsCertifyingRights) {
		this.documentsCertifyingRights = documentsCertifyingRights;
	}

	public float getTotalArea() {
		return totalArea;
	}

	public void setTotalArea(float totalArea) {
		this.totalArea = totalArea;
	}

	public float getAnnualTax() {
		return annualTax;
	}

	public void setAnnualTax(float annualTax) {
		this.annualTax = annualTax;
	}

	public boolean isObligations() {
		return obligations;
	}

	public void setObligations(boolean obligations) {
		this.obligations = obligations;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@SuppressWarnings("CloneDoesntCallSuperClone")
	public SubjectRight clone() {
		SubjectRight rights = new SubjectRight();
		rights.setId(id);
		rights.setOwnershipDate(ownershipDate);
		rights.setTerminationDate(terminationDate);
		rights.setRightOwner(rightOwner != null ? rightOwner.clone() : null);
		rights.setRightKind(rightKind != null ? rightKind.clone() : null);
		rights.setOwnershipForm(ownershipForm != null ? ownershipForm.clone() : null);
		rights.setShareNumerator(getShareNumerator());
		rights.setShareDenominator(getShareDenominator());
		rights.setRegistrationDocuments(getRegistrationDocuments() != null ? getRegistrationDocuments().stream().map(document -> new Document(document.getId(), document.getName())).collect(Collectors.toList()) : null);
		rights.setDocumentsCertifyingRights(getDocumentsCertifyingRights() != null ? getDocumentsCertifyingRights().stream().map(document -> new Document(document.getId(), document.getName())).collect(Collectors.toList()) : null);
		rights.setComment(comment);
		rights.setObligations(obligations);
		rights.setAnnualTax(annualTax);
		rights.setTotalArea(totalArea);
		return rights;
	}
}
