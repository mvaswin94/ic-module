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

import mipl.icmodule.dto.PrepareIc;
import mipl.icmodule.user.User;
import mipl.icmodule.util.MiplUtil;

@Controller
@RequestMapping(value = "/master/user")
public class SearchUserController {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/search")
	public String checkinSearch(
			@PageableDefault(size = 1000) Pageable pageable,
			@RequestParam(defaultValue = "") String name, 
			@RequestParam(defaultValue = "") String contactNumber, 
			@RequestParam(defaultValue = "") List<String> email, 
			@RequestParam(defaultValue = "") List<String> role, 
			Model model) {
		
			boolean and = false;
			String SQL = "SELECT * FROM user_master";

/*====================================================================================================================*/
			
			if (name !=null && name.length() > 0) {
			if (!and) {
			SQL += " WHERE ";
			and = true;

			} else {
			SQL += "AND";
			and = true;
			}
			SQL += " user_id IN(" + name + ")";
			}

/*====================================================================================================================*/
			
			if (contactNumber !=null  && contactNumber.length() > 0) {
			if (!and) {
			SQL += " WHERE ";
			and = true;

			} else {
			SQL += "AND";
			and = true;
			}
			SQL += " contact_number IN(" + contactNumber + ")";
			}
	
/*====================================================================================================================*/
			
			if (email != null && email.size() > 0) {
			if (!and) {
			SQL += " WHERE ";
			and = true;

			} else {
			SQL += "AND";
			and = true;
			}
			String emailString = MiplUtil.ListToStringEmail(email);
			SQL += " email_address IN(" + emailString + ")";
			}
			
/*====================================================================================================================*/
			
			if (role != null && role.size() > 0) {
			if (!and) {
			SQL += " WHERE ";
			and = true;
 
			} else {
			SQL += "AND";
			and = true;
			}
			String roleString = MiplUtil.ListToStringRole(role);
			SQL += " role IN(" + roleString + ")";
			}
			

/*==================================================================================================================*/
			
			SQL = SQL + " Order By user_name ASC";
			Query q = em.createNativeQuery(SQL, User.class);
			List<User> checkinList = q.getResultList();
			
			Page<User> PageObj = new PageImpl<>(checkinList, pageable, checkinList.size());
			
			model.addAttribute("userObj", new User());
			model.addAttribute("userObj1", PageObj);
			return "UserMaster";
			}

/*==================================================================================================================*/	
}
