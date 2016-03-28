package ru.sovzond.mgis2.taxes.model.property;

import ru.sovzond.mgis2.taxes.model.common.TaxPeriodicPaymentDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey Lvov on 23.03.16.
 *
 * Property tax periodic payment details
 */
@Entity
@Table(name = "mgis2_taxes_property_periodic_payment_details")
public class PropertyTaxPeriodicPaymentDetails extends TaxPeriodicPaymentDetails implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_taxes_property_periodic_payment_details_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "mgis2_taxes_property_periodic_payment_details_by_payer")
	private List<PropertyTaxPeriodicPayerPaymentDetails> payerPaymentDetails = new ArrayList<>();

	@Column
	private double propertyTaxRate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<PropertyTaxPeriodicPayerPaymentDetails> getPayerPaymentDetails() {
		return payerPaymentDetails;
	}

	public void setPayerPaymentDetails(List<PropertyTaxPeriodicPayerPaymentDetails> payerPaymentDetails) {
		this.payerPaymentDetails = payerPaymentDetails;
	}

	public double getPropertyTaxRate() {
		return propertyTaxRate;
	}

	public void setPropertyTaxRate(double propertyTaxRate) {
		this.propertyTaxRate = propertyTaxRate;
	}

	public PropertyTaxPeriodicPaymentDetails clone() {
		PropertyTaxPeriodicPaymentDetails details = (PropertyTaxPeriodicPaymentDetails) super.clone();
		if(details == null) return null;
		details.setPayerPaymentDetails(getPayerPaymentDetails().stream().map(PropertyTaxPeriodicPayerPaymentDetails::clone).collect(Collectors.toList()));
		return details;
	}

}
