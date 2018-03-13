import logging
import threading
from Queue import Queue

from fr.istic.sit.lapommevolante.config.Config import Config
from fr.istic.sit.lapommevolante.exception.InstanciationException import InstanciationException
from fr.istic.sit.lapommevolante.gw2drone.DroneFacade import DroneFacade
from fr.istic.sit.lapommevolante.gw2server.ServerFacade import ServerFacade


class Main:
    def __init__(self):
        config = Config()
        # Create a condition object (i.e like monitors in Java) to synchronize thread that receive from server and thread that send to server.
        condition = threading.Condition()

        # create a FIFO Queue that purpose is to store missions received from server
        missionsQueue = Queue(config.missions_queue)

        serverFacade = ServerFacade(config, condition, missionsQueue)
        droneFacade = DroneFacade(config, condition, missionsQueue)

        try:
            droneFacade.addObserver(serverFacade)
        except InstanciationException as e:
            print e

        if not serverFacade.connect(config.socket_host, config.socket_port):
            logging.error('Unable to connect to server')
            return

        droneFacade.handleMissions()



main = Main()

