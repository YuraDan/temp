package ru.sovzond.mgis2.web.isogd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.isogd.Volume;
import ru.sovzond.mgis2.isogd.business.DocumentBean;
import ru.sovzond.mgis2.isogd.business.VolumeBean;
import ru.sovzond.mgis2.isogd.business.classifiers.DocumentSubObjectBean;
import ru.sovzond.mgis2.isogd.business.classifiers.representation.RepresentationFormatBean;
import ru.sovzond.mgis2.isogd.business.document.parts.CommonPartBean;
import ru.sovzond.mgis2.isogd.business.document.parts.DocumentContentBean;
import ru.sovzond.mgis2.isogd.business.document.parts.SpecialPartBean;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentClass;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentSubObject;
import ru.sovzond.mgis2.isogd.classifiers.documents.representation.RepresentationFormat;
import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.isogd.document.DocumentContent;
import ru.sovzond.mgis2.isogd.document.parts.CommonPart;
import ru.sovzond.mgis2.isogd.document.parts.SpecialPart;

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
    private VolumeBean volumeBean;

    @Autowired
    private DocumentBean documentBean;

    @Autowired
    private DocumentSubObjectBean documentSubObjectBean;

    @Autowired
    private CommonPartBean commonPartBean;

    @Autowired
    private SpecialPartBean specialPartBean;

    @Autowired
    private DocumentContentBean documentContentBean;

//	@Autowired
//	private RepresentationFormBean representationFormBean;

    @Autowired
    private RepresentationFormatBean representationFormatBean;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Transactional
    public PageableContainer<Document> list(@RequestParam("volumeId") Long volumeId, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
        Volume volume = volumeBean.readVolume(volumeId);
        return documentBean.pageDocuments(volume, first, max);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @Transactional
    public Document save(@PathVariable("id") Long id, @RequestBody Document document) {
        Document document2;
        if (id == 0) {
            document2 = new Document();
            document2.setVolume(volumeBean.readVolume(document.getVolume().getId()));
        } else {
            document2 = documentBean.readDocument(id);
        }
        document2.setName(document.getName());
        document2.setDocNumber(document.getDocNumber());
        document2.setDocDate(document.getDocDate());
        document2.setDocumentSubObject(documentSubObjectBean.load(document.getDocumentSubObject().getId()));
        if (document.getCommonPart() != null) {
            CommonPart commonPart;
            if (document2.getCommonPart() == null) {
                commonPart = new CommonPart();
                commonPart.setDocument(document2);
                document2.setCommonPart(commonPart);
            } else {
                commonPart = document2.getCommonPart();
            }
            updateDocumentCommonPartContents(document, commonPart);
            commonPartBean.save(commonPart);
        }
        if (document.getSpecialPart() != null) {
            SpecialPart specialPart;
            if (document2.getSpecialPart() == null) {
                specialPart = new SpecialPart();
                specialPart.setDocument(document2);
                document2.setSpecialPart(specialPart);
            } else {
                specialPart = document2.getSpecialPart();
            }
            updateDocumentSpecialPartContents(document, specialPart);
            specialPartBean.save(specialPart);
        }
        documentBean.save(document2);
        return document2.clone();
    }

    private void updateDocumentCommonPartContents(Document sourceDocument, CommonPart part) {
        List<Long> ids = sourceDocument.getCommonPart().getDocumentContents().stream().map(documentContent -> documentContent.getId()).collect(Collectors.toList());
        if (ids.size() > 0) {
            List<DocumentContent> documentContents = documentContentBean.load(ids);
            part.getDocumentContents().clear();
            part.getDocumentContents().addAll(documentContents);
        } else {
            part.getDocumentContents().clear();
        }
    }

    private void updateDocumentSpecialPartContents(Document sourceDocument, SpecialPart part) {
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
    public Document read(@PathVariable("id") Long id) {
        return documentBean.readDocument(id).clone();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional
    public void delete(@PathVariable Long id) {
        documentBean.delete(documentBean.readDocument(id));
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

    @RequestMapping(value = "/uploadDocumentContent", headers = "Accept=*/*", produces = "application/json", method = RequestMethod.POST)
    @Transactional
    @ResponseBody
    public String uploadCommonContent(@RequestBody MultipartFile file) throws JsonProcessingException {
        String contentType = file.getContentType();
        List<RepresentationFormat> list = representationFormatBean.findByFormat(contentType);
        switch (list.size()) {
            case 0:
                throw new IllegalArgumentException("NO_REPRESENTATION_FORMAT_FOUND: " + contentType);
            case 1:
                RepresentationFormat representationFormat = list.get(0);
                DocumentContent documentContent = new DocumentContent();
                documentContent.setRepresentationFormat(representationFormat);
                documentContent.setFileName(file.getOriginalFilename());
                documentContentBean.save(documentContent);
                ObjectMapper mapper = new ObjectMapper();
                try {
                    return mapper.writeValueAsString(documentContent.clone());
                } catch (JsonProcessingException ex) {
                    throw ex;
                }
            default:
                throw new IllegalArgumentException("MORE_THAN_ONE_REPRESENTATION_FORMATS_FOUND: " + contentType);
        }
    }
}
