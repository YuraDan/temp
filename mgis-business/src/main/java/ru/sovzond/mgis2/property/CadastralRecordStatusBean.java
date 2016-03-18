package ru.sovzond.mgis2.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey Lvov on 16.03.16.
 *
 * Service for CadastralRecordStatus
 */
@Service
public class CadastralRecordStatusBean extends CRUDBeanBase<CadastralRecordStatus> {

	@Autowired
	private CadastralRecordStatusDaO dao;

	@Override
	protected IPageableDAOBase<CadastralRecordStatus> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<CadastralRecordStatus> getIIdentifiableDao() {
		return dao;
	}

	@Transactional
	public List<CadastralRecordStatus> getAll() {
		List<CadastralRecordStatus> list  = dao.getAll();
		if(list.isEmpty()) {
			CadastralRecordStatus status = new CadastralRecordStatus();
			status.setCode("01");
			status.setName("Ранее учтенный");
			dao.save(status);
			status = new CadastralRecordStatus();
			status.setCode("05");
			status.setName("Временный");
			dao.save(status);
			status = new CadastralRecordStatus();
			status.setCode("06");
			status.setName("Учтенный");
			dao.save(status);
			status = new CadastralRecordStatus();
			status.setCode("07");
			status.setName("Снят с учета");
			dao.save(status);
			status = new CadastralRecordStatus();
			status.setCode("08");
			status.setName("Аннулированный");
			dao.save(status);
			list = dao.getAll();
		}
		return list.stream().map(CadastralRecordStatus::clone).collect(Collectors.toList());
	}
}
