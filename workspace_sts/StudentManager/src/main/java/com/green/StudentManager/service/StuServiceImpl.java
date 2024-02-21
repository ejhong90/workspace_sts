package com.green.StudentManager.service;

import com.green.StudentManager.vo.StuVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stuService")
public class StuServiceImpl implements StuService {
    // mybatis 사용한다고 하면 SqlSessionTemplate 는 자동으로 ~!
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public int insertStu(StuVO stuVO) {
        int result = sqlSession.insert("insertStu", stuVO);
        return result;
    }

//    sqlSession.insert("실행시킬 쿼리 id", 빈값을 채울 것);
    @Override
    public List<StuVO> selectStuList() {
//        List<StuVO> list = sqlSession.selectList("selectStu");
//        return list;
        return sqlSession.selectList("selectStuList");
    }

    @Override
    // 쿼리의 WHERE 문으로 인하여 빈값 대입 필요
    public StuVO selectStuDetail(int stuNo) {
        return sqlSession.selectOne("selectStuDetail", stuNo);
    }

    // "실행할 쿼리 id" , html에서 뭐라고 부를랭?
    @Override
    public int deleteStu(int stuNo) {
        return sqlSession.delete("deleteStu", stuNo);
    }


}
