package mipl.icmodule.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mipl.icmodule.user.UsersRepository;

@Controller
@RequestMapping(value = "/master")
public class ClientController {
	
	@Autowired
	ClientRepository repo;
	
	@Autowired
	UsersRepository Userrepo;


	@GetMapping(value = "/client")
	public String clientMasterGet(@PageableDefault(size = 1000) Pageable pageable, Model model) {

		Page<Client> page = repo.findAll(pageable);	
		model.addAttribute("page", page);
		return "ClientMaster";
		
	}
	
	@PostMapping(value = "/client/info")
	public String clientMasterInfoPost(@RequestParam Long clientId, @ModelAttribute Client add) {
		new Client();
		Client user = repo.findByClientId(clientId);
		user.setEnagementModel(add.getEnagementModel());
		user.setLocation(add.getLocation());
		user.setIc(add.getIc());
		user.setIcType(add.getIcType());
		user.setPaymentMode(add.getPaymentMode());
		user.setCycleDays(add.getCycleDays());
		user.setSms(add.getSms());
		user.setAgency(addR.getAgency());
		user.setPercentage(add.getPercentage());
		user.setStatus((byte) 0);
		repo.save(user); 
		return "redirect:/master/client";
		
	}
	
	@PostMapping(value = "/client/active")
	public String active(@RequestParam Long clientId, @ModelAttribute Client active) {
		new Client();
		Client user = repo.findByClientId(clientId);
		user.setEffectiveFrom(active.getEffectiveFrom());
		user.setEffectiveTo(active.getEffectiveTo());
		user.setStatus((byte) 1);
		repo.save(user);
		
		return "redirect:/master/client";

	}

	@PostMapping(value = "/client/deactive")
	public String deactive(@RequestParam Long clientId, @ModelAttribute Client deactive) {
		new Client();
		Client user = repo.findByClientId(clientId);
		user.setEffectiveFrom(deactive.getEffectiveFrom());
		user.setEffectiveTo(deactive.getEffectiveTo());
		user.setStatus((byte) 2);
		repo.save(user);
		
		return "redirect:/master/client";

	}
	
}