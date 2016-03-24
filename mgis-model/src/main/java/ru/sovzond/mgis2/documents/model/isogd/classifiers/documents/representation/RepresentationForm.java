package ru.sovzond.mgis2.documents.model.isogd.classifiers.documents.representation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author asd
 *         <p/>
 *         11. Классификатор формы представления документов системы. Код классификатора: 2.B
 */
@Entity
@Table(name = "isogd_cls_document_repr_form")
public class RepresentationForm implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_representation_form_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Код формы представления документа
	 */
	@Column(unique = true, nullable = false)
	private String code;

	@Column
	private String name;

	@OneToMany(mappedBy = "representationForm", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	@OrderBy("code")
	private List<RepresentationFormat> representationFormats = new ArrayList<>();

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

	public List<RepresentationFormat> getRepresentationFormats() {
		return representationFormats;
	}

	public void setRepresentationFormats(List<RepresentationFormat> representationFormats) {
		this.representationFormats = representationFormats;
	}

	@SuppressWarnings("CloneDoesntCallSuperClone")
	public RepresentationForm clone() {
		RepresentationForm representationForm = new RepresentationForm();
		representationForm.setId(id);
		representationForm.setCode(code);
		representationForm.setName(name);
		representationFormats.stream().forEach(representationFormat -> representationForm.getRepresentationFormats().add((RepresentationFormat) representationFormat.clone()));
		return representationForm;
	}

}
