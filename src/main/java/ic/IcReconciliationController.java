package mipl.icmodule.ic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import mipl.icmodule.bill.Investigations;
import mipl.icmodule.bill.InvestigationsRepository;
import mipl.icmodule.dto.PrepareIc;
import mipl.icmodule.dto.PrepareIcRepository;

@Controller
@RequestMapping(value = "/ic")
public class IcReconciliationController {
	
	@Autowired
	PrepareIcRepository PrepareIcRepo;
	
	@Autowired
	InvestigationsRepository InvestigationsRepo;
	
	@GetMapping(value = "/noneligibleic")
	public String eligibleIcGet(Model model, @PageableDefault(size = 50) Pageable pageable) {
		Page<PrepareIc> obj = PrepareIcRepo.findAllReconciliationIc(pageable);
		model.addAttribute("page", obj);
		return "ReconciliationIc";
	}
	
	@PostMapping(value = "/noneligibleic/iseligible")
	public String iseligibleIcPost(@RequestBody ArrayList<PrepareIc> items) {

		
		for (int i = 0; i < items.size(); i++) {
			PrepareIc objLoop = items.get(i);
			
			Investigations investObj = InvestigationsRepo.findByIbid(objLoop.getIbid());
			investObj.setIseligibleIc((byte) 0); 
			InvestigationsRepo.save(investObj);

		}
		return "redirect:/ic/noneligibleic";
	}

}
