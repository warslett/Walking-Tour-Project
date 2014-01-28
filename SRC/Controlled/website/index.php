<?php

include_once 'src/connection.php';

$database = new dbConnection();

$tour = $database->getTour(1);

$footer='
<script type="text/javascript">initmap(' . $tour->getID() . ')</script>
';
$header='
<link rel="stylesheet" type="text/css" href="leaflet/leaflet.css" />
<script type="text/javascript" src="leaflet/leaflet.js"></script>
<script type="text/javascript" src="leaflet/leafletembed.js"></script>
';
function displayContent() {
		global $tour;
?>


    <article class="post article indexpositions">

        <div class=" sidebar2">
            <div class="block clearfix">
                <div class="blockheader">
                    <h3 class="t">List of routes </h3>
                </div>

                <div class="blockcontent">

                </div>
            </div>
        </div>


        <h2 class="postheader"><? echo $tour->getTitle(); ?></h2>

        <div class="postcontent postcontent-0 clearfix">

            <? echo "<p>" . $tour->getLongDesc() . "</p>"; ?>

            <div class="mapfp" id="map">










            </div>

        </div>



    </article>

<?php
}
require_once 'src/template.php';
?>