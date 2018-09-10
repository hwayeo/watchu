$(document).ready(function(){
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
	
	$(function(){
		$('.slide_comment').bxSlider({
			controls:true,
			pager:false
		});
	})
	
	$(function(){
		$('.division1').mouseover(function(){
			$('.bx-prev').show();
			$('.bx-next').show();
		$('.bx-controls-direction').mouseout(function(){
			//$('.bx-prev').hide();
			//$('.bx-next').hide();
		});
	});
});
});