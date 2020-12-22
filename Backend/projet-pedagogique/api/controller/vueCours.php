<?php

header("Access-Control-Allow-Origin: *");
header("Content-type: application/json");
require_once("../models/Cours.php");

        $res["reponse"] = array();
        $cours = new Cours();

        $J=[];
        $J["data"]=array();

        $id_cours = $_POST['id_cours'];
        $id_etudiant=$_POST['id_etudiant'];
    

        $exec = $cours->vueCours($id_cours,$id_etudiant) ; 

        $BFetch=$cours->nombreVue($id_cours) ;
       
        while($Fetch=$BFetch->fetch(PDO::FETCH_ASSOC)){
          
     extract($Fetch);
                $I=array(
                  
                    "prenom"=>$Fetch['prenom'],
                    "nom"=>$Fetch['nom'],
                    "id_etudiant"=>$Fetch['id_etudiant'],
                    $exec
       );
               array_push($J["data"],$I);
            
             
        } ;




       
        echo json_encode($J);