import threading
import time

class AsyncWrite(threading.Thread):
    def __init__(self, text, out):
        threading.Thread.__init__(self)
        self.text = text
        self.out = out

    def run(self):
        f = open(self.out, "a")
        f.write(self.text + '\n')
        f.close()
        time.sleep(2)
        print "Finished background file write to " + self.out

def Main():
        message = raw_input("Enter string to store: ")
        background = AsyncWrite(message, "out.txt")
        background.start()
        print "Main fininshed"
        print 100+100

        #Waits until thread background is finished
        background.join()
        print "Thread finished"

if __name__ == '__main__':
    Main()


            
