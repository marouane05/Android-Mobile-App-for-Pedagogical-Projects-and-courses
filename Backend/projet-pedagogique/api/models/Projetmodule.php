<?php
require_once("../config/Connection.php");

class Projetmodule extends Connection{

public $id ;
public $nom_module ;
public $nbr_heure ;
public $id_semestre ;
public $id_projet ; 


    public function __construct()
    {
        parent::__construct();
    }

    

    public function addModule(){
$sql = "insert into moduleprojet (nom_module,nbr_heure,id_semestre,id_projet) values (?,?,?,?) " ;
$stm = $this->conn->prepare($sql);
    $data = array($this->nom_module,$this->nbr_heure,$this->id_semestre,$this->id_projet) ; 
    
    
    return $stm->execute($data)  ;

}


public function DetailModuleById($id_demande){
    $BFetch = $this->conn->prepare("SELECT m.nom_module, m.nbr_heure ,s.nom_semestre from moduleprojet m , semestre s where m.id_semestre=s.id and m.id_projet='$id_demande' order by s.id;");         
    $BFetch->execute() ; 
    return $BFetch; 


}


public function AffectModule($id_prof,$nom_module,$id_semestre,$id_projet){


    $sql = "insert into moduleaffecte(id_prof,nom_module,id_semestre,id_projet,rempli) values(?,?,?,?,'non' );";
    $stm = $this->conn->prepare($sql);
    $data = array($id_prof,$nom_module,$id_semestre,$id_projet);
    
    return $stm->execute($data);

}

public function NotificationModule($id_prof){
    $BFetch = $this->conn->prepare("SELECT C.nom_cycle ,M.id, M.id_projet, S.nom_semestre,M.nom_module,P.nom_projet, Pr.nom , Pr.prenom   from semestre S, moduleaffecte M, offreprojet P,professeur Pr, cycle C where C.id=P.id_cycle and P.id_responsable=Pr.id and S.id=M.id_semestre and P.id=M.id_projet and M.id_prof='$id_prof' and M.rempli ='non';");         
    $BFetch->execute() ; 
    return $BFetch; 


}


public function UpdateModule($id_module){

    $sql = "UPDATE moduleaffecte set rempli='oui' where id='$id_module';";
    $stm = $this->conn->prepare($sql);
  
    
    return $stm->execute();

   

}


public function RemplirModule($id_module,$objectif,$prerequis,$modeevaluation,$notemodule,$validation,$id_projet){

    $sql = "insert into moduledetail(id_module,objectif,prerequis,evaluation,notemodule,validation,id_projet) values(?,?,?,?,?,?,? );";
    $stm = $this->conn->prepare($sql);
    $data = array($id_module,$objectif,$prerequis,$modeevaluation,$notemodule,$validation,$id_projet);
    
    return $stm->execute($data);


}

public function RemplirElement($id_module,$intitule,$hrtd,$hrcours,$hrtp,$desc){

    $sql = "insert into element(id_module,intitule,hrtd,hrcours,hrtp,description) values(?,?,?,?,?,? );";
    $stm = $this->conn->prepare($sql);
    $data = array($id_module,$intitule,$hrtd,$hrcours,$hrtp,$desc);
    
    return $stm->execute($data);

}


public function ListMs($idprj){
    $BFetch = $this->conn->prepare("SELECT * from moduleaffecte   where  id_projet='$idprj' order by id_semestre ;");         
    $BFetch->execute() ; 
    return $BFetch; 

}

public function ListModuleByFil($id_fil){
    $BFetch = $this->conn->prepare("SELECT * from module   where  id_filiere='$id_fil'  ;");         
    $BFetch->execute() ; 
    return $BFetch; 

}


public function ContenuModule($id_module){
    $BFetch = $this->conn->prepare("SELECT  e.id , e.hrtp , e.hrtd , e.hrcours , e.description , e.intitule , d.objectif , d.prerequis, d.evaluation , d.notemodule , d.validation from moduledetail d ,element e   where e.id_module= d.id_module and e.id_module='$id_module' ;");         
    $BFetch->execute() ; 
    return $BFetch; 


}

/*
public function ListModuleByProject($idprj){
    $BFetch = $this->conn->prepare("SELECT d.id_module, d.id , d.prerequis , d.evaluation , d.notemodule , d.validation , d.objectif ,  a.nom_module from moduledetail d , moduleaffecte a  where a.id = d.id_module and d.id_projet='$idprj'  ;");         
    $BFetch->execute() ; 
    return $BFetch; 

}

*/


public function ListModuleByProject($idprj){
    $BFetch = $this->conn->prepare("select d.id_module, d.id , d.prerequis , d.evaluation , d.notemodule , d.validation , d.objectif ,  a.nom_module,
    e.id , e.hrtp , e.hrtd , e.hrcours , e.description , e.intitule
    from moduledetail d 
    , moduleaffecte a , element e
    
    where a.id = d.id_module and e.id_module= d.id_module and d.id_projet='$idprj';");         
    $BFetch->execute() ; 
    return $BFetch; 

}

public function ModuleFile($idp,$pge1,$pge2){

    $sql = "INSERT into modulefile (id_projet,module_page1,module_page2) values(?,?,?);";
    $stm = $this->conn->prepare($sql);
    $data = array($idp,$pge1,$pge2);
    
    return $stm->execute($data);

}

public function ListModuleFile($id_projet)
{
    $BFetch = $this->conn->prepare("SELECT * from modulefile   where  id_projet='$id_projet'  ;"); 
    $BFetch->execute() ; 
    return $BFetch; 

}

public function TrouverFichier($id_projet){
    $BFetch = $this->conn->prepare("SELECT * from modulefile   where id_projet='$id_projet' ;");         
    $BFetch->execute() ; 
    return $BFetch; 


}


}