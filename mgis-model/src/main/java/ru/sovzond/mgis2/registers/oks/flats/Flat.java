package ru.sovzond.mgis2.registers.oks.flats;

import ru.sovzond.mgis2.registers.oks.OKSAddress;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Arakelyan
 *
 *         Помещение
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "rosreg_oks_flat")
public class Flat {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_oks_flat_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@ElementCollection
	private List<String> cadastralBlocks = new ArrayList<>();

	@Column
	private String objectType;

	@ManyToOne
	private AssignationFlat assignation;

	@Column
	private BigDecimal area;

	@ManyToOne
	private OKSAddress address;

	@Column
	private String cadastralNumber;

}
