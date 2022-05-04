<?php
    require_once('utils/Http.php');

    function findAllDemandeClient($id) {
        return get("client/all");
    }

    function findById($id) {
        return get("client/id/".$id);
    }

?>