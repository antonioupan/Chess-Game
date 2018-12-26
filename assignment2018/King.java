package assignment2018;
import assignment2018.codeprovided.*;
import java.util.*;

/**
 * King.java
 *
 * Concrete class to represent a king
 *
 * @author Panagiotis Antoniou
 * 
 */

public class King extends Piece {
    
    //constants that represent width and height of board
    private static final int BOARD_X=8;
    private static final int BOARD_Y=8;
    
    /**
     * Constructor that constructs a king piece
     * @param ix
     * @param iy
     * @param c
     * @param b
     */
    
    public King(int ix, int iy, int c, Board b) {
        super(PieceCode.KING, ix, iy, c, b);
    }

    /**
     * method implements abstract availableMoves method in Piece class
     * 
     * @return a list of available moves 
     * 
     */
    public ArrayList<Move> availableMoves() {
        return king();
    }

    /**
     * method to return list of legal moves for a king class
     * 
     * @return a list of available moves 
     * 
     */
    private ArrayList<Move> king() {
        // obtain current co-ordinates
        int x = this.getX();
        int y = this.getY();

        // otherwise create a new vector to store legal kingMoves
        ArrayList<Move> kingMoves = new ArrayList<Move>();

        // set up theMove to refer to a Move object
        Move theMove = null;
        
        //first available move going up
        if (y<BOARD_Y-1) {
          //if it is, perform a move either when a piece does not occupy the 
            //position or when there is a piece of different colour in the position
            if (!getBoard().occupied(x, y+1)||(conquerMove(x,y+1))) {
                theMove = new Move(this, x, y, x, y+1, false);
                kingMoves.add(theMove);
            }
        }
        
        //a move going down
        if (y>0) {
            if (!getBoard().occupied(x, y-1)||(conquerMove(x,y-1))) {
                theMove = new Move(this, x, y, x, y-1, false);
                kingMoves.add(theMove);
            }
        }
        
        //a move going right
        if (x<BOARD_X-1) {
            if (!getBoard().occupied(x+1, y)||(conquerMove(x+1,y))) {
                theMove = new Move(this, x, y, x+1, y, false);
                kingMoves.add(theMove);
            }
        }
        
        //a move going left
        if (x>0) {
            if (!getBoard().occupied(x-1, y)||(conquerMove(x-1,y))) {
                theMove = new Move(this, x, y, x-1, y, false);
                kingMoves.add(theMove);
            }
        }
        
        //a move going right-up diagonally
        if (x<BOARD_X-1 && y<BOARD_Y-1) {
            if (!getBoard().occupied(x+1, y+1)||(conquerMove(x+1,y+1))) {
                theMove = new Move(this, x, y, x+1, y+1, false);
                kingMoves.add(theMove);
            }
        }
        
        //a move going right-down diagonally
        if (x<BOARD_X-1 && y>0) {
            if (!getBoard().occupied(x+1, y-1)||(conquerMove(x+1,y-1)))  {
                theMove = new Move(this, x, y, x+1, y-1, false);
                kingMoves.add(theMove);
            }
        }
        
        //a move going left-up diagonally
        if (x>0 && y<BOARD_Y-1) {
            if (!getBoard().occupied(x-1, y+1)||(conquerMove(x-1,y+1)))  {
                theMove = new Move(this, x, y, x-1, y+1, false);
                kingMoves.add(theMove);
            }
        }
        
        //a move going left-down diagonally
        if (x>0 && y>0) {
            if (!getBoard().occupied(x-1, y-1)||(conquerMove(x-1,y-1)))  {
                theMove = new Move(this, x, y, x-1, y-1, false);
                kingMoves.add(theMove);
            }
        }

        if (kingMoves.isEmpty())
            return null;
        return kingMoves;
    }
    


    /**
     * conquerMove is represented when there is a piece of the opposite colour
     * in the next move
     * 
     * @param newX
     * @param newY
     * 
     * @return true when there is a conquerMove otherwise it is false
     */
    private boolean conquerMove (int newX, int newY) {
        return (!getBoard().outOfRange(newX, newY) && getBoard().occupied(newX, newY)
                && (getBoard().getPiece(newX, newY).getColour() != this.getColour()));
    }
}
