<?php
 header("Access-Control-Allow-Origin: *");
 header("Content-type: application/json");
require_once('../models/Projetmodule.php');

$projet_module = new Projetmodule();


$nom_module = $_POST['nom_module'] ;
$id_semestre=$_POST['id_semestre'] ;
$id_prof=$_POST['id_prof'] ;
$id_projet=$_POST['id_projet'] ;



$res = $projet_module->AffectModule($id_prof,$nom_module,$id_semestre,$id_projet);
$tab = array();

array_push($tab,["semestre"=>$res]);
http_response_code(200);
echo json_encode($tab);
