package ru.sovzond.mgis2.taxes.model.land.privilege;

import javax.persistence.*;

/**
 * Created by Sergey Lvov on 25.03.16.
 *
 * Land tax sum privilege
 */
@Entity
@Table(name = "mgis2_taxes_land_privilege_sum")
public class LandTaxSumPrivilege implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_taxes_land_privilege_sum_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private double taxPrivilegePercent;

	@Column
	private double taxPrivilegeSum;

	@Column
	@Enumerated(EnumType.STRING)
	private LandTaxSumPrivilegeType privilegeType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getTaxPrivilegePercent() {
		return taxPrivilegePercent;
	}

	public void setTaxPrivilegePercent(double taxPrivilegePercent) {
		this.taxPrivilegePercent = taxPrivilegePercent;
	}

	public double getTaxPrivilegeSum() {
		return taxPrivilegeSum;
	}

	public void setTaxPrivilegeSum(double taxPrivilegeSum) {
		this.taxPrivilegeSum = taxPrivilegeSum;
	}

	public LandTaxSumPrivilegeType getPrivilegeType() {
		return privilegeType;
	}

	public void setPrivilegeType(LandTaxSumPrivilegeType privilegeType) {
		this.privilegeType = privilegeType;
	}

	public LandTaxSumPrivilege clone() {
		try {
			return (LandTaxSumPrivilege) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
