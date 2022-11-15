package mipl.icmodule.ic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import mipl.icmodule.bill.Investigations;
import mipl.icmodule.bill.InvestigationsRepository;
import mipl.icmodule.dto.PrepareIc;
import mipl.icmodule.dto.PrepareIcRepository;

@Controller
@RequestMapping(value = "/ic")
public class IcHoldController {

	@Autowired
	PrepareIcRepository PrepareIcRepo;

	@Autowired
	IcRepository repo;

	@Autowired
	InvestigationsRepository InvestigationsRepo;

	@GetMapping(value = "/hold")
	public String holdIcGet(Model model, @PageableDefault(size = 50) Pageable pageable) {
		Page<PrepareIc> obj = PrepareIcRepo.findAllHoldIc(pageable);
		model.addAttribute("page", obj);
		return "HoldIc";
	}

	@PostMapping(value = "/unhold")
	public String unHold(@RequestBody ArrayList<PrepareIc> items) {

		
		for (int i = 0; i < items.size(); i++) {
			PrepareIc objLoop = items.get(i);

			if (objLoop.getBillinvestStatus()==0) {
			Investigations investObj = InvestigationsRepo.findByIbid(objLoop.getIbid());
			investObj.setHold((byte) 0);
			InvestigationsRepo.save(investObj);
		}
			else {
				Investigations investObj2 = InvestigationsRepo.findByIbid(objLoop.getIbid());
				investObj2.setHold((byte) 0);
				InvestigationsRepo.save(investObj2);

				Ic icObj = repo.findByibid(objLoop.getIbid());
				icObj.setHold((byte) 0);
				repo.save(icObj);
			}
		}
		return "redirect:/ic/hold";
	}
	
	@PostMapping(value = "/hold/{keyword}")
	public String ic(@PathVariable String keyword, @RequestBody ArrayList<PrepareIc> items) {

		if (keyword.equals("prepare")) {
			for (int i = 0; i < items.size(); i++) {
				PrepareIc objLoop = items.get(i);
				
				Investigations investObj = InvestigationsRepo.findByIbid(objLoop.getIbid());
				investObj.setIcStatus((byte) 0);
				investObj.setHold((byte) 1);
				InvestigationsRepo.save(investObj);
			}
			return "redirect:/ic/prepare";
		}

		else if (keyword.equals("allocate")) {
			for (int i = 0; i < items.size(); i++) {
				PrepareIc objLoop = items.get(i);

				Investigations investObj = InvestigationsRepo.findByIbid(objLoop.getIbid());
				investObj.setHold((byte) 1);
				InvestigationsRepo.save(investObj);

				Ic icObj = repo.findByibid(objLoop.getIbid());
				icObj.setHold((byte) 1);
				repo.save(icObj);
			}
			return "redirect:/ic/allocate";
		}

		else if (keyword.equals("approve")) {
			for (int i = 0; i < items.size(); i++) {
				PrepareIc objLoop = items.get(i);

				Investigations investObj = InvestigationsRepo.findByIbid(objLoop.getIbid());
				investObj.setHold((byte) 1);
				InvestigationsRepo.save(investObj);

				Ic icObj = repo.findByibid(objLoop.getIbid());
				icObj.setHold((byte) 1);
				repo.save(icObj);
			}
			return "redirect:/ic/approve";
		}
		return "HoldIc";
	}

}