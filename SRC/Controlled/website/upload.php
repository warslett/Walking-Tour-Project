<?php

  include_once 'src/connection.php';
  
  mail('wia2@aber.ac.uk, wjl3@aber.ac.uk', 'Test Success', print_r($_POST, true));

  $database = new dbConnection();

  $data = json_decode ( $_POST['message'] );

  $database->insertTour($data);

?>