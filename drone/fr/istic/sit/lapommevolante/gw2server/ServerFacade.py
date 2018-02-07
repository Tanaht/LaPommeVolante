from fr.istic.sit.lapommevolante.util import Observer


class ServerFacade(Observer):
    def __init__(self): #constructor
        # Stub
        pass

    def connect(self):
        """
        connect to remote server using a socket
        :return: boolean wether or not the GW is connected to the Server
        TODO not implemented yet always return true
        """
        return True

