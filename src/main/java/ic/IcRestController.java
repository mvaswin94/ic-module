package mipl.icmodule.ic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mipl.icmodule.dto.PrepareIc;
import mipl.icmodule.dto.PrepareIcRepository;

@RestController
@RequestMapping(value = "/ic")
public class IcRestController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	PrepareIcRepository PrepareIcRepo;
	
	@GetMapping(value = "/cover/{keyword}")   
	public List<PrepareIc> getIcList(@PathVariable("keyword") Long keyword)  {
			List<PrepareIc> obj = PrepareIcRepo.findAllByCoverId(keyword);
		return obj;
	}
	
}
