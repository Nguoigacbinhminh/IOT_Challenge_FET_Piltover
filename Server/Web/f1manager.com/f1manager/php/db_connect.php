<?php
function OpenCon()
{
    $dbhost="localhost";
    $dbuser="maestro";
    $dbpass="Dphps278";
    $db="f1manager";

    $conn = new mysqli($dbhost,$dbuser,$dbpass,$db) or die("Connection failed: %s\n". $conn->error);
    return $conn;
}

