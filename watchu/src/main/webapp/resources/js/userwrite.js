$(document).ready(function(){
	$('.mailBtn').click(function(){
		var email = $('#email').val();
		
		if(email == null || email == ''){
			alert('email을 입력해주세요.');
		}else{
			//버튼을 누를 시 해당 버튼이 사라짐
			$('.mailButton').css('display','none');
			//버튼이 사라지면 인증번호를 쓸 수 있는 input창이 생김
			$('.permitInput').css('display','block');

			//인증번호 받기 버튼 클릭 시 input에 쓴 email 주소로 인증번호 발송
			$.ajax({
				url:'mail.do',
				type:'post',
				data:{id:$('#id').val(),email:$('#email').val()}, //input에 쓴 id,email 값 가져오기
				dataType:'json',
				cache:false,
				timeout:8500,
				success:function(){
					//가져온 email 값으로 이메일을 보내 인증번호 보내기, db에 저장
					alert('입력하신 email로 인증메일이 발송되었습니다.');
				},
				error:function(){
					alert('네트워크 오류!');
				}
			});
		}
	});
	
	//permit에 쓴 값을 controller에 보내서 sessino값과 대조 후 success,error값을 받아오기
	$('.permitBtn').click(function(){
		var permitCheck = 0;
		
		$.ajax({
			url:'checkPermit.do',
			type:'post',
			data:{permit:$('#permit').val()}, //input에 쓴 인증코드 값만 가져오기
			dataType:'json',
			cache:false,
			timeout:8500,
			success:function(data){
				if(data.result == 'success'){
					permitCheck = 1;
					
					$('.permit').attr('readonly',true);
					$('.permitBtn').hide();
					$('.display').show().html('인증에 성공하였습니다.');
				}else{
					alert('입력하신 정보가 일치하지 않습니다.');
					permitCheck = 0;
					
					$('#permit').val('');
				}
				
				//permitCheck 값이 1일 경우에만 회원가입으로 이동
				if(permitCheck != 1){
					$('#submit').attr('disabled',true);
				}else{
					$('#submit').attr('disabled',false);
				}
			},
			error:function(){
				alert('네트워크 오류!');
			}
		});
	});
});