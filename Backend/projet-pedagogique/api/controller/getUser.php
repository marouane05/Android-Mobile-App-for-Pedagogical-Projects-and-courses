<?php
require_once("../models/User.php");
header("Access-Control-Allow-Origin:*");
        header("Content-type: application/json");
 
            $user = new User();
        $J=[];
        $J["data"]=array();
    
     
     $email = $_POST['email'];   
     
   $profession = $_POST['profession'];

switch($profession){
case "Professeur":
   
    $BFetch=$user->findProfByEmail($email);
    
    while($Fetch=$BFetch->fetch(PDO::FETCH_ASSOC)){
      
 extract($Fetch);
            $I=array(
                "id"=>$Fetch['id'],
            "nom"=>$Fetch['nom'],
            "prenom"=>$Fetch['prenom'],
            "email"=>$Fetch['email'],
            "naissance"=>$Fetch['naissance'],
         "departement"=>$Fetch['departement'],
        "tele"=>$Fetch['tele'],
     
    "profession"=>$profession );
           array_push($J["data"],$I);
          
         
    } ;
    
  //  array_push($J["verification"],["existence"=>$existence]); 
    break ; 

case "Administrateur":
    $BFetch=$user->findAdminByEmail($email);
    
    while($Fetch=$BFetch->fetch(PDO::FETCH_ASSOC)){
      
 extract($Fetch);
            $I=array(
                "id"=>$Fetch['id'],
            "nom"=>$Fetch['nom'],
            "prenom"=>$Fetch['prenom'],
            "email"=>$Fetch['email'],
         "poste"=>$Fetch['poste'],
         "naissance"=>$Fetch['naissance'],
        "tele"=>$Fetch['tele'],
     
    "profession"=>$profession );
           array_push($J["data"],$I);
          
         
    } ;

    break ; 
    case "Etudiant":
   
        $BFetch=$user->findStudentByEmail($email);
        
        while($Fetch=$BFetch->fetch(PDO::FETCH_ASSOC)){
          
     extract($Fetch);
                $I=array(
       

                    "id"=>$Fetch['id'],
                "nom"=>$Fetch['nom'],
                "prenom"=>$Fetch['prenom'],
                "email"=>$Fetch['email'],
                "naissance"=>$Fetch['naissance'],
             "filiere"=>$Fetch['id_filiere'],
            "tele"=>$Fetch['tele'],
         
        "profession"=>$profession );
               array_push($J["data"],$I);
              
             
        } ;
        
      //  array_push($J["verification"],["existence"=>$existence]); 
        break ; 


}




        
        echo json_encode($J);
    

?>