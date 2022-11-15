package mipl.icmodule.search;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mipl.icmodule.client.Pro;
import mipl.icmodule.dto.PrepareIc;

@Controller
@RequestMapping(value = "/master/pro")
public class SearchProController {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/search")
	public String checkinSearch(
			@PageableDefault(size = 1000) Pageable pageable,
			@RequestParam(defaultValue = "") String pro, 
			Model model) {
		
			boolean and = false;
			String SQL = "SELECT * FROM v_pro";

/*====================================================================================================================*/
			
			if (pro !=null && pro.length() > 0) {
			if (!and) {
			SQL += " WHERE ";
			and = true;

			} else {
			SQL += "AND";
			and = true;
			}
			SQL += " pro_id IN(" + pro + ")";
			}

/*==================================================================================================================*/
			
			SQL = SQL + " Order By pro_name ASC";
			Query q = em.createNativeQuery(SQL, Pro.class);
			List<Pro> checkinList = q.getResultList();
			Page<Pro> PageObj = new PageImpl<>(checkinList, pageable, checkinList.size());
			model.addAttribute("page", PageObj);
			
			return "ProMaster";
			}

/*==================================================================================================================*/	
}
