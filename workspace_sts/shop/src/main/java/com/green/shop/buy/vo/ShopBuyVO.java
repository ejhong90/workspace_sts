package com.green.shop.buy.vo;

import com.green.shop.admin.vo.SearchBuyVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class ShopBuyVO {
    private int buyCode;
    private String memberId;
    private int buyPrice;
    private String buyDate;
    private List<BuyDetailVO> buyDetailList;

    private SearchBuyVO searchVO;
}
