import javax.swing.*;
import java.awt.BorderLayout;

public class HuvudFonster extends JFrame{

    //Constructor
    public HuvudFonster(String title){
	super(title);
	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	this.setSize(1900,1000);
	this.setLayout(new BorderLayout());
    }


}
