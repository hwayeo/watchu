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
	//답변 등록
	$('#re_form').submit(function(event){
		if($('#recontent').val() == ''){
			alert('내용을 입력하세요!');
			$('#recontent').focus();
			return false();
		}
		
		var data = $(this).serialize();
		
		$.ajax({
			type: 'post',
			data:data,
			url: '/watchu/user/writeReply.do',
			dataType: 'json',
			cache:false,
			timeout: 30000,
			success: function(data){
				if(data.result == 'logout'){
					alert('로그인 해야 작성할 수 있습니다.');
				}else if(data.result == 'success'){
					initForm();
					selectData(1, $('#contact_num').val());
				}else{
					alert('등록 시 오류 발생');
				}
			},
			error: function(){
				alert('등록 시 네트워크 오류 발생');
			}
		});
		event.preventDefault();
	});
	function initForm(){
		$('textarea').val('');		
	}
});