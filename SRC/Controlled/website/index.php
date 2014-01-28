<?php

include_once 'src/connection.php';

$database = new dbConnection();

if(isset($_GET['id'])){
    $tour = $database->getTour($_GET['id']);
} else {
    header("Location: list.php");
}

$footer;
$header='
<link rel="stylesheet" type="text/css" href="leaflet/leaflet.css" />
<script type="text/javascript" src="leaflet/leaflet.js"></script>
<script type="text/javascript" src="leaflet/leafletembed.js"></script>
';
function displayContent() {
    global $tour;
    global $footer;
    $footer='
<script type="text/javascript">initmap(' . $tour->getID() . ')</script>
';
?>


    <article class="post article">

        <div class="layout-cell sidebar1">
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

            <div id="map">










            </div>

        </div>

            <div class="content-layout-br layout-item-0"></div>

            <div class="content-layout">
                <div class="content-layout-row">
                    <div class="layout-cell layout-item-1" style="width: 100%">
                        <h3>Popular Routes</h3>
                    </div>
                </div>
            </div>



    </article>

<?php
}
require_once 'src/template.php';
?>