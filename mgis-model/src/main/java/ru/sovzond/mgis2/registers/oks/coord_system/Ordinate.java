package ru.sovzond.mgis2.registers.oks.coord_system;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Alexander Arakelyan
 *
 *         Координата
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "rosreg_oks_coord_ordinate")
public class Ordinate {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_oks_coord_ordinate_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Примечание
	 */
	@Column
	private String note;

	/**
	 * Координата X
	 */
	@Column
	private BigDecimal x;

	/**
	 * Координата Y
	 */
	@Column
	private BigDecimal y;

	/**
	 * Номер точки (порядок обхода)
	 */
	@Column
	private BigInteger ordNmb;

	/**
	 * Номер точки (межевой точки)
	 */
	@Column
	private BigInteger numGeopoint;

	/**
	 * Средняя квадратическая погрешность положения характерной точки
	 */
	@Column
	private BigDecimal deltaGeopoint;

}
