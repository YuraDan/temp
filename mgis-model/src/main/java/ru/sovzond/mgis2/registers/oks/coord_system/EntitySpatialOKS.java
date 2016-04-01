package ru.sovzond.mgis2.registers.oks.coord_system;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Arakelyan
 *
 *         Описание местоположения границ
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "rosreg_oks_coord_entity_spatial")
public class EntitySpatialOKS {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_oks_coord_entity_spatial_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Элемент контура
	 */
	@ElementCollection
	private List<SpatialElementOKS> spatialElement = new ArrayList<SpatialElementOKS>();

	/**
	 * Ссылка на систему координат
	 */
	@ManyToOne
	private CoordSystem entSys;
}
