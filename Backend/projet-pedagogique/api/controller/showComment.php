<?php

header("Access-Control-Allow-Origin: *");
header("Content-type: application/json");
require_once("../models/Cours.php");

        $res["reponse"] = array();
        $cours = new Cours();

     
        $id_cours=$_POST['id_cours'];

        $J=[];
        $J["data"]=array();

        

        $BFetch=$cours->showComment($id_cours) ;
       
        while($Fetch=$BFetch->fetch(PDO::FETCH_ASSOC)){
          
     extract($Fetch);
                $I=array(
                    "id"=>$Fetch['id'],
                    "prenom"=>$Fetch['prenom'],
                    "nom"=>$Fetch['nom'],
                    "commentaire"=>$Fetch['commentaire']
       );
               array_push($J["data"],$I);
            
             
        } ;

       
        echo json_encode($J);
    

?>       