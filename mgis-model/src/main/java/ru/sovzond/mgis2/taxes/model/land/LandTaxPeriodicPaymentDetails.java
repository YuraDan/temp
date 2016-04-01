package ru.sovzond.mgis2.taxes.model.land;

import ru.sovzond.mgis2.taxes.model.common.TaxPeriodicPaymentDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey Lvov on 23.03.16.
 *
 * Land tax periodic payment details
 */
@Entity
@Table(name = "mgis2_taxes_land_periodic_payment_details")
public class LandTaxPeriodicPaymentDetails extends TaxPeriodicPaymentDetails implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_taxes_land_periodic_payment_details_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private LandTaxRate landTaxRate;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "mgis2_taxes_land_periodic_payment_details_by_payer")
	private List<LandTaxPeriodicPayerPaymentDetails> payerPaymentDetails = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LandTaxRate getLandTaxRate() {
		return landTaxRate;
	}

	public void setLandTaxRate(LandTaxRate landTaxRate) {
		this.landTaxRate = landTaxRate;
	}

	public List<LandTaxPeriodicPayerPaymentDetails> getPayerPaymentDetails() {
		return payerPaymentDetails;
	}

	public void setPayerPaymentDetails(List<LandTaxPeriodicPayerPaymentDetails> payerPaymentDetails) {
		this.payerPaymentDetails = payerPaymentDetails;
	}

	public LandTaxPeriodicPaymentDetails clone() {
		LandTaxPeriodicPaymentDetails details = (LandTaxPeriodicPaymentDetails) super.clone();
		if(details == null) return null;
		details.setLandTaxRate(getLandTaxRate() != null ? getLandTaxRate().clone() : null);
		details.setPayerPaymentDetails(getPayerPaymentDetails().stream().map(LandTaxPeriodicPayerPaymentDetails::clone).collect(Collectors.toList()));
		return details;
	}
}
