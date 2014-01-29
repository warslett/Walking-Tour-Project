<?php

/**
 * tour.php defines the classes that make up the data structure for the tours.
 * This file forms part of the Model-View-Controller
 *
 * @author William Arslett <wia2@aber.ac.uk>, Stephen Clasby <spc3@aber.ac.uk>
 */
class Tour
{

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
        $locations)
    {

        $this->id = $id;
        $this->title = $title;
        $this->shortDesc = $shortDesc;
        $this->longDesc = $longDesc;
        $this->hours = $hours;
        $this->distance = $distance;
        $this->locations = $locations;

    }

    function getTitle()
    {
        return $this->title;
    }

    function getID()
    {
        return $this->id;
    }

    function getShortDesc()
    {
        return $this->shortDesc;
    }

    function getLongDesc()
    {
        return $this->longDesc;
    }

    function getLocations()
    {
        return $this->locations;
    }

    function getLocationsJSON()
    {

        //store the required data as an associative array

        $assocarray = array();

        foreach ($this->locations as $location) {

            if ($location->getPlace() != null) {

                //if there is a place attached to location get the data for it

                $place = array(
                    "shortDesc" => $location->getPlace()->getShortDesc(),
                    "photos" => $location->getPlace()->getPhotos(),
                );

            } else {

                //otherwise set it to null to be implicit

                $place = NULL;

            }

            $assocarray[] = array(
                "latitude" => $location->getLatitude(),
                "longitude" => $location->getLongitude(),
                "timestamp" => $location->getTimeStamp(),
                "place" => $place,
            );

        }

        //encode the array as json so that it can be parsed to javascript

        return json_encode($assocarray);

    }

}

class Location
{

    private $latitude;
    private $longitude;
    private $timestamp;
    private $place;

    function __construct($latitude, $longitude, $timestamp, $place)
    {

        $this->latitude = $latitude;
        $this->longitude = $longitude;
        $this->timestamp = $timestamp;
        $this->place = $place;

    }

    function getLatitude()
    {
        return $this->latitude;
    }

    function getLongitude()
    {
        return $this->longitude;
    }

    function getTimeStamp()
    {
        return $this->timestamp;
    }

    function getPlace()
    {
        return $this->place;
    }

}

class Place
{

    private $shortDesc;
    private $photos; //photos should store an array of photo locations

    public function __construct($shortDesc, $photos)
    {

        $this->shortDesc = $shortDesc;
        $this->photos = $photos;

    }

    public function getShortDesc()
    {
        return $this->shortDesc;
    }

    public function getPhotos()
    {
        return $this->photos; //returns an array
    }
}
