package swinger.app;

import java.net.*;
import java.io.*;

public class ServerCommunication {

	URL url;
	HttpURLConnection urlConn;
	BufferedReader input;
	String error = "";
	String result = "";

	public void sendData(String iData)
	{
		try 
		{
			OutputStreamWriter wr = new OutputStreamWriter(urlConn.getOutputStream());
			wr.write(iData);
			wr.flush();
			
		}
		catch (Exception e)
		{
			result = "SendData : " + e.toString() + "\n";
		}
	}
	
	public String receiveData()
	{
		try
		{
			DataInputStream in = new DataInputStream(urlConn.getInputStream());
			input = new BufferedReader(new InputStreamReader(in));
			String str;
			while ((str = input.readLine()) != null) {
				result = result + str + "\n";
			}
			input.close();		
		}
		catch (Exception e)
		{
			result = result + "ReceiveData : " + e.toString() + "\n";			
		}
		return result;
	}
	
	public void init(String method, String field, String newValue) 
	{
		try 
		{
			url = new URL("http://www.neotechss.com/swingerapp/actionDispatcher.php");
		
			urlConn = (HttpURLConnection)url.openConnection();
			urlConn.setRequestMethod(method);
			urlConn.setRequestProperty(field, newValue);
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			urlConn.setUseCaches(false);
			
		}
		catch (Exception e) 
		{
			result = "Init : " + e.toString() + "\n";
		}
	}
	
	
	public void sendPicture(String picturePath)
	{
		DataOutputStream outputStream = null;
		//DataInputStream inputStream = null;

		String pathToOurFile = picturePath + ".jpeg";
		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary =  "*****";

		int bytesRead, bytesAvailable, bufferSize;
		byte[] buffer;
		int maxBufferSize = 1*1024*1024;

		try
		{
		   	String data = "";
			data = URLEncoder.encode("action", "UTF-8") + "=" + URLEncoder.encode("updatePicture", "UTF-8");
			
			init("POST", "Content-Type", "multipart/form-data;boundary="+boundary);
			
			FileInputStream fileInputStream = new FileInputStream(new File(pathToOurFile) );
	
			result += "d";
	
			outputStream = new DataOutputStream( urlConn.getOutputStream() );
			outputStream.writeBytes(twoHyphens + boundary + lineEnd);
			outputStream.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + pathToOurFile +"\"" + lineEnd);
			outputStream.writeBytes(lineEnd);
	
			bytesAvailable = fileInputStream.available();
			bufferSize = Math.min(bytesAvailable, maxBufferSize);
			buffer = new byte[bufferSize];
	
			// Read file
			bytesRead = fileInputStream.read(buffer, 0, bufferSize);
	
			while (bytesRead > 0)
			{
				outputStream.write(buffer, 0, bufferSize);
				bytesAvailable = fileInputStream.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = fileInputStream.read(buffer, 0, bufferSize);
			}
	
			outputStream.writeBytes(lineEnd);
			outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
			outputStream.writeBytes(data);
	
			// Responses from the server (code and message)
	//		int serverResponseCode = urlConn.getResponseCode();
	//		String serverResponseMessage = urlConn.getResponseMessage();
	
			fileInputStream.close();
			outputStream.flush();
			outputStream.close();
		}
		catch (Exception ex)
		{
			result += ex.toString();
			//Exception handling
		}		
	}

	public void closeConnection()
	{
		urlConn.disconnect();
	}
}
