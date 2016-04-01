package ru.sovzond.mgis2.documents.web.isogd;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.documents.model.isogd.section.Book;
import ru.sovzond.mgis2.documents.model.isogd.section.Volume;
import ru.sovzond.mgis2.documents.services.isogd.section.IBookService;
import ru.sovzond.mgis2.documents.services.isogd.section.IVolumeService;

import javax.transaction.Transactional;
import java.io.Serializable;

@RestController
@RequestMapping("/isogd/volumes")
@Scope("session")
public class VolumeRESTController implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1275669415070078631L;

	@Autowired
	private IVolumeService volumeService;

	@Autowired
	private IBookService bookService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public PageableContainer<Volume> list(@RequestParam("bookId") Long bookId, @RequestParam(defaultValue = "sortOrder") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		Book book = bookService.load(bookId);
		return volumeService.list(book, orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public Volume save(@PathVariable("id") Long id, @RequestBody Volume volume) {
		Volume volume2;
		if (id == 0) {
			volume2 = new Volume();
			volume2.setBook(bookService.load(volume.getBook().getId()));
		} else {
			volume2 = volumeService.readVolume(id);
		}
		BeanUtils.copyProperties(volume, volume2, "id", "book", "documents");
		volumeService.save(volume2);
		return volume2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public Volume read(@PathVariable Long id) {
		return volumeService.readVolume(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@Transactional
	public void delete(@PathVariable Long id) {
		volumeService.remove(volumeService.readVolume(id));
	}

	@RequestMapping(value = "/swap-orders", method = RequestMethod.POST)
	@Transactional
	public void swapOrders(@RequestBody SwapIdPair pair) {
		SwapManager.byOrder(pair, (CRUDBeanBase) volumeService);
	}

}
