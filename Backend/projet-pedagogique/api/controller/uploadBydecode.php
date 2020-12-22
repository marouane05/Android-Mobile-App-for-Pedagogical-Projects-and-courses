<?php

header("Access-Control-Allow-Origin: *");
header("Content-type: application/json");
require_once('../models/Projetmodule.php');


$projet_module = new Projetmodule();
$res["reponse"] = array();
 $target_dir = "fichierModule/";
 $fichier = $_POST["fichier"];
 $id_module = $_POST["id_module"];
 $id_page = $_POST["id_page"];

 $repo = $target_dir."/".$id_module."/";
 //
if(!is_dir($repo)){
    mkdir($repo,0777,true) ;
    $target_dir = $repo . "/" . $id_page .".bmp" ;

 if(file_put_contents($target_dir,base64_decode($fichier))) {

    $exec= "The file is uploaded";
   
    $projet_module->ModuleFile($id_page, base64_decode($fichier),$id_page);
    array_push($res["reponse"],["succed"=>true,"loggedin"=>$exec]);
    echo json_encode($res);


 } else {

    $exec=  "Problem uploading file";
    array_push($res["reponse"],["succed"=>true,"loggedin"=>$exec]);
    echo json_encode($res);


 }
}  else {

    $target_dir = $repo . "/" .$id_page .".bmp" ;

    if(file_put_contents($target_dir,base64_decode($fichier))) {
   
       $exec= "The file is uploaded";
   
       array_push($res["reponse"],["succed"=>true,"loggedin"=>$exec]);
       echo json_encode($res);
   
   
    } else {
   
       $exec=  "Problem uploading file";
       array_push($res["reponse"],["succed"=>true,"loggedin"=>$exec]);
       echo json_encode($res);
   
   
    }

}
 
