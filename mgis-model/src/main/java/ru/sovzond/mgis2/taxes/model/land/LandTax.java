package ru.sovzond.mgis2.taxes.model.land;

import ru.sovzond.mgis2.taxes.model.common.Tax;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey Lvov on 25.03.16.
 *
 * Land tax
 */
@Entity
@Table(name = "mgis2_taxes_land")
public class LandTax extends Tax implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_taxes_land_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "mgis2_taxes_land_by_payment_details")
	private List<LandTaxPeriodicPaymentDetails> landTaxPeriodicPaymentDetailsList = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<LandTaxPeriodicPaymentDetails> getLandTaxPeriodicPaymentDetailsList() {
		return landTaxPeriodicPaymentDetailsList;
	}

	public void setLandTaxPeriodicPaymentDetailsList(List<LandTaxPeriodicPaymentDetails> landTaxPeriodicPaymentDetailsList) {
		this.landTaxPeriodicPaymentDetailsList = landTaxPeriodicPaymentDetailsList;
	}

	public LandTax clone() {
		LandTax landTax = (LandTax) super.clone();
		if(landTax == null) return null;
		landTax.setLandTaxPeriodicPaymentDetailsList(getLandTaxPeriodicPaymentDetailsList().stream()
				.map(LandTaxPeriodicPaymentDetails::clone).collect(Collectors.toList()));
		return landTax;
	}
}
