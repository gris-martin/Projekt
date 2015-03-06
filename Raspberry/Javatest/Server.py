import socket, time, os, random

class Server():

    def __init__(self,Adress=('',5000),MaxClient=3):
        self.s = socket.socket()
        self.s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.s.bind(Adress)
        self.s.listen(MaxClient)

    def WaitForConnection(self):
        self.Client, self.Adr = (self.s.accept())
        print "New connection from " + str(self.Adr)

    def close(self):
        self.s.close()

s = Server()
s.WaitForConnection()
s.close()
