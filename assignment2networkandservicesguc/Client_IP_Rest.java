import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;


import org.json.JSONObject;

public class Client_IP_Rest {
	public static void main(String[] args) {
		try {
	        Client_IP_Rest.call_me();
		} catch (Exception e) {
	        e.printStackTrace();
		}
	}
	//Gets Ip Address
	public static void call_me() throws Exception {
			
			String webservice = "http://httpbin.org/ip";
			
	        URL a = new URL(webservice);
	        HttpURLConnection connection = (HttpURLConnection) a.openConnection();
	        // optional default is GET
	        connection.setRequestMethod("GET");
	        //add request header
	        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
	        int responseCode = connection.getResponseCode();
	        System.out.println("\nSending 'GET' request to URL : " + webservice);
	        System.out.println("Response Code : " + responseCode);
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(connection.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	        while ((inputLine = in.readLine()) != null) {
	        	response.append(inputLine);
	        }
	        in.close();
	        
	        //print in String
	        System.out.println(response.toString());
	        
	        //Read JSON response and print
	        JSONObject myResponse = new JSONObject(response.toString());
	        System.out.println("JSON Response :");
	        System.out.println("Your Ip Address is :"+myResponse.getString("origin"));
	         
	
	}
	}	
	