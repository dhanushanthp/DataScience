var notPresent = [];
var districts = [];
var dis = [];
var state = [];

function Create2DArray(rows) {
	var arr = [];

	for (var i = 0; i < rows; i++) {
		arr[i] = [];
	}

	return arr;
}
var pop = Create2DArray(1000);
var pop_bjp = Create2DArray(1000)
var pop_inc = Create2DArray(1000);

for (var i = 1; i < 32; i++) {
	pop[i][1] = incSeats[i - 1] + bjpSeats[i - 1];
}

for (var i = 1; i < 32; i++) {
	pop_bjp[i][1] = bjpSeats[i - 1];
}

for (var i = 1; i < 32; i++) {
	pop_inc[i][1] = incSeats[i - 1];
}

console.log(pop_bjp);

d3.select("body").select("svg").select("text.title").data([ 1 ]).enter()
		.append("text").attr("class", "title")
		.attr("font-family", "sans-serif").attr("x", 100).attr("y", 1000).attr(
				"font-size", "20px").attr("fill", "red");

var div = d3.select("body").append("div").attr("class", "tooltip").style(
		"opacity", 0);
// var rScale =
// d3.scale.threshold().domain([0.16,0.32,0.48]).range(["rgb(253,190,133)","rgb(253,141,60)","rgb(230,85,13)","rgb(166,54,3)"]);
// var color = d3.scale.category20b();
// console.log(color(5));
var max = d3.max(pop, function(array) {
	return d3.max(array);
});

var min = d3.min(pop, function(array) {
	return d3.min(array);
});

d3.select("body").selectAll("path").each(function(d, i) {
	district_id = d3.select(this).attr("entity_id");
	state_id = d3.select(this.parentNode).attr("entity_id");
	title = d3.select(this).attr("title");
	if (!district_id || !state_id) {
	} else {
		d3.select(this).attr('pop', pop[state_id][district_id]);
		d3.select(this).attr('pop_bjp', pop_bjp[state_id][district_id]);
		d3.select(this).attr('pop_inc', pop_inc[state_id][district_id]);
	}
});

d3.select("body").selectAll("path").attr("fill", function(d, i) {
	var bjp = d3.select(this).attr("pop_inc");
	var inc = d3.select(this).attr("pop_bjp");

	if (inc > bjp) {
		return '#6CCECB';
	} else if (inc < bjp) {
		return '#EF7126';
	} else {
		return '#D4CBC3';
	}
})

var el = d3.select("body").selectAll("path").on(
		"mouseover",
		function(d) {
			// console.log(this);
			// d3.select(this).style("fill","blue");
			var dist = d3.select(this).attr("title");
			var population = d3.select(this).attr("pop");
			var population_m = d3.select(this).attr("pop_bjp");
			var population_f = d3.select(this).attr("pop_inc");
			var state = d3.select(this.parentNode).attr("title");
			var dist_code = d3.select(this).attr("entity_id");
			var state_code = d3.select(this.parentNode).attr("entity_id");

			div.transition().duration(100).style("opacity", .75);
			div.html(
					"<strong>" + state + "</strong><br/>" + "<br/>" + "INC : "
							+ population_f + "<br/>" + "BJP : " + population_m
							+ "<br/>" + "Total : " + population + "<br/>")
					.style("left", (d3.event.pageX - 50) + "px").style("top",
							(d3.event.pageY - 132) + "px");
		}).on("mouseout", function(d) {
	div.transition().duration(500).style("opacity", 0)
})
