package idusw.springboot.boradthymleaf.service;

import idusw.springboot.boradthymleaf.domain.Member;
import idusw.springboot.boradthymleaf.domain.PageRequestDTO;
import idusw.springboot.boradthymleaf.domain.PageResultDTO;
import idusw.springboot.boradthymleaf.entity.MemberEntity;
import idusw.springboot.boradthymleaf.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class MemberServiceImpl implements MemberService { // 구현체
    // DI - IoC (Inversion of Control : 제어의 역전) 방법 중 하나 , ID, DL ...
    MemberRepository memberRepository;
    public  MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public int create(Member m) {
        MemberEntity entity = MemberEntity.builder()
                .seq(m.getSeq())
                .email(m.getEmail())
                .name(m.getName())
                .pw(m.getPw())
                .build();
        if(memberRepository.save(entity) != null){ // 저장 성공
            return 1;
        }
        else
            return 0;
    }

    @Override
    public Member read(Member m) {
        MemberEntity e = memberRepository.getById(m.getSeq());
        Member result = new Member(); // DTO (Data Transfer Object) : Controller - Service or Controller - View
        result.setSeq(e.getSeq());
        result.setEmail(e.getEmail());
        result.setName(e.getName());
        return result;
    }

    @Override
    public List<Member> readList() {
        List<MemberEntity> entities = new ArrayList<>();
        List<Member> members = null;
        if((entities = memberRepository.findAll()) != null) {
            members = new ArrayList<>();
            for(MemberEntity e : entities) {
                Member m = Member.builder()
                        .seq(e.getSeq())
                        .email(e.getEmail())
                        .name(e.getName())
                        .pw(e.getPw())
                        .regDate(e.getRegDate())
                        .modDate(e.getModDate())
                        .build();
                members.add(m);
            }
        }
        return members;
    }

    @Override
    public int update(Member m) {
        MemberEntity entity = MemberEntity.builder()
                .seq(m.getSeq())
                .email(m.getEmail())
                .name(m.getName())
                .pw(m.getPw())
                .build();
        if(memberRepository.save(entity) != null) // 저장 성공
            return 1;
        else
            return 0;
    }

    @Override
    public int delete(Member m) {
        MemberEntity entity = MemberEntity.builder()
                .seq(m.getSeq())
                .build();
        memberRepository.deleteById(entity.getSeq());
        return 1;
    }

    @Override
    public Member login(Member m) {
        MemberEntity e = memberRepository.getByEmailPw(m.getEmail(),m.getPw());
        Member result = null; // DTO (Data Transfer Object) : Controller - Service or Controller - View
        if (e != null) {
            result = new Member();
            result.setSeq(e.getSeq());
            result.setEmail(e.getEmail());
            result.setName(e.getName());
        }
        return result;
    }

    @Override
    public PageResultDTO<Member, MemberEntity> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("seq").ascending());

        Page<MemberEntity> result = memberRepository.findAll(pageable);
        Function<MemberEntity, Member> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }
}
