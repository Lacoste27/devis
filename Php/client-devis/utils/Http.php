<?php 

    function get($url, $data = null, $param = null) {
        $base_api = "http://localhost:8080/api/";
        $api = curl_init($base_api.$url);
        curl_setopt($api, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($api, CURLOPT_CUSTOMREQUEST, "GET");
        $response = curl_exec($api);
        return $response;
    }

    function add($url, $data) {
        $base_api = "http://localhost:8080/api/";

        $options = array(
            'http' => array(
                'header'  => "Content-type: application/x-www-form-urlencoded\r\n",
                'method'  => 'POST',
                'content' => http_build_query($data)
            )
        );
        $context  = stream_context_create($options);
        $result = file_get_contents($base_api, false, $context);
        if ($result === FALSE) { /* Handle error */ }
    }

    function delete($url, $param) {
        $base_api = "http://localhost:8080/api/";
        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $base_api);
        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "DELETE");
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        $response  = curl_exec($ch);
        curl_close($ch);
        return $response;
    }


?>