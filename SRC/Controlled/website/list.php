<?
include_once 'connection.php';
$database = new dbConnection();
function displayContent() {
    global $database;
    ?><article class="post article">
        <h2 class="postheader">List of Tours</h2>
        <div class="postcontent postcontent-0 clearfix">
            <?$list=$database->getListOfTours();
            foreach($list as $walk){
                print('<a href="map.php?id=' . $walk->getID() . '">' . $walk->getTitle() . '</a><br>');
            }?>
        </div>
    </article><?
}

require_once 'template.php';
?>
