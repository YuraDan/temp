package ru.sovzond.mgis2.registers.oks;

import ru.sovzond.mgis2.registers.oks.buildings.Building;
import ru.sovzond.mgis2.registers.oks.constructions.Construction;
import ru.sovzond.mgis2.registers.oks.flats.Flat;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
@Table(name = "rosreg_oks_realty")
public class Realty {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_oks_realty_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@ManyToOne
	private Building building;

	@ManyToOne
	private Construction construction;

	@ManyToOne
	private Uncompleted uncompleted;

	@ManyToOne
	private Flat flat;

}
