<?php
 header("Access-Control-Allow-Origin: *");
 header("Content-type: application/json");
require_once('../models/Projetmodule.php');

$projet_module = new Projetmodule();



$id_projet=$_POST['id_projet'] ;
$page1 = $_POST['pageun'];
$page2 =$_POST['pagedeux'];




$res = $projet_module->ModuleFile($id_projet,$page1,$page2);

$tab['data'] = array();
array_push($tab['data'],["created"=>$res]);
http_response_code(200);
echo json_encode($tab);