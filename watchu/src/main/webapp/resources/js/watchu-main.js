$(document).ready(function(){
	var windowHeight;
	var windowWidth;
	
	windowHeight = $(document).scrollTop();
	windowWidth = $(document).width();

	//초기값
	headerClassControl();
	
	
	//브라우저 사이즈가 변동되었을때 동작
	$(window).on('resize',function(){
		windowWidth = $(document).width();
		windowHeight = $(document).scrollTop();
		headerClassControl();
		
	});
	
	//스크롤을 내리거나 올릴때 동작 //300
	$(window).on('scroll',function(){
		windowWidth = $(document).width();
		windowHeight = $(document).scrollTop();
		headerClassControl();
		if(windowHeight > 325 && windowWidth >= 768){
			$('#top-header').hide();
			$('#scroll-header').show();
		}else if(windowHeight <= 325 && windowWidth >= 768){
			$('#top-header').show();
			$('#scroll-header').hide();
		}
	});

	//메인 배너 이미지 교체 메서드
	function showBannerImage(){
		var movie_num = $('#ranBanner').val();
		var url ='url(bannerView.do?movie_num='+movie_num+')';
		$('#img-test').css('background-image',url);
	};
/*
	function showRandomMovieImg(){
		var movie_num = $('#ranMovie').val();
		var url = 'url(bannerView.do?movie_num='+movie_num+')';
		$('#rec-random-banner').css('background-image',url).
		css('height','400px').css('background-repeat','no-repeat').
		css('background-position','center center').css('background-size','100%')
		.css('border-top-left-radius','8px').css('border-top-right-radius','8px');
		
	}
	showRandomMovieImg();*/
	function headerClassControl(){
		if(windowWidth < 768){
			if(windowHeight < 20){
				$('.search-form').attr('class','form-group search-form-top');
			}else if(windowHeight >= 21){
				$('.search-form-top').attr('class','form-group search-form');
			}
		}else if(windowWidth >= 768){
			$('.search-form-top').attr('class','form-group search-form');
		}
		
		if(windowHeight < 20){
			$('#scrolled-navbar').attr('id','top-navbar');
		}else if(windowHeight >= 21){
			$('#top-navbar').attr('id','scrolled-navbar');
		}

	}

	showBannerImage();

	
	/* //검색
	$('#search-btn').on('click',function(){
		var keyword = $('#xs-search').val();
		if(keyword == ''){
			alert('검색어를 입력하세요');
		}
		
		$.ajax({
			url:'search.do',
			method:'get',
			data:{keyword:keyword},
			cache:false,
			timeout:30000
		});
	}); */

	
	//검색어 자동완성
	var movieNames = new Array();
	$('.search-input').keyup(function (event) {
		var keyword = $(this).val();
		var keyfield = 'title';
		autolist(keyword,keyfield);
	});
	$('.search-input').keydown(function (event) {
		var keyword = $(this).val();
		var keyfield = 'title';
		autolist(keyword,keyfield);
	});
	$('.search-input').keypress(function (event) {
		var keyword = $(this).val();
		var keyfield = 'title';
		autolist(keyword,keyfield);
	});
	
	function autolist(keyword,keyfield){
		$.ajax({
			type: 'post',
			url: '/watchu/main/autoComplete.do',
			data: {keyword:keyword,keyfield:keyfield},
			dataType: 'json',
			success: function (data) {
				movieNames = [];
				$(data).each(function (index, element) {
					$(element.list).each(function (index, value) {
						movieNames.push(value.title);
					});
				});
			}
		});
		$('.search-input').autocomplete({source:movieNames});
	}
});

