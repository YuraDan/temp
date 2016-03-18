package ru.sovzond.mgis2.property;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;

import java.util.List;

/**
 * Created by Sergey Lvov on 16.03.16.
 *
 * DAO for CadastralRecordStatus
 */
@Repository
public class CadastralRecordStatusDaO extends CRUDDaoBase<CadastralRecordStatus> {

	@SuppressWarnings("unchecked")
	public List<CadastralRecordStatus> getAll() {
		return getSession().createCriteria(persistentClass).list();
	}


}
