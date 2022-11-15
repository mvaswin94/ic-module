package mipl.icmodule.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import mipl.icmodule.client.ClientRepository;
import mipl.icmodule.dto.PrepareIcRepository;

@Controller
public class TokenController {
	
	@Autowired
	TokenRepository repo;
	
	@Autowired
	PrepareIcRepository PrepareIcRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ClientRepository ClientRepo;
	
/*============================================================================================================*/

	/*@RequestMapping(value = "/tokenview", method = RequestMethod.GET)
    public String getTest(Model model) {
		
		 model.addAttribute("tokenObj", repo.findAll());
		 model.addAttribute("tenentObj", RatecardRepo.findAll());
	        
	 return "test/Sample";    
 }	*/
	
/*============================================================================================================*/

	@RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getTestSample(Model model) {
		//List<PrepareIc> obj = PrepareIcRepo.findAllPrepare();
		/*List<Ic> obj = repo.findAll();*/
		//model.addAttribute("prepare", obj);
	 return "test/test";    
 }	
	
	
	
	/*@RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model, BillInvest bill) {
		List<BillInvest> obj = BillInvestRepo.findAll();
		for (int i = 0; i < obj.size(); i++) {
			BillInvest	obj2 = obj.get(i);
			obj2.setIcStatus((byte) 0);
		BillInvestRepo.saveAll(obj);
		}
	 return "test/test";    
 }	*/
	
	
/*============================================================================================================*/
	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.GET) public String
	 * login(Model model) {
	 * 
	 * return "Login"; }
	 */
	
/*============================================================================================================*/

	@RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2() {
		
	 return "test/test2";    
 }

/*============================================================================================================*/
	
}
