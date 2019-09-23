import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;


public class TCPServer {
	public static void main(String argv[]) throws Exception {
		String clientSentence;
		String capitalizedSentence;
		ArrayList<ClientThread> clientThread;
		boolean exists;
		int clientID;
		ServerSocket welcomeSocket = new ServerSocket(6789); 
		Socket connectionSocket = welcomeSocket.accept();

		clientThread=new ArrayList<>();
		while (true) {
			//Socket connectionSocket = welcomeSocket.accept();
			clientID=new Random().nextInt(50)+1;
			exists=true;
			
			do{
				clientID=new Random().nextInt(50)+1;
				boolean flag=true;
				for(int i=0;i<clientThread.size();i++)
				{
					if(clientID==clientThread.get(i).getID()){
						flag=false;
					}
				}
				exists =!flag;
			}while(exists);
			
			clientThread.add(new ClientThread(clientID,connectionSocket));
//			while(exists)
//			{
//				clientID=new Random().nextInt(50)+1;
//				for(int i=0;i<clientThread.size();i++)
//				{
//					if(clientID==clientThread.get(i).getID())
//						break;
//				}
//				if(i==clientThread.size()){
//					
//				}
//			}
			BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(
					connectionSocket.getOutputStream());
			clientSentence = inFromClient.readLine();
			capitalizedSentence = clientSentence.toUpperCase()+" Client " +clientID+ '\n';
			outToClient.writeBytes(capitalizedSentence);
		}
	}
}
