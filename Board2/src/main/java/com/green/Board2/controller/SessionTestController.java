package com.green.Board2.controller;

import com.green.Board2.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// server 에 data를 저장
@Controller
@RequestMapping("/test")
public class SessionTestController {

//    localhost:8081/test/ : 로 가면 보인닷
    @GetMapping("/page1")
    public String page1(HttpSession session){
        
        // session에 data 저장
        // 아래 내용을 굳이 Model 인터페이스로 html에 데이터 전달하지 않아도
        // 전달됨
        session.setAttribute("name", "java");
        session.setAttribute("age", 20);
        session.setAttribute("member", new MemberVO());
        // new MemberVO : MemberVO 클래스를 객체 생성해서 member라는 이름으로 전달하겠다.
        // session은 데이터가 꺼지는 두 가지 경우
        // 1. 웹브라우저가 완전히 꺼졌을 때
            // (page1 session 데이터가 보이는 이유?)
            // 인터넷 주소창 입력하면 실행되는 것은 controller 이므로 세션 세팅 명령어가 재실행됨
            // session은 타 웹브라우저끼리 session data 공유가 되지 않음
        // 2. server 에서 실행 중인 프로젝트를 중단되는 경우

        // 세션 유지 시간 설정 : 초단위
        session.setMaxInactiveInterval(60 * 30); // 30분
        return "page1";
    }

    @GetMapping("page2")
    public String page2(HttpSession session){
        // session에 담긴 데이터 확인
//        Object name = session.getAttribute("name");
        String name = (String) session.getAttribute("name");
        int age = (int) session.getAttribute("age");
        MemberVO member = (MemberVO) session.getAttribute("member");

        // 세션 데이터 삭제
        session.removeAttribute("age");

        // 모든 session data 삭제
//        session.invalidate();

        return "page2";
    }

    @GetMapping("page3")
    public String page3(){
        return "page3";
    }


}
