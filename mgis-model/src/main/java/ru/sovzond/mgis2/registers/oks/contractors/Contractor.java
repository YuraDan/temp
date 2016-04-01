package ru.sovzond.mgis2.registers.oks.contractors;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Alexander Arakelyan
 *
 *         сведения о кадастровых инженерах
 *
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "rosreg_oks_uncompleted")
public class Contractor {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_oks_uncompleted_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Кадастровый инженер
	 */
	@ManyToOne
	private Engineer engineer;

	/**
	 * Дата проведения кадастровых работ
	 */
	@Column
	private Date date;

}
