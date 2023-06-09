package idusw.springboot.boradthymleaf.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
    private int page; // 요청하는 페이지
    private int size; // 페이지당 게시물 수

    public PageRequestDTO(){
        this.page = 1;
        this.size = 3;

    }
    public Pageable getPageable(Sort sort){
        return PageRequest.of(page - 1, size, sort);
    }


}
