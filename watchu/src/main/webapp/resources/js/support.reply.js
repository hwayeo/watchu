$(document).ready(function(){
	//==============답변==============//
	var currentPage;
	var count;
	var rowCount;
	
	//-----답변 목록-----//
	function selectData(pageNum, contact_num){
		currentPage = pageNum;
		console.log(pageNum);
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
							output += 	'<h5><b>관리자 답변</b>　　' + item.reg_date + '</h5>';
							output += 	'<div class="sub-item" style="margin-top:20px;">';
							output += 		'<p>' + item.recontent + '</p>';
							
							//댓글 수정&삭제 버튼
							output += '<input type="button" data-num="'+item.recontact_num+'" value="수정" class="reply_mod btn btn-sm btn-default"> ';
							output += '<input type="button" data-num="'+item.recontact_num+'" value="삭제" class="reply_del btn btn-sm btn-danger">';
							
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
				alert('답변 목록 네트워크 오류');
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
					alert('답변이 등록 되었습니다.');
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
		
		console.log(recontact_num);
		console.log(recontent);
		
		//댓글 수정 폼
		var modifyUI = '<form id="mre_form" class="form-group">';
			modifyUI += '<div align="center" style="margin:20px auto">';
			modifyUI +=		'<input type="hidden" name="recontact_num" id="mrecontact_num" value="'+recontact_num+'">';
			modifyUI +=		'<textarea rows="3" cols="50" name="recontent" id="mrecontent" class="rep-content form-control" style="width:80%">'+recontent+'</textarea>';
			modifyUI +=		'<div id="mre_btn" align="right" style="margin-top:10px;">';
			modifyUI +=			'<input type="submit" value="수정" class="btn btn-sm btn-default"> ';
			modifyUI +=			'<input type="button" value="취소" class="re-reset btn btn-sm btn-default">';
			modifyUI +=		'</div>';
			modifyUI +=		'<hr size="1" noshade>';
			modifyUI +=	'</div>';
			modifyUI +=	'</form>';
			
		initModifyForm(); //수정폼 초기화
		$(this).parent().hide();	//수정버튼을 감싸는 div의 데이터 숨김
		$(this).parents('.item').append(modifyUI);	//.item div에 수정하고자하는 데이터 노출
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

		//수정폼에 입력한 데이터 반환
		var data = {
				recontact_num: $('#mrecontact_num').val(),
			    recontent: $('#mrecontent').val()
		};
		
		//수정된 데이터 처리
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

	//-----답변 삭제-----//
	$(document).on('click', '.reply_del', function(){
		var recontact_num = $(this).attr('data-num');
		
		$.ajax({
			type:'post',
			url:'/watchu/admin/deleteReply.do',
			data:{recontact_num:recontact_num},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == 'logout'){
					alert('로그인해야  삭제 할 수 있습니다!');
				}else if(data.result == 'success'){
					alert('삭제 완료!');
					//전체 댓글 목록 새로 읽어 옴
					selectData(1, $('#contact_num').val());
				}else{
					alert('댓글 삭제 오류!');
				}
			},
			error:function(){
				alert('답변 삭제 네트워크 오류!');
			}
		});
	});
	
	//초기 데이터(목록) 호출
	selectData(1, $('#contact_num').val());
});