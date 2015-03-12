import java.net.Socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.ImageIcon;

public class Servant extends Thread{
    Server server;
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    Camera camera;
    
    private static Integer IMAGE = 1;
    private static Integer END = 2;

    public Servant(Server ser, Socket soc, Camera cam){
	camera = cam;
	server = ser;
	socket = soc;
	
	System.out.println("Servant for " + 
			   socket.getInetAddress().getHostName() +
			   ":" +  socket.getPort());

	try{
	    in = new ObjectInputStream(socket.getInputStream());
	    System.out.println("Servant: Input stream created.");
	    out = new ObjectOutputStream(socket.getOutputStream());
	    System.out.println("Servant: Output stream created.");
	} catch (IOException e){
	    e.printStackTrace();
	}
    }

    
    
    
    public void run(){
	System.out.println("Servant run method started");
	BufferedImage pic = null;
	try{
	    while(performAction()){
	    }
	} catch(Exception e){
		e.printStackTrace();
	    }
	    
    } 

    private boolean performAction() throws IOException, ClassNotFoundException{
    	Integer typeOfObject = (Integer)in.readObject();
    	if(typeOfObject.equals(IMAGE)){
	    BufferedImage bimg = camera.takePicture();
	    ImageIcon imgIcon = new ImageIcon(bimg);
	    out.writeObject(imgIcon);
	    return true;
	}
	else if(typeOfObject.equals(END)){
	    destroyServant();
	    return false;
	}
	return false;
	
    }

    public void destroyServant(){
	server.remove(this);
    }
    
}
