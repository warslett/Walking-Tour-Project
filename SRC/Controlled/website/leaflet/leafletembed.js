/**
 * leafletembed.js contains the code and ajax functionality for loading the map
 * getting the data for a tour and displaying it as a vector overlay on the map
 * with popups for places.
 * 
 * Some code has been copied from the following tutorial:
 * http://switch2osm.org/using-tiles/getting-started-with-leaflet/
 * but has been heavily modified.
 * 
 * @author William Arslett <wia2@aber.ac.uk>, Stephen Clasby <spc3@aber.ac.uk>
 */

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
        
    map.addLayer(osm);
    
    //when the plot data is recieved from the server...
    ajaxRequest.onreadystatechange = function() {
        
        if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
            
            //load the json from the ajax response and parse it to an object
            data = JSON.parse(ajaxRequest.responseText);
            
            var langlongs = [];
            
            for (var i = 0; i < data.length; i++) {
                
                //for every location in the object create a new leaflet coord
                langlongs.push(new L.LatLng(
                        data[i].latitude,
                        data[i].longitude
                        ));
                
                /* if the location has a place description attached, generate a
                 * new marker and popup for that leaflet coord and add it to the
                 * map
                 */
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
            
            /* generate a new polyline based on the array of leaflet coords and
             * add it to the map.
             */
            var polyline = L.polyline(langlongs,
                    {
                        color: 'red',
                        weight: 3,
                        opacity: 0.5,
                        smoothFactor: 1
                    }
            ).addTo(map);
                
            //resize/zoom the map 
            map.fitBounds(polyline.getBounds());
            
        }
        
    }
    
    /* Use AJAX to get the data for the plots from the plot generator script
     * parsing the tour id of the required tour as a parameter
     */
    ajaxRequest.open("GET", "leaflet/plots.php?id=" + id);
    ajaxRequest.send();
    
}        