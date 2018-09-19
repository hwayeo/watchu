$(document).ready(function(){

 	var currentPage;
	var count;
	var rowCount;
	var keyfield;
	var keyword;
	var type = $('.page-type').val();
	
	$('#movieSearch').submit(function(){
		if($('#movie-search-keyword').val() == ''){
			alert('검색어를 입력해주세요!');
			$('#movie-search-keyword').focus();
			return false;
		}
	});
	$('#movieSearch2').submit(function(){
		if($('#movie-search-keyword2').val() == ''){
			alert('검색어를 입력해주세요!');
			$('#movie-search-keyword2').focus();
			return false;
		}
	});
	
	$('.gbutton').on('click',function(event){
		if($('#movie-search-keyword').val() == ''){
			alert('검색어를 입력해주세요!');
			$('#movie-search-keyword').focus();
			return false;
		}
		
		keyfield = 'search';
		keyword = $('#movie-search-keyword').val();		
		if(type == 'movieHome'){
			$('#movieSearch').submit(); 
		}
	});
	$('.gbutton2').on('click',function(){
		if($('#movie-search-keyword2').val() == ''){
			alert('검색어를 입력해주세요!');
			$('#movie-search-keyword2').focus();
			return false;
		}
		
		keyfield = 'search';
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
						var released = item.released;
						var rate = item.rate;
						
						mlist = '<div class="col-sm-3 col-md-3 col-xs-6" id="main-category">';
						if(item.poster_img == null){
							mlist += '<div class="thumbnail" onclick="location.href=\'movieDetail.do?movie_num='+item.movie_num+'\'"><img src="../resources/images/default-poster.jpg"></div>';
						}else{
							mlist += '<div class="thumbnail" onclick="location.href=\'movieDetail.do?movie_num='+item.movie_num+'\'"><img src="imageView.do?movie_num='+item.movie_num+'&type=poster"></div>';
						}
						mlist += '<div class="sub-category caption">';
						mlist += '<p class="ptitle">'+item.title+'</p>';
						mlist += '<p class="pcountry">'+item.country+' · '+released.substring(0,4)+'</p>';
						mlist += '<p class="prate"><span class="glyphicon glyphicon-star"></span> '+rate.toFixed(1)+'</p>';
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
						var released = item.released;
						var rate = item.rate;
						
						mlist = '<div class="col-sm-3 col-md-3 col-xs-6" id="main-category">';
						if(item.poster_img == null){
							mlist += '<div class="thumbnail" onclick="location.href=\'movieDetail.do?movie_num='+item.movie_num+'\'"><img src="../resources/images/default-poster.jpg"></div>';
						}else{
							mlist += '<div class="thumbnail" onclick="location.href=\'movieDetail.do?movie_num='+item.movie_num+'\'"><img src="imageView.do?movie_num='+item.movie_num+'&type=poster"></div>';
						}
						mlist += '<div class="sub-category caption">';
						mlist += '<p class="ptitle">'+item.title+'</p>';
						mlist += '<p class="pcountry">'+item.country+' · '+released.substring(0,4)+'</p>';
						mlist += '<p class="prate"><span class="glyphicon glyphicon-star"></span> '+rate.toFixed(1)+'</p>';
						mlist += '</div>';
						mlist += '</div>';
 						$('.mlist2').append(mlist);
					});
				}
			},error:function(){
			}
		});
	}
	selectHome2(1,'search',$('#rangenre').val());
	
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
						var released = item.released;
						var rate = item.rate;
						
						mlist = '<div class="col-sm-3 col-md-3 col-xs-6" id="main-category">';
						if(item.poster_img == null){
							mlist += '<div class="thumbnail" onclick="location.href=\'movieDetail.do?movie_num='+item.movie_num+'\'"><img src="../resources/images/default-poster.jpg"></div>';
						}else{
							mlist += '<div class="thumbnail" onclick="location.href=\'movieDetail.do?movie_num='+item.movie_num+'\'"><img src="imageView.do?movie_num='+item.movie_num+'&type=poster"></div>';
						}
						mlist += '<div class="sub-category caption">';
						mlist += '<p class="ptitle">'+item.title+'</p>';
						mlist += '<p class="pcountry">'+item.country+' · '+released.substring(0,4)+'</p>';
						mlist += '<p class="prate"><span class="glyphicon glyphicon-star"></span> '+rate.toFixed(1)+'</p>';
						mlist += '</div>';
						mlist += '</div>';
 						$('.mlist3').append(mlist);
					});
				}
			},error:function(){
			}
		});
	}
	selectHome3(1,'ran','ran');
	
});