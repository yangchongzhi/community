package life.ycz.community.controller;

import life.ycz.community.dto.PaginationDTO;
import life.ycz.community.mapper.QuestionMapper;
import life.ycz.community.mapper.UserMapper;
import life.ycz.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionMapper questionMapper;

    @GetMapping("/")
    public String hello(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size){
        PaginationDTO paginationDTO = questionService.list(page,size);
        model.addAttribute("pagination", paginationDTO);
        return "index";
    }
}
