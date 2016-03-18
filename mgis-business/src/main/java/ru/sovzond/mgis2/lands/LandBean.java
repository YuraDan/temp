package ru.sovzond.mgis2.lands;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.geo.GeometryParser;
import ru.sovzond.mgis2.property.model.lands.includes.LandIncludedObjects;
import ru.sovzond.mgis2.property.model.lands.Land;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LandBean extends CRUDBeanBase<Land> {

	@Autowired
	private LandDao dao;

	@Override
	protected IPageableDAOBase<Land> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<Land> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<Land> list(String cadastralNumber, Long[] ids, String orderBy, int first, int max) {
		Pageable<Land> lands = dao.pager(dao.createFilter(cadastralNumber, ids, orderBy, first, max));
		return new PageableContainer<>(lands.list().stream().map(Land::clone).collect(Collectors.toList()), lands.count(), first, max);
	}

	public boolean saveGeospatialAttribute(Long id, String wktString) {
		Land land = dao.findById(id);
		Polygon polygon = (Polygon) GeometryParser.wktToGeometry(wktString);
		MultiPolygon geom = new MultiPolygon(new Polygon[]{polygon}, new GeometryFactory());
		land.setGeometry(geom);
		dao.save(land);
		return true;
	}

	public List<Land> find(String cadastralNumber) {
		return dao.find(cadastralNumber);
	}

	public List<Land> getByIncludedObjects(List<LandIncludedObjects> includedObjects) {
		return dao.getByIncludedObjects(includedObjects);
	}
}
