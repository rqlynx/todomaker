package jp.abc;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.abc.repository.ProfileRepository;

@Controller
public class DiagContoller {

	@Autowired
	private ProfileRepository prorepository;

	//診断リストページの呼び出し
	@RequestMapping(value = "/diag", method = RequestMethod.GET)
	public ModelAndView diag(
			ModelAndView mav) {
		mav.setViewName("diag");
		return mav;
	}

	//診断ページの呼び出し
	@RequestMapping(value = "/omikuji", method = RequestMethod.GET)
	public ModelAndView omikuji(
			ModelAndView mav, Principal p) {
		String[] omikuji = {"大吉","中吉","吉","小吉","末吉","凶","大凶"};

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yMd");
        String time = sdf.format(cal.getTime());
        String name = p.getName();
        int hash = (name + time).hashCode() % 7;
        hash = Math.abs(hash);
		String result = omikuji[hash];

		mav.setViewName("omikuji");
		mav.addObject("result",result);
		mav.addObject("username", p.getName());
		Optional<Profile> data = prorepository.findByName(p.getName());
		Profile profile;
		profile = data.get();
		mav.addObject("profile", profile);
		return mav;
	}
}
