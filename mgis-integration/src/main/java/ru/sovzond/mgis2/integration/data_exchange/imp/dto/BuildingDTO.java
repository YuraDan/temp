package ru.sovzond.mgis2.integration.data_exchange.imp.dto;

/**
 * Created by Alexander Arakelyan on 23.12.15.
 */
public class BuildingDTO {
	private String cadastralNumber;
	private String objectType;
	private String assignationBuildings;
	private Double area;
	private AddressDTO address;
	private Double cadastralCostValue;
	private Integer cadastralCostUnit;
	private EntitySpatialDTO entitySpatial;

	public String getCadastralNumber() {
		return cadastralNumber;
	}

	public void setCadastralNumber(String cadastralNumber) {
		this.cadastralNumber = cadastralNumber;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getAssignationBuildings() {
		return assignationBuildings;
	}

	public void setAssignationBuildings(String assignationBuildings) {
		this.assignationBuildings = assignationBuildings;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public Double getCadastralCostValue() {
		return cadastralCostValue;
	}

	public void setCadastralCostValue(Double cadastralCostValue) {
		this.cadastralCostValue = cadastralCostValue;
	}

	public Integer getCadastralCostUnit() {
		return cadastralCostUnit;
	}

	public void setCadastralCostUnit(Integer cadastralCostUnit) {
		this.cadastralCostUnit = cadastralCostUnit;
	}

	public EntitySpatialDTO getEntitySpatial() {
		return entitySpatial;
	}

	public void setEntitySpatial(EntitySpatialDTO entitySpatial) {
		this.entitySpatial = entitySpatial;
	}
}
