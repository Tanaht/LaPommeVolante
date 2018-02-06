from fr.istic.sit.lapommevolante.exception import InstanciationException
from fr.istic.sit.lapommevolante.gw2drone.DroneFacade import DroneFacade
from fr.istic.sit.lapommevolante.gw2server.ServerFacade import ServerFacade


class Main:
    def __init__(self):
        serverFacade = ServerFacade()
        droneFacade = DroneFacade()

        try:
            serverFacade.addObserver(droneFacade)
            droneFacade.addObserver(serverFacade)
        except InstanciationException as e:
            print e

        serverFacade.connect()
