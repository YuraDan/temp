package ru.sovzond.mgis2.documents.model.isogd.document;

import ru.sovzond.mgis2.common.classifiers.oktmo.Territory;
import ru.sovzond.mgis2.documents.model.common.Document;
import ru.sovzond.mgis2.documents.model.isogd.section.Volume;
import ru.sovzond.mgis2.registers.national_classifiers.OKTMO;

import javax.persistence.*;

/**
 * @author Alexander Arakelyan
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "mgis2_docs_isogd")
public class IsogdDocument extends Document implements Cloneable {

	@ManyToOne(optional = false)
	private Volume volume;

	@OneToOne(mappedBy = "document", cascade = CascadeType.REMOVE)
	private IsogdSpecialPart specialPart;

	@ManyToOne
	private IsogdDocumentSubObject documentSubObject;

	@ManyToOne(targetEntity = OKTMO.class)
	@JoinColumn(name = "oktmo_id")
	private Territory oktmo;

	public IsogdDocument() {
	}

	public IsogdDocument(Long id, String name) {
		setId(id);
		setName(name);
	}

	public Volume getVolume() {
		return volume;
	}

	public void setVolume(Volume volume) {
		this.volume = volume;
	}

	public Territory getOktmo() {
		return oktmo;
	}

	public void setOktmo(Territory oktmo) {
		this.oktmo = oktmo;
	}

	public IsogdDocumentSubObject getDocumentSubObject() {
		return documentSubObject;
	}

	public void setDocumentSubObject(IsogdDocumentSubObject documentSubObject) {
		this.documentSubObject = documentSubObject;
	}

	public IsogdSpecialPart getSpecialPart() {
		return specialPart;
	}

	public void setSpecialPart(IsogdSpecialPart specialPart) {
		this.specialPart = specialPart;
	}

	public Object clone() {
		IsogdDocument document;
		document = (IsogdDocument) super.clone();
		if(document == null) return null;
		document.setOktmo(getOktmo() != null ? getOktmo().clone() : null);
		document.setSpecialPart(getSpecialPart() != null ? getSpecialPart().clone() : null);
		document.setDocumentSubObject(getDocumentSubObject() != null ? getDocumentSubObject().clone() : null);
		document.setVolume(getVolume() != null ? getVolume().clone(): null);
		return document;
	}
}
