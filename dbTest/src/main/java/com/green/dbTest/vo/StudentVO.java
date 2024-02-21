package com.green.dbTest.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// STUDENT 테이블과 매칭되는 클래스
@Setter
@Getter
@ToString // 무조건 필요하진 않지만 콘솔에서 확인하기 위한 수단임
public class StudentVO {
    private int stuNo;
    private String stuName;
    private int score;
    private String addr;

}
