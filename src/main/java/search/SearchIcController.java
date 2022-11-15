package mipl.icmodule.search;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mipl.icmodule.dto.PrepareIc;
import mipl.icmodule.dto.PrepareIcRepository;
import mipl.icmodule.util.MiplUtil;

@Controller
@RequestMapping(value = "/ic")
public class SearchIcController {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	PrepareIcRepository PrepareIcRepo;

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/{keyword}/search")
	public String prepareSearch(
			@PageableDefault(size = 1000) Pageable pageable,
			@PathVariable String keyword,
			@RequestParam(defaultValue = "") String pro, 
			@RequestParam(defaultValue = "") String client, 
			@RequestParam(defaultValue = "") String test, 
			@RequestParam(defaultValue = "") List<String> clientType, 
			@RequestParam(defaultValue = "") String cycleDays,
			@RequestParam(defaultValue = "") String department, 
			@RequestParam(defaultValue = "") String tenent,
			@RequestParam(defaultValue = "") String status,
			
			@RequestParam(defaultValue = "") String dateType,
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
			SQL += " AND";
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
			SQL += " AND";
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
			SQL += " AND";
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
			SQL += " AND";
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
			SQL += " AND";
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
			SQL += " AND";
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
			SQL += " AND";
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
				SQL += " AND";
				and = true;

				}
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				
			    Date date1 = sdf.parse(fromDate);
			    Date date2 = sdf.parse(toDate);
			    

			    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");        
			    String from = sdf2.format(date1);
			    String to = sdf2.format(date2);
			    
				
				if(dateType.equals("Visit Date")) {
				SQL += " CONVERT(visit_date, DATE) BETWEEN '" + from + "' AND '" + to+ "'";
				}
				else if(dateType.equals("Prepared Date")) {
				SQL += " CONVERT(prepared_date, DATE) BETWEEN '" + from + "' AND '" + to+ "'";			
				}
				else if(dateType.equals("Allocated Date")) {
				SQL += " CONVERT(allocated_date, DATE) BETWEEN '" + from + "' AND '" + to+ "'";			
				}
				else if(dateType.equals("Approved Date")) {
				SQL += " CONVERT(approved_date, DATE) BETWEEN '" + from + "' AND '" + to+ "'";			
				}
			}
/*--------------------------------------------------------------------------------------------------------------------*/
			/*if (pro != null && client != null && test != null && cycleDays != null &&
					department != null && tenent != null && fromDate != null && toDate != null && dateType != null) {
				if (!and) {
					SQL += " WHERE ";
					and = true;

					} else {
					SQL += " AND";
					and = true;
					}
			}*/
/*--------------------------------------------------------------------------------------------------------------------*/
			
			/*SQL = SQL + " Order By investigation_name ASC";
			Query q = em.createNativeQuery(SQL, PrepareIc.class);
			List<PrepareIc> checkinList = q.getResultList();
			model.addAttribute("page", checkinList);*/
			
 			if (keyword.equals("prepare")) {
					
 				int limit=pageable.getPageSize();
 				int offset=0;
 				
 				String SQL1 = "SELECT COUNT(*) FROM v_ic WHERE CONVERT(visit_date, DATE) BETWEEN '2019-06-01' AND '2019-06-07' AND client_status='1' AND calculated_amount >0 AND billinvest_status ='0' AND billinvest_hold ='0' AND iseligible_ic ='0' Order By investigation_name asc";
 								
 				//Query q1 = em.createNativeQuery(SQL1);
				//List<PrepareIc> Obj1 = q1.getResultList();
 				//int a = Obj1.size();
 				//int Obj1 = q1.
 				
				SQL = SQL + " client_status='1' AND calculated_amount >0 AND billinvest_status ='0' AND billinvest_hold ='0' AND iseligible_ic ='0' Order By investigation_name ASC LIMIT "+limit+" OFFSET "+offset+"";
				Query q = em.createNativeQuery(SQL, PrepareIc.class);
				List<PrepareIc> Obj = q.getResultList();
				
				Page<PrepareIc> PageObj = new PageImpl<>(Obj, pageable, Obj.size());
				
				//Page<PrepareIc> PageObj2 = PrepareIcRepo.findAllPrepareIcSearch(PageObj, pageable);
				model.addAttribute("page", PageObj);
				return "PrepareIc";
				
			}
			else if (keyword.equals("allocate")) {
				SQL = SQL + " AND ic_status='1' Order By investigation_name ASC";
				Query q = em.createNativeQuery(SQL, PrepareIc.class);
				List<PrepareIc> checkinList = q.getResultList();
				Page<PrepareIc> PageObj = new PageImpl<>(checkinList, pageable, checkinList.size());
				model.addAttribute("page", PageObj);
				return "AllocateIc ";	
			}
			else if (keyword.equals("approve")) {
				SQL = SQL + " AND ic_status='3' Order By investigation_name ASC";
				Query q = em.createNativeQuery(SQL, PrepareIc.class);
				List<PrepareIc> checkinList = q.getResultList();
				Page<PrepareIc> PageObj = new PageImpl<>(checkinList, pageable, checkinList.size());
				model.addAttribute("page", PageObj);
				return "ApproveIc";	
			}
			else if (keyword.equals("dispatch")) {
				SQL = SQL + " AND Order By investigation_name ASC";
				Query q = em.createNativeQuery(SQL, PrepareIc.class);
				List<PrepareIc> checkinList = q.getResultList();
				Page<PrepareIc> PageObj = new PageImpl<>(checkinList, pageable, checkinList.size());
				model.addAttribute("page", PageObj);
				return "DispatchIc";	
			}
			else if (keyword.equals("noneligibleic")){
				
				if(status.equals("")) {
				SQL = SQL + " client_status='0' AND calculated_amount <=0 AND billinvest_hold ='1' AND iseligible_ic ='1' Order By investigation_name ASC";
				}
				else if(status.equals("No IC")) {
					SQL = SQL + " client_status='0' Order By investigation_name ASC";
				}
				else if(status.equals("Unassigned PRO")) {
					SQL = SQL + " pro_id='0' Order By investigation_name ASC";
				}
				else if(status.equals("Zero IC Amount")) {
					SQL = SQL + " calculated_amount <=0 Order By investigation_name ASC";
				}
				
				Query q = em.createNativeQuery(SQL, PrepareIc.class);
				List<PrepareIc> checkinList = q.getResultList();
				Page<PrepareIc> PageObj = new PageImpl<>(checkinList, pageable, checkinList.size());
				model.addAttribute("page", PageObj);
				return "ReconciliationIc";	
			}
			return null;
			}

}
