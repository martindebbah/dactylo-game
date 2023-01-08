# Compléments en Programmation Orientée Objet Projet : Dactylo-game

## Membres

- Ahmed Yahia Yacine (22001256)
- Debbah Martin (71804727)


## Présentation du projet

Nous vous présentons Dactylo-game, un super jeu de dactylographie.

Testez vos performances de frappe au clavier grâce au mode normal.
Une citation au hasard parmi les dix citations disponible est sélectionnée.
Essayez d'écrire le plus rapidement possible, le tout sans faire de faute de
frappe. Analysez vos performances et tentez de vous améliorer grâce aux
différents calculs de statistiques. Vous avez accès au nombre de mots par
minute, à votre précision et votre régularité de frappe.

Découvrez aussi notre mode de jeu qui allie dactylographie et Tetris.
Essayez de tenir le plus longtemps possible sans perdre la totalité de vos
points de vie. Vous débutez la partie avec cent points de vie et chaque faute
vous fait perdre un point. Plus vous écrivez de mots sans faute, plus la
vitesse d'apparition des mots augmente. Certains mots, signalés en bleu, vous
permettent de regagner des points de vie lorsque vous l'écrivez sans faute.
Essayez donc de les avoir du premier coup.

Avant de lancer un des deux modes ci-dessus, vous pouvez passer par la page
de paramètres. Depuis celle-ci, vous pouvez définir quelques paramètres de jeu.
Vous pourrez choisir le texte à utiliser et la fréquence des mots bonus.
Il vous suffit ensuite de cliquer sur un des boutons qui permettent de jouer
avec les paramètres définis.

Mesurez vous à vos amis avec notre mode multijoueur en réseau. Le mode
multijoueur ressemble beaucoup au mode jeu en solo, avec comme seule différence
que les mots n'aparaissent plus à intervalles réguliers. En effet, dans ce mode
de jeu, en plus des mots en bleus, vous pourrez trouver des mots signalés en
rouge. Ces mots, si vous les écrivez correctement du premiers coup, seront
alors envoyés à tous vos opposants.

Pour pouvoir jouer à plusieurs, il suffit qu'un des joueurs chosisse d'héberger
une partie. Les autres joueurs cliquent alors sur le bouton pour rejoindre une
partie. Il faut alors rentrer l'adresse IP qui s'affiche sur l'écran du joeur
hébergeant la partie et de cliquer sur se connecter. Les joeurs doivent alors
attendrent que l'hébergeur lance la partie pour tout le monde.


## Configuration demandée

- Java 19
- Maven 3.8.6


## Exécuter le programme

Se placer dans la racine de l'arborescence.
Donner les droits en exécution au script `run.sh`. (-> `chmod u+x run.sh`)
Lancer le script `run.sh` pour exécuter le programme. (-> `./run.sh`)


## Exécuter les tests

Lancer la commande `mvn test`
