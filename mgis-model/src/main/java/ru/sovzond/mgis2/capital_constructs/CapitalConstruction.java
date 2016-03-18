package ru.sovzond.mgis2.capital_constructs;

import com.vividsolutions.jts.geom.MultiPolygon;
import org.hibernate.annotations.Type;
import ru.sovzond.mgis2.address.Address;
import ru.sovzond.mgis2.capital_constructs.characteristics.ConstructionCharacteristics;
import ru.sovzond.mgis2.capital_constructs.constructive_elements.ConstructiveElement;
import ru.sovzond.mgis2.capital_constructs.rights.ConstructionRights;
import ru.sovzond.mgis2.geo.SpatialGroup;
import ru.sovzond.mgis2.lands.includes.LandIncludedObjects;
import ru.sovzond.mgis2.property.CadastralRecordStatus;
import ru.sovzond.mgis2.registers.national_classifiers.LandEncumbrance;
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
@Entity
@Table(name = "occ_capital_construction", indexes = @Index(name = "oks_cadastral_number_ind", unique = true, columnList = "cadastralnumber"))
public class CapitalConstruction implements Cloneable {
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
	 * Общие сведения
	 */

	@Column
	private String cadastralNumber;

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
	 * Адрес
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;

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

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private CadastralRecordStatus cadastralRecordStatus;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private LandEncumbrance encumbrance;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private ConstructionRights rights;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private ConstructionCharacteristics characteristics;

	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
	private List<ConstructiveElement> constructiveElements = new ArrayList<>();

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private LandIncludedObjects landIncludedObjects;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "spatial_data_id")
	private SpatialGroup spatialData;

	@Type(type = "org.hibernate.spatial.GeometryType")
	@Column(name = "geometry")
	private MultiPolygon geometry;

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

	public String getCadastralNumber() {
		return cadastralNumber;
	}

	public void setCadastralNumber(String cadastralNumber) {
		this.cadastralNumber = cadastralNumber;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public ConstructionRights getRights() {
		return rights;
	}

	public void setRights(ConstructionRights rights) {
		this.rights = rights;
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

	public LandIncludedObjects getLandIncludedObjects() {
		return landIncludedObjects;
	}

	public void setLandIncludedObjects(LandIncludedObjects landIncludedObjects) {
		this.landIncludedObjects = landIncludedObjects;
	}

	public SpatialGroup getSpatialData() {
		return spatialData;
	}

	public void setSpatialData(SpatialGroup spatialData) {
		this.spatialData = spatialData;
	}

	public LandEncumbrance getEncumbrance() {
		return encumbrance;
	}

	public void setEncumbrance(LandEncumbrance encumbrance) {
		this.encumbrance = encumbrance;
	}

	public void setGeometry(MultiPolygon geometry) {
		this.geometry = geometry;
	}

	public MultiPolygon getGeometry() {
		return geometry;
	}

	public CadastralRecordStatus getCadastralRecordStatus() {
		return cadastralRecordStatus;
	}

	public void setCadastralRecordStatus(CadastralRecordStatus cadastralRecordStatus) {
		this.cadastralRecordStatus = cadastralRecordStatus;
	}

	@SuppressWarnings("CloneDoesntCallSuperClone")
	public CapitalConstruction clone() {
		CapitalConstruction construct = new CapitalConstruction();
		construct.setId(id);
		construct.setName(name);
		construct.setActualUsage(actualUsage);
		construct.setAddress(address != null ? address.clone() : null);
		construct.setBuildCompletionYear(buildCompletionYear);
		construct.setCadastralNumber(cadastralNumber);
		construct.setConditionalNumber(conditionalNumber);
		construct.getConstructiveElements().addAll(constructiveElements.stream().map(ConstructiveElement::clone).collect(Collectors.toList()));
		construct.setInventoryNumber(inventoryNumber);
		construct.setLastReconstructionDate(lastReconstructionDate);
		construct.setLetter(letter);
		construct.setMunicipalEntity(municipalEntity != null ? municipalEntity.clone() : null);
		construct.setObjectPurpose(objectPurpose);
		construct.setOperationStartYear(operationStartYear);
		construct.setOverallArea(overallArea);
		construct.setPlacement(placement);
		construct.setRebuildingLastYear(rebuildingLastYear);
		construct.setRights(rights != null ? rights.clone() : null);
		construct.setTechnicalAccountingStatementDate(technicalAccountingStatementDate);
		construct.setType(type != null ? type.clone() : null);
		construct.setCharacteristics(characteristics != null ? characteristics.clone() : null);
		construct.setLandIncludedObjects(landIncludedObjects != null ? landIncludedObjects.clone() : null);
		construct.setSpatialData(spatialData != null ? spatialData.clone() : null);
		construct.setEncumbrance(getEncumbrance() != null ? getEncumbrance().clone() : null);
		construct.setCadastralRecordStatus(getCadastralRecordStatus() != null ? getCadastralRecordStatus().clone() : null);
		return construct;
	}

}
