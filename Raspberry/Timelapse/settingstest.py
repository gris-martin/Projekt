import time
import picamera
from fractions import Fraction

with picamera.PiCamera() as camera:
    camera.resolution = (1024, 768)
    camera.framerate = 30
    camera.vflip = True
    camera.hflip = True
    # Wait for the automatic gain control to settle
    time.sleep(2)
    # Now fix the values
    # camera.shutter_speed = 6000
    # time.sleep(1)
    camera.exposure_mode = 'auto'
    # g = camera.awb_gains
    # camera.awb_mode = 'off'
    # camera.awb_gains = g
    camera.awb_mode = 'sunlight'
    # camera.awb_gains = (Fraction(200, 128), Fraction(261, 256))

    print camera.shutter_speed
    print camera.awb_gains
    print camera.iso
    camera.capture_sequence(['/home/pi/testy/image%02d.jpg' % i for i in range(10)])




# with picamera.PiCamera() as cam:
#     # time.sleep(2)
#     cam.resolution = (1024,768)
#     cam.vflip = True
#     cam.hflip = True
#     cam.framerate = 30
#     time.sleep(2)
#     cam.shutter_speed = cam.exposure_speed
#     cam.exposure_mode = 'off'
#     g = cam.awb_gains
#     cam.awb_mode = 'off'
#     cam.awb_gains = g
#     print("Exposure speed: %f; AWB Gain: %f" % (cam.shutter_speed, g)
#     # cam.capture('/home/pi/testy/image.jpg')
