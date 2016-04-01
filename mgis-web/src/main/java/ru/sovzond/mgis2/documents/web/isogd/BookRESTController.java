package ru.sovzond.mgis2.documents.web.isogd;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentObject;
import ru.sovzond.mgis2.documents.model.isogd.section.Book;
import ru.sovzond.mgis2.documents.model.isogd.section.Section;
import ru.sovzond.mgis2.documents.services.isogd.document.IIsogdDocumentObjectService;
import ru.sovzond.mgis2.documents.services.isogd.section.IBookService;
import ru.sovzond.mgis2.documents.services.isogd.section.ISectionService;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/isogd/books")
@Scope("session")
public class BookRESTController implements Serializable {

	private static final long serialVersionUID = 4539915548911543515L;

	@Autowired
	private ISectionService sectionService;

	@Autowired
	private IBookService bookService;

	@Autowired
	private IIsogdDocumentObjectService documentObjectService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public PageableContainer<Book> list(@RequestParam("sectionId") Long sectionId, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "sortOrder") String orderBy) {
		Section section = sectionService.readSection(sectionId);
		return bookService.list(section, orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public Book save(@PathVariable Long id, @RequestBody Book book) {
		Book book2;
		if (id == 0) {
			book2 = new Book();
			book2.setSection(sectionService.readSection(book.getSection().getId()));
		} else {
			book2 = bookService.load(id);
		}
		BeanUtils.copyProperties(book, book2, "id", "section", "documentObject", "volumes");
		book2.setDocumentObject(documentObjectService.load(book.getDocumentObject().getId()));
		bookService.save(book2);
		return book2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public Book read(@PathVariable Long id) {
		return bookService.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		bookService.remove(bookService.load(id));
	}

	@RequestMapping(value = "/listDocumentObjectsBySectionId/{sectionId}")
	@Transactional
	public PageableContainer<IsogdDocumentObject> listDocumentObjectsBySectionId(@PathVariable Long sectionId) {
		return new PageableContainer<>(bookService.listDocumentObjectsBySection(sectionService.readSection(sectionId)).stream().map(IsogdDocumentObject::clone).collect(Collectors.toList()));
	}

	@RequestMapping(value = "/swap-orders", method = RequestMethod.POST)
	@Transactional
	public void swapOrders(@RequestBody SwapIdPair pair) {
		SwapManager.byOrder(pair, (CRUDBeanBase) bookService);
	}

}
