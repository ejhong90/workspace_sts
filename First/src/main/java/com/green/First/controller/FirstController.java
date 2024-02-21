package com.green.First.controller;

import com.green.First.vo.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//anotation
@Controller
public class FirstController {
//    특별한 기능? survlet 기능을 하는 클래스
//    어떤 페이지에서 어떤 게 오면 다른 페이지로 보내겠습니다. 같은 관제탑 역할 의무 부여

    @GetMapping("main") // servelet get방식과 동일 (doGet)
    public String main(){
        return "first"; // first = first.html
    }

    //@RequestParam html에서 넘어 오는 데이터를 받을 때 사용
    //name : 넘어오는 데이터의 이름을 의미함
    @GetMapping("second")
    public String second(@RequestParam(name = "name") String name, @RequestParam(name="age")int age){
//        System.out.println("@@@@@" + name);
//        System.out.println("@@@@@" + age);
        return "abc";
    }

    
    // 넘어오는 데이터의 이름과 동일한 변수를 가진 클래스 객체로 데이터를 받을 수 있다.
    @GetMapping("third")
    public String third(MemberVO memberVO, Model model){
//        System.out.println(memberVO);
        
        // 데이터를 html로 전달
        model.addAttribute("score", 80);

        return "abc";
    }
}
