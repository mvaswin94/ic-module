package mipl.icmodule.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mipl.icmodule.bill.BillRepository;
import mipl.icmodule.client.ClientRepository;
import mipl.icmodule.dto.PrepareIc;
import mipl.icmodule.dto.PrepareIcRepository;
import mipl.icmodule.ic.IcRepository;
import mipl.icmodule.user.UsersRepository;

@Controller
@RequestMapping(value = "/report")
public class ReportController {


	@Autowired
	ClientRepository Clientrepo;

	@Autowired
	IcRepository repo;
	
	@Autowired
	BillRepository BIllRepo;
	
	@Autowired
	PrepareIcRepository PrepareIcRepo;
	
	@Autowired
	UsersRepository Userrepo;

	@Autowired
	IcRepository Icrepo;

	@GetMapping(value = "/eligibleic")
	public String eligibleIc(Model model, @PageableDefault(size = 50) Pageable pageable) {
		
		//List<PrepareIc> obj = PrepareIcRepo.findAllPrepare();
		Page<PrepareIc> obj = PrepareIcRepo.findAllPrepare(pageable);
		model.addAttribute("page", obj);

		return "EligibleIcReport";
	}

	@GetMapping(value = "/noneligibleic") 
	public String nonEligibleIc(Model model, @PageableDefault(size = 50) Pageable pageable) {

		//List<PrepareIc> obj = PrepareIcRepo.findAllNonEligibleIc();
		Page<PrepareIc> obj = PrepareIcRepo.findAllNonEligibleIc(pageable);
		model.addAttribute("page", obj);

		return "NonEligibleIcReport";
	}

	@GetMapping(value = "/noic")
	public String noIc(Model model, @PageableDefault(size = 50) Pageable pageable) {

		//List<PrepareIc> obj = PrepareIcRepo.findAllNoIc();
		Page<PrepareIc> obj = PrepareIcRepo.findAllNoIc(pageable);
		model.addAttribute("page", obj);
		
		return "NoIcReport";
	}

	@GetMapping(value = "/unassignedpro")
	public String unassignedPro(Model model, @PageableDefault(size = 50) Pageable pageable) { 

		//List<PrepareIc> obj = PrepareIcRepo.findAllUnAssignedPro();
		Page<PrepareIc> obj = PrepareIcRepo.findAllUnAssignedPro(pageable);
	 	model.addAttribute("page", obj);

		return "UnassignedProReport";
	}

	@GetMapping(value = "/icstages")
	public String icStages(Model model, @PageableDefault(size = 50) Pageable pageable) {
		
		//List<PrepareIc> obj = PrepareIcRepo.findAllPrepare();
		Page<PrepareIc> obj = PrepareIcRepo.findAllIcStages(pageable);
		model.addAttribute("page", obj);

		return "IcStages";
	}
	
	@GetMapping(value = "/denomination")
	public String denominationIc(Model model, @PageableDefault(size = 50) Pageable pageable) {

		return "DenominationIC";
	}

	@GetMapping(value = "/notprepared")
	public String notPreparedIc(Model model, @PageableDefault(size = 50) Pageable pageable) {
		
		//List<PrepareIc> obj = PrepareIcRepo.findAllNotPreparedIc();
		Page<PrepareIc> obj = PrepareIcRepo.findAllNotPreparedIc(pageable);
	 	model.addAttribute("page", obj);
		
		return "NotPreparedIc";
	}

	@GetMapping(value = "/notallocated")
	public String notAllocatedIc(Model model, @PageableDefault(size = 50) Pageable pageable) {

		//List<PrepareIc> obj = PrepareIcRepo.findAllNotAllocatedIc();
		Page<PrepareIc> obj = PrepareIcRepo.findAllNotAllocatedIc(pageable);
	 	model.addAttribute("page", obj);
	 	
		return "NotAllocatedIc";
	}

	@GetMapping(value = "/notapproved")
	public String notApprovedIc(Model model, @PageableDefault(size = 50) Pageable pageable) {
		
		//List<PrepareIc> obj = PrepareIcRepo.findAllNotApprovedIc();
		Page<PrepareIc> obj = PrepareIcRepo.findAllNotApprovedIc(pageable);
	 	model.addAttribute("page", obj);
	 	
		return "NotApprovedIc";
	}

	@GetMapping(value = "/completed")
	public String completedIc(Model model, @PageableDefault(size = 50) Pageable pageable) {

		//List<PrepareIc> obj = PrepareIcRepo.findAllCompletedIc();
		Page<PrepareIc> obj = PrepareIcRepo.findAllCompletedIc(pageable);
	 	model.addAttribute("page", obj);
	 	
		return "CompletedIcReport";
	}
	
/*	@GetMapping(value = "/cover")
	public String coverIc(Model model, @PageableDefault(size = 50) Pageable pageable) {

		List<PrepareIc> obj = PrepareIcRepo.findAllCompletedIc();
	 	model.addAttribute("coverIc", obj);
	 	
		return "fragment/DispatchIcReport";
	}*/

}
