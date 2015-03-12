import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.imageio.ImageIO;

import java.io.IOException;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

import java.net.InetAddress;
import java.net.Socket;

public class Client{

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private View view;
    private String ip;
    private static final Integer IMAGE = 1;
    private static final Integer END = 2;

    public static void main(String[] args){
	try{
	    Client c = new Client();
	} catch (IOException e){
	    e.printStackTrace();
	}
    }

    public void connectToServer(String ip){
	try{
	    socket = new Socket(InetAddress.getByName(ip), 2020);
	    out = new ObjectOutputStream(socket.getOutputStream());
	    in = new ObjectInputStream(socket.getInputStream());
	    run();
	} catch (IOException e){
	    e.printStackTrace();
	}

    }
    
    public Client() throws IOException{
	view = new View(this);
	BufferedReader sc = new BufferedReader();

	BufferedImage pic = null;

	    
    }

    private void run(){
	ImageIcon pic = null;

	do{
	    try{
		pic = (ImageIcon)in.readObject();
		view.updateImage(pic);
	    } catch(IOException e){
		e.printStackTrace();
	    } catch(ClassNotFoundException e){
		e.printStackTrace();
	    }
	} while(pic != null);

    }

    public void closeConnection() throws IOException{
	out.writeObject(END);
	// in.close();
	// out.close();
	// socket.close();
    }

    public void requestPicture() throws IOException{
	out.writeObject(IMAGE);
    }
}
	
//     public static void main(String args[]){

// 	InetAddress ip = null;
// 	int port = 0;
// 	try {
// 	    ip = InetAddress.getByName(args[0]);
// 	    port = Integer.parseInt(args[1]);
// 	} catch (Exception e){
// 	    e.printStackTrace();
// 	}

// 	Client c = new Client(ip, port);

// 	BufferedImage img = null;
// 	try{
// 	    DataInputStream in = new DataInputStream(c.socket.getInputStream());
// 	    DataOutputStream out = new DataOutputStream(c.socket.getOutputStream());
// 	    img = ImageIO.read(c.socket.getInputStream());
// 	} catch (IOException e){
// 	    e.printStackTrace();
// 	}



// 	//Metoder for fonster
// 	HuvudFonster mainWindow = new HuvudFonster("Main Window");

// 	//Images
// 	JLabel imageBox1 = newImage("plankan.jpg", 0.7);
// 	JLabel imageBox2 = new JLabel(new ImageIcon(img));

// 	//Buttons
// 	JPanel buttonPanel = new JPanel(new FlowLayout());
// 	JButton capButton = new JButton("Ta bild");
// 	capButton.addActionListener(new ActionListener(){
// 		public void actionPerformed(ActionEvent e){
// 		    imageBox1.setIcon(newImageIcon("plunkan.png", 1));
// 		}
// 	    });
// 	JButton closeButton = new JButton("Avsluta");
// 	closeButton.addActionListener(new ActionListener(){
// 		public void actionPerformed(ActionEvent e){
// 		    System.out.println("Avslutar");
// 		    disconnect = true;
// 		}
// 	    });
// 	buttonPanel.add(capButton);
// 	buttonPanel.add(closeButton);

// 	//Add components
// 	mainWindow.add(imageBox1, BorderLayout.CENTER);
// 	mainWindow.add(imageBox2, BorderLayout.LINE_END);
// 	mainWindow.add(buttonPanel, BorderLayout.PAGE_END);
// 	//Show window
// 	mainWindow.pack();
// 	mainWindow.setVisible(true);
	
// 	// try{
// 	//     c.socket.close();
// 	// } catch(IOException e){
// 	//     e.printStackTrace();
// 	// }
// 	while(!disconnect){
// 	}
// 	System.out.println("Slut!");
	
//     }

//     public Client(InetAddress adress, int port){
// 	try {
// 	    socket = new Socket(adress, port);
// 	}
// 	catch (Exception e) {
// 	    e.printStackTrace();
// 	}

// 	disconnect = false;
	
//     }

    

// }
