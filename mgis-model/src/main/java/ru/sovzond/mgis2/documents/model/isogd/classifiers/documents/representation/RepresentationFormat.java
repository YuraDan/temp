package ru.sovzond.mgis2.documents.model.isogd.classifiers.documents.representation;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * @author asd
 *         <p>
 *         11. Классификатор формы представления документов системы. Код классификатора: 2.B
 */
@Entity
@Table(name = "isogd_cls_document_repr_format")
public class RepresentationFormat implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_representation_format_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Код формата представления документа
	 */
	@NotNull
	@Column(unique = true)
	private String code;

	@Column
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private RepresentationForm representationForm;

	@ElementCollection
	@CollectionTable(name = "isogd_cls_document_repr_format_fmt",
			uniqueConstraints = @UniqueConstraint(columnNames = {"formats"})
	)
	@Column(name = "formats")
	private Set<String> formats = new HashSet<>();

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

	public RepresentationForm getRepresentationForm() {
		return representationForm;
	}

	public void setRepresentationForm(RepresentationForm representationForm) {
		this.representationForm = representationForm;
	}

	public Set<String> getFormats() {
		return formats;
	}

	public void setFormats(Set<String> formats) {
		this.formats = formats;
	}

	@SuppressWarnings("CloneDoesntCallSuperClone")
	public RepresentationFormat clone() {
		RepresentationFormat representationFormat = new RepresentationFormat();
		representationFormat.setId(getId());
		representationFormat.setCode(getCode());
		representationFormat.setName(getName());
		representationFormat.getFormats().addAll(getFormats());
		RepresentationForm representationForm = new RepresentationForm();
		representationForm.setId(representationForm.getId());
		representationFormat.setRepresentationForm(representationForm);
		return representationFormat;
	}


}
