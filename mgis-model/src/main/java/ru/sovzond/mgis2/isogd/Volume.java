package ru.sovzond.mgis2.isogd;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Alexander Arakelyan
 *
 *
 */
@Entity
@Table(name = "isogd_volume")
public class Volume {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private String name;

	@ManyToOne
	private Section section;

	@OneToMany(mappedBy = "volume", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
	private List<Book> bools = new ArrayList<Book>();

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

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public List<Book> getBools() {
		return bools;
	}

	public void setBools(List<Book> bools) {
		this.bools = bools;
	}

}