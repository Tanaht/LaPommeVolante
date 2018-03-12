#!/usr/bin/env python
# coding: utf-8

import socket
import time

hote = "localhost"
port = 15556

socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
socket.connect((hote, port))
print("Connection on {}".format(port))
msg = 'RESET'
socket.send(msg.encode())
time.sleep(1)
msg = "48.1148383, -1.6388297, 10"
socket.send(msg.encode())
time.sleep(1)
msg = "48.1153379, -1.6391757, 10"
socket.send(msg.encode())
time.sleep(1)
msg = "48.1161849, -1.6390014, 10"
socket.send(msg.encode())
time.sleep(1)
msg = "48.1164571, -1.6373706, 10"
socket.send(msg.encode())
time.sleep(1)
msg = "48.1155689, -1.6360724, 10"
socket.send(msg.encode())
time.sleep(1)
msg = "48.1152322, -1.6378534, 10"
socket.send(msg.encode())
time.sleep(1)
msg = "START"
socket.send(msg.encode())
time.sleep(1)

while True:
    response = socket.recv(255)
    if response != "":
        if response == "STOP":
            print("PARCOURS TERMINE !")
            msg = "EXIT"
            socket.send(msg.encode())
            break;
        else:
            print "POSITION DRONE :", response
            time.sleep(0.5)

print("Close")
socket.close()