import json
import socket
import threading
from Queue import Queue
from argparse import Namespace
from pprint import pprint

import logging

from fr.istic.sit.lapommevolante.util.Observer import Observer


class ServerFacade(Observer):
    def __init__(self):
        Observer.__init__(self)
        # TODO: choose type of socket with integration guy
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.missionsQueue = Queue(1)

    def __del__(self):
        if not self.sock is None:
            self.sock.close()

    def connect(self, host, port):
        """
        connect to remote server using a socket
        :return: boolean wether or not the GW is connected to the Server
        """

        self.sock.connect((host, port))

        threading.Thread(target=self.receiveMission).start()
        return True

    def receiveMission(self):
        """
        Here listen server and start mission according to received JSON
        A concept of Queue could be used to put in queue missions when there is currently a mission being processed
        :return:
        """
        jsonMission = json.loads(self.sock.recv(4096), object_hook=lambda d: Namespace(**d))
        logging.info('Mission added to queue: [type: %s title: %s with %s points]', jsonMission.type, jsonMission.data.title, len(jsonMission.data.trajectory))
        self.missionsQueue.put(jsonMission)
        threading.notify()