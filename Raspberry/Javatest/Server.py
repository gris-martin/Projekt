import socket
from sys import argv
import threading

class Server():

    #Constructor
    def __init__(self,port,MaxClient=3):
        Adress = ('',port)
        self.s = socket.socket()
        self.s.bind(Adress)
        self.s.listen(MaxClient)

    #Waits for new connection
    def WaitForConnection(self):
        i = 0;
        while i<3:
            self.Client, self.Adr = (self.s.accept())
            print "New connection from " + str(self.Adr)
            threading.Thread(target=self.HandleClient, args=(self.Client,self.Adr)).start()
            i += 1

    def close(self):
        self.s.close()

    #Handles clients that connect
    def HandleClient(self, c, a):
        while True:
            data = c.recv(1024)
            if not data:
                break
            print "From connected user: " + str(data)
            data = str(data).upper()
            print "Sending: " + str(data)
            c.send(data)
        print str(a) + " disconnected."

script, port = argv
s = Server(int(port))
s.WaitForConnection()
s.close()
