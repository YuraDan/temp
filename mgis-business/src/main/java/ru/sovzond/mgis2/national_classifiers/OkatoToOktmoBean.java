package ru.sovzond.mgis2.national_classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.registers.national_classifiers.OKATO;
import ru.sovzond.mgis2.registers.national_classifiers.OKTMO;
import ru.sovzond.mgis2.registers.national_classifiers.OkatoToOktmo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by donchenko-y on 1/25/16.
 */

@Service
public class OkatoToOktmoBean extends CRUDBeanBase<OkatoToOktmo> {

	@Autowired
	private OkatoToOktmoDao dao;

	@Autowired
	private OKATOBean okatoBean;

	@Autowired
	private OKTMOBean oktmoBean;


	@Override
	protected IPageableDAOBase<OkatoToOktmo> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<OkatoToOktmo> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<OkatoToOktmo> list(String orderBy, int first, int max, String name) {
		Pageable<OkatoToOktmo> pager = dao.pager(dao.createFilter(name, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(OkatoToOktmo::clone).collect(Collectors.toList()), pager.count(), first, max);
	}

	public List<OkatoToOktmo> findByOktmoList(String oktmo) {
		return dao.findByOktmoList(oktmo);
	}

	public List<OkatoToOktmo> findByOkatoList(String okato) {
		return dao.findByOkatoList(okato);
	}

//	public OKATO findByOktmo(String oktmo) {
//		return dao.findByOktmo(oktmo);
//	}

	public OKTMO findByOkato(String okato) {
		List<OkatoToOktmo> list = findByOkatoList(okato);
		if (list.size() == 1) {
			return oktmoBean.findByCode(list.get(0).getOktmo());
		}
		return null;
	}

	public OKATO findByOktmo(String oktmo) {
		List<OkatoToOktmo> list = findByOktmoList(oktmo);
		if (list.size() == 1) {
			return okatoBean.findByCode(list.get(0).getOktmo());
		}
		return null;
	}

}