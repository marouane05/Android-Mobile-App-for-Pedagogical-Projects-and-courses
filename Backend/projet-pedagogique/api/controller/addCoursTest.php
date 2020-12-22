<?php

header("Access-Control-Allow-Origin: *");
header("Content-type: application/json");
require_once("../models/Cours.php");

        $res["reponse"] = array();
        $cours = new Cours();

        $id_cours = $_POST['cours'];
        $id_module=$_POST['module'];
        $titre_cours =$_POST['titre'];

        $exec = $cours->addCours($id_cours,$id_module,$titre_cours) ; 

        array_push($res["reponse"],["succed"=>true,"loggedin"=>$exec]);
        echo json_encode($res);