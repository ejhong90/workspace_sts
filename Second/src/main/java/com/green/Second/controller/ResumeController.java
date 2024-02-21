package com.green.Second.controller;

import com.green.Second.vo.ResumeVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class ResumeController {

    @GetMapping("/resume")
    public String inputResume(){
        return "input_resume";
    }

    @PostMapping("/r2")
    public String r2(@RequestParam(name = "name") String name, @RequestParam(name = "tel") String tel, Model model){
        System.out.println("name = " + name);
        System.out.println("tel =" + tel);

        model.addAttribute("name", name);
        model.addAttribute("tel", tel);
        return "info_resume";

    }

    @PostMapping("/r3")
    public String third(ResumeVO resumeVO){
        System.out.println(resumeVO);

        return "resume_chk";
    }
}
