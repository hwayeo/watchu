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
	
	//버튼 클릭시
	$('.movieListButton input').click(function(){
			if(currentPage>=Math.ceil(count/rowCount)){
			}else if(keyfield == 'allcategory'){
				console.log('allcategory');
				pageNum = currentPage + 1;
				selectCategory(pageNum,keyword,keyword2,keyword3,keyfield);
			}else if(keyfield == 'genrecountry'){
				console.log('genrecountry');
				pageNum = currentPage + 1;
				selectCategory(pageNum,keyword,keyword2,keyword3,keyfield);
			}else if(keyfield == 'genreorder'){
				console.log('genreorder');
				pageNum = currentPage + 1;
				selectCategory(pageNum,keyword,keyword2,keyword3,keyfield);
			}else if(keyfield == 'countryorder'){
				console.log('countryorder');
				pageNum = currentPage + 1;
				selectCategory(pageNum,keyword,keyword2,keyword3,keyfield);
			}else if(keyfield == 'genre'){
				console.log('genre');
				pageNum = currentPage + 1;
				selectCategory(pageNum,keyword,keyword2,keyword3,keyfield);
			}else if(keyfield == 'country'){
				console.log('country');
				pageNum = currentPage + 1;
				selectCategory(pageNum,keyword,keyword2,keyword3,keyfield);
			}else if(keyfield == 'order'){
				console.log('order');
				pageNum = currentPage + 1;
				selectCategory(pageNum,keyword,keyword2,keyword3,keyfield);
			}else if(keyfield == 'title'){
				console.log('title');
				pageNum = currentPage + 1;
				selectList(pageNum,keyword,keyfield);
			}else if(keyfield == 'search'){
				console.log('search');
				pageNum = currentPage + 1;
				selectList(pageNum,keyword,keyfield);
			}else{
				console.log('Not all');
				pageNum = currentPage + 1;
				selectList(pageNum,subkeyword,subkeyfield);
			}
	});

	//스크롤 이벤트 발생시 pageNum값을 증가 시킨다.
	$(window).scroll(function(){
		if($(window).scrollTop() == $(document).height() - $(window).height()){ 
			if(currentPage>=Math.ceil(count/rowCount)){
			}else if(keyfield == 'allcategory'){
				console.log('allcategory');
				pageNum = currentPage + 1;
				selectCategory(pageNum,keyword,keyword2,keyword3,keyfield);
			}else if(keyfield == 'genrecountry'){
				console.log('genrecountry');
				pageNum = currentPage + 1;
				selectCategory(pageNum,keyword,keyword2,keyword3,keyfield);
			}else if(keyfield == 'genreorder'){
				console.log('genreorder');
				pageNum = currentPage + 1;
				selectCategory(pageNum,keyword,keyword2,keyword3,keyfield);
			}else if(keyfield == 'countryorder'){
				console.log('countryorder');
				pageNum = currentPage + 1;
				selectCategory(pageNum,keyword,keyword2,keyword3,keyfield);
			}else if(keyfield == 'genre'){
				console.log('genre');
				pageNum = currentPage + 1;
				selectCategory(pageNum,keyword,keyword2,keyword3,keyfield);
			}else if(keyfield == 'country'){
				console.log('country');
				pageNum = currentPage + 1;
				selectCategory(pageNum,keyword,keyword2,keyword3,keyfield);
			}else if(keyfield == 'order'){
				console.log('order');
				pageNum = currentPage + 1;
				selectCategory(pageNum,keyword,keyword2,keyword3,keyfield);
			}else if(keyfield == 'title'){
				console.log('title');
				pageNum = currentPage + 1;
				selectList(pageNum,keyword,keyfield);
			}else if(keyfield == 'search'){
				console.log('search');
				pageNum = currentPage + 1;
				selectList(pageNum,keyword,keyfield);
			}else{
				console.log('Not all');
				pageNum = currentPage + 1;
				selectList(pageNum,subkeyword,subkeyfield);
			}
		}
	});

	/*---------------검색창에서 이벤트 발생시 호출---------------------*/
	$('.gbutton').on('click',function(event){
		if($('#movie-search-keyword').val() == ''){
			alert('검색어를 입력해주세요!');
			$('#movie-search-keyword').focus();
			return false;
		}
		
		keyfield = 'search';
		keyword = $('#movie-search-keyword').val();
		if(type == 'movieList'){
			selectList(1,keyword,keyfield);
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
		if(type == 'movieList'){
			selectList(1,keyword,keyfield);
		}
	});
	
	$('#movieSearch').on('submit',function(e){
		if($('#movie-search-keyword').val() == ''){
			alert('검색어를 입력해주세요!');
			$('#movie-search-keyword').focus();
			return false;
		}
		
		keyfield = 'search';
		keyword = $('#movie-search-keyword').val();
		selectList(1,keyword,keyfield);
		e.preventDefault();
	});
	$('#movieSearch2').on('submit',function(e){
		if($('#movie-search-keyword2').val() == ''){
			alert('검색어를 입력해주세요!');
			$('#movie-search-keyword2').focus();
			return false;
		}
		
		keyfield = 'search';
		keyword = $('#movie-search-keyword2').val();
		selectList(1,keyword,keyfield);
		e.preventDefault();
	});
	/*---------------검색창에서 이벤트 발생시 호출---------------------*/

	/*---------------카테고리 변경시 호출---------------------*/
	$('.all-category').on('change',function(){
		keyword = $('.genre-category').find('option:selected').val();
		keyword2 = $('.country-category').find('option:selected').val();
		keyword3 = $('.order-category').find('option:selected').val();
		
		console.log('--mobilecategory--');
		console.log('--keyword-- : ' + keyword);
		console.log('--keyword2-- : ' + keyword2);
		console.log('--keyword3-- : ' + keyword3);
		
		if(keyword != '' && keyword2 != '' && keyword3 != ''){
			keyfield = 'allcategory';
			selectCategory(1,keyword,keyword2,keyword3,keyfield);
		}else if(keyword != '' && keyword2 != '' && keyword3 == ''){
			keyfield = 'genrecountry';
			selectCategory(1,keyword,keyword2,keyword3,keyfield);
		}else if(keyword != '' && keyword2 == '' && keyword3 != ''){
			keyfield = 'genreorder';
			selectCategory(1,keyword,keyword2,keyword3,keyfield);
		}else if(keyword == '' && keyword2 != '' && keyword3 != ''){
			keyfield = 'countryorder';
			selectCategory(1,keyword,keyword2,keyword3,keyfield);
		}else if(keyword != '' && keyword2 == '' && keyword3 == ''){
			keyfield = 'genre';
			selectCategory(1,keyword,keyword2,keyword3,keyfield);
		}else if(keyword == '' && keyword2 == '' && keyword3 != ''){
			keyfield = 'order';
			selectCategory(1,keyword,keyword2,keyword3,keyfield);
		}else if(keyword == '' && keyword2 != '' && keyword3 == ''){
			keyfield = 'country';
			selectCategory(1,keyword,keyword2,keyword3,keyfield);
		}else{
			selectCategory(1,keyword,keyword2,keyword3,subkeyfield);
		}
	});
	
	$('.all-category2').on('change',function(){
		keyword = $('.genre-category2').find('option:selected').val();
		keyword2 = $('.country-category2').find('option:selected').val();
		keyword3 = $('.order-category2').find('option:selected').val();
		
		console.log('--webcategory--');
		console.log('--keyword-- : ' + keyword);
		console.log('--keyword2-- : ' + keyword2);
		console.log('--keyword3-- : ' + keyword3);
		
		if(keyword != '' && keyword2 != '' && keyword3 != ''){
			keyfield = 'allcategory';
			selectCategory(1,keyword,keyword2,keyword3,keyfield);
		}else if(keyword != '' && keyword2 != '' && keyword3 == ''){
			keyfield = 'genrecountry';
			selectCategory(1,keyword,keyword2,keyword3,keyfield);
		}else if(keyword != '' && keyword2 == '' && keyword3 != ''){
			keyfield = 'genreorder';
			selectCategory(1,keyword,keyword2,keyword3,keyfield);
		}else if(keyword == '' && keyword2 != '' && keyword3 != ''){
			keyfield = 'countryorder';
			selectCategory(1,keyword,keyword2,keyword3,keyfield);
		}else if(keyword != '' && keyword2 == '' && keyword3 == ''){
			keyfield = 'genre';
			selectCategory(1,keyword,keyword2,keyword3,keyfield);
		}else if(keyword == '' && keyword2 == '' && keyword3 != ''){
			keyfield = 'order';
			selectCategory(1,keyword,keyword2,keyword3,keyfield);
		}else if(keyword == '' && keyword2 != '' && keyword3 == ''){
			keyfield = 'country';
			selectCategory(1,keyword,keyword2,keyword3,keyfield);
		}else{
			selectCategory(1,keyword,keyword2,keyword3,subkeyfield);
		}
	});
	/*---------------카테고리 변경시 호출---------------------*/
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