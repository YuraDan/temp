package ru.sovzond.mgis2.taxes.model.land;

import ru.sovzond.mgis2.taxes.model.common.TaxPeriodicPaymentDetails;

import javax.persistence.*;

/**
 * Created by Sergey Lvov on 23.03.16.
 *
 * Land tax periodic payment details
 */
@Entity
@Table(name = "tax_land_periodic_payment_details")
public class LandTaxPeriodicPaymentDetails extends TaxPeriodicPaymentDetails {

	@ManyToOne(fetch = FetchType.LAZY)
	private LandTaxRate landTaxRate;

	@Column
	private double taxExemption;

	public LandTaxRate getLandTaxRate() {
		return landTaxRate;
	}

	public void setLandTaxRate(LandTaxRate landTaxRate) {
		this.landTaxRate = landTaxRate;
	}

	public double getTaxExemption() {
		return taxExemption;
	}

	public void setTaxExemption(double taxExemption) {
		this.taxExemption = taxExemption;
	}

	public LandTaxPeriodicPaymentDetails clone() {
		LandTaxPeriodicPaymentDetails details;
		details = (LandTaxPeriodicPaymentDetails) super.clone();
		details.setLandTaxRate(getLandTaxRate() != null ? getLandTaxRate().clone() : null);
		details.setTaxExemption(getTaxExemption());
		return details;
	}
}
