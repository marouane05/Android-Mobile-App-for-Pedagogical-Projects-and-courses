<?php
require_once("../models/Projetmodule.php");
header("Access-Control-Allow-Origin:*");
        header("Content-type: application/json");
       
            $detailModule = new Projetmodule();
            $id_projet=$_POST['id_projet'];   
            $J=[];
  $J["data"]=array();
  $K=[];
  $K["data"]=array();
        $res=[];
        

        $BFetch=$detailModule->ListMs($id_projet) ;
       
        while($Fetch=$BFetch->fetch(PDO::FETCH_ASSOC)){
          
     extract($Fetch);
                
     $I=array("id"=>$Fetch['id'],
                   
                    "id_semestre"=>$Fetch['id_semestre'],
                "id_projet"=>$Fetch['id_projet'],
            "nom_module"=>$Fetch['nom_module'] );
      
                   
         
       array_push($J["data"],$I);
      //  array_push( $K["data"],["nom_module"=>$Fetch['nom_module'],"voila"=>true]);
             
        } ;

   echo json_encode($J);
 //  echo json_encode($K);


       
    

?>       