Compte-rendu de Cédric Delourme

Travaillant avec le groupe Drone, j'ai commencé par de la recherche de documentation concernant la simulation de drone sur linux.
Le site http://ardupilot.org est une source d'information tres interessante.
Après installation de APM Planner, de SITL, MavProxy et de Python 2.7, je pouvais exécuter des essais de programmes python.

J'ai ainsi développé une version "fonctionnelle" mais pas jolie. Avec des délais courts, il était compliqué de développer proprement.
Cette version recoit une mission en JSON du serveur et, toutes les secondes, renvoie les coordonnées GPS et génère un fichier KML pour Google Earth.
Après essais et ajustement avec la partie "serveur", le tout fonctionne correctement.

Cette partie du projet m'a permis d'appréhender la programmation Python (socket + JSON) sur l'IDE Pycharm, la génération de fichier KML pour la visualisation 
de vol via Google Earth. Egalement respecter les spécifications 

