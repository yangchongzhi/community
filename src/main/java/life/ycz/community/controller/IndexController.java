package life.ycz.community.controller;

import life.ycz.community.dto.PaginationDTO;
import life.ycz.community.dto.QuestionDTO;
import life.ycz.community.mapper.QuestionMapper;
import life.ycz.community.mapper.UserMapper;
import life.ycz.community.model.User;
import life.ycz.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionMapper questionMapper;

    @GetMapping("/")
    public String hello(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "2") Integer size){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("token")){
                    User user = userMapper.findByToken(cookies[i].getValue());
                    if (user != null){
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        PaginationDTO paginationDTO = questionService.list(page,size);
        model.addAttribute("pagination", paginationDTO);
        return "index";
    }

}
