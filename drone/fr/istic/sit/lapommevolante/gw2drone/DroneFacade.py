import logging

from fr.istic.sit.lapommevolante.exception import InstanciationException
from fr.istic.sit.lapommevolante.util.Subject import Subject


class DroneFacade(Subject):
    def __init__(self, config, condition, missionsQueue):  # constructor
        Subject.__init__(self)
        self.condition = condition
        self.missionsQueue = missionsQueue
        self.status = True

    def handleMissions(self):
        """
        Loop while server is running and wait for missions
        :return:
        """
        self.condition.acquire()
        while self.status:
            if self.missionsQueue.empty():
                self.condition.wait()
            self.handleMission(self.missionsQueue.get())
        self.condition.release()

    def handleMission(self, mission):
        """
        Prend en charge une mission reçue par le sevreur et réveille le drone pour effectué les actions nécéssaires
        envoie les informations au serveur en notifiant ServerFacade (self.notify())
        :param mission: la mission reçue à utiliser
        :return: void
        """
        logging.info("Mission to process: ", mission)

    def stopDroneFacade(self):
        self.status = False