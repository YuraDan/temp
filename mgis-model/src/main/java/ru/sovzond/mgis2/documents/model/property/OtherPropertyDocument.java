package ru.sovzond.mgis2.documents.model.property;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Sergey Lvov on 24.03.16.
 *
 * Other property documents - прочие документы по объектам недвижимости
 */
@Entity
@Table(name = "mgis2_docs_property_other")
public class OtherPropertyDocument extends PropertyDocument implements Cloneable {

	public OtherPropertyDocument clone() {
		return (OtherPropertyDocument) super.clone();
	}
}
