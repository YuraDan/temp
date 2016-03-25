package ru.sovzond.mgis2.documents.model.property;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Sergey Lvov on 24.03.16.
 *
 * Constitutive documents - правоустанавливающие документы
 */
@Entity
@Table(name = "mgis2_docs_property_constitutive")
public class ConstitutiveDocument extends PropertyDocument implements Cloneable {

	public ConstitutiveDocument clone() {
		return (ConstitutiveDocument) super.clone();
	}
}
