<?php
require_once("../models/Projetmodule.php");
header("Access-Control-Allow-Origin:*");
        header("Content-type: application/json");
        $id_module=$_POST['id'];
            $detailModule = new Projetmodule();
        $J=[];
        $J["data"]=array();

        

        $BFetch=$detailModule->ContenuModule($id_module) ;
       
        while($Fetch=$BFetch->fetch(PDO::FETCH_ASSOC)){
          
     extract($Fetch);
                $I=array(
                    "objectif"=>$Fetch['objectif'],
                    "prerequis"=>$Fetch['prerequis'],
                    "evaluation"=>$Fetch['evaluation'],
                    "notemodule"=>$Fetch['notemodule'],
                    "validation"=>$Fetch['validation'],
                    "hrtp"=>$Fetch['hrtp'],
                    "hrtd"=>$Fetch['hrtd'],
                    "hrcours"=>$Fetch['hrcours'],
                    "description"=>$Fetch['description'],
                    "intitule"=>$Fetch['intitule']
         
       );
               array_push($J["data"],$I);
            
             
        } ;

       
        echo json_encode($J);
    

?>       