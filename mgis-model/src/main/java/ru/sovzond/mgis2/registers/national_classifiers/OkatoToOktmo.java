package ru.sovzond.mgis2.registers.national_classifiers;

/**
 * Created by donchenko-y on 1/20/16.
 */

import javax.persistence.*;

/**
 * Соответсвие окато к октмо
 */
@Entity
@Table(name = "nc_okato_oktmo", indexes = {@Index(columnList = "name", name = "nc_okato_oktmo_name_index")})
public class OkatoToOktmo implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "nc_okato_oktmo_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private String name;

	@Column(unique = true)
	private String okato;

	@Column(unique = true)
	private String oktmo;

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

	public String getOkato() {
		return okato;
	}

	public void setOkato(String okato) {
		this.okato = okato;
	}

	public String getOktmo() {
		return oktmo;
	}

	public void setOktmo(String oktmo) {
		this.oktmo = oktmo;
	}

	public OkatoToOktmo clone() {
		OkatoToOktmo okatoToOktmo = new OkatoToOktmo();
		okatoToOktmo.setId(id);
		okatoToOktmo.setName(name);
		okatoToOktmo.setOkato(okato);
		okatoToOktmo.setOktmo(oktmo);
		return okatoToOktmo;
	}
}
