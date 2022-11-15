package mipl.icmodule.client;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import mipl.icmodule.bill.BillRepository;

@Controller
@RequestMapping(value = "/master")
public class ProController {

	@Autowired
	ProRepository ProRepo;
	
	@Autowired
	BillRepository BillRepo;
	 
	@Autowired
	ClientRepository ClientRepo;
	
	@GetMapping(value = "/pro")
	public String proMasterGet(Model model, @PageableDefault(size = 50) Pageable pageable) {

		Page<Pro> page = ProRepo.findAll(pageable);	
		model.addAttribute("page", page);
		return "ProMaster";
	}
	
	@GetMapping(value = "/pro/{id}/client")
	public String proMasterClientListGet(@PathVariable Long id, Model model) {

		new Client();
		Optional<Client> client = ClientRepo.findById(id);
		Client clientObj = client.get();
		model.addAttribute("proClientViewObj", clientObj);
		
		return "ProViewClient";
		
	}
	
}
