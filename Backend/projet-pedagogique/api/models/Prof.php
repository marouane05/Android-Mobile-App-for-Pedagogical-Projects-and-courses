<?php
    require_once("../config/Connection.php");

    class Prof extends Connection {

public $id;
public $nom ;
public $prenom ;
public $email ;
public $departement ; 
public $naissance ;
public $role ;
public $tele ;





      public function ListProf(){
        $BFetch = $this->conn->prepare("SELECT *from professeur;");         
    $BFetch->execute() ; 
    return $BFetch; 
      }

      


    function ListMod() {
      $tabpush=[];
      $tabpush["data"]=array();

      $sql = 'SELECT * from moduleaffecte';
   
        $stmt = $this->$conn->query($sql);
        $stmt->execute();
        while ($row = $stmt->fetch(PDO::FETCH_NUM, PDO::FETCH_ORI_NEXT)) {
       //   $data = $row[0] . "\t" . $row[1] . "\t" . $row[2] . "\n";
          $j= array(
            "id"=>$row[0],
            "nom"=> $row[1]
          );

          array_push($tabpush["data"],$j) ; 

        }
     
        return $tabpush;
      
    }

public function FiliereProf($id_prof){

  $BFetch = $this->conn->prepare("select f.intitule as intitule , f.id as id , m.intitule as moduleintitule , m.id as idmodule
   from profparfiliere p  , filiere f , module m   where  
  p.id_filiere = f.id 
  and m.id_filiere=f.id 
  and m.id_respo='$id_prof';");
  $BFetch->execute() ; 
  return $BFetch;

}


public function ModuleFiliereProf($id_prof,$id_filiere){

  $BFetch = $this->conn->prepare("select m.intitule , m.id from module m  where id_filiere='$id_filiere' and id_respo='$id_prof';");
  $BFetch->execute() ; 
  return $BFetch;

}















    }