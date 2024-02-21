package com.green.FetchStudent.controller;

import com.green.FetchStudent.service.StuServiceImpl;
import com.green.FetchStudent.vo.ClassVO;
import com.green.FetchStudent.vo.DetailVO;
import com.green.FetchStudent.vo.ScoreVO;
import com.green.FetchStudent.vo.StuVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/stu")
public class StuController {

    @Resource(name = "stuService")
    private StuServiceImpl stuService;

//    // stu_manage.html로 이동
//    @GetMapping("/list")
//    public String list(){
//        return "stu_manage";
//    }
    // 학급 목록 조회
    @GetMapping("/list")
    public  String classList(Model model, StuVO stuVO){
        // 학급 목록 조회
        List<ClassVO> clsList = stuService.selectClassList();
        model.addAttribute("clsList", clsList);
        // 학생 목록 조회
        model.addAttribute("stuList", stuService.selectStuList(stuVO));
        return "stu_manage";
    }

    @ResponseBody
    @PostMapping("/fetchSelect")
    public List<StuVO> fetchSelect(StuVO stuVO){
        // 전달받은 데이터로 쿼리 실행
        List<StuVO> stuList = stuService.selectStuList(stuVO);
        return stuList;
    }

    @ResponseBody
    @PostMapping("/score")
    public DetailVO stuDetail(@RequestParam(name = "stuNum") int stuNum){
        // 클릭한 학생의 상세 정보 조회
        StuVO stuInfo = stuService.selectStuOne(stuNum);
        // 클릭한 학생의 점수 정보 조회
        ScoreVO scoreInfo = stuService.selectStuScore(stuNum);

        // 조회한 데이터를 가지고 자바스크립트로 이동
        DetailVO result = new DetailVO();
        result.setStuVO(stuInfo);
        result.setScoreVO(scoreInfo);

        return result;
    }

    @ResponseBody
    @PostMapping("/insertScore")
    public void score(ScoreVO scoreVO){
        stuService.insertScore(scoreVO);
    }

}
