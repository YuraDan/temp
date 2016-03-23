package ru.sovzond.mgis2.registers.oks.coord_system;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Alexander Arakelyan
 *
 *         Часть элемента (точка, окружность)
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "rosreg_oks_coord_spelement_unit")
public class SpelementUnitOKS {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_oks_coord_spelement_unit_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Координата
	 */
	@ManyToOne
	private Ordinate ordinate;

	/**
	 * Радиус
	 */
	@Column
	private BigDecimal r;

	/**
	 * Элементарный тип для части элемента
	 */
	@Column
	private TypeUnit typeUnit;

	/**
	 * Номер части элемента (порядок обхода)
	 */
	@Column
	private BigInteger suNumb;
}
