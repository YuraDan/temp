package ru.sovzond.mgis2.isogd.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.isogd.document.DocumentDao;
import ru.sovzond.mgis2.isogd.document.IncludedDocuments;
import ru.sovzond.mgis2.isogd.document.IncludedDocumentsDao;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Sergey Lvov on 10.03.16.
 *
 */

@Service
public class IncludedDocumentsBean extends CRUDBeanBase<IncludedDocuments> {

	@Autowired
	private IncludedDocumentsDao dao;

	@Autowired
	private DocumentDao documentDao;

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
			persistIncludedDocuments = new IncludedDocuments();
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
				persistent = documentDao.findById(document.getId());
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
