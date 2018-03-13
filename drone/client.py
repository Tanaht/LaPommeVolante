#!/usr/bin/env python
# coding: utf-8

import socket
import time
import json
from fr.istic.sit.lapommevolante.config.Config import Config

config = Config()
print('Connexion à %s: %s' % (config.socket_host, config.socket_port))
socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
socket.connect((config.socket_host, config.socket_port))


json_data = {
  'type': 'mission_order',
  'data': {
    'title': "highway to hell",
    'trajectory': [
        {'lat': 48.1148383, 'lon': -1.6388297, 'alt': 10, 'photo': False},
        {'lat': 48.1153379, 'lon': -1.6391757, 'alt': 10, 'photo': False},
        {'lat': 48.1161849, 'lon': -1.6390014, 'alt': 10, 'photo': False},
        {'lat': 48.1164571, 'lon': -1.6373706, 'alt': 10, 'photo': False},
        {'lat': 48.1155689, 'lon': -1.6360724, 'alt': 10, 'photo': False},
        {'lat': 48.1152322, 'lon': -1.6378534, 'alt': 10, 'photo': False}]
  }
}
socket.sendall((json.dumps(json_data)).encode())
time.sleep(1)

#msg = 'RESET'
#socket.send(msg.encode())
#time.sleep(1)
#msg = "48.1148383, -1.6388297, 10"
#socket.send(msg.encode())
#time.sleep(1)
#msg = "48.1153379, -1.6391757, 10"
#socket.send(msg.encode())
#time.sleep(1)
#msg = "48.1161849, -1.6390014, 10"
#socket.send(msg.encode())
#time.sleep(1)
#msg = "48.1164571, -1.6373706, 10"
#socket.send(msg.encode())
#time.sleep(1)
#msg = "48.1155689, -1.6360724, 10"
#socket.send(msg.encode())
#time.sleep(1)
#msg = "48.1152322, -1.6378534, 10"
#socket.send(msg.encode())
#time.sleep(1)
#msg = "START"
#socket.send(msg.encode())
#time.sleep(1)

while True:
    response = socket.recv(4096)
    if response != "":
        trame = json.loads(response.decode())
        mission = trame['type']

        if mission == "mission_finished":
            print("PARCOURS TERMINE !")
            break;
        elif mission == "drone_status":
            print "POSITION DRONE :", format(trame['data']['position'])
            time.sleep(0.5)
        else:
            print "STATUS UNKNOWN :", mission
print("Close")
socket.close()