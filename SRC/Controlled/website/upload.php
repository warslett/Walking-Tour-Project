<?php

include_once 'src/connection.php';

$database = new dbConnection();

$sample_data = '
{
    "title": "test",
    "longDesc": "long test description",
    "shortDesc": "short test description",
    "locations": [
        {
            "Longitude": "-4.0655833",
            "Latitude": "52.4163558",
            "TimeStamp": "5",
            "Place": {
                "description": "First point of interest",
                "photos": [
                    "img1",
                    "img2"
                ]
            }
        },
        {
            "Longitude": "-4.0656098",
            "Latitude": "52.4163347",
            "TimeStamp": "10",
            "Place": "NULL"
        },
        {
            "Longitude": "-4.0656285",
            "Latitude": "52.416353",
            "Time Stamp": "15",
            "Place": "NULL"
        },
        {
            "Longitude": "-4.0656281",
            "Latitude": "52.4163935",
            "TimeStamp": "20",
            "Place": "NULL"
        },
        {
            "Longitude": "-4.0656301",
            "Latitude": "52.416437",
            "Time Stamp": "25",
            "Place": "NULL"
        },
        {
            "Longitude": "-4.0656496",
            "Latitude": "52.4164734",
            "TimeStamp": "30",
            "Place": {
                "description": "Second point of interest",
                "photos": [
                    "img3",
                    "img4"
                ]
            }
        },
        {
            "Longitude": "-4.065644",
            "Latitude": "52.416469",
            "TimeStamp": "35",
            "Place": "NULL"
        },
        {
            "Longitude": "-4.065644",
            "Latitude": "52.416469",
            "TimeStamp": "40",
            "Place": "NULL"
        },
        {
            "Longitude": "-4.0656434",
            "Latitude": "52.4164622",
            "TimeStamp": "45",
            "Place": "NULL"
        },
        {
            "Longitude": "-4.0656491",
            "Latitude": "52.4164701",
            "TimeStamp": "50",
            "Place": "NULL"
        },
        {
            "Longitude": "-4.0656598",
            "Latitude": "52.4164401",
            "Time Stamp": "20140128T114631Europe/London",
            "Place": "NULL"
        },
        {
            "Longitude": "-4.0656383",
            "Latitude": "52.4163946",
            "TimeStamp": "55",
            "Place": "NULL"
        },
        {
            "Longitude": "-4.0656174",
            "Latitude": "52.4163567",
            "Time Stamp": "60",
            "Place": "NULL"
        },
        {
            "Longitude": "-4.0656174",
            "Latitude": "52.4163567",
            "TimeStamp": "65",
            "Place": {
                "description": "Last point of interest",
                "photos": [
                    "img1",
                    "img2"
                ]
            }
        }
    ]
}    
';

$data=  json_decode($sample_data);

print_r($data);

//$database->insertTour($data);

//mail('wia2@aber.ac.uk, wjl3@aber.ac.uk', 'Test Success', $_POST['message']);

?>