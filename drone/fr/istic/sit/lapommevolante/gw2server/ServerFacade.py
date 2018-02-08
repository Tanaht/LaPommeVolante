import socket

from fr.istic.sit.lapommevolante.util.Observer import Observer


class ServerFacade(Observer):
    def __init__(self):
        Observer.__init__(self)
        # TODO: choose type of socket with integration guy
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    def __del__(self):
        if not self.sock is None:
            self.sock.close()

    def connect(self, host, port):
        """
        connect to remote server using a socket
        :return: boolean wether or not the GW is connected to the Server
        """

        self.sock.connect((host, port))
        return True

