<?php

//// For every functionality add their include here.

#include "./dbConnect.php"
#include "./users.php"

//



if (isset($_POST["action"]))
{
	$action = $_POST["action"];
	// open the database connection to perform the requested action
	openConnection();
	
	if ($action = "connect")
	{
		loginUser();
	}
	else if ($action = "statusUpdate")
	{
		updateStatus();
	}
	else if ($action = "ping")
	{
		sendPingRequest();
	}
	else if ($action = "pong")
	{
		getPongReply();
	}	
	else if ($action = "pictureUpdate")
	{
		receivePictureFile();
	}
	
	// close the connection when finish
	closeConnection();
}
else
{
	echo "ERROR 01: Missing action parameter!";
} 