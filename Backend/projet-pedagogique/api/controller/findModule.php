<?php
require_once("../models/Projetmodule.php");
header("Access-Control-Allow-Origin:*");
        header("Content-type: application/json");
 
        $id = $_POST['id'];
        $projet = new Projetmodule();
        $J=[];
        $J["data"]=array();

    
        $BFetch=$projet->DetailModuleById($id);
       
        while($Fetch=$BFetch->fetch(PDO::FETCH_ASSOC)){
          
     extract($Fetch);
                $I=array(
                   "nom_module"=>$Fetch['nom_module'],
                "nom_semestre"=>$Fetch['nom_semestre'],
                "nbr_heure"=>$Fetch['nbr_heure'],
               
         
       );
               array_push($J["data"],$I);
              
             
        } ;

       
        echo json_encode($J);
    

?>       