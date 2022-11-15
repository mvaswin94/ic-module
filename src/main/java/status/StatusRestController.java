package mipl.icmodule.status;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mipl.icmodule.bill.BillRepository;
import mipl.icmodule.client.ClientRepository;
import mipl.icmodule.ic.IcRepository;

@RestController
@RequestMapping(value = "/status")
public class StatusRestController {
	
	@Autowired
	BillRepository BillRepo;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	IcRepository repo;
	
	@Autowired
	ClientRepository ClientRepo;
	
/*============================================================================================================*/

	@GetMapping(value = "/pro/{keyword}")
	public List<Map<String,Object>> getProBasedStatus(@PathVariable("keyword") String keyword)  {
			return jdbcTemplate.queryForList("SELECT * FROM v_ic WHERE pro_name LIKE '" +keyword+ "%'");
	}
	
/*============================================================================================================*/

}	