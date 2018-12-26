package assignment2018;

import java.util.*;

import assignment2018.codeprovided.*;

/**
 * RandomPlayer.java
 * 
 * Represents a random player (makes a random move) 
 * 
 * @author Panagiotis Antoniou
 *
 */

public class RandomPlayer extends Player{
    
    //instance variables
    private int movePosition;
    private Move move;
    private ArrayList<Move> availableMoves;
    
    
    /**
     * Constructs a random player
     * 
     * @param name the name of the player
     * @param pieces the pieces used for the player 
     * @param board the chess board
     * @param opp the opponent Player
     */
    public RandomPlayer(String name, Pieces pieces, Board board, Player opp) {
        super(name,pieces,board,opp);
    }

    //get methods
    
    /**
     * gets the move position
     * @return movePosition as integer
     */
    public int getMovePosition() {
        return movePosition;
    }    
    
    /**
     * gets the move
     * @return move in Move class
     */
    public Move getMove() {
        return move;
    }

    //set methods 
    
    /**
     * sets the move position
     * @param integer movePos that represents the move position
     */
    public void setMovePosition(int movePos) {
        movePosition=movePos;
    }
    
    /**
     * sets the move 
     * @param Move object m that represents the move
     */
    public void setMove(Move m) {
        move=m;
    }
    
    /**
     * Check if there are legal moves available in the game
     * @return true if they exist, false if otherwise
     */
    public boolean legalMovesExist() {
        availableMoves=new ArrayList<Move>();
        for (int j=0; j<8; j++) {
            for (int i=0; i<8; i++) {
                Piece piece=getBoard().getPiece(i, j);
                if ((piece==null)||piece.availableMoves()==null) {					
                    continue;
                }
                else 
                    if (piece.getColour()==this.getPieces().getPiece(0).getColour()) 
                        availableMoves.addAll(piece.availableMoves());					
            }
        }
        return !availableMoves.isEmpty();
    }
    
    /**
     * finds random move    
     * @return the random move
     */
    public Move findRandomMove() {   
      //finds a random movePosition based on the available moves arraylist
        movePosition=(int)(Math.random()*(availableMoves.size()));
        move=availableMoves.get(movePosition);
        
        return move;
    }

    /**
     * Method to make a move
     * @return true if there are legal moves available false if otherwise
     */    
    public boolean makeMove() {
        boolean legalMovesAvailable=legalMovesExist();
        if (legalMovesAvailable)  {
            
            //if there are legal moves find a random move
            move=findRandomMove();	
            
            //perform the move
            getBoard().deletePiece(move.getFromX(), move.getFromY());
            getBoard().setPosition(move.getToX(), move.getToY(),move.getPiece());
        }
        else 
            return false;

        return legalMovesAvailable;
    }
}
