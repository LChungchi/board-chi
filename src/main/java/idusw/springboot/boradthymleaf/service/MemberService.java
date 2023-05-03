package idusw.springboot.boradthymleaf.service;

import idusw.springboot.boradthymleaf.domain.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {
    int create(Member m);
    Member read(Member m);  // mno 값을 넘김
    List<Member> readList();
    int update(Member m);
    int delete(Member m);

    Member login(Member m);
}
