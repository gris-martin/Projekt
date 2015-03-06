import socket
from sys import argv

class Client():
    def __init__(self, adress, port):
        self.s = socket.socket()
        self.s.connect((adress, port))

        
script, adress, port = argv
port = int(port)
TC = Client(adress, port)

    
