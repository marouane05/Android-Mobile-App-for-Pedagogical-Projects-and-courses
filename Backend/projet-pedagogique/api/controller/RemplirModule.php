<?php
 header("Access-Control-Allow-Origin: *");
 header("Content-type: application/json");
require_once('../models/Projetmodule.php');

$projet_module = new Projetmodule();


$id_module = $_POST['id_module'] ;
$objectif=$_POST['objectif'] ;
$prerequis=$_POST['prerequis'] ;
$modeevaluation=$_POST['modeevaluation'] ;
$notemodule=$_POST['notemodule'] ;
$validation=$_POST['validation'] ;
$id_projet =$_POST['id_projet'];

$res = $projet_module->RemplirModule($id_module,$objectif,$prerequis,$modeevaluation,$notemodule,$validation,$id_projet);
$tab = array();

array_push($tab,["remplir"=>$res]);
http_response_code(200);
echo json_encode($tab);
