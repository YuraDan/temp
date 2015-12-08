package ru.sovzond.mgis2.web.data_exchange.rusregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ru.sovzond.mgis2.integration.data_exchange.imp.impl.KptLandsImporter;
import ru.sovzond.mgis2.integration.data_exchange.imp.impl.RegionCadastrLandsImporter;
import ru.sovzond.mgis2.web.data_exchange.imp.UploadControllerBase;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 17.11.15.
 */
@Controller
public class LandsImportController extends UploadControllerBase {

	@Autowired
	private RegionCadastrLandsImporter landsImporter;

	@Autowired
	private KptLandsImporter kptLandsImporter;

	@RequestMapping(value = "/data-exchange/import/lands/rusregister/regcad", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public String rusRegisterRegionCadastr(@RequestParam(FLOW_CHUNK_NUMBER) int flowChunkNumber, //
										   @RequestParam(FLOW_CHUNK_SIZE) int flowChunkSize, //
										   @RequestParam(FLOW_TOTAL_SIZE) long flowTotalSize, //
										   @RequestParam(FLOW_IDENTIFIER) String flowIdentifier, //
										   @RequestParam(FLOW_FILENAME) String flowFileName, //
										   @RequestParam(FLOW_RELATIVE_PATH) String flowRelativePath, //
										   @RequestParam(FLOW_FILE) MultipartFile file //
	) {
		return doUploadChunk(flowChunkNumber, flowChunkSize, flowTotalSize, flowIdentifier, flowFileName, flowRelativePath, file, landsImporter);
	}

	@RequestMapping(value = "/data-exchange/import/lands/rusregister/regcad", method = RequestMethod.GET, consumes = "text/plain", produces = "text/plain")
	protected ResponseEntity<String> rusRegisterRegionCadastr(@RequestParam(FLOW_CHUNK_NUMBER) int flowChunkNumber, //
															  @RequestParam(FLOW_IDENTIFIER) String flowIdentifier //
	) {
		return doCheckCunk(flowChunkNumber, flowIdentifier);
	}

	@RequestMapping(value = "/data-exchange/import/lands/rusregister/kpt", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public String rusRegisterKPT(@RequestParam(FLOW_CHUNK_NUMBER) int flowChunkNumber, //
								 @RequestParam(FLOW_CHUNK_SIZE) int flowChunkSize, //
								 @RequestParam(FLOW_TOTAL_SIZE) long flowTotalSize, //
								 @RequestParam(FLOW_IDENTIFIER) String flowIdentifier, //
								 @RequestParam(FLOW_FILENAME) String flowFileName, //
								 @RequestParam(FLOW_RELATIVE_PATH) String flowRelativePath, //
								 @RequestParam(FLOW_FILE) MultipartFile file //
	) {
		return doUploadChunk(flowChunkNumber, flowChunkSize, flowTotalSize, flowIdentifier, flowFileName, flowRelativePath, file, kptLandsImporter);
	}

	@RequestMapping(value = "/data-exchange/import/lands/rusregister/kpt", method = RequestMethod.GET, consumes = "text/plain", produces = "text/plain")
	protected ResponseEntity<String> rusRegisterKPT(@RequestParam(FLOW_CHUNK_NUMBER) int flowChunkNumber, //
													@RequestParam(FLOW_IDENTIFIER) String flowIdentifier //
	) {
		return doCheckCunk(flowChunkNumber, flowIdentifier);
	}

}