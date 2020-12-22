<?php
 header("Access-Control-Allow-Origin: *");
 header("Content-type: application/json");
require_once('../models/Projetmodule.php');

$projet_module = new Projetmodule();
$id_module = $_POST['id_module'] ;
$intitule=$_POST['intitule'] ;
$hrtd=$_POST['hrtd'] ;
$hrcours=$_POST['hrcours'] ;
$hrtp=$_POST['hrtp'] ;
$desc=$_POST['desc'] ;

$res = $projet_module->RemplirElement($id_module,$intitule,$hrtd,$hrcours,$hrtp,$desc);
$tab = array();

array_push($tab,["remplir"=>$res]);
http_response_code(200);
echo json_encode($tab);
