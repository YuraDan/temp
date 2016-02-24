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
import ru.sovzond.mgis2.capital_construct.CapitalConstructBean;
import ru.sovzond.mgis2.capital_constructs.CapitalConstruction;
import ru.sovzond.mgis2.lands.includes.LandIncludedObjects;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 18.02.16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
public class LandBeanTest {

	@Autowired
	LandBean landBean;

	@Autowired
	CapitalConstructBean capitalConstructBean;

	@Autowired
	LandIncludedObjectsBean landIncludedObjectsBean;

	@Test
	@Ignore
	@Transactional
	public void getByIncludedObjectsTest() {
		List<LandIncludedObjects> listIO = landIncludedObjectsBean.getIncludedObjectsByCapitalConstruct(590L);
		List<CapitalConstruction> list = capitalConstructBean.getByIncludedObjects(listIO);
		for(CapitalConstruction item: list) {
			System.out.println(item.getName());
		}
		Assert.assertTrue("1", list.size() > 0);
	}
}
