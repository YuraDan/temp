package ru.sovzond.mgis2.documents.web.isogd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.sovzond.mgis2.documents.services.isogd.business.classifiers.representation.RepresentationFormatBean;
import ru.sovzond.mgis2.documents.services.isogd.business.document.parts.DocumentContentService;
import ru.sovzond.mgis2.documents.model.isogd.classifiers.documents.representation.RepresentationFormat;
import ru.sovzond.mgis2.documents.model.isogd.document.DocumentContent;
import ru.sovzond.mgis2.preview.ImageManipulationBean;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by Alexander Arakelyan on 15.07.15.
 */
@RestController
@RequestMapping("/isogd/documentContents")
@Scope("session")
public class DocumentContentRESTController {

	public static final String JPEG = "jpeg";
	public static final String JPG = "jpg";
	public static final String PNG = "png";
	public static final String GIF = "gif";
	public static final String DOC = "doc";
	public static final String WORD_DOCUMENT = "word";
	public static final String PDF = "pdf";
	public static final String UPLOAD_CHARSET = "iso8859-1";
	private static final int MAX_ALLOWED_PREVIEW_BYTES_SIZE = 512 * 1024;

	@Autowired
	private DocumentContentService documentContentBean;

	@Autowired
	private RepresentationFormatBean representationFormatBean;

	@Autowired
	private ImageManipulationBean imageManipulationBean;

	@RequestMapping(value = "/upload", headers = "Accept=*/*;charset=UTF-8", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@Transactional
	@ResponseBody
	public String uploadCommonContent(@RequestBody MultipartFile file) throws IOException {
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
				try {
					documentContent.setBytes(file.getBytes());
				} catch (IOException ex) {
					throw ex;
				}
				documentContentBean.save(documentContent);
				ObjectMapper mapper = new ObjectMapper();
				try {
					DocumentContent clone = documentContent.clone();
					clone.setFileName(file.getOriginalFilename());
					return new String(mapper.writeValueAsString(clone).getBytes(), UPLOAD_CHARSET);
				} catch (JsonProcessingException ex) {
					throw ex;
				}
			default:
				throw new IllegalArgumentException("MORE_THAN_ONE_REPRESENTATION_FORMATS_FOUND: " + contentType);
		}
	}

	private byte[] previewImage(byte[] bytes) throws IOException {
		if (bytes.length <= MAX_ALLOWED_PREVIEW_BYTES_SIZE) {
			return imageManipulationBean.createThumbnailFromImage(bytes);
		}
		return imageManipulationBean.createDocThumbnail();
	}

	@RequestMapping(value = "/{contentId}/preview")
	@Transactional
	public ResponseEntity<byte[]> preview(@PathVariable("contentId") Long contentId) throws IOException {
		DocumentContent documentContent = documentContentBean.load(contentId);
		Set<String> formats = documentContent.getRepresentationFormat().getFormats();
		MediaType defaultFormat = MediaType.IMAGE_PNG;
		byte[] previewBytes = null;
		for (String format : formats) {
			String format2 = format.toLowerCase();
			if (format2.contains(JPEG) || format2.contains(JPG)) {
				defaultFormat = MediaType.IMAGE_JPEG;
				previewBytes = previewImage(documentContent.getBytes());
				break;
			} else if (format2.contains(PNG)) {
				defaultFormat = MediaType.IMAGE_PNG;
				previewBytes = previewImage(documentContent.getBytes());
				break;
			} else if (format2.contains(GIF)) {
				defaultFormat = MediaType.IMAGE_GIF;
				previewBytes = previewImage(documentContent.getBytes());
				break;
			} else if (format2.contains(DOC) || format2.contains(WORD_DOCUMENT)) {
				defaultFormat = MediaType.IMAGE_PNG;
				// previewBytes = imageManipulationBean.createThumbnailFromDoc(documentContent.getBytes());
				previewBytes = imageManipulationBean.createDocThumbnail();
				break;
			} else if (format2.contains(PDF)) {
				defaultFormat = MediaType.IMAGE_PNG;
				previewBytes = imageManipulationBean.createDocThumbnail();
				break;
			}
		}
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(defaultFormat);
		return new ResponseEntity<>(previewBytes, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{contentId}/download")
	@Transactional
	public ResponseEntity<byte[]> download(@PathVariable("contentId") Long contentId) {
		DocumentContent documentContent = documentContentBean.load(contentId);
		Set<String> formats = documentContent.getRepresentationFormat().getFormats();
		MediaType defaultFormat = MediaType.IMAGE_PNG;
		for (String format : formats) {
			String format2 = format.toLowerCase();
			if (format2.contains(JPEG) || format2.contains(JPG)) {
				defaultFormat = MediaType.IMAGE_JPEG;
				break;
			} else if (format2.contains(PNG)) {
				defaultFormat = MediaType.IMAGE_PNG;
				break;
			} else if (format2.contains(GIF)) {
				defaultFormat = MediaType.IMAGE_GIF;
				break;
			} else if (format2.contains(DOC) || format2.contains(WORD_DOCUMENT)) {
				defaultFormat = MediaType.APPLICATION_OCTET_STREAM;
				break;
			} else if (format2.contains(PDF)) {
				defaultFormat = MediaType.APPLICATION_OCTET_STREAM;
				break;
			}
		}
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(defaultFormat);
		return new ResponseEntity<>(documentContent.getBytes(), headers, HttpStatus.CREATED);
	}
}
