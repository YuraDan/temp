package ru.sovzond.mgis2.registers.oks.coord_system;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Arakelyan
 *
 *         Элемент контура
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "rosreg_oks_coord_spatial_element")
public class SpatialElementOKS {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_oks_coord_spatial_element_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Часть элемента (точка, окружность)
	 */
	@ElementCollection
	private List<SpelementUnitOKS> spelementUnit = new ArrayList<>();

	/**
	 * Номер контура
	 */
	@Column
	private Integer number;
}
