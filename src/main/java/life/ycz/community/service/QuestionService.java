package life.ycz.community.service;

import life.ycz.community.dto.QuestionDTO;
import life.ycz.community.mapper.QuestionMapper;
import life.ycz.community.mapper.UserMapper;
import life.ycz.community.model.Question;
import life.ycz.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    public List<QuestionDTO> list(){
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questionList;
        questionList = questionMapper.list();
        for (Question question:questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
