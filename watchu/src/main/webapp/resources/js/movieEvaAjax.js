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
	
	//평가창에서의 브라우저 창 넓이
	$(window).on('resize',function(){
		width = $(document).width();
		if(width <= 425){
			selectEva(1,keyword,keyfield);
		}
	});
	
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
	$('.gbutton').on('click',function(event){
		keyfield = 'search';
		keyword = $('#movie-search-keyword').val();
		if(type == 'movieList'){
			selectEva(1,keyword,keyfield);
		}
	});
	$('.gbutton2').on('click',function(){
		keyfield = 'search';
		keyword = $('#movie-search-keyword2').val();
		if(type == 'movieList'){
			selectEva(1,keyword,keyfield);
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
							elist += '<input type="radio" id="star5-'+item.movie_num+'" name="rating" value="5" data-num="'+item.movie_num+'"/>';
							elist += '<label class = "full" for="star5-'+item.movie_num+'" title="Awesome - 5 stars"></label>';
							elist += '<input type="radio" id="star4half-'+item.movie_num+'" name="rating" value="4.5" data-num="'+item.movie_num+'"/>';
							elist += '<label class="half" for="star4half-'+item.movie_num+'" title="Pretty good - 4.5 stars"></label>';
							elist += '<input type="radio" id="star4-'+item.movie_num+'" name="rating" value="4" data-num="'+item.movie_num+'"/>';
							elist += '<label class = "full" for="star4-'+item.movie_num+'" title="Pretty good - 4 stars"></label>';
							elist += '<input type="radio" id="star3half-'+item.movie_num+'" name="rating" value="3.5" data-num="'+item.movie_num+'"/>';
							elist += '<label class="half" for="star3half-'+item.movie_num+'" title="better than good - 3.5 stars"></label>';
							elist += '<input type="radio" id="star3-'+item.movie_num+'" name="rating" value="3" data-num="'+item.movie_num+'"/>';
							elist += '<label class = "full" for="star3-'+item.movie_num+'" title="Good - 3 stars"></label>';
							elist += '<input type="radio" id="star2half-'+item.movie_num+'" name="rating" value="2.5" data-num="'+item.movie_num+'"/>';
							elist += '<label class="half" for="star2half-'+item.movie_num+'" title="so so - 2.5 stars"></label> ';
							elist += '<input type="radio" id="star2-'+item.movie_num+'" name="rating" value="2" data-num="'+item.movie_num+'"/>';
							elist += '<label class = "full" for="star2-'+item.movie_num+'" title="not bad - 2 stars"></label>';
							elist += '<input type="radio" id="star1half-'+item.movie_num+'" name="rating" value="1.5" data-num="'+item.movie_num+'"/>';
							elist += '<label class="half" for="star1half-'+item.movie_num+'" title="bad - 1.5 stars"></label>';
							elist += '<input type="radio" id="star1-'+item.movie_num+'" name="rating" value="1" data-num="'+item.movie_num+'"/>';
							elist += '<label class = "full" for="star1-'+item.movie_num+'" title="so bad - 1 star"></label>';
							elist += '<input type="radio" id="starhalf-'+item.movie_num+'" name="rating" value="0.5" data-num="'+item.movie_num+'"/>';
							elist += '<label class="half" for="starhalf-'+item.movie_num+'" title="Worst - 0.5 stars"></label>';
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
							elist += '				<input type="radio" id="star5-'+item.movie_num+'" name="rating" value="5" data-num="'+item.movie_num+'"/>';
							elist += '				<label class = "full" for="star5-'+item.movie_num+'" title="Awesome - 5 stars"></label>';
							elist += ' 				<input type="radio" id="star4half-'+item.movie_num+'" name="rating" value="4.5" data-num="'+item.movie_num+'"/>';
							elist += ' 				<label class="half" for="star4half-'+item.movie_num+'" title="Pretty good - 4.5 stars"></label>';
							elist += ' 				<input type="radio" id="star4-'+item.movie_num+'" name="rating" value="4" data-num="'+item.movie_num+'"/>';
							elist += ' 				<label class = "full" for="star4-'+item.movie_num+'" title="Pretty good - 4 stars"></label>';
							elist += ' 				<input type="radio" id="star3half-'+item.movie_num+'" name="rating" value="3.5" data-num="'+item.movie_num+'"/>';
							elist += ' 				<label class="half" for="star3half-'+item.movie_num+'" title="better than good - 3.5 stars"></label>';
							elist += ' 				<input type="radio" id="star3-'+item.movie_num+'" name="rating" value="3" data-num="'+item.movie_num+'"/>';
							elist += ' 				<label class = "full" for="star3-'+item.movie_num+'" title="Good - 3 stars"></label>';
							elist += ' 				<input type="radio" id="star2half-'+item.movie_num+'" name="rating" value="2.5" data-num="'+item.movie_num+'"/>';
							elist += ' 				<label class="half" for="star2half-'+item.movie_num+'" title="so so - 2.5 stars"></label>';
							elist += ' 				<input type="radio" id="star2-'+item.movie_num+'" name="rating" value="2" data-num="'+item.movie_num+'"/>';
							elist += ' 				<label class = "full" for="star2-'+item.movie_num+'" title="not bad - 2 stars"></label>';
							elist += ' 				<input type="radio" id="star1half-'+item.movie_num+'" name="rating" value="1.5" data-num="'+item.movie_num+'"/>';
							elist += ' 				<label class="half" for="star1half-'+item.movie_num+'" title="bad - 1.5 stars"></label>';
							elist += ' 				<input type="radio" id="star1-'+item.movie_num+'" name="rating" value="1" data-num="'+item.movie_num+'"/>';
							elist += ' 				<label class = "full" for="star1-'+item.movie_num+'" title="so bad - 1 star"></label>';
							elist += ' 				<input type="radio" id="starhalf-'+item.movie_num+'" name="rating" value="0.5" data-num="'+item.movie_num+'"/>';
							elist += ' 				<label class="half" for="starhalf-'+item.movie_num+'" title="Worst - 0.5 stars"></label>';
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
							elist += '<input type="radio" id="star5-'+item.movie_num+'" name="rating" value="5" data-num="'+item.movie_num+'"/>';
							elist += '<label class = "full" for="star5-'+item.movie_num+'" title="Awesome - 5 stars"></label>';
							elist += '<input type="radio" id="star4half-'+item.movie_num+'" name="rating" value="4.5" data-num="'+item.movie_num+'"/>';
							elist += '<label class="half" for="star4half-'+item.movie_num+'" title="Pretty good - 4.5 stars"></label>';
							elist += '<input type="radio" id="star4-'+item.movie_num+'" name="rating" value="4" data-num="'+item.movie_num+'"/>';
							elist += '<label class = "full" for="star4-'+item.movie_num+'" title="Pretty good - 4 stars"></label>';
							elist += '<input type="radio" id="star3half-'+item.movie_num+'" name="rating" value="3.5" data-num="'+item.movie_num+'"/>';
							elist += '<label class="half" for="star3half-'+item.movie_num+'" title="better than good - 3.5 stars"></label>';
							elist += '<input type="radio" id="star3-'+item.movie_num+'" name="rating" value="3" data-num="'+item.movie_num+'"/>';
							elist += '<label class = "full" for="star3-'+item.movie_num+'" title="Good - 3 stars"></label>';
							elist += '<input type="radio" id="star2half-'+item.movie_num+'" name="rating" value="2.5" data-num="'+item.movie_num+'"/>';
							elist += '<label class="half" for="star2half-'+item.movie_num+'" title="so so - 2.5 stars"></label> ';
							elist += '<input type="radio" id="star2-'+item.movie_num+'" name="rating" value="2" data-num="'+item.movie_num+'"/>';
							elist += '<label class = "full" for="star2-'+item.movie_num+'" title="not bad - 2 stars"></label>';
							elist += '<input type="radio" id="star1half-'+item.movie_num+'" name="rating" value="1.5" data-num="'+item.movie_num+'"/>';
							elist += '<label class="half" for="star1half-'+item.movie_num+'" title="bad - 1.5 stars"></label>';
							elist += '<input type="radio" id="star1-'+item.movie_num+'" name="rating" value="1" data-num="'+item.movie_num+'"/>';
							elist += '<label class = "full" for="star1-'+item.movie_num+'" title="so bad - 1 star"></label>';
							elist += '<input type="radio" id="starhalf-'+item.movie_num+'" name="rating" value="0.5" data-num="'+item.movie_num+'"/>';
							elist += '<label class="half" for="starhalf-'+item.movie_num+'" title="Worst - 0.5 stars"></label>';
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
							elist += '				<input type="radio" id="star5-'+item.movie_num+'" name="rating" value="5" data-num="'+item.movie_num+'"/>';
							elist += '				<label class = "full" for="star5-'+item.movie_num+'" title="Awesome - 5 stars"></label>';
							elist += ' 				<input type="radio" id="star4half-'+item.movie_num+'" name="rating" value="4.5" data-num="'+item.movie_num+'"/>';
							elist += ' 				<label class="half" for="star4half-'+item.movie_num+'" title="Pretty good - 4.5 stars"></label>';
							elist += ' 				<input type="radio" id="star4-'+item.movie_num+'" name="rating" value="4" data-num="'+item.movie_num+'"/>';
							elist += ' 				<label class = "full" for="star4-'+item.movie_num+'" title="Pretty good - 4 stars"></label>';
							elist += ' 				<input type="radio" id="star3half-'+item.movie_num+'" name="rating" value="3.5" data-num="'+item.movie_num+'"/>';
							elist += ' 				<label class="half" for="star3half-'+item.movie_num+'" title="better than good - 3.5 stars"></label>';
							elist += ' 				<input type="radio" id="star3-'+item.movie_num+'" name="rating" value="3" data-num="'+item.movie_num+'"/>';
							elist += ' 				<label class = "full" for="star3-'+item.movie_num+'" title="Good - 3 stars"></label>';
							elist += ' 				<input type="radio" id="star2half-'+item.movie_num+'" name="rating" value="2.5" data-num="'+item.movie_num+'"/>';
							elist += ' 				<label class="half" for="star2half-'+item.movie_num+'" title="so so - 2.5 stars"></label>';
							elist += ' 				<input type="radio" id="star2-'+item.movie_num+'" name="rating" value="2" data-num="'+item.movie_num+'"/>';
							elist += ' 				<label class = "full" for="star2-'+item.movie_num+'" title="not bad - 2 stars"></label>';
							elist += ' 				<input type="radio" id="star1half-'+item.movie_num+'" name="rating" value="1.5" data-num="'+item.movie_num+'"/>';
							elist += ' 				<label class="half" for="star1half-'+item.movie_num+'" title="bad - 1.5 stars"></label>';
							elist += ' 				<input type="radio" id="star1-'+item.movie_num+'" name="rating" value="1" data-num="'+item.movie_num+'"/>';
							elist += ' 				<label class = "full" for="star1-'+item.movie_num+'" title="so bad - 1 star"></label>';
							elist += ' 				<input type="radio" id="starhalf-'+item.movie_num+'" name="rating" value="0.5" data-num="'+item.movie_num+'"/>';
							elist += ' 				<label class="half" for="starhalf-'+item.movie_num+'" title="Worst - 0.5 stars"></label>';
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
	/*기본 검색 및 호출시 상태*/
	selectEva(1,keyword,keyfield);
});