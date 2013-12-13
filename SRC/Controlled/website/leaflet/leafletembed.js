var map;
var ajaxRequest;
var plotlist;
var plotlayers=[];

function initmap() {
	// set up the map
	map = new L.Map('map');

	// create the tile layer with correct attribution
	var cmUrl='http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
	var cmAttrib='Map data © OpenStreetMap contributors';
	var cm = new L.TileLayer(cmUrl, {minZoom: 8, maxZoom: 12, attribution: cmAttrib});		

	// start the map in South-East England
	map.setView(new L.LatLng(52.41592, -4.06259),9);
	map.addLayer(osm);
}