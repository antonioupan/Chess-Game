package assignment2018;
import assignment2018.codeprovided.*;
/**
 * TextDisplay.java
 *
 * Class that represents the display of the game in console and implements 
 * Display.java to use the method that displays the board
 *
 * @author Panagiotis Antoniou 
 */

public class TextDisplay implements Display {
    
    //constants that are used to represent width and height of board
    private final int BOARD_X = 8;
    private final int BOARD_Y = 8;
    
    //instance variable
    private Board board;
    
    /**
     * 
     * Constructor that sets the display class to text
     * 
     */
    public TextDisplay() {
        //set the name of the display class to Text
        Chess.setDisplayClass( "Text" );
    }
    
    /**
     * method that displays the board in the console
     * 
     * @param Pieces myPieces, display the board based on the 
     * pieces supplied.
     */
    public void displayBoard(Pieces myPieces) {
        //get the board from the pieces supplied as a parameter
        board=myPieces.getPiece(0).getBoard();
        
        //display the board
        System.out.println(" |ABCDEFGH");
        System.out.println("----------");
        for (int j=0; j<BOARD_Y; j++) {
            if (j>0)
                System.out.println();
            System.out.print((BOARD_X-j)+"|");
            for (int i=0; i<BOARD_X; i++) {
                //check if the piece is null
                if (board.getPiece(i, j)==null)
                    System.out.print(".");
                else
                    System.out.print(board.getPiece(i, j).getChar());
            }
        }
        
        System.out.println();
        System.out.println();
    }
    
    /**
     * method that displays the winner of the game in console
     * 
     * @param the player that won the game 
     */
    public void displayWinner(Player player) {
        System.out.println(player.getOpponent().toString() + " lost.");
        System.out.println("Winner is "+ player.toString() + ".");
    }
}
