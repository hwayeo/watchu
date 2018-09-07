$(document).ready(function(){
	
	//=========================================회원가입 유효성체크====================================================
	
	var checkId = 0;
	
	
	//아이디 중복 체크
	$('#confirmId').click(function(){
		if($('#id').val()==''){
			alert('아이디를 입력하세요!');
			$('#id').focus();
			return;
		}
		
		//var regMsg = new RegExp('^[A-Za-z0-9+]{4,12}$');//영문이나숫자 4~12자로만 아이디 가능(한글 불가),첫번째방법
		/*var regMsg = /^[A-Za-z0-9+]{4,12}$/;//두번째 방법

		if(!regMsg.test($('#id').val())){
			alert('영문,숫자 4자이상 12자 이하');
			$('#id').focus();
			return;
		}*/
		
		//세번째방법(어노테이션)-memberCommand로
		
		
		$('#message_id').text(''); //메시지 초기화
		$('#error_id').text('');//유효성체크시 보여주는 메시지 초기화
		$('#loading').show(); //로딩이미지 노출
		
		
		$.ajax({
			url:'confirmId.do',
			type:'post',
			data:{id:$('#id').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				$('#loading').hide();//로딩이미지 감추기
				
				if(data.result == 'idNotFound'){				
					$('#message_id').css('color','blue')
					                .text('등록가능 ID');
					checkId =1;
					
				}else if(data.result == 'idDuplicated'){			
					$('#message_id').css('color','red')
	                                .text('중복된 ID');
					$('#id').val('').focus();
					checkId =0;

				}else{
					alert('ID중복체크 오류');
				}
				
			},
			error:function(){
				$('#loading').hide();//로딩이미지 감추기
				alert('네트워크 오류 발생');
			}
		});
	});
	
	//아이디 중복 안내 메시지 초기화 및 아이디 중복 값 초기화
	$('#insert_Form #id').keyup(function(){
		$('#message_id').text('');
	});
	
	//submit 이벤트 발생시 아이디 중복 체크 여부/비밀번호일치여부 확인
	$('#insert_Form').submit(function(){
		if(checkId==0){
			alert('아이디 중복 체크 필수');
			if($('#id').val()==''){
				$('#id').focus();
			}
			return false;
		}
		
		if($('#passwd').val()!= $('#cpasswd').val()){
			alert('비밀번호와 비밀번호확인이 불일치 합니다');
			$('#passwd').val('').focus();
			$('#cpasswd').val('');
			return false;
		}
	});
	
	//=========================================회원수정 유효성체크====================================================
	
	$('#updateUser_Form').submit(function(){
				
		if($('#passwd').val()!= $('#cpasswd').val()){
			alert('비밀번호와 비밀번호확인이 불일치 합니다');
			$('#passwd').val('').focus();
			$('#cpasswd').val('');
			return false;
		}
	});
	
	//=========================================회원삭제 유효성체크====================================================
	$('#deleteUser_Form').submit(function(){
		
		if($('#passwd').val()!= $('#cpasswd').val()){
			alert('비밀번호와 비밀번호확인이 불일치 합니다');
			$('#passwd').val('').focus();
			$('#cpasswd').val('');
			return false;
		}
	});
});