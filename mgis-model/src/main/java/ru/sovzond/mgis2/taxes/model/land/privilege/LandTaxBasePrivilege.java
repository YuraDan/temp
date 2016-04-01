package ru.sovzond.mgis2.taxes.model.land.privilege;

import ru.sovzond.mgis2.documents.model.property.OtherPropertyDocument;

import javax.persistence.*;

/**
 * Created by Sergey Lvov on 25.03.16.
 *
 * Land tax base privilege
 */
@Entity
@Table(name = "mgis2_taxes_land_privilege_base")
public class LandTaxBasePrivilege implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_taxes_land_privilege_base_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	@Enumerated(EnumType.STRING)
	private LandTaxBasePrivilegeType landTaxBasePrivilegeType; // вид льготы

	@Column
	@Enumerated(EnumType.STRING)
	private LandTaxBasePrivilegeReductionType landTaxBasePrivilegeReductionType; // вид уменьшения налоговой базы

	@Column
	private String article391PrivilegeCode; // код льготы статья 391

	@ManyToOne(fetch = FetchType.LAZY)
	private LandTaxBasePrivilegeArticle395Codes article395Code; // код льготы статья 395

	@ManyToOne(fetch = FetchType.LAZY)
	private OtherPropertyDocument normativeAct; // нормативный акт

	@Column
	private double taxPrivilegeSumArticle391; // сумма льготы по статье 391

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LandTaxBasePrivilegeType getLandTaxBasePrivilegeType() {
		return landTaxBasePrivilegeType;
	}

	public void setLandTaxBasePrivilegeType(LandTaxBasePrivilegeType landTaxBasePrivilegeType) {
		this.landTaxBasePrivilegeType = landTaxBasePrivilegeType;
	}

	public LandTaxBasePrivilegeReductionType getLandTaxBasePrivilegeReductionType() {
		return landTaxBasePrivilegeReductionType;
	}

	public void setLandTaxBasePrivilegeReductionType(LandTaxBasePrivilegeReductionType landTaxBasePrivilegeReductionType) {
		this.landTaxBasePrivilegeReductionType = landTaxBasePrivilegeReductionType;
	}

	public String getArticle391PrivilegeCode() {
		return article391PrivilegeCode;
	}

	public void setArticle391PrivilegeCode(String article391PrivilegeCode) {
		this.article391PrivilegeCode = article391PrivilegeCode;
	}

	public LandTaxBasePrivilegeArticle395Codes getArticle395Code() {
		return article395Code;
	}

	public void setArticle395Code(LandTaxBasePrivilegeArticle395Codes article395Code) {
		this.article395Code = article395Code;
	}

	public OtherPropertyDocument getNormativeAct() {
		return normativeAct;
	}

	public void setNormativeAct(OtherPropertyDocument normativeAct) {
		this.normativeAct = normativeAct;
	}

	public double getTaxPrivilegeSumArticle391() {
		return taxPrivilegeSumArticle391;
	}

	public void setTaxPrivilegeSumArticle391(double taxPrivilegeSumArticle391) {
		this.taxPrivilegeSumArticle391 = taxPrivilegeSumArticle391;
	}

	public LandTaxBasePrivilege clone() {
		LandTaxBasePrivilege privilege;
		try {
			privilege = (LandTaxBasePrivilege) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		privilege.setArticle395Code(getArticle395Code() != null ? getArticle395Code().clone() : null);
		privilege.setNormativeAct(getNormativeAct() != null ? getNormativeAct().clone() : null);
		return privilege;
	}

}
