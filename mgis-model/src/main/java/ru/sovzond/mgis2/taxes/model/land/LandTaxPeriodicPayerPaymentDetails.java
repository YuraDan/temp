package ru.sovzond.mgis2.taxes.model.land;

import ru.sovzond.mgis2.taxes.model.common.TaxPeriodicPayerPaymentDetails;
import ru.sovzond.mgis2.taxes.model.land.privilege.LandTaxBasePrivilege;
import ru.sovzond.mgis2.taxes.model.land.privilege.LandTaxSumPrivilege;

import javax.persistence.*;

/**
 * Created by Sergey Lvov on 25.03.16.
 *
 * Land tax periodic payments by payer details
 */
@Entity
@Table(name = "mgis2_taxes_land_periodic_payer_payment_details")
public class LandTaxPeriodicPayerPaymentDetails extends TaxPeriodicPayerPaymentDetails implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_taxes_land_periodic_payer_payment_details_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private LandTaxBasePrivilege landTaxBasePrivilege;

	@ManyToOne(fetch = FetchType.LAZY)
	private LandTaxSumPrivilege landTaxSumPrivilege;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LandTaxBasePrivilege getLandTaxBasePrivilege() {
		return landTaxBasePrivilege;
	}

	public void setLandTaxBasePrivilege(LandTaxBasePrivilege landTaxBasePrivilege) {
		this.landTaxBasePrivilege = landTaxBasePrivilege;
	}

	public LandTaxSumPrivilege getLandTaxSumPrivilege() {
		return landTaxSumPrivilege;
	}

	public void setLandTaxSumPrivilege(LandTaxSumPrivilege landTaxSumPrivilege) {
		this.landTaxSumPrivilege = landTaxSumPrivilege;
	}

	public LandTaxPeriodicPayerPaymentDetails clone() {
		LandTaxPeriodicPayerPaymentDetails details = (LandTaxPeriodicPayerPaymentDetails) super.clone();
		if(details == null) return null;
		details.setLandTaxBasePrivilege(getLandTaxBasePrivilege() != null ? getLandTaxBasePrivilege().clone() : null);
		details.setLandTaxSumPrivilege(getLandTaxSumPrivilege() != null ? getLandTaxSumPrivilege().clone() : null);
		return details;
	}
}
