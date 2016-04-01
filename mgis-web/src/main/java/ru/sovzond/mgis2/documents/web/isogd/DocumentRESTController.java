package ru.sovzond.mgis2.documents.web.isogd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.documents.model.common.CommonPart;
import ru.sovzond.mgis2.documents.model.common.DocumentContent;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocument;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentClass;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentSubObject;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdSpecialPart;
import ru.sovzond.mgis2.documents.model.isogd.section.Volume;
import ru.sovzond.mgis2.documents.services.common.ICommonPartService;
import ru.sovzond.mgis2.documents.services.common.IDocumentContentService;
import ru.sovzond.mgis2.documents.services.isogd.document.IIsogdDocumentService;
import ru.sovzond.mgis2.documents.services.isogd.document.IIsogdDocumentSubObjectService;
import ru.sovzond.mgis2.documents.services.isogd.document.IIsogdSpecialPartService;
import ru.sovzond.mgis2.documents.services.isogd.section.IVolumeService;
import ru.sovzond.mgis2.persons.PersonBean;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/isogd/documents")
@Scope("session")
public class DocumentRESTController implements Serializable {

	private static final long serialVersionUID = -440806213097386154L;

	@Autowired
	private IVolumeService volumeService;

	@Autowired
	private IIsogdDocumentService isogdDocumentService;

	@Autowired
	private IIsogdDocumentSubObjectService documentSubObjectService;

	@Autowired
	private ICommonPartService commonPartService;

	@Autowired
	private IIsogdSpecialPartService specialPartService;

	@Autowired
	private IDocumentContentService documentContentService;

	@Autowired
	private PersonBean personBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<IsogdDocument> list(@RequestParam("volumeId") Long volumeId, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "id desc") String orderBy) {
		Volume volume = volumeService.readVolume(volumeId);
		return isogdDocumentService.pageDocuments(volume, orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public IsogdDocument save(@PathVariable("id") Long id, @RequestBody IsogdDocument sourceDocument) {
		IsogdDocument document;
		if (id == 0) {
			document = new IsogdDocument();
			document.setVolume(volumeService.readVolume(sourceDocument.getVolume().getId()));
		} else {
			document = isogdDocumentService.load(id);
		}
		document.setName(sourceDocument.getName());
		document.setDocNumber(sourceDocument.getDocNumber());
		document.setDocDate(sourceDocument.getDocDate());
		document.setDocumentSubObject(documentSubObjectService.load(sourceDocument.getDocumentSubObject().getId()));
		if (sourceDocument.getCommonPart() != null) {
			CommonPart commonPart;
			if (document.getCommonPart() == null) {
				commonPart = new CommonPart();
				commonPart.setDocument(document);
				document.setCommonPart(commonPart);
				isogdDocumentService.save(document);
			} else {
				commonPart = document.getCommonPart();
			}
			updateDocumentCommonPartContents(sourceDocument, commonPart);
			commonPartService.save(commonPart);
		}
		if (sourceDocument.getSpecialPart() != null) {
			IsogdSpecialPart specialPart;
			if (document.getSpecialPart() == null) {
				specialPart = new IsogdSpecialPart();
				specialPart.setDocument(document);
				document.setSpecialPart(specialPart);
				isogdDocumentService.save(document);
			} else {
				specialPart = document.getSpecialPart();
			}
			updateDocumentSpecialPartContents(sourceDocument, specialPart);
			specialPartService.save(specialPart);
		}
		if (sourceDocument.getAuthor() != null && sourceDocument.getAuthor().getId() != 0) {
			document.setAuthor(personBean.load(sourceDocument.getAuthor().getId()));
		} else {
			document.setAuthor(null);
		}
		isogdDocumentService.save(document);
		return (IsogdDocument) document.clone();
	}

	private void updateDocumentCommonPartContents(IsogdDocument sourceDocument, CommonPart part) {
		List<Long> ids = sourceDocument.getCommonPart().getDocumentContents().stream().map(DocumentContent::getId).collect(Collectors.toList());
		if (ids.size() > 0) {
			List<DocumentContent> documentContents = documentContentService.load(ids);
			part.getDocumentContents().clear();
			part.getDocumentContents().addAll(documentContents);
		} else {
			part.getDocumentContents().clear();
		}
	}

	private void updateDocumentSpecialPartContents(IsogdDocument sourceDocument, IsogdSpecialPart part) {
		List<Long> ids = sourceDocument.getSpecialPart().getDocumentContents().stream().map(DocumentContent::getId).collect(Collectors.toList());
		if (ids.size() > 0) {
			List<DocumentContent> documentContents = documentContentService.load(ids);
			part.getDocumentContents().clear();
			part.getDocumentContents().addAll(documentContents);
		} else {
			part.getDocumentContents().clear();
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public IsogdDocument read(@PathVariable("id") Long id) {
		return (IsogdDocument) isogdDocumentService.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		isogdDocumentService.delete(isogdDocumentService.load(id));
	}

	@RequestMapping(value = "/listDocumentSubObjectsByVolumeId/{volumeId}")
	@Transactional
	public PageableContainer<IsogdDocumentSubObject> listDocumentSubObjectList(@PathVariable Long volumeId) {
		return new PageableContainer<>(isogdDocumentService.listDocumentSubObjectsByVolume(volumeService.readVolume(volumeId)).stream().map(IsogdDocumentSubObject::clone).collect(Collectors.toList()));
	}

	@RequestMapping(value = "/readDocumentClassByVolumeId/{volumeId}")
	@Transactional
	public IsogdDocumentClass readDocumentClassByVolumeId(@PathVariable Long volumeId) {
		return isogdDocumentService.readDocumentClassByVolume(volumeService.readVolume(volumeId)).clone();
	}

}
