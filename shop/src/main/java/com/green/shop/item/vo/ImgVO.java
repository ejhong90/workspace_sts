package com.green.shop.item.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ImgVO {
    private int imgCode;
    private String originFileName;
    private String attachedFileName;
    private String isMain;
    private int itemCode;

}
