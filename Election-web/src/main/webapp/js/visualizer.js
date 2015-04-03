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

pop[1][1] = 875564;
pop[2][1] = 518844;
pop[3][1] = 2299026;
pop[4][1] = 1054686;
pop[5][1] = 329686;
pop[6][1] = 558890;
pop[7][1] = 3651261;
pop[8][1] = 1969520;
pop[9][1] = 3464228;
pop[10][1] = 3922780;
pop[11][1] = 43354;
pop[12][1] = 49950;
pop[13][1] = 250671;
pop[14][1] = 354972;
pop[15][1] = 85757;
pop[16][1] = 1724619;
pop[17][1] = 642923;
pop[18][1] = 886999;
pop[19][1] = 1842034;
pop[20][1] = 1322387;
pop[21][1] = 1478833;
pop[22][1] = 659039;
pop[23][1] = 687952;
pop[24][1] = 2090313;
pop[25][1] = 52056;
pop[26][1] = 342853;
pop[27][1] = 1646177;
pop[28][1] = 2737738;
pop[29][1] = 4778439;
pop[30][1] = 817761;
pop[31][1] = 64429;
pop[32][1] = 1302600;
pop[33][1] = 3725697;
pop[34][1] = 55616;
pop[35][1] = 36819;
pop_bjp[1][1] = 475126;
pop_bjp[2][1] = 260848;
pop_bjp[3][1] = 1212995;
pop_bjp[4][1] = 580282;
pop_bjp[5][1] = 168335;
pop_bjp[6][1] = 298919;
pop_bjp[7][1] = 1960677;
pop_bjp[8][1] = 1043730;
pop_bjp[9][1] = 1835740;
pop_bjp[10][1] = 2057669;
pop_bjp[11][1] = 24513;
pop_bjp[12][1] = 29361;
pop_bjp[13][1] = 132062;
pop_bjp[14][1] = 183081;
pop_bjp[15][1] = 44567;
pop_bjp[16][1] = 877930;
pop_bjp[17][1] = 324900;
pop_bjp[18][1] = 452965;
pop_bjp[19][1] = 934796;
pop_bjp[20][1] = 683984;
pop_bjp[21][1] = 748332;
pop_bjp[22][1] = 334336;
pop_bjp[23][1] = 361685;
pop_bjp[24][1] = 1096343;
pop_bjp[25][1] = 25639;
pop_bjp[26][1] = 193178;
pop_bjp[27][1] = 834866;
pop_bjp[28][1] = 1366964;
pop_bjp[29][1] = 2427104;
pop_bjp[30][1] = 417536;
pop_bjp[31][1] = 33106;
pop_bjp[32][1] = 626617;
pop_bjp[33][1] = 1878559;
pop_bjp[34][1] = 27277;
pop_bjp[35][1] = 20705;
pop_inc[1][1] = 400438;
pop_inc[2][1] = 257996;
pop_inc[3][1] = 1086031;
pop_inc[4][1] = 474404;
pop_inc[5][1] = 161351;
pop_inc[6][1] = 259971;
pop_inc[7][1] = 1690584;
pop_inc[8][1] = 925790;
pop_inc[9][1] = 1628488;
pop_inc[10][1] = 1865111;
pop_inc[11][1] = 18841;
pop_inc[12][1] = 20589;
pop_inc[13][1] = 118609;
pop_inc[14][1] = 171891;
pop_inc[15][1] = 41190;
pop_inc[16][1] = 846689;
pop_inc[17][1] = 318023;
pop_inc[18][1] = 434034;
pop_inc[19][1] = 907238;
pop_inc[20][1] = 638403;
pop_inc[21][1] = 730501;
pop_inc[22][1] = 324703;
pop_inc[23][1] = 326267;
pop_inc[24][1] = 993970;
pop_inc[25][1] = 26417;
pop_inc[26][1] = 149675;
pop_inc[27][1] = 811311;
pop_inc[28][1] = 1370774;
pop_inc[29][1] = 2351335;
pop_inc[30][1] = 400225;
pop_inc[31][1] = 31323;
pop_inc[32][1] = 675983;
pop_inc[33][1] = 1847138;
pop_inc[34][1] = 28339;
pop_inc[35][1] = 16114;

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
	} else {
		return '#EF7126';
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
