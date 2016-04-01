package ru.sovzond.mgis2.registers.oks;

import javax.persistence.*;
import java.util.Date;

@SuppressWarnings("unused")
@Entity
@Table(name = "rosreg_oks_certification_doc")
public class CertificationDoc {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_oks_certification_doc_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Наименование органа кадастрового учета
	 */
	@Column
	private String organization;

	/**
	 * Дата
	 */
	@Column
	private Date date;

	/**
	 * Номер документа
	 */
	@Column
	private String number;

}
