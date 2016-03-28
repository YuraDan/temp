package ru.sovzond.mgis2.documents.model.isogd.document;

import ru.sovzond.mgis2.documents.model.common.DocumentContent;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "isogd_document_special_part")
public class IsogdSpecialPart implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_document_special_part_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	private IsogdDocument document;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<DocumentContent> documentContents = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public IsogdDocument getDocument() {
		return document;
	}

	public void setDocument(IsogdDocument document) {
		this.document = document;
	}

	public List<DocumentContent> getDocumentContents() {
		return documentContents;
	}

	public void setDocumentContents(List<DocumentContent> documentContents) {
		this.documentContents = documentContents;
	}

	@SuppressWarnings("CloneDoesntCallSuperClone")
	public IsogdSpecialPart clone() {
		IsogdSpecialPart part = new IsogdSpecialPart();
		part.setId(id);
		IsogdDocument document = new IsogdDocument();
		document.setId(this.document.getId());
		part.setDocument(document);
		part.setDocumentContents(documentContents.stream().map(documentContent -> {
			DocumentContent documentContent1 = new DocumentContent();
			documentContent1.setId(documentContent.getId());
			documentContent1.setFileName(documentContent.getFileName());
			//documentContent1.setBytes(documentContent.getBytes());
			documentContent1.setRepresentationFormat(documentContent.getRepresentationFormat() != null ? documentContent.getRepresentationFormat().clone() : null);
			return documentContent1;
		}).collect(Collectors.toList()));

		return part;
	}

}
