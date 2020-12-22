<?php
require_once("../models/Projet.php");
header("Access-Control-Allow-Origin:*");
        header("Content-type: application/json");
        $res["reponse"] = array();
        $projet = new Projet();

        $image = $_POST['image'];
        $id_projet=$_POST['id_projet'];

        $exec = $projet->AddImage($image,$id_projet) ; 

        array_push($res["reponse"],["succed"=>true,"loggedin"=>$exec]);
        echo json_encode($res);