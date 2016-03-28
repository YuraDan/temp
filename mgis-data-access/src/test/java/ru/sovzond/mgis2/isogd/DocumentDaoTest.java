package ru.sovzond.mgis2.isogd;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ru.sovzond.mgis2.dataaccess.base.HibernateConfiguration;
import ru.sovzond.mgis2.documents.dao.isogd.section.impl.BookDao;
import ru.sovzond.mgis2.documents.dao.isogd.section.impl.SectionDao;
import ru.sovzond.mgis2.documents.dao.isogd.section.impl.VolumeDao;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocument;
import ru.sovzond.mgis2.documents.model.isogd.section.Book;
import ru.sovzond.mgis2.documents.model.isogd.section.Section;
import ru.sovzond.mgis2.documents.model.isogd.section.Volume;
import ru.sovzond.mgis2.documents.dao.isogd.document.impl.IsogdDocumentDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
public class DocumentDaoTest {

	@Autowired
	private SectionDao sectionDao;

	@Autowired
	private VolumeDao volumeDao;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private IsogdDocumentDao documentDao;

	@Test
	@Transactional
	public void testDocument() {
		Section section = new Section();
		sectionDao.save(section);
		Assert.assertTrue(section.getId() != null);

		try {
			Book book = new Book();
			book.setSection(section);
			bookDao.save(book);
			Assert.assertTrue(book.getId() != null);

			try {
				Volume volume = new Volume();
				volume.setBook(book);
				volumeDao.save(volume);
				Assert.assertTrue(volume.getId() != null);

				try {
					IsogdDocument document = new IsogdDocument();
					document.setVolume(volume);
					documentDao.save(document);

					Assert.assertTrue(section.getId() != null);
					documentDao.delete(document);
				} finally {
					volumeDao.delete(volume);
				}
			} finally {
				bookDao.delete(book);
			}
		} finally {
			sectionDao.delete(section);
		}
	}
}
