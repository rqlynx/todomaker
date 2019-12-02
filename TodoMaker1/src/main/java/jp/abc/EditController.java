package jp.abc;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.abc.repository.ProfileRepository;
import jp.abc.repository.TodoRepository;

@Controller
public class EditController {

	//Todoリストのrepository
    @Autowired
    private TodoRepository todorepository;

	//プロフィールのrepository
    @Autowired
    private ProfileRepository prorepository;

	//TODOリストの編集(GET)
//	@RequestMapping(value = "/todoedit/{id}", method = RequestMethod.GET)
//	public ModelAndView todoedit(@ModelAttribute("todo") Todo todo,
//												@PathVariable int id,
//												ModelAndView mav) {
//		//引っ張ってくるHTML
//		mav.setViewName("todoedit");
//		//指定したidのデータベースを取得
//		Optional<Todo> list = todorepository.findById((long)id);
//		//データベースをmavに渡す
//		mav.addObject("pro",list.get() );
//		return mav;
//	}

	//仮
	@RequestMapping(value = "/todoedit", method = RequestMethod.GET)
	public ModelAndView todoedit(@ModelAttribute("todo") Todo todo,

												ModelAndView mav) {
		//引っ張ってくるHTML
		mav.setViewName("todoedit");
		//指定したidのデータベースを取得
		Long i = (long) 1;
		Optional<Todo> list = todorepository.findById(i);
		//データベースをmavに渡す
		mav.addObject("todo",list.get() );
		return mav;
	}


	//TODOリストの編集(POST)
	@RequestMapping(value = "/todoedit", method = RequestMethod.POST)
	public ModelAndView edit(
	        @ModelAttribute("todo") @Validated Todo todo,
	        BindingResult result,
	        ModelAndView mav) {
		//errorがなかった場合
	    if (!result.hasErrors()) {
	    	//データベースの更新
	        todorepository.saveAndFlush(todo);
	      //マイページへのリダイレクト
	        return new ModelAndView("redirect:/mypage");
	    }
	    mav.setViewName("todoedit");
	    mav.addObject("msg", "sorry, error is occured...");
	    return mav;
	}

	//プロフィールの編集(GET)
	@RequestMapping(value = "/proedit/{id}", method = RequestMethod.GET)
	public ModelAndView proedit(@ModelAttribute Profile pro,
												@PathVariable int id,
												ModelAndView mav) {
		//引っ張ってくるHTML
		mav.setViewName("proedit");
		//指定したidのデータベースを取得
		Optional<Profile> list = prorepository.findById((long)id);
		//データベースをmavに渡す
		mav.addObject("pro",list.get() );
		return mav;
	}

//	  @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
//    public ModelAndView edit(@ModelAttribute Profile profile,@PathVariable long id, ModelAndView mav) {
//        mav.setViewName("edit");
//        Optional<Profile> data = repository.findById((long)id);
//        mav.addObject("formModel", data.get());
//        return mav;
//    }

	//プロフィールの編集(POST)
	@RequestMapping(value = "/proedit", method = RequestMethod.POST)
	public ModelAndView proedit(
	        @ModelAttribute @Validated Profile pro,
	        Errors result,
	        ModelAndView mav) {
		//errorがなかった場合
//	    if (!result.hasErrors()) {
//	    	//データベースの更新
//	        prorepository.saveAndFlush(pro);
//	      //マイページへのリダイレクト
//	        return new ModelAndView("redirect:/mypage");
//	    }
////	    mav.setViewName("proedit");
//	    mav.addObject("pro",pro);
////	    mav.addObject("msg", "sorry, error is occured...");
//	    return mav;
		      if (result.hasErrors()) {
		    	  mav.addObject("pro",pro);
		    	  return mav;
		      }
		  prorepository.saveAndFlush(pro);
		  return new ModelAndView("redirect:/mypage");
	}

//    @RequestMapping(value = "/edit/", method = RequestMethod.POST)
//    public ModelAndView form(@ModelAttribute @Validated Profile profile,
//            Errors result,
//            ModelAndView mav) {
//        if (result.hasErrors()) {
//            return mav;
//        }
//        repository.saveAndFlush(profile);
//        return new ModelAndView("redirect:/mypage");
//    }
}
