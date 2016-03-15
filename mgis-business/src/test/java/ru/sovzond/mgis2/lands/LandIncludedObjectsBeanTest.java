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
import ru.sovzond.mgis2.lands.includes.LandIncludedObjects;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
public class LandIncludedObjectsBeanTest {

	@Autowired
	LandIncludedObjectsBean landIncludedObjectsBean;

	@Autowired
	LandBean landBean;

	@Autowired
	CapitalConstructBean capitalConstructBean;

	@Test
	@Ignore
	@Transactional
	public void getIncludedObjectsByCapitalConstructTest() {

		List<LandIncludedObjects> list = landIncludedObjectsBean.getIncludedObjectsByCapitalConstruct(590L);
		for(LandIncludedObjects item: list) {
			System.out.println(item.getId());
		}
		Assert.assertTrue("1", list.size() > 0);
	}

	@Test
	@Ignore
	@Transactional
	public void getIncludedObjectsByLandTest() {

		List<LandIncludedObjects> list = landIncludedObjectsBean.getIncludedObjectsByLand(7848L);
		for(LandIncludedObjects item: list) {
			System.out.println(item.getId());
		}
		Assert.assertTrue("2", list.size() > 0);
	}

}
