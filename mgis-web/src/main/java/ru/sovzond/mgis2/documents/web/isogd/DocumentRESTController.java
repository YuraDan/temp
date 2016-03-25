package ru.sovzond.mgis2.documents.web.isogd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.documents.model.isogd.Volume;
import ru.sovzond.mgis2.documents.services.isogd.business.IsogdDocumentService;
import ru.sovzond.mgis2.documents.services.isogd.business.VolumeService;
import ru.sovzond.mgis2.documents.services.isogd.business.classifiers.DocumentSubObjectService;
import ru.sovzond.mgis2.documents.services.isogd.business.document.parts.CommonPartService;
import ru.sovzond.mgis2.documents.services.isogd.business.document.parts.DocumentContentService;
import ru.sovzond.mgis2.documents.services.isogd.business.document.parts.SpecialPartService;
import ru.sovzond.mgis2.documents.model.isogd.classifiers.documents.DocumentClass;
import ru.sovzond.mgis2.documents.model.isogd.classifiers.documents.DocumentSubObject;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocument;
import ru.sovzond.mgis2.documents.model.isogd.document.DocumentContent;
import ru.sovzond.mgis2.documents.model.isogd.document.parts.CommonPart;
import ru.sovzond.mgis2.documents.model.isogd.document.parts.SpecialPart;
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
	private VolumeService volumeBean;

	@Autowired
	private IsogdDocumentService documentBean;

	@Autowired
	private DocumentSubObjectService documentSubObjectBean;

	@Autowired
	private CommonPartService commonPartBean;

	@Autowired
	private SpecialPartService specialPartBean;

	@Autowired
	private DocumentContentService documentContentBean;

	@Autowired
	private PersonBean personBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<IsogdDocument> list(@RequestParam("volumeId") Long volumeId, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "id desc") String orderBy) {
		Volume volume = volumeBean.readVolume(volumeId);
		return documentBean.pageDocuments(volume, orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public IsogdDocument save(@PathVariable("id") Long id, @RequestBody IsogdDocument sourceDocument) {
		IsogdDocument document;
		if (id == 0) {
			document = new IsogdDocument();
			document.setVolume(volumeBean.readVolume(sourceDocument.getVolume().getId()));
		} else {
			document = documentBean.load(id);
		}
		document.setName(sourceDocument.getName());
		document.setDocNumber(sourceDocument.getDocNumber());
		document.setDocDate(sourceDocument.getDocDate());
		document.setDocumentSubObject(documentSubObjectBean.load(sourceDocument.getDocumentSubObject().getId()));
		if (sourceDocument.getCommonPart() != null) {
			CommonPart commonPart;
			if (document.getCommonPart() == null) {
				commonPart = new CommonPart();
				commonPart.setDocument(document);
				document.setCommonPart(commonPart);
				documentBean.save(document);
			} else {
				commonPart = document.getCommonPart();
			}
			updateDocumentCommonPartContents(sourceDocument, commonPart);
			commonPartBean.save(commonPart);
		}
		if (sourceDocument.getSpecialPart() != null) {
			SpecialPart specialPart;
			if (document.getSpecialPart() == null) {
				specialPart = new SpecialPart();
				specialPart.setDocument(document);
				document.setSpecialPart(specialPart);
				documentBean.save(document);
			} else {
				specialPart = document.getSpecialPart();
			}
			updateDocumentSpecialPartContents(sourceDocument, specialPart);
			specialPartBean.save(specialPart);
		}
		if (sourceDocument.getAuthor() != null && sourceDocument.getAuthor().getId() != 0) {
			document.setAuthor(personBean.load(sourceDocument.getAuthor().getId()));
		} else {
			document.setAuthor(null);
		}
		documentBean.save(document);
		return (IsogdDocument) document.clone();
	}

	private void updateDocumentCommonPartContents(IsogdDocument sourceDocument, CommonPart part) {
		List<Long> ids = sourceDocument.getCommonPart().getDocumentContents().stream().map(documentContent -> documentContent.getId()).collect(Collectors.toList());
		if (ids.size() > 0) {
			List<DocumentContent> documentContents = documentContentBean.load(ids);
			part.getDocumentContents().clear();
			part.getDocumentContents().addAll(documentContents);
		} else {
			part.getDocumentContents().clear();
		}
	}

	private void updateDocumentSpecialPartContents(IsogdDocument sourceDocument, SpecialPart part) {
		List<Long> ids = sourceDocument.getSpecialPart().getDocumentContents().stream().map(documentContent -> documentContent.getId()).collect(Collectors.toList());
		if (ids.size() > 0) {
			List<DocumentContent> documentContents = documentContentBean.load(ids);
			part.getDocumentContents().clear();
			part.getDocumentContents().addAll(documentContents);
		} else {
			part.getDocumentContents().clear();
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public IsogdDocument read(@PathVariable("id") Long id) {
		return (IsogdDocument) documentBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		documentBean.delete(documentBean.load(id));
	}

	@RequestMapping(value = "/listDocumentSubObjectsByVolumeId/{volumeId}")
	@Transactional
	public PageableContainer<DocumentSubObject> listDocumentSubObjectList(@PathVariable Long volumeId) {
		return new PageableContainer<>(documentBean.listDocumentSubObjectsByVolume(volumeBean.readVolume(volumeId)).stream().map(documentSubObject -> documentSubObject.clone()).collect(Collectors.toList()));
	}

	@RequestMapping(value = "/readDocumentClassByVolumeId/{volumeId}")
	@Transactional
	public DocumentClass readDocumentClassByVolumeId(@PathVariable Long volumeId) {
		return documentBean.readDocumentClassByVolume(volumeBean.readVolume(volumeId)).clone();
	}

}
