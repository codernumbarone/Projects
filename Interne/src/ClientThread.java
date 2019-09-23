import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread {
	int clientID;
	Socket clientSocket;
	BufferedReader informclient;
	DataOutputStream outtoclient;
	 public ClientThread(int clientID,Socket clientSocket){
		 this.clientID=clientID;
		 this.clientSocket=clientSocket;
		 try {
			BufferedReader inFromClient = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
    public int getID(){
    	return clientID;
    }
	

}
