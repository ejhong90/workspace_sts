package com.green.StudentManager.controller;

import com.green.StudentManager.service.StuServiceImpl;
import com.green.StudentManager.vo.StuVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {
    @Resource(name = "stuService")
    private StuServiceImpl stuService;

    // 학생 목록 페이지로 이동
    @GetMapping("/")
    public String stuList(Model model){
        // 학생 목록 조회
        List<StuVO> list = stuService.selectStuList();

        // 조회한 목록을 html로 전달
        model.addAttribute("stuList", list);

        return "stu_list";

    }

    // Oracle은 빈문자열을 NULL로 자동 전환 해줌
    // 자바, mariaDB는 빈문자열, NULL 다르게 취급하여 빈문자열 ( "" )
    // NULL은 데이터 입력값이 전혀 없을 경우에 발생하는 것
    // 데이터 입력창이 있을 경우에는 문자열 빈칸으로 취급

    @GetMapping("/regStu")
    public String regStu(){
        return "reg_student";
    }

    @PostMapping("/regStu")
    public String reg(StuVO stuVO){
        // 학생 등록 기능
        stuService.insertStu(stuVO);

        return "redirect:/";
        // redirect: : 이전의 컨트롤러로 향하는 것
    }

    @GetMapping("/stuDetail")
    public String stuDetail(@RequestParam(name = "stuNo") int stuNo, Model model){
        // 클릭한 학생의 정보 조회
        StuVO stu = stuService.selectStuDetail(stuNo);
        // 조회한 학생의 정보를 html로 전달
        // model.addAttribute("넘어가는 데이터의 name" , html이 받을 정보)
        model.addAttribute("stuInfo",stu);
        return "student_detail";
    }

    @GetMapping("/deleteStu")
    public String deleteStu(StuVO stuVO){
        // 해당 학생 정보 삭제
        stuService.deleteStu(stuVO.getStuNo());
        return "redirect:/";
    }
}
