import sys
sys.path.append('/home/pi/Adafruit-Raspberry-Pi-Python-Code/Adafruit_CharLCD')
from Adafruit_CharLCD import Adafruit_CharLCD
lcd = Adafruit_CharLCD()

lcd.clear()
lcd.message('Hej hej\nBla bla')
