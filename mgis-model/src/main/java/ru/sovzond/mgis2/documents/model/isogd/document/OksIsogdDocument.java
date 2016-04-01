package ru.sovzond.mgis2.documents.model.isogd.document;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Sergey Lvov on 24.03.16.
 */
@Entity
@Table(name = "mgis2_docs_isogd_oks")
public class OksIsogdDocument extends IsogdDocument implements Cloneable {

	public OksIsogdDocument clone() {
		return (OksIsogdDocument) super.clone();
	}

}
