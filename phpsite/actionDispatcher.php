<?php

//// For every functionality add their include here.

include('./connect.php');
include('./users.php');
include('./thumbnail_generator.php');

//
$response = "";


if (isset($_POST["action"]))
{
	$action = $_POST["action"];
	// open the database connection to perform the requested action
	//openConnection();
	$response = $response . $action;

	logToFile($response);	
	if ($action == "connect")
	{
		loginUser();
	}
	else if ($action == "statusUpdate")
	{
		updateStatus();
	}
	else if ($action == "ping")
	{
		sendPingRequest();
	}
	else if ($action == "pong")
	{
		getPongReply();
	}	
	else if ($action == "pictureUpdate")
	{
		receivePictureFile();
	}
	logToFile($response);	
	
	// close the connection when finish
	//closeConnection();
}
else if (isset($_FILE['uploadedfile']))
{
  receivePictureFile();
  $response = $response . " UPLOAD OK";
  logToFile($response);	
}
else
{
	$response = $response . "ERROR 01: Missing action parameter!";
	logToFile($response);	
}


function logToFile($logging)
{
	$myFile = "result.txt";
	$fh = fopen($myFile, 'w+') or die("can't open file");
	fwrite($fh, $logging);
	fwrite($fh, "\n");
	fclose($fh);
} 