package ru.sovzond.mgis2.authentication.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mgis2_user")
public class User {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column(unique = true, nullable = false)
	private String username;

	@Column()
	private String password;

	@Column
	private boolean active;

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "mgis2_user_privilege", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "privilege_id"))
	private List<Privilege> privileges = new ArrayList<Privilege>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Privilege> getPrivileges() {
		// return Collections.unmodifiableList(privileges);
		return privileges;

	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	// public void addPrivilege(Privilege privilege) {
	// if (!privileges.contains(privilege)) {
	// privileges.add(privilege);
	// privilege.addUser(this);
	// }
	// }

	// public void removePrivilege(Privilege privilege) {
	// if (privileges.contains(privilege)) {
	// privileges.remove(privilege);
	// privilege.removeUser(this);
	// }
	// }

	// public boolean containsPrivilege(Privilege privilege) {
	// return privileges.contains(privilege);
	// }
}