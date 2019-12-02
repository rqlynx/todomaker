package jp.abc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewUserController {

	@Autowired
    private JdbcUserDetailsManager userManager;

	@RequestMapping(value = "/newuser", method = RequestMethod.GET)
    public String newuser() {
        return "newuser";
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public String register(@RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest req) {
        UserBuilder users = User.withDefaultPasswordEncoder();
        try {
            userManager.createUser(users.username(username).password(password).roles("USER").build());
        } catch (Exception e) {
            req.setAttribute("msg", "その名前は登録できません。");
            return "newuser";
        }
        return "login";
    }
}
