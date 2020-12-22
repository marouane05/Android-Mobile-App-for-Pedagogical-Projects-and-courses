<?php
 header("Access-Control-Allow-Origin: *");
 header("Content-type: application/json");
require_once('../models/Projetmodule.php');

$projet_module = new Projetmodule();


$nom_module = $_POST['nom_module'] ;
$nbr_heure= $_POST['nbr_heure'] ;
$id_semestre=$_POST['id_semestre'] ;
$id_projet=$_POST['id_projet'] ;


$projet_module->nom_module=$nom_module;
$projet_module->nbr_heure=$nbr_heure;
$projet_module->id_semestre=$id_semestre;
$projet_module->id_projet=$id_projet;


$res = $projet_module->addModule();
$tab = array();

array_push($tab,["created"=>$res]);
http_response_code(200);
echo json_encode($tab);