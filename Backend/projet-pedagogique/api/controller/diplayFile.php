<?php

header("Access-Control-Allow-Origin: *");
header("Content-type: application/json");
require_once("../models/Projetmodule.php");

$ListFichier = new Projetmodule();
$id_projet=$_POST['id_projet'];   
$J=[];
$J["data"]=array();



/*$img = file_get_contents( 
'http://192.168.1.9:8080/projet-pedagogique/api/controller/fichierModule/49/page0.bmp'); 
  */
// Encode the image string data into base64 
/*
$data = base64_encode(file_get_contents( 
    'http://192.168.1.9:8080/projet-pedagogique/api/controller/fichierModule/49/page0.bmp') 
      ); 
*/



      $BFetch=$ListFichier->TrouverFichier($id_projet) ;
       
      while($Fetch=$BFetch->fetch(PDO::FETCH_ASSOC)){
        
   extract($Fetch);
              
   $I=array("id"=>$Fetch['id'],                
  "page1" =>base64_encode(file_get_contents("http://192.168.1.9:8080/projet-pedagogique/api/controller/fichierModule/" . $Fetch['module_page1'])),
  "page2" => base64_encode(file_get_contents("http://192.168.1.9:8080/projet-pedagogique/api/controller/fichierModule/" . $Fetch['module_page2'])),
  "id_projet" => $Fetch['id_projet']
 );

 array_push($J["data"],$I);

 
} ;

echo json_encode($J);


?> 