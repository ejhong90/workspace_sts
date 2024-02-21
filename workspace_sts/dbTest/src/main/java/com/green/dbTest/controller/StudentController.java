package com.green.dbTest.controller;

import com.green.dbTest.service.StudentService;
import com.green.dbTest.service.StudentServiceImpl;
import com.green.dbTest.vo.StudentVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {
    @Resource(name = "stuService")
    private StudentService studentService;

    @GetMapping("/")
    public String insertTest(){
        // 학생 한 명 등록
//      studentService.insertStudent();

        // 학생 한 명 삭제
//        studentService.deleteStudent();

        // 학생 1명 이름 조회
//        String name = studentService.selectName();
//        int score = studentService.selectScore();
//        System.out.println("name =" + name);
//        System.out.println("score =" + score);

        // 학생 1명 정보 조회
//        StudentVO stu = studentService.selectStu();
//        System.out.println(stu);

        // 모든 학생 정보 조회
        List<StudentVO> list = studentService.selectStuList();
//        for(int i = 0; i < list.size(); i++){
//            System.out.println(list.get(i));
//        }
        for(StudentVO e : list){
            System.out.println(e);
        }
        return "insert";
    }

    @GetMapping("/delete")
    public String delete(){
        return "delete";
    }

    @PostMapping("/deleteResult")
    public String deleteResult(@RequestParam(name = "stuNo") int stuNo){
        // 학생 삭제 기능

        studentService.delete(stuNo);
        return "delete_result";
    }





}
