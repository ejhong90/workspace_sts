

package com.green.shop.admin.controller;

import com.green.shop.admin.service.AdminServiceImpl;
import com.green.shop.admin.vo.SearchBuyVO;
import com.green.shop.buy.vo.ShopBuyVO;
import com.green.shop.item.service.ItemServiceImpl;
import com.green.shop.item.vo.CategoryVO;
import com.green.shop.item.vo.ImgVO;
import com.green.shop.item.vo.ItemVO;
import com.green.shop.util.UploadUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource(name = "itemService")
    private ItemServiceImpl itemService;

    @Resource(name = "adminService")
    private AdminServiceImpl adminService;

    // 상품 등록 페이지로 이동
    @GetMapping("/regItemForm")
    public String regItemForm(Model model){


        // 카테고리 목록 조회
        List<CategoryVO> categoryList = itemService.showCategory();
        // 조회한 카테고리 html로 전송
        model.addAttribute("categoryList", categoryList);

        model.addAttribute("page", 2);
        return "content/admin/reg_item_form";
    }

    // 상품 등록 기능
    @PostMapping("/regItem")
    public String regItem(ItemVO itemVO, @RequestParam(name = "main")MultipartFile main
                        , @RequestParam(name = "details")MultipartFile[] details){

        // 단일 파일 첨부 메소드 호출 (메인 이미지)
        ImgVO mainImgVo = UploadUtil.uploadFile(main); 

        // 파일 첨부 (다중)
        List<ImgVO> imgList = UploadUtil.multiUploadFile(details);

        // 다음에 들어갈 ITEM_CODE 조회
        int itemCode = adminService.selectNextItemCode();

        // 상품 등록 DB 작업
        itemVO.setItemCode(itemCode); // 앞서 조회한 ITEM_CODE 값을 세팅

        // --------------- 상품 이미지 정보 등록 DB 작업 -------------- //
        // ItemVO에 빈값 채우기
        // 이미지 등록 시 등록하는 상품 코드
        mainImgVo.setItemCode(itemCode); // 단일 itemCode 등록
        for(ImgVO img : imgList){
            img.setItemCode(itemCode); /* 다중 itemCode 등록 */
        }
        imgList.add(mainImgVo);
        itemVO.setImgList(imgList); // itemVO 속 imgList에 상세페이지 데이터 입력!

//        System.out.println(itemVO);
        // 상품 등록 시 버튼을 클릭했을 때 전송되는 데이터를 itemVO에 담고 있음
        adminService.insertItem(itemVO);
        return "redirect:/admin/regItemForm";
    }

    // 구매 내역 페이지 이동
    @RequestMapping("/history")
    public String adminHistory(Model model, SearchBuyVO searchBuyVO){
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + searchBuyVO);

        List<ShopBuyVO> buyList = adminService.selectAdminBuys(searchBuyVO);
        model.addAttribute("buyList", buyList);

        model.addAttribute("page", 1);
//        System.out.println(buyList);
        return "content/admin/admin_history";
    }

    // 구매 상세 내역 보기
    @ResponseBody
    @PostMapping("/detailHistory")
    public ShopBuyVO detailHistory(@RequestParam(name = "buyCode")int buyCode){
        // 구매 상세 내역 조회
        ShopBuyVO result = adminService.selectDetailHistory(buyCode);
//      System.out.println(result);

        return  result;
    }
    // 상품 정보 변경 페이지 이동
    @GetMapping("/itemCondition")
    public String itemCondition(Model model, @RequestParam(name = "itemCode", required = false, defaultValue = "0")int itemCode){
//        System.out.println(adminService.selectItemCondition());
        model.addAttribute("items", adminService.selectItemCondition());
        model.addAttribute("page", 4);
        model.addAttribute("updateItemCode", itemCode);
        return "content/admin/update_item";
    }

    // 상품 정보 중 선택한 상품의 정보 상세 조회
    @ResponseBody
    @PostMapping("/detailItem")
    public Map<String, Object> detailItem(@RequestParam(name = "itemCode")int itemCode){
        // 상세 목록 조회
        ItemVO itemDetail = adminService.showDetail(itemCode);
        // 카테고리 전체 목록 조회
        List<CategoryVO> cateList = itemService.showCategory();

        // 위의 두 데이터를 담을 Map 객체 형성
        Map<String, Object> map = new HashMap<>();
        map.put("itemDetail", itemDetail);
        map.put("cateList", cateList);
        return map;
    }

    // 상품 변경
    @PostMapping("/updateItem")
    public String updateItem(ItemVO itemVO){
        adminService.updateItem(itemVO);
        return "redirect:/admin/itemCondition?itemCode=" + itemVO.getItemCode();
    }

}

