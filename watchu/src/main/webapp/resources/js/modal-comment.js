$(document).ready(function(){
	//코멘트 
	$(document).on('click','.modal-comment',function(){
		cleanModal();
		
		var id = $(this).parents('.division1').find('.comment-id').text();
		$('#modal-output-id').text(id);
		
		var imgUrl = '<img src="../main/imageView.do?id='+id+'" width="35" height="35" class="img-circle" style="float: left; margin-right: 10px;">';
		$('#modal-output-title').html(imgUrl);

		var content = $(this).parents('.division1').find('.inner-box2').text();
		$('#modal-output-content').text(content);
		
		var likes = $(this).parents('.division1').find('.inner-box3').text();
		$('#modal-output-likes').text(likes);
		
		var reg_date = $(this).parents('.division1').find('.inner-box4').text();
		$('#modal-output-reg_date').text(reg_date);
		
	});  
	
	function cleanModal(){
		$('#modal-output-id').text('');
		$('#modal-output-content').text('');
		$('#modal-output-likes').text('');
		$('#modal-output-reg_date').text('');
		$('#modal-output-title').empty();
	}
	//코멘트 슬라이더 
	$(function(){
		$('.slide_comment').bxSlider({
			controls:true,
			pager:false
		});
	})
	
	$(function(){
		$('#slidercomment').mouseover(function(){
			$(this).find('.bx-prev').show();
			$(this).find('.bx-next').show();
		$('.bx-controls-direction').mouseout(function(){
			$(this).find('.bx-prev').hide();
			$(this).find('.bx-next').hide();
		});
	});
});
	//배우 슬라이더 
	$(function(){
		$('.slide_actors').bxSlider({
			controls:true,
			pager:false,
			infiniteLoop:false,
			hideControlOnEnd:true,
			minSlides:2,
			maxSlides:2
		});
	});
	$(function(){
		$('.bx-wrapper').mouseover(function(){
			$(this).find('.bx-prev').show();
			$(this).find('.bx-next').show();
		$('.bx-controls-direction').mouseout(function(){
			$(this).find('.bx-prev').hide();
			$(this).find('.bx-next').hide();
			});
		});
	});
	
	//코멘트 글자수 제한
	$(document).on('keyup','textarea',function(){
		var inputLength = $(this).val().length;
		
		if(inputLength>1000){
			$(this).val($(this).val().substring(0,1000));
		}else{
			var remain = 1000 - inputLength;
			remain += '/1000';
			if($(this).attr('id')=='text'){
				//등록폼 글자수 
				$('#re_first .letter-count').text(remain);
			}else{
				//수정폼 글자수
				$('#mre_first .letter-count').text(remain);
			}
		}
	});
	
	//재생 색깔 후버
	$(function(){
		$('.preview').find('span').hover(
		function(){
			$(this).css('color','#c73f71')
		},function(){
			$(this).css('color','rgba(255,255,255,0.15)')
		});
	});
	
	$(document).on('click','input[name=rating]',function(event){
		var movie_num = $(this).parents('.starRating').attr('data-num');
		var rate = $(this).val();
		var id = $('#user_id').val();
		if(id==null || id == ""){
			alert('로그인을 해야 서비스를 이용할 수 있습니다.');
			
			return false;
		}
		movieRate(movie_num,rate,id);
	});
	
	function movieRate(movie_num,rate,id){
		console.log(movie_num + " : " + rate+ " : " + id);
		$.ajax({
			url:'rating.do',
			type:'post',
			data:{movie_num:movie_num,id:id,rate:rate},
			dataType:'json',
			timeout:30000,
			cache:false,
			success:function(data){
				if(data.result == 'insert'){
					alert('입력하신 점수가 성공적으로 저장되었습니다.');
				}else if(data.result == 'update'){
					alert('입력하신 정보로 수정되었습니다.');
				}else if(data.result == 'login'){
					alert('로그인을 하시지 않으면 서비스를 이용할 수 없습니다.');
				}else if(data.result == 'failure'){
					alert('영화 평가가실패하였습니다. 관리자에게 문의하세요');
				}
				//radio 별 평가 후 초기화 
				$('input[name=rating]').prop("checked", false);
			},
			error:function(){
				alert('네트워크 오류');
			}
		});
	}
	function showBannerImage(){
	      var movie_num = $('#movie_num2').val();
	      var url ='url(imageView.do?movie_num='+movie_num+'&type=banner)';
	      $('#img-banner').css('background-image',url);
	   };
    showBannerImage();
    
    $('#onlinesubmit').click(function(){
    	
    	var content = $('#text1').val();
    	var movie_num = $('input[name=movie_num]').val();
    	var id = $('input[name=id]').val();
    	
    	if(content==''){
    		return;
    	}
    	//ajax로 movie_rated 테이블에 movie_num, 로그인한 id값을
    	$.ajax({
    		url:'commentRated.do',
    		type:'post',
    		data:{movie_num:movie_num,id:id},
    		dataType:'json',
    		timeout:30000,
    		cache:false,
    		success:function(data){
    			if(data.result == 'submit'){
    				alert('코멘트가 정상적으로 입력되었습니다.');
    				$('#commentRegisterForm').submit();
    			}else if(data.result = 'failure'){
    				alert('영화평가 후 코멘트를 입력 해주세요');
    				
    			}
    		},
    		error:function(){
    			alert('네트워크 오류');
    		}
    	});
    });
});
	


	
	