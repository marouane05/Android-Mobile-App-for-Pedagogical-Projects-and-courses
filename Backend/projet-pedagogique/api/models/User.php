<?php
    require_once("../config/Connection.php");
    class User extends Connection{
     
        public $profession;
        public $email;
        public $cni ;
        public $date ; 
        public $password;
        //auto 
        public $created;
        public $id;
        public function __construct()
        {
            parent::__construct();
        }

        public function save($email,$password,$profession)
        {
            $sql = "insert into user(email,password,profession) values(?,?,?) ";
            $stm = $this->conn->prepare($sql);
            $data = array($email,$password,$profession);
            
          return $stm->execute($data)  ;
        }
        // trouver le professeur ou bien l'administrateur 

         
        public function findProf($email,$naissance)
        {
        
            $BFetch = $this->conn->prepare("select *from professeur where email='$email' and naissance='$naissance'  ;");         
            $BFetch->execute() ; 
            return $BFetch; 
        

        }


        public function findAdmin($email,$naissance)
        {
            $BFetch = $this->conn->prepare("select *from admin where email='$email' and naissance='$naissance'  ;");   
            $BFetch->execute() ; 
            return $BFetch; 
        }    


        public function login($email,$password)
        {
            $sql = "select password from user where email='$email' ;";
            $stm = $this->conn->query($sql);
            if($row = $stm->fetch())
            {   //
                return password_verify($password, $row['password']) ;
            }
            return false ;
        }

        public function findUser($email)
        {
            $sql = "select id from user where email='$email' ;";
            $stm = $this->conn->query($sql);
            if($row = $stm->fetch())
            {
                return true;
            }
            return false ;
        }


        public function updateProf($email,$tele,$password)
        {
            $sql = "update professeur, user set professeur.tele ='$tele' ,user.password='$password' where professeur.email='$email' and user.email='$email' ;";
            $stm = $this->conn->prepare($sql);
          if($stm->execute()){
              return true;
          }
            
            return false ;
            
        }
        public function updateAdmin($email,$tele,$password){
            $sql = "update admin, user set professeur.tele ='$tele' ,user.password='$password' where professeur.email='$email' and user.email='$email' ;";
            $stm = $this->conn->prepare($sql);
          if($stm->execute()){
              return true;
          }
            
            return false ;
            
        }


        public function findProfession($email){
            $sql = "select profession from user where email='$email'  ;";   
            $stm = $this->conn->query($sql);
            if($row = $stm->fetch())
            {   //
                return $row['profession'] ;
            }
            return false ;

        }

        public function findProfByEmail($email)
        {
        
            $BFetch = $this->conn->prepare("select *from professeur where email='$email'  ;");         
            $BFetch->execute() ; 
            return $BFetch; 
        

        }

        public function findAdminByEmail($email)
        {
        
            $BFetch = $this->conn->prepare("select *from admin where email='$email'  ;");         
            $BFetch->execute() ; 
            return $BFetch; 
        

        }


        public function updateProfTele($email,$tele)
        {
            $sql = "update professeur, user set professeur.tele ='$tele'  where professeur.email='$email' and user.email='$email' ;";
            $stm = $this->conn->prepare($sql);
          if($stm->execute()){
              return true;
          }
            
            return false ;
            
        }

        public function updateAdminTele($email,$tele){
            $sql = "update admin, user set professeur.tele ='$tele'  where professeur.email='$email' and user.email='$email' ;";
            $stm = $this->conn->prepare($sql);
          if($stm->execute()){
              return true;
          }
            
            return false ;
            
        }


        public function findStudent($email,$naissance)
        {
        
            $BFetch = $this->conn->prepare("select *from etudiant where email='$email' and naissance='$naissance'  ;");         
            $BFetch->execute() ; 
            return $BFetch; 
        

        }

        public function findStudentByEmail($email)
        {
        
            $BFetch = $this->conn->prepare("select *from etudiant where email='$email'  ;");         
            $BFetch->execute() ; 
            return $BFetch; 
        

        }


    }