#### Travail effectué.
L'équipe à été séparée en groupe, je fais partie du groupe qui s'occupe du développement de la passerelle.
Pour des souci de simplicité, on à choisit d'utiliser Python pour implémenté la passerelle.

Le projet m'a permis d'apprendre l'orientée objet en python et plus généralement la programmation python.
Au final je n'ai pas acquis une grosse expériences sur l'API dronekit, 
mais j'ai travaillé sur la communication gateway-server et j'ai proposé une solution pour pouvoir configuré la passerelle avec 
un fichier yaml notamment les adresses IPs et ports pour se connecté au serveur et au drone.

J'avais aussi commencé à intégré (code que j'ai supprimé au final) la notion de concurrences juste pour des notions de robustesse, c'est à dire
une file d'attente des missions reçues du serveur (en supposant qu'il puissent en envoyé plusieurs à la suite) et avec la notion de Producteur/Consommateur:
 - Un thread écoute le serveur et lorsqu'il produit une mission (que la mission est reçue via le socket il réveille le consommateur. et attend un message du serveur.
 - Un thread qui consomme les missions et fait volé le drone. une fois que le drone à finis sa mission. le thread vérifie la file d'attente et s'endort si aucune mission n'est dans la file.
Au final cette intégration est tombé à l'eau car elle rentre dans le cadre d'un script orienté objet et on à finalement utilisé un script All-In-One en tant que passerelle pour des raisons de délais.

De la même manière il n'y a pas de validations des données reçue du serveur (si le serveur renvoie un message auquel on ne s'attend pas la passerelle ne fonctionne plus)

#### Technologies.
Chacun des membres de l'équipe à apporté son expérience au projet.
Pour ma part j'ai proposé d'utiliser une méthodologie pour gérer correctement le code source: git flow,
Au final la méthodologie à été globalement accepté et je l'ai trouvée très utile.

#### Retour sur expériences.
Le travail de groupe à été globalement mené correctement. 
On à reussi à garder un semblant d'ordre dans nos missions respectives, 
sur la dernières phase de rush cependant, 
toutes les bonnes pratiques pour gérer le projet (Git Flow) ont été laissé de côté ce qui se comprend par les délais fournies.
