<?php
require_once("../models/Prof.php");
header("Access-Control-Allow-Origin:*");
        header("Content-type: application/json");
 
            $prof = new Prof();
        $J=[];
        $J["data"]=array();

        $id_prof =$_POST['id_prof'];


        $BFetch=$prof->FiliereProf($id_prof) ;
       
        while($Fetch=$BFetch->fetch(PDO::FETCH_ASSOC)){
          
     extract($Fetch);
                $I=array(
                    "id"=>$Fetch['id'],
                    "intitule"=>$Fetch['intitule'],
                    "moduleintitule"=>$Fetch['moduleintitule'],
                    "idmodule"=>$Fetch['idmodule']
                    
         
       );
               array_push($J["data"],$I);
              
             
        } ;

       
        echo json_encode($J);
    

?>       