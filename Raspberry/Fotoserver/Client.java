import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.net.InetAddress;
import java.net.Socket;


public class Client{

    public Socket socket;
    public byte[] bufferedImage;

    public static void main(String args[]){

	InetAddress ip = null;
	int port = 0;
	try {
	    ip = InetAddress.getByName(args[0]);
	    port = Integer.parseInt(args[1]);
	} catch (Exception e){
	    e.printStackTrace();
	}

	Client c = new Client(ip, port);

	DataInputStream inputReader = new DataInputStream(c.socket.getInputStream());
	int bytesRead = inputReader.read(c.bufferedImage);
	inputReader.close();

	//Metoder for fonster
	HuvudFonster mainWindow = new HuvudFonster("Main Window");

	//Images
	JLabel imageBox1 = newImage("plankan.jpg", 0.7);

	//Buttons
	JButton capButton = new JButton("Ta bild");
	capButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    imageBox1.setIcon(newImageIcon("plunkan.png", 1));
		}
	    });

	//Add images
	mainWindow.add(imageBox1, BorderLayout.CENTER);
	mainWindow.add(capButton, BorderLayout.PAGE_END);
	//Show window
	mainWindow.pack();
	mainWindow.setVisible(true);
	
    }

    public Client(InetAddress adress, int port){
	try {
	    socket = new Socket(adress, port);
	}
	catch (Exception e) {
	    e.printStackTrace();
	}

    }
    
    //Scale an image
    public static Image resizeImage(BufferedImage pic, double scale){
	return pic.getScaledInstance((int)((double)pic.getWidth()*0.5), (int)((double)pic.getHeight()*0.5),Image.SCALE_SMOOTH);
    }

    //Create a new ImageIcon
    public static ImageIcon newImageIcon(String fileName, double scale){
	BufferedImage bigPicture = null;
	Image smallPicture = null;
	try{
	    bigPicture = ImageIO.read(new File(fileName));
	} catch(IOException e){
	    e.printStackTrace();
	    System.exit(1);
	}
	
	smallPicture = resizeImage(bigPicture,scale);
	return new ImageIcon(smallPicture);
    }	

    //Create a new image panel (JLabel)
    public static JLabel newImage(String fileName, double scale){
	return new JLabel(newImageIcon(fileName, scale));

    }

}





