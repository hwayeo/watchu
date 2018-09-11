$(document).ready(function(){
	$('.mailButton').click(function(){
		//버튼을 누를 시 해당 버튼이 사라짐
		$('.mailButton').css('display','none');
		
		//버튼이 사라지면 인증번호를 쓸 수 있는 input창이 생김
		$('.permitInput').css('display','block');
	});
	
	//가입 버튼 클릭 시 db에 저장된 인증키와 다르면 가입 불가로 회원가입 페이지 계속 머물기
	
});