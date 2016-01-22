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

}
