$(document).ready(function(){
	
   //이미지에 마우스를 올렸을 시 평점 오버레이      
   $(document).on('mouseover','.thumbnail',function(){
      $(this).find('.overlay').css('display','block');
   }); 
   //이미지에서 마우스가 벗어날 시 원상태 복귀
   $(document).on('mouseout','.thumbnail',function(){
      $(this).find('.overlay').css('display','none');
   });

   /* // ajax(비동기처리)로 input 값을 해당 영화 고유번호로 저장(누적)
   $(document).on('click','.rating',function(event){
      var movie_num = $(this).find('input[name="rating"]').attr('data-num');
      var id = $('#user_id').val();
      var rate = $(this).find('input[name="rating"]:checked').val();

      if(id == null || id == ""){
         alert('로그인을 해야 서비스를 이용할 수 있습니다.');

         return false;
      }
      movieRate(movie_num,id,rate);
   }); */
   // ajax(비동기처리)로 input 값을 해당 영화 고유번호로 저장(누적)
   $(document).on('click','input[name=rating]',function(event){
         var movie_num = $(this).attr('data-num');
         var rate = $(this).val();
         var id = $('#user_id').val();
         if(id == null || id == ""){
            alert('로그인을 해야 서비스를 이용할 수 있습니다.');
            
            return false;
         }
         movieRate(movie_num,rate,id);
   });
	
	function movieRate(movie_num,rate,id){
		$.ajax({
		   url:'rating.do',
		   type:'post',
		   data:{movie_num:movie_num,id:id,rate:rate},  //평가 점수 db에 저장
		   dataType:'json',
		   timeout:30000,
		   cache:false,
		   success:function(data){
			   if(data.result == 'insert'){
				   alert('입력하신 점수가 성공적으로 저장되었습니다.');

               return false;
            }else if(data.result == 'update'){
               alert('입력하신 정보로 수정되었습니다.');

               return false;
            }else if(data.result == 'login'){
               alert('로그인을 하시지 않으면 서비스를 이용할 수 없습니다.');
            }

            // radio 별 평가 후 초기화
            $('input:radio[name=rating]:input[value=""]').attr("checked", true); 
         },
         error:function(){
            alert('네트워크 오류');
         }
      });
   }
});