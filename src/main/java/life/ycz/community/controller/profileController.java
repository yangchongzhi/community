package life.ycz.community.controller;

import life.ycz.community.dto.PaginationDTO;
import life.ycz.community.mapper.UserMapper;
import life.ycz.community.model.User;
import life.ycz.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class profileController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "5") Integer size,
                          HttpServletRequest request,
                          Model model){
        User user = (User)request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/";
        }
        if("question".equals(action)){
            model.addAttribute("section", "question");
            model.addAttribute("sectionName", "我的问题");
        }
        if("reply".equals(action)){
            model.addAttribute("section", "reply");
            model.addAttribute("sectionName", "最新回复");
        }
        PaginationDTO paginationDTO = questionService.listById(user.getId(),page,size);
        model.addAttribute("pagination",paginationDTO);
        return "profile";
    }
}
