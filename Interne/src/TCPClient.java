import java.io.*;
import java.net.*;
public class TCPClient {
public static void main(String argv[]) throws Exception{
 String sentence;
  String modifiedSentence;
  Socket clientSocket = new Socket("localhost",6789);
  for(int i=0;i<50;i++)
  {
 BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
 // Socket clientSocket = new Socket("localhost",6789);
  
  DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
   BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
 sentence = inFromUser.readLine();
  outToServer.writeBytes(sentence + '\n');
 modifiedSentence = inFromServer.readLine();

 System.out.println("From SERVER: " + modifiedSentence);
  }
 clientSocket.close();
}
}
