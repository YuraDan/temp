package ru.sovzond.mgis2.property.model.oks.rights;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "oks_rights")
public class ConstructionRights implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "oks_rights_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<ConstructionRight> rights = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ConstructionRight> getRights() {
		return rights;
	}

	public void setRights(List<ConstructionRight> rights) {
		this.rights = rights;
	}

	@SuppressWarnings("CloneDoesntCallSuperClone")
	public ConstructionRights clone() {
		ConstructionRights rights = new ConstructionRights();
		rights.setId(getId());
		rights.setRights(getRights() != null ? getRights().stream().map(ConstructionRight::clone).collect(Collectors.toList()) : null);
		return rights;
	}

}
