package life.ycz.community.controller;

import life.ycz.community.dto.QuestionDTO;
import life.ycz.community.mapper.UserMapper;
import life.ycz.community.model.Question;
import life.ycz.community.model.User;
import life.ycz.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    QuestionService questionService;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                     Model model){
        QuestionDTO question = questionService.getQuestionById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",id);
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            @RequestParam(value = "id",required = false) Integer id,
                            HttpServletRequest request,
                            Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if(title==null||"".equals(title)){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description==null||"".equals(description)){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        if(tag==null||"".equals(tag)){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            model.addAttribute("error","用户尚未登录,请先登录...");
            return "redirect:/";
        }
        Question question = new Question();
        question.setCreator(user.getId());
        question.setDescription(description);
        question.setTitle(title);
        question.setTag(tag);
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }

}
