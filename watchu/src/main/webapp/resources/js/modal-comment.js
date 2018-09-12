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
			pager:false
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
			$(this).css('color','#d24b7d')
		},function(){
			$(this).css('color','#333333')
		});
	});
});