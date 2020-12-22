<?php
require_once("../models/Prof.php");
header("Access-Control-Allow-Origin:*");
        header("Content-type: application/json");
 
            $prof = new Prof();
        $J=[];
        $J["data"]=array();



        $BFetch=$prof->ListProf();
       
        while($Fetch=$BFetch->fetch(PDO::FETCH_ASSOC)){
          
     extract($Fetch);
                $I=array(
                    "id"=>$Fetch['id'],
                    "nom"=>$Fetch['nom'],
                    "prenom"=>$Fetch['prenom'],
                    "email"=>$Fetch['email'],
                    "naissance"=>$Fetch['naissance'],
                 "departement"=>$Fetch['departement'],
                "tele"=>$Fetch['tele'],
                "role"=>$Fetch['role']
         
       );
               array_push($J["data"],$I);
              
             
        } ;

       
        echo json_encode($J);
    

?>       