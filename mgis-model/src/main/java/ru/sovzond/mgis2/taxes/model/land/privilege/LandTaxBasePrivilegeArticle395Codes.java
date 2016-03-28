package ru.sovzond.mgis2.taxes.model.land.privilege;

import javax.persistence.*;

/**
 * Created by Sergey Lvov on 25.03.16.
 *
 * Land tax base privilege codes for article 395
 */
@Entity
@Table(name = "mgis2_taxes_land_privilege_base_codes")
public class LandTaxBasePrivilegeArticle395Codes implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_taxes_land_privilege_base_codes_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private String code;

	@Column
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	private LandTaxBasePrivilegeBasis privilegeBasis; // основание льготы

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LandTaxBasePrivilegeBasis getPrivilegeBasis() {
		return privilegeBasis;
	}

	public void setPrivilegeBasis(LandTaxBasePrivilegeBasis privilegeBasis) {
		this.privilegeBasis = privilegeBasis;
	}

	public LandTaxBasePrivilegeArticle395Codes clone() {
		LandTaxBasePrivilegeArticle395Codes articleCode;
		try {
			articleCode = (LandTaxBasePrivilegeArticle395Codes) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		articleCode.setPrivilegeBasis(getPrivilegeBasis() != null ? getPrivilegeBasis().clone() : null);
		return articleCode;
	}
}
