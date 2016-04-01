package ru.sovzond.mgis2.isogd;

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
import ru.sovzond.mgis2.documents.model.isogd.section.Book;
import ru.sovzond.mgis2.documents.model.isogd.section.Section;
import ru.sovzond.mgis2.documents.model.isogd.section.Volume;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentSubObject;
import ru.sovzond.mgis2.documents.dao.isogd.document.impl.IsogdDocumentDao;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 25.06.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
public class DocumentSubObjectDaoTest {

    @Autowired
    private SectionDao sectionDao;

    @Autowired
    private BookDao bookDao;


    @Autowired
    private VolumeDao volumeDao;

    @Autowired
    private IsogdDocumentDao documentDao;

    @Test
    @Transactional
    public void testVolumeFilter() {
        Section section = null;
        try {
            section = new Section();
            section.setName("testSection1");
            sectionDao.save(section);
            Book book = null;
            try {
                book = new Book();
                book.setName("testBook1");
                book.setSection(section);
                bookDao.save(book);
                Volume volume = null;
                try {
                    volume = new Volume();
                    volume.setName("testVolume1");
                    volume.setBook(book);
                    volumeDao.save(volume);
                    List<IsogdDocumentSubObject> listAvailableDocumentSubObjects = documentDao.listAvailableDocumentSubObjects(volume);
                    Assert.assertNotNull(listAvailableDocumentSubObjects);
                } finally {
                    if (volume != null && volume.getId() > 0) {
                        volumeDao.delete(volume);
                    }
                }
            } finally {
                if (book != null && book.getId() > 0) {
                    bookDao.delete(book);
                }
            }
        } finally {
            if (section != null && section.getId() > 0) {
                sectionDao.delete(section);
            }
        }
    }
}
