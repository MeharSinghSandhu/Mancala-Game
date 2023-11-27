Mehar Singh

SID - 1247922

Resources used - brocode swing video('https://www.youtube.com/watch?v=Kmgo00avvEw&t=2721s'), lecture slides , javadocs

This Mancala Game project is a Java-based desktop application that simulates the ancient board game Mancala with a graphical user interface (GUI) using Swing. It features two distinct rulesets: Kalah Rules and Ayo Rules, offering varied gameplay experiences. The application is designed with an Object-Oriented Programming (OOP) approach


Features:- 

- Two Rulesets: Play with either Kalah Rules or Ayo Rules for varied strategic gameplay.
- Graphical User Interface: Developed using Swing, the GUI provides an intuitive and visually appealing way to interact with the game.
- Game Persistence: Save and load game progress using the Saver class.
- Player Profiles: Manage player information and statistics through UserProfile class.
- Flexible UI: A TextUI class offers an alternative way to interact with the game, suitable for debugging or different user preferences.

Java Files Overview

- AyoRules.java: Implements the specific rules and mechanics of the Ayo variant of Mancala.
- Countable.java: An interface defining methods for objects that can be counted, such as pits or stores.
- GameNotOverException.java: Custom exception to signal that the game is not over when an end condition is checked prematurely.
- GameRules.java: Abstract class that outlines the structure and necessary methods for game rule variants.
- InvalidMoveException.java: Custom exception to handle invalid moves within the game.
- KalahRules.java: Contains the rules and logic specific to the Kalah variant of Mancala.
- MancalaDataStructure.java: Represents the data structure of the Mancala board, managing pits and stores.
- MancalaGame.java: Central class managing the game state, interactions, and progression.
- NoSuchPlayerException.java: Exception thrown when an operation refers to a player that does not exist.
- Pit.java: Represents a single pit on the Mancala board and manages its stones.
- PitNotFoundException.java: Exception thrown when a specific pit is referred to but does not exist.
- Player.java: Defines a player in the game, holding related data such as the player's store.
- PositionAwareButton.java: Extends JButton to include positional awareness in the game GUI.
- Saver.java: Handles the saving and loading of game states to and from a persistent storage.
- Store.java: Specialized pit that acts as a store for captured stones in the game.
- UserProfile.java: Manages user information and statistics, such as games played and won.

