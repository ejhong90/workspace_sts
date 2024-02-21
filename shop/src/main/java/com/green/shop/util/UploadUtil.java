package com.green.shop.util;

import com.green.shop.item.vo.ImgVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// 파일 첨부와 관련된 기능 모음집
public class UploadUtil {
    // 파일의 확장자를 문자열로 리턴하는 메소드
    public static String getExtension(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    // UUID를 통한 파일명 생성 메소드
    public static String getUuid(){
        return UUID.randomUUID().toString();
    }

    // 단일 파일 업로드 하는 메소드
    public static ImgVO uploadFile(MultipartFile uploadFile){
        // 파일 미첨부 시 나오는 값 확인용
//        System.out.println(main.getOriginalFilename()); // 파일 미첨부하면 빈값으로 나옴
//        System.out.println(main.isEmpty()); // 미첨부 시, true 로 나옴
        ImgVO imgVO = null;
        // 파일 첨부 (단일)
        // 업로드할 경로 및 파일명을 문자로 나열
        // 첨부한 파일이 존재할 때에만 실행
        if(!uploadFile.isEmpty()){
            imgVO = new ImgVO();
            // 확장자 추출
            String extension = getExtension(uploadFile.getOriginalFilename());

            // 중복되지 않는 파일명 생성
            String fileName = getUuid() + extension;

            // 파일업로드
            try{
                File file1 = new File(ConstantVariable.UPLOAD_PATH + fileName);
                uploadFile.transferTo(file1);

                imgVO.setAttachedFileName(fileName);
                imgVO.setOriginFileName(uploadFile.getOriginalFilename());
                imgVO.setIsMain("Y");


            } catch (Exception e){
                System.out.println("!!! 단일 파일 첨부 중 예외 발생 !!!");
                e.printStackTrace();
            }
        }
        return imgVO;
    }

    // 다중 파일 업로드 메소드
    public  static List<ImgVO> multiUploadFile(MultipartFile[] uploadFiles){
        List<ImgVO> imgList = new ArrayList<>();
        for(MultipartFile uploadFile : uploadFiles){
            ImgVO vo = uploadFile(uploadFile);
            if(vo != null){
            vo.setIsMain("N");
            imgList.add(vo);

            }
        }
        return imgList;
    }


}
