<?
include_once 'connection.php';
$database = new dbConnection();
if(isset($_GET['id'])){
    $tour = $database->getTour($_GET['id']);
} else {
    header("Location: list.php");
}
?>
<link rel="stylesheet" type="text/css" href="leaflet/leaflet.css" />
<script type="text/javascript" src="leaflet/leaflet.js"></script>
<script type="text/javascript" src="leaflet/leafletembed.js"></script>
<?
function displayContent() {
    global $tour;
    ?>

    <article class="post article">
        <h2 class="postheader"><? echo $tour->getTitle(); ?></h2>

        <div class="postcontent postcontent-0 clearfix">

           <div id="map">
		   </div>
        </div>
    </article>

    <?
}

require_once 'template.php';
?>
<script type="text/javascript">initmap()</script>