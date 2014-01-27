<?php

include_once "../connection.php";

$connection= new dbConnection();

$tour = $connection->getTour($_GET['id']);

print($tour->getLocationsJSON());

?>
