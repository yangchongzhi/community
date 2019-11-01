package life.ycz.community.mapper;

import life.ycz.community.dto.QuestionDTO;
import life.ycz.community.model.Question;
import life.ycz.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {

    void incView(Question question);
}