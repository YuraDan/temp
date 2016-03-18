package ru.sovzond.mgis2.property.model.lands.works;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lands_land_works")
public class LandWorks {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "lands_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@OneToMany
	private List<LandWork> landWorks = new ArrayList<LandWork>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<LandWork> getLandWorks() {
		return landWorks;
	}

	public void setLandWorks(List<LandWork> landWorks) {
		this.landWorks = landWorks;
	}
}
