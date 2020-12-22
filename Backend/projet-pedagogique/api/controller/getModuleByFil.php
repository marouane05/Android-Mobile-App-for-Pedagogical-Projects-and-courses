<?php
require_once("../models/Projetmodule.php");
header("Access-Control-Allow-Origin:*");
        header("Content-type: application/json");
       
            $detailModule = new Projetmodule();
            $id_fil=$_POST['id_fil'];   
            $J=[];
  $J["data"]=array();
  $K=[];
  $K["data"]=array();
        $res=[];
        

        $BFetch=$detailModule->ListModuleByFil($id_fil) ;
       
        while($Fetch=$BFetch->fetch(PDO::FETCH_ASSOC)){
          
     extract($Fetch);
                
     $I=array("id"=>$Fetch['id'],
                   
                    "id"=>$Fetch['id'],
                "id_filiere"=>$Fetch['id_filiere'],
            "id_respo"=>$Fetch['id_respo'],
            "intitule"=>$Fetch['intitule'],
            "id_semestre"=>$Fetch['id_semestre'] );
      
                   
         
       array_push($J["data"],$I);
      //  array_push( $K["data"],["nom_module"=>$Fetch['nom_module'],"voila"=>true]);
             
        } ;

   echo json_encode($J);
 //  echo json_encode($K);


       
    

?>       