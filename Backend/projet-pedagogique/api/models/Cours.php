<?php
    require_once("../config/Connection.php");

    class Cours extends Connection {

        
     public function getCours(){
       
  $BFetch = $this->conn->prepare("select id , nom , url from cours  ;");
  $BFetch->execute() ; 
  return $BFetch;



     }
    
     


     public function addCours($id_cours,$id_module,$titre_cours)
     {
        $sql = "insert into addcours(id_cours,id_module,titre) values(?,?,?) ";
        $stm = $this->conn->prepare($sql);
        $data = array($id_cours,$id_module,$titre_cours);
        
    return $stm->execute($data)  ;
     }




     public function watchCours($id_module){
       
      $BFetch = $this->conn->prepare("select c.id , c.nom , c.url from cours c , addcours a where c.id=a.id_cours and a.id_module='$id_module' ;");
      $BFetch->execute() ; 
      return $BFetch;
    
    
    
         }

      
      public function addComment($id_etudiant,$id_cours,$commentaire){
         $sql = "insert into commentaire (id_etudiant,id_cours,commentaire) values(?,?,?) ";
         $stm = $this->conn->prepare($sql);
         $data = array($id_etudiant,$id_cours,$commentaire);
         
     return $stm->execute($data)  ;


      }   
      
      public function showComment($id_cours){
       
         $BFetch = $this->conn->prepare("select e.nom, e.prenom , c.commentaire , c.id from commentaire c , etudiant e where e.id = c.id_etudiant and c.id_cours ='$id_cours' ;");
         $BFetch->execute() ; 
         return $BFetch;
       
       
       
            }

            public function vueCours($id_etudiant,$id_cours){
               $sql = "insert into vue (id_etudiant,id_cours) values(?,?) ";
               $stm = $this->conn->prepare($sql);
               $data = array($id_etudiant,$id_cours);
               
           return $stm->execute($data)  ;
      
      
            }   

            public function nombreVue($id_cours){

               $BFetch = $this->conn->prepare("select distinct v.id_etudiant , e.nom, e.prenom from etudiant e, vue v where e.id = v.id_etudiant and v.id_cours='$id_cours';");
               $BFetch->execute() ; 
               return $BFetch;


            }




    }