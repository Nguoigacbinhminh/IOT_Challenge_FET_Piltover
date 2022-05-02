<?php

require_once 'db_function.php';
$db = new DB_Functions();

//JSON respone
$response = array("error" => FALSE);

if(isset($_POST['phone']) && isset($_POST['password'])) {
	//receiving the post
	$phone = $_POST['phone'];
	$password = $_POST['password'];

    $user = $db->changePassword($phone, $password);

    if ($user != false) {
        $response["error"] = FALSE;
        $response["uid"] = $user["id_user"];
        $response["user"]["name"] = $user["full_name"];
        $response["user"]["phone"] = $user["phone_number"];
        $response["user"]["age"] = $user["age"];
        $response["user"]["gender"] = $user["gender"];
        $response["user"]["address"] = $user["address"];

        echo json_encode($response);			
    } else {
        $response["error"] = TRUE;
        $response["error_msg"] = "Unknow error occured in registration!";
        echo json_encode($response);
    }

} else {
	$response["error"] = TRUE;
	$response["error_msg"] = "Information is missing";
	echo json_encode($response);
}

?>