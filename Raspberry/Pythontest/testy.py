import wiringpi2 as GPIO
import time

GPIO.wiringPiSetup()
GPIO.pinMode(1,2)
GPIO.pwmSetMode(0)
GPIO.pwmSetClock(100)
GPIO.pwmSetRange(200)

for duty in range(200,0,-1):
    GPIO.pwmWrite(1,duty)
    time.sleep(0.01)

for duty in range(0,201):
    GPIO.pwmWrite(1,duty)
    time.sleep(0.01)
