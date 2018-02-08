from fr.istic.sit.lapommevolante.config.Config import Config
from fr.istic.sit.lapommevolante.exception.InstanciationException import InstanciationException
from fr.istic.sit.lapommevolante.gw2drone.DroneFacade import DroneFacade
from fr.istic.sit.lapommevolante.gw2server.ServerFacade import ServerFacade


class Main:
    def __init__(self):
        config = Config()
        serverFacade = ServerFacade()
        droneFacade = DroneFacade()

        try:
            droneFacade.addObserver(serverFacade)
        except InstanciationException as e:
            print e

        if not serverFacade.connect(config.socket_host, config.socket_port):
            print "Unable to connect to server"
            return



main = Main()

