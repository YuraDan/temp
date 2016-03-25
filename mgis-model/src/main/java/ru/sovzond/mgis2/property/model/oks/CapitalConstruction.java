package ru.sovzond.mgis2.property.model.oks;

import ru.sovzond.mgis2.property.model.common.Property;
import ru.sovzond.mgis2.property.model.oks.characteristics.ConstructionCharacteristics;
import ru.sovzond.mgis2.property.model.oks.constructive_elements.ConstructiveElement;
import ru.sovzond.mgis2.registers.national_classifiers.OKTMO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 05.11.15.
 *
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "occ_capital_construction", indexes = @Index(name = "oks_cadastral_number_ind", unique = true, columnList = "cadastralnumber"))
public class CapitalConstruction extends Property implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_occ_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "type_id")
	private ConstructionType type;

	@Column
	private String objectPurpose;

	/**
	 * Условный номер
	 */
	@Column
	private String conditionalNumber;

	/**
	 * Инвентарный номер
	 */
	@Column
	private String inventoryNumber;

	/**
	 * Общая площадь
	 */
	@Column
	private Double overallArea;

	/**
	 * Муниципальное образование
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "municipal_entity_id")
	private OKTMO municipalEntity;

	/**
	 * Местоположение
	 */
	@Column
	private String placement;

	/**
	 * Литера
	 */
	@Column
	private String letter;

	/**
	 * Дата постановки на технический учёт
	 */
	@Column
	private Date technicalAccountingStatementDate;

	/**
	 * Фактическое использование
	 */
	@Column
	private String actualUsage;

	/**
	 * Год ввода объекта в эксплуатацию
	 */
	@Column
	private Integer operationStartYear;

	/**
	 * Года завершения строительства
	 */
	@Column
	private Integer buildCompletionYear;

	/**
	 * Дата последней реконструкции
	 */
	@Column
	private Date lastReconstructionDate;

	/**
	 * Год последнего кап.ремонта
	 */
	@Column
	private Integer rebuildingLastYear;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private ConstructionCharacteristics characteristics;

	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
	private List<ConstructiveElement> constructiveElements = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ConstructionType getType() {
		return type;
	}

	public void setType(ConstructionType type) {
		this.type = type;
	}

	@SuppressWarnings("unused")
	public String getObjectPurpose() {
		return objectPurpose;
	}

	public void setObjectPurpose(String objectPurpose) {
		this.objectPurpose = objectPurpose;
	}

	@SuppressWarnings("unused")
	public String getConditionalNumber() {
		return conditionalNumber;
	}

	public void setConditionalNumber(String conditionalNumber) {
		this.conditionalNumber = conditionalNumber;
	}

	@SuppressWarnings("unused")
	public String getInventoryNumber() {
		return inventoryNumber;
	}

	public void setInventoryNumber(String inventoryNumber) {
		this.inventoryNumber = inventoryNumber;
	}

	@SuppressWarnings("unused")
	public Double getOverallArea() {
		return overallArea;
	}

	public void setOverallArea(Double overallArea) {
		this.overallArea = overallArea;
	}

	public OKTMO getMunicipalEntity() {
		return municipalEntity;
	}

	public void setMunicipalEntity(OKTMO municipalEntity) {
		this.municipalEntity = municipalEntity;
	}

	public String getPlacement() {
		return placement;
	}

	public void setPlacement(String placement) {
		this.placement = placement;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	@SuppressWarnings("unused")
	public Date getTechnicalAccountingStatementDate() {
		return technicalAccountingStatementDate;
	}

	public void setTechnicalAccountingStatementDate(Date technicalAccountingStatementDate) {
		this.technicalAccountingStatementDate = technicalAccountingStatementDate;
	}

	@SuppressWarnings("unused")
	public String getActualUsage() {
		return actualUsage;
	}

	public void setActualUsage(String actualUsage) {
		this.actualUsage = actualUsage;
	}

	@SuppressWarnings("unused")
	public Integer getOperationStartYear() {
		return operationStartYear;
	}

	public void setOperationStartYear(Integer operationStartYear) {
		this.operationStartYear = operationStartYear;
	}

	@SuppressWarnings("unused")
	public Integer getBuildCompletionYear() {
		return buildCompletionYear;
	}

	public void setBuildCompletionYear(Integer buildCompletionYear) {
		this.buildCompletionYear = buildCompletionYear;
	}

	@SuppressWarnings("unused")
	public Date getLastReconstructionDate() {
		return lastReconstructionDate;
	}

	public void setLastReconstructionDate(Date lastReconstructionDate) {
		this.lastReconstructionDate = lastReconstructionDate;
	}

	@SuppressWarnings("unused")
	public Integer getRebuildingLastYear() {
		return rebuildingLastYear;
	}

	public void setRebuildingLastYear(Integer rebuildingLastYear) {
		this.rebuildingLastYear = rebuildingLastYear;
	}

	public ConstructionCharacteristics getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(ConstructionCharacteristics characteristics) {
		this.characteristics = characteristics;
	}

	public List<ConstructiveElement> getConstructiveElements() {
		return constructiveElements;
	}

	@SuppressWarnings("unused")
	public void setConstructiveElements(List<ConstructiveElement> constructiveElements) {
		this.constructiveElements = constructiveElements;
	}

	@Override
	public CapitalConstruction clone() {
		CapitalConstruction construct = (CapitalConstruction) super.clone();
		if(construct == null) return null;
		construct.getConstructiveElements().addAll(getConstructiveElements().stream().map(ConstructiveElement::clone).collect(Collectors.toList()));
		construct.setMunicipalEntity(getMunicipalEntity() != null ? getMunicipalEntity().clone() : null);
		construct.setType(getType() != null ? getType().clone() : null);
		construct.setCharacteristics(getCharacteristics() != null ? getCharacteristics().clone() : null);
		return construct;
	}

}
