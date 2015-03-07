import RPi.GPIO as GPIO

class LedTest:
    
    def __init__(self, pn):
        self.pinNumber = pn;
        GPIO.setmode(GPIO.BCM)
