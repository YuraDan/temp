package ru.sovzond.mgis2.property.model.lands.rights;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "lands_rights")
public class LandRights implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "lands_rights_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<LandRight> rights = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<LandRight> getRights() {
		return rights;
	}

	public void setRights(List<LandRight> rights) {
		this.rights = rights;
	}

	@SuppressWarnings("CloneDoesntCallSuperClone")
	public LandRights clone() {
		LandRights rights = new LandRights();
		rights.setId(getId());
		rights.setRights(getRights() != null ? getRights().stream().map(LandRight::clone).collect(Collectors.toList()) : null);
		return rights;
	}

}
