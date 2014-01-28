<?php

include_once "../src/connection.php";

$connection= new dbConnection();

$tour = $connection->getTour($_GET['id']);

print($tour->getLocationsJSON());

?>
