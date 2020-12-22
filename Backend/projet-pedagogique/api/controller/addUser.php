<?php
  header("Access-Control-Allow-Origin: *");
  header("Content-type: application/json");
require_once('../models/User.php');
//define('PREFIXE_SHA1', 'p8%B;Qdf78');

$email = $_POST['email'];
$password = $_POST['password'];
$profession = $_POST['profession'];

$user = new User();

//$mdp=sha1(PREFIXE_SHA1.$password);

$pass_hash = password_hash($password,PASSWORD_DEFAULT);

$res=$user->save($email,$pass_hash,$profession);

    
    $tabRes["reponse"] = array();

    array_push($tabRes["reponse"],["succed"=>$res]);
    // reponse d'acces true ;
    http_response_code(200);
    
    echo json_encode($tabRes);












