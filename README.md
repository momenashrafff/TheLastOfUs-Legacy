# The Last of Us: Legacy

## Introduction

The Last of Us: Legacy is a single-player survival game set in a zombie apocalyptic world. The game is inspired by the popular video game "The Last of Us" and is conducted in a turn-based manner. Each player character receives a specific number of action points per turn, which they can use to move, attack or cure zombies, or use special actions.

The objective of the game is for the player to survive as long as possible and cure a sufficient number of zombies to build a community capable of surviving the apocalypse.

## Characters

Characters in the game are split into Heroes and Zombies.

### Heroes

Heroes are the characters that the player can control. There are several types of heroes available in the game, each providing different assets to help the player succeed. Each hero has an initial amount of health, which decreases whenever they are attacked. If a hero's health reaches 0, they are killed and removed from the game.

The player starts the game controlling only one hero but can gain additional heroes by curing zombies. Each hero type has a unique action that can be added to the player's team:

- **Explorer**: Allows the player to see the entire map for 1 turn whenever a supply is used.
- **Medic**: Can heal and restore health to other heroes or themselves. Each healing process uses 1 supply.
- **Fighter**: Can attack multiple times in a turn without costing action points for 1 turn whenever a supply is used.

Possible actions that can be performed by a hero:
- Move
- Attack a zombie
- Cure a zombie
- Use their class-dependent unique action

### Zombies

Zombies are the characters that pose a threat to the player during the game. Zombies cannot be controlled, but they can be cured or attacked. Each time a zombie is killed, another zombie will spawn somewhere on the map. Additional zombies also spawn at the end of each player turn.

Whenever a zombie is cured, an extra hero will take its place and be available for the player to use in future turns.

## Collectibles

Collectibles are scattered objects across the map that can help the player survive and advance in the game. Each collectible is usable only once and is then discarded from the hero's inventory, making it unavailable for reuse.

- **Vaccines**: Vaccines are essential for winning the game. The player can only win once all vaccines have been collected and used. Vaccines are also the means through which players can cure zombies and recruit new heroes.

- **Supplies**: Supplies are another type of collectible available in the game. They enable the carrying hero to use their special action.

## Gameplay Flow

The game starts with the player in a 15x15 grid map, controlling one hero and facing 10 zombies. Initially, the player can only see the directly adjacent cells next to their pool of heroes. The player takes turns, attempting to collect vaccines, cure or kill zombies, and expand their community.

The game follows the following controls:

- Move the hero using the keyboard: "W" (up), "S" (down), "A" (left), "D" (right).
- Select a hero to control by pressing the left mouse button on it.
- Select a zombie as a target by pressing the left mouse button on it.
- Select a hero as a target by pressing the right mouse button on it.
- To perform actions such as "Attack," "Use Special," or "Cure," the player must be in an adjacent cell to the target.
- Use supplies to activate special actions, and use vaccines to cure zombies.
- When the player ends their turn, the available actions are reset, and one zombie is spawned randomly on the map.

The game ends when the player has collected and used all vaccines or when all heroes have been overwhelmed and defeated by the zombies.

The player wins only if they have successfully collected and used all vaccines and have 5 or more heroes alive.

## Gameplay Screenshots

Here are some screenshots from the game:

![Main Menu](Screenshots/MainMenu.png)
![Champion Selection](Screenshots/ChampionSelection.png)
![Game Play 1](Screenshots/GamePlay1.png)
![Game Play 2](Screenshots/GamePlay2.png)

## Project Information

This game was developed using JavaFX SDK 20.0.1.

To run the game, you will need to download the JavaFX SDK from [this link](https://gluonhq.com/products/javafx/) and install it based on your IDE. Detailed installation instructions can be found [here](https://openjfx.io/openjfx-docs/).

Once you have installed the JavaFX SDK, you can compile and run the game code using your preferred Java IDE.

Note: Make sure to set up the JavaFX SDK correctly in your IDE to ensure the game runs smoothly.

Enjoy playing The Last of Us: Legacy!
