from fr.istic.sit.lapommevolante.exception import InstanciationException
from fr.istic.sit.lapommevolante.gw2drone.DroneFacade import DroneFacade
from fr.istic.sit.lapommevolante.gw2server.ServerFacade import ServerFacade


class Main:
    def __init__(self):
        serverFacade = ServerFacade()
        droneFacade = DroneFacade()

        try:
            droneFacade.addObserver(serverFacade)
        except InstanciationException as e:
            print e

        if not serverFacade.connect():
            print "Unable to connect to server"
            return
