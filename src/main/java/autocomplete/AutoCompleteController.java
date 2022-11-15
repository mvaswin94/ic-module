package mipl.icmodule.autocomplete;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mipl.icmodule.bill.Bill;
import mipl.icmodule.bill.BillRepository;
import mipl.icmodule.client.Client;
import mipl.icmodule.client.ClientRepository;
import mipl.icmodule.ic.IcRepository;

@RestController
@RequestMapping(value = "/autocomplete")
public class AutoCompleteController {
	
	@Autowired
	BillRepository BillRepo;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	IcRepository repo;
	
	@Autowired
	ClientRepository ClientRepo;

	
/*============================================================================================================*/
	
	@GetMapping(value = "/client")
	public List<Map<String,Object>> getClientName()  {
			return jdbcTemplate.queryForList("SELECT DISTINCT client_id,client_name FROM v_client WHERE client_name !='.' AND client_name !='0' ORDER BY client_name ASC");
	}
	
/*============================================================================================================*/
	
	@GetMapping(value = "/billclient")
	public List<Map<String,Object>> getBillClientName()  {
			return jdbcTemplate.queryForList("SELECT DISTINCT client_id,client_name FROM v_ic WHERE client_name !='.' AND client_name !='0' ORDER BY client_name ASC");
	}	
	
/*============================================================================================================*/
	
	@GetMapping(value = "/client/{searchTerms}")
	public List<Map<String,Object>> getClientNameKeyword(@PathVariable("searchTerms") String searchTerms)  {
			return jdbcTemplate.queryForList("SELECT DISTINCT client_id,client_name FROM v_client WHERE client_name LIKE '" +searchTerms+ "%' ORDER BY client_name ASC");
	}
	
/*============================================================================================================*/
	
	@GetMapping(value = "/clienttype/{searchTerms}")
	public List<Map<String,Object>> getClientTypeKeyword(@PathVariable("searchTerms") String searchTerms)  {
			return jdbcTemplate.queryForList("SELECT DISTINCT client_type FROM v_client_type WHERE client_type LIKE '" +searchTerms+ "%' ORDER BY client_type ASC");
	}	
	
/*============================================================================================================*/
	
	@GetMapping(value = "/pro/{searchTerms}")
	public List<Map<String,Object>> getProNameKeyword(@PathVariable("searchTerms") String searchTerms)  {
			return jdbcTemplate.queryForList("SELECT DISTINCT pro_id,pro_name FROM v_pro WHERE pro_name LIKE '" +searchTerms+ "%' ORDER BY pro_name ASC");
	}	
/*============================================================================================================*/
	
	@GetMapping(value = "/ic/{searchTerms}")
	public List<Map<String, Object>> getClientIc(@PathVariable("searchTerms") String searchTerms) {
		return jdbcTemplate.queryForList("SELECT DISTINCT ic FROM v_client_ic WHERE ic LIKE '" +searchTerms+ "%' ORDER BY ic ASC");
	}
	
/*============================================================================================================*/
	
	@GetMapping(value = "/cycledays/{searchTerms}")
	public List<Map<String,Object>> getCycleDays(@PathVariable("searchTerms") String searchTerms)  {
			return jdbcTemplate.queryForList("SELECT DISTINCT cycle_days FROM client WHERE cycle_days LIKE '" +searchTerms+ "%' ORDER BY cycle_days ASC");
	}
/*============================================================================================================*/
	
	@GetMapping(value = "/billcycledays")
	public List<Map<String, Object>> getBillCycleDays(HttpServletRequest request) {
		return jdbcTemplate.queryForList("SELECT DISTINCT cycle_days FROM v_ic WHERE cycle_days !=''");
	}
	
/*============================================================================================================*/
	
	@GetMapping(value = "/clienttype")
	public List<Map<String,Object>> getClientType(HttpServletRequest request) {
		return jdbcTemplate.queryForList("SELECT DISTINCT client_type FROM client");
	}

/*============================================================================================================*/
	
	@GetMapping(value = "/billclienttype")
	public List<Map<String,Object>> getBillClientType(HttpServletRequest request) {
		return jdbcTemplate.queryForList("SELECT DISTINCT client_type FROM v_client_type");
	}
	
/*============================================================================================================*/
	
	@GetMapping(value = "/paymentmode/{searchTerms}")
	public List<Map<String, Object>> getPaymentMode(@PathVariable("searchTerms") String searchTerms) {
		return jdbcTemplate.queryForList("SELECT DISTINCT payment_mode FROM v_payment_mode WHERE payment_mode LIKE '" +searchTerms+ "%' ORDER BY payment_mode");
	}	
	
/*============================================================================================================*/
	
	@GetMapping(value = "/proid")
	public List<Bill> getProId(HttpServletRequest request) {
		return BillRepo.findAllProId();
	}	
		
/*=====================================================================================================	=======*/
	
	@GetMapping(value = "/pro")
		public List<Map<String,Object>> getPro()  {
			return jdbcTemplate.queryForList("SELECT DISTINCT pro_id,pro_name FROM bill WHERE pro_id!='' ORDER BY pro_name ASC ");
	}	
	
/*============================================================================================================*/
	
	@GetMapping(value = "/clientpro")
		public List<Map<String,Object>> getClientPro()  {
			return jdbcTemplate.queryForList("SELECT DISTINCT pro FROM client WHERE pro !='' ORDER BY pro ASC");
	}	
	
/*============================================================================================================*/
	/*
	@GetMapping(value = "/pro/{keyword}")
		public List<Map<String,Object>> getProKeyword(@PathVariable("keyword") String keyword)  {
			return jdbcTemplate.queryForList("SELECT pro_id,pro_name FROM bill WHERE pro_name LIKE '" +keyword+ "%'");
	}*/	
	
/*============================================================================================================*/
	
	@GetMapping(value = "/status")
	public List<Client> getStatus(HttpServletRequest request) {
		return ClientRepo.findAllStatus();
	}
	
/*============================================================================================================*/
	
	@GetMapping(value = "/clienttenent")
	public List<Map<String,Object>> getClientTenentName(HttpServletRequest request) {
		return jdbcTemplate.queryForList("SELECT DISTINCT tenent_id,tenent_name FROM client ORDER BY tenent_name ASC");
	}
	
/*============================================================================================================*/
	
	@GetMapping(value = "/tenent/{searchTerms}")
	public List<Map<String,Object>> getTenentName(@PathVariable("searchTerms") String searchTerms)  {
			return jdbcTemplate.queryForList("SELECT DISTINCT tenent_id,tenent_name FROM v_tenent  WHERE tenent_name LIKE '" +searchTerms+ "%' ORDER BY tenent_name ASC");
	}	
	
/*============================================================================================================*/
	
	@GetMapping(value = "/proname/{name}" , produces = MediaType.APPLICATION_JSON_VALUE)   
	@ResponseBody
	public List<Bill> getProName(@PathVariable String name, HttpServletRequest request) {
		return BillRepo.findAllProByName(name);
	}
/*============================================================================================================*/
	
	/*@GetMapping(value = "/clienttype/{name}")   
	public List<Bill> getClienttype(@PathVariable String name, HttpServletRequest request) {
		return BillRepo.findAllClientTypeByName(name);
	}*/
	
/*============================================================================================================*/

	@GetMapping(value = "/billno" , produces = MediaType.APPLICATION_JSON_VALUE)   
	public List<Map<String,Object>> getBillNo(HttpServletRequest request) {
		return jdbcTemplate.queryForList("SELECT DISTINCT bill_no FROM v_ic;");
	}
	
/*============================================================================================================*/

	@GetMapping(value = "/tenentname/{name}" , produces = MediaType.APPLICATION_JSON_VALUE)   
	@ResponseBody
	public List<Bill> getTenentName(@PathVariable String name, HttpServletRequest request) {
		return BillRepo.findAllTenentByName(name);
	}
	
/*============================================================================================================*/

	@GetMapping(value = "/bill")   
	public Iterable<Bill> getAllInBill() {
		return BillRepo.findAll();
		
	}
	
/*============================================================================================================*/

	@GetMapping(value = "/investigation")   
	public List<Map<String,Object>> getInvestigation() {
		return jdbcTemplate.queryForList("SELECT DISTINCT investigation_id,investigation_name FROM v_investigation WHERE investigation_name !='' ORDER BY investigation_name ASC");
	}

/*============================================================================================================*/

	@GetMapping(value = "/investigation/{searchTerms}")   
	public List<Map<String,Object>> getInvestKeyword(@PathVariable("searchTerms") String searchTerms)  {
		return jdbcTemplate.queryForList("SELECT DISTINCT investigation_id,investigation_name FROM v_investigation WHERE investigation_name LIKE '" +searchTerms+ "%' ORDER BY investigation_name ASC");
	}	
	
/*============================================================================================================*/
	
	@GetMapping(value = "/department")   
	public List<Map<String,Object>> getDepartment()  {
		return jdbcTemplate.queryForList("SELECT DISTINCT department_id,department_name FROM v_department WHERE department_name !='' ORDER BY department_name ASC");
	}
	
/*============================================================================================================*/
	
	@GetMapping(value = "/billdepartment")   
	public List<Map<String,Object>> getBillDepartment()  {
		return jdbcTemplate.queryForList("SELECT DISTINCT department_id,department_name FROM v_ic WHERE department_name !='' ORDER BY department_name ASC");
	}	
		
/*============================================================================================================*/

	@GetMapping(value = "/department/{searchTerms}")   
	public List<Map<String,Object>> getDepartKeyword(@PathVariable("searchTerms") String searchTerms)  {
		return jdbcTemplate.queryForList("SELECT DISTINCT department_id,department_name FROM v_department WHERE department_name LIKE '" +searchTerms+ "%' ORDER BY department_name ASC ");
	}
		
/*============================================================================================================*/
	
	@GetMapping(value = "/username/{searchTerms}")   
	public List<Map<String,Object>> getUser(@PathVariable("searchTerms") String searchTerms)  {
		return jdbcTemplate.queryForList("SELECT DISTINCT user_id,user_name FROM user_master WHERE user_name LIKE '" +searchTerms+ "%' ORDER BY user_name ASC");
	}
		
/*============================================================================================================*/

	@GetMapping(value = "/contactnumber/{searchTerms}")   
	public List<Map<String,Object>> getContact(@PathVariable("searchTerms") String searchTerms)  {
		return jdbcTemplate.queryForList("SELECT DISTINCT contact_number FROM user_master WHERE contact_number LIKE '" +searchTerms+ "%' ORDER BY contact_number ASC");
	}
		
/*============================================================================================================*/

	@GetMapping(value = "/email/{searchTerms}")   
	public List<Map<String,Object>> getEmail(@PathVariable("searchTerms") String searchTerms)  {
		return jdbcTemplate.queryForList("SELECT DISTINCT email_address FROM user_master WHERE email_address LIKE '" +searchTerms+ "%' ORDER BY email_address ASC");
	}
		
/*============================================================================================================*/

	@GetMapping(value = "/role/{searchTerms}")   
	public List<Map<String,Object>> getRole(@PathVariable("searchTerms") String searchTerms)  {
		return jdbcTemplate.queryForList("SELECT DISTINCT role FROM user_master WHERE role LIKE '" +searchTerms+ "%' ORDER BY role ASC");
	}
		
/*============================================================================================================*/
	
	@GetMapping(value = "/test/{keyword}")   
	public List<Map<String,Object>> getTest(@PathVariable("keyword") String keyword)  {
		return jdbcTemplate.queryForList("SELECT * FROM cover_ic WHERE cover_id = '" +keyword+ "%'");
	}

/*============================================================================================================*/


	@GetMapping(value = "/test2")   
	public List<Map<String,Object>> getTest2()  {
		return jdbcTemplate.queryForList("SELECT * FROM cover_ic WHERE cover_id ='2'");
	}
}	
