import wiringpi2 as GPIO
import time

r = 200;
div = 100;
outPin = 1;

GPIO.wiringPiSetup()
GPIO.pinMode(outPin,2)
GPIO.pwmSetMode(0)
GPIO.pwmSetClock(div)
GPIO.pwmSetRange(r)

for duty in range(r,0,-1):
    GPIO.pwmWrite(1,duty)
    if not duty%20:
        print str(int(float(duty)/float(r)*100)) + "%"
    time.sleep(0.01)

for duty in range(0,r+1):
    GPIO.pwmWrite(1,duty)
    if not duty%20:
        print str(int(float(duty)/float(r)*100)) + "%"
    time.sleep(0.01)
