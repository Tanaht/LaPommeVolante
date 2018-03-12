import socket
import logging

socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
socket.bind(('localhost', 15556))
socket.listen(1)

logging.basicConfig(level=logging.INFO)
logging.info('Wait for connection from gateway')
(conn, address) = socket.accept()

conn.send("""{
  "type": "mission_order",
  "data": {
    "title": "title",
    "trajectory": [
      {
        "lat": "latitude",
        "lon": "longitude",
        "alt": "altitude",
        "photo": "false"
      }
    ]
  }
}""")

logging.info('Gateway connected')

socket.close()