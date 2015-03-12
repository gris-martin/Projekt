import java.net.Socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.awt.image.BufferedImage;

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

	
	while(true){
	    try{
		performAction();
	    } catch(Exception e){
		e.printStackTrace();
	    }
	    
	} 
	
    }

    private void performAction() throws IOException, ClassNotFoundException{
    	Integer typeOfObject = (Integer)in.readObject();
    	if(typeOfObject.equals(IMAGE)){
	    BufferedImage bimg = camera.takePicture();
	    byte[] byteImage =
		ByteArrayConversion.toByteArray(bimg);
	    out.writeObject(byteImage);
	}
	else if(typeOfObject.equals(END)){
	    destroyServant();
	}
	
    }

    public void destroyServant(){
	try{
	    server.remove(this);
	    in.close();
	    out.close();
	    socket.close();
	} catch(IOException e){
	    e.printStackTrace();
	}
    }
    
}
