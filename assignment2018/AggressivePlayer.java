package assignment2018;

import java.util.*;

import assignment2018.codeprovided.*;

/**
 * AggressivePlayer.java
 * 
 * Represents an aggressive player (Takes the piece with the highest value) 
 * otherwise move is random
 * 
 * @author Panagiotis Antoniou
 *
 */

public class AggressivePlayer extends Player{
    
    //instance variables
    private int movePosition;
    private Move move;
    private ArrayList<Move> availableMoves;
    private ArrayList<Move> conquerMoves;
    
    /**
     * Constructs an aggressive player
     * 
     * @param name the name of the player
     * @param pieces the pieces used for the player 
     * @param board the chess board
     * @param opp the opponent Player
     */
    public AggressivePlayer(String name, Pieces pieces, Board board, Player opp) {
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
     * Check if there are conquer moves (that conquer a piece) available in the game
     * @return true if they exist, false if otherwise
     */
    
    public boolean conquerMovesAvailable() {
        //go through the availableMoves
        ListIterator<Move> availableMove= availableMoves.listIterator();
        
        //check if there are any conquer moves and add them to conquerMoves arraylist
        conquerMoves=new ArrayList<Move>();
        while (availableMove.hasNext()) {
            Move m=availableMove.next();   		
            if (getBoard().getPiece(m.getToX(), m.getToY())==null){
                continue;
            }
            else
                conquerMoves.add(m);
        }
        return !conquerMoves.isEmpty();
    }
    
    /**
     * finds the highest value move
     * @return the highest value move
     */
    public Move getHighestValueMove() {
        int maxValue=0;
        int moveNum=0;
        
        //find the maximum value of the piece that can be taken 
        //and store the number of position of the move in the arraylist
        for (int i=0; i<conquerMoves.size(); i++) {
            maxValue=Math.max(maxValue,conquerMoves.get(i).getPiece().getValue());
            if (conquerMoves.get(i).getPiece().getValue()==maxValue)
                moveNum=i;
        }
   
        return conquerMoves.get(moveNum);
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
            
            //if there are conquerMoves available find the highest value move
            if (conquerMovesAvailable()) {
                move=getHighestValueMove();	
            }
            
            //else find a random move
            else {
                move=findRandomMove();
            }

            //perform the move
            getBoard().deletePiece(move.getFromX(), move.getFromY());
            getBoard().setPosition(move.getToX(), move.getToY(),move.getPiece());
        }
        else 
            return false;

        return legalMovesAvailable;
    }
}
