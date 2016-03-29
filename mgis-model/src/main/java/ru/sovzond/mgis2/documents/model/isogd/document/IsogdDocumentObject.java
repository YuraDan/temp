package ru.sovzond.mgis2.documents.model.isogd.document;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author asd
 *         <p/>
 *         * 10. Классификатор документов, размещаемых в ИС ОГД. Код классификатора: 2.A
 *         <p/>
 *         Объект **
 */
@Entity
@Table(name = "isogd_cls_document_obj")
public class IsogdDocumentObject implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_cls_document_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * 1.01.01
	 */
	@Column(unique = true, nullable = false)
	private String code;

	@Column(nullable = false)
	private String name;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private IsogdDocumentClass documentClass;

	@OneToMany(mappedBy = "documentObject", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	@OrderBy("code")
	private List<IsogdDocumentSubObject> documentSubObjects = new ArrayList<>();

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

	public IsogdDocumentClass getDocumentClass() {
		return documentClass;
	}

	public void setDocumentClass(IsogdDocumentClass documentClass) {
		this.documentClass = documentClass;
	}

	public List<IsogdDocumentSubObject> getDocumentSubObjects() {
		return documentSubObjects;
	}

	public void setDocumentSubObjects(List<IsogdDocumentSubObject> documentSubObjects) {
		this.documentSubObjects = documentSubObjects;
	}

	public IsogdDocumentObject clone() {
		IsogdDocumentObject documentObject;
		try {
			documentObject = (IsogdDocumentObject) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		if(documentObject == null) return null;
		if(getDocumentClass() != null) {
			IsogdDocumentClass newDocumentClass = new IsogdDocumentClass();
			newDocumentClass.setId(getDocumentClass().getId());
			newDocumentClass.setCode(getDocumentClass().getCode());
			documentObject.setDocumentClass(getDocumentClass() != null ? newDocumentClass : null);
		}
		documentObject.setDocumentSubObjects(getDocumentSubObjects().stream().map(IsogdDocumentSubObject::clone).collect(Collectors.toList()));
		return documentObject;
	}

}
