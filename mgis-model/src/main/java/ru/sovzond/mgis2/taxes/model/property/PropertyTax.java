package ru.sovzond.mgis2.taxes.model.property;

import ru.sovzond.mgis2.taxes.model.common.Tax;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey Lvov on 25.03.16.
 *
 * Property tax
 */
@Entity
@Table(name = "mgis2_taxes_property")
public class PropertyTax extends Tax implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_taxes_property_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "mgis2_taxes_property_by_payment_details")
	List<PropertyTaxPeriodicPaymentDetails> propertyTaxPeriodicPaymentDetailsList = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<PropertyTaxPeriodicPaymentDetails> getPropertyTaxPeriodicPaymentDetailsList() {
		return propertyTaxPeriodicPaymentDetailsList;
	}

	public void setPropertyTaxPeriodicPaymentDetailsList(List<PropertyTaxPeriodicPaymentDetails> propertyTaxPeriodicPaymentDetailsList) {
		this.propertyTaxPeriodicPaymentDetailsList = propertyTaxPeriodicPaymentDetailsList;
	}

	public PropertyTax clone() {
		PropertyTax propertyTax = (PropertyTax) super.clone();
		if(propertyTax == null) return null;
		propertyTax.setPropertyTaxPeriodicPaymentDetailsList(getPropertyTaxPeriodicPaymentDetailsList().stream()
			.map(PropertyTaxPeriodicPaymentDetails::clone).collect(Collectors.toList()));
		return propertyTax;
	}

}
