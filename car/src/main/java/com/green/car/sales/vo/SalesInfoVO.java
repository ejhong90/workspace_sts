package com.green.car.sales.vo;

import com.green.car.manage.vo.CarInfoVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class SalesInfoVO {
    private int salesNum;
    private String salesName;
    private String salesPhone;
    private String salesColor;
    private String salesDate;
    private int modelNum;
    private CarInfoVO carInfoVO;
}
