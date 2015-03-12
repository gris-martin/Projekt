import java.io.File;
import java.io.IOException;

import java.awt.image.BufferedImage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.imageio.ImageIO;

public class Camera{
    Runtime r;
    
    public Camera(){
	r = Runtime.getRuntime();
    }

    public synchronized BufferedImage takePicture(){
	BufferedImage img = null;
	try{
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd-hh-mm-ss");
	    Date date = new Date();
	    String currentDate = dateFormat.format(date);

	    executeCommand("raspistill -o pic" + currentDate +
			   " -h 768 -w 1024 -t 3000");

	    Thread.sleep(5000);
	    img = ImageIO.read(new File("pic"+currentDate));
	} catch(Exception e){
	    e.printStackTrace();
	}
	return img;
	
    }

    private void executeCommand(String s) throws IOException{
	r.exec(s);
    }

    public static void main(String[] args){
	takePicture();
    }
    
}
