<?php
require_once("../models/User.php");
header("Access-Control-Allow-Origin:*");
        header("Content-type: application/json");
 
            $user = new User();
        $J=[];
        $J["data"]=array();
    
     $tele = $_POST['tele'];
     $profession = $_POST['profession'];
     $email = $_POST['email'];   
     $password = $_POST['password'];
     $naissance= $_POST['naissance'];
     $pass_hash = password_hash($password,PASSWORD_DEFAULT);

switch($profession){
case "Professeur":
    if($password==""){
        $modification =$user->updateProfTele($email,$tele) ;
    }else {
        $modification =$user->updateProf($email,$tele,$pass_hash) ;
    }
   
    $BFetch=$user->findProf($email,$naissance);
    
    while($Fetch=$BFetch->fetch(PDO::FETCH_ASSOC)){
      
 extract($Fetch);
            $I=array(
            "nom"=>$Fetch['nom'],
            "prenom"=>$Fetch['prenom'],
            "email"=>$Fetch['email'],
         "departement"=>$Fetch['departement'],
        "tele"=>$Fetch['tele'],
     
    "modification"=>$modification );
           array_push($J["data"],$I);
          
         
    } ;
    
  //  array_push($J["verification"],["existence"=>$existence]); 
    break ; 

case "Administrateur":

    if($password==""){
        $modification =$user->updateAdminTele($email,$tele) ;
     }
    else 
    {$modification =$user->updateAdmin($email,$tele,$pass_hash) ;}
    
    $BFetch2=$user->findAdmin($email,$naissance);

    while($Fetch2=$BFetch2->fetch(PDO::FETCH_ASSOC)){
      
 extract($Fetch2);
            $I2=array(
            "nom"=>$Fetch2['nom'],
            "prenom"=>$Fetch2['prenom'],
            "email"=>$Fetch2['email'],
         "poste"=>$Fetch2['poste'],
        "tele"=>$Fetch2['tele'] ,
        "modification"=>$modification   );
           array_push($J["data"],$I2);
          
         
    } ;
  

    break ; 



}




        
        echo json_encode($J);
    

?>