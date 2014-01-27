<?php

require_once 'src/connection.php';
require_once 'src/template.php';
require_once 'src/tour.php';

$database = new dbConnection();

/* 
 * map.php will only load if the id parameter in the get method is set.
 * If the parameter is not set, the server will redirect to the list page.
 */

if(isset($_GET['id'])){
    $tour = $database->getTour($_GET['id']);
} else {
    header("Location: list.php");
}

/*
 * The header variable will be inserted into the <head> section of the page if
 * it is declared. The following header contains scripts and stylesheets for
 * the leaflet map.
 */
$header='
<link rel="stylesheet" type="text/css" href="leaflet/leaflet.css" />
<script type="text/javascript" src="leaflet/leaflet.js"></script>
<script type="text/javascript" src="leaflet/leafletembed.js"></script>
';

/*
 * The footer will be inserted at the bottom of the page just before </body> if
 * it is declared. The following footer contains a function to initialise the
 * map.
 */
$footer;

function displayContent() {
    
    global $tour;
    global $footer;
    $footer='
<script type="text/javascript">initmap(' . $tour->getID() . ')</script>';
    
    ?>

    <article class="post article">
        
        <h2 class="postheader"><? echo $tour->getTitle(); ?></h2>
        
        <div class="postcontent postcontent-0 clearfix">
            
           <?php echo "<p>" . $tour->getLongDesc() . "</p>"; ?>
            
           <div id="map">
		   
		   
		   
		   
		   
		   
		   
		   
		   
			
		   </div>
           
        </div>
        
    </article>

<?php
}
?>