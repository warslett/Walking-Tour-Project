<?php

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
        $this->locations=$locations;

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

    function getLocationsJSON(){

        $assocarray = array();
        foreach($this->locations as $location){
            if($location->getPlace()!=null){
                $place=array(
                    "shortDesc" => $location->getPlace()->getShortDesc(),
                    "photos" => $location->getPlace()->getPhotos(),
                );
            } else {
                $place = NULL;
            }
            $assocarray[]=array(
                "latitude" => $location->getLatitude(),
                "longitude" => $location->getLongitude(),
                "timestamp" => $location->getTimeStamp(),
                "place" => $place,
            );
        }

        return json_encode($assocarray);

    }

}
class Location {

    private $latitude;
    private $longitude;
    private $timestamp;
    private $place;

    function __construct($latitude, $longitude, $timestamp, $place){

        $this->latitude = $latitude;
        $this->longitude = $longitude;
        $this->timestamp = $timestamp;
        $this->place=$place;


    }

    function getLatitude(){
        return $this->latitude;
    }

    function getLongitude(){
        return $this->longitude;
    }

    function getTimeStamp(){
        return $this->timestamp;
    }

    function getPlace(){
        return $this->place;
    }

}

class Place
{

    private $shortDesc;
    private $photos;

    public function __construct($shortDesc, $photos) {

        $this->shortDesc=$shortDesc;
        $this->photos=$photos;

    }

    public function getShortDesc(){
        return $this->shortDesc;
    }

    public function getPhotos(){
        return $this->photos;
    }
}
