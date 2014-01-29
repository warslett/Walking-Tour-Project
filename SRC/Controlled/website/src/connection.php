<?php

/**
 * connection.php defines a PHP class for a database connection object.
 * This allows the webdeveloper to access data on the database as objects.
 * This file forms the basis of the Model-View-Controller
 *
 * @author William Arslett <wia2@aber.ac.uk>, Stephen Clasby <spc3@aber.ac.uk>
 */
include_once 'tour.php';

class dbConnection {

    private $connection;

    function __construct() {

        //connection is made upon initialisation

        $this->connection = new mysqli(
                'db.dcs.aber.ac.uk', 'wia2', 't6y7u8i9o0p', 'wia2');

        //failure to connect will produce an unfriendly error

        if (!$this->connection) {
            die('Could not connect to database: ' .
                    mysqli_error($this->connection));
        }
    }

    function insertTour($data) {

        //insert the tour first
        
        $this->connection->query("
                 INSERT INTO listOfWalks (
                    title,
                    shortDesc,
                    longDesc,
                    hours,
                    distance
                 ) 
	         VALUES (
                    '" . $data->title . "',
                    '" . $data->shortDesc . "',
                    '" . $data->longDesc . "',
                    NULL,
                    NULL);    
");

        //get the id of the tour so that we can link all locations to the tour
        
        $tourid = mysqli_fetch_assoc($this->connection->query(
                                "SELECT LAST_INSERT_ID();"
                ))['LAST_INSERT_ID()'];

        foreach ($data->locations as $curloc) {

            //insert every location
            
            $this->connection->query("
                 INSERT INTO location (
                    walkID,
                    latitude,
                    longitude,
                    timestamp
                 ) 
	         VALUES (
                    " . $tourid . ",
                    " . $curloc->Latitude . ",
                    " . $curloc->Longitude . ",
                    " . $curloc->TimeStamp . ");
            ");

            if ($curloc->Place != NULL) {
                
                /*if the location has a place, load its id so that we can
                 * assoicate it with the place in the database
                 */

                $locid = mysqli_fetch_assoc($this->connection->query(
                                        "SELECT LAST_INSERT_ID();"
                        ))['LAST_INSERT_ID()'];
                
                //insert the place to thed atabase

                $this->connection->query("
                 INSERT INTO placeDescription (
                    locationID,
                    shortDesc
                 ) 
	         VALUES (
                    " . $locid . ",
                    '" . $curloc->Place->description . "');
                 ");

                /* get the place id so that we can associate it with the
                 * photographs
                 */
                
                $placeid = mysqli_fetch_assoc($this->connection->query(
                                        "SELECT LAST_INSERT_ID();
                "))['LAST_INSERT_ID()'];

                foreach ($curloc->Place->photos as $photo) {
                    
                    //insert every photograph
                    
                    $this->connection->query("
                 INSERT INTO photoUsage (
                    placeID,
                    photoName
                 ) 
	         VALUES (
                    " . $locid . ",
                    '" . $photo . "');
                 ");
                    
                }
                
            }
            
        }
        
    }

    function getTour($tourID) {

        //return a single tour from an ID

        $db_record = mysqli_fetch_array($this->connection->query(
                        "SELECT * FROM listOfWalks WHERE id=" . $tourID . ";"
        ));

        return new Tour(
                $tourID,
                $db_record['title'],
                $db_record['shortDesc'],
                $db_record['longDesc'],
                $db_record['hours'],
                $db_record['distance'],
                $this->getLocations($tourID));
        
    }

    function getLocations($tourID) {

        //return an array of locations from a tour ID

        $locations = array();

        $db_result = $this->connection->query("
            SELECT * FROM location WHERE `location`.`walkID`=" . $tourID . "
            ");

        while ($db_record = mysqli_fetch_array($db_result)) {

            $place_query = $this->connection->query("
            SELECT * FROM placeDescription WHERE 
            `placeDescription`.`locationID`=" . $db_record['id'] . " 
            LIMIT 1");

            if (!$place_record = mysqli_fetch_array($place_query)) {
                $place = NULL;
            } else {
                $photo_array = array();
                $photo_query = $this->connection->query("
            SELECT * FROM photoUsage WHERE 
            `photoUsage`.`placeID`=" . $place_record['id']);
                while ($photo_record = mysqli_fetch_array($photo_query)) {
                    $photo_array[] = $photo_record['photoName'];
                }
                $place = new Place($place_record['shortDesc'], $photo_array);
            }

            $locations[] = new Location(
                    $db_record['latitude'], $db_record['longitude'], $db_record['timestamp'], $place);
        }

        return $locations;
    }

    function getListOfTours() {

        //get a list of tours

        $list = array();

        $db_result = $this->connection->query("SELECT * FROM listOfWalks");
        
        while ($db_record = mysqli_fetch_array($db_result)) {
            
            $list[] = new Tour(
                    $db_record['id'],
                    $db_record['title'],
                    $db_record['shortDesc'],
                    $db_record['longDesc'],
                    $db_record['hours'],
                    $db_record['distance'],
                    $this->getLocations($db_record['id']));
            
        }

        return $list;
    }

}