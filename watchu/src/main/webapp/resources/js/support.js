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
	
	//-----답변 수정-----//
	//수정 버튼 클릭 시 수정폼 노출
	$(document).on('click', '.reply_mod', function(){
		var recontact_num = $(this).attr('data-num');
		var recontent = $(this).parent().find('p').text();
		
		//댓글 수정 폼
		var modifyUI = '<form id="mre_form>';
			modifyUI +=		'<input type="hidden" name="recotact_num" id="mrecontact_num" value="'+recontact_num+'">';
			modifyUI +=		'<textarea rows="3" cols="50" name="recontent" id="mrecontent" class="rep-content">'+recontent+'<textarea>';
			modifyUI +=		'<div id="mre_btn>';
			modifyUI +=			'<input type="submit" value="수정">';
			modifyUI +=			'<input type="button" value="취소" class="re-reset">';
			modifyUI +=		'</div>';
			modifyUI +=		'<hr size="1" noshade>';
			modifyUI +=	'</form>';
			
		initModifyForm();
		$(this).parent().hide();
		$(this).parent('.item').append(modifyUI);
	});
	
	//댓글 수정 폼 취소버튼 -> 수정폼 초기화
	$(document).on('click', '.re-reset', function(){
		initModifyForm();
	});
	
	//댓글 수정폼 초기화
	function initModifyForm(){
		$('.sub-item').show();
		$('#mre_form').remove();
	}
	
	//답변 수정
	$(document).on('submit', '#mre_form', function(event){
		if($('#mrecontent').val() == ''){
			alert('내용을 입력하세요');
			$('#mrecontent').focus();
			return false();
		}
		
		var data = $(this).serialize();
		console.log(data);
		
		$.ajax({
			url: '/watchu/admin/updateReply.do',
			type: 'post',
			data: data,
			dataType: 'json',
			cache: false,
			timeout: 10000,
			success: function(data){
				if(data.result == 'logout'){
					alert('로그인이 필요합니다!');
				}else if(data.result == 'success'){
					$('#mre_form').parent().find('p').text($('#mrecontent').val());
					//수정폼 초기화
					initModifyForm();
				}
			},
			error: function(){
				alert('댓글 수정 네트워크 오류!');
			}
		});
		event.preventDefault();
	});

	//초기 데이터(목록) 호출
	selectData(1, $('#contact_num').val());
});