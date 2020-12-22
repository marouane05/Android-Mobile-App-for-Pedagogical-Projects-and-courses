<?php
require_once("../models/Projet.php");
header("Access-Control-Allow-Origin:*");
        header("Content-type: application/json");
 
        $id = $_POST['id'];
        $situation = $_POST['situation'];
        $projet = new Projet();
        $J=[];
        $J["data"]=array();

    
        $BFetch=$projet->NotificationProjet($id,$situation);
       
        while($Fetch=$BFetch->fetch(PDO::FETCH_ASSOC)){
          
     extract($Fetch);
                $I=array(
                   "id"=>$Fetch['id'],
                "nom_projet"=>$Fetch['nom_projet'],
                "situation"=>$Fetch['situation'],
                "nom_cycle"=>$Fetch['nom_cycle'],
                "nombre_semestre"=>$Fetch['nbr_semestre']
       );
               array_push($J["data"],$I);
              
             
        } ;

       
        echo json_encode($J);
    

?>       