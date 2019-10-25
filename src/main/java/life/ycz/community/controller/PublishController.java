package life.ycz.community.controller;

import life.ycz.community.mapper.QuestionMapper;
import life.ycz.community.mapper.UserMapper;
import life.ycz.community.model.Question;
import life.ycz.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
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
        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    user = userMapper.findByToken(cookie.getValue());
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        if(user==null){
            model.addAttribute("error","用户尚未登录,请先登录...");
            return "publish";
        }
        Question question = new Question();
        question.setCreator(user.getId());
        question.setDescription(description);
        question.setTitle(title);
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(System.currentTimeMillis());
        questionMapper.create(question);
        return "redirect:/";
    }

}
