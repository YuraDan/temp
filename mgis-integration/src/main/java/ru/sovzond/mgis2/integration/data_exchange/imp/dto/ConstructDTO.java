package ru.sovzond.mgis2.integration.data_exchange.imp.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by Alexander Arakelyan on 24.12.15.
 */
public class ConstructDTO {
	private String cadastralNumber;
	private String objectType;
	private String assignationBuilding;
	private Double area;
	private AddressDTO address;
	private Double cadastralCostValue;
	private Integer cadastralCostUnit;
	private EntitySpatialDTO entitySpatial;
	private Integer keyParameterType;
	private Double keyParameterValue;

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

	public String getAssignationBuilding() {
		return assignationBuilding;
	}

	public void setAssignationBuilding(String assignationBuilding) {
		this.assignationBuilding = assignationBuilding;
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

	public Integer getKeyParameterType() {
		return keyParameterType;
	}

	public void setKeyParameterType(Integer keyParameterType) {
		this.keyParameterType = keyParameterType;
	}

	public Double getKeyParameterValue() {
		return keyParameterValue;
	}

	public void setKeyParameterValue(Double keyParameterValue) {
		this.keyParameterValue = keyParameterValue;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("cadastralNumber", cadastralNumber)
				.append("objectType", objectType)
				.append("cadastralCostValue", cadastralCostValue)
				.append("cadastralCostUnit", cadastralCostUnit)
				.append("assignationBuilding", assignationBuilding)
				.append("entitySpatial", entitySpatial)
				.append("keyParameterType", keyParameterType)
				.append("keyParameterValue", keyParameterValue)
				.toString();
	}
}
