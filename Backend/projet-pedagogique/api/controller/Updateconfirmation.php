<?php
 header("Access-Control-Allow-Origin: *");
 header("Content-type: application/json");
require_once('../models/Projetmodule.php');

$module_confirmation = new Projetmodule();


$id_module = $_POST['id_module'] ;


$res = $module_confirmation->UpdateModule($id_module);
$tab = array();

array_push($tab,["confirmer"=>$res]);
http_response_code(200);
echo json_encode($tab);
