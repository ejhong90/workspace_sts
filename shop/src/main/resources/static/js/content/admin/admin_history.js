// 부트스트랩이 제공하는 모달 태그 선택 방법
const buy_detail_modal = new bootstrap.Modal('#buy-detail-modal');

// 모달 열기
// buy_detail_modal.show();
// 모달 닫기
// buy_detail_modal.hide();

// 행 클릭 시, 구매 상세 내역 조회 및 모달창 띄우기
function modalDetail(buyCode){
    console.log(buyCode);

    fetch('/admin/detailHistory', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           buyCode : buyCode

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
        
        // 모달 상세 정보 상단
        document.querySelector('#modal-buyCode').textContent = data.buyCode;
        document.querySelector('#modal-memberId').textContent = data.memberId;
        document.querySelector('#modal-buyPrice').textContent = data.buyPrice;
        document.querySelector('#modal-buyDate').textContent = data.buyDate;

        // 모달 상세 정보 하단
        const modal_tbody = document.querySelector('#modal-tbody');

        // modal_tbody.innerHTML = '';
        modal_tbody.replaceChildren();

        
        let str = '';
        data.buyDetailList.forEach(function(detail, i){
            str +=  `<tr>
                        <td>${data.buyDetailList.length - i}</td>
                        <td> <img width="20%" src="/upload/${detail.itemVO.imgList[0].attachedFileName}">
                        ${detail.itemVO.itemName}</td>
                        <td>${detail.buyCnt}</td>
                        <td>${detail.totalPrice}</td>
                    </tr>`
        });


        modal_tbody.insertAdjacentHTML('afterbegin', str);


        buy_detail_modal.show();
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}