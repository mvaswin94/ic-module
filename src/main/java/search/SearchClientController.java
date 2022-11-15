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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mipl.icmodule.client.Client;
import mipl.icmodule.client.ClientRepository;
import mipl.icmodule.dto.PrepareIc;
import mipl.icmodule.util.MiplUtil;

@Controller
@RequestMapping(value = "/master/client")
public class SearchClientController {

	@Autowired
	ClientRepository repo;
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/search")
	public String checkinSearch(
			@PageableDefault(size = 1000) Pageable pageable,
			@RequestParam(defaultValue = "") String client, 
			@RequestParam(defaultValue = "") List<String> ic, 
			@RequestParam(defaultValue = "") List<String> clientType, 
			@RequestParam(defaultValue = "") List<String> paymentMode, 
			@RequestParam(defaultValue = "") List<String> pro,
			@RequestParam(defaultValue = "") String tenent,
			@RequestParam(defaultValue = "") String cycleDays,

			@RequestParam(defaultValue = "") @DateTimeFormat(pattern = "dd-MM-yyyy") String fromDate,
			@RequestParam(defaultValue = "") @DateTimeFormat(pattern = "dd-MM-yyyy") String toDate,
			Model model) throws ParseException {
		
			boolean and = false;
			String SQL = "SELECT * FROM client";

/*====================================================================================================================*/
			
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
		
/*====================================================================================================================*/
			
			if (ic !=null  && ic.size() > 0) {
			if (!and) {
			SQL += " WHERE ";
			and = true;

			} else {
			SQL += "AND";
			and = true;
			}
			String icString = MiplUtil.ListToStringIC(ic);
			SQL += " ic IN(" + icString + ")";
			}
					
/*====================================================================================================================*/
			
			if (clientType !=null  && clientType.size() > 0) {
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
			
/*====================================================================================================================*/
			
			if (paymentMode !=null  && paymentMode.size() > 0) {
			if (!and) {
			SQL += " WHERE ";
			and = true;

			} else {
			SQL += "AND";
			and = true;
			}
			String paymentModeString = MiplUtil.ListToStringPaymentMode(paymentMode);
			SQL += " payment_mode IN(" + paymentModeString + ")";
			}
			
/*====================================================================================================================*/
			
			if (pro !=null && pro.size() > 0) {
			if (!and) {
			SQL += " WHERE ";
			and = true;

			} else {
			SQL += "AND";
			and = true;
			}
			String proString = MiplUtil.ListToStringPro(pro);
			SQL += " pro IN(" + proString + ")";
			}
			
/*====================================================================================================================*/
			
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
			
/*====================================================================================================================*/
			
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
			
/*====================================================================================================================*/
			
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
			    
				
				
				SQL += " CONVERT(effective_from, DATE) BETWEEN '" + from + "' AND '" + to+ "'";

				}

/*==================================================================================================================*/
			
			SQL = SQL + " Order By client_id ASC";
			Query q = em.createNativeQuery(SQL, Client.class);
			List<Client> checkinList = q.getResultList();
			Page<Client> PageObj = new PageImpl<>(checkinList, pageable, checkinList.size());
			model.addAttribute("page", PageObj);
			
			//model.addAttribute("page", obj);
			
			return "ClientMaster";
			}

/*==================================================================================================================*/	
}
