import time
import picamera

HOURS = 11
MINUTES = 60*HOURS
PICS_PER_MIN = 2
FRAMES = PICS_PER_MIN*MINUTES

#Function to capture frame
def capture_frame(frame):
    with picamera.PiCamera() as cam:
        time.sleep(2)
        cam.vflip = True
        cam.capture('/home/pi/testy/frame%04d.jpg' % (frame+1799))

for frame in range(FRAMES):
    start = time.time()
    capture_frame(frame)
    time.sleep(60/PICS_PER_MIN)
    print frame
