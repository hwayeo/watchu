$(document).ready(function(){
	
	var currentPage;
	var count;
	var rowCount;
	var keyfield;
	var keyword;
	var keyword2;
	var keyword3;
	var type = $('.page-type').val();
	var width = $(document).width();
	var movierate;
	
	//평가창에서의 브라우저 창 넓이
	$(window).on('resize',function(){
		width = $(document).width();
		if(width <= 425){
			selectEva(1,'ran','ran');
		}
		
	});
	
	//ajax로 영화 평점 갯수 출력
	$(document).on('click','.star5',function(){
		ratecount($('#movierate').val());
	});
	$(document).on('click','.star4',function(){
		ratecount(movierate);
	});
	$(document).on('click','.star3',function(){
		ratecount(movierate);
	});
	$(document).on('click','.star2',function(){
		ratecount(movierate);
	});
	$(document).on('click','.star1',function(){
		ratecount();
	});

	ratecount();
	//ajax로 영화 평점 갯수 출력
	
	//버튼 클릭시 목록 추가 호출
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
				selectEva(pageNum,keyword,keyfield);
			}else if(keyfield == 'search'){
				console.log('search');
				pageNum = currentPage + 1;
				selectEva(pageNum,keyword,keyfield);
			}else{
				console.log('Not all');
				pageNum = currentPage + 1;
				selectEva(pageNum,keyword,keyfield);
			}
		}
	});
	
	/*---------------movieList 호출---------------------*/
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
	
	/*---------------movieList 호출---------------------*/
	
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
			selectCategory(1,keyword,keyword2,keyword3,keyfield);
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
			selectCategory(1,keyword,keyword2,keyword3,keyfield);
		}
	});
	/*---------------카테고리 변경시 호출---------------------*/
 	
 	//영화 평가 화면
	function selectEva(pageNum,keyword,keyfield){
		var elist = '';
		currentPage = pageNum;
 		if(pageNum == 1){
			$('#elist').empty();
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
 					$('#elist').empty();
					$(list).each(function(index,item){
						var released = item.released;
						if(width > 425){
							elist += '<div class="col-sm-3 col-md-3 col-xs-6" id="eva-category">';
							elist += '<div class="thumbnail">';
							if(item.poster_img == null){
							elist += '<img src="../resources/images/default-poster.jpg" class="mimg">';
							}else{
							elist += '<img src="../movie/imageView.do?movie_num='+item.movie_num+'&type=poster" class="mimg">';
							}
							elist += '<div class="overlay">';
							elist += '<div class="list-contents">';
							elist += '<p class="subtitle">'+item.title+'</p>';
							elist += '<p class="year">'+released.substring(0,4)+'</p>';
							elist += '<div class="starRating">';
							elist += '<fieldset class="rating">';
							elist += '<input type="radio" class="star5" id="star5-'+item.movie_num+'" name="rating" value="5" data-num="'+item.movie_num+'"/>';
							elist += '<label class = "full" for="star5-'+item.movie_num+'"></label>';
							elist += '<input type="radio" class="star5" id="star4half-'+item.movie_num+'" name="rating" value="4.5" data-num="'+item.movie_num+'"/>';
							elist += '<label class="half" for="star4half-'+item.movie_num+'"></label>';
							elist += '<input type="radio" class="star4" id="star4-'+item.movie_num+'" name="rating" value="4" data-num="'+item.movie_num+'"/>';
							elist += '<label class = "full" for="star4-'+item.movie_num+'"></label>';
							elist += '<input type="radio" class="star4" id="star3half-'+item.movie_num+'" name="rating" value="3.5" data-num="'+item.movie_num+'"/>';
							elist += '<label class="half" for="star3half-'+item.movie_num+'"></label>';
							elist += '<input type="radio" class="star3" id="star3-'+item.movie_num+'" name="rating" value="3" data-num="'+item.movie_num+'"/>';
							elist += '<label class = "full" for="star3-'+item.movie_num+'"></label>';
							elist += '<input type="radio" class="star3" id="star2half-'+item.movie_num+'" name="rating" value="2.5" data-num="'+item.movie_num+'"/>';
							elist += '<label class="half" for="star2half-'+item.movie_num+'"></label> ';
							elist += '<input type="radio" class="star2" id="star2-'+item.movie_num+'" name="rating" value="2" data-num="'+item.movie_num+'"/>';
							elist += '<label class = "full" for="star2-'+item.movie_num+'"></label>';
							elist += '<input type="radio" class="star2" id="star1half-'+item.movie_num+'" name="rating" value="1.5" data-num="'+item.movie_num+'"/>';
							elist += '<label class="half" for="star1half-'+item.movie_num+'"></label>';
							elist += '<input type="radio" class="star1" id="star1-'+item.movie_num+'" name="rating" value="1" data-num="'+item.movie_num+'"/>';
							elist += '<label class = "full" for="star1-'+item.movie_num+'"></label>';
							elist += '<input type="radio" class="star1" id="starhalf-'+item.movie_num+'" name="rating" value="0.5" data-num="'+item.movie_num+'"/>';
							elist += '<label class="half" for="starhalf-'+item.movie_num+'"></label>';
							elist += '</fieldset>';
							elist += '</div>';
							elist += '</div>';
							elist += '</div>';
							elist += '</div>';
							elist += '</div>';
						}else if(width <= 425){
							elist += '<div class="row">';
							elist += '   <div class="col-xs-12 movie-cell">';
							elist += '	    <div class="col-xs-4 posters">';
							if(item.poster_img == null){
							elist += '	 	    <img src="../resources/images/default-poster.jpg" class="img-responsive posters">';
							}else{
							elist += '			<img src="../movie/imageView.do?movie_num='+item.movie_num+'&type=poster" class="img-responsive posters">';
							}  
							elist += '		</div>';
							elist += '	<div class="col-xs-8 info-cell">';
							elist += '   <div class="row">';
							elist += '		<div class="col-xs-12">';
							elist += '          <p class="ml-title">'+item.title+'</p>';
							elist += '		   <p class="ml-info">'+released.substring(0,4)+'&nbsp;&middot;&nbsp;'+item.country+'</p>';
							elist += '		</div>';
							elist += '	</div>';
							elist += '	<div class="row">';
							elist += '	   <div class="col-xs-12">';
							elist += '		  <div class="starRating">';
							elist += '			<fieldset class="rating">';
							elist += '				<input type="radio" class="star5" id="star5-'+item.movie_num+'" name="rating" value="5" data-num="'+item.movie_num+'"/>';
							elist += '				<label class = "full" for="star5-'+item.movie_num+'"></label>';
							elist += '				<input type="radio" class="star5" id="star4half-'+item.movie_num+'" name="rating" value="4.5" data-num="'+item.movie_num+'"/>';
							elist += '				<label class="half" for="star4half-'+item.movie_num+'"></label>';
							elist += '				<input type="radio" class="star4" id="star4-'+item.movie_num+'" name="rating" value="4" data-num="'+item.movie_num+'"/>';
							elist += '				<label class = "full" for="star4-'+item.movie_num+'"></label>';
							elist += '				<input type="radio" class="star4" id="star3half-'+item.movie_num+'" name="rating" value="3.5" data-num="'+item.movie_num+'"/>';
							elist += '				<label class="half" for="star3half-'+item.movie_num+'"></label>';
							elist += '				<input type="radio" class="star3" id="star3-'+item.movie_num+'" name="rating" value="3" data-num="'+item.movie_num+'"/>';
							elist += '				<label class = "full" for="star3-'+item.movie_num+'"></label>';
							elist += '				<input type="radio" class="star3" id="star2half-'+item.movie_num+'" name="rating" value="2.5" data-num="'+item.movie_num+'"/>';
							elist += '				<label class="half" for="star2half-'+item.movie_num+'"></label> ';
							elist += '				<input type="radio" class="star2" id="star2-'+item.movie_num+'" name="rating" value="2" data-num="'+item.movie_num+'"/>';
							elist += '				<label class = "full" for="star2-'+item.movie_num+'"></label>';
							elist += '				<input type="radio" class="star2" id="star1half-'+item.movie_num+'" name="rating" value="1.5" data-num="'+item.movie_num+'"/>';
							elist += '				<label class="half" for="star1half-'+item.movie_num+'"></label>';
							elist += '				<input type="radio" class="star1" id="star1-'+item.movie_num+'" name="rating" value="1" data-num="'+item.movie_num+'"/>';
							elist += '				<label class = "full" for="star1-'+item.movie_num+'"></label>';
							elist += '				<input type="radio" class="star1" id="starhalf-'+item.movie_num+'" name="rating" value="0.5" data-num="'+item.movie_num+'"/>';
							elist += '				<label class="half" for="starhalf-'+item.movie_num+'"></label>';
							elist += '			</fieldset>';
							elist += '	     </div>';
							elist += '	   </div>';
							elist += '	</div>';
							elist += '	<div class="row">';
							elist += '	  <div class="col-xs-12 text-right">';
							elist += '		<a href="../movie/movieDetail.do?movie_num='+item.movie_num+'" class="movie-link">더보기</a>';
							elist += '    </div>';
							elist += '	</div>';
							elist += '	</div>';
							elist += '	</div>';
							elist += ' 	</div>';
						}
					});
					$('#elist').append(elist);
					
					if(currentPage>=Math.ceil(count/rowCount)){
						$('.movieListButton').hide();
					}else{
						$('.movieListButton').show();
					}
				}
			},
			error:function(){				
			}
 		});
	}
	
	//영화 평가 카테고리 변경시 목록 호출
	function selectCategory(pageNum,keyword,keyword2,keyword3,keyfield){
		var elist = '';
		currentPage = pageNum;
 		if(pageNum == 1){
			$('#elist').empty();
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
						if(width > 425){
							elist += '<div class="col-sm-3 col-md-3 col-xs-6" id="eva-category">';
							elist += '<div class="thumbnail">';
							if(item.poster_img == null){
							elist += '<img src="../resources/images/default-poster.jpg" class="mimg">';
							}else{
							elist += '<img src="../movie/imageView.do?movie_num='+item.movie_num+'&type=poster" class="mimg">';
							}
							elist += '<div class="overlay">';
							elist += '<div class="list-contents">';
							elist += '<p class="subtitle">'+item.title+'</p>';
							elist += '<p class="year">'+released.substring(0,4)+'</p>';
							elist += '<div class="starRating">';
							elist += '<fieldset class="rating">';
							elist += '<input type="radio" class="star5" id="star5-'+item.movie_num+'" name="rating" value="5" data-num="'+item.movie_num+'"/>';
							elist += '<label class = "full" for="star5-'+item.movie_num+'"></label>';
							elist += '<input type="radio" class="star5" id="star4half-'+item.movie_num+'" name="rating" value="4.5" data-num="'+item.movie_num+'"/>';
							elist += '<label class="half" for="star4half-'+item.movie_num+'"></label>';
							elist += '<input type="radio" class="star4" id="star4-'+item.movie_num+'" name="rating" value="4" data-num="'+item.movie_num+'"/>';
							elist += '<label class = "full" for="star4-'+item.movie_num+'"></label>';
							elist += '<input type="radio" class="star4" id="star3half-'+item.movie_num+'" name="rating" value="3.5" data-num="'+item.movie_num+'"/>';
							elist += '<label class="half" for="star3half-'+item.movie_num+'"></label>';
							elist += '<input type="radio" class="star3" id="star3-'+item.movie_num+'" name="rating" value="3" data-num="'+item.movie_num+'"/>';
							elist += '<label class = "full" for="star3-'+item.movie_num+'"></label>';
							elist += '<input type="radio" class="star3" id="star2half-'+item.movie_num+'" name="rating" value="2.5" data-num="'+item.movie_num+'"/>';
							elist += '<label class="half" for="star2half-'+item.movie_num+'"></label> ';
							elist += '<input type="radio" class="star2" id="star2-'+item.movie_num+'" name="rating" value="2" data-num="'+item.movie_num+'"/>';
							elist += '<label class = "full" for="star2-'+item.movie_num+'"></label>';
							elist += '<input type="radio" class="star2" id="star1half-'+item.movie_num+'" name="rating" value="1.5" data-num="'+item.movie_num+'"/>';
							elist += '<label class="half" for="star1half-'+item.movie_num+'"></label>';
							elist += '<input type="radio" class="star1" id="star1-'+item.movie_num+'" name="rating" value="1" data-num="'+item.movie_num+'"/>';
							elist += '<label class = "full" for="star1-'+item.movie_num+'"></label>';
							elist += '<input type="radio" class="star1" id="starhalf-'+item.movie_num+'" name="rating" value="0.5" data-num="'+item.movie_num+'"/>';
							elist += '<label class="half" for="starhalf-'+item.movie_num+'"></label>';
							elist += '</fieldset>';
							elist += '</div>';
							elist += '</div>';
							elist += '</div>';
							elist += '</div>';
							elist += '</div>';
						}else if(width <= 425){
							elist += '<div class="row">';
							elist += '   <div class="col-xs-12 movie-cell">';
							elist += '	    <div class="col-xs-4 posters">';
							if(item.poster_img == null){
							elist += '	 	    <img src="../resources/images/default-poster.jpg" class="img-responsive posters">';
							}else{
							elist += '			<img src="../movie/imageView.do?movie_num='+item.movie_num+'&type=poster" class="img-responsive posters">';
							}  
							elist += '		</div>';
							elist += '	<div class="col-xs-8 info-cell">';
							elist += '   <div class="row">';
							elist += '		<div class="col-xs-12">';
							elist += '          <p class="ml-title">'+item.title+'</p>';
							elist += '		   <p class="ml-info">'+released.substring(0,4)+'&nbsp;&middot;&nbsp;'+item.country+'</p>';
							elist += '		</div>';
							elist += '	</div>';
							elist += '	<div class="row">';
							elist += '	   <div class="col-xs-12">';
							elist += '		  <div class="starRating">';
							elist += '			<fieldset class="rating">';
							elist += '				<input type="radio" class="star5" id="star5-'+item.movie_num+'" name="rating" value="5" data-num="'+item.movie_num+'"/>';
							elist += '				<label class = "full" for="star5-'+item.movie_num+'"></label>';
							elist += '				<input type="radio" class="star5" id="star4half-'+item.movie_num+'" name="rating" value="4.5" data-num="'+item.movie_num+'"/>';
							elist += '				<label class="half" for="star4half-'+item.movie_num+'"></label>';
							elist += '				<input type="radio" class="star4" id="star4-'+item.movie_num+'" name="rating" value="4" data-num="'+item.movie_num+'"/>';
							elist += '				<label class = "full" for="star4-'+item.movie_num+'"></label>';
							elist += '				<input type="radio" class="star4" id="star3half-'+item.movie_num+'" name="rating" value="3.5" data-num="'+item.movie_num+'"/>';
							elist += '				<label class="half" for="star3half-'+item.movie_num+'"></label>';
							elist += '				<input type="radio" class="star3" id="star3-'+item.movie_num+'" name="rating" value="3" data-num="'+item.movie_num+'"/>';
							elist += '				<label class = "full" for="star3-'+item.movie_num+'"></label>';
							elist += '				<input type="radio" class="star3" id="star2half-'+item.movie_num+'" name="rating" value="2.5" data-num="'+item.movie_num+'"/>';
							elist += '				<label class="half" for="star2half-'+item.movie_num+'"></label> ';
							elist += '				<input type="radio" class="star2" id="star2-'+item.movie_num+'" name="rating" value="2" data-num="'+item.movie_num+'"/>';
							elist += '				<label class = "full" for="star2-'+item.movie_num+'"></label>';
							elist += '				<input type="radio" class="star2" id="star1half-'+item.movie_num+'" name="rating" value="1.5" data-num="'+item.movie_num+'"/>';
							elist += '				<label class="half" for="star1half-'+item.movie_num+'"></label>';
							elist += '				<input type="radio" class="star1" id="star1-'+item.movie_num+'" name="rating" value="1" data-num="'+item.movie_num+'"/>';
							elist += '				<label class = "full" for="star1-'+item.movie_num+'"></label>';
							elist += '				<input type="radio" class="star1" id="starhalf-'+item.movie_num+'" name="rating" value="0.5" data-num="'+item.movie_num+'"/>';
							elist += '				<label class="half" for="starhalf-'+item.movie_num+'"></label>';
							elist += '			</fieldset>';
							elist += '	     </div>';
							elist += '	   </div>';
							elist += '	</div>';
							elist += '	<div class="row">';
							elist += '	  <div class="col-xs-12 text-right">';
							elist += '		<a href="${pageContext.request.contextPath}/movie/movieDetail.do?movie_num=${ml.movie_num}" class="movie-link">더보기</a>';
							elist += '    </div>';
							elist += '	</div>';
							elist += '	</div>';
							elist += '	</div>';
							elist += ' 	</div>';
						}
					});
					$('#elist').append(elist);
					
					if(currentPage>=Math.ceil(count/rowCount)){
						$('.movieListButton').hide();
					}else{
						$('.movieListButton').show();
					}
				}
			},
			error:function(){				
			}
 		});
	}
	/*평가 화면 초기 호출*/
	selectEva(1,'ran','ran');
	
	function ratecount(){
		var ratecount = '';
		$('#ratecount').empty();
		
		$.ajax({
			type:'post',
			url:'movieMlist2.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){	
				movierate = data.movierate;
				
				$(movierate).each(function(index,item){
					var ratecount = item.movierated;
					
					$('#ratecount').append(ratecount);
				});
			},error:function(){
				
			}
		});
	}
});