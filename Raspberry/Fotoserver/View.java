import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import javax.imageio.ImageIO;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import java.net.InetAddress;

class View extends JFrame{

    Client client;
    JLabel imageBox;
    JPanel buttonPanel;
    JButton captureButton;
    JButton exitButton;
    JPanel connectionPanel;
    JLabel connectionInfo;
    JTextField ipField;
    JButton connectButton;

    // public static void main(String[] args){
    // 	View v = new View();
    // }
    
    public View(Client c){
	super("Martins program");
	initiateWindow();
	client = c;
    }

    public void updateImage(ImageIcon pic){
	SwingUtilities.invokeLater(new Runnable(){
		public void run(){
		    if(pic == null){
			System.out.println("??");
		    } else {
			imageBox.setIcon(pic);
		    }
		}
	    });
	
    }

    public void initiateWindow(){
	SwingUtilities.invokeLater(new Runnable(){
		public void run(){

		    setSize(2000,2000);
		    setLayout(new BorderLayout());

		    imageBox = new JLabel(new ImageIcon());//newImagePanel("black.png",1);
		    imageBox.setPreferredSize(new Dimension(800,600));
		    buttonPanel = new JPanel(new FlowLayout());
		    try{
			initiateButtons();
		    } catch(IOException e){
			e.printStackTrace();
		    }
		    buttonPanel.add(captureButton);
		    buttonPanel.add(exitButton);

		    connectionPanel = new JPanel(new FlowLayout());
		    connectionInfo = new JLabel("Ange IP:");
		    ipField = new JTextField(15);
		    connectButton = new JButton("Connect");
		    connectButton.addActionListener(new ActionListener(){
			    public void actionPerformed(ActionEvent e){
				String ip = ipField.getText();
				try{
				    client.connectToServer(ip);
				} catch(Exception exc){
				    exc.printStackTrace();
				}
			    }
			});
		    
		    connectionPanel.add(connectionInfo);
		    connectionPanel.add(ipField);
		    connectionPanel.add(connectButton);
		    add(connectionPanel,BorderLayout.PAGE_START);
		    add(imageBox, BorderLayout.CENTER);
		    add(buttonPanel, BorderLayout.PAGE_END);
	

		    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		    pack();
		    setVisible(true);

		}
	    });
    }



    //Initiate buttons
    private void initiateButtons() throws IOException{
	//Initiate captureButton
	captureButton = new JButton("Ta bild");
	captureButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
		    imageBox.setText("...");
		    try{
			client.requestPicture();
		    } catch(IOException e){
			e.printStackTrace();
		    }
		}
	    });

	//Initiate exitButton
	exitButton = new JButton("Avsluta");
	exitButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
		    try{
			client.closeConnection();
		    } catch(IOException e){
			e.printStackTrace();
		    }
		    setVisible(false);
		    dispose();
		}
	    });

	
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
    public static JLabel newImagePanel(String fileName, double scale){
	return new JLabel(newImageIcon(fileName, scale));

    }


    //Scale an image
    public static Image resizeImage(BufferedImage pic, double scale){
	return pic.getScaledInstance((int)((double)pic.getWidth()*0.5), (int)((double)pic.getHeight()*0.5),Image.SCALE_SMOOTH);
    }



}
