package ru.sovzond.mgis2.property.model.rights;

import ru.sovzond.mgis2.documents.model.property.CertifyingDocument;
import ru.sovzond.mgis2.documents.model.property.ConstitutiveDocument;
import ru.sovzond.mgis2.persons.model.Person;
import ru.sovzond.mgis2.registers.national_classifiers.LandRightKind;
import ru.sovzond.mgis2.registers.national_classifiers.OKFS;

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
@Table(name = "mgis2_property_subject_right")
public class SubjectRight implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_property_subject_right_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@ManyToOne
	private LandRightKind rightKind;

	@Column
	private Date ownershipDate;

	@Column
	private Date terminationDate;

	@Column
	private int shareDenominator;

	@Column
	private String registrationNumber;

	@ManyToMany
	@JoinTable(name = "mgis2_property_rights_cert_docs", joinColumns = @JoinColumn(name = "mgis2_property_rights_id"), inverseJoinColumns = @JoinColumn(name = "cert_doc_id"))
	private List<CertifyingDocument> documentsCertifyingRights = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "mgis2_property_rights_reg_docs", joinColumns = @JoinColumn(name = "mgis2_property_rights_id"), inverseJoinColumns = @JoinColumn(name = "registration_doc_id"))
	private List<ConstitutiveDocument> registrationDocuments = new ArrayList<>();

	@ManyToOne
	private Person rightOwner;

	@ManyToOne
	private OKFS ownershipForm;

	@Column
	private int shareNumerator;

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

	public List<ConstitutiveDocument> getRegistrationDocuments() {
		return registrationDocuments;
	}

	public void setRegistrationDocuments(List<ConstitutiveDocument> registrationDocuments) {
		this.registrationDocuments = registrationDocuments;
	}

	public List<CertifyingDocument> getDocumentsCertifyingRights() {
		return documentsCertifyingRights;
	}

	public void setDocumentsCertifyingRights(List<CertifyingDocument> documentsCertifyingRights) {
		this.documentsCertifyingRights = documentsCertifyingRights;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public SubjectRight clone() {
		SubjectRight right;
		try {
			right = (SubjectRight) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		right.setRightOwner(getRightOwner() != null ? getRightOwner().clone() : null);
		right.setRightKind(getRightKind() != null ? getRightKind().clone() : null);
		right.setOwnershipForm(getOwnershipForm() != null ? getOwnershipForm().clone() : null);
		right.setRegistrationDocuments(getRegistrationDocuments() != null ? getRegistrationDocuments().stream().map(ConstitutiveDocument::clone).collect(Collectors.toList()) : null);
		right.setDocumentsCertifyingRights(getDocumentsCertifyingRights() != null ? getDocumentsCertifyingRights().stream().map(CertifyingDocument::clone).collect(Collectors.toList()) : null);
		return right;
	}
}
