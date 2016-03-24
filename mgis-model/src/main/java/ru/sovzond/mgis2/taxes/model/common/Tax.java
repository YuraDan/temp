package ru.sovzond.mgis2.taxes.model.common;

import javax.persistence.*;

/**
 * Created by Sergey Lvov on 23.03.16.
 *
 * Taxes
 */
@MappedSuperclass
public class Tax {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "taxes_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Object clone() {
		Tax tax;
		try {
			tax = (Tax) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		tax.setId(getId());
		return tax;
	}
}
