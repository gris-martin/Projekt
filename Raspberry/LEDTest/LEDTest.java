import com.pi4j.wiringpi.Gpio;
    
public class LEDTest{
    int r;
    int div;
    int outPin;
    
    public static void main(String[] args) throws InterruptedException{
	
	LEDTest led = new LEDTest();
	Gpio.pinMode(led.outPin,2);
	Gpio.pwmSetMode(0);
	Gpio.pwmSetClock(led.div);
	Gpio.pwmSetRange(led.r);
	
	for(int i = led.r; i > 0; i--){
	    Gpio.pwmWrite(1,i);
	    if(i%20==0){
		System.out.println((((double)i/(double)led.r)*100) + "%");
	    }
	    Thread.sleep(10);
	}

	for(int i = 0; i < led.r+1; i++){
	    Gpio.pwmWrite(1,i);
	    if(i%20==0){
		System.out.println((((double)i/(double)led.r)*100) + "%");
	    }
	    Thread.sleep(10);
	}



	
    }

    public LEDTest(){
	Gpio.wiringPiSetup();
	r = 200;
	div = 100;
	outPin = 1;
    }
    
}