package ru.sovzond.mgis2.documents.services.isogd.section.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.documents.dao.isogd.section.IVolumeDao;
import ru.sovzond.mgis2.documents.model.isogd.section.Book;
import ru.sovzond.mgis2.documents.model.isogd.section.Volume;
import ru.sovzond.mgis2.documents.services.isogd.section.IVolumeService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 28/06/15.
 */
@Service
public class VolumeService extends CRUDBeanBase<Volume> implements IVolumeService {

	@Autowired
	private IVolumeDao dao;

	@Override
	protected IPageableDAOBase<Volume> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<Volume> getIIdentifiableDao() {
		return dao;
	}

	public Volume readVolume(Long id) {
		return dao.findById(id);
	}

	public PageableContainer<Volume> list(Book book, String orderBy, int first, int max) {
		Pageable<Volume> pager = dao.pager(dao.createFilter(book, orderBy, first, max));
		List<Volume> list = pager.list().stream().map(Volume::clone).collect(Collectors.toList());
		return new PageableContainer<>(list, pager.count(), first, max);
	}

}
