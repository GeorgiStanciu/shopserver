package Main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

public static void main(String[] args){
		
		ServerSocket server = null;
		Socket client;
		
		try {
			server = new ServerSocket(9982);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			while(true){
				client = server.accept();
				ServerProcessRunner spr = new ServerProcessRunner(server, client);
				spr.start();
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	
	}
}
