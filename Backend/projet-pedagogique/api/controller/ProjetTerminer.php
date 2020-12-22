<?php
  header("Access-Control-Allow-Origin: *");
  header("Content-type: application/json");
  require_once("../models/Projet.php");

  $data = json_decode(file_get_contents("php://input"), true);
  
  $res["data"] = array();
  
  $projet = new Projet();

  
  //$email = $data['email'];
  $id_projet = $_POST['id_projet'];
  //$password = $data['password'];

/*
  $res=array("succed"=>true,"loggedin"=>$id);

  echo json_encode($res); */

  $res1 = $projet->ProjetRempli($id_projet);
  $res2 = $projet->ModuleRempli($id_projet);

  if($res1==$res2){

    $eg ='oui';

  } else {
      $eg='non';

  }
/*
  array_push($res,["succed"=>true,"loggedin"=>$id]);

  echo json_encode($res);
  */

  //$res=array("succed"=>true,"loggedin"=>$id);
  array_push($res["data"],["succed"=>true,"res1"=>$res1,"res2"=>$res2,"egaux"=>$eg]);
  echo json_encode($res);