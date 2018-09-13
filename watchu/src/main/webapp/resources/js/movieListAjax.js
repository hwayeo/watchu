$(document).ready(function(){

	var currentPage;
	var count;
	var rowCount;
	var keyfield;
	var keyword;
	var keyword2;
	var keyword3;
	var subkeyfield = $('#ajx_keyfield').val();
	var subkeyword = $('#ajx_keyword').val();
	var type = $('.page-type').val();
	var width = $(document).width();
	
	//버튼 클릭시
	$('.movieListButton input').click(function(){
			if(currentPage>=Math.ceil(count/rowCount)){
			}else if(keyfield == null && keyword == null && keyword2 == null && keyword3 == null){
				pageNum = currentPage + 1;
				console.log('초기&검색 출력');
				selectList(pageNum,subkeyword,subkeyfield);
			}else if(keyword != '' && keyword2 != '' && keyfield != ''){ 
				pageNum = currentPage + 1;
				console.log('장르,나라 선택');
				selectCategory(pageNum,keyword,keyword2,keyfield);
			}else if(keyword != '' && keyfield == 'genre'){
				pageNum = currentPage + 1;
				console.log('장르 선택');
				selectList(pageNum,keyword,keyfield);
			}else if(keyword2 != '' && keyfield == 'country'){
				pageNum = currentPage + 1;
				console.log('나라 선택');
				selectList(pageNum,keyword2,keyfield);
			}else if(keyword3 != '' && keyfield == 'order'){
				pageNum = currentPage + 1;
				console.log('정렬 선택');
				selectList(pageNum,keyword3,keyfield);
			}else{
				pageNum = currentPage + 1;
				console.log('카테고리 선택X');
				selectList(pageNum,keyword,keyfield);
			}
	});

	//스크롤 이벤트 발생시 pageNum값을 증가 시킨다.
	$(window).scroll(function(){
		if($(window).scrollTop() == $(document).height() - $(window).height()){ 
			if(currentPage>=Math.ceil(count/rowCount)){
			}else if(keyfield == null && keyword == null && keyword2 == null && keyword3 == null){
				pageNum = currentPage + 1;
				console.log('초기&검색 출력');
				selectList(pageNum,subkeyword,subkeyfield);
			}else if(keyword != '' && keyword2 != '' && keyfield != ''){ 
				pageNum = currentPage + 1;
				console.log('장르,나라 선택');
				selectCategory(pageNum,keyword,keyword2,keyfield);
			}else if(keyword != '' && keyfield == 'genre'){
				pageNum = currentPage + 1;
				console.log('장르 선택');
				selectList(pageNum,keyword,keyfield);
			}else if(keyword2 != '' && keyfield == 'country'){
				pageNum = currentPage + 1;
				console.log('나라 선택');
				selectList(pageNum,keyword2,keyfield);
			}else if(keyword3 != '' && keyfield == 'order'){
				pageNum = currentPage + 1;
				console.log('정렬 선택');
				selectList(pageNum,keyword3,keyfield);
			}else{
				pageNum = currentPage + 1;
				console.log('카테고리 선택X');
				selectList(pageNum,keyword,keyfield);
			}
		}
	});

	/*---------------movieList 호출---------------------*/
	$('.gbutton').on('click',function(event){
		keyfield = 'all';
		keyword = $('#movie-search-keyword').val();
		if(type == 'movieList'){
			selectList(1,keyword,keyfield);
		}
	});
	$('.gbutton2').on('click',function(){
		keyfield = 'all';
		keyword = $('#movie-search-keyword2').val();
		if(type == 'movieList'){
			selectList(1,keyword,keyfield);
		}
	});
	$('#movieSearch').on('submit',function(e){
		keyfield = 'all';
		keyword = $('#movie-search-keyword').val();
		selectList(1,keyword,keyfield);
		e.preventDefault();
	});
	$('#movieSearch2').on('submit',function(e){
		keyfield = 'all';
		keyword = $('#movie-search-keyword2').val();
		selectList(1,keyword,keyfield);
		e.preventDefault();
	});

	/*---------------movieList 호출---------------------*/

	/*---------------카테고리 변경 호출---------------------*/
	/*두개 장르 선택*/
	$('.all-category').on('change',function(){
		keyword = $('.genre-category').find('option:selected').val();
		keyword2 = $('.country-category').find('option:selected').val();
		keyword3 = $('.order-category').find('option:selected').val();
		console.log('--keyword-- : ' + keyword);
		console.log('--keyword2-- : ' + keyword2);
		console.log('--keyword3-- : ' + keyword3);

		if(keyword != '' && keyword2 != ''){
			keyfield = 'category';
			selectCategory(1,keyword,keyword2,keyfield);
		}else if(keyword != ''){
			keyfield = 'genre';
			selectList(1,keyword,keyfield);
		}else if(keyword2 != ''){
			keyfield = 'country';
			selectList(1,keyword2,keyfield);
		}else if(keyword3 != ''){
			keyfield = 'order';
			selectList(1,keyword3,keyfield);
		}else{
			selectList(1,subkeyword,subkeyfield);
		}
	});
	$('.all-category2').on('change',function(){
		keyword = $('.genre-category2').find('option:selected').val();
		keyword2 = $('.country-category2').find('option:selected').val();
		keyword3 = $('.order-category2').find('option:selected').val();
		console.log('--keyword-- : ' + keyword);
		console.log('--keyword2-- : ' + keyword2);
		console.log('--keyword3-- : ' + keyword3);

		if(keyword != '' && keyword2 != ''){
			keyfield = 'category';
			selectCategory(1,keyword,keyword2,keyfield);
		}else if(keyword != ''){
			keyfield = 'genre';
			selectList(1,keyword,keyfield);
		}else if(keyword2 != ''){
			keyfield = 'country';
			selectList(1,keyword2,keyfield);
		}else if(keyword3 != ''){
			keyfield = 'order';
			selectList(1,keyword3,keyfield);
		}else{
			selectList(1,subkeyword,subkeyfield);
		}
	});
	/*---------------카테고리 변경 호출---------------------*/

	//영화 목록 화면
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
	//영화 카테고리
	function selectCategory(pageNum,keyword,keyword2,keyfield){
		var slist = '';
		currentPage = pageNum;
		if(pageNum == 1){
			$('#slist').empty();
		}
		$.ajax({ 
			type:'post',
			data:{pageNum:pageNum,keyfield:keyfield,keyword:keyword,keyword2:keyword2},
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
						slist += '<div class="thumbnail tform" onclick="location.href=\'movieDetail.do?movie_num='+item.movie_num+'\'"><span class="movieImg><img src="../resources/images/default-poster.jpg"></span></div>';
						}else{
	                     slist += '<div class="thumbnail tform" onclick="location.href=\'movieDetail.do?movie_num='+item.movie_num+'\'"><img src="imageView.do?movie_num='+item.movie_num+'&type=poster"></div>';
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