<?php

class DB_Functions {
	private $conn;

	//constructor
	function __construct() {
		require_once 'db_connect.php';
		$db =  new DB_Connect();
		$this->conn = $db->connect();
	}

	//destructor
	function __destruct() { }

	//Store new user
	//Return user details
	public function storeUser($name, $phone, $password) {
		$uuid = uniqid('', true);
		$hash = $this->hashSSHA($password);
		$encrypted_password = $hash["encrypted"]; //encrypted password
		$salt = $hash["salt"];

		$stmt = $this->conn->prepare("INSERT INTO users(id_user, full_name, phone_number, encrypted_password, salt) VALUES (?,?,?,?,?)");
		$stmt->bind_param("sssss", $uuid, $name, $phone, $encrypted_password, $salt);
		$result = $stmt->execute();
		$stmt->close();

		//Check for successful store
		if($result) {
			$stmt = $this->conn->prepare("SELECT * FROM users WHERE phone_number = ?");
			$stmt->bind_param("s", $phone);
			$stmt->execute();
			$user = $stmt->get_result()->fetch_assoc();
			$stmt->close();

			return $user;
		} else {
			return false;
		}
	}

	public function getUserByPhoneAndPassword($phone, $password) {
		$stmt = $this->conn->prepare("SELECT * FROM users WHERE phone_number = ?");
		$stmt->bind_param("s", $phone);

		if($stmt->execute()) {
			$user = $stmt->get_result()->fetch_assoc();
			$stmt->close();

			//Vefifying user password
			$salt = $user['salt'];
			$encrypted_password = $user['encrypted_password'];
			$hash = $this->checkhashSSHA($salt, $password);

			//Check for password equality
			if($encrypted_password == $hash) {
				return $user;
			}
		} else {
			return NULL;
		}
	}

	//Check user is existed or not
	public function isUserExisted($phone) {
		$stmt = $this->conn->prepare("SELECT phone_number FROM users WHERE phone_number = ?");
		$stmt->bind_param("s", $phone);
		$stmt->execute();
		$stmt->store_result();

		if($stmt->num_rows > 0) {
			$stmt->close();
			return true;			
		} else {
			$stmt->close();
			return false;
		}
	}

	//Encrypting password
	public function hashSSHA($password) {
		$salt = sha1(rand());
		$salt = substr($salt, 0, 10);
		$encrypted = base64_encode(sha1($password . $salt, true) . $salt);
		$hash = array("salt"=>$salt, "encrypted"=>$encrypted);

		return $hash;
	}

	//Decrypting password
	public function checkhashSSHA($salt, $password) {
		$hash = base64_encode(sha1($password . $salt, true) . $salt);
		return $hash;
	}

	public function getUserInfo($phone) {
		$stmt = $this->conn->prepare("SELECT * FROM users WHERE phone_number = ?");
		$stmt->bind_param("s", $phone);
		if($stmt->execute()) {
			$user = $stmt->get_result()->fetch_assoc();
			$stmt->close();

			return $user;
		} else {
			return NULL;
		}
	}

	public function updateUserInfo($name, $phone, $age, $gender, $address) {
		$stmt = $this->conn->prepare("UPDATE users SET full_name = ?, age = ?, gender = ?, address = ? WHERE phone_number = ?");
		$stmt->bind_param("sisss", $name, $age, $gender, $address, $phone);
		$result = $stmt->execute();
		$stmt->close();

		//Check for successful store
		if($result) {
			$stmt = $this->conn->prepare("SELECT * FROM users WHERE phone_number = ?");
			$stmt->bind_param("s", $phone);
			$stmt->execute();
			$user = $stmt->get_result()->fetch_assoc();
			$stmt->close();

			return $user;
		} else {
			return false;
		}
	}

	public function getResultMeasure($phone) {
		$stmt = $this->conn->prepare("SELECT * FROM results WHERE phone_number = ? ORDER BY id_result DESC");
		$stmt->bind_param("s", $phone);
		if($stmt->execute()) {
			$user = $stmt->get_result()->fetch_assoc();
			$stmt->close();

			return $user;
		} else {
			return NULL;
		}
	}

	public function getDetailList($phone) {
		$stmt = $this->conn->prepare("SELECT * FROM results WHERE phone_number = ? ORDER BY id_result DESC");
		$stmt->bind_param("s", $phone);
		if ($stmt->execute()){
			$listResult = array();
			$result = $stmt->get_result();
			while($row = $result->fetch_assoc()) {
				$listResult[] = $row;
			}
			$stmt->close();
			return $listResult;
		} else {
			return NULL;
		} 
	}

	public function changePassword($phone, $newPassword) {
		$hash = $this->hashSSHA($newPassword);
		$encrypted_password = $hash["encrypted"]; //encrypted password
		$salt = $hash["salt"];

		$stmt = $this->conn->prepare("UPDATE users SET encrypted_password = ?, salt = ? WHERE phone_number = ?");
		$stmt->bind_param("sss", $encrypted_password, $salt, $phone);
		$result = $stmt->execute();
		$stmt->close();

		//Check for successful store
		if($result) {
			$stmt = $this->conn->prepare("SELECT * FROM users WHERE phone_number = ?");
			$stmt->bind_param("s", $phone);
			$stmt->execute();
			$user = $stmt->get_result()->fetch_assoc();
			$stmt->close();

			return $user;
		} else {
			return false;
		}
	}
}

?>