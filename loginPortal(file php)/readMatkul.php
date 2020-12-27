<?php

require_once('connection.php');

$result = array();
$jurusan = $_GET['jurusan'];

$query = mysqli_query($CON,"SELECT * FROM mata_kuliah WHERE jurusan='$jurusan'");
while($row = mysqli_fetch_assoc($query)){
  $result[] = $row;
}

echo json_encode(array('result'=>$result));

?>
