<?php

/**
 * connection.php defines a PHP class for a database connection object.
 * This allows the webdeveloper to access data on the database as objects.
 * This file forms the basis of the Model-View-Controller
 * 
 * @author William Arslett <wia2@aber.ac.uk>
 */

class dbConnection {

    private $connection;

    function __construct() {
        
        $this->connection = new mysqli(
                'db.dcs.aber.ac.uk', 
                'wia2',
                't6y7u8i9o0p',
                'wia2');
        
        if (!$this->connection) {
            die('Could not connect to database: ' . mysqli_error());
        }
        
    }
    
    function getTour($tourID){
        
        $db_record= mysqli_fetch_array($this->connection->query(
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
    
    function getLocations($tourID){
        
        $locations = array();
        
        $db_result = $this->connection->query("
            SELECT * FROM location WHERE `location`.`walkID`=" . $tourID . "
            ");
        
        while($db_record=mysqli_fetch_array($db_result)){
            
            $place_query=$this->connection->query("
            SELECT * FROM placeDescription WHERE 
            `placeDescription`.`locationID`=" . $db_record['id'] . " 
            LIMIT 1");
            
            if(!$place_record=mysqli_fetch_array($place_query)){
                $place=NULL;
            } else {
                $photo_array=array();
                $photo_query=$this->connection->query("
            SELECT * FROM photoUsage WHERE 
            `photoUsage`.`placeID`=" . $place_record['id']);
                while($photo_record=mysqli_fetch_array($photo_query)){
                    $photo_array[]=$photo_record['photoName'];
                }
                $place=new Place($place_record['shortDesc'], $photo_array);
            }
            
            $locations[]=new Location(
                    $db_record['latitude'], 
                    $db_record['longitude'],
                    $db_record['timestamp'],
                    $place);
        }
        
        return $locations;
        
    }
    
    function getListOfTours(){
        
        $list = array();
        
        $db_result = $this->connection->query("SELECT * FROM listOfWalks");
        
        while($db_record=mysqli_fetch_array($db_result)){
            $list[]=new Tour(
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





