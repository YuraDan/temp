package ru.sovzond.mgis2.documents.model.common;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey Lvov on 09.03.16.
 *
 */
@Entity
@Table(name = "mgis2_docs_included_docs")
public class IncludedDocuments implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_docs_included_docs_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Document> documents = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public IncludedDocuments clone() {
		IncludedDocuments docs;
		try {
			docs = (IncludedDocuments) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		docs.setDocuments(getDocuments().stream().map(entry -> (Document) entry.clone()).collect(Collectors.toList()));
		return docs;
	}
}
