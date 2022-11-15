package mipl.icmodule.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ic")
public class TokenRestController {

	@Autowired
	IntegrationService intnService;

	@GetMapping("/sync/client")
	public void syncClient() {
		intnService.syncClients();
	}

	@GetMapping("/sync/bills")
	public void syncBills() {
		intnService.syncBills();
	}
	
	@GetMapping("/sync/bills/time")
	public void syncBillsAutoTrigger() {
		intnService.syncBillsAutoTrigger();
	}

}