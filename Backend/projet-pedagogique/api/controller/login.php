<?php
  header("Access-Control-Allow-Origin: *");
  header("Content-type: application/json");
  require_once("../models/User.php");

  $data = json_decode(file_get_contents("php://input"), true);
  
  $res["reponse"] = array();
  
  $user = new User();

  
  //$email = $data['email'];
  $email = $_POST['email'];
  //$password = $data['password'];
  $password=$_POST['password'];
/*
  $res=array("succed"=>true,"loggedin"=>$id);

  echo json_encode($res); */

  $id = $user->login($email,$password);
  $profession = $user->findProfession($email);
/*
  array_push($res,["succed"=>true,"loggedin"=>$id]);

  echo json_encode($res);
  */

  //$res=array("succed"=>true,"loggedin"=>$id);
  array_push($res["reponse"],["succed"=>true,"loggedin"=>$id,"profession"=>$profession]);
  echo json_encode($res);