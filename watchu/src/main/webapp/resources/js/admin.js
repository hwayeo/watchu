$(document).ready(function(){
	//영화 삭제 시 경고창
	
	//장르 검색 유효성 체크
	$('#genre_search').submit(function(){
		if($('#keyword').val() == ''){
			alert('검색어를 입력하세요!');
			$('#keyword').focus();
			return false;
		}
	});

//genreModify 모달에 데이터 넘기기
	$('.modify_btn').on('click', function () {
		var num = $(this).attr('data-whatever');
		var data = $(this).text();
		$('#genre_num').val(num);
		$('#show-num').text(num);
		$('#name').val(data);
		//장르 삭제 버튼
		var modify = "location.href='genreDelete.do?genre_num=";
		var modifyUrl = modify+num+"'";
		$('#modifyBtn').attr("onclick", modifyUrl);
	});

//======감독_자동완성=====//
var directorList = new Array();
$('.auto_director').keyup(function (event) {
	var keyword = $(this).val();
	var keyfield = 'DIRECTOR';
	director_List(keyword,keyfield);
	
	if (event.keyCode === 13) {
		$('.auto_director').val('');
	}
});

var director_value = '';
var director_count = 0;
$('.auto_director').keydown(function (event) {
	var keyword = $(this).val();
	var keyfield = 'DIRECTOR';
	director_List(keyword,keyfield);
	
	//감독 목록
	if (event.keyCode === 13) {
		event.preventDefault();
		if(director_count > 0) director_value += ',';
		director_value += $(this).val();
		console.log(director_value);
		$('.input_director').val(director_value);
		director_count++;
    }
});

$('.auto_director').keypress(function (event) {
	var keyword = $(this).val();
	var keyfield = 'DIRECTOR';
	director_List(keyword,keyfield);
});

function director_List(keyword,keyfield){
	$.ajax({
		type: 'post',
		url: '/watchu/admin/auto_offList.do',
		data: {keyword:keyword,keyfield:keyfield},
		dataType: 'json',
		success: function (data) {
			directorList = [];
			$(data).each(function (index, element) {
				$(element.list).each(function (index, value) {
					directorList.push(value.name);
				});
			});
		}
	});	
	$('.auto_director').autocomplete({source: directorList});
}

//======배우_자동완성=====//
var actorList = new Array();
$('.auto_actor').keyup(function (event) {
	var keyword = $(this).val();
	var keyfield = 'ACTOR';
	actor_List(keyword,keyfield);
	
	if (event.keyCode === 13) {
		$('.auto_actor').val('');
	}
});

var actor_value = '';
var actor_count = 0;
$('.auto_actor').keydown(function (event) {
	
	var keyword = $(this).val();
	var keyfield = 'ACTOR';
	
	actor_List(keyword,keyfield);
	
	//배우 목록
	if (event.keyCode === 13) {
		event.preventDefault();
		$('.input_actor').val('');
		if(actor_count > 0) actor_value += ',';
		actor_value += $(this).val();
		console.log(actor_value);
		$('.input_actor').val(actor_value);
		actor_count++;
    }
});

$('.auto_actor').keypress(function (event) {
	
	var keyword = $(this).val();
	var keyfield = 'ACTOR';
	
	actor_List(keyword,keyfield);
	$('.auto_actor').val('').focus();
});

function actor_List(keyword,keyfield){
	$.ajax({
		type: 'post',
		url: '/watchu/admin/auto_offList.do',
		data: {keyword:keyword,keyfield:keyfield},
		dataType: 'json',
		success: function (data) {
			actorList = [];
			$(data).each(function (index, element) {
				$(element.list).each(function (index, value) {
					actorList.push(value.name);
				});
			});
		}
	});	
	$('.auto_actor').autocomplete({source: actorList});
}

//======장르_자동완성=====//
var genreList = new Array();
$('.auto_genre').keyup(function (event) {
	var keyword = $(this).val();
	var keyfield = 'genre';
	genre_List(keyword,keyfield);
});

$('.auto_genre').keydown(function (event) {
	var keyword = $(this).val();
	var keyfield = 'genre';
	genre_List(keyword,keyfield);
	if (event.keyCode === 13) event.preventDefault();
});

$('.auto_genre').keypress(function (event) {
	var keyword = $(this).val();
	var keyfield = 'genre';
	genre_List(keyword,keyfield);
});

function genre_List(keyword,keyfield){
	$.ajax({
		type: 'post',
		url: '/watchu/admin/auto_genreList.do',
		data: {keyword:keyword,keyfield:keyfield},
		dataType: 'json',
		success: function (data) {
			genreList = [];
			$(data).each(function (index, element) {
				$(element.genre_list).each(function (index, value) {
					genreList.push(value.genre);
				});
			});
		}
	});	
	$('.auto_genre').autocomplete({source: genreList});
}

//======장르_선택항목 삭제=====//
var checked_genre = '';
var checked_count = 0;
$("#check_genreDel").click(function() {
	$("input[name=genreChecked]:checked").each(function() {
		if(checked_count > 0) checked_genre += ',';
		checked_genre += $(this).val();
		console.log(checked_genre);
		checked_count++;
	});
	
	$.ajax({
		type: 'post',
		url: '/watchu/admin/check_genreDel.do',
		data: {}
	});
});


});