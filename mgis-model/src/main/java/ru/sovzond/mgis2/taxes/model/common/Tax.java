package ru.sovzond.mgis2.taxes.model.common;

import javax.persistence.*;

/**
 * Created by Sergey Lvov on 23.03.16.
 *
 * Taxes
 */
@MappedSuperclass
public class Tax implements Cloneable {

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
