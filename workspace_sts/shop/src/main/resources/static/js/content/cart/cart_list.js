setFinalPrice();

// 총 가격 계산하는 함수 
function setFinalPrice(){
    // 1번 방식 : 총 가격 다 가져와서 가격 책정
    // 2번 방식 : 체크된 상품들의 가격 책정 (체크박스 체크 변경에 따라 총 가격이 변경과 호환되도록)
    // 체크된 장바구니 상품의 총 가격을 모두 더해서 계산

    // 클래스가 chk인 태그 중, 체크된 태그만 선택
    const chks = document.querySelectorAll('.chk:checked');
    
    let finalPrice = 0;
    // chk : 대입되는 변수명
    // i : 반복 횟수
    chks.forEach(function(chk, i){
        // chk 각각 같은 행에 있는 총 가격 데이터 찾기
        const strPrice = chk.closest('tr').children[5].textContent; // closest : 내가 선택한 태그에서 가장 가까운 태그 선택
        // console.log(price);

        // 정규식을 사용해서 쉼표, 원화 표시 제거
        const regex = /[^0-9]/g;
        const price = parseInt(strPrice.replace(regex, '')); // ￦20,000 > 20000 으로 change
        finalPrice = finalPrice + price;

    });
    
    document.querySelector('#finalPrice-span').textContent = finalPrice.toLocaleString();
    
}

// 체크박스 작동 (ALL CHK)
function chkAll(){
    const chkAll = document.querySelector('#chkAll');
    const chks = document.querySelectorAll('.chk'); //tbody 태그 속 생성되는 체크박스
    
    const isChecked = chkAll.checked;

    if(isChecked){
        for(const act of chks){
            act.checked = true;

        }
    }
    else{
        for(const act of chks){
            act.checked = false;
            
        }
    }
    setFinalPrice();
}

// 삭제 버튼
function deleteCart(cartCode){
    const result = confirm('선택한 상품을 장바구니에서 삭제하시겠습니까?');
    if(result){
        location.href=`/cart/deleteCart?cartCode=${cartCode}`;
    }
    
}

// 장바구니 상품 수량 변경
function changeCnt(selectedTag, cartCode, itemPrice){

    if(confirm('상품 수량을 변경하시겠습니까?')){

        const cartCnt = parseInt(selectedTag.closest('.row').querySelector('input[type="number"]').value);
        // selectedTag.parentElement.previousElementSibling.firstElementChild.value : 위와 동일
        const priceCalcul = selectedTag.closest('tr').querySelector('.totalPrice-span');
        // selectedTag.closest('td').nextElementSibling.firstElementChild;
        console.log(priceCalcul);
        
        fetch('/cart/changeCnt', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
            // 데이터명 : 데이터값
            cartCode : cartCode,
            cartCnt : cartCnt
            })
        })
        .then((response) => {
            if(!response.ok){
                alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                return ;
            }
        
            return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
            //return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            priceCalcul.textContent = '₩' + (cartCnt * itemPrice).toLocaleString();
            setFinalPrice();

            alert('상품의 수량이 변경되었습니다.');

        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
    }
}

// 선택 삭제
function deleteSelection(){
    // 만약에 체크된 상품이 하나도 없다면
    // alert으로 '삭제할 상품을 선택하세요' 띄우기
    const selectChks = document.querySelectorAll('.chk:checked');

    if(selectChks.length == 0){
        alert('삭제할 상품을 선택하세요');
        return;
    }

    const chkArr = []; 
    // 컨트롤러로 넘겨줄 cartCode 값 가져오기
    // 체크된 체크박스에서 cartCode 값

    for(const selectChk of selectChks){
        console.log(selectChk.value);
        chkArr.push(selectChk.value);
    }

    location.href=`/cart/deleteCarts?cartCodeList=${chkArr}`;



}

function buySelection(){

    const chks = document.querySelectorAll('.chk:checked');
    if(chks.length == 0){
        alert('구매할 상품을 선택하세요');
        return;
    }
    const chkArr = [];
    for(const chk of chks){
        chkArr.push(chk.value);
    }
    location.href=`/buy/list?cartCodeList=${chkArr}`;

    
}


