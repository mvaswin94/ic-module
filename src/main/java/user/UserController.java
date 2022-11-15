package mipl.icmodule.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/master")
public class UserController {

	@Autowired
	UsersRepository repo;

	@GetMapping(value = "/user")
	public String userMasterGet(Model model, @PageableDefault(size = 50) Pageable pageable) {

		Page<User> page = repo.findAll(pageable);
		model.addAttribute("page", page);
		model.addAttribute("userObj", new User());

		return "UserMaster";
	}

	@GetMapping(value = "/user/add")
	public String newUserMasterGet(Model model) {

		model.addAttribute("userObj", new User());

		return "UserMasterAdd";
	}

	@PostMapping(value = "/user/add")
	public String newUserMasterPost(@ModelAttribute User user, Model model) {

		repo.save(user);

		return "redirect:/master/user";
	}
}
