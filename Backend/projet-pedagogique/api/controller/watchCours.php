<?php

header("Access-Control-Allow-Origin: *");
header("Content-type: application/json");
require_once("../models/Cours.php");

        $res["reponse"] = array();
        $cours = new Cours();

     
        $id_module=$_POST['id_module'];

        $J=[];
        $J["data"]=array();

        

        $BFetch=$cours->watchCours($id_module) ;
       
        while($Fetch=$BFetch->fetch(PDO::FETCH_ASSOC)){
          
     extract($Fetch);
                $I=array(
                    "id"=>$Fetch['id'],
                    "url"=>$Fetch['url'],
                  
                    "nom"=>$Fetch['nom'] 
         
       );
               array_push($J["data"],$I);
            
             
        } ;

       
        echo json_encode($J);
    

?>       