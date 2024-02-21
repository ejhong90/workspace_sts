const updateItemCode = document.querySelector('#updateItemCode').value;

if(updateItemCode != 0){
    showDetailInfo(updateItemCode);
}



function showDetailInfo(itemCode){

    fetch('/admin/detailItem', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           itemCode : itemCode
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        console.log(data);

        const make_spot = document.querySelector('.make-spot-div');
        const itemStatus_tags = document.querySelectorAll('input[type="radio"]');

        make_spot.innerHTML = '';
   
        let str = '';
        str += `
        <form action="/admin/updateItem" method="post">
            <h5>
            상품 기본정보
            </h5>
            <div class="row bg-primary bg-opacity-10 rounded" style="padding-top: 2%; padding-bottom: 2%;">
                <div class="col">
                
                <input type="hidden" name="itemCode" value="${data.itemDetail.itemCode}">
                    <div class="row align-middle">
                    
                        <div class="col-3 text-center">카테고리</div>
                        
                        
                        <div class="col d-grid ">
                            <select name="cateCode" class="form-select">`;
        for(const e of data.cateList){
            if(data.itemDetail.cateCode == e.cateCode){
                str += `<option value="${e.cateCode}" selected>${e.cateName}</option>`;
            }
            else{
                str += `<option value="${e.cateCode}">${e.cateName}</option>`;
            }
        }                    
                            
        str +=  `</select>
                        </div>
                    </div>
                    <div class="row align-middle">
                        <div class="col-3 text-center">상품수량</div>
                        <div class="col d-grid">
                            <input class="form-control" type="number" name="itemStock" value="${data.itemDetail.itemStock}">
                        </div>
                    </div>
                    <div class="row align-middle">
                        <div class="col-3 text-center">상품명</div>
                        <div class="col d-grid">
                            <input class="form-control" type="text" name="itemName" value="${data.itemDetail.itemName}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-3 text-center">상품상태</div>
                        <div class="col">
                            <div class="row">`
        
            if(data.itemDetail.itemStatus == 1){
                str += ` <div class="col">
                            <input class="form-check-input" type="radio" name="itemStatus" value="1" checked> 준비중 
                        </div>
                        `;
            }
            else{
                str += ` <div class="col">
                            <input class="form-check-input" type="radio" name="itemStatus" value="1"> 준비중 
                        </div>
                        `;
            }
            
            
            if(data.itemDetail.itemStatus == 2){
                str += ` 
                        <div class="col">
                            <input class="form-check-input" type="radio" name="itemStatus" value="2" checked> 판매중 
                        </div>`;
            }
            else{
                str += ` 
                        <div class="col">
                            <input class="form-check-input" type="radio" name="itemStatus" value="2"> 판매중 
                        </div>`;
            }
            if(data.itemDetail.itemStatus == 3){
                str += `<div class="col">
                <input class="form-check-input" type="radio" name="itemStatus" value="3" checked> 매진 
            </div>`;
            }
            else{str += `
                <div class="col">
                    <input class="form-check-input" type="radio" name="itemStatus" value="3"> 매진 
                </div>`;}

        str +=                  `
                            </div>
                        </div>
                        
                    </div>
                </div>
            </div>
            <div class="row mt-1">
                <div class="col ">
                    <div class="row mt-3">
                        <div class="col">
                            <h5>상품 이미지 정보</h5>
                        </div>
                    </div>
                    <div class="row bg-primary bg-opacity-10 rounded">
                        <div class="col ">
                            <div class="row">
                                <div class="offset-1 col-3">
                                    메인이미지 
                                </div>
                                <div class="col">`;
        for(const e of data.itemDetail.imgList){
            if(e.isMain == 'Y'){
                str += `<span class="pointer-span" onclick="showModal('${e.attachedFileName}')">${e.originFileName}</span>`;
                console.log(e.attachedFilName);
            }
        }                            
        str +=          `</div>
                            </div>
                            <div class="row mt-2">
                                <div class="offset-1 col-3">
                                    상세이미지 
                                </div>`;
        let cnt = 0;
        data.itemDetail.imgList.forEach(function(e, idx){
            if(e.isMain == 'N'){
                if(cnt == 0){
                    str += `
                        <div class="col" ">
                            <span class="pointer-span" onclick="showModal('${e.attachedFileName}')">${e.originFileName}</span>
                        </div>
                    `;

                    cnt++;
                } else {
                    str += `
                        <div class="offset-4 col" onclick="showModal('${e.attachedFileName}')">
                           <span class="pointer-span" onclick="showModal('${e.attachedFileName}')">${e.originFileName}</span>
                        </div>
                    `;
                }

            }
        });
                               
        str +=                 `</div>
                            </div>
                        </div>
                    </div>
                    <div class="row mt-3 ">
                        <div class="col-3">
                            <input class="btn btn-primary" type="submit" value="변경">
                        </div>
                    </div>
                    
                </div>
            </div>
            </form>
        `
        make_spot.insertAdjacentHTML('afterbegin',str);
        

    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });


}

// 이미지 모달창 띄우기
function showModal(attachedFileName){
    console.log(attachedFileName);

    const img_detail_modal = new bootstrap.Modal('#img-detail-modal');
    const img_tag = document.querySelector('#img-detail-modal img');
    img_tag.innerHTML = '';
    img_tag.src=`/upload/${attachedFileName}`;
    img_detail_modal.show();


}