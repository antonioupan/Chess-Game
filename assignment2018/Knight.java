package assignment2018;
import assignment2018.codeprovided.*;
import java.util.*;

/**
 * Knight.java
 *
 * Concrete class to represent a knight
 *
 * @author Panagiotis Antoniou
 * 
 */

public class Knight extends Piece {
    
    //constants that represent width and height of board
    private static final int BOARD_X=8;
    private static final int BOARD_Y=8;
    
    /**
     * Constructor that constructs a knight piece
     * @param ix
     * @param iy
     * @param c
     * @param b
     */
    
    public Knight(int ix, int iy, int c, Board b) {
        super(PieceCode.KNIGHT, ix, iy, c, b);
    }

    /**
     * method implements abstract availableMoves method in Piece class
     * 
     * @return a list of available moves 
     * 
     */
    public ArrayList<Move> availableMoves() {
        return knight();
    }

    /**
     * method to return list of legal moves for a knight class
     * 
     * @return a list of available moves 
     * 
     */
    private ArrayList<Move> knight() {
        // obtain current co-ordinates
        int x = this.getX();
        int y = this.getY();

        // otherwise create a new vector to store legal knightMoves
        ArrayList<Move> knightMoves = new ArrayList<Move>();

        // set up theMove to refer to a Move object
        Move theMove = null;

        if (x<BOARD_X-1 && y<BOARD_Y-2) {
            //if it is, perform a move either when a piece does not occupy the 
            //position or when there is a piece of different colour in the position
            if (!getBoard().occupied(x+1, y + 2)||(conquerMove(x+1,y+2))) {
                theMove = new Move(this, x, y, x+1, y+2, false);
                knightMoves.add(theMove);
            }
        }

        if (x<BOARD_X-2 && y<BOARD_Y-1) {
            if (!getBoard().occupied(x+2,y+1)||(conquerMove(x+2,y+1))) {
                theMove = new Move(this, x,y,x+2,y+1, false);
                knightMoves.add(theMove);
            }
        }

        if (x<BOARD_X-2 && y>0) {
            if (!getBoard().occupied(x+2,y-1)||(conquerMove(x+2,y-1))) {
                theMove = new Move(this, x, y, x+2,y-1, false);
                knightMoves.add(theMove);
            }
        }
        
        if (x<BOARD_X-1 && y>1) {
            if (!getBoard().occupied(x+1,y-2)||(conquerMove(x+1,y-2))) {
                theMove = new Move(this, x, y,x+1,y-2, false);
                knightMoves.add(theMove);
            }
        }

        if (x>0 && y>1) {
            if (!getBoard().occupied(x-1,y-2)||(conquerMove(x-1,y-2))){
                theMove = new Move(this, x, y, x-1,y-2, false);
                knightMoves.add(theMove);
            }
        }

        if (x>1 && y>0) {
            if (!getBoard().occupied(x-2,y-1)||(conquerMove(x-2,y-1))){
                theMove = new Move(this, x, y, x-2,y-1, false);
                knightMoves.add(theMove);
            }
        }

        if (x>1 && y<BOARD_Y-1) {
            if (!getBoard().occupied(x-2,y+1)||(conquerMove(x-2,y+1))) {
                theMove = new Move(this, x, y,x-2,y+1, false);
                knightMoves.add(theMove);
            }
        }

        if (x>0 && y<BOARD_Y-2) {
            if (!getBoard().occupied(x-1,y+2)||(conquerMove(x-1,y+2))){
                theMove = new Move(this, x, y, x-1,y+2, false);
                knightMoves.add(theMove);
            }
        }

        if (knightMoves.isEmpty())
            return null;
        return knightMoves;
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
