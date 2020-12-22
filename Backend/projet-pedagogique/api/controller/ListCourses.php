<?php
require_once("../models/Cours.php");
header("Access-Control-Allow-Origin:*");
        header("Content-type: application/json");
 
            $cours = new Cours();
        $J=[];
        $J["data"]=array();

      


        $BFetch=$cours->getCours() ;
       
        while($Fetch=$BFetch->fetch(PDO::FETCH_ASSOC)){
          
     extract($Fetch);
                $I=array(
                    "id"=>$Fetch['id'],
                    "nom"=>$Fetch['nom'] ,
               "url"=>$Fetch['url']
   );
               array_push($J["data"],$I);
              
             
        } ;

       
        echo json_encode($J);
    

?>       