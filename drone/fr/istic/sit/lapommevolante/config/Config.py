import yaml

class Config:
    def __init__(self):
        with open("config.yml", 'r') as stream:
            try:
                self.config = yaml.load(stream)
            except yaml.YAMLError as exc:
                print(exc)
