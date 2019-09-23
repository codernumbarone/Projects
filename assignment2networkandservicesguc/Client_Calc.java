
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Client_Calc {

	public static void main(String[] args) {
		 try {
		 String url = "http://www.dneonline.com/calculator.asmx?op=Add";
		 URL obj = new URL(url);
		 HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		 con.setRequestMethod("POST");
		 con.setRequestProperty("Content-Type","application/soap+xml; charset=utf-8");
		 int a = 5;
		 int b = 10;
		 String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + 
		 		"<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" + 
		 		"  <soap12:Body>\n" + 
		 		"    <Add xmlns=\"http://tempuri.org/\">\n" + 
		 		"      <intA>"+a+"</intA>\n" + 
		 		"      <intB>"+b+"</intB>\n" + 
		 		"    </Add>\n" + 
		 		"  </soap12:Body>\n" + 
		 		"</soap12:Envelope>";
		 con.setDoOutput(true);
		 DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		 wr.writeBytes(xml);
		 wr.flush();
		 wr.close();
		 String responseStatus = con.getResponseMessage();
		 System.out.println(responseStatus);
		 BufferedReader in = new BufferedReader(new InputStreamReader(
		 con.getInputStream()));
		 String inputLine;
		 StringBuffer response = new StringBuffer();
		 while ((inputLine = in.readLine()) != null) {
		 response.append(inputLine);
		 }
		 in.close();
		 System.out.println("The Result is:" + response.toString());
		 } catch (Exception e) {
		 System.out.println(e);
		 }
		 }
}
