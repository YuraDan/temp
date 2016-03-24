package ru.sovzond.mgis2.taxes.model.land;

import ru.sovzond.mgis2.registers.national_classifiers.LandCategory;

import javax.persistence.*;

/**
 * Created by Sergey Lvov on 23.03.16.
 *
 * Land tax rate
 */
@Entity
@Table(name = "tax_land_rate")
public class LandTaxRate {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "tax_land_rate_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private LandCategory landCategory;

	@Column
	private double taxRate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LandCategory getLandCategory() {
		return landCategory;
	}

	public void setLandCategory(LandCategory landCategory) {
		this.landCategory = landCategory;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	public LandTaxRate clone() {
		LandTaxRate landTaxRate;
		try {
			landTaxRate = (LandTaxRate) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		landTaxRate.setId(getId());
		landTaxRate.setTaxRate(getTaxRate());
		landTaxRate.setLandCategory(getLandCategory() != null ? getLandCategory().clone() : null);
		return landTaxRate;
	}
}
