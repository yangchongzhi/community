package life.ycz.community.service;

import life.ycz.community.dto.PaginationDTO;
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

    public PaginationDTO list(Integer page, Integer size){
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questionList;
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.questionCount();
        paginationDTO.setPagination(totalCount,page,size);
        if(page<1){
            page = 1;
        }
        if(page>paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
        }
        Integer offset = size*(page-1);
        questionList = questionMapper.list(offset, size);
        for (Question question:questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTOList(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO listById(Integer userId, Integer page,Integer size){
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questionList;
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.countById(userId);
        paginationDTO.setPagination(totalCount,page,size);
        if(page<1){
            page = 1;
        }
        if(page>paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
        }
        Integer offset = size*(page-1);
        questionList = questionMapper.listById(userId,offset, size);
        for (Question question:questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTOList(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getQuestionById(Integer id) {
        Question question = questionMapper.getQuestionById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            //添加
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.create(question);
        }else {
            //更新
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.update(question);
        }
    }
}
