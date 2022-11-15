package mipl.icmodule.search;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mipl.icmodule.client.Pro;
import mipl.icmodule.dto.PrepareIc;
import mipl.icmodule.util.MiplUtil;

@Controller
@RequestMapping(value = "/report")
public class SearchReportController {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/search")
	public String prepareSearch(
			@PageableDefault(size = 1000) Pageable pageable,
			@RequestParam(defaultValue = "") String pro, 
			@RequestParam(defaultValue = "") String client, 
			@RequestParam(defaultValue = "") String test, 
			@RequestParam(defaultValue = "") List<String> clientType, 
			@RequestParam(defaultValue = "") String cycleDays,
			@RequestParam(defaultValue = "") String department, 
			@RequestParam(defaultValue = "") String tenent,
			
			@RequestParam(defaultValue = "") @DateTimeFormat(pattern = "dd-MM-yyyy") String fromDate,
			@RequestParam(defaultValue = "") @DateTimeFormat(pattern = "dd-MM-yyyy") String toDate,
			
			Model model) throws ParseException {
		
			
			
			boolean and = false;
			String SQL = "SELECT * FROM v_ic";

/*--------------------------------------------------------------------------------------------------------------------*/
			
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

/*--------------------------------------------------------------------------------------------------------------------*/
			
			if (client !=null  && client.length() > 0) {
			if (!and) {
			SQL += " WHERE ";
			and = true;

			} else {
			SQL += "AND";
			and = true;
			}
			SQL += " client_id IN(" + client + ")";
			}
	
/*--------------------------------------------------------------------------------------------------------------------*/
			
			if (test != null && test.length() > 0) {
			if (!and) {
			SQL += " WHERE ";
			and = true;

			} else {
			SQL += "AND";
			and = true;
			}
			SQL += " investigation_name IN(" + test + ")";
			}
			
/*--------------------------------------------------------------------------------------------------------------------*/
			
			if (clientType != null && clientType.size() > 0) {
			if (!and) {
			SQL += " WHERE ";
			and = true;

			} else {
			SQL += "AND";
			and = true;
			}
			String clientString = MiplUtil.ListToStringClientType(clientType);
			SQL += " client_type IN(" + clientString + ")";
			}
		
/*--------------------------------------------------------------------------------------------------------------------*/
			
			if (cycleDays != null && cycleDays.length() > 0) {
			if (!and) {
			SQL += " WHERE ";
			and = true;

			} else {
			SQL += "AND";
			and = true;
			}
			SQL += " cycle_days IN(" + cycleDays + ")";
			}
		
/*--------------------------------------------------------------------------------------------------------------------*/
			
			if (department != null && department.length() > 0) {
			if (!and) {
			SQL += " WHERE ";
			and = true;

			} else {
			SQL += "AND";
			and = true;
			}
			SQL += " department_id IN(" + department + ")";
			}
			
/*--------------------------------------------------------------------------------------------------------------------*/
			
			if (tenent != null && tenent.length() > 0) {
			if (!and) {
			SQL += " WHERE ";
			and = true;

			} else {
			SQL += "AND";
			and = true;
			}
			SQL += " tenent_id IN(" + tenent + ")";
			}
			
			
/*--------------------------------------------------------------------------------------------------------------------*/
			
			if (fromDate != null && fromDate.length() > 0 && toDate != null && toDate.length() > 0) {
				if (!and) {
				SQL += " WHERE";
				and = true;
				} else {
				SQL += "AND";
				and = true;

				}
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				
			    Date date1 = sdf.parse(fromDate);
			    Date date2 = sdf.parse(toDate);
			    

			    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");        
			    String from = sdf2.format(date1);
			    String to = sdf2.format(date2);
			    
				
				
				SQL += " CONVERT(visit_date, DATE) BETWEEN '" + from + "' AND '" + to+ "'";

				}

/*--------------------------------------------------------------------------------------------------------------------*/
			
			SQL = SQL + " Order By investigation_name ASC";
			Query q = em.createNativeQuery(SQL, PrepareIc.class);
			List<PrepareIc> checkinList = q.getResultList();
			
			Page<PrepareIc> PageObj = new PageImpl<>(checkinList, pageable, checkinList.size());
			
			model.addAttribute("searchReport", PageObj);
			
			return "SearchReportIc";
			}

}

