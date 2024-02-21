package com.green.dbTest.service;

import com.green.dbTest.vo.StudentVO;

import java.util.List;

// Interface : 기능 정의
public interface StudentService {
    
    // 학생 한 명을 저장하는 기능 (어디에 저장? DB)
    public void insertStudent();


    // 학생 한 명을 삭제하는 기능
    public void deleteStudent();

    // 입력받은 값으로 학생 삭제 기능
    public void delete(int stuNo);

    // 학번이 1번인 학생의 이름 조회 기능
    public String selectName();

    // 학번이 1번인 학생의 성적 조회 기능
    int selectScore();

    // 학번이 1번인 학생의 정보 조회 기능
    StudentVO selectStu();

    // 모든 학생의 정보 조회 기능
    List<StudentVO> selectStuList();

}
