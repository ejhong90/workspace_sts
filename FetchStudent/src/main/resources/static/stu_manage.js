function goSelect(){
    const classCode = document.querySelector('#class-selector').value;
    location.href=`/stu/list?classCode=${classCode}`;
    
}

// 선택한 반에 소속된 학생들의 목록 조회
function fetchSelect(){
    const classCode = document.querySelector('#class-selector').value;

    fetch('/stu/fetchSelect', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           classCode : classCode
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
        // 기존 테이블 내용 삭제
        // 태그 선택
        const rowBodyTags = document.querySelectorAll('.table > .rowBody');
        
        // rowBodyTag 안의 모든 내용 삭제
        for(const rowBody of rowBodyTags){
            rowBody.remove();
        }

        // 새로 조회한 데이터로 tbody 안의 내용을 채워 줌
    
        let str = ``;
        // for(const stu of data){
        //     str += `<div class="rowBody">   
        //         <div class="cell"></div>
        //         <div class="cell">${stu.className}</div>
        //         <div class="cell">${stu.stuNum}</div>
        //         <div class="cell">${stu.stuName}</div>
        //     </div>`  
            
        // }
        // No를 카운팅할 방법이 없음

        // element = 위 반복문의 stu 빼낸 값을 대입할 변수
        // index = 몇 번 반복할래
        //  data.foreach(function(element, index){});
        data.forEach(function(stu, i){
            str += `<div class="rowBody">   
                <div class="cell">${data.length - i}</div>
                <div class="cell">${stu.className}</div>
                <div class="cell">${stu.stuNum}</div>
                <div class="cell" onclick="goScore(${stu.stuNum})">${stu.stuName}</div>
            </div>`

         });

        // for(let i = 0 ; i < data.lenght ; i++){
        //     str = `<div class="rowBody">   
        //         <div class="cell">${data.lenght -1}</div>
        //         <div class="cell">${data[i].className}</div>
        //         <div class="cell">${data[i].stuNum}</div>
        //         <div class="cell">${data[i].stuName}</div>
        //     </div>`
        //     const tableTag = document.querySelector('.table');
        //     tableTag.insertAdjacentHTML('beforeend', str); 
        // }
        const tableTag = document.querySelector('.table');
        tableTag.insertAdjacentHTML('beforeend', str);
        

        // rowBodyTag 안의 모든 내용 삭제
        //rowBodyTag.innerHTML = '';
        

    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

function goScore(stuNum){
    
    //const stuNum = document.querySelector('#name-span').value;
    fetch('/stu/score', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
            stuNum : stuNum


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

        let avg = 0.0;
        if(data.scoreVO != null){
            avg = (data.scoreVO.korScore + data.scoreVO.engScore + data.scoreVO.mathScore) / 3.0;
        }
        // 조회된 데이터로 그림 그리기
        
        // 그림을 그릴 태그 선택
        const detail_div = document.querySelector('.stu-detail-div');
        detail_div.innerHTML='';

        let str = `
            <div>
                <div>
                    <div> 학생 기본 정보</div>
                    <div>
                        <table>
                            <thead>
                                <tr>
                                    <td>학번</td>
                                    <td>소속반</td>
                                    <td>학생명</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="stuNumTd">${data.stuVO.stuNum}</td>
                                    <td>${data.stuVO.className}</td>
                                    <td>${data.stuVO.stuName}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                
                <div>
                    <div>학생 점수 정보</div>
                    <div>
                        <table>
                            <thead>
                                <tr>
                                    <td>국어점수</td>
                                    <td>영어점수</td>
                                    <td>수학점수</td>
                                    <td>평균</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="scoreTd">${data.scoreVO == null ? 0 : data.scoreVO.korScore}</td>
                                    <td class="scoreTd">${data.scoreVO == null ? 0 : data.scoreVO.engScore}</td>
                                    <td class="scoreTd">${data.scoreVO == null ? 0 : data.scoreVO.mathScore}</td>
                                    <td>${avg}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div>
                    <input id="btn" type="button" value="점수입력" onclick="setInput()">
                </div>
            </div>
            
        `;
        detail_div.insertAdjacentHTML('afterbegin', str);
        

    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
    
    
}

function setInput(){
    document.querySelector('#btn');
    if(btn.value == '점수입력'){
        const tds = document.querySelectorAll('.scoreTd');
        // for(let i = 0 ; i < tds.length ; i++){
        //     tds[i].textContent = '<input type="text">';
        // }
        
        // for(const e of tds){
        //     e.textContent = '<input type="text">';
        // }
    
        tds.forEach(function(e, i){
            e.innerHTML = '<input type="text" class="scoreInput" >';
        });
    
        document.querySelector('#btn').value = '저장';
    }
    else if(btn.value == '저장'){
        const result = confirm('입력한 정보로 점수를 등록할까요?');
        if(result){
            insertScore();    
        }

        
    }

    
}


function insertScore(){
    const inputs = document.querySelectorAll('.scoreInput');
    const stuNum = document.querySelector('.stuNumTd').textContent;

    // alert(inputs[0].value);
    // alert(inputs[1].value);
    // alert(inputs[2].value);
    // alert(stuNum);

    fetch('/stu/insertScore', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
        // 데이터명 : 데이터값
        'korScore'  : inputs[0].value,
        'engScore'  : inputs[1].value, 
        'mathScore' : inputs[2].value,
        'stuNum'    : stuNum

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
        alert('점수가 등록되었습니다.');
        goScore(stuNum);
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}
