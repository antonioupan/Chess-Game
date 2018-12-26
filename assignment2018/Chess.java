package assignment2018;
import javax.swing.JOptionPane;

import assignment2018.codeprovided.*;

/**
 * Chess.java
 *
 * Main class to represent chess game
 *
 * @author Panagiotis Antoniou
 * 
 */

public class Chess{	
    
    //instance variables
    private static Board theBoard;
    private static Pieces pieces1;
    private static Pieces pieces2;
    private static Player playerOne;
    private static Player playerTwo;
    private static GraphicalDisplay displayGame;
    private static String displayClass;
    
    public static String getDisplayClass() {
        return displayClass;
    }

    public static void setDisplayClass(String n) {
        displayClass=n;
    }
    
    /**
     * Static method to initialise the chess board
     */
    public static void initialiseBoard() {
        //initialise board and display
        theBoard= new Board();
        displayGame=new GraphicalDisplay();
        //configure the players 
        playerOne= playerOneConfigured();
        playerTwo= playerTwoConfigured();	
        playerOne.setOpponent(playerTwo);
    }
    
    /**
     * Check if the king exists in the game for the specific player
     * @param player 
     * @return true if the king exists false if otherwise
     */
    public static boolean checkKingExists(Player player) {
        for (int j=0; j<8; j++) {
            for (int i=0; i<8; i++) {
                if (player.getBoard().getPiece(i, j)==player.getPieces().getPiece(15)) 
                    return true;				
            }
        }
        //if the king doesn't exist then display the winner.
        displayGame.displayWinner(player.getOpponent());
        return false;
    }
    
    /**
     * Configures playerOne based on the input of the user in a JOption window
     * @return playerOne as Player type
     */
    public static Player playerOneConfigured() {		
        //get the colour of the pieces for player 1
        String[] colour = {"White", "Black"};
        int x = JOptionPane.showOptionDialog(null, "Choose the colour of your pieces:",
                "Player One",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, colour, colour[0]);
        
        //catch any exceptions if no colours are given
        try {
            if (colour[x]=="White") {
                pieces1=new Pieces(theBoard,PieceCode.WHITE);
            }
            else
                pieces1=new Pieces(theBoard,PieceCode.BLACK);
        }
        catch (ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(
                    null, 
                    "Thank you",
                    "Game Over",
                    JOptionPane.PLAIN_MESSAGE
                    );
            System.exit(0);

        }

        //get the name of player 1
        //catch any exceptions if no name is given
        try {
            String name=null;
            while (name==null||name.length()==0) {
                name= JOptionPane.showInputDialog(
                        null, "Player One: Please Enter your name:", 
                        "Player 1",
                        JOptionPane.INFORMATION_MESSAGE
                        ).replaceAll(" ","");
            }
            playerOne=new HumanPlayer(name,pieces1,theBoard,null);
        }
        
        catch (NullPointerException e) {
            JOptionPane.showMessageDialog(
                    null, 
                    "Thank you",
                    "Game Over",
                    JOptionPane.PLAIN_MESSAGE
                    );
            System.exit(0);
        }

        return playerOne;
    }

    /**
     * Configures playerTwo based on the input of the user in a JOption window
     * @return playerTwo as Player type
     */
    
    public static Player playerTwoConfigured() {		
        //declare the colour of the pieces of player2
        if (pieces1.getPiece(0).getColour()==0) {
            pieces2=new Pieces(theBoard,PieceCode.WHITE);
        }
        else {
            pieces2=new Pieces(theBoard,PieceCode.BLACK);
        }
        
        //choose Player type for opponent
        String[] playerType = {"Human Player", "Random Player", "Aggressive Player"};
        int x = JOptionPane.showOptionDialog(null, "Choose the type of your opponent:",
                "Opponent type",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, playerType, playerType[0]);

        //get the opponent type
        try {
            //get the name of player 2 if it is a human player
            if (playerType[x]=="Human Player") {
                try {
                    String name = null;
                    while (name==null||name.length()==0)
                        name=JOptionPane.showInputDialog(
                                null, "Player Two: Please Enter your name:", 
                                "Player 2",
                                JOptionPane.INFORMATION_MESSAGE
                                ).replaceAll(" ","");				
                    playerTwo=new HumanPlayer(name,pieces2,theBoard,playerOne);
                }
                catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(
                            null, 
                            "Thank you",
                            "Game Over",
                            JOptionPane.PLAIN_MESSAGE
                            );
                    System.exit(0);
                }
            }
            
            //generate a Random Player
            else if (playerType[x]=="Random Player") {
                playerTwo=new RandomPlayer("Random Bot",pieces2,theBoard,playerOne);
            }
            
            //generate an Aggressive Player
            else {
                playerTwo=new AggressivePlayer("Aggressive Bot",pieces2,theBoard,playerOne);
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(
                    null, 
                    "Thank you",
                    "Game Over",
                    JOptionPane.PLAIN_MESSAGE
                    );
            System.exit(0);
        }    
        return playerTwo;
    }

    /**
     * Main method
     * @param args not used
     */
    
    public static void main(String[] args) {

        initialiseBoard();
        
        //A guide of which is the black and white pieces for text display
        if (displayGame.getClass().getName()=="TextDisplay") {//(MoveDisplay.getDisplayClass()=="Text") {
            System.out.println("Capital letters represent Black pieces");
            System.out.println("Small letters represent White pieces");
        }
        
        //display the game
        try {
            displayGame.displayBoard(playerOne.getPieces());
        }
        catch (NullPointerException e) {
            JOptionPane.showMessageDialog(
                    null, 
                    "Thank you",
                    "Game Over",
                    JOptionPane.PLAIN_MESSAGE
                    );
            System.exit(0);
        }

        //check that there is a king for player 1
        while (checkKingExists(playerOne)) {
            //player 1 makes a move and checks if there are available moves
            if (playerOne.makeMove()) {
                displayGame.displayBoard(playerTwo.getPieces());
                //check that there is a king for player 2
                if (!checkKingExists(playerTwo))
                    break;
                //player 2 makes a move
                playerTwo.makeMove();
                displayGame.displayBoard(playerOne.getPieces());
            }
            else {
                //if there are no legal moves available in the game display a message
                JOptionPane.showMessageDialog(
                        null, 
                        "Out of Moves",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE
                        );
                break;
            }			
        }
    }
}
