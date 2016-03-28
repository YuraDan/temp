package ru.sovzond.mgis2.documents.dao.isogd.document.representation.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.documents.dao.isogd.document.representation.IRepresentationFormatDao;
import ru.sovzond.mgis2.documents.model.isogd.document.representation.RepresentationFormat;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Repository
public class RepresentationFormatDao extends CRUDDaoBase<RepresentationFormat> implements IRepresentationFormatDao {
	public List<RepresentationFormat> findByFormat(String contentType) {
		//noinspection JpaQlInspection
		return getSession().createQuery("SELECT reprFmt FROM RepresentationFormat reprFmt JOIN reprFmt.formats f WHERE f IN (:fmts) ").setParameter("fmts", contentType).list();
	}
}
