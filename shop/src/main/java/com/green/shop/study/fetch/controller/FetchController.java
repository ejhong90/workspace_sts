package com.green.shop.study.fetch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.*;

@Controller
@RequestMapping("/fetch")
public class FetchController {

    @GetMapping("/main")
    public String mainFetch(){
        return "test/fetch/main";
    }

    // 넘어오는 데이터를 받을 때 사용하는 어노테이션 2가지
    // 1) requestParam (form태그 / a태그에서 보내는 데이터만 받아오는 형식)
    //    - URL의 데이터가 함께 넘어올 때 사용
    //    - ex) localhost:8081/aaa?a=b 처럼 데이터가 보이는 형태로 넘어올 때
    //    - form태그를 사용하여 post형식으로 넘기는 경우에도 포함됨
    // 2) requestBody (앞으로 비동기 통신은 requestBody로 받아 온다)
    //    - URL이 아닌 body 영역에 데이터가 담겨서 올 때

    // gitHub Test
    
    // 1개 이상의 데이터를 js에서 받아오기 (vo로 담기)
    @ResponseBody
    @PostMapping("/fetch1")
    public void fetch1(@RequestBody MemberVO memberVO){
        System.out.println("fetch1 method 실행");
        System.out.println(memberVO);
    }

    // 하나의 데이터를 js에서 받기
    @ResponseBody
    @PostMapping("/fetch2")
    public void fetch2(@RequestBody HashMap<String, String> data){
        System.out.println("fetch2 method 실행");
//        System.out.println(data);
        System.out.println("아이디 " + data.get("id"));
        System.out.println("이름 " + data.get("name"));
        System.out.println("나이 " + data.get("age"));
//        System.out.println(data.values());

    }

    // js에서 배열이 넘어오면 ArrayList로 받는다.
    @ResponseBody
    @PostMapping("/fetch3")
    public void fetch3(@RequestBody ArrayList<MemberVO> list){
        System.out.println("fetch3 method 실행");
        System.out.println(list);
        for(int i = 0 ; i < list.size() ; i++){
            System.out.println(i+1 + " : " + list.get(i).getId());
        }

        for(MemberVO memberVO : list){
            System.out.println("member 정보 : " + memberVO);
        }
    }
    
    
    // js에서 넘어오는 데이터를 Map으로 받을 때
    @ResponseBody
    @PostMapping("/fetch4")
    public void fetch4(@RequestBody HashMap<String, Object> map){
        System.out.println("fetch4 method 실행");
        System.out.println(map);
        int cartCode = (int) map.get("cartCode");
        System.out.println("cartCode = "+ cartCode);

        // memberId 가져오기
        HashMap<String, String> vo = (HashMap<String, String>) map.get("memberInfo");
        String memberId = vo.get("memberId");
        System.out.println("memberId = " + memberId);

        // 첫번째 imgCode 가져오기
        HashMap<String, Object> itemInfo = (HashMap<String, Object>) map.get("itemInfo");
        List<Object> list = (ArrayList<Object>) itemInfo.get("imgList");
        Map<String, Object> img = (HashMap<String, Object>) list.get(1);
        int imgCode = (int) img.get("imgCode");
        System.out.println(imgCode);

        // map 데이터를 vo객체에 매핑하기
        ObjectMapper mapper = new ObjectMapper();
        MapDataVO data = mapper.convertValue(map, MapDataVO.class);
        System.out.println(data);
    }
}
