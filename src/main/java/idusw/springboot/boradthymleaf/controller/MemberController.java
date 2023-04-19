package idusw.springboot.boradthymleaf.controller;

import ch.qos.logback.core.model.Model;
import idusw.springboot.boradthymleaf.domain.Member;
import idusw.springboot.boradthymleaf.repository.MemberRepository;
import idusw.springboot.boradthymleaf.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")

public class MemberController {
    // Constructor DI(Dependency Injection)
    MemberService memberService;
    public  MemberController(MemberService memberService){
        this.memberService = memberService;
    }
    @GetMapping("/logins")
    public String goLogins(){
        return "/members/login";
    } // view : template engine - thymeleaf .html

    @PostMapping("/logins")
    public String loginMember(){ // 로그인 처리 -> service ->repository -> service -> controller
        return "redirect:/";
    }
    @GetMapping("/register")
    public String getRegisterForm(Model model){ // form 요청 -> view (template engine)
        model.addAttribute("member",Member.builder().build());
        return "/members/register";
    }
    @PostMapping("/register") // 등록 처리 -> service -> repository -> service -> controller
    public String createMember(@ModelAttribute("member") Member member, Model model){
        System.out.println("this");
        member.setSeq(1L);
        if(memberService.create(member) > 0){
        return "redirect:/";
        }
        else {
            return "/main/error";
        }
    }

    @GetMapping("/update")
    public String getUpdateform(){ // form 요청 -> view (template engine)
        return "/members/update";
    }
    @PutMapping("/update") // 등록 처리 -> service -> repository -> service -> controller
    public String updateMamber(){
        return "redirect:/";
    }

    @DeleteMapping("/delete") // 등록 처리 -> service -> repository -> service -> controller
    public String deleteMember(){
        return "redirect:/";
    }
    @GetMapping("/forgot") // 조회 read
    public String goForgotform(){ // 분실 비밀번호 처리 요청 -> view
        return "/members/forgot-password"; // view : template engine - thymeleaf .html
    }

    @PostMapping("/forgot") // create vs update -> @PutMapping, delete -> @DeleteMapping
    public String forgotMemberPassword(){  // 비밀번호(갱신) -> service -> repository -> service -> controller
        return "redirect:/"; // 루트로 이동
    }

}
