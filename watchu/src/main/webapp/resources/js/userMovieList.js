$(document).ready(function(){

	var currentPage;
	var count;
	var rowCount;
	var keyword3;
	var subkeyword = $('#ajx_keyword').val();
	var type = $('.page-type').val();
	
	//버튼 클릭시
	$('.movieListButton input').click(function(){
			if(currentPage>=Math.ceil(count/rowCount)){
			}else if(keyfield == 'allcategory'){
				console.log('카테고리 3개 전부');
				pageNum = currentPage + 1;
				selectCategory(pageNum,keyword3,keyfield);
			}else{
				console.log('Not all');
				pageNum = currentPage + 1;
				selectList(pageNum,subkeyword,subkeyfield);
			}
	});

	//영화 목록 & 검색 출력
	function selectList(pageNum,keyword,keyfield){
		var slist = '';
		currentPage = pageNum;
		if(pageNum == 1){
			$('#slist').empty();
		}
		$.ajax({ 
			type:'post',
			data:{pageNum:pageNum,keyfield:keyfield,keyword:keyword},
			url:'movieMlist2.do',
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
						slist = '<div class="col-sm-3 col-md-3 col-xs-6" id="main-category">';
						if(item.poster_img == null){
							slist += '<div class="thumbnail" onclick="location.href=\'movieDetail.do?movie_num='+item.movie_num+'\'"><img class="movieImg" src="../resources/images/default-poster.jpg"></div>';
						}else{
							slist += '<div class="thumbnail" onclick="location.href=\'movieDetail.do?movie_num='+item.movie_num+'\'"><img class="movieImg" src="imageView.do?movie_num='+item.movie_num+'&type=poster"></div>';
						}
						slist += '<div class="sub-category caption">';
						slist += '<p class="ptitle">'+item.title+'</p>';
						slist += '<p class="pcountry">'+item.country+' · '+released.substring(0,4)+'</p>';
						slist += '<p class="prate"><span class="glyphicon glyphicon-star"></span> '+rate.toFixed(1)+'</p>';
						slist += '</div>';
						slist += '</div>';

						$('#slist').append(slist);
					});
					if(currentPage>=Math.ceil(count/rowCount)){
						$('.movieListButton').hide();
					}else{
						$('.movieListButton').show();
					}
				}
			},error:function(){
			}
		});
	}
	
	//영화 카레고리 출력
	function selectCategory(pageNum,keyword,keyword2,keyword3,keyfield){
		var slist = '';
		currentPage = pageNum;
		if(pageNum == 1){
			$('#slist').empty();
		}
		$.ajax({ 
			type:'post',
			data:{pageNum:pageNum,keyfield:keyfield,keyword:keyword,keyword2:keyword2,keyword3:keyword3},
			url:'movieMlist2.do',
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
						slist = '<div class="col-sm-3 col-md-3 col-xs-6" id="main-category">';
						if(item.poster_img == null){
							slist += '<div class="thumbnail" onclick="location.href=\'movieDetail.do?movie_num='+item.movie_num+'\'"><img class="movieImg" src="../resources/images/default-poster.jpg"></div>';
						}else{
							slist += '<div class="thumbnail" onclick="location.href=\'movieDetail.do?movie_num='+item.movie_num+'\'"><img class="movieImg" src="imageView.do?movie_num='+item.movie_num+'&type=poster"></div>';
						}
						slist += '<div class="sub-category caption">';
						slist += '<p class="ptitle">'+item.title+'</p>';
						slist += '<p class="pcountry">'+item.country+' · '+released.substring(0,4)+'</p>';
						slist += '<p class="prate"><span class="glyphicon glyphicon-star"></span> '+rate.toFixed(1)+'</p>';
						slist += '</div>';
						slist += '</div>';

						$('#slist').append(slist);
					});
					if(currentPage>=Math.ceil(count/rowCount)){
						$('.movieListButton').hide();
					}else{
						$('.movieListButton').show();
					}
				}
			},error:function(){
			}
		});
	}
	
	/*기본 검색 및 호출시 상태*/
	selectList(1,subkeyword,subkeyfield);
});