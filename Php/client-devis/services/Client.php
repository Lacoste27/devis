<?php
    require_once('utils/Http.php');

    function findAll() {
        return get("client/all");
    }

    function findById($id) {
        return get("client/id/".$id);
    }

?>