<?

  include_once 'src/connection.php';

  $database = new dbConnection();

  /*
   * map.php will only load if the id parameter in the get method is set.
   * If the parameter is not set, the server will redirect to the list page.
   */

  if ( isset( $_GET[ 'id' ] ) ) {
    $tour = $database->getTour ( $_GET[ 'id' ] );
  }
  else {
    header ( "Location: list.php" );
  }

  /*
   * The header variable will be inserted into the <head> section of the page if
   * it is declared. The following header contains scripts and stylesheets for
   * the leaflet map.
   */
  $header = '
<link rel="stylesheet" type="text/css" href="leaflet/leaflet.css" />
<script type="text/javascript" src="leaflet/leaflet.js"></script>
<script type="text/javascript" src="leaflet/leafletembed.js"></script>
';

  /*
   * The footer will be inserted at the bottom of the page just before </body> if
   * it is declared. The following footer contains a function to initialise the
   * map.
   */
  $footer = '
<script type="text/javascript">initmap(); loadTour(' . $tour->getID () . ');</script>
';

  function displayContent ()
  {

    global $tour;


    ?>

    <article class = "article">
      <article class = "block clearfix">
        <h2 class = "blockheader">The Map Page!</h2>

        <div class = "blockcontent">
          <p>
            The map page using the leaflet API and OpenStreetMap tiles; we are able to edit the look of the
            API map with plot markers, editable polylines and media support.
            Leaflet also allows us to use <em>'layers'</em>for different map data, tile overlays e.g. traffic
            overlay.
          </p>

          <p>
            The Map controls are simple; zoom in and out using the + and - buttons in the top left corner and
            drag the map to navigate around also clicking on the Points of Interest markers will trigger them to
            open up and display the available information and relevant media files.
          </p>


        </div>


        <div class = "block">
          <h3 class = "blockheader"><? echo $tour->getTitle (); ?></h3>

          <div class = "blockcontent clearfix">

            <? echo "<p>" . $tour->getLongDesc () . "</p>"; ?>
          </div>

            <div class = "blockcontent clearfix">
              <div class="mapmp" id ="map">
                </div>

            </div>
        </div>

    </article>

  <?

  }

  require_once 'src/template.php';

?>
