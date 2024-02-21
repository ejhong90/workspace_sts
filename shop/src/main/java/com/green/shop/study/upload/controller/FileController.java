package com.green.shop.study.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


// 파일 업로드, 다운도르 학습용 Controller
@Controller
@RequestMapping("/file")
public class FileController {

    // 파일 첨부 페이지로 이동
    @GetMapping("/uploadForm")
    public String uploadForm(){

        // 파일 확장자 추출
        String file1 = "abc.jpg";
        System.out.println(file1.substring(1, 5));
        System.out.println(file1.indexOf(".")); // indexOf : 중복된 문자가 있으면 가장 처음 나오는 거로 찾아줌
        System.out.println(file1.lastIndexOf(".")); // 중복된 문자가 존재할 경우, 가장 마지막에 있는 걸 찾아줌

        System.out.println(file1.substring(file1.lastIndexOf(".")));

        return "test/upload/upload_form";
    }

    // 첨부한 파일 데이터 등록(파일 업로드)
    @PostMapping("/upload")
    public String upload(@RequestParam(name = "img1") MultipartFile img1, @RequestParam(name = "img2") MultipartFile img2){
        // 첨부한 파일명 호출
        String originFileName = img1.getOriginalFilename(); // 리턴타입 String, 첨부한 파일명 호출
        System.out.println(originFileName); // 첨부한 파일명 출력

        // 첨부한 파일의 저장 경로 설정(업로드 경로)
        String uploadPath = "D:\\01-STUDY\\dev\\workspace_sts\\shop\\src\\main\\resources\\static\\upload\\";

        // 특수문자를 문자열 취급하는 방법 : \ + 특수문자
//        String str = "\"안녕\""; // 예시

        // 업로드 할 경로 및 파일명을 문자열로 나열
        String fileName = uploadPath + originFileName;

        // 파일 업로드
        // java.io.File
        // try, catch 구문 : 예외 처리
        try {
            File file = new File(fileName);
            img1.transferTo(file);
        } catch (IOException e) {
            System.out.println("!!! 파일 첨부 중 오류 발생 !!!");
            throw new RuntimeException(e);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////--------------두 번 째 방법---------------/////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////
        // 두번재 파일 업로드 // 중복된 오리지널 파일명이 있으면 덮어쓰기로 저장됨 > 저장 위치에서 명을 바꾸어서 저장
        // 원본 파일의 확장자만 추출
        String secondOriginFileName = img2.getOriginalFilename();
        String extension = originFileName.substring(secondOriginFileName.lastIndexOf("."));
        
        // 서버에 저장할 파일명 생성
        String uuid = UUID.randomUUID().toString(); // 문자열 랜덤 생성
        String attachedFileName = uuid + extension;

        // 파일 생성
        try {
            File file1 = new File(uploadPath + attachedFileName);
            img2.transferTo(file1);

        }catch (Exception e){
            System.out.println("!!! 파일 첨부 중 오류 발생 !!!");
            e.printStackTrace();  // 오류가 나는 위치, 원인 알려줌
        }
        
        return "";
    }
    
    // 다중 첨부 페이지로 이동
    @GetMapping("/multiForm")
    public String multiForm(){
        return "test/upload/multi_form";
    }


    // 다중 첨부 파일 업로드
    @PostMapping("/multi")
    public String multi(@RequestParam(name = "imgs") MultipartFile[] imgs){
        String uploadPath = "D:\\01-STUDY\\dev\\workspace_sts\\shop\\src\\main\\resources\\static\\upload\\";

        for(MultipartFile img : imgs){
            System.out.println(img.getOriginalFilename());

            // 확장자 추출
            String extension = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));

            // 첨부될 파일명
            String attachedFileName = UUID.randomUUID().toString() + extension;

            // 첨부
            try{
                File file = new File(uploadPath + attachedFileName);
                img.transferTo(file);

            } catch (Exception e){
                System.out.println("!!! 다중 첨부 중 오류 발생 !!!");
                e.printStackTrace();
            }


        }
        return "";
    }

    
}
