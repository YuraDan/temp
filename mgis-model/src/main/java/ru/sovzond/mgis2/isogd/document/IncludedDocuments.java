package ru.sovzond.mgis2.isogd.document;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey Lvov on 09.03.16.
 *
 */
@Entity
@Table(name = "included_documents")
public class IncludedDocuments implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "included_documents_seq", allocationSize = 1)
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

	@SuppressWarnings("CloneDoesntCallSuperClone")
	public IncludedDocuments clone() {
		IncludedDocuments docs = new IncludedDocuments();
		docs.setId(this.getId());
		docs.getDocuments().addAll(getDocuments().stream().map(Document::clone).collect(Collectors.toList()));
		return docs;
	}
}
