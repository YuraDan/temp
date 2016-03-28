package ru.sovzond.mgis2.documents.services.isogd.section.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.documents.dao.isogd.section.ISectionDao;
import ru.sovzond.mgis2.documents.model.isogd.section.Section;
import ru.sovzond.mgis2.documents.services.isogd.section.ISectionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionService extends CRUDBeanBase<Section> implements ISectionService {

	@Autowired
	private ISectionDao sectionDao;

	@Override
	protected IPageableDAOBase<Section> getPageableDao() {
		return sectionDao;
	}

	@Override
	protected IIdentifiableDao<Section> getIIdentifiableDao() {
		return sectionDao;
	}

	public Section readSection(Long id) {
		return sectionDao.findById(id);
	}

	public Section save(Section section) {
		sectionDao.save(section);
		return section;
	}

	public void delete(Section section) {
		sectionDao.delete(section);
	}

	public PageableContainer<Section> pageSections(String orderBy, int first, int max, String name) {
		Pageable<Section> pager = sectionDao.pager(sectionDao.createFilter(name, orderBy, first, max));
		List<Section> sections = pager.list().stream().map(Section::clone).collect(Collectors.toList());
		return new PageableContainer<>(sections, pager.count(), first, max);
	}

}
