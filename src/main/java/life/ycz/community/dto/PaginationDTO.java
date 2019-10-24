package life.ycz.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOList;
    private boolean hasPrevious;
    private boolean hasFirst;
    private boolean hasNext;
    private boolean hasEnd;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;
    public void setPagination(Integer totalCount, Integer page, Integer size){
        if(totalCount%size!=0){
            totalPage=totalCount/size+1;
        }else {
            totalPage = totalCount/size;
        }
        if (page<1){
            page = 1;
        }
        if(page>totalPage){
            page = totalPage;
        }
        if(page==1){
            hasPrevious = false;
        }else {
            hasPrevious = true;
        }
        if (page==totalPage){
            hasNext = false;
        }else {
            hasNext = true;
        }
        this.page = page;
        pages.add(page);
        for(int i = 1;i<=3;i++){
            if((page-i)>0){
                pages.add(0,page-i);
            }
            if((page+i)<=totalPage){
                pages.add(page+i);
            }
        }
        if (pages.contains(1)){
            hasFirst = false;
        }else {
            hasFirst = true;
        }
        if (pages.contains(totalPage)){
            hasEnd = false;
        }else {
            hasEnd = true;
        }

    }
}
