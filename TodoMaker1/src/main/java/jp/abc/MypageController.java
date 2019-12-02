package jp.abc;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.abc.repository.ProfileRepository;
import jp.abc.repository.TodoRepository;

@Controller
public class MypageController {

    @Autowired
    private JdbcUserDetailsManager userManager;

	@Autowired
	private ProfileRepository prorepository;

    @Autowired
    private TodoRepository todoRepository;

    @RequestMapping(value = "/mypage")
    public ModelAndView mypage(ModelAndView mav, Principal p) {
        mav.setViewName("mypage");
        mav.addObject("name", p.getName());
        Optional<Profile> data = prorepository.findByName(p.getName());
        Profile profile;
        if (!data.isPresent()) {
            profile = new Profile();
            profile.setName(p.getName());
            prorepository.saveAndFlush(profile);
        } else {
            profile = data.get();
        }
        mav.addObject("profile", profile);
        List<Todo> list = todoRepository.findAll();
        mav.addObject("datalist", list);
        return mav;
    }

}
