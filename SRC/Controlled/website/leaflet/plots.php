<?php

/**
 * plots.php outputs the json for the plots of a given tour id so that it can
 * be requested by an AJAX request.
 *
 * @author William Arslett <wia2@aber.ac.uk>, Stephen Clasby <spc3@aber.ac.uk>
 */

include_once "../src/connection.php";

$connection = new dbConnection();

$tour = $connection->getTour($_GET['id']);

print($tour->getLocationsJSON());

?>
