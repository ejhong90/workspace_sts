
// serach 버튼 클릭 시, 주소록 검색 팝업창 띄우기
function searchAddress(){
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            document.querySelector('#postCode').value = data.zonecode;
            document.querySelector('#roadAddr').value = data.roadAddress;
            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
            
        }
    }).open();
}

// join 버튼 클릭 시 form 태그를 submit!
function memberJoin(){
    // submit 전에 유효성 검사 (validation check)
    // 1) ID 입력 여부 확인
    const idInput = document.querySelector('#memberId');
    if(idInput.value == ''){
        alert('id는 필수입력 사항입니다.');
        return ;
    }

    // 2) id 입력 문자의 길이가 20을 초과하는 지 검사
    if(idInput.value.length > 20){
        alert('id는 20글자 내로 작성하세요');
        return ;
    }

    // 3) pw 두개 입력 -> 일치 확인
    const pwInput = document.querySelector('#memberPw');
    const confirmInput = document.querySelector('#confrimPw');
    // document.querySelectorAll('input[tpye="password"]').value;
    if(pwInput.value != confirmInput.value){
        alert('입력한 두 비밀번호가 다릅니다. \n비밀번호를 다시 입력해주세요.');
        return ;
    }

    // 정규식 비밀번호 (문자 + 숫자 + 특수문자가 포함된 4~12 이내의 글자 )
    const regExp = /^.*(?=^.{4,12}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
    // 함수 test의 매개변수로 들어온 문자가 정규식 조건에 부합하면 return = true
    const expResult = regExp.test(pwInput.value);
    // if(!expResult){
    //     alert('비밀번호는 대문자, 소문자, 숫자, 특수문자를 1개 이상씩 포함한 4~12 이내 글자로 작성해주세요.');
    //     return ;
    // }

    // 이메일 주소
    const emailInput = document.querySelector('#memberEmail');
    if(emailInput.value == ''){
        alert('이메일을 입력하세요');
        return ; 
    }

    // 1. submit 시킬 form 태그 선택 (const joinModal)
    document.querySelector('#join-form').submit();

    
}

// Login 클릭 시 로그인 페이지로 이동
function goLoginForm(){
    location.href = '/member/loginForm';
}

// 로그아웃 함수
function logout(){
    const result = confirm('로그아웃 하시겠습니까?');

    if(result){
        location.href = '/member/logout';
    }
}

// 2. 모달 창이 닫히면 form 태그 reset
// 회원가입 모달창이 닫힐 때 실행되는 이벤트
// 1. 모달창 선택
const joinModal = document.querySelector('#join-modal');
// 2. 선택한 태그에 이벤트 달아주기
// hidden.bs.modal : 모달창이 닫힐때 실행
joinModal.addEventListener('hidden.bs.modal', function(event){
    document.querySelector('#join-form').reset();
});


function goHome(){
    location.href='/item/list';
}