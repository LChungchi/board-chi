package idusw.springboot.boradthymleaf.controller;

import idusw.springboot.boradthymleaf.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    // Field DI
    @Autowired
    MemoService memoService;  // MemoService 인터페이스의 구현체를 필드 주입

    @GetMapping("/")
    public String goHome() {
        return "/main/index";
    }
    @GetMapping("/buttons")
    public String goButtons(){
        return "/main/buttons";
    }
    @GetMapping("/cards")
    public String goCards(){
        return "/main/cards";
    }
    @GetMapping("/colors")
    public String goColors(){
        return "/main/utilities-color";
    }
    @GetMapping("/borders")
    public String goBorders(){
        return "/main/utilities-border";
    }
    @GetMapping("/animations")
    public String goAnimations(){
        return "/main/utilities-animation";
    }
    @GetMapping("/others")
    public String goOther(){
        return "/main/utilities-other";
    }


    @GetMapping("/404pages")
    public String go404Pages(){
        return "/main/404";
    }
    @GetMapping("/blanks")
    public String goBlanks(){
        return "/main/blank";
    }

    @GetMapping("/charts")
    public String goCharts(){
        return "/main/charts";
    }
    @GetMapping("/tables")
    public String goTables(){
        return "/main/tables";
    }
}
