package com.green.StudentManager.service;

import com.green.StudentManager.vo.StuVO;

import java.util.List;

public interface StuService {

    // SELECT 리턴 : 매번 바뀜
    // INSERT, DELETE, UPDATE 리턴 : void, int (정석)
    // 왜 정석이 int인가?
    // select 문 실행 시, 테이블이 나오고
    // insert문 실행 시, 콘솔같은 곳에 결과물이 나온다
    // insert, delete, update는 몇 개의 행이 수정되었는 지만 알려준다.
    // 수정된 행의 개수를 알려주므로 int가 정석으로 함
    // 무조건 int로 할 필요는 없음

    // return 타입, 매개변수 쿼리 수행시 어떤 역할

    // return
    // return 타입 : 쿼리 실행 결과를 받아 옴
    // insert, delete, update는 쿼리 실행 결과가 몇 개의 행이 삽입, 삭제, 수정됐는 지 보여 줌
    // so 위 셋의 쿼리 결과 리턴 타입은 무조건 int, void

    // select는 쿼리의 타입에 따라 리턴 타입(자료형)이 달라짐
    // 조회 결과 데이터가 0~1행 : vo클래스
    // 조회 결과 데이터가 2행 이상 : List<vo클래스>
    // list 출력은 for문 사용하면 됨

    // 매개변수
    // 역할 : 쿼리 실행 시 빈값을 채우는 역할 (값을 저장)
    // 빈 값을 채울 데이터의 갯수에 따라 구분 가능
    // 0개 : 매개변수 필요 없음
    // 1개 :
    //  1) 숫자일 경우 - int 하나
    //  2) 문자열인 경우 - String 하나
    // 2개 이상 : 매개변수 VO

    //학생등록
    int insertStu(StuVO stuVO);
//    StuVO selectStu2(StuVO stuVO);

    // 학생 목록 조회
    List<StuVO> selectStuList();

    // 학생 한 명 상세 조회

    // 쿼리의 WHERE 문으로 인하여 빈값 대입 필요
    StuVO selectStuDetail(int stuNo);

    // 선택한 학생 한 명의 데이터를 삭제
    int deleteStu (int stuNo);

}
