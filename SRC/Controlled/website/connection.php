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
            $locations[]=new Location(
                    $db_record['latitude'], 
                    $db_record['longitude'],
                    $db_record['timestamp']);
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
                    $db_record['distance']);
        }
        
        return $list;
        
    }

}

class Tour {
    
    private $id;
    private $title;
    private $shortDesc;
    private $longDesc;
    private $hours;
    private $distance;
    private $locations;
    
    function __construct(
            $id,
            $title,
            $shortDesc,
            $longDesc,
            $hours,
            $distance,
            $locations) {
        
        $this->id=$id;
        $this->title=$title;
        $this->shortDesc=$shortDesc;
        $this->longDesc=$longDesc;
        $this->hours=$hours;
        $this->distance=$distance;
        $this->location=$locations;
        
    }
    
    function getTitle(){
        return $this->title;
    }

    function getID(){
        return $this->id;
    }
    
    function getShortDesc(){
        return $this->shortDesc;
    }
    
    function getLongDesc(){
        return $this->longDesc;
    }
    
    function getLocations(){
        return $this->locations;
    }

}

class Location {
    
    private $latitude;
    private $longitude;
    private $timestamp;
    
    function __construct($latitude, $longitude, $timestamp){
        
        $this->latitude = $latitude;
        $this->longitude = $longitude;
        $this->timestamp = $timestamp;
        
    }
    
}