<?php
require_once("../models/Projet.php");
header("Access-Control-Allow-Origin:*");
        header("Content-type: application/json");
 
        $id_projet = $_POST['id_projet'];
        $projet = new Projet();
        $J=[];
        $J["data"]=array();

    
        $BFetch=$projet->EquipeByProjet($id_projet);
       
        while($Fetch=$BFetch->fetch(PDO::FETCH_ASSOC)){
          
     extract($Fetch);
                $I=array(
                   "nom"=>$Fetch['nom'],
                   "prenom"=>$Fetch['prenom'],
                   "tele"=>$Fetch['tele'],
                   "email"=>$Fetch['email']
         
       );
               array_push($J["data"],$I);
              
             
        } ;

       
        echo json_encode($J);
    

?>       