<?php
    require_once("config.php");
    class Connection{
        protected $conn;
        public function __construct()
        {
            global $env;
            try{
                $this->conn = new PDO("mysql:host=$env[db_host];dbname=$env[db_name]", $env['db_username'], $env['db_password']);
                $this->conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            }
            catch(PDOException $e)
            {
                die("Connection failed: " . $e->getMessage());
            }
        }
    }