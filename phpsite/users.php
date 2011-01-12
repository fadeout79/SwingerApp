<?php

	function loginUser()
	{
		if (isset($_POST["username"]) && isset($_POST["password"]))
		{
			$username = $_POST["username"];
			$password = $_POST["password"];
	
			$query = "SELECT * FROM users WHERE username='$username' AND password='$password'";
			
			$result = mysql_query($query) or die ('ERROR 20: There was a problem sending login information to the mysql database.');
			
			while($row = mysql_fetch_array($result)) 
			{			
			  // TODO > Add here a way to package information to return to the user.
			  echo $row['firstname'] . " " . $row['lastname'];
			}
		}
		else
		{
			echo "ERROR 02: Missing info for connection!";
		}		
	}
	
	function updateStatus()
	{
	
	}

	function sendPingRequest()
	{
	
	}
	
	function sendPongReply()
	{
	
	}	
	
	function getPingRequest()
	{
	
	}
	
	function getPongReply()
	{
			
	}
	
	function updatePicture()
	{
		/// call her the function of the picture update....  
		/// a thumbnail should be produce, as well as a resize of "normalized" 
		/// size image.	
	}
	
	function register()
	{
		/// Add here the informations needed for registering an user.
		if (isset($_POST["username"]) && isset($_POST["password"]))
		{
			$username = $_POST["username"];
			$password = $_POST["password"];
		}
		else
		{
				
		}
	}
	
 ?>