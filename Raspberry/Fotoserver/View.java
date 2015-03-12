import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import javax.imageio.ImageIO;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

class View extends JFrame{

    Client client;
    JLabel imageBox;
    JPanel buttonPanel;
    JButton captureButton;
    JButton exitButton;

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

		    setSize(1024,768);
		    setLayout(new BorderLayout());

		    imageBox = newImagePanel("black.png",1);
		    buttonPanel = new JPanel(new FlowLayout());
		    try{
			initiateButtons();
		    } catch(IOException e){
			e.printStackTrace();
		    }
		    buttonPanel.add(captureButton);
		    buttonPanel.add(exitButton);

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
