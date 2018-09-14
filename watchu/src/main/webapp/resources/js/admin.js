$(document).ready(function(){
//삭제 경고창
$('.edit_btn .confirm_del').on('click', function(){
	if(confirm("삭제하시겠습니까?") == false) {
		return false;
	}
});
	 
//수정 경고창
$('.edit_btn .confirm_mod').on('click', function(){
	if(confirm('수정하시겠습니까?') == false) return false;
});

//장르 검색 유효성 체크
$('.confirm_search').submit(function(){
	if($('#keyword').val() == ''){
		alert('검색어를 입력하세요!');
		$('#keyword').focus();
		return false;
	}
});

//영화 관계자 구분
/*$("input[name='jobs']:radio").change(function () {
	var checked = $(this).val(); //선택된 값 가져옴
	location.href='officialList.do?jobs='+checked;
});
*/

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
		event.preventDefault(); //기본 이벤트 제거
		if(director_count > 0) director_value += ',';
		director_value += $(this).val(); //입력값을 director_value에 담음
		console.log(director_value);
		$('.input_director').val(director_value); //director_value에 담은 값을 실제 폼으로 전달
		director_count++; //기존입력값 플러스
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
		if(actor_count > 0) actor_value += ',';
		actor_value += $(this).val();
		console.log(actor_value);
		$('.input_actor').val(actor_value);
		actor_count++;
		event.preventDefault();
    }
});

$('.auto_actor').keypress(function (event) {	
	var keyword = $(this).val();
	var keyfield = 'ACTOR';
	actor_List(keyword,keyfield);
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

//======영화_선택항목 삭제=====//
$('#check_movieDel').click(function(){
	//체크박스 값을 배열에 담음
	var c_movie = [];
	$("input[name='movieChecked']:checked").each(function(){
		c_movie.push($(this).val());
		console.log(c_movie);
	});
	if(c_movie.length == 0){
		alert('삭제할 항목을 선택하세요.');
	}else{
		if(confirm('선택항목을 삭제하시겠습니까?') == true){
			$.ajax({
				url: '/watchu/admin/check_movieDel.do',
				type: 'post',
				dataType: 'text',
				data: {c_movieTest:c_movie},
				success: function(data){
					console.log(data);
					alert("삭제 완료");
					location.href="/watchu/admin/admin_movieList.do";
				},
				error: function(request, status, error){
					alert("code: " + request.status + "message: " + request.responseText + "error: " + error);
				}
			});
			//배열 초기화
			c_movie = new Array();
		}else{
			location.reload(true); //취소 시 페이지 reload
		}
	}
});

//======관계자_선택항목 삭제=====//
$('#check_offDel').click(function(){
	//체크박스 값을 배열에 담음
	var c_off = [];
	$("input[name='offChecked']:checked").each(function(){
		c_off.push($(this).val());
		console.log(c_off);
	});
	if(c_off.length == 0){
		alert('삭제할 항목을 선택하세요.');
	}else{
		if(confirm('삭제하시겠습니까?') == true){
			$.ajax({
				url: '/watchu/admin/check_offDel.do',
				type: 'post',
				dataType: 'text',
				data: {c_offTest:c_off},
				success: function(data){
					console.log(data);
					alert("삭제 완료");
					location.href="/watchu/admin/officialList.do";
				},
				error: function(request, status, error){
					alert("code: " + request.status + "message: " + request.responseText + "error: " + error);
				}
			});
			//배열 초기화
			c_off = new Array();
		}else{
			location.reload(true); //취소 시 페이지 reload
		}
	}
});

//======장르_선택항목 삭제=====//
$('#check_genreDel').click(function(){
	//체크박스 값을 배열에 담음
	var c_genre = [];
	$("input[name='genreChecked']:checked").each(function(){
		c_genre.push($(this).val());
		console.log(c_genre);
	});
	if(c_genre.length == 0){
		alert('삭제할 항목을 선택하세요.');
	}else{
		if(confirm('삭제하시겠습니까?') == true){
			//jQuery.ajaxSettings.traditional = true; //배열을 넘기기위한 ajax 셋팅
			$.ajax({
				url: '/watchu/admin/check_genreDel.do',
				type: 'post',
				dataType: 'text',
				data: {c_genreTest:c_genre},
				success: function(data){
					console.log(data);
					alert("삭제 완료");
					location.href="/watchu/admin/genreList.do";
				},
				error: function(request, status, error){
					alert("code: " + request.status + "message: " + request.responseText + "error: " + error);
				}
			});
			//배열 초기화
			c_genre = new Array();
		}else{
			location.reload(true); //취소 시 페이지 reload
		}
	}
});


});