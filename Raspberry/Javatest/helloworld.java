import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.trigger.*;
import com.pi4j.io.gpio.event.*;
import com.pi4j.wiringpi.SoftPwm;
import com.pi4j.wiringpi.Gpio;

public class helloworld{
    //Create GPIO controller instance
    final GpioController gpio = GpioFactory.getInstance();

    //Set pin 8 as output
    GpioPinDigitalOutput pin8 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "Hej", PinState.LOW);

    public helloworld(){
	System.out.println("New object created");
    }
    
    public static void main(String args[]) {
	System.out.println("Hej");
	helloworld hw = new helloworld();

	SoftPwm.softPwmCreate(8,0,100);
	try{
	    while(true){
		for (int i = 50; i <100; i++){
		    SoftPwm.softPwmWrite(8,i);
		    Thread.sleep(100);
		}
		
		for (int i = 100; i > 50; i--){
		    SoftPwm.softPwmWrite(8,i);
		    Thread.sleep(100);
		}
	    }
	} catch(InterruptedException e){
	    System.out.println("Nat gick fel...");
	    System.exit(1);
	}
    }
}