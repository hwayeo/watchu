$(document).ready(function(){
	var windowHeight;
	var windowWidth;
	//날짜 구하기
	var date = new Date();
	var year = date.getFullYear();
	var month = new String(date.getMonth()+1);
	var day = new String(date.getDate()-1);
	
	if(month.length == 1){
		month = "0"+month;
	}
	if(day.length == 1){
		day = "0"+day;
	}
	
	var today = year+month+day;
	
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
		$('#img-test').css('background-image','url(imageView.do?id=deft)');
	};

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


	//박스오피스
	function boxOffice(){
		var basicUrl ="http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=eff3e14691bf6fe3fd1b35c8e35a5340";
		    basicUrl += "&targetDt="+today;
		    basicUrl += "&itemPerPage=7";
		var output = '<table class="table boxOffice"></tr>'
		$.getJSON(basicUrl, function(data){
			$.each(data, function (index, value) { 
				$(value.dailyBoxOfficeList).each(function (index, element) {
					output += '<tr>';
					output += '<td>'+element.rank+'</td>'
					if(element.rankInten == 0){
						output += '<td> - </td>'
					}else if(element.rankInten > 0){
						output += '<td><span class="glyphicon glyphicon-triangle-top rank-plus"></span> '+element.rankInten+'</td>'
					}else if(element.rankInten < 0){
						var rank = element.rankInten;
						var r = rank.substring(1);
						console.log(r);
						output += '<td><span class="glyphicon glyphicon-triangle-bottom rank-minus"></span> '+r+'</td>'
					}
					output += '<td>'+element.movieNm+'</td>'
					output += '<td>'+addComma(element.audiAcc)+'명'+'</td>'
					output += '</tr>';
				});
			});
			output += '</table>';
			$('#boxOffice-output').append(output);
		});
	}
	//박스오피스 초기값	
	boxOffice();

	function addComma(num) {
		  var regexp = /\B(?=(\d{3})+(?!\d))/g;
		   return num.toString().replace(regexp, ',');
		}
	
	
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

