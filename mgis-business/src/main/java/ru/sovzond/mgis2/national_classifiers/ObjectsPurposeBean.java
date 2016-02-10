package ru.sovzond.mgis2.national_classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.registers.national_classifiers.ObjectsPurpose;

/**
 * Created by donchenko-y on 2/4/16.
 */
@Service
public class ObjectsPurposeBean {

	@Autowired
	private ObjectsPurposeDao dao;

	public ObjectsPurpose findByCode(String code) {
		return dao.findByCode(code);
	}

	public String findNameByCode(String code) {
		ObjectsPurpose objectsPurpose = findByCode(code);
		if (objectsPurpose != null) {
			return objectsPurpose.getName();
		}
		return null;
	}
}
