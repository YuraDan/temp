package ru.sovzond.mgis2.property.model.lands;

import ru.sovzond.mgis2.property.model.common.Property;
import ru.sovzond.mgis2.property.model.lands.characteristics.LandCharacteristics;
import ru.sovzond.mgis2.property.model.lands.control.LandControl;
import ru.sovzond.mgis2.property.model.lands.works.LandWorks;
import ru.sovzond.mgis2.registers.national_classifiers.LandAllowedUsage;
import ru.sovzond.mgis2.registers.national_classifiers.LandCategory;
import ru.sovzond.mgis2.registers.national_classifiers.OKTMO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Entity
@Table(name = "lands_land", indexes = @Index(name = "lands_cadastral_number_ind", unique = true, columnList = "cadastralnumber"))
public class Land extends Property implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "lands_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private LandCategory landCategory;

	@Column
	private Date stateRealEstateCadastreaStaging;

	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<LandArea> landAreas = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	private LandAllowedUsage allowedUsageByDictionary;

	@Column
	private String allowedUsageByDocument;

	@ManyToOne(fetch = FetchType.LAZY)
	private TerritorialZone allowedUsageByTerritorialZone;

	@ManyToOne(fetch = FetchType.LAZY)
	private OKTMO addressOfMunicipalEntity;

	@Column
	private String addressPlacement;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private LandCharacteristics characteristics;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private LandWorks works;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private LandControl control;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private Land previousVersion;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private LandCadastralStatus landCadastralStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStateRealEstateCadastreaStaging() {
		return stateRealEstateCadastreaStaging;
	}

	public void setStateRealEstateCadastreaStaging(Date stateRealEstateCadastreaStaging) {
		this.stateRealEstateCadastreaStaging = stateRealEstateCadastreaStaging;
	}

	public LandAllowedUsage getAllowedUsageByDictionary() {
		return allowedUsageByDictionary;
	}

	public void setAllowedUsageByDictionary(LandAllowedUsage allowedUsageByDictionary) {
		this.allowedUsageByDictionary = allowedUsageByDictionary;
	}

	public String getAllowedUsageByDocument() {
		return allowedUsageByDocument;
	}

	public void setAllowedUsageByDocument(String allowedUsageByDocument) {
		this.allowedUsageByDocument = allowedUsageByDocument;
	}

	public TerritorialZone getAllowedUsageByTerritorialZone() {
		return allowedUsageByTerritorialZone;
	}

	public void setAllowedUsageByTerritorialZone(TerritorialZone allowedUsageByTerritorialZone) {
		this.allowedUsageByTerritorialZone = allowedUsageByTerritorialZone;
	}

	public OKTMO getAddressOfMunicipalEntity() {
		return addressOfMunicipalEntity;
	}

	public void setAddressOfMunicipalEntity(OKTMO addressOfMunicipalEntity) {
		this.addressOfMunicipalEntity = addressOfMunicipalEntity;
	}

	public String getAddressPlacement() {
		return addressPlacement;
	}

	public void setAddressPlacement(String addressPlacement) {
		this.addressPlacement = addressPlacement;
	}

	@SuppressWarnings("unused")
	public Land getPreviousVersion() {
		return previousVersion;
	}

	@SuppressWarnings("unused")
	public void setPreviousVersion(Land previousVersion) {
		this.previousVersion = previousVersion;
	}

	public List<LandArea> getLandAreas() {
		return landAreas;
	}

	public void setLandAreas(List<LandArea> landAreas) {
		this.landAreas = landAreas;
	}

	public LandCategory getLandCategory() {
		return landCategory;
	}

	public void setLandCategory(LandCategory landCategory) {
		this.landCategory = landCategory;
	}

	public LandCharacteristics getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(LandCharacteristics characteristics) {
		this.characteristics = characteristics;
	}

	public LandWorks getWorks() {
		return works;
	}

	public void setWorks(LandWorks works) {
		this.works = works;
	}

	public LandControl getControl() {
		return control;
	}

	public void setControl(LandControl control) {
		this.control = control;
	}

	public LandCadastralStatus getLandCadastralStatus() {
		return landCadastralStatus;
	}

	public void setLandCadastralStatus(LandCadastralStatus landCadastralStatus) {
		this.landCadastralStatus = landCadastralStatus;
	}

	@Override
	public Land clone() {
		Land land = (Land) super.clone();
		if(land == null) return null;
		land.setAllowedUsageByDictionary(getAllowedUsageByDictionary() != null ? getAllowedUsageByDictionary().clone() : null);
		land.setAllowedUsageByTerritorialZone(getAllowedUsageByTerritorialZone() != null ? getAllowedUsageByTerritorialZone().clone() : null);
		land.setLandCategory(getLandCategory() != null ? getLandCategory().clone() : null);
		land.setAddressOfMunicipalEntity(getAddressOfMunicipalEntity() != null ? getAddressOfMunicipalEntity().clone() : null);
		land.setCharacteristics(getCharacteristics() != null ? getCharacteristics().clone() : null);
		land.setControl(getControl() != null ? getControl().clone() : null);
		land.setLandAreas(getLandAreas().stream().map(LandArea::clone).collect(Collectors.toList()));
		return land;
	}

}
