package com.green.FetchStudent.service;

import com.green.FetchStudent.vo.ClassVO;
import com.green.FetchStudent.vo.ScoreVO;
import com.green.FetchStudent.vo.StuVO;

import java.util.List;

public interface StuService {

    // 학급 목록 조회
    List<ClassVO> selectClassList();

    // 학생 목록 조회
    List<StuVO> selectStuList(StuVO stuVO);

    // 학생 한 명의 성적 조회
    ScoreVO selectStuScore(int stuNum);

    // 학생 한 명의 데이터 조회
    StuVO selectStuOne(int stuNum);

    // 학생 성적 등록
    void insertScore(ScoreVO scoreVO);

}
