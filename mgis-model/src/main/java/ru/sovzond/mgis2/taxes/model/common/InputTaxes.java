package ru.sovzond.mgis2.taxes.model.common;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by donchenko-y on 3/22/16.
 *
 */

@Entity
@Table(name = "mgis2_Taxes_Input")
public class InputTaxes implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "taxesIn_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Кадастровый номер ЗУ
	 */

	@NotNull
	@Column
	private String cadastralNumber;

	/**
	 * Площадь
	 */
	@Column
	private String area;

	/**
	 * Категория земель
	 */
	@Column
	private String landCategory;

	/**
	 * Вид разрешенного использования
	 */
	@Column
	private String allowedUsage;

	/**
	 * АДРЕС ЗУ
	 */
	@Column
	private String address;

	/**
	 * ОКАТО
	 */
	@Column
	private String codeOkato;

	/**
	 * КАДАСТРОВАЯ СТОИМОСТЬ
	 */
	@Column
	private Float cadastralCost;

	/**
	 * СТАВКА НАЛОГА
	 */
	@Column
	private Float taxRate;

	/**
	 * НАЛОГ К УПЛАТЕ
	 */
	@Column
	private Float taxPayment;

	/**
	 * ГОД
	 */
	@Column
	private Integer year;

	/**
	 * УПЛАЧЕН НАЛОГ
	 */
	@Column
	private Float taxPaid;

	/**
	 * Дата уплаты налога
	 */
	@Column
	private Date payDateTax;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCadastralNumber() {
		return cadastralNumber;
	}

	public void setCadastralNumber(String cadastralNumber) {
		this.cadastralNumber = cadastralNumber;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLandCategory() {
		return landCategory;
	}

	public void setLandCategory(String landCategory) {
		this.landCategory = landCategory;
	}

	public String getAllowedUsage() {
		return allowedUsage;
	}

	public void setAllowedUsage(String allowedUsage) {
		this.allowedUsage = allowedUsage;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCodeOkato() {
		return codeOkato;
	}

	public void setCodeOkato(String codeOkato) {
		this.codeOkato = codeOkato;
	}

	public Float getCadastralCost() {
		return cadastralCost;
	}

	public void setCadastralCost(Float cadastralCost) {
		this.cadastralCost = cadastralCost;
	}

	public Float getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Float taxRate) {
		this.taxRate = taxRate;
	}

	public Float getTaxPayment() {
		return taxPayment;
	}

	public void setTaxPayment(Float taxPayment) {
		this.taxPayment = taxPayment;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Float getTaxPaid() {
		return taxPaid;
	}

	public void setTaxPaid(Float taxPaid) {
		this.taxPaid = taxPaid;
	}

	public Date getPayDateTax() {
		return payDateTax;
	}

	public void setPayDateTax(Date payDateTax) {
		this.payDateTax = payDateTax;
	}

	@SuppressWarnings("CloneDoesntCallSuperClone")
	@Override
	public InputTaxes clone() {
		InputTaxes inputTaxes = new InputTaxes();
		inputTaxes.setId(id);
		inputTaxes.setCadastralNumber(cadastralNumber);
		inputTaxes.setArea(area);
		inputTaxes.setLandCategory(landCategory);
		inputTaxes.setAllowedUsage(allowedUsage);
		inputTaxes.setAddress(address);
		inputTaxes.setCodeOkato(codeOkato);
		inputTaxes.setCadastralCost(cadastralCost);
		inputTaxes.setTaxRate(taxRate);
		inputTaxes.setTaxPayment(taxPayment);
		inputTaxes.setTaxPaid(taxPaid);
		inputTaxes.setYear(year);
		inputTaxes.setPayDateTax(payDateTax);
		return inputTaxes;
	}
}
