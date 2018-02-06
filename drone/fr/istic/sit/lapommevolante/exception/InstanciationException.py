class InstanciationException(Exception):
    def __init__(self, instance, classinfo):
        self.message = instance.__class__ + " is expected to be an instance of " + classinfo