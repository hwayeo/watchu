$(document).ready(function(){
	
 	var currentPage;
	var count;
	var rowCount;
	var keyfield;
	var keyword;
	var keyfield2 = $('#ajx_keyfield').val();
	var keyword2 = $('#ajx_keyword').val();
	var type = $('.page-type').val();
	var width = $(document).width();
	 
	//스크롤 이벤트 발생시 pageNum값을 증가 시킨다.
 	$(window).scroll(function(){
 		if($(window).scrollTop() == $(document).height() - $(window).height()){ 
 			if(currentPage>=Math.ceil(count/rowCount)){
 			}else if(keyfield == null && keyword == null){
 				pageNum = currentPage + 1;
 				selectList(pageNum,keyword2,keyfield2);
 			}else{ 
 				pageNum = currentPage + 1;
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
	
	/*기본 검색 및 호출시 상태*/
	selectList(1,keyword2,keyfield2);
	/*---------------movieList 호출---------------------*/
	
	/*---------------카테고리 변경 호출---------------------*/
	/*두개 장르 선택*/
	$('all-category').on('change',function(){
		
	});
	
	/*장르 선택*/
	$('.genre-category').on('change',function(){
		keyfield = 'genre';
		keyword = $(this).find('option:selected').val();
		console.log('===keyfield=== : ' + keyfield);
		console.log('===keyword=== : ' + keyword);
		selectList(1,keyword,keyfield);
	});
	
	/*국가 선택*/
	$('.country-category').on('change',function(){
		keyfield = 'country';
		keyword2 = $(this).find('option:selected').val();
		console.log('===keyfield=== : ' + keyfield);
		console.log('===keyword2=== : ' + keyword2);
		selectList(1,keyword2,keyfield);
	});
	
	/*순서 선택*/
	$('.order-category').on('change',function(){
		keyfield = 'order';
		keyword = $(this).find('option:selected').val();
		console.log('===keyfield=== : ' + keyfield);
		console.log('===keyword=== : ' + keyword);
		selectList(1,keyword,keyfield);
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
						slist = '<div class="col-sm-6 col-md-3 col-xs-6" id="main-category">';
						if(item.poster_img == null){
							slist += '<div class="thumbnail" onclick="location.href=\'movieDetail.do?movie_num='+item.movie_num+'\'"><img src="../resources/images/img4.jpg"></div>';
						}else{
							slist += '<div class="thumbnail" onclick="location.href=\'movieDetail.do?movie_num='+item.movie_num+'\'"><img src="imageView.do?movie_num='+item.movie_num+'&type=poster"></div>';
						}
						slist += '<div class="sub-category caption">';
						slist += '<p class="ptitle">'+item.title+item.rate+'</p>';
						slist += '<p class="pgeren">'+item.country+'</p>';
						slist += '<p class="pgeren">'+released.substring(0,4)+'</p>';
						slist += '</div>';
						slist += '</div>';
						
 						$('#slist').append(slist);
					});
				}
				},error:function(){
				}
		});
	}
});