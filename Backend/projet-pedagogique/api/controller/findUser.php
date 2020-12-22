<?php
require_once("../models/User.php");
header("Access-Control-Allow-Origin:*");
        header("Content-type: application/json");
 
            $user = new User();
        $J=[];
        $J["data"]=array();
    
     $naissance = $_POST['naissance'];
     $profession = $_POST['profession'];
     $email = $_POST['email'];   



switch($profession){
case "Professeur":
    $BFetch=$user->findProf($email,$naissance);
    $exist =$user->findUser($email) ;
    while($Fetch=$BFetch->fetch(PDO::FETCH_ASSOC)){
      
 extract($Fetch);
            $I=array(
            "nom"=>$Fetch['nom'],
            "prenom"=>$Fetch['prenom'],
            "email"=>$Fetch['email'],
         "departement"=>$Fetch['departement'],
        "tele"=>$Fetch['tele'],
     
    "existence"=>$exist );
           array_push($J["data"],$I);
          
         
    } ;
    
  //  array_push($J["verification"],["existence"=>$existence]); 
    break ; 

case "Administrateur":

    $BFetch2=$user->findAdmin($email,$naissance);
    $existence =$user->findUser($email) ;
    while($Fetch2=$BFetch2->fetch(PDO::FETCH_ASSOC)){
      
 extract($Fetch2);
            $I2=array(
            "nom"=>$Fetch2['nom'],
            "prenom"=>$Fetch2['prenom'],
            "email"=>$Fetch2['email'],
         "poste"=>$Fetch2['poste'],
        "tele"=>$Fetch2['tele'] ,
        "existence"=>$existence   );
           array_push($J["data"],$I2);
            };
          
 case "Etudiant":

    $BFetch3=$user->findStudent($email,$naissance);
    $existence =$user->findUser($email) ;
    while($Fetch3=$BFetch3->fetch(PDO::FETCH_ASSOC)){
      
 extract($Fetch3);
            $I3=array(
            "nom"=>$Fetch3['nom'],
            "prenom"=>$Fetch3['prenom'],
            "email"=>$Fetch3['email'],
         "filiere"=>$Fetch3['id_filiere'],
        "tele"=>$Fetch3['tele'] ,
        "existence"=>$existence   );
           array_push($J["data"],$I3);
         
    } ;
  

    break ; 



}




        
        echo json_encode($J);
    

?>