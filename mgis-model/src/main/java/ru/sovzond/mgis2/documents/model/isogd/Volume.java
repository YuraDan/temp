package ru.sovzond.mgis2.documents.model.isogd;

import ru.sovzond.mgis2.Sortable;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocument;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexander Arakelyan
 */
@Entity
@Table(name = "isogd_volume", indexes = {@Index(name = "isogd_volume_sortorder_ix", columnList = "book_id, sort_order")})
public class Volume implements Cloneable, Sortable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_volume_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private String name;

	@ManyToOne(optional = false)
	@JoinColumn(name = "book_id")
	private Book book;

	@Column(name = "sort_order")
	private Long sortOrder;

	@OneToMany(mappedBy = "volume", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	private List<IsogdDocument> documents = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public List<IsogdDocument> getDocuments() {
		return documents;
	}

	public void setDocuments(List<IsogdDocument> documents) {
		this.documents = documents;
	}

	@SuppressWarnings("CloneDoesntCallSuperClone")
	public Volume clone() {
		Volume volume = new Volume();
		volume.setId(id);
		volume.setName(name);
		volume.setBook(book.clone());
		volume.setSortOrder(sortOrder);
		volume.setDocuments(documents.stream().map(document -> {
			IsogdDocument document2 = new IsogdDocument();
			document2.setId(document.getId());
			document2.setDocNumber(document.getDocNumber());
			document2.setDocDate(document.getDocDate());
			document2.setName(document.getName());
			return document2;
		}).collect(Collectors.toList()));
		return volume;
	}

}
