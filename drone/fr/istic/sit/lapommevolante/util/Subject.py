from fr.istic.sit.lapommevolante.exception.InstanciationException import InstanciationException
from fr.istic.sit.lapommevolante.util import Observer


class Subject:
    #   observers: liste d'Observer

    def __init__(self):
        self.observers = []

    def addObserver(self, observer):
        """
        @precondition: observer is an instance of fr.istic.sit.lapommevolante.util.Observer
        :param observer:
        :return: void
        """
        if not isinstance(observer, Observer.Observer):
            raise InstanciationException(observer, Observer)

        self.observers.append(observer)

    def removeObserver(self, observer):
        """
        remove an observer from this subject
        :param observer:
        :return: void
        """
        self.observers.remove(observer)

    def notifyAll(self):
        """
        notify all registered observers about changes in subject
        :return: void
        """
        for observer in self.observers:
            observer.update(self)

    def notify(self, observer):
        """
        @precondition: observer is an instance of fr.istic.sit.lapommevolante.util.Observer
        notify specified observer about changes in subject
        :param observer:
        :return: void
        """
        if not isinstance(observer, Observer.Observer):
            raise InstanciationException(observer, Observer)
        observer.update(self)
