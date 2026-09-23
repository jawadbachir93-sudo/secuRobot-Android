# SecuRobot – Application Android

Application Android réalisée dans le cadre de mon projet de BTS CIEL. Elle permet de piloter un robot par Wi-Fi, d’afficher une interface vidéo et de consulter des données de télémétrie.

## Ma contribution

Dans ce projet réalisé en équipe, j’ai développé la partie application mobile : l’interface Android, les boutons de déplacement et l’envoi des commandes HTTP au Raspberry Pi. Le pilotage du robot côté serveur a été réalisé par les autres membres de l’équipe.

## Fonctionnalités

- Commandes de déplacement : avancer, reculer, gauche, droite et arrêt.
- Affichage vidéo dans une WebView.
- Affichage des données de télémétrie.
- Écran de connexion de démonstration.

## Technologies

Android Studio, Kotlin, Java, XML, HTTP, Raspberry Pi et Node-RED.

## Utilisation et limites

L’adresse IP du Raspberry Pi est définie dans `MainActivity.kt` et doit être adaptée au réseau utilisé. Sans le robot et son serveur, les commandes et la vidéo ne fonctionnent pas. En cas d’échec de la télémétrie, l’application affiche des valeurs simulées. Le bouton Photo génère également une image simulée. L’écran de connexion vérifie uniquement que les deux champs sont remplis : il ne sécurise pas l’accès au robot.