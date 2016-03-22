package ru.sovzond.mgis2.property.model.common;

import com.vividsolutions.jts.geom.MultiPolygon;
import org.hibernate.annotations.Type;
import ru.sovzond.mgis2.address.Address;
import ru.sovzond.mgis2.geo.SpatialGroup;
import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.property.model.nesting.IncludedObjects;
import ru.sovzond.mgis2.property.model.rights.PropertyRights;
import ru.sovzond.mgis2.registers.national_classifiers.LandEncumbrance;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey Lvov on 21.03.16.
 *
 * Superclass for property objects
 */
@MappedSuperclass
public class Property implements Cloneable {

	@Column
	private String cadastralNumber;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private PropertyRights rights;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private IncludedObjects includedObjects;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private LandEncumbrance encumbrance;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private CadastralRecordStatus cadastralRecordStatus;

	@ManyToMany
	private List<Document> documents = new ArrayList<>();

	/**
	 * Адрес
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Address address;

	@Type(type = "org.hibernate.spatial.GeometryType")
	@Column(name = "geometry")
	private MultiPolygon geometry;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "spatial_data_id")
	private SpatialGroup spatialData;

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public String getCadastralNumber() {
		return cadastralNumber;
	}

	public void setCadastralNumber(String cadastralNumber) {
		this.cadastralNumber = cadastralNumber;
	}

	public PropertyRights getRights() {
		return rights;
	}

	public void setRights(PropertyRights rights) {
		this.rights = rights;
	}

	public IncludedObjects getIncludedObjects() {
		return includedObjects;
	}

	public void setIncludedObjects(IncludedObjects includedObjects) {
		this.includedObjects = includedObjects;
	}

	public LandEncumbrance getEncumbrance() {
		return encumbrance;
	}

	public void setEncumbrance(LandEncumbrance encumbrance) {
		this.encumbrance = encumbrance;
	}

	public CadastralRecordStatus getCadastralRecordStatus() {
		return cadastralRecordStatus;
	}

	public void setCadastralRecordStatus(CadastralRecordStatus cadastralRecordStatus) {
		this.cadastralRecordStatus = cadastralRecordStatus;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public MultiPolygon getGeometry() {
		return geometry;
	}

	public void setGeometry(MultiPolygon geometry) {
		this.geometry = geometry;
	}

	public SpatialGroup getSpatialData() {
		return spatialData;
	}

	public void setSpatialData(SpatialGroup spatialData) {
		this.spatialData = spatialData;
	}

	public Object clone() {
		Property property;
		try {
			property = (Property) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		property.setCadastralNumber(getCadastralNumber());
		property.setRights(getRights() != null ? getRights().clone() : null);
		property.setIncludedObjects(getIncludedObjects() != null ? getIncludedObjects().clone() : null);
		property.setEncumbrance(getEncumbrance() != null ? getEncumbrance().clone() : null);
		property.setCadastralRecordStatus(getCadastralRecordStatus());
		property.setDocuments(getDocuments() != null ? getDocuments().stream().map(Document::clone).collect(Collectors.toList()) : null);
		property.setSpatialData(getSpatialData() != null ? getSpatialData().clone() : null);
		property.setAddress(getAddress() != null? getAddress().clone() : null);
		property.setGeometry(null);
		return property;
	}
}
