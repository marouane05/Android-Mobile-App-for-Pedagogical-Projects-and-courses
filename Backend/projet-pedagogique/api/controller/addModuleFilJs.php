<?php
 header("Access-Control-Allow-Origin: *");
 header("Content-type: application/json");
require_once('../models/Projetmodule.php');


$projet_module = new Projetmodule();
$data = json_decode(file_get_contents("php://input"), true);


$id_projet=$_POST['id_projet'] ;
$page1 = $_POST['page_un'];
$page2 =$_POST['page_deux'];




$res = $projet_module->ModuleFile($id_projet,$page1,$page2);

$tab['data'] = array();
array_push($tab['data'],["created"=>$res]);
http_response_code(200);
echo json_encode($tab);