package com.green.shop.study.fetch.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
@Getter
@Setter
@ToString
public class MapDataVO {
    private int cartCode;
    private MapDataMember memberInfo;
    private MapDataItem itemInfo;
}

@Setter
@Getter
@ToString
class MapDataMember{
    private String memberId;
    private String memberName;
}

@Setter
@Getter
@ToString
class MapDataItem{
    private String itemName;
    private int itemCode;
    private int itemPrice;
    private List<ImgInfo> imgList;

}

@Getter
@Setter
@ToString
class ImgInfo{
    private String imgName;
    private int imgCode;
}