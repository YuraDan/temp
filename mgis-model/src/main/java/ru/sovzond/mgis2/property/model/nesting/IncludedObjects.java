package ru.sovzond.mgis2.property.model.nesting;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.sovzond.mgis2.documents.model.common.Document;
import ru.sovzond.mgis2.property.model.lands.Land;
import ru.sovzond.mgis2.property.model.oks.CapitalConstruction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 22.07.15.
 *
 */
@Entity
@Table(name = "mgis2_property_included_objects")
@OnDelete(action = OnDeleteAction.CASCADE)
public class IncludedObjects implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_property_included_objects_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@ManyToOne
	private Document landDealDocument;

	@ManyToMany
	private List<Land> includedLands = new ArrayList<>();

	@ManyToOne
	private Document inventoryDealDocument;

	@ManyToMany
	private List<CapitalConstruction> includedCapitalConstructions = new ArrayList<>();

	@OneToMany
	private List<Document> urbanPlanningDocuments = new ArrayList<>();


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Document getLandDealDocument() {
		return landDealDocument;
	}

	public void setLandDealDocument(Document landDealDocument) {
		this.landDealDocument = landDealDocument;
	}


	public List<Land> getIncludedLands() {
		return includedLands;
	}

	@SuppressWarnings("unused")
	public void setIncludedLands(List<Land> includedLands) {
		this.includedLands = includedLands;
	}

	public Document getInventoryDealDocument() {
		return inventoryDealDocument;
	}

	public void setInventoryDealDocument(Document inventoryDealDocument) {
		this.inventoryDealDocument = inventoryDealDocument;
	}

	public List<CapitalConstruction> getIncludedCapitalConstructions() {
		return includedCapitalConstructions;
	}

	@SuppressWarnings("unused")
	public void setIncludedCapitalConstructions(List<CapitalConstruction> includedCapitalConstructions) {
		this.includedCapitalConstructions = includedCapitalConstructions;
	}

	public List<Document> getUrbanPlanningDocuments() {
		return urbanPlanningDocuments;
	}

	public void setUrbanPlanningDocuments(List<Document> urbanPlanningDocuments) {
		this.urbanPlanningDocuments = urbanPlanningDocuments;
	}

	public IncludedObjects clone() {
		IncludedObjects includedObjects;
		try {
			includedObjects = (IncludedObjects) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		includedObjects.setIncludedLands(getIncludedLands().stream().map(Land::clone).collect(Collectors.toList()));
		includedObjects.setIncludedCapitalConstructions(getIncludedCapitalConstructions().stream().map(CapitalConstruction::clone).collect(Collectors.toList()));
		includedObjects.setInventoryDealDocument(getInventoryDealDocument() != null ? (Document) getInventoryDealDocument().clone() : null);
		includedObjects.setLandDealDocument(getLandDealDocument() != null ? (Document) getLandDealDocument().clone() : null);
		includedObjects.setUrbanPlanningDocuments(getUrbanPlanningDocuments().stream().map(entry -> (Document) entry.clone()).collect(Collectors.toList()));
		return includedObjects;
	}
}
