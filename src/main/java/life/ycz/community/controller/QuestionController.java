package life.ycz.community.controller;

import life.ycz.community.dto.QuestionDTO;
import life.ycz.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                                Model model){
        QuestionDTO questionDTO = questionService.getQuestionById(id);
        questionService.incView(id);
        questionDTO.setViewCount(questionDTO.getViewCount()+1);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
