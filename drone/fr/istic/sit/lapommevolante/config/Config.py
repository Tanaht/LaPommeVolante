import logging
from pprint import pprint

import yaml

class Config:
    def __init__(self):
        logging.basicConfig(level=logging.INFO)
        with open("config.yml", 'r') as stream:
            try:
                self.document = yaml.load(stream)
                self.socket_host = self.document["config"]["connection"]["socket"]["host"]
                self.socket_port = self.document["config"]["connection"]["socket"]["port"]
                self.missions_queue = self.document["config"]["missions"]["max_in_queue"]
            except yaml.YAMLError as exc:
                print(exc)
        logging.info('Environment configured with: [socket_host: %s], [socket_port: %s], [missions_queue: %s]', self.socket_host, self.socket_port, self.missions_queue)
