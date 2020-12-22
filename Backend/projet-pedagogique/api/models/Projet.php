<?php
    require_once("../config/Connection.php");

    class Projet extends Connection {

public $nom_projet ;
public $id ;
public $description ;
public $admission ;
public $debouche ;
public $cout ;
public $date_demande ;

public $id_responsable ;
public $id_cycle; 





        public function __construct()
        {
            parent::__construct();
        }

public function addProjet($nb){
    $sql = "insert into offreprojet(nom_projet,description,admission,debouche,cout,id_responsable,id_cycle,nbr_semestre,situation) values(?,?,?,?,?,?,?,?,'oui') ";
    $stm = $this->conn->prepare($sql);
    $data = array($this->nom_projet,$this->description, $this->admission,$this->debouche,$this->cout,$this->id_responsable,$this->id_cycle,$nb);
    
$stm->execute($data)  ;

$last_id = $this->conn->lastInsertId();

return  $last_id;


}


public function DetailProjet(){
    $BFetch = $this->conn->prepare("SELECT F.id , F.nom_projet , F.description , F.admission , F.debouche , F.cout , F.date_demande , P.nom , P.prenom , P.email  , C.nom_cycle  FROM professeur P , offreprojet F , cycle C   WHERE F.id_responsable= P.id and F.id_cycle = C.id  and F.situation='A';");         
    $BFetch->execute() ; 
    return $BFetch; 


}


public function AddImage($image,$id_projet){
$sql = "insert into moduleimage (image,id_projet) values (?,?) ;";
$stm = $this->conn->prepare($sql);
$data = array($image,$id_projet);

return $stm->execute($data)  ;

}

public function DetailProjetById($id_demande){
    $BFetch = $this->conn->prepare("SELECT F.id , F.nom_projet , F.description , F.admission , F.debouche , F.cout , F.date_demande , F.nbr_semestre , P.nom , P.prenom , P.email  , C.nom_cycle  FROM professeur P , offreprojet F , cycle C   WHERE F.id_responsable= P.id and F.id_cycle = C.id and F.id='$id_demande' ;");         
    $BFetch->execute() ; 
    return $BFetch; 


}


public function ValiderProjet($val,$id){

    $sql = "UPDATE offreprojet set situation=? where id = ?;";
$stm = $this->conn->prepare($sql);
$data = array($val,$id);

return $stm->execute($data)  ;


}


public function NotificationProjet($id,$situation){
    $BFetch = $this->conn->prepare("SELECT F.id , F.nom_projet , F.situation , F.nbr_semestre , C.nom_cycle  FROM cycle C , offreprojet F    WHERE F.id_cycle = C.id and F.id_responsable='$id' and F.situation='$situation';");         
    $BFetch->execute() ; 
    return $BFetch; 


}


public function ProjetRempli($id_projet){
    $sql = "SELECT COUNT(*) as nombre FROM `moduleaffecte` WHERE id_projet='$id_projet';";
    $stm = $this->conn->query($sql);
  
    if($row = $stm->fetch())
    {   //
        return $row['nombre'] ;
    }
    return 0  ;


}

public function ModuleRempli ($id_projet){

    $sql = "SELECT COUNT(*) as nombre FROM `moduleaffecte` WHERE id_projet='$id_projet' and rempli='oui';";
    $stm = $this->conn->query($sql);

    if($row = $stm->fetch())
    {   //
        return $row['nombre'] ;
    }
    return 0  ;


}


public function EquipeByProjet($id_projet){
    $BFetch = $this->conn->prepare("SELECT DISTINCT(p.id), p.nom , p.prenom , p.tele , p.email from professeur p , moduleaffecte m where p.id = m.id_prof and m.id_projet = '$id_projet' ;");         
    $BFetch->execute() ; 
    return $BFetch; 


}





    }