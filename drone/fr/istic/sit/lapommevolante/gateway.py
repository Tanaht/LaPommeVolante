#!/usr/bin/env python
# coding: utf-8
from __future__ import print_function
import socket
from fr.istic.sit.lapommevolante.config.Config import Config

from dronekit import connect, VehicleMode, LocationGlobalRelative, LocationGlobal, Command
import time
import math
import json
from pymavlink import mavutil

config = Config()
client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

print('Connexion à %s: %s' % (config.socket_host, config.socket_port))
client.connect((config.socket_host, config.socket_port))

while True:

    response = client.recv(4096)

    if response != "":
        trame = json.loads(response.decode())
        mission = trame['type']
        title = trame['data']['title']

        if mission == "mission_order":
            # decode des points
            liste = []
            elt = trame['data']['trajectory'];
            for point in elt:
               liste.append(LocationGlobal(point['lat'], point['lon'], point['alt']))


            # START du drone
            connection_string = "udpin:{}:{}" .format(config.drone_host , config.drone_port)
            sitl = None

            # Start SITL if no connection string specified
            if not connection_string:
                import dronekit_sitl

                sitl = dronekit_sitl.start_default()
                connection_string = sitl.connection_string()

            # Connect to the Vehicle
            print('Connecting to vehicle on: %s' % connection_string)
            vehicle = connect(connection_string, wait_ready=True)


            def get_location_metres(original_location, dNorth, dEast):
                """
                Returns a LocationGlobal object containing the latitude/longitude `dNorth` and `dEast` metres from the
                specified `original_location`. The returned Location has the same `alt` value
                as `original_location`.

                The function is useful when you want to move the vehicle around specifying locations relative to
                the current vehicle position.
                The algorithm is relatively accurate over small distances (10m within 1km) except close to the poles.
                For more information see:
                http://gis.stackexchange.com/questions/2951/algorithm-for-offsetting-a-latitude-longitude-by-some-amount-of-meters
                """
                earth_radius = 6378137.0  # Radius of "spherical" earth
                # Coordinate offsets in radians
                dLat = dNorth / earth_radius
                dLon = dEast / (earth_radius * math.cos(math.pi * original_location.lat / 180))

                # New position in decimal degrees
                newlat = original_location.lat + (dLat * 180 / math.pi)
                newlon = original_location.lon + (dLon * 180 / math.pi)
                return LocationGlobal(newlat, newlon, original_location.alt)


            def get_distance_metres(aLocation1, aLocation2):
                """
                Returns the ground distance in metres between two LocationGlobal objects.

                This method is an approximation, and will not be accurate over large distances and close to the
                earth's poles. It comes from the ArduPilot test code:
                https://github.com/diydrones/ardupilot/blob/master/Tools/autotest/common.py
                """
                dlat = aLocation2.lat - aLocation1.lat
                dlong = aLocation2.lon - aLocation1.lon
                return math.sqrt((dlat * dlat) + (dlong * dlong)) * 1.113195e5


            def distance_to_current_waypoint():
                """
                Gets distance in metres to the current waypoint.
                It returns None for the first waypoint (Home location).
                """
                nextwaypoint = vehicle.commands.next
                if nextwaypoint == 0:
                    return None
                missionitem = vehicle.commands[nextwaypoint - 1]  # commands are zero indexed
                lat = missionitem.x
                lon = missionitem.y
                alt = missionitem.z
                targetWaypointLocation = LocationGlobalRelative(lat, lon, alt)
                distancetopoint = get_distance_metres(vehicle.location.global_frame, targetWaypointLocation)
                return distancetopoint


            def download_mission():
                """
                Download the current mission from the vehicle.
                """
                cmds = vehicle.commands
                cmds.download()
                cmds.wait_ready()  # wait until download is complete.


            def adds_square_mission(aLocation, aSize):
                """
                Adds a takeoff command and four waypoint commands to the current mission.
                The waypoints are positioned to form a square of side length 2*aSize around the specified LocationGlobal (aLocation).

                The function assumes vehicle.commands matches the vehicle mission state
                (you must have called download at least once in the session and after clearing the mission)
                """

                cmds = vehicle.commands

                print(" Clear any existing commands")
                cmds.clear()

                print(" Define/add new commands.")
                # Add new commands. The meaning/order of the parameters is documented in the Command class.

                # Add MAV_CMD_NAV_TAKEOFF command. This is ignored if the vehicle is already in the air.
                cmds.add(
                    Command(0, 0, 0, mavutil.mavlink.MAV_FRAME_GLOBAL_RELATIVE_ALT, mavutil.mavlink.MAV_CMD_NAV_TAKEOFF, 0, 0, 0, 0,
                            0, 0, 0, 0, 10))

                # Define the four MAV_CMD_NAV_WAYPOINT locations and add the commands
                point1 = get_location_metres(aLocation, aSize, -aSize)
                point2 = get_location_metres(aLocation, aSize, aSize)
                point3 = get_location_metres(aLocation, -aSize, aSize)
                point4 = get_location_metres(aLocation, -aSize, -aSize)
                cmds.add(
                    Command(0, 0, 0, mavutil.mavlink.MAV_FRAME_GLOBAL_RELATIVE_ALT, mavutil.mavlink.MAV_CMD_NAV_WAYPOINT, 0, 0, 0,
                            0, 0, 0, point1.lat, point1.lon, 11))
                cmds.add(
                    Command(0, 0, 0, mavutil.mavlink.MAV_FRAME_GLOBAL_RELATIVE_ALT, mavutil.mavlink.MAV_CMD_NAV_WAYPOINT, 0, 0, 0,
                            0, 0, 0, point2.lat, point2.lon, 12))
                cmds.add(
                    Command(0, 0, 0, mavutil.mavlink.MAV_FRAME_GLOBAL_RELATIVE_ALT, mavutil.mavlink.MAV_CMD_NAV_WAYPOINT, 0, 0, 0,
                            0, 0, 0, point3.lat, point3.lon, 13))
                cmds.add(
                    Command(0, 0, 0, mavutil.mavlink.MAV_FRAME_GLOBAL_RELATIVE_ALT, mavutil.mavlink.MAV_CMD_NAV_WAYPOINT, 0, 0, 0,
                            0, 0, 0, point4.lat, point4.lon, 14))
                # add dummy waypoint "5" at point 4 (lets us know when have reached destination)
                cmds.add(
                    Command(0, 0, 0, mavutil.mavlink.MAV_FRAME_GLOBAL_RELATIVE_ALT, mavutil.mavlink.MAV_CMD_NAV_WAYPOINT, 0, 0, 0,
                            0, 0, 0, point4.lat, point4.lon, 14))

                print(" Upload new commands to vehicle")
                cmds.upload()


            def custom(maListe):
                cmds = vehicle.commands

                print(" Clear any existing commands")
                cmds.clear()

                print(" Define/add new commands.")
                # Add new commands. The meaning/order of the parameters is documented in the Command class.

                # Add MAV_CMD_NAV_TAKEOFF command. This is ignored if the vehicle is already in the air.
                cmds.add(
                    Command(0, 0, 0, mavutil.mavlink.MAV_FRAME_GLOBAL_RELATIVE_ALT, mavutil.mavlink.MAV_CMD_NAV_TAKEOFF, 0, 0, 0, 0,
                            0, 0, 0, 0, 10))

                for point in maListe:
                    cmds.add(
                        Command(0, 0, 0, mavutil.mavlink.MAV_FRAME_GLOBAL_RELATIVE_ALT,
                                mavutil.mavlink.MAV_CMD_NAV_WAYPOINT, 0, 0, 0,
                                0, 0, 0, point.lat, point.lon, point.alt))

                cmds.add(
                    Command(0, 0, 0, mavutil.mavlink.MAV_FRAME_GLOBAL_RELATIVE_ALT, mavutil.mavlink.MAV_CMD_NAV_WAYPOINT, 0, 0, 0,
                            0, 0, 0, point.lat, point.lon, point.alt))
                print(" Upload new commands to vehicle")
                cmds.upload()


            def arm_and_takeoff(aTargetAltitude):
                """
                Arms vehicle and fly to aTargetAltitude.
                """

                print("Basic pre-arm checks")
                # Don't let the user try to arm until autopilot is ready
                while not vehicle.is_armable:
                    print(" Waiting for vehicle to initialise...")
                    time.sleep(1)

                print("Arming motors")
                # Copter should arm in GUIDED mode
                vehicle.mode = VehicleMode("GUIDED")
                vehicle.armed = True

                while not vehicle.armed:
                    print(" Waiting for arming...")
                    time.sleep(1)

                print("Taking off!")
                vehicle.simple_takeoff(aTargetAltitude)  # Take off to target altitude

                # Wait until the vehicle reaches a safe height before processing the goto (otherwise the command
                #  after Vehicle.simple_takeoff will execute immediately).
                while True:
                    print(" Altitude: ", vehicle.location.global_relative_frame.alt)
                    if vehicle.location.global_relative_frame.alt >= aTargetAltitude * 0.95:  # Trigger just below target alt.
                        print("Reached target altitude")
                        break
                    time.sleep(1)


            def updateKmlFile( lon, alt, lat):
                import time
                from math import cos, sin, pi, sqrt
                import random

                kml_template = '''<kml xmlns="http://www.opengis.net/kml/2.2"
                xmlns:gx="http://www.google.com/kml/ext/2.2">
                  <Document>
                    <name>Test camera KML</name>
                    <open>1</open>
                    <Camera>
                      <longitude>{longitude}</longitude>
                      <latitude>{latitude}</latitude>
                      <altitude>{altitude}</altitude>
                      <heading>{heading}</heading>
                      <tilt>{tilt}</tilt>
                      <altitudeMode>absolute</altitudeMode>
                    </Camera>
                  </Document>
                </kml>'''

                coord = {'longitude': lon,
                         'latitude': lat,
                         'altitude': alt,
                         'heading': 160.0 + 80,
                         'tilt': 70.0}

                with open("camera_tmp.kml", "w") as kml_file:
                    kml_file.write(kml_template.format(**coord))


            print('Create a new mission (for current location)')
            # adds_square_mission(vehicle.location.global_frame, 50)
            custom(liste)

            print("vehicle.location.global_frame : ", vehicle.location.global_frame)
            # From Copter 3.3 you will be able to take off using a mission item. Plane must take off using a mission item (currently).
            arm_and_takeoff(10)

            print("Starting mission")
            # Reset mission set to first (0) waypoint
            vehicle.commands.next = 0

            # Set mode to AUTO to start mission
            vehicle.mode = VehicleMode("AUTO")

            # Monitor mission.
            # Demonstrates getting and setting the command number
            # Uses distance_to_current_waypoint(), a convenience function for finding the
            #   distance to the next waypoint.

            while True:
                nextwaypoint = vehicle.commands.next
                print('Distance to waypoint (%s): %s' % (nextwaypoint, distance_to_current_waypoint()))
                lat = vehicle.location.global_frame.lat
                lon = vehicle.location.global_frame.lon
                alt = vehicle.location.global_frame.alt
#
                updateKmlFile(lon, alt, lat)

                json_data = json.dumps({
                    'type': 'drone_status',
                    'data': {
                        'position': dict(lat=lat, lon=lon, alt=alt),
                        'battery': 12
                    }
                })
                client.send(json_data.encode()) # send data via socket

                if nextwaypoint == vehicle.commands.count:
                    print("Exit 'standard' mission when start heading to final waypoint : ",vehicle.commands.count)
                    break;
                time.sleep(1)

            print('Return to launch')
            vehicle.mode = VehicleMode("RTL")

            # Close vehicle object before exiting script
            print("Close vehicle object")
            vehicle.close()

            json_data = json.dumps({
                'type': 'mission_finished',
                'data': {'mission': 10, 'status': True, 'error': ""}
            })
            client.send(json_data.encode())

            # Shut down simulator if it was started.
            if sitl is not None:
                sitl.stop()

            break;
client.close()
#socket.close()