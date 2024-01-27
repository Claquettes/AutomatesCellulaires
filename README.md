# Projet d'Automates Cellulaires

Ce projet est une implémentation en Java d'automates cellulaires. Il comprend différents modèles tels que le Feu, Conway et 1D.

## Structure du Projet

Le projet est organisé en plusieurs classes Java :

- `Automate.java` : Cette classe représente l'automate cellulaire. Elle comprend des méthodes pour mettre à jour l'état de l'automate.
- `Grille.java` : Cette classe représente la grille de cellules dans l'automate.
- `Cellule.java` : Cette classe représente une cellule de l'automate.
- `Coordonnee.java` : Cette classe représente les coordonnées d'une cellule dans l'automate.
- `EtatCellule.java` : Cette classe représente l'état d'une cellule dans l'automate.
- `LocalRule.java` : Cette classe représente les règles locales de l'automate.
- `GUI.java` : Cette classe représente l'interface graphique de l'automate.
- `UserInterface.java` : Cette classe gère les entrées et sorties de l'utilisateur en verison console.
- `Main.java` : C'est la classe principale qui exécute l'automate.

## Comment Exécuter

### Prérequis

#### Sous Linux

- Ouvrez un terminal et exécutez la commande suivante pour installer Java :

```bash
sudo apt install default-jre
```

- Vérifiez que Java est installé en exécutant la commande suivante :

```bash
java --version
```

- Si Java est installé, vous devriez voir quelque chose comme ceci :

```bash
openjdk 11.0.11 2021-04-20
OpenJDK Runtime Environment (build 11.0.11+9-Ubuntu-0ubuntu2.20.04)
OpenJDK 64-Bit Server VM (build 11.0.11+9-Ubuntu-0ubuntu2.20.04, mixed mode, sharing)
```

#### Sous Windows et MacOS

- Téléchargez et installez Java 17 ou supérieur à partir du lien suivant : https://www.oracle.com/java/technologies/downloads/#java17

- Vérifiez que Java est installé en exécutant la commande suivante :

```bash
java --version
```

- Si Java est installé, vous devriez voir quelque chose comme ceci :

```bash
openjdk 17.0.1 2021-10-19
OpenJDK Runtime Environment (build 17.0.1+12-39)
OpenJDK 64-Bit Server VM (build 17.0.1+12-39, mixed mode, sharing)
```

### Compilation

Pour exécuter le projet, compilez et exécutez la classe `Main.java`. Utilisez les commandes suivantes :

```bash
javac AutomatesCellulaires/td/Main.java
java  AutomatesCellulaires/td/Main.java
```

Veillez à bien exécuter ces commandes en etant dans le dossier parent au dossier `AutomatesCellulaires`.
Si vous utilisez un IDE, vous pouvez simplement exécuter la classe `Main.java`.

Vous pourrez ensuite sélectionner le mode d'interface utilisateur que vous souhaitez utiliser : console ou graphique.

## Modèles

- Feu : Simule la propagation d'un feu dans une forêt.
- Conway : Implémente le Jeu de la Vie de Conway.
- 1D : Un automate cellulaire unidimensionnel.

## Interface Utilisateur

L'interface utilisateur invite l'utilisateur à sélectionner un modèle et à définir les paramètres. Les paramètres comprennent le nombre de colonnes et de rangées pour la grille, le nombre de voisins à considérer, et le numéro de règle pour le modèle 1D.

## Equipe 

- PONTON Mathieu
- VEGA BACA Sara
- RIGONNET Arthur