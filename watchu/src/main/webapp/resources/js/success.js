$(document).ready(function(){
	$.ajax({
		url:'',
		type:'post',
		data:JSON.stringify(param),
		dataType:'json',
		cache:false,
		timeout:30000,
		success:function(obj){
			
		},
		error:function(){
			alert('네트워크 오류!');
		}
	});
});