<?php
require_once("../models/Prof.php");
header("Access-Control-Allow-Origin:*");
        header("Content-type: application/json");
 
            $prof = new Prof();
        $J=[];
        $J["data"]=array();

        $id_prof =$_POST['id_prof'];
        $id_filiere=$_POST['id_filiere'];


        $BFetch=$prof-> ModuleFiliereProf($id_prof,$id_filiere) ;
       
        while($Fetch=$BFetch->fetch(PDO::FETCH_ASSOC)){
          
     extract($Fetch);
                $I=array(
                    "id"=>$Fetch['id'],
                    "intitule"=>$Fetch['intitule']
                    
         
       );
               array_push($J["data"],$I);
              
             
        } ;

       
        echo json_encode($J);
    

?>       