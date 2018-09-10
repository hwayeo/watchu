$(document).ready(function(){

 	var currentPage;
	var count;
	var rowCount;
	var keyfield;
	var keyword;
	var type = $('.page-type').val();
	
	$('.gbutton').on('click',function(event){
		keyfield = 'all';
		keyword = $('#movie-search-keyword').val();		
		if(type == 'movieHome'){
			$('#movieSearch').submit();
		}
	});
	$('.gbutton2').on('click',function(){
		keyfield = 'all';
		keyword = $('#movie-search-keyword2').val();		
		if(type == 'movieHome'){
			$('#movieSearch2').submit();
		}
	});
	
	
 	//영화 홈 화면 출력
	function selectHome(pageNum,keyfield,keyword){
		var mlist = '';
		currentPage = pageNum;
 		if(pageNum == 1){
			$('.mlist').empty();
		}
 		$.ajax({
			type:'post',
			data:{pageNum:pageNum,keyfield:keyfield,keyword:keyword},
			url:'movieMlist.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				count = data.count;
				rowCount = data.rowCount;
				var list = data.list;
 				if(count < 0 || list == null){
				}else{
					$(list).each(function(index,item){
						mlist = '<div class="col-sm-6 col-md-3 col-xs-6" id="main-category">';
						if(item.poster_img == null){
							mlist += '<div class="thumbnail" onclick="location.href=\'movieDetail.do?movie_num='+item.movie_num+'\'"><img src="../resources/images/img4.jpg"></div>';
						}else{
							mlist += '<div class="thumbnail" onclick="location.href=\'movieDetail.do?movie_num='+item.movie_num+'\'"><img src="imageView.do?movie_num='+item.movie_num+'&type=poster"></div>';
						}
						mlist += '<div class="sub-category caption">';
						mlist += '<p class="ptitle">'+item.title+'</p>';
						mlist += '</div>';
						mlist += '</div>';
 						$('.mlist').append(mlist);
					});
				}
			},error:function(){
			}
		});
	}
	selectHome(1,keyfield,keyword);
	
	function selectHome2(pageNum,keyfield,keyword){
		var mlist = '';
		currentPage = pageNum;
 		if(pageNum == 1){
			$('.mlist2').empty();
		}
 		$.ajax({
			type:'post',
			data:{pageNum:pageNum,keyfield:keyfield,keyword:keyword},
			url:'movieMlist.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				count = data.count;
				rowCount = data.rowCount;
				var list = data.list;
 				if(count < 0 || list == null){
				}else{
					$(list).each(function(index,item){
						mlist = '<div class="col-sm-6 col-md-3 col-xs-6" id="main-category">';
						if(item.poster_img == null){
							mlist += '<div class="thumbnail" onclick="location.href=\'movieDetail.do?movie_num='+item.movie_num+'\'"><img src="../resources/images/img4.jpg"></div>';
						}else{
							mlist += '<div class="thumbnail" onclick="location.href=\'movieDetail.do?movie_num='+item.movie_num+'\'"><img src="imageView.do?movie_num='+item.movie_num+'&type=poster"></div>';
						}
						mlist += '<div class="sub-category caption">';
						mlist += '<p class="ptitle">'+item.title+'</p>';
						mlist += '</div>';
						mlist += '</div>';
 						$('.mlist2').append(mlist);
					});
				}
			},error:function(){
			}
		});
	}
	selectHome2(1,'title','쿵푸');
	function selectHome3(pageNum,keyfield,keyword){
		var mlist = '';
		currentPage = pageNum;
 		if(pageNum == 1){
			$('.mlist3').empty();
		}
 		$.ajax({
			type:'post',
			data:{pageNum:pageNum,keyfield:keyfield,keyword:keyword},
			url:'movieMlist.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				count = data.count;
				rowCount = data.rowCount;
				var list = data.list;
 				if(count < 0 || list == null){
				}else{
					$(list).each(function(index,item){
						mlist = '<div class="col-sm-6 col-md-3 col-xs-6" id="main-category">';
						if(item.poster_img == null){
							mlist += '<div class="thumbnail" onclick="location.href=\'movieDetail.do?movie_num='+item.movie_num+'\'"><img src="../resources/images/img4.jpg"></div>';
						}else{
							mlist += '<div class="thumbnail" onclick="location.href=\'movieDetail.do?movie_num='+item.movie_num+'\'"><img src="imageView.do?movie_num='+item.movie_num+'&type=poster"></div>';
						}
						mlist += '<div class="sub-category caption">';
						mlist += '<p class="ptitle">'+item.title+'</p>';
						mlist += '</div>';
						mlist += '</div>';
 						$('.mlist3').append(mlist);
					});
				}
			},error:function(){
			}
		});
	}
	selectHome3(1,'country','프랑스');
});