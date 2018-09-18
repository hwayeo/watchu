$(document).ready(function(){
	//controller에서 지정해준 map값 가져오기
	var label1 = $('#label1').text();
	var label2 = $('#label2').text();
	var label3 = $('#label3').text();
	var value1 = $('#value1').text();
	var value2 = $('#value2').text();
	var value3 = $('#value3').text();
	
	var total = Number(value1)+Number(value2)+Number(value3);
	var result1 = (value1/total)*100;
	var result2 = (value2/total)*100;
	var result3 = (value3/total)*100;
	
	//차트
	var seedData = [
		{
		  "label": label1,  //장르 이름
		  "value": result1 //장르 수치
		},
		{
		  "label": label2,
		  "value": result2
		},
		{
		  "label": label3,
		  "value": result3
		}
	];
	
	// Define size & radius of donut pie chart
	var width = 300,
	    height = 300, 
	    radius = Math.min(width, height) / 2;
	
	// Define arc colours
	var colour = d3.scaleOrdinal(d3.schemeCategory20);
	
	// Define arc ranges
	var arcText = d3.scaleOrdinal().range([0, width]);
	
	// Determine size of arcs
	var arc = d3.arc()
	  .innerRadius(radius - 100)
	  .outerRadius(radius - 40);
	
	// Create the donut pie chart layout
	var pie = d3.pie()
	  .value(function (d) { return d["value"]; })
	  .sort(null);
	
	// Append SVG attributes and append g to the SVG
	var svg = d3.select("#donut-chart")
	  .attr("width", width)
	  .attr("height", height)
	  .append("g")
	  .attr("transform", "translate(" + radius + "," + radius + ")");
	
	// Define inner circle
	svg.append("circle")
	  .attr("cx", 0)
	  .attr("cy", 0)
	  .attr("r", 100)
	  .attr("fill", "#fff");
	
	// Calculate SVG paths and fill in the colours
	var g = svg.selectAll(".arc")
	  .data(pie(seedData))
	  .enter().append("g")
	  .attr("class", "arc")
	
		// Append the path to each g
		g.append("path")
	  	.attr("d", arc)
	  	.attr("fill", function(d, i) {
	    	return colour(i);
	  	});
	
		// Append text labels to each arc
		g.append("text")
	  	.attr("transform", function(d) {
	    	return "translate(" + arc.centroid(d) + ")";
	  	})
	  	.attr("dy", ".35em")
	  	.style("text-anchor", "middle")
	  	.attr("fill", "#fff")
			.text(function(d,i) { return seedData[i].label; })
	  
	g.selectAll(".arc text").call(wrap, arcText.range([0, width]));
	
	// Wrap function to handle labels with longer text
	function wrap(text, width) {
	  text.each(function() {
	    var text = d3.select(this),
	        words = text.text().split(/\s+/).reverse(),
	        word,
	        line = [],
	        lineNumber = 0,
	        lineHeight = 1.1, //ems
	        y = text.attr("y"),
	        dy = parseFloat(text.attr("dy")),
	        tspan = text.text(null).append("tspan").attr("x", 0).attr("y", y).attr("dy", dy + "em");
	    
	    console.log("tspan: " + tspan);
	    
	    while (word = words.pop()) {
	      line.push(word);
	      tspan.text(line.join(" "));
	      if (tspan.node().getComputedTextLength() > 90) {
	        line.pop();
	        tspan.text(line.join(" "));
	        line = [word];
	        tspan = text.append("tspan").attr("x", 0).attr("y", y).attr("dy", ++lineNumber * lineHeight + dy + "em").text(word);
	      }
	    }
	  });
	}
	
	//더보기 클릭 이벤트
	$('.clickChange').click(function(){
		
	});
});