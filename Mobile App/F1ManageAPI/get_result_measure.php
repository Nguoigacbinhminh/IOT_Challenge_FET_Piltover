<?php

require_once 'db_function.php';
$db = new DB_Functions();

//JSON respone
$response = array("error"=>FALSE);

if(isset($_POST['phone'])) {
	//receiving the post
	$phone = $_POST['phone'];

	$result = $db->getResultMeasure($phone);

	if ($result != false) {
		$response["error"] = FALSE;
		$response["uid"] = $result["id_result"];
		$response["result"]["phone"] = $result["phone_number"];
		$response["result"]["heart"] = $result["heart"];
		$response["result"]["temp"] = $result["temp"];
		$response["result"]["spo2"] = $result["spo2"];
		$response["result"]["time"] = $result["timestamps"];

		echo json_encode($response);
	} else {

		$response["error"] = TRUE;
		$response["error_msg"] = "Chưa có lần đo nào!";
		echo json_encode($response);
	}
} else {
	$response["error"] = TRUE;
	$response["error_msg"] = "Required parameters phone is missing";
	echo json_encode($response);
}

?>