<?php

  include_once 'src/connection.php';

  $database = new dbConnection();

  $header = '
<link rel="stylesheet" type="text/css" href="leaflet/leaflet.css" />
<script type="text/javascript" src="leaflet/leaflet.js"></script>
<script type="text/javascript" src="leaflet/leafletembed.js"></script>
';
  $footer = '
<script type="text/javascript">initmap();</script>
';

  function displayContent ()
  {

    global $database;
    ?>


    <article class = "article">
      <div class = "block clearfix">
        <div class = "block">
          <h1 class = "blockheader">
            Welcome to Group 05’s second year project website.
          </h1>

          <p class = "blockcontent">
            As part of this project we have developed an android application
            that can create walkingtours through GPS tracking. Those tours
            can then be uploaded to an online database and shown here on the
            site.

            To view the walks currently in the database go to the List of
            Walks page. For information on how the app works in more detail,
            go to the About page.
          </p>

        </div>
        <br>

        <div>
          <div class = "block clearfix routefl">
            <div class = "blockheader">
              <h3 class = "t">List of routes </h3>
            </div>
            <div class = "blockcontent">

              <?

                $list = $database->getListOfTours ();

                foreach ( $list as $tour ) {

                  print( "<p>" );
                  print( '<a href="javascript:loadTour(' );
                  print( $tour->getID () );
                  print( ')">' );
                  print( $tour->getTitle () );
                  print( '</a>' );
                  print( "</p>" );

                }

              ?>
            </div>

          </div>

          <div class = "postcontent postcontent-0 clearfix mapfl">
            <div id = "map">
            </div>

          </div>
        </div>
      </div>
    </article>

  <?php
  }

  require_once 'src/template.php';
?>