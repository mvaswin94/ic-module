package mipl.icmodule.ic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import mipl.icmodule.bill.BillRepository;
import mipl.icmodule.bill.Investigations;
import mipl.icmodule.bill.InvestigationsRepository;
import mipl.icmodule.cover.Cover;
import mipl.icmodule.cover.CoverIc;
import mipl.icmodule.cover.CoverIcRepository;
import mipl.icmodule.cover.CoverRepository;
import mipl.icmodule.dto.PrepareIc;
import mipl.icmodule.dto.PrepareIcRepository;
import mipl.icmodule.log.DispatchLog;
import mipl.icmodule.log.DispatchLogRepository;
import mipl.icmodule.user.UsersRepository;

@Controller
@RequestMapping(value = "/ic")
public class IcController {

	@Autowired
	IcRepository repo;

	@Autowired
	CoverRepository CoverRepo;

	@Autowired
	CoverIcRepository CoverIcRepo;

	@Autowired
	DispatchLogRepository DispatchLogRepo;

	@Autowired
	BillRepository BillRepo;

	@Autowired
	PrepareIcRepository PrepareIcRepo;

	@Autowired
	UsersRepository Userrepo;

	@Autowired
	InvestigationsRepository InvestigationsRepo;

	/*-----------------------------------------------------------------------------------------------------------------	*/

	@GetMapping(value = "")
	public String Ic() {
		return "Ic";
		}
	
	
	 @GetMapping(value = "/prepare") 
	 public String prepareIcGet(Model model,@PageableDefault(size = 1000) Pageable pageable) {
	  
	  Page<PrepareIc> obj = PrepareIcRepo.findAllPrepare(pageable);
	  model.addAttribute("page", obj);
	 
	 return "PrepareIc"; }

	 	
	@GetMapping(value = "/allocate")
	public String allocateIcGet(Model model, @PageableDefault(size = 1000) Pageable pageable) {
		// List<PrepareIc> obj = PrepareIcRepo.findAllAllocate();
		Page<PrepareIc> obj = PrepareIcRepo.findAllAllocate(pageable);
		model.addAttribute("page", obj);
		return "AllocateIc";
	}

	@GetMapping(value = "/approve")
	public String approveIcGet(Model model, @PageableDefault(size = 1000) Pageable pageable) {
		/* List<PrepareIc> obj = PrepareIcRepo.findAllApprove(); */
		Page<PrepareIc> obj = PrepareIcRepo.findAllApprove(pageable);
		model.addAttribute("page", obj);
		return "ApproveIc";
	}

	/*---------------------------------------------------------------------------------------------------------------------------------------------	*/

	@GetMapping(value = "/dispatch")
	public String dispatchIcGet(Model model, @PageableDefault(size = 1000) Pageable pageable) {
		/* List<Cover> obj = CoverRepo.findAll(); */
		Page<Cover> obj = CoverRepo.findAll(pageable);
		model.addAttribute("page", obj);

		return "DispatchIc";
	}

	@PostMapping(value = "/dispatch/send")
	public String dispatchIcSave(@ModelAttribute DispatchLog dispatch) {
		DispatchLog obj = new DispatchLog();
		obj.setCoverId(dispatch.getCoverId());
		obj.setType("Dispatch");
		obj.setDate(dispatch.getDate());
		obj.setProId(dispatch.getProId());
		obj.setCreatedBy("1");
		DispatchLogRepo.save(obj);

		Cover obj2 = CoverRepo.findByCoverId(dispatch.getCoverId());
		obj2.setStatus((byte) 7);
		CoverRepo.save(obj2);

		CoverIc obj3 = CoverIcRepo.findByCoverId(dispatch.getCoverId());
		Ic obj4 = repo.findByIcId(obj3);
		obj4.setIcStatus((byte) 7);
		repo.save(obj4);

		return "redirect:/ic/dispatch";
	}

	@PostMapping(value = "/dispatch/return")
	public String returnIc(@ModelAttribute DispatchLog dispatch) {
		DispatchLog obj = new DispatchLog();
		obj.setCoverId(dispatch.getCoverId());
		obj.setType("Return");
		obj.setDate(dispatch.getDate());
		obj.setProId(dispatch.getProId());
		obj.setCreatedBy("1");
		DispatchLogRepo.save(obj);

		Cover obj2 = CoverRepo.findByCoverId(dispatch.getCoverId());
		obj2.setStatus((byte) 8);
		CoverRepo.save(obj2);

		CoverIc obj3 = CoverIcRepo.findByCoverId(dispatch.getCoverId());
		Ic obj4 = repo.findByIcId(obj3);
		obj4.setIcStatus((byte) 8);
		repo.save(obj4);

		return "redirect:/ic/dispatch";
	}

	@PostMapping(value = "/dispatch/success")
	public String successIc(@ModelAttribute DispatchLog dispatch) {
		DispatchLog obj = new DispatchLog();
		obj.setCoverId(dispatch.getCoverId());
		obj.setType("Completed");
		obj.setDate(dispatch.getDate());
		obj.setProId(dispatch.getProId());
		obj.setCreatedBy("1");
		DispatchLogRepo.save(obj);

		Cover obj2 = CoverRepo.findByCoverId(dispatch.getCoverId());
		obj2.setStatus((byte) 9);
		CoverRepo.save(obj2);

		CoverIc obj3 = CoverIcRepo.findByCoverId(dispatch.getCoverId());
		Ic obj4 = repo.findByIcId(obj3);
		obj4.setIcStatus((byte) 9);
		repo.save(obj4);

		return "redirect:/ic/dispatch";
	}

	@PostMapping(value = "/dispatch/redispatch")
	public String redispatchIc(@ModelAttribute DispatchLog dispatch) {
		DispatchLog obj = new DispatchLog();
		obj.setCoverId(dispatch.getCoverId());
		obj.setType("Re-Dispatch");
		obj.setDate(dispatch.getDate());
		obj.setProId(dispatch.getProId());
		obj.setCreatedBy("1");
		DispatchLogRepo.save(obj);

		Cover obj2 = CoverRepo.findByCoverId(dispatch.getCoverId());
		obj2.setStatus((byte) 7);
		CoverRepo.save(obj2);

		CoverIc obj3 = CoverIcRepo.findByCoverId(dispatch.getCoverId());
		Ic obj4 = repo.findByIcId(obj3);
		obj4.setIcStatus((byte) 7);
		repo.save(obj4);

		return "redirect:/ic/dispatch";
	}

	/*------------------------------------------------------------------------------------------------------------------------------------------------	*/

	@GetMapping(value = "/all")
	public String allIcGet(Model model) {

		List<PrepareIc> obj = PrepareIcRepo.findAll();
		model.addAttribute("all", obj);

		return "AllIc";
	}

	@PostMapping(value = "/all")
	public String allIcSave(Model model) {

		return "AllIc";
	}

	/*------------------------------------------------------------------------------------------------------------------------------------------------	*/

	@PostMapping(value = "/{keyword}")
	public String ic(@PathVariable String keyword, @RequestBody ArrayList<PrepareIc> items) {

		if (keyword.equals("prepare")) {
			for (int i = 0; i < items.size(); i++) {
				PrepareIc objLoop = items.get(i);

				Ic obj = new Ic();
				obj.setIbid(objLoop.getIbid());
				obj.setBillNo(objLoop.getBillNo());
				obj.setInvestigationId(objLoop.getInvestigationId());
				obj.setCalculatedAmount(objLoop.getCalculatedAmount());
				obj.setPreparedAmount(objLoop.getCalculatedAmount());
				obj.setPreparedBy("1");
				obj.setIcStatus((byte) 1);
				obj.getPreparedDate();
				obj.getCreatedOn();
				obj.setHold((byte) 0);
				repo.save(obj);

				Investigations invest = InvestigationsRepo.findByIbid(objLoop.getIbid());
				invest.setIcAmount(objLoop.getCalculatedAmount());
				invest.setIcStatus((byte) 1);
				InvestigationsRepo.save(invest);
			}
			return "redirect:/ic/prepare";
		}

		else if (keyword.equals("notprepare")) {

			for (int i = 0; i < items.size(); i++) {
				PrepareIc objLoop = items.get(i);

				Ic obj = new Ic();
				obj.setIbid(objLoop.getIbid());
				obj.setBillNo(objLoop.getBillNo());
				obj.setInvestigationId(objLoop.getInvestigationId());
				obj.setCalculatedAmount(objLoop.getCalculatedAmount());
				obj.setPreparedAmount(objLoop.getCalculatedAmount());
				obj.setPreparedBy("1");
				obj.setIcStatus((byte) 2);
				obj.getPreparedDate();
				obj.getCreatedOn();
				obj.setHold((byte) 0);
				repo.save(obj);

				Investigations invest = InvestigationsRepo.findByIbid(objLoop.getIbid());
				invest.setIcAmount(objLoop.getCalculatedAmount());
				invest.setIcStatus((byte) 2);
				InvestigationsRepo.save(invest);
			}
			return "redirect:/ic/prepare";
		}

		/*------------------------------------------------------------------------------------------------------------*/
		else if (keyword.equals("allocate")) {

			for (int i = 0; i < items.size(); i++) {
				PrepareIc objLoop = items.get(i);

				Ic obj = repo.findByIcId(objLoop.getIcId());
				obj.setAllocatedAmount(objLoop.getPreparedAmount());
				obj.setAllocatedBy("1");
				obj.setIcStatus((byte) 3);
				obj.getAllocatedDate();
				obj.setHold((byte) 0);
				repo.save(obj);

				Investigations invest = InvestigationsRepo.findByIbid(objLoop.getIbid());
				invest.setIcAmount(objLoop.getPreparedAmount());
				invest.setIcStatus((byte) 3);
				InvestigationsRepo.save(invest);
			}

			return "redirect:/ic/allocate";

		}

		else if (keyword.equals("allocatecancel")) {

			for (int i = 0; i < items.size(); i++) {
				PrepareIc objLoop = items.get(i);

				Ic obj = repo.findByibid(objLoop.getIbid());
				obj.setAllocatedBy("1");
				obj.setIcStatus((byte) 4);
				obj.getAllocatedDate();
				obj.setHold((byte) 0);
				repo.save(obj);

				Investigations invest = InvestigationsRepo.findByIbid(objLoop.getIbid());
				invest.setIcAmount(objLoop.getPreparedAmount());
				invest.setIcStatus((byte) 4);
				InvestigationsRepo.save(invest);
			}

			return "redirect:/ic/allocate";

		}

		else if (keyword.equals("notallocate")) {

			for (int i = 0; i < items.size(); i++) {
				PrepareIc objLoop = items.get(i);

				Ic obj = repo.findByibid(objLoop.getIbid());
				obj.setAllocatedAmount(objLoop.getPreparedAmount());
				obj.setAllocatedBy("1");
				obj.setIcStatus((byte) 0);
				obj.getAllocatedDate();
				obj.setHold((byte) 0);
				repo.save(obj);

				Investigations invest = InvestigationsRepo.findByIbid(objLoop.getIbid());
				invest.setIcAmount(objLoop.getPreparedAmount());
				invest.setIcStatus((byte) 0);
				InvestigationsRepo.save(invest);

			}

			return "redirect:/ic/allocate";

		}
		/*------------------------------------------------------------------------------------------------------------*/

		if (keyword.equals("approve")) {

			Cover CoverObj = new Cover();
			CoverObj.setPreparedBy("2");
			CoverObj.setStatus((byte) 5);

			for (int i = 0; i <= 0; i++) {
				PrepareIc objLoop = items.get(i);

				CoverObj.setProId(objLoop.getProId());
				CoverObj.setProName(objLoop.getProName());
				CoverObj.setClientId(objLoop.getClientId());
				CoverObj.setClientName(objLoop.getClientName());
				CoverRepo.save(CoverObj);

				
				//List<Ic> obj1 = repo.findAll();
						
				for (int j = 0; j < items.size(); j++) {
					PrepareIc objLoop2 = items.get(j);

					Ic obj1 = repo.findByIcId(objLoop2.getIcId());
					obj1.setApprovedAmount(objLoop2.getAllocatedAmount());
					obj1.setApprovedBy("2");
					obj1.setIcStatus((byte) 5);
					obj1.getApprovedDate();
					obj1.setHold((byte) 0);
					repo.save(obj1);

					Investigations invest = InvestigationsRepo.findByIbid(objLoop.getIbid());
					invest.setIcAmount(objLoop.getAllocatedAmount());
					invest.setIcStatus((byte) 5);
					InvestigationsRepo.save(invest);

					CoverIc obj4 = new CoverIc();
					/* obj4.setId(obj4.getId()); */
					obj4.setIcId(objLoop2.getIcId());
					obj4.setCoverId(CoverObj.getCoverId());
					
					CoverIcRepo.save(obj4);
				}
			}
 			return "redirect:/ic/approve";

		}

		else if (keyword.equals("approvecancel")) {

			for (int i = 0; i < items.size(); i++) {
				PrepareIc objLoop = items.get(i);

				Ic obj = repo.findByibid(objLoop.getIbid());
				obj.setApprovedAmount(objLoop.getAllocatedAmount());
				obj.setApprovedBy("2");
				obj.setIcStatus((byte) 6);
				obj.getApprovedDate();
				obj.setHold((byte) 0);
				repo.save(obj);

				Investigations invest = InvestigationsRepo.findByIbid(objLoop.getIbid());
				invest.setIcAmount(objLoop.getAllocatedAmount());
				invest.setIcStatus((byte) 6);
				InvestigationsRepo.save(invest);
			}
			return "redirect:/ic/approve";

		}

		else if (keyword.equals("notapprove")) {

			for (int i = 0; i < items.size(); i++) {
				PrepareIc objLoop = items.get(i);

				Ic obj = repo.findByibid(objLoop.getIbid());
				obj.setApprovedAmount(objLoop.getAllocatedAmount());
				obj.setApprovedBy("2");
				obj.setIcStatus((byte) 1);
				obj.getApprovedDate();
				obj.setHold((byte) 0);
				repo.save(obj);

				Investigations invest = InvestigationsRepo.findByIbid(objLoop.getIbid());
				invest.setIcAmount(objLoop.getAllocatedAmount());
				invest.setIcStatus((byte) 1);
				InvestigationsRepo.save(invest);
			}
			return "redirect:/ic/approve";

		}

		return "redirect:/ic/prepare";
	}

	/*------------------------------------------------------------------------------------------------------------*/
}
