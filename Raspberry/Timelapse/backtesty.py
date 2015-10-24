import time
import picamera
import os

HOURS = 6
PICS_PER_MIN = 10
MINUTES = 60*HOURS
FRAMES = PICS_PER_MIN*MINUTES

#Function to capture frame
def capture_frame(frame):
    with picamera.PiCamera() as cam:
        # time.sleep(2)
        cam.resolution = (1024,768)
        cam.vflip = True
        cam.capture('/home/pi/usbdrv/frame%04d.jpg' % frame)

print("Capturing %d images." % FRAMES)
start = time.time()
print("Starting time: %.2f" % start)
for frame in range(FRAMES):
    while (time.time() - start) < (frame*(60/PICS_PER_MIN)):
        time.sleep(0.1)
    print("Frame %d; Time: %.2f" % (frame, time.time()-start))    
    capture_frame(frame)
    # time.sleep(60/PICS_PER_MIN-1)

# os.system("gst-launch-1.0 multifilesrc location=/home/pi/testy/frame%04d.jpg index=0 caps='image/jpeg,framerate=24/1' ! jpegdec ! omxh264enc ! avimux ! filesink location=/home/pi/testy/timelapse.avi")
