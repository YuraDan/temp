package ru.sovzond.mgis2.documents.model.common;

import ru.sovzond.mgis2.documents.model.isogd.document.representation.RepresentationFormat;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 28/06/15.
 */
@Entity
@Table(name = "isogd_document_content")
public class DocumentContent implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_document_content_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private String fileName;

	@Column
	private byte[] bytes;

	@ManyToOne(optional = false)
	private RepresentationFormat representationFormat;

	@Column
	@Enumerated(EnumType.STRING)
	private StorageType storageType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public RepresentationFormat getRepresentationFormat() {
		return representationFormat;
	}

	public void setRepresentationFormat(RepresentationFormat representationFormat) {
		this.representationFormat = representationFormat;
	}

	public StorageType getStorageType() {
		return storageType;
	}

	public void setStorageType(StorageType storageType) {
		this.storageType = storageType;
	}

	public DocumentContent clone() {
		DocumentContent documentContent;
		try {
			documentContent = (DocumentContent) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		if(documentContent == null) return null;
		documentContent.setRepresentationFormat(getRepresentationFormat() != null ? getRepresentationFormat().clone() : null);
		return documentContent;
	}
}
