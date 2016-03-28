package ru.sovzond.mgis2.documents.model.isogd.document;

import javax.persistence.*;

/**
 * @author asd
 *         <p/>
 *         10. Классификатор документов, размещаемых в ИС ОГД. Код классификатора: 2.A
 *         <p/>
 *         Подобъект
 */
@Entity
@Table(name = "isogd_cls_document_sub_obj")
public class IsogdDocumentSubObject {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_cls_document_sub_obj_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(unique = true, nullable = false)
	private String code;

	/**
	 * Наименование документа
	 */
	@Column(nullable = false)
	private String name;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private IsogdDocumentObject documentObject;

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

	public IsogdDocumentObject getDocumentObject() {
		return documentObject;
	}

	public void setDocumentObject(IsogdDocumentObject documentObject) {
		this.documentObject = documentObject;
	}

	@SuppressWarnings("CloneDoesntCallSuperClone")
	public IsogdDocumentSubObject clone() {
		IsogdDocumentSubObject documentSubObject = new IsogdDocumentSubObject();
		documentSubObject.setId(id);
		documentSubObject.setCode(code);
		documentSubObject.setName(name);
		IsogdDocumentObject documentObject = new IsogdDocumentObject();
		documentObject.setId(this.documentObject.getId());
		documentObject.setCode(this.getDocumentObject().getCode());
		documentSubObject.setDocumentObject(documentObject);
		return documentSubObject;
	}

}
