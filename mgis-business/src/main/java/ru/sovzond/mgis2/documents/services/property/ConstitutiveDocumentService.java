package ru.sovzond.mgis2.documents.services.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.documents.dao.property.ConstitutiveDocumentDao;
import ru.sovzond.mgis2.documents.model.property.ConstitutiveDocument;

/**
 * Created by Sergey Lvov on 25.03.16.
 */
@Service
public class ConstitutiveDocumentService extends CRUDBeanBase<ConstitutiveDocument> {

	@Autowired
	private ConstitutiveDocumentDao dao;

	@Override
	protected IPageableDAOBase<ConstitutiveDocument> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<ConstitutiveDocument> getIIdentifiableDao() {
		return dao;
	}

	public ConstitutiveDocument load(Long id) {
		return dao.findById(id);
	}

}
