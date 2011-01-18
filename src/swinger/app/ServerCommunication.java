package swinger.app;

import java.net.*;
import java.io.*;

import org.apache.http.HttpEntity; 
import org.apache.http.HttpResponse; 
import org.apache.http.HttpVersion; 
import org.apache.http.client.HttpClient; 
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity; 
import org.apache.http.entity.mime.content.ContentBody; 
import org.apache.http.entity.mime.content.StringBody; 
import org.apache.http.entity.mime.content.FileBody; 
import org.apache.http.impl.client.DefaultHttpClient; 
import org.apache.http.params.CoreProtocolPNames; 
import org.apache.http.util.EntityUtils;   


import android.util.Log;

public class ServerCommunication {

	public static final String LOG_TAG = "ServerCommunication";
	
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
	
	

	
	
	
	public void sendPicture(String picturePath) throws Exception
	{
		HttpClient httpclient = new DefaultHttpClient();     
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);      
		HttpPost httppost = new HttpPost("http://www.neotechss.com/swingerapp/actionDispatcher.php");     
		File file = new File(picturePath);   
		
//		FileEntity reqEntity = new FileEntity(file, "binary/octet-stream");
//	    reqEntity.setContentType("binary/octet-stream"); 
//	    httppost.setEntity(reqEntity);     
	    
		MultipartEntity mpEntity = new MultipartEntity();     
		ContentBody cbFile = new FileBody(file, "image/jpeg");     
		mpEntity.addPart("uploadedfile", cbFile);   
		mpEntity.addPart("action", new StringBody("pictureUpdate"));
		httppost.setEntity(mpEntity);     
		System.out.println("executing request " + httppost.getRequestLine());     
		HttpResponse response = httpclient.execute(httppost);     
		HttpEntity resEntity = response.getEntity();      
		Log.v(LOG_TAG, response.getStatusLine().toString());     
		if (resEntity != null) 
		{      
			Log.v(LOG_TAG, EntityUtils.toString(resEntity));     
		}     
		if (resEntity != null) 
		{       
			resEntity.consumeContent();     
		}      
		httpclient.getConnectionManager().shutdown();   
	}	
	
	
//	public void sendPicture(String picturePath)
//	{
//		DataOutputStream outputStream = null;
//
//		String pathToOurFile = picturePath;
//		String lineEnd = "\r\n";
//		String twoHyphens = "--";
//		String boundary =  "*****";
//
//		int bytesRead, bytesAvailable, bufferSize;
//		byte[] buffer;
//		int maxBufferSize = 1*1024*1024;
//
//		try
//		{
//		   	String data = "";
//			data = URLEncoder.encode("action", "UTF-8") + "=" + URLEncoder.encode("pictureUpdate", "UTF-8");
//			
//			init("POST", "Content-Type", "multipart/form-data;boundary="+boundary);
//			
//			FileInputStream fileInputStream = new FileInputStream(new File(pathToOurFile) );
//	
//
//			outputStream = new DataOutputStream( urlConn.getOutputStream() );
//			outputStream.writeChars(data);
//			
//			outputStream.writeBytes(twoHyphens + boundary + lineEnd);
//			outputStream.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + pathToOurFile +"\"" + lineEnd);
//			outputStream.writeBytes(lineEnd);
//	
//			bytesAvailable = fileInputStream.available();
//			bufferSize = Math.min(bytesAvailable, maxBufferSize);
//			buffer = new byte[bufferSize];
//	
//			// Read file
//			bytesRead = fileInputStream.read(buffer, 0, bufferSize);
//	
//			while (bytesRead > 0)
//			{
//				outputStream.write(buffer, 0, bufferSize);
//				bytesAvailable = fileInputStream.available();
//				bufferSize = Math.min(bytesAvailable, maxBufferSize);
//				bytesRead = fileInputStream.read(buffer, 0, bufferSize);
//			}
//	
//			outputStream.writeBytes(lineEnd);
//			outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
//			outputStream.writeBytes(data);
//
//			// Responses from the server (code and message)
//			receiveData();
//	
//			Log.v(LOG_TAG, result);
//			
//			fileInputStream.close();
//			outputStream.flush();
//			outputStream.close();
//		}
//		catch (Exception ex)
//		{
//			Log.e(LOG_TAG, ex.toString());
//			//Exception handling
//		}		
//	}

	public void closeConnection()
	{
		urlConn.disconnect();
	}
}
