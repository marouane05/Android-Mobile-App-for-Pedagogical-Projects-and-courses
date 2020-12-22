<?php
  header("Access-Control-Allow-Origin: *");
  header("Content-type: application/json");
  require_once("../models/Projet.php");

  $data = json_decode(file_get_contents("php://input"), true);
  
  $res["data"] = array();
  
  $projet = new Projet();

  
  //$email = $data['email'];
  $val = $_POST['situation'];
  //$password = $data['password'];
  $id=$_POST['id'];
/*
  $res=array("succed"=>true,"loggedin"=>$id);

  echo json_encode($res); */

  $ex = $projet->ValiderProjet($val,$id);

/*
  array_push($res,["succed"=>true,"loggedin"=>$id]);

  echo json_encode($res);
  */

  //$res=array("succed"=>true,"loggedin"=>$id);
  array_push($res["data"],["succed"=>true,"execution"=>$ex]);
  echo json_encode($res);