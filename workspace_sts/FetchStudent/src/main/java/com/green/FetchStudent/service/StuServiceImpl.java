package com.green.FetchStudent.service;

import com.green.FetchStudent.vo.ClassVO;
import com.green.FetchStudent.vo.ScoreVO;
import com.green.FetchStudent.vo.StuVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stuService")
public class StuServiceImpl implements StuService{

    @Autowired
    private SqlSessionTemplate session;

    // 선택한 반의 학생 조회
    @Override
    public List<ClassVO> selectClassList() {
        return session.selectList("stuMapper.selectClassList");
    }

    // 학생 전체 조회
    @Override
    public List<StuVO> selectStuList(StuVO stuVO) {
        return session.selectList("stuMapper.stuList", stuVO);
    }

    // 학생 한 명의 성적 조회
    @Override
    public ScoreVO selectStuScore(int stuNum) {
        return session.selectOne("stuMapper.selectScoreInfo", stuNum);
    }

    // 학생 한 명의 정보 조회
    @Override
    public StuVO selectStuOne(int stuNum) {
        return session.selectOne("stuMapper.stuInfo", stuNum);
    }

    // 학생 성적 등록
    @Override
    public void insertScore(ScoreVO scoreVO) {
        session.insert("stuMapper.insertScore", scoreVO);
    }


}
