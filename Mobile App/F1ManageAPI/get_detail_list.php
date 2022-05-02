<?php

require_once 'db_function.php';
$db = new DB_Functions();

//JSON respone
$responseList = array();

if(isset($_POST['phone'])) {
	//receiving the post
	$phone = $_POST['phone'];

	$result = $db->getDetailList($phone);
	if ($result != false) {
		foreach($result as $value) {
			$response["uid"] = $value["id_result"];
			$response["phone"] = $value["phone_number"];
			$response["heart"] = $value["heart"];
			$response["temp"] = $value["temp"];
			$response["spo2"] = $value["spo2"];
			$response["time"] = $value["timestamps"];
			$responseList[] = $response;
		}

		echo json_encode($responseList);
	} else {

		$response["error"] = TRUE;
		$response["error_msg"] = "Unknown phone number";
		echo json_encode($response);
	}
} else {
	$response["error"] = TRUE;
	$response["error_msg"] = "Required parameters phone is missing";
	echo json_encode($response);
}

?>