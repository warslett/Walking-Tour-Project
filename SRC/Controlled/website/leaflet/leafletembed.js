var map;
var ajaxRequest = new XMLHttpRequest();

function initmap(id) {

    // set up the map
    map = new L.Map('map');

    // create the tile layer with correct attribution
    var osmUrl = 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
    var osmAttrib = 'Map data � OpenStreetMap contributors';
    var osm = new L.TileLayer(
            osmUrl,
            {
                minZoom: 10,
                maxZoom: 20,
                attribution: osmAttrib
            }
    );

    // start the map in South-East England
    map.setView(new L.LatLng(52.24935, -4.3813), 15);
    map.addLayer(osm);
    ajaxRequest.onreadystatechange = function()
    {
        if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
            data = JSON.parse(ajaxRequest.responseText);
            var langlongs = [];
            for (var i = 0; i < data.length; i++) {
                langlongs.push(new L.LatLng(
                        data[i].latitude,
                        data[i].longitude
                        ));
                if (data[i].place !== null) {
                    var location = L.marker(
                            [data[i].latitude, data[i].longitude]
                            ).addTo(map);
                    location.bindPopup("<h3>" +
                            data[i].place.shortDesc +
                            "</h3>"
                            );
                }
            }
            var polyline = L.polyline(langlongs,
                    {
                        color: 'red',
                        weight: 3,
                        opacity: 0.5,
                        smoothFactor: 1
                    }
            ).addTo(map);
            map.fitBounds(polyline.getBounds());
        }
    }
    ajaxRequest.open("GET", "leaflet/plots.php?id=" + id);
    ajaxRequest.send();
}        