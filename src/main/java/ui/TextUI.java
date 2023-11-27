package ui;



import mancala.MancalaGame;
import mancala.AyoRules; 
import mancala.InvalidMoveException;
import mancala.KalahRules;
import mancala.MancalaDataStructure;
import mancala.Saver;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.stream.IntStream;
import mancala.Player;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import mancala.Saver;
import java.io.Serializable;

public class TextUI {
    private MancalaGame mancalaGame;
    private JButton[] pitButtons;
    private JFrame mainMenuFrame;
    private JFrame gameBoardFrame;

    private Player playerOne = new Player("Sandhu");
    private Player playerTwo = new Player("Kaput");

    public TextUI() {
        mancalaGame = new MancalaGame();
        initializeMainMenu();
    }

    private void initializeMainMenu() {
        mainMenuFrame = new JFrame("Mancala Game");
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setSize(800, 600);
        mainMenuFrame.setLayout(new BorderLayout());

        JMenuBar menu = new JMenuBar();

        JMenu file = new JMenu("File");

        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");
        file.add(save);
        save.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int res  = chooser.showSaveDialog(null);

            if(res == JFileChooser.APPROVE_OPTION){
                String name = chooser.getSelectedFile().getName();
                Saver.saveObject(mancalaGame,name);
            }
        });
        file.add(load);
        load.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int res  = chooser.showOpenDialog(null);

            if(res == JFileChooser.APPROVE_OPTION){
                String name = chooser.getSelectedFile().getName();
                Serializable info = Saver.loadObject(name);
                mancalaGame =(MancalaGame)info;
                refreshGameBoard();
            }  
        
        });
        menu.add(file);

        mainMenuFrame.setJMenuBar(menu);
    

        // Main menu buttons
        JButton kalahButton = new JButton("Kalah");
        JButton ayoButton = new JButton("Ayo");
        
        JButton quitGameButton = new JButton("Quit Game");

        kalahButton.addActionListener(e -> showGameBoard("Kalah"));
        ayoButton.addActionListener(e -> showGameBoard("Ayo"));
        
        quitGameButton.addActionListener(e -> System.exit(0));

        JPanel menuPanel = new JPanel(new FlowLayout());
        menuPanel.add(kalahButton);
        menuPanel.add(ayoButton);
        
        menuPanel.add(quitGameButton);

        mainMenuFrame.add(menuPanel, BorderLayout.NORTH);
        mainMenuFrame.setVisible(true);
    }
    private void showGameBoard(String gameType) {
    // Set the game rules based on the selected game type
    if ("Kalah".equals(gameType)) {
        mancalaGame.setBoard(new KalahRules());
    } else if ("Ayo".equals(gameType)) {
        mancalaGame.setBoard(new AyoRules());
    } else {
        throw new IllegalArgumentException("Invalid game type: " + gameType);
    }

   // mancalaGame.startNewGame();

    // Initialize the game board GUI components
    if (gameBoardFrame == null) {
        gameBoardFrame = new JFrame(gameType);
        gameBoardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameBoardFrame.setSize(800, 600);
        gameBoardFrame.setLayout(new BorderLayout());

        initializeBoardComponents();
    }

    refreshGameBoard();
    mainMenuFrame.setVisible(false);
    gameBoardFrame.setVisible(true);
}

// private void saveGame() {
//     String fileName = JOptionPane.showInputDialog(gameBoardFrame, "Enter filename to save:", "Save Game", JOptionPane.QUESTION_MESSAGE);
//     if (fileName != null && !fileName.trim().isEmpty()) {
//         try {
//             FileOutputStream fileOut = new FileOutputStream(Paths.get("assets", fileName).toString());
//             ObjectOutputStream out = new ObjectOutputStream(fileOut);
//             out.writeObject(mancalaGame);
//             out.close();
//             fileOut.close();
//             JOptionPane.showMessageDialog(gameBoardFrame, "Game saved successfully.", "Save Successful", JOptionPane.INFORMATION_MESSAGE);
//         } catch (Exception ex) {
//             JOptionPane.showMessageDialog(gameBoardFrame, "Error saving game: " + ex.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
//         }
//     }
// }


private void startNewGame() {
    mancalaGame.startNewGame();
    refreshGameBoard();
}

private void initializeBoardComponents() {
    // Initialize the pit buttons array to hold all pit and store buttons
    mancalaGame.setPlayers(playerOne,playerTwo);
    JPanel controlPanel = new JPanel();
    JButton newGameButton = new JButton("New Game");

    JButton quitGameButton = new JButton("Quit Game");

    newGameButton.addActionListener(e -> startNewGame());

    quitGameButton.addActionListener(e -> System.exit(0));

    controlPanel.add(newGameButton);
    
    controlPanel.add(quitGameButton);

    gameBoardFrame.add(controlPanel, BorderLayout.SOUTH);

    pitButtons = new JButton[14 ]; // 12 pits + 2 stores
    
    // Create and add the stores
    pitButtons[12] = new JButton();
    pitButtons[13] = new JButton();
    pitButtons[12].setText(String.valueOf(mancalaGame.getStoreCount(mancalaGame.getPlayers().get(0))));
    pitButtons[13].setText(String.valueOf(mancalaGame.getStoreCount(mancalaGame.getPlayers().get(1))));

    // Customize the store buttons (size, font, etc.)
    pitButtons[12].setPreferredSize(new Dimension(100, 100)); // Example size
    pitButtons[13].setPreferredSize(new Dimension(100, 100)); // Example size

    // Add stores to the frame
    gameBoardFrame.add(pitButtons[12], BorderLayout.WEST);
    gameBoardFrame.add(pitButtons[13], BorderLayout.EAST);

    // Create a panel to hold the pit buttons
    JPanel pitsPanel = new JPanel(new GridLayout(2, 6)); // Assuming a 2x6 grid for the pits

    // Create pit buttons, configure them, and add to the panel
    for (int i = 0; i < 12; i++) {
        int pitIndex = i + 1;
        pitButtons[i] = new JButton();
        pitButtons[i].setText(Integer.toString(mancalaGame.getNumStones(pitIndex)));
        pitButtons[i].putClientProperty("Pit", pitIndex);
        // Set any additional properties for the pit buttons here
        pitButtons[i].setPreferredSize(new Dimension(80, 80)); // Example size

        // Add action listeners for the pit buttons
        pitButtons[i].addActionListener(e -> onPitClick(e));

        // Add the pit button to the panel
        pitsPanel.add(pitButtons[i]);
    }

    // Add the pits panel to the frame
    gameBoardFrame.add(pitsPanel, BorderLayout.CENTER);
}
    private void refreshGameBoard() {
       for (int i = 1; i <= 12; i++) {
            pitButtons[i - 1].setText("" + mancalaGame.getNumStones(i));
        }
        // Update the stores
        if (!mancalaGame.getPlayers().isEmpty()) {
    pitButtons[12] = new PositionAwareButton(Integer.toString(mancalaGame.getStoreCount(mancalaGame.getPlayers().get(0))));
    pitButtons[13] = new PositionAwareButton(Integer.toString(mancalaGame.getStoreCount(mancalaGame.getPlayers().get(1))));
}
    }
    private void onPitClick(ActionEvent e) {
    if (e.getSource() instanceof PositionAwareButton) {
        // PositionAwareButton clickedButton = (PositionAwareButton) e.getSource();
        JButton clickedButton = (JButton) e.getSource();
        // Assuming that the pit index is stored in the PositionAwareButton
        int pitIndex = (int) clickedButton.getClientProperty("Pit");

        try {
            // Attempt to make a move using the MancalaGame class
            int additionalTurns = mancalaGame.move(pitIndex);
            refreshGameBoard(); // Update the UI with the new game state

            // Check if the game is over after the move
            if (mancalaGame.isGameOver()) {
                handleGameOver(); // Method to handle game over scenario
                return; // Exit the method to prevent further actions after game over
            }

            // If there are no additional turns, switch to the next player
            if (additionalTurns == 0) {
                switchToNextPlayer();
            } else {
                // Optionally handle the scenario for additional turns, if the game rules allow it
            }
        } catch (InvalidMoveException ex) {
            JOptionPane.showMessageDialog(gameBoardFrame, ex.getMessage(), "Invalid Move", JOptionPane.ERROR_MESSAGE);
        }
    }
}

private void handleGameOver() {
    // You can implement logic to display the winner and prompt for a new game
    Player winner = mancalaGame.getWinner();
    if (winner != null) {
        JOptionPane.showMessageDialog(gameBoardFrame, winner.getName() + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(gameBoardFrame, "It's a tie!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Additional logic to reset the game or return to the main menu
}




private void switchToNextPlayer() {
    // This method would switch the current player in the MancalaGame instance
    Player currentPlayer = mancalaGame.getCurrentPlayer();
    Player nextPlayer = (currentPlayer == mancalaGame.getPlayers().get(0)) ? mancalaGame.getPlayers().get(1) : mancalaGame.getPlayers().get(1);
    mancalaGame.setCurrentPlayer(nextPlayer);
    
    // Optionally update any relevant UI elements to indicate the current player
}

    public static void main(String[] args) {
        new TextUI(); // Start the application
    }
}


// >java -jar build/libs/TextUI.jar



// private void render(){
//         playerOneStore.setText(String.valueOf(game.getPlayerOne().getStoreCount()));
//         playerTwoStore.setText(String.valueOf(game.getPlayerTwo().getStoreCount()));
//         int j = 12;
//         for(int i = 1; i < NUM_PITS + 1; i++){
//             playerOnePits[i-1].setText(String.valueOf(game.getNumStones(i)));
//             playerOnePits[i-1].putClientProperty("Index",i);
//             playerTwoPits[i-1].setText(String.valueOf(game.getNumStones(j)));
//             playerTwoPits[i-1].putClientProperty("Index",j);
//             j--;
//             playerOnePits[i-1].addActionListener(e -> {
//                 try{
//                     JButton butt =(JButton) e.getSource();
//                     int index = (int) butt.getClientProperty("Index");
//                     game.move(index);
//                 }catch(InvalidMoveException f){
//                     f.printStackTrace();
//                 }
//                 render();
//             });
//             playerTwoPits[i-1].addActionListener(e -> {
//                 try{
//                     JButton butt =(JButton) e.getSource();
//                     int index = (int) butt.getClientProperty("Index");
//                     game.move(index);
//                 }catch(InvalidMoveException f){
//                     f.printStackTrace();
//                 }
//                 render();
//             });

//     }
// }






