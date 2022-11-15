package mipl.icmodule.status;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mipl.icmodule.dto.PrepareIc;
import mipl.icmodule.dto.PrepareIcRepository;

@Controller
@RequestMapping(value = "/ic")
public class StatusIcController {

	@Autowired
	PrepareIcRepository repo;

	
	@GetMapping(value = "/status")
    public String statusIcGet(Model model) {

		List<PrepareIc> obj = repo.findAllIc();
		model.addAttribute("all", obj);
		
		/*model.addAttribute("all",repo.findAll());*/
		
		/*Page<PrepareIc> page = repo.findAll(pageable);	
		model.addAttribute("all", page);*/
		
		return "AllIc";	
    }
	
}
