$(document).ready(function(){
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
});
