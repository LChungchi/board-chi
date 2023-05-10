package idusw.springboot.boradthymleaf.controller;

import idusw.springboot.boradthymleaf.domain.Member;
import idusw.springboot.boradthymleaf.domain.PageRequestDTO;
import idusw.springboot.boradthymleaf.domain.PageResultDTO;
import idusw.springboot.boradthymleaf.entity.MemberEntity;
import idusw.springboot.boradthymleaf.repository.MemberRepository;
import idusw.springboot.boradthymleaf.service.MemberService;
import jakarta.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberControllerTests {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional  // could not initialize proxy - no Session : Lazy fetch 로 인한 오류
    void readMember() { // seq를 사용해야 함
        Member member = new Member();
        member.setSeq(51L);
        Member result = null;
        if((result = memberService.read(member)) != null )
            System.out.println("조회 성공! " + result.getEmail() + ":::" + result.getName());
        else
            System.out.println("조회 실패!");
    }

    @Test
    void readMemberList() {
        List<Member> resultList = null;
        if((resultList = memberService.readList()) != null) {
            for(Member m : resultList)
                System.out.format("%-10s | %-10s | %10s\n", m.getName(), m.getEmail(), m.getRegDate());
        }
        else
            System.out.println("목록 없음");
    }

    @Test
    void initializeMember() {
        // Integer 데이터 흐름, Lambda 식 - 함수형 언어의 특징을 활용
        IntStream.rangeClosed(1, 33).forEach(i -> {
            MemberEntity member = MemberEntity.builder()
                    .seq(Long.valueOf(i))
                    .email("email" + i + "@induk.ac.kr")
                    .pw("pw" + i)
                    .name("name" + i)
                    .build();
            memberRepository.save(member);
        });
    }

    @Test
    void createMember() {
        Member member = Member.builder()
                .email("13@13.com")
                .name("13")
                .pw("13")
                .build();
        if(memberService.create(member) > 0 ) // 정상적으로 레코드의 변화가 발생하는 경우 영향받는 레코드 수를 반환
            System.out.println("등록 성공");
        else
            System.out.println("등록 실패");
    }

    @Test
    public void testPageList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(10).size(3).build();
        PageResultDTO<Member, MemberEntity> resultDTO = memberService.getList(pageRequestDTO);
        // 페이지에 있는 레코드 출력 print records in page
        for(Member member : resultDTO.getDtoList())
            System.out.println(member);
        // boolean prev은 lombok으로 generation할 때 isPrev()를 생성함, setter 는 setPrev()
        // int totalPage 인 경우 getter는 getTotalPage(), setter setTotalPage()
        // @Data == @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode.
        System.out.println("Prev : " + resultDTO.isPrev());
        System.out.println("Next : " + resultDTO.isNext());
        System.out.println("Total Page : " + resultDTO.getTotalPage());
        resultDTO.getPageList().forEach(i -> System.out.println(i));

        for(Integer i : resultDTO.getPageList())
            System.out.println(i);

    }

}
