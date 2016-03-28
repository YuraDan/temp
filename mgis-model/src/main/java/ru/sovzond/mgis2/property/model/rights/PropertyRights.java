package ru.sovzond.mgis2.property.model.rights;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 06.11.15.
 *
 */
@Entity
@Table(name = "mgis2_property_rights")
public class PropertyRights implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "property_rights_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<SubjectRight> rights = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<SubjectRight> getRights() {
		return rights;
	}

	public void setRights(List<SubjectRight> rights) {
		this.rights = rights;
	}

	public PropertyRights clone() {
		PropertyRights rights;
		try {
			rights = (PropertyRights) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		rights.setRights(getRights() != null ? getRights().stream().map(SubjectRight::clone).collect(Collectors.toList()) : null);
		return rights;
	}
}
