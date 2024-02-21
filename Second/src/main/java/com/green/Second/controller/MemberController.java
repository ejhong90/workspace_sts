package com.green.Second.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Name;


// @ : 어노테이션
// @Controller : 해당 클래스 파일이 컨트롤러 역할을 하는 클래스임을 spring에게 인식 시킴
@Controller
public class MemberController {

    // @GetMapping, @PostMapping
    // 페이지 접속 정보
    // 소괄호 안의 글자와 localhost:8081 뒤의 글자가 일치하면 해당 메소드를 실행하는 역할
    // @PostMapping은 페이지 이동 방법 중, form태그로 이동하면서 form태그의 method 속성 값이
    // post일 때에만 실행 된다.
    //@GetMapping 의 경우는 위의 방식 제외하고 전부
    // get 방식
    // 1. form태그의 method 속성 값이 get일 경우
    // 2. a 태그로 페이지가 이동한 경우
    // 3. 주소창에 직접 url을 입력한 경우

    @GetMapping("/")
    public String main(){
        // 리턴되는 문자열은 마지막에 실행되는 html 파일명을 의미함
        // html 파일은 반드시 templates 폴더 안에 존재해야 함
        return "first"; //first.html 파일 실행
    }

    // @RequestParam 어노테이션으로 html에서 넘어오는 데이터를 전달 받을 수 있음
    // 해당 어노테이션 속성으로 name, required, defaultValue 가 있음
    // name = html에서 넘어오는 데이터 이름
    // required = html에서 넘어오는 데이터가 필수 데이터인지 설정
    // required를 적지 않으면 default 값은 true
    // defaultValue =  데이터가 넘어오지 않을 때, 설정되는 기본 값

    // html로 데이터를 전달하기 위해서 method 매개변수로 Model(Interface)의 객체 선언
    @GetMapping("/second")
    public String second(@RequestParam(name = "addr", required = false) String address, @RequestParam(name = "age", required = false, defaultValue = "1") int age
            /*html로 데이터 전달하기*/, Model model){
        System.out.println("name = " + address);
        System.out.println("age = " + age);

        // html로 데이터 전달하기 :: 매개변수 Model (Interface) + (변수명)
        model.addAttribute("addr", address);
        model.addAttribute("age", age);
        model.addAttribute("name", "홍길동");

        return "second";
    }

}
