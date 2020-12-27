<?php

require_once('connection.php');

$result = array();
$nim = $_GET['nim'];

$query = mysqli_query($CON,"SELECT * FROM profil WHERE nim='$nim'");
while($row = mysqli_fetch_assoc($query)){
  $result[] = $row;
}

echo json_encode(array('result'=>$result));

?>
