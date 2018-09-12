$(document).ready(function(){
	//검색 유효성 체크
	$('#search_form').submit(function(){
		if($('#keyword').val()==''){
			alert('검색어를 입력하세요!');
			$('#keyword').focus();
			return false;
		} 
	});

	//==============답변==============//
	var currentPage;
	var count;
	var rowCount;
	
	//-----답변 목록-----//
	function selectData(pageNum, contact_num){
		currentPage = pageNum;
		
		if(pageNum == 1){
			$('#output').empty();
		}
		//로딩 이미지 노출 
		$('#loading').show();
		
		$.ajax({
			type: 'post',
			data: {pageNum:pageNum, contact_num:contact_num},
			url: '/watchu/admin/listReply.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				$('#loading').hide();
				count = data.count;
				rowCount = data.rowCount;
				var list = data.list;
				
				if(count < 0 || list == null){
					alert('목록 호출 오류!');
				}else{
					$(list).each(function(index, item){
						var output = '<div class="item">';
							output += 	'<h4>관리자 답변</h4>';
							output += 	'<div class="sub-item">';
							output += 		'<p>' + item.recontent + '</p>';
							output +=		item.reg_date;
							
							//댓글 수정&삭제 버튼
							output += '<input type="button" data-num="'+item.recontact_num+'" value="수정" class="reply_mod">';
							output += '<input type="button" data-num="'+item.recontact_num+'" value="삭제" class="reply_del">';
							
							output +=	'<hr size="1" noshade>';
							output +=	'</div>';
							output += '</div>';
						
						//문서 객체에 추가	
						$('#output').append(output);	
					});
					//페이징
					if(currentPage >= Math.ceil(count/rowCount)){
						$('.paging-button').hide();
					}else{
						$('.paging-button').show();
					}
				}
			},
			error:function(){
				$('#loading').hide();
				alert('네트워크 오류');
			}
		});
	}
	//다음 댓글 보기 버튼 클릭 시 데이터 추가
	$('.paging-button input').click(function(){
		var pageNum = currentPage + 1;
		selectData(pageNum, $('#contact_num').val());
	});
	
	//-----답변 등록-----//
	$('#reply_div').submit(function(event){
		if($('#recontent').val() == ''){
			alert('내용을 입력하세요!');
			$('#recontent').focus();
			return false();
		}
		
		var data = {
				contact_num: $('#contact_num').val(),
			    recontent: $('#recontent').val()
		};
		
		console.log(data);
		
		//데이터 등록
		$.ajax({
			type: 'post',
			data:data,
			url: '/watchu/admin/writeReply.do',
			dataType: 'json',
			cache:false,
			timeout: 30000,
			success: function(data){
				if(data.result == 'logout'){
					alert('로그인 해야 작성할 수 있습니다.');
				}else if(data.result == 'success'){
					initForm();
					selectData(1, $('#contact_num').val());
					alert('데이터 등록');
				}else{
					alert('등록 시 오류 발생');
				}
			},
			error: function(){
				alert('네트워크 오류 발생');
			}
		});
		//기본 이벤트 제거
		event.preventDefault();
	});
	//답변 작성 폼 초기화
	function initForm(){
		$('textarea').val('');		
	}
	
	//수정 버튼 클릭 시 수정폼 노출
	
	
	
	//초기 데이터(목록) 호출
	selectData(1, $('#contact_num').val());
});