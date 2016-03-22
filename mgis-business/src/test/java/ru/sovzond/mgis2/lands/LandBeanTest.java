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
import ru.sovzond.mgis2.property.model.oks.CapitalConstruction;
import ru.sovzond.mgis2.property.services.lands.ILandService;
import ru.sovzond.mgis2.property.services.nesting.IIncludedObjectsService;
import ru.sovzond.mgis2.property.services.oks.ICapitalConstructService;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 18.02.16.
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
public class LandBeanTest {

	@Autowired
	ILandService landService;

	@Autowired
	ICapitalConstructService capitalConstructService;

	@Autowired
	IIncludedObjectsService landIncludedObjectsService;

	@Test
	@Ignore
	@Transactional
	public void getByIncludedObjectsTest() {
		List<IncludedObjects> listIO = landIncludedObjectsService.getIncludedObjectsByCapitalConstruct(590L);
		List<CapitalConstruction> list = capitalConstructService.getByIncludedObjects(listIO);
		for(CapitalConstruction item: list) {
			System.out.println(item.getName());
		}
		Assert.assertTrue("1", list.size() > 0);
	}
}
