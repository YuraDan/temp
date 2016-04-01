package ru.sovzond.mgis2.lands;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.sovzond.mgis2.business.base.HibernateConfiguration;
import ru.sovzond.mgis2.property.model.nesting.IncludedObjects;
import ru.sovzond.mgis2.property.services.lands.ILandService;
import ru.sovzond.mgis2.property.services.nesting.IIncludedObjectsService;
import ru.sovzond.mgis2.property.services.oks.ICapitalConstructService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
public class LandIncludedObjectsBeanTest {

	@Autowired
	IIncludedObjectsService landIncludedObjectsService;

	@Autowired
	ILandService landService;

	@Autowired
	ICapitalConstructService capitalConstructService;

	@Test
	@Ignore
	@Transactional
	public void getIncludedObjectsByCapitalConstructTest() {

		List<IncludedObjects> list = landIncludedObjectsService.getIncludedObjectsByCapitalConstruct(590L);
		for(IncludedObjects item: list) {
			System.out.println(item.getId());
		}
		Assert.assertTrue("1", list.size() > 0);
	}

	@Test
	@Ignore
	@Transactional
	public void getIncludedObjectsByLandTest() {

		List<IncludedObjects> list = landIncludedObjectsService.getIncludedObjectsByLand(7848L);
		for(IncludedObjects item: list) {
			System.out.println(item.getId());
		}
		Assert.assertTrue("2", list.size() > 0);
	}

}
