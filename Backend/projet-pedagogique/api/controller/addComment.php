<?php

header("Access-Control-Allow-Origin: *");
header("Content-type: application/json");
require_once("../models/Cours.php");

        $res["reponse"] = array();
        $cours = new Cours();

        $id_etudiant = $_POST['id_etudiant'];
        $id_cours=$_POST['id_cours'];
        $commentaire =$_POST['commentaire'];

        $exec = $cours->addComment($id_etudiant,$id_cours,$commentaire) ; 

        array_push($res["reponse"],["succed"=>true,"loggedin"=>$exec]);
        echo json_encode($res);