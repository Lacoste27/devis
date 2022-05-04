<?php
    require_once('controller/Controller.php');
    try {
        if (isset($_GET["action"])) {
            $action = $_GET["action"];
            if ($action == "listeclient") {
                getAll();
            }
            if ($action == "byId") {
                $id = $_GET["id"];
                getById($id);
            }
        } else {
            require('views/accueil.php');
        }
    } catch (Exception $e) {
        echo ($e.getMessage());
    }
?>