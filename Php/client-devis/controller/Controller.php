<?php
    require_once('services/Client.php');
    require_once('services/Devis.php');

    function getAll(){
        $list = json_decode(findAll());
        require('views/listeclient.php');
    }

    function getById($id) {
        $client = json_decode(findById($id));
        require('views/client.php');
    }
?>