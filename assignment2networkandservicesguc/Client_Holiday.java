import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client_Holiday {
	public static void main(String[] args) {
		 
		 try {
		 String url = "http://www.holidaywebservice.com/HolidayService_v2/HolidayService2.asmx?op=GetHolidaysAvailable";
		 URL a = new URL(url);
		 HttpURLConnection connection = (HttpURLConnection) a.openConnection();
		 connection.setRequestMethod("POST");
		 connection.setRequestProperty("Content-Type","application/soap+xml; charset=utf-8");
		 String country="UnitedStates";
		 String body = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
		 "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"> " +
		 " <soap12:Body> " +
		 " <GetHolidaysAvailable xmlns=\"http://www.holidaywebservice.com/HolidayService_v2/\"> " +
		 " <countryCode>"+country+"</countryCode>" +
		 " </GetHolidaysAvailable>" +
		 " </soap12:Body>" +
		 "</soap12:Envelope>";
		 connection.setDoOutput(true);
		 DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		 out.writeBytes(body);
		 out.flush();
		 out.close();
		 String resp = connection.getResponseMessage();
		 System.out.println(resp);
		 BufferedReader in = new BufferedReader(new InputStreamReader(
		 connection.getInputStream()));
		 String inp;
		 StringBuffer response = new StringBuffer();
		 while ((inp = in.readLine()) != null) {
		 response.append(inp);
		 }
		 in.close();
		 System.out.println("The Resonse is :" + response.toString());
		 } catch (Exception e) {
		 System.out.println(e);
		 }
		 }
		
}
