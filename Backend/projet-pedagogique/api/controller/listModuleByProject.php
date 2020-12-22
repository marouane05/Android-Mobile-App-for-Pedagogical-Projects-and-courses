<?php
require_once("../models/Projetmodule.php");
header("Access-Control-Allow-Origin:*");
        header("Content-type: application/json");
       
            $detailModule = new Projetmodule();
            $id_projet=$_POST['id_projet'];   
            $J=[];
  $J["data"]=array();

  $K=[];
 // $J["element"]=array();
        $res=[];
        

        $BFetch=$detailModule->ListModuleByProject($id_projet) ;
       
        while($Fetch=$BFetch->fetch(PDO::FETCH_ASSOC)){
          
     extract($Fetch);
                
     $I=array("id"=>$Fetch['id'],                
    "id_module"=>$Fetch['id_module'],
    "prerequis"=>$Fetch['prerequis'],
    "evaluation"=>$Fetch['evaluation'],
    "notemodule"=>$Fetch['notemodule'],
    "validation"=>$Fetch['validation'],
    "intitulemodule"=>$Fetch['nom_module'],
    "objectif"=>$Fetch['objectif'],
    "hrtp"=>$Fetch['hrtp'],
    "hrtd"=>$Fetch['hrtd'],
    "hrcours"=>$Fetch['hrcours'],
    "description"=>$Fetch['description'],
    "intituleelement"=>$Fetch['intitule']

   );
   array_push($J["data"],$I);
   
   /*
    $M= array(
    "hrtp"=>$Fetch['hrtp'],
    "hrtd"=>$Fetch['hrtd'],
    "hrcours"=>$Fetch['hrcours'],
    "description"=>$Fetch['description'],
    "intituleelement"=>$Fetch['intitule']

   ) ;

   array_push($J["element"],$M);
                 */
         
       
      //  array_push( $K["data"],["nom_module"=>$Fetch['nom_module'],"voila"=>true]);
             
        } ;

   echo json_encode($J);
 //  echo json_encode($K);


       
    

?>       