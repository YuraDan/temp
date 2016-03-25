package ru.sovzond.mgis2.documents.services.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.documents.dao.common.IncludedDocumentsDao;
import ru.sovzond.mgis2.documents.model.common.Document;
import ru.sovzond.mgis2.documents.model.common.IncludedDocuments;
import ru.sovzond.mgis2.documents.services.isogd.business.IsogdDocumentService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Sergey Lvov on 10.03.16.
 *
 */

@Service
public class IncludedDocumentsService extends CRUDBeanBase<IncludedDocuments> {

	@Autowired
	private IncludedDocumentsDao dao;

	@Autowired
	private IsogdDocumentService documentService;

	@Override
	protected IPageableDAOBase<IncludedDocuments> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<IncludedDocuments> getIIdentifiableDao() {
		return dao;
	}

	public IncludedDocuments load(Long id) {
		return dao.findById(id);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public IncludedDocuments syncIncludedDocuments(IncludedDocuments persistIncludedDocuments, IncludedDocuments newIncludedDocuments) {
		if(persistIncludedDocuments == null || persistIncludedDocuments.getId() == null || persistIncludedDocuments.getId() == 0) {
			if(newIncludedDocuments == null) return null;
			persistIncludedDocuments = new IncludedDocuments();
		}
		if(newIncludedDocuments == null) {
			dao.delete(persistIncludedDocuments);
			return null;
		}
		List<Document> persistentList = persistIncludedDocuments.getDocuments();
		List<Document> newList = newIncludedDocuments.getDocuments();
		int oldListSize = persistentList.size();
		Map<Long, Document> persistentMap = new HashMap<>();
		for (Document document : persistentList) {
			persistentMap.put(document.getId(), document);
		}
		Set<Long> newIds = new HashSet<>();
		for (Document document: newList) {
			Document persistent = persistentMap.get(document.getId());
			if(persistent == null) {
				persistent = documentService.load(document.getId());
				persistentMap.put(persistent.getId(), persistent);
				persistentList.add(persistent);
			}
			newIds.add(persistent.getId());
		}
		List<Document> toBeRemoved = persistentMap.entrySet().stream().filter(entry -> !newIds.contains(entry.getKey())).map(Map.Entry::getValue).collect(Collectors.toList());
		toBeRemoved.forEach(persistentList::remove);
		return (!toBeRemoved.isEmpty() || persistentList.size() != oldListSize) ? persistIncludedDocuments : null;
	}

}
