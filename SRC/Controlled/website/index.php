<?php

include_once 'src/connection.php';

$database = new dbConnection();

$tour = $database->getTour(1);
//DO rest of the query...

$footer = '
<script type="text/javascript">initmap(' . $tour->getID() . ')</script>
';
$header = '
<link rel="stylesheet" type="text/css" href="leaflet/leaflet.css" />
<script type="text/javascript" src="leaflet/leaflet.js"></script>
<script type="text/javascript" src="leaflet/leafletembed.js"></script>
';
$tour = '';

function displayContent()
{

    global $tour;
    global $database;
    ?>


    <article class="post article">
        <div>
            <h1 class="postheader">Welcome to Group 05’s second year project website.</h1>

            <p class="blockcontent">As part of this project we have developed an android application that can create
                walking
                tours through GPS tracking. Those tours can then be uploaded to an online database and
                shown here on the site.

                To view the walks currently in the database go to the List of Walks page.
                For information on how the app works in more detail, go to the About page.</p>

        </div>
        <div>
            <div class="block clearfix routefl">
                <div class="blockheader">
                    <h3 class="t">List of routes </h3>
                </div>
                <div class="blockcontent">

                    <?

                    $list = $database->getListOfTours();

                    foreach ($list as $tour) {

                        print("<p>");
                        print('<a href="index.php?id=');
                        print($tour->getID());
                        print('">');
                        print($tour->getTitle());
                        print('</a>');
                        print("</p>");
                        print("<hr>");

                    }

                    ?>
                </div>

            </div>

            <div class="postcontent postcontent-0 clearfix mapfl">
                <h2 class="postheader"><? echo $tour->getTitle(); ?></h2>
                <? echo "<p>" . $tour->getLongDesc() . "</p>"; ?>
                <div class="mapfp" id="map">
                </div>

            </div>
        </div>
    </article>

<?php
}

require_once 'src/template.php';
?>