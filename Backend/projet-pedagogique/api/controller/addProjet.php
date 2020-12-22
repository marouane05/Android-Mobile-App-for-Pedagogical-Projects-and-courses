<?php
 header("Access-Control-Allow-Origin: *");
 header("Content-type: application/json");
require_once('../models/Projet.php');

$projet = new Projet();


 $nom_projet=$_POST['nom_projet'] ;
 
 $description =$_POST['description'];
 $admission=$_POST['admission'] ;
 $debouche=$_POST['debouche'] ;
 $cout= $_POST['cout'] ;

 $nbr_semestre = $_POST['nbr_semestre'];

 $id_responsable =$_POST['id_responsable'];
 $id_cycle = $_POST['id_cycle']; 

 $projet->nom_projet = $nom_projet ;
 $projet->description =  $description ;
 $projet->admission =  $admission ;
 $projet->debouche = $debouche ;
 $projet->cout = $cout ;

 $projet->id_responsable = $id_responsable ;
 $projet->id_cycle=$id_cycle ; 

 $res = $projet->addProjet($nbr_semestre);
 $tab["data"] = array();

 array_push($tab["data"],["id_projet"=>$res]);
 http_response_code(200);
echo json_encode($tab);