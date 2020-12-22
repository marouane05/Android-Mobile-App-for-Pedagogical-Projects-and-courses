<?php
require_once("../models/Projetmodule.php");
header("Access-Control-Allow-Origin:*");
        header("Content-type: application/json");
 
        $id = $_POST['id'];
        $projet = new Projetmodule();
        $J=[];
        $J["data"]=array();

    
        $BFetch=$projet->NotificationModule($id);
       
        while($Fetch=$BFetch->fetch(PDO::FETCH_ASSOC)){
          
     extract($Fetch);
     
                $I=array(
                   "id_module"=>$Fetch['id'],
                   "nom_projet"=>$Fetch['nom_projet'],
                   "id_projet"=>$Fetch['id_projet'],
                   "module"=>$Fetch['nom_module'],
                   "semestre"=>$Fetch['nom_semestre'],
                   "ProfNom"=>$Fetch['nom'],
                   "ProfPrenom"=>$Fetch['prenom'],
                   "cycle"=>$Fetch['nom_cycle']
         
                      );

               array_push($J["data"],$I);
              
             
        } ;

       
        echo json_encode($J);
    

?>       