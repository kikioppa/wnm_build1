package com.example.dodgema.service;


import com.example.dodgema.model.Spirit;
import com.example.dodgema.repository.SpiritRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;
import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SpiritService {
    private final SpiritRepository spiritRepository;
    private final com.example.dodgema.configration.WebConfig webConfig;
    /*private final com.example.dodgema. imgConfig;*/


    // merge 병합
    @Transactional
    public void saveItem(Spirit spirit) {
        spiritRepository.save(spirit);
    }

    // 변경 감지
    @Transactional
    public void updateItem(Long id, String spiritName,String spiritImg) {
        Spirit spirit = spiritRepository.findOne(id);
        spirit.setSpiritName(spiritName);
        if(!(spiritImg == null)) {
            /*spirit.setSpiritImg(spiritImg);*/

        /*spirit.setPrice(price);
        item.setStockQuantity(stockQuantity);
        item.setMemo(memo);*/

        }
    }

    public List<Spirit> findSpirits(){
        return spiritRepository.findAll();
    }

    public Spirit findOne(Long id) {
        return spiritRepository.findOne(id);
    }


 /*   // 외부 환경에 따라 수정해야 할 필요 있음
    public String uploadFile(MultipartFile files) throws IOException {
        if(files.getSize() == 0) {
            return null;
        }

            String baseDir = "C:\\Users\\irlink\\Documents\\uploadFiles";
            String filePath = baseDir + "\\" + files.getOriginalFilename();
            files.transferTo((new File(filePath)));
            //spring security

            return filePath;

    }*/


   /*     // 확장자를 제외한 파일이름
        String fileName = file.getOriginalFilename();
        int index = fileName.lastIndexOf(".");
        System.out.println(fileName + ", " + index);

        // 파일 확장자
        String fileExtension = fileName.substring(index + 1);

        // 파일 이름 랜덤 설정 후 확장자 부여
        String savedName = UUID.randomUUID().toString() + "." + fileExtension;

        // 지정한 경로에 복사하여 파일 저장
        File target = new File(webConfig.getFileRealPath(), savedName);
        FileCopyUtils.copy(file.getBytes(), target);


        // '/img/파일명'을 db에 저장
        String path = target.getPath();
        System.out.println("패쓰"+path);
        int pathIndex = path.lastIndexOf("uploadFiles");
        System.out.println("패쓰인덱스"+pathIndex);*/



    public void deleteItem(Long id) {
        spiritRepository.delete(spiritRepository.findOne(id));
    }

    public List<Spirit> orderByReview() {
        return spiritRepository.findAllOrderByReview();
    }

    /*public List<Spirit> orderBySaleCount() {
        return spiritRepository.findAllOrderBySale();
    }*/

    public List<Spirit> findBySort(String sort) {
        String[] sortList = {"home", "sale", "review", "book", "album", "movie"};
        int select = 1;

        for (String list : sortList) {
            if(list.equals(sort)){
                switch (select){
                    case 1 :
                        return findSpirits();
                    case 2:
                        return orderByReview();
                    //case 3: return orderBySaleCount();
                    default:
                        return spiritRepository.findByCategory(select);
                }
            }
            select++;
        }

        return null;
    }


}
