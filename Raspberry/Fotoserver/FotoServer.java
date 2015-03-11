import java.io.IOException;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;

public class FotoServer
{
    public ServerSocket serverSocket;
    public ArrayList<FotoServant> servantList;
    
    public static void main(String args[])
    {
	FotoServer s = new FotoServer(2020);
	s.servantList = new ArrayList<FotoServant>();
	while(true){
	    try{
		
		FotoServant sera = new FotoServant(s.serverSocket.accept());
		sera.start();
		System.out.println("Socket accepted from " + sera);
		s.servantList.add(sera);
		System.out.println("Number of servants: " + s.servantList.size());

		for(int i = 0; i < s.servantList.size(); i++){
		    if(s.servantList.get(i).socket.isClosed()){
			System.out.println(s.servantList.get(i) + " removed.");
			s.servantList.remove(i);
			i--;
		    }
		}

		for(int i = 0; i < s.servantList.size(); i++){
		    System.out.println(i + ": " + s.servantList.get(i));
		}

		System.out.println("");


	    } catch (IOException e){
		e.printStackTrace();
	    }
	}

    }


    //Konstruktor
    public FotoServer(int port){
	try{
	    serverSocket = new ServerSocket(port);
	} catch (IOException e){
	    e.printStackTrace();
	    System.exit(1);
	}
    }



}

